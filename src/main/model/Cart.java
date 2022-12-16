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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartNum;

	public enum Payment {
		cash, credit_card, paypal;
	}

	private Payment payment;

//	@NotNull(message = "{tour.date.notnull}")
//	@Future(message = "{tour.date.future}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cartDate;

//	@DecimalMin(value="0.1" , inclusive=false ,message = "{tour.code.decimal}")
//	@DecimalMax(value="10000" , inclusive=false ,message = "{tour.code.decimal}")
	private BigDecimal amount;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CartDetail> cartDetails;

//	@Min(value = 2, message="{tour.custId}")
//	@Max(value = 0, message="{tour.custId}")
	private String userId;

	// ------------------------------------------------------------------------------------------
	public long getCartNum() {
		return cartNum;
	}

	public void setCartNum(long cartNum) {
		this.cartNum = cartNum;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCartDate() {
		return cartDate;
	}

	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
