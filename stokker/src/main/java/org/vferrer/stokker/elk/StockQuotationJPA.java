package org.vferrer.stokker.elk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockQuotationJPA 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id;
	
	@Column
	private String stock;
	
	@Column
	private Double value;
	
	@Column
	private Date timestamp;
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public static StockQuotationJPA fromElasticSearchStockQuotation(StockQuotation input){
		StockQuotationJPA toReturn = new StockQuotationJPA();
		toReturn.setStock(input.getStock());
		toReturn.setValue(input.getValue());
		toReturn.setTimestamp(input.getTimestamp().getTime());
		
		return toReturn;
	}

}
