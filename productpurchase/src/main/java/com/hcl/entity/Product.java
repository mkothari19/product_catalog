package com.hcl.entity;

import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String productName;
	private String productType;
	private double cost;
	private char promotionApplicable;

	public Product(int productId, String productName, String productType, double cost, char promotionApplicable) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.cost = cost;
		this.promotionApplicable = promotionApplicable;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public char getPromotionApplicable() {
		return promotionApplicable;
	}

	public void setPromotionApplicable(char promotionApplicable) {
		this.promotionApplicable = promotionApplicable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + promotionApplicable;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (promotionApplicable != other.promotionApplicable)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", cost=" + cost + ", promotionApplicable=" + promotionApplicable + "]";
	}

}
