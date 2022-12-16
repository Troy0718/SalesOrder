package main.service;

import java.util.List;

import main.model.Products;

public interface ProductsService {

	public List<Products> getAll();

	public Products getById(long prodNum);

	public void saveOrUpdate(Products products);

	public void delete(long prodNum);
}
