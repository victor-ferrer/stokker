package org.vferrer.stokker.feeder.csv;

import java.util.Arrays;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CSVLineSplitter extends AbstractMessageSplitter {

	@Override
	protected Object splitMessage(Message<?> message)
	{
		String payload = message.getPayload().toString();
		
		String[] chunks = payload.split("\\r?\\n");
		
		return Arrays.asList(chunks);
		
	}

}
