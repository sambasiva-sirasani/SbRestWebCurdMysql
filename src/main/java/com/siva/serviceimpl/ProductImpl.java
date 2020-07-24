package com.siva.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siva.model.Product;
import com.siva.repo.ProductRepo;
import com.siva.service.IProduct;

@Service
public class ProductImpl implements IProduct{
	@Autowired
	private ProductRepo repo;

	@Override
	public Integer saveProduct(Product p) {
		return repo.save(p).getProdId();
	}

	@Override
	public void updateProduct(Product p) {
		repo.save(p);
		
	}

	@Override
	public void deleteProduct(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public Optional<Product> getOneProduct(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Product> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

}
