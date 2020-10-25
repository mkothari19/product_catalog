package com.hcl.driver;

import java.util.List;

import com.hcl.business.Cart;
import com.hcl.business.ProductCatelog;
import com.hcl.data.InMemoryData;
import com.hcl.entity.Product;
import com.hcl.entity.User;
import com.hcl.util.CatalogUtil;

/**
 * Hello world!
 *
 */
public class Driver {
	public static void main(String[] args) throws Exception {
		InMemoryData inMemory = InMemoryData.getInstance();
		ProductCatelog productCatelogObj = new ProductCatelog();
	
		List<Product> productCatelog = inMemory.getProductCatelog();
		
		// Use case 1  Filter based on Product type and/or Promotions applicable.
		List<Product> financeProduct = productCatelogObj.filterProduct(productCatelog, "productType", "Finance");
		List<Product> notPromotionalProduct = productCatelogObj.filterProduct(productCatelog, "promotion", "N");
		
		System.out.println(financeProduct);
		System.out.println(notPromotionalProduct);
		System.out.println(productCatelog);
	
		// Use case:2 > purchase products which should be persisted with User ID and purchase date(system generated date)
		
		//Validation rules:1 > New User should get a discount of 10% on total sold products (irrespective of promotion applicable)
		// Add dummy product and user to cart
		User user =new User(5,"Maneesh",true,"manishkothari1982@gmail.com");
		Cart newusercat=new Cart();
		for(int i=10;i<11;i++) {
			newusercat.addToCart(new Product(20, "productName" , "productType", 150, 'Y'),user) ;
		}
		
		System.out.println(newusercat.getProductPurchaseList());
		System.out.println("Actual Price for new user:: "+newusercat.cartTotal());
	   System.out.println("After 10% discount for new user :: "+newusercat.cartTotalAfterNewUserDiscount());
	 
		
	/*
	 * Validation rules:2 > Existing user purchases products of same Product type check for:
	 */

     /*
      * If 2 products are selected of same Product Type and Promotion applicable is Y for all selected products,
       then discount should be 10% of total cost of Promotion applicable products
      */
	Cart user1cart=new Cart();
	
	List<User> userList = inMemory.getUserList();
	User olduser1 = inMemory.selectUser(1, userList);
	for(int i=1;i<=2;i++) {
		user1cart.addToCart(new Product(i, "productName"+i,"productType",100,'Y'),olduser1) ;
	}
	System.out.println("Actual price for new user:: "+user1cart.cartTotal());
	System.out.println("After 10% discount for existing user:::"+user1cart.cartTotalAfterSameProductTypeDiscount());
	
	
	 /*
     * If 3 products are selected of same Product Type and Promotion applicable is Y for all selected products,
     * then discount should be 20% of total cost of Promotion applicable products
     */
	Cart user2cart=new Cart();
	User olduser2 = inMemory.selectUser(2, userList);
	for(int i=1;i<=3;i++) {
		user2cart.addToCart(new Product(i, "productName"+i,"productType",120,'Y'),olduser2) ;
	}
	System.out.println("Actual price for new user:: "+user2cart.cartTotal());
	System.out.println("After 20% discount for existing user:::"+user2cart.cartTotalAfterSameProductTypeDiscount());
	
	
	}
}
