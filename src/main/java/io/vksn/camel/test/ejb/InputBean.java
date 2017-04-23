package io.vksn.camel.test.ejb;

import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import io.vksn.camel.test.model.Message;

/**
 * Author Timo "Timii" Paananen
 * 
 * Scheduled singleton EJB bean for producing data for Apache Camel process
 * 
 */
@Singleton
@Startup
public class InputBean {

	@Inject
	private Logger log;
	
	@EndpointInject(context = "camelContext", uri="direct:start.incoming")	
	private ProducerTemplate producer;

	@Schedule(second="*/10", minute="*",hour="*", persistent=false)
	public void produceIncomingMessage() {
		log.info("producing incoming message");
		producer.sendBody(new Message("INCOMING"));
	}

}
