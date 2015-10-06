package org.vferrer.portfolio.manager.entitties;

import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author victor-ferrer
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stock 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stock_id")
	private Long id;
	
	@Column
	private String ticker;
	
	@Column
	private String market;
	
	@Column
	private boolean needMarketSuffix;
	
	@Column
	private String label;
	
	@Column
	private Currency currency;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isNeedMarketSuffix() {
		return needMarketSuffix;
	}

	public void setNeedMarketSuffix(boolean needMarketSuffix) {
		this.needMarketSuffix = needMarketSuffix;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
