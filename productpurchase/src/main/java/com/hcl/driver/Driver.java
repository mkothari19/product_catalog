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
		Cart.getInstance().cleanCart();
		for(int i=10;i<15;i++) {
			Cart.getInstance().addToCart(new Product(10, "productName"+i,"productType"+i,120.0,'Y'),user) ;
		}
		
		System.out.println("Actual Price for new user:: "+Cart.getInstance().cartTotal());
	System.out.println("After 10% discount for new user :: "+Cart.getInstance().cartTotalAfterNewUserDiscount());
	 double output=CatalogUtil.getInstance().discount(10, 300.00);
	 System.out.println("*****"+output);
		
	/*
	 * Validation rules:2 > Existing user purchases products of same Product type check for:
	 */

     /*
      * If 2 products are selected of same Product Type and Promotion applicable is Y for all selected products,
       then discount should be 10% of total cost of Promotion applicable products
      */
	Cart.getInstance().cleanCart();
	List<User> userList = inMemory.getUserList();
	User olduser1 = inMemory.selectUser(1, userList);
	for(int i=1;i<=2;i++) {
		Cart.getInstance().addToCart(new Product(i, "productName"+i,"productType",100,'Y'),olduser1) ;
	}
	System.out.println("Actual price for new user:: "+Cart.getInstance().cartTotal());
	System.out.println("After 10% discount for existing user:::"+Cart.getInstance().cartTotalAfterSameProductTypeDiscount());
	
	
	 /*
     * If 3 products are selected of same Product Type and Promotion applicable is Y for all selected products,
     * then discount should be 20% of total cost of Promotion applicable products
     */
	Cart.getInstance().cleanCart();
	User olduser2 = inMemory.selectUser(2, userList);
	for(int i=1;i<=3;i++) {
		Cart.getInstance().addToCart(new Product(i, "productName"+i,"productType",120,'Y'),olduser2) ;
	}
	System.out.println("Actual price for new user:: "+Cart.getInstance().cartTotal());
	System.out.println("After 20% discount for existing user:::"+Cart.getInstance().cartTotalAfterSameProductTypeDiscount());
	
	
	}
}
