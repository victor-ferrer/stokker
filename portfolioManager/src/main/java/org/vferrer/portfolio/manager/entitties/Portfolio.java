package org.vferrer.portfolio.manager.entitties;

import java.util.Currency;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Portfolio  
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "portfolio_id", unique = true, nullable = false)
	private Long id;
	
	@Column
	private String label;
	
	@Column
	private Date incorporationDate;
	
	@Column
	private Currency currency;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
	private Set<Position> positions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getIncorporationDate() {
		return incorporationDate;
	}

	public void setIncorporationDate(Date incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
}
