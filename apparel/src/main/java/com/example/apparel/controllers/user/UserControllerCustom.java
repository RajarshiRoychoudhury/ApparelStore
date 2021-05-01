package com.example.apparel.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.example.apparel.api.response.MessageResponse;
import com.example.apparel.model.HomePage;
import com.example.apparel.model.Item;
import com.example.apparel.model.ListSearchHistory;
import com.example.apparel.model.PQueue;
import com.example.apparel.model.SearchHistory;
import com.example.apparel.model.Cart;
import com.example.apparel.model.CartItem;
import com.example.apparel.repository.CartRepository;
import com.example.apparel.repository.ItemRepository;
import com.example.apparel.repository.ListSearchHistoryRepository;
import com.example.apparel.security.services.AppUserDetails;




@RestController
@RequestMapping("/api/user")
@CrossOrigin("/*")
public class UserControllerCustom {
	
	
	//things to be stored in a session : number of times discounted / new arrival. default based on gender. also keep track of each item
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ListSearchHistoryRepository lshrepository;
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/home")
	public ResponseEntity<?> getHomePage(HttpServletRequest req) {
		Item[] nA = this.newArrivals(req);
		Item[] d = this.discounted(req);
		Item[] b = this.based(req);
		return ResponseEntity.ok(new HomePage(d,nA,b));
	}
	
	
	public Item[] newArrivals(HttpServletRequest req){

		try {
					AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					Item[] itemlist= itemRepository.findAll().
					stream().
					filter(item -> item.getGender().equals(userdetails.getGender()))
					.filter(item-> item.getDate().before(new Date()))
					.filter(item->item.getStock() >0)
					.limit(20)
					.toArray(Item[]::new);
					return itemlist;
			}
			catch(Exception e) {
				Item[] itemlist= itemRepository.findAll().
				stream().
				filter(item -> item.getGender().equals("F"))
				.filter(item-> item.getDate().before(new Date()))
				.filter(item->item.getStock() >0)
				.limit(20)
				.toArray(Item[]::new);
				System.out.println(e);
				return itemlist;
			}

		}

	public Item[] discounted(HttpServletRequest req){

			try {
					AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					Item[] itemlist= itemRepository.findAll().
					stream().
					filter(item -> item.getGender().equals(userdetails.getGender()))
					.filter(item-> item.getHasDiscount())
					.filter(item->item.getStock() >0)
					.limit(30)
					.toArray(Item[]::new);
					return itemlist;
			}
			catch(Exception e) {
				System.out.println(e);
				Item[] itemlist= itemRepository.findAll().
				stream().
				filter(item -> item.getGender().equals("F"))
				.filter(item-> item.getHasDiscount())
				.filter(item->item.getStock() >0)
				.limit(20)
				.toArray(Item[]::new);
				return itemlist;
			}
		//return new Item[0];
	}
	
	public Item[] based(HttpServletRequest req){

			try {
					AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					List<String> items = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
					
					List<SearchHistory> history = lshrepository.findByUserId(userdetails.getId()).get(0).getHistory();
					System.out.println("History is");
					System.out.println(history);
					PQueue queue = new PQueue(history);
					List<SearchHistory> topThree = queue.getTopThree();
					System.out.println("Now top 3 is:");
					System.out.println(topThree);
					Item[] itemlist= itemRepository.findAll().
					stream().
					filter(item -> this.belongsTo(topThree, item))
					.filter(item->item.getStock() >0)
					.limit(20)
					.toArray(Item[]::new);
					return itemlist;
			}
			catch(Exception e) {
//				System.out.println(e);
//				Item[] itemlist= itemRepository.findAll().
//				stream().
//				filter(item -> item.getGender().equals("M"))
//				.filter(item-> item.getHasDiscount())
//				.limit(20)
//				.toArray(Item[]::new);
//				return itemlist;
				System.out.println("Exception encountered");
				return new Item[0];
			}
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping("/addcart")
	public ResponseEntity<?> addToCart(@RequestBody CartItem iq,HttpServletRequest req){
		try {
			AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		Item item = itemRepository.findById(iq.getId()).get();
		Cart cart = cartRepository.findByUserId(userdetails.getId()).get(0);
		System.out.println(cart);
		cart.addItem(iq);
		cartRepository.save(cart);
		ListSearchHistory lsh = lshrepository.findByUserId(userdetails.getId()).get(0);
		System.out.println("inside add to cart");
		List<String> itemCategory = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
		for(int i=0;i<itemCategory.size();i++) {
			if(item.getCategory().equals(itemCategory.get(i))) {
				lsh.getHistory().get(i).setTimes(lsh.getHistory().get(i).getTimes()+1);
				lshrepository.save(lsh);
				break;
			}
		}
		String type = item.getCategory();
		System.out.println("Quantity:"+iq.getQuantity());
		if(item.getStock()<iq.getQuantity()) {
			System.out.println("Out of stock");
			return new ResponseEntity<>(new MessageResponse("Item out of stock"),HttpStatus.valueOf(405));
		}
		item.setStock(item.getStock()-iq.getQuantity());
		itemRepository.save(item);
		System.out.println("Item in stock");
		return new ResponseEntity<Item>(item,HttpStatus.OK);
		}
		catch(Error e) {
			System.out.println(e);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Invalid Request"),HttpStatus.valueOf(400));
		}
		
	}
	
	
	public Boolean belongsTo(List<SearchHistory> history,Item item) {
			for(int i=0;i<history.size();i++) {
				if(history.get(i).getCategory().equals(item.getCategory())){
					return true;
					}
				}
		return false;
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping("/removeItem")
	public ResponseEntity<?> removeCategory(@RequestBody CartItem updatedItem){
		System.out.println(updatedItem);
		Item item = itemRepository.findById(updatedItem.getId()).get();
		item.setStock(item.getStock()+updatedItem.getQuantity());
		AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ListSearchHistory lsh = lshrepository.findByUserId(userdetails.getId()).get(0);
		List<String> itemCategory = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
		for(int i=0;i<itemCategory.size();i++) {
			if(item.getCategory().equals(itemCategory.get(i))) {
				lsh.getHistory().get(i).setTimes(lsh.getHistory().get(i).getTimes()+1);
				lshrepository.save(lsh);
				break;
			}
		}
		itemRepository.save(item);
		System.out.println("Hi");
		Cart cart = cartRepository.findByUserId(userdetails.getId()).get(0);
		System.out.println(cart);
		cart.removeItem(updatedItem);
		cartRepository.save(cart);
		System.out.println(item);
		return ResponseEntity.ok("Updated");
	}
	
	@PostMapping("/getCategory")
	public ResponseEntity<List<Item>> getCategory(@RequestBody HashMap<String,String> category){
		System.out.println(category);
		List<Item> items= itemRepository.findByCategory(category.get("category"));
		try {
			AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ListSearchHistory lsh = lshrepository.findByUserId(userdetails.getId()).get(0);
			List<String> itemCategory = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
			for(int i=0;i<itemCategory.size();i++) {
				if(category.get("category").equals(itemCategory.get(i))) {
					System.out.println("Found");
					lsh.getHistory().get(i).setTimes(lsh.getHistory().get(i).getTimes()+1);
					lshrepository.save(lsh);
					break;
				}
			}
			
		}
		catch(Exception e) {}
		return ResponseEntity.ok(items);
	}
	
	

}
