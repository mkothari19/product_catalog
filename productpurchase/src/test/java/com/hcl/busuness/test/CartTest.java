package com.hcl.busuness.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hcl.business.Cart;
import com.hcl.business.ProductCatelog;
import com.hcl.data.InMemoryData;
import com.hcl.entity.Product;
import com.hcl.entity.ProductPurchase;
import com.hcl.entity.User;

public class CartTest {

	static User user;
	static InMemoryData inMemory = InMemoryData.getInstance();
	static ProductCatelog productCatelogObj = new ProductCatelog();

	static List<Product> productCatelog;
	static List<User> userList;

	@BeforeClass
	public static void init() {
		user = new User(5, "Maneesh", true, "manishkothari1982@gmail.com");
		productCatelog = inMemory.getProductCatelog();
		userList = inMemory.getUserList();

	}

	@Test
	public void testAddtoCart() {

		Cart newusercat = new Cart();
		Product p = new Product(10, "HDF 200", "Finance Test", 200.0, 'Y');
		newusercat.addToCart(p, user);
		assertEquals(newusercat.getProductPurchaseList().get(0).getProduct(), p);
	}

	@Test
	public void testCartTotal() {

		Cart newusercat = new Cart();
		Product p = new Product(10, "HDF 200", "Finance Test", 200.0, 'Y');
		newusercat.addToCart(p, user);
		String total = newusercat.cartTotal() + "";
		assertEquals("200.0", total);

	}

	@Test
	public void testcartTotalAfterNewUserDiscount() {

		Cart newusercat = new Cart();

		newusercat.addToCart(new Product(10, "productName", "productType", 200.0, 'Y'), user);
		newusercat.cartTotal();
		String total = newusercat.cartTotalAfterNewUserDiscount() + "";
		assertEquals("180.0", total);

	}

	@Test
	public void testcartTotalAfterSameProductTypeDiscount10Per() {

		Cart newusercat = new Cart();
		User olduser2 = inMemory.selectUser(2, userList);
		for (int i = 1; i <= 2; i++) {
			newusercat.addToCart(new Product(i, "productName" + i, "productType", 100, 'Y'), olduser2);
		}
		newusercat.cartTotal();
		String total = newusercat.cartTotalAfterSameProductTypeDiscount() + "";
		assertEquals("180.0", total);

	}

	@Test
	public void testcartTotalAfterSameProductTypeDiscount20Per() {

		Cart newusercat = new Cart();
		User olduser2 = inMemory.selectUser(2, userList);
		for (int i = 1; i <= 3; i++) {
			newusercat.addToCart(new Product(i, "productName" + i, "productType", 150, 'Y'), olduser2);
		}
		newusercat.cartTotal();
		String total = newusercat.cartTotalAfterSameProductTypeDiscount() + "";
		assertEquals("360.0", total);

	}
	
	@Test
	public void testgetPurchaseList() {
		Cart newusercat = new Cart();
		User olduser2 = inMemory.selectUser(2, userList);
	   newusercat.addToCart(new Product(20, "productName" , "productType", 150, 'Y'), olduser2);
	    String result= "[ProductPurchase [product=Product [productId=20, productName=productName, productType=productType, cost=150.0, promotionApplicable=Y], date="+newusercat.getProductPurchaseList().get(0).getDate()+", user=User [id=2, name=Deepak, newUser=false]]]";
		assertEquals(result, newusercat.getProductPurchaseList().toString());

	}
}
