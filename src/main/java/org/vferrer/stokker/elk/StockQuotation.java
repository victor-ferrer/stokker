package org.vferrer.stokker.elk;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "stockquotations")
public class StockQuotation 
{
	private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long  id;
	
	@Field (type = FieldType.String, store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
	private String stock;
	
	@Field(type=FieldType.Double, store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
	private Double value;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = DATE_FORMAT, store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
	private Calendar timestamp;
	
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
	public Calendar getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString()
	{
		return String.format("[%s]@%s (%s)", this.stock, this.value, this.timestamp.getTime().toString()); 
		
	}


}
