package com.hcl.product_purchase;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hcl.business.ProductCatelog;
import com.hcl.data.InMemoryData;
import com.hcl.entity.Product;
import com.hcl.util.CatalogUtil;


public class CatalogTest {
	
	@Test
	public void testFileterByType() {
		List<Product> list=new ArrayList<>();
        list.add(new Product(1 ,"ABC Mutual Funds","Finance",1000,'Y'));
		list.add(new Product(2 ,"XYZ Co Shares","Finance",200,'N'));
		list.add(new Product(3 ,"PQR Shares","Finance",3000,'Y'));
		
		ProductCatelog productCatelogObj = new ProductCatelog();
		List<Product> productCatelog = InMemoryData.getInstance().getProductCatelog();
		List<Product> financeproduct=	productCatelogObj.filterProduct(productCatelog, "productType", "Finance");
		assertEquals(list,financeproduct);
	}
	
	@Test
	public void testDiscount() {
	 String  result ="270.0";
	 String output=CatalogUtil.getInstance().discount(10, 300.00)+"";
	assertEquals(result,output);
	}
	
}
