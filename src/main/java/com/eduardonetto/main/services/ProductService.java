package com.eduardonetto.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardonetto.main.controllers.dto.ProductDTO;
import com.eduardonetto.main.entities.Product;
import com.eduardonetto.main.repositories.ProductRepository;
import com.eduardonetto.main.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> product = repository.findById(id);
		return product.orElseThrow(() -> new ObjectNotFoundException("Object not found. Id = " + id));
	}

	public Product insert(Product product) {
		return repository.save(product);
	}

	public void delete(Long id) {
		Product product = findById(id);
		repository.delete(product);
	}

	public Product update(Long id, Product product) {
		Product entity = findById(id);
		updateProduct(entity, product);
		return repository.save(entity);
	}

	private void updateProduct(Product entity, Product product) {
		entity.setName(product.getName());
		entity.setPrice(product.getPrice());
	}

	public Product fromDto(ProductDTO productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		return product;
	}

}
