package com.hcl.util;

import java.util.List;

import com.hcl.data.InMemoryData;
import com.hcl.entity.User;

public class CatalogUtil {
	
private CatalogUtil() {
		
	}
	 private static CatalogUtil  instance;
	 public static CatalogUtil getInstance(){
	        if (instance == null) {
	        	  synchronized (CatalogUtil.class) {
	        		  if(instance == null){
	            instance = new CatalogUtil();
	        		  }
	        	  }
	        } 

	        return instance;
	    }
	
	public double discount(int discountPer, double total) {

			return total - (total * discountPer) / 100;
	
	}

	public  boolean isOldUser(int userid) {
		List<User> userList = InMemoryData.getInstance().getUserList();
		User olduser = InMemoryData.getInstance().selectUser(userid, userList);
		
		if(olduser==null) {
			return false;
		}else {
			return true;
		}
	}
}
