package com.example.apparel.controllers.admin;

import java.lang.Math;   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.example.apparel.model.Item;
import com.example.apparel.repository.ItemRepository;



/*/*
 * 
 *  	private String id;
	private Integer price;
	private Integer stock;
	private String gender;
	private String category;
	private Integer discount;
	private Boolean hasDiscount;
	
	    Map<String, List<String>> myMaps = new HashMap<String, List<String>>();
    for (DataObject item : myList) {
        if (!myMaps.containsKey(item.getKey())) {
            myMaps.put(item.getKey(), new ArrayList<String>());
        }
        myMaps.get(item.getKey()).add(item.getValue());
    }
 **/

@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	ItemRepository itemRepository;
	


	@GetMapping("/generate")
	public String generate() {
		List<String> genderList = new ArrayList<String>();
		genderList.add("M");
		genderList.add("F");
		List<String> categoryList = Arrays.asList("Shoe","Top","Skirt","Pant","Shirt","Tshirt");
		List<String> shoeList = Arrays.asList("Reebok","Bata","Khadims","Nike");
		List<String> topList = Arrays.asList("Manyavar","Ethnic","Modern");
		List<String> skirtList = Arrays.asList("Manyavar","Ethnic","Modern");
		List<String> pantList = Arrays.asList("Jeans","LooseFit","Formal","PeterEngland");
		List<String> shirtList = Arrays.asList("Reebok","Zara","Turtle","Nike");
		List<String> tshirtList = Arrays.asList("Reebok","Zara","Turtle","Nike");
		Map<String,List<String> > map = new HashMap<String,List<String>>();

		map.put("Shoe",shoeList);
		map.put("Top",topList);
		map.put("Skirt",skirtList);
		map.put("Pant",pantList);
		map.put("Shirt",skirtList);
		map.put("Tshirt",tshirtList);
		for(int i = 0; i<1000;i++) {
			int len = categoryList.size();
			int index = (Integer)(int)(Math.random()*(len));
			String category = categoryList.get(index);
			index = map.get(category).size();
			int cat_index = (int)(Math.random()*index);
			String name = map.get(category).get(cat_index);
			Integer price = (int)(Math.random()*3000)+200;
			Integer stock = (int)(Math.random()*30);
			Boolean hasDiscount = (int)(Math.random()*2)>0;
			Integer discount = (int)(Math.random()*100);
			String gender = genderList.get((int)(Math.random()*2));
			itemRepository.save(new Item(price,stock,gender,category,discount,hasDiscount,name));
		}
		return "Public Content.";

	}
	
}