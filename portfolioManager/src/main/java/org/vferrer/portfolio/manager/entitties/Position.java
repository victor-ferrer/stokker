package org.vferrer.portfolio.manager.entitties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Position 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	// TODO this should be extracted to a FK -> Stock
	@Column
	private String stock;
	
	@Column
	private Double amount;
	
	@Column
	private Double price;
	
	// FIXME
//	@Column
//	private DateTime tradeDate;
	
	@Column
	private TradeType tradeType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	public DateTime getTradeDate() {
//		return tradeDate;
//	}
//
//	public void setTradeDate(DateTime tradeDate) {
//		this.tradeDate = tradeDate;
//	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

}
