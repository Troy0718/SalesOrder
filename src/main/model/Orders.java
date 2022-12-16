package main.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ordNum;

	public enum Payment {
		cash, credit_card, paypal;
	}

//	@NotNull(message = "{tour.date.notnull}")
//	@Future(message = "{tour.date.future}")
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ordDate;

	private Payment payment;

//	@DecimalMin(value="0.1" , inclusive=false ,message = "{tour.code.decimal}")
//	@DecimalMax(value="10000" , inclusive=false ,message = "{tour.code.decimal}")
	private BigDecimal amount;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<OrderDetails> orderDetails;

//	@Min(value = 2, message="{tour.custId}")
//	@Max(value = 0, message="{tour.custId}")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getOrdNum() {
		return ordNum;
	}

	public void setOrdNum(long ordNum) {
		this.ordNum = ordNum;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}



}
