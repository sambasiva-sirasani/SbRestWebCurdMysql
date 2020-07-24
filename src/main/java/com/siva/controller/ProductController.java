package com.siva.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siva.model.Product;
import com.siva.service.IProduct;

@RestController
@RequestMapping("/rest/product")
public class ProductController {
	@Autowired
	private IProduct service;
	
	//Inserting Data
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product product){
		ResponseEntity<String> resp=null;
		try {
			if(product.getProdId()!=null&&service.isExist(product.getProdId())) {
				resp=new ResponseEntity<String>("Product with"+product.getProdId()+"is already exist", HttpStatus.BAD_REQUEST);
			}else {
				Integer id=service.saveProduct(product);
				resp=new ResponseEntity<String>("Product"+id+"saved", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to save product",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
	
		//Get one Product Id
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneProduct(
			@PathVariable Integer id)
	{
		ResponseEntity<?>  resp = null;
		try {
			//communicate with DB using ID with Serivce Layer
			Optional<Product> opt = service.getOneProduct(id);
			
			if(opt.isPresent()) { //if Product exist
				Product product = opt.get();
				resp = new ResponseEntity<Product>(
						product,
						HttpStatus.OK);
				
			} else { //if product not exist
				resp = new ResponseEntity<String>(
						"Product '"+id+"' Not exist!",
						HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to fetch Product", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
	
	//Get All Products
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		ResponseEntity<?> resp=null;
		try {
			List<Product> list=service.getAll();
			resp=new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>(
					"Unable to fetch Product", 
					HttpStatus.INTERNAL_SERVER_ERROR);
			
			e.printStackTrace();
		}
		return resp;
	}
	
	
	//Update product
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Product product){
		 ResponseEntity<?> resp=null;
		try {
			if(product.getProdId()!=null&&service.isExist(product.getProdId())) {
				service.updateProduct(product);
				resp=new ResponseEntity<String>("Product updated",HttpStatus.OK);
			}else {
				resp = new ResponseEntity<String>(
						"Product '"+product.getProdId()+"' Not updated!",
						HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to update Product",HttpStatus.INTERNAL_SERVER_ERROR);
			
			e.printStackTrace();
		}
		return resp;
		
	}
	
	//Delete product
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		ResponseEntity<String> resp=null;
		try {
			if(service.isExist(id)) {
				service.deleteProduct(id);
				resp=new ResponseEntity<String>("Product Deleted", HttpStatus.OK);
			}
			else {
				resp = new ResponseEntity<String>(
						"Product  Not deleted!",HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to delete Product",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
		
	}
	
}
