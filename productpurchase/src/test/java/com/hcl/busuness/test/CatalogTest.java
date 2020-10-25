package com.hcl.busuness.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hcl.business.ProductCatelog;
import com.hcl.data.InMemoryData;
import com.hcl.entity.Product;
import com.hcl.entity.User;

public class CatalogTest {

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
	public void testFileterByType() {
		List<Product> list = new ArrayList<>();
		list.add(new Product(1, "ABC Mutual Funds", "Finance", 1000, 'Y'));
		list.add(new Product(2, "XYZ Co Shares", "Finance", 200, 'N'));
		list.add(new Product(3, "PQR Shares", "Finance", 3000, 'Y'));
		List<Product> financeproduct = productCatelogObj.filterProduct(productCatelog, "productType", "Finance");
		assertEquals(list, financeproduct);
	}

	@Test
	public void testFileterByPromotion() {
		List<Product> list = new ArrayList<>();
		list.add(new Product(1, "ABC Mutual Funds", "Finance", 1000, 'Y'));
		list.add(new Product(3, "PQR Shares", "Finance", 3000, 'Y'));
		List<Product> promotionappliedproduct = productCatelogObj.filterProduct(productCatelog, "promotion", "Y");
		assertEquals(list, promotionappliedproduct);
	}
	@Test
	public void testFileterByPromotionNotApplied() {
		List<Product> list = new ArrayList<>();
		list.add(new Product(2, "XYZ Co Shares", "Finance", 200, 'N'));
		list.add(new Product(4, "123 Mutual Funds", "Capital Market", 100, 'N'));
		list.add(new Product(5, "345 Mutual Funds", "Capital Market", 200, 'N'));
		List<Product> promotionnotappliedproduct = productCatelogObj.filterProduct(productCatelog, "promotion", "N");
		assertEquals(list, promotionnotappliedproduct);
	}

	@Test
	public void testgetAllProductd() {
		List<Product> list = new ArrayList<>();
		list.add(new Product(1, "ABC Mutual Funds", "Finance", 1000, 'Y'));

		list.add(new Product(2, "XYZ Co Shares", "Finance", 200, 'N'));
		list.add(new Product(3, "PQR Shares", "Finance", 3000, 'Y'));

		list.add(new Product(4, "123 Mutual Funds", "Capital Market", 100, 'N'));
		list.add(new Product(5, "345 Mutual Funds", "Capital Market", 200, 'N'));
		List<Product> allproduct = productCatelogObj.getAllProduct();
		assertEquals(list, allproduct);
	}

}
