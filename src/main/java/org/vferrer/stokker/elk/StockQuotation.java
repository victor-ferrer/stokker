package org.vferrer.stokker.elk;

import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName=ELKClient.INDEX_NAME)
public class StockQuotation 
{
	
	@Id
	private Long  id;
	
	@Field (type = FieldType.String, store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
	private String stock;
	
	@Field(type=FieldType.Double, store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
	private Double value;
	
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern ="dd-MM-yyyy HH:mm:ss", store = true, index = FieldIndex.analyzed, searchAnalyzer = "standard", indexAnalyzer = "standard")
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
	
}
