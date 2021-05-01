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
import com.example.apparel.model.PQueue;
import com.example.apparel.model.SearchHistory;
import com.example.apparel.model.UpdateCart;
import com.example.apparel.repository.ItemRepository;
import com.example.apparel.security.services.AppUserDetails;




@RestController
@RequestMapping("/api/user")
@CrossOrigin("/*")
public class UserControllerCustom {
	
	
	//things to be stored in a session : number of times discounted / new arrival. default based on gender. also keep track of each item
	
	@Autowired
	ItemRepository itemRepository;
	
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/home")
	public ResponseEntity<?> getHomePage(HttpServletRequest req) {
		Item[] nA = this.newArrivals(req);
		Item[] d = this.discounted(req);
		Item[] b = this.based(req);
		return ResponseEntity.ok(new HomePage(d,nA,b));
	}
	
	
	public Item[] newArrivals(HttpServletRequest req){
		HttpSession session = req.getSession();
		System.out.println(session);
		if(session.isNew()) {
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
		else {
			try {
				AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Item[] itemlist= itemRepository.findAll().
				stream().
				filter(item -> item.getCategory().equals(userdetails.getGender()))
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
			.filter(item->item.getStock() >0)
			.limit(20)
			.toArray(Item[]::new);
			System.out.println(e);
			return itemlist;
		}
		}
	}

	public Item[] discounted(HttpServletRequest req){
		if(req.getSession().isNew()) {
			try {
					AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					Item[] itemlist= itemRepository.findAll().
					stream().
					filter(item -> item.getGender().equals(userdetails.getGender()))
					.filter(item->item.getStock() >0)
					.filter(item-> item.getHasDiscount())
					.limit(20)
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
		}
		else {
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
		}
		//return new Item[0];
	}
	
	public Item[] based(HttpServletRequest req){
		HttpSession session = req.getSession();
		if(session.isNew()) {
			System.out.println("New session");
			return new Item[0];
		}
		else {
			System.out.println("Exisiting session");
			try {
					AppUserDetails userdetails = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					List<String> items = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
					List<SearchHistory> history = new ArrayList<SearchHistory>();
					for(int i=0;i<items.size();i++) {
						if(session.getAttribute(items.get(i))!=null) {
								SearchHistory sh = new SearchHistory(items.get(i),(Integer)session.getAttribute(items.get(i)));
								history.add(sh);
							}
						}
					System.out.println("History is");
					System.out.println(history);
					PQueue queue = new PQueue(history);
					List<SearchHistory> topThree = queue.getTopThree();
					Item[] itemlist= itemRepository.findAll().
					stream().
					filter(item -> this.belongsTo(history, item))
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
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping("/addcart")
	public ResponseEntity<?> addToCart(@RequestBody UpdateCart iq,HttpServletRequest req){
		try {
			
		Item item = itemRepository.findById(iq.getId()).get();
		HttpSession session = req.getSession();
		if(session.isNew()) {
			//"Shoe","Top","Skirt","Pant","Shirt","Tshirt"
			List<String> items = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
			for(int i=0;i<items.size();i++) {
				session.setAttribute(items.get(i), 0);
			}
		}
		String type = item.getCategory();
		session.setAttribute(type, (Integer)session.getAttribute(type)+1);
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
	public ResponseEntity<?> removeCategory(@RequestBody HashMap<String,String> updateItem){
		System.out.println(updateItem);
		Item item = itemRepository.findById(updateItem.get("id")).get();
		item.setStock(item.getStock()+Integer.parseInt(updateItem.get("quantity")));
		itemRepository.save(item);
		System.out.println(item);
		return ResponseEntity.ok("Updated");
	}
	
	@PostMapping("/getCategory")
	public ResponseEntity<List<Item>> getCategory(@RequestBody HashMap<String,String> category){
		System.out.println(category);
		List<Item> items= itemRepository.findByCategory(category.get("category"));
		return ResponseEntity.ok(items);
	}
	
	

}
