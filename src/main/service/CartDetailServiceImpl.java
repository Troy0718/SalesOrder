package main.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.CartDAO;
import main.dao.CartDetailDAO;
import main.dao.ProductsDAO;
import main.model.Cart;
import main.model.CartDetail;
import main.model.Products;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailDAO cartDetailDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	ProductsDAO productsDAO;

	@Override
	public CartDetail getById(int id) {
		return cartDetailDAO.getById(id);
	}

	@Override
	public void saveOrUpdate(CartDetail CartDetail) {
		cartDetailDAO.saveOrUpdate(CartDetail);
	}

	@Override
	public void delete(int id) {
		cartDetailDAO.delete(id);

	}

	/**
	 * 新增一筆CartDetail 將product 資料塞入 CartDetail
	 * 
	 */
	public void addCartDetail(long prodNum, String UserId) {
		CartDetail cartDetail = new CartDetail();
		// 取得加入cartDetail 的prodNum
		Products product = productsDAO.getById(prodNum);
		// 將Product 資料塞入cartDetail
		cartDetail.setCartPrice(product.getProdPrice());
		cartDetail.setDiscount(new BigDecimal(1));
		cartDetail.setOrdQty(1);
		cartDetail.setProdNum(prodNum);
		cartDetail.setProdPic(product.getProdPic());
		// 取得購物車id 將cartNum 塞入 cartDetail
		Cart cart = cartDAO.getCartByUserId(UserId);
		// Cart cart = cartDAO.getById(cartDAO.getAll().get(0).getCartNum());
		cartDetail.setCart(cart);
		// 將cartDetail這筆資料存入資料庫
		cartDetailDAO.saveOrUpdate(cartDetail);
	}

	/**
	 * 確認CartDetailList 項目是否重複，若有重複則回傳該CartDetailList id
	 */
	@Override
	public int checkCartDetail(long prodNum) {
		List<CartDetail> cartDetails = cartDAO.getAllDetail();
		int num = 0;
		for (CartDetail item : cartDetails) {
			if (item.getProdNum() == prodNum) {
				num = item.getId();
				return item.getId();
			}
		}
		return num;
	}

}
