package org.vferrer.portfolio.manager.entitties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Preconditions;

/*
 * Record of a Cash Dividend awarded on a stock position
 * The currency of the payment must be the same of the stock and the 
 * position can only be a LONG one
 */
@Entity
public class CashDividend {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cash_dividend_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;
	
	@Column
	private Date paymentDate;
	
	@Column
	private Double grossAmount;
	
	@Column
	private Double netAmount;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		Preconditions.checkArgument(position.getTradeType() == TradeType.LONG, "Only LONG positions may receive dividends!");
		this.position = position;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}
	

}
