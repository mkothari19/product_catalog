package com.hcl.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hcl.business.Cart;
import com.hcl.entity.Product;
import com.hcl.entity.ProductPurchase;
import com.hcl.entity.User;

public class InMemoryData {

	private InMemoryData() {

	}

	private static InMemoryData instance;

	public static InMemoryData getInstance() {
		if (instance == null) {
			synchronized (InMemoryData.class) {
				if (instance == null) {
					instance = new InMemoryData();
				}
			}
		}

		return instance;
	}

	public static Map<Integer, List<ProductPurchase>> map = new HashMap<Integer, List<ProductPurchase>>();

	public List<Product> getProductCatelog() {
		List<Product> list = new ArrayList<Product>();

		list.add(new Product(1, "ABC Mutual Funds", "Finance", 1000, 'Y'));

		list.add(new Product(2, "XYZ Co Shares", "Finance", 200, 'N'));
		list.add(new Product(3, "PQR Shares", "Finance", 3000, 'Y'));

		list.add(new Product(4, "123 Mutual Funds", "Capital Market", 100, 'N'));
		list.add(new Product(5, "345 Mutual Funds", "Capital Market", 200, 'N'));
		return list;
	}

	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		list.add(new User(1, "Maneesh", false, "maneeshkothari1982@gmail.com"));
		list.add(new User(2, "Deepak", false, "deepak@gamil.com"));
		list.add(new User(3, "Jagdish", false, "jagdish@gmail.com"));

		return list;
	}

	public User selectUser(int id, List<User> list) {

		List<User> user = list.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
		if (user.isEmpty()) {
			return null;
		} else {
			return user.get(0);
		}
	}

	public void addPurchaseData(int userId, List<ProductPurchase> list) {
		if (map.containsKey(userId)) {
			List<ProductPurchase> existingList = map.get(userId);
			existingList.addAll(list);

		} else {
			map.put(userId, list);
		}
	}

}
