package org.vferrer.portfolio.manager.entitties;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Portfolio  
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String label;
	
	@ManyToOne(targetEntity=Position.class)
	private List<Position> positions;
	
}
