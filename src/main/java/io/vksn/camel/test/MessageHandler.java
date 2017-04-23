
package io.vksn.camel.test;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import io.vksn.camel.test.model.Message;

@Named("messageHandler")
public class MessageHandler {
	
	@Inject
	private Logger log;

    public String someMethod(Message message) {
    	log.info(String.format("MessageHandler handling message %s", message.toString()));
        return "message consumed by CDI bean";
    }
}
