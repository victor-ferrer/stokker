package org.vferrer.stokker.feeder.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class HistoricalCSVLineSplitter extends AbstractMessageSplitter {

	@Override
	protected Object splitMessage(Message<?> message)
	{
		String payload = message.getPayload().toString();
		
		String[] chunks = payload.split("\\r?\\n");
		
		List<String> toReturn = Arrays.asList(chunks);
		
		// Remove headers
		if (!toReturn.isEmpty()){
			toReturn = new ArrayList<String>(toReturn.subList(1, toReturn.size() - 1));
		}
		return toReturn;
	}
}
