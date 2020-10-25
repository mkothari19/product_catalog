package com.hcl.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hcl.catalog.Discount;
import com.hcl.entity.Product;
import com.hcl.entity.ProductPurchase;
import com.hcl.entity.User;
import com.hcl.util.CatalogUtil;


public class Cart implements Discount{
	private double total;

	ProductPurchase ProductPurchase ;
	
	List<ProductPurchase> productPurchaseList = new ArrayList<ProductPurchase>();
	
	 
	public void addToCart(Product product,User user) {
		
		ProductPurchase = new ProductPurchase(product,user);
		productPurchaseList.add(ProductPurchase);
	
	}
/*
 * Total cost of cart
 */
	public double cartTotal() {
	
		productPurchaseList.stream().forEach(x -> total += x.getProduct().getCost());
		
		return total;
	}
	

/*
 * Discount to new user
 */
	public double cartTotalAfterNewUserDiscount(){
	
		int userid=ProductPurchase.getUser().getId();
		
		if(!CatalogUtil.getInstance().isOldUser(userid)) {
			
			return CatalogUtil.getInstance().discount(10, total);
		
		
		}else {
			
		return total;	
		}
	}
/*
 * Discount 
 */
	public double cartTotalAfterSameProductTypeDiscount() {
		int userid=ProductPurchase.getUser().getId();
		if(CatalogUtil.getInstance().isOldUser(userid)) {
					Map<String, List<Product>> productPerType = productPurchaseList.stream().map(x -> x.getProduct())
				.filter(y -> y.getPromotionApplicable() == "Y".charAt(0))
				.collect(Collectors.groupingBy(Product::getProductType));
		// double total =0;
			productPerType.size();
		for (Map.Entry<String, List<Product>> entry : productPerType.entrySet()) {
			List<Product> value = entry.getValue();
			double tempTotal = 0;
			int productCountPerCat = value.size();
			
			if(productCountPerCat==2) {
				for (int i = 0; i < productCountPerCat; i++) {

					tempTotal += value.get(i).getCost();
      
				}
				
				total=CatalogUtil.getInstance().discount(10, tempTotal);

			}else if(productCountPerCat==3) {
				for (int i = 0; i < productCountPerCat; i++) {

					tempTotal += value.get(i).getCost();

				}
				total=CatalogUtil.getInstance().discount(20, tempTotal);

			}else {
				for (int i = 0; i < productCountPerCat; i++) {

					tempTotal += value.get(i).getCost();

				}
				total=tempTotal;
			}

			
					
	}
		}
		return total;
}
	public List<ProductPurchase> getProductPurchaseList() {
		return productPurchaseList;
	}

	
	
}
