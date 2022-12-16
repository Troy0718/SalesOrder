package main.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long prodNum;

	public enum ProdType {
		Clothes, Paints, Shirts, Jeans, Coat;
	}

	public enum ProdLine {
		SexStyle, CuteStyle, ComfortableStyle, HappyStyle;
	}

	private ProdType prodType;
	private ProdLine prodLine;

	@Column(length = 100)
	private String prodName;

	private String prodPic;

	private BigDecimal prodPrice;

	public String getProdPic() {
		return prodPic;
	}

	public void setProdPic(String prodPic) {
		this.prodPic = prodPic;
	}

	public ProdType getProdType() {
		return prodType;
	}

	public void setProdType(ProdType prodType) {
		this.prodType = prodType;
	}

	public ProdLine getProdLine() {
		return prodLine;
	}

	public void setProdLine(ProdLine prodLine) {
		this.prodLine = prodLine;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public long getProdNum() {
		return prodNum;
	}

	public void setProdNum(long prodNum) {
		this.prodNum = prodNum;
	}

	public BigDecimal getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}

}
