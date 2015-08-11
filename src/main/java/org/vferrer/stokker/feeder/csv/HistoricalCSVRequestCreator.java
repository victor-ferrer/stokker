package org.vferrer.stokker.feeder.csv;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component
public class HistoricalCSVRequestCreator implements ApplicationListener {

	private List<String> tickers;
	
	@Autowired
	@Qualifier("historical.trigger.split.channel")
	private MessageChannel channel;
	
	@Value("${stokker.target.stocks}")
	private String tickersConfig;
	
	@PostConstruct
	public void init()
	{
		tickers = Arrays.asList(tickersConfig.split(","));
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof ContextRefreshedEvent)
		{
			System.out.println("Building request for tickers: " + tickersConfig);
			
			for (String ticker : tickers) {
				MessageBuilder<String> builder = MessageBuilder.withPayload("");
				builder.setHeaderIfAbsent("ticker", ticker);
				channel.send(builder.build());
			}
		}
	}
	
}
