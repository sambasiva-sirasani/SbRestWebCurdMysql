package com.siva.service;

import java.util.List;
import java.util.Optional;

import com.siva.model.Product;

public interface IProduct {
	
	public Integer saveProduct(Product p);
	public void updateProduct(Product p);
	public void deleteProduct(Integer id);
	public Optional<Product> getOneProduct(Integer id);
	public List<Product> getAll();
	public boolean isExist(Integer id);

}
