package com.hcl.business;

import java.util.List;
import java.util.stream.Collectors;

import com.hcl.data.InMemoryData;
import com.hcl.entity.Product;

public class ProductCatelog {

	/*
	 * Filter based on Product type and/or Promotions applicable.
	 */

	public List<Product> filterProduct(List<Product> list, String filterCondition, String match) {

		List<Product> filteredProduct = null;
		switch (filterCondition) {
		case "productType":
			filteredProduct = list.stream().filter(x -> x.getProductType().equals(match)).collect(Collectors.toList());
			break;
		case "promotion":
			filteredProduct = list.stream().filter(x -> x.getPromotionApplicable() == match.charAt(0))
					.collect(Collectors.toList());
			break;
		}

		return filteredProduct;
	}
	public List<Product> getAllProduct() {

		return InMemoryData.getInstance().getProductCatelog();
	}
	
}
