package org.vferrer.portfolio.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vferrer.portfolio.manager.entitties.Portfolio;
import org.vferrer.portfolio.manager.repositories.PortfolioRepository;
import org.vferrer.portfolio.manager.service.PortfolioValuationService;

@Controller
@RequestMapping("/portfolioValuation")
public class PortfolioValuationController 
{

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private PortfolioValuationService valuationService;
	
	@RequestMapping(value="/investment", produces="application/json")
	public String getPortfolioInvestment(@Param("portfolioId")Long portfolioId)
	{
		Portfolio portfolio = portfolioRepository.findOne(portfolioId);
		
		Double value = valuationService.findPorfolioInvestment(portfolio);
		
		return String.format("{ \"valuation\" : \"%s\"}", value.toString());
		
	}
}
