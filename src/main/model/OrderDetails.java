package main.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "orders_ordNum")
	private Orders orders;

	private long prodNum;

	private BigDecimal ordPrice;

	private int ordQty;

	private BigDecimal discount;

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public long getProdNum() {
		return prodNum;
	}

	public void setProdNum(long prodNum) {
		this.prodNum = prodNum;
	}

	public BigDecimal getOrdPrice() {
		return ordPrice;
	}

	public void setOrdPrice(BigDecimal ordPrice) {
		this.ordPrice = ordPrice;
	}

	public int getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(int ordQty) {
		this.ordQty = ordQty;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
