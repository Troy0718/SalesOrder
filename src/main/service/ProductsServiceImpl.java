package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.ProductsDAO;
import main.model.Products;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	ProductsDAO productsDAO;

	@Override
	public Products getById(long prodNum) {

		return productsDAO.getById(prodNum);
	}

	@Override
	public void saveOrUpdate(Products products) {
		productsDAO.saveOrUpdate(products);
	}

	@Override
	public void delete(long prodNum) {
		productsDAO.delete(prodNum);
	}

	@Override
	public List<Products> getAll() {
		return productsDAO.getAll();
	}

}
