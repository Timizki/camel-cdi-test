package io.vksn.camel.test;

import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.restlet.data.MediaType;

import io.vksn.camel.test.model.Message;
import io.vksn.camel.test.model.MessageWrapper;


/**
 *   
 * @author Timo "Timii" Paananen
 * 
 * Test Apache Camel route configuration. Route will add receiving time to headers before it starts to handle input.
 * The process contains call to CDI bean, marshalling with Jaxb, two REST calls, multicast and pipeline setup, some logging and header handling.
 *
 */
@ApplicationScoped
@ContextName("camelContext")
public class IncomingRouteBuilder extends RouteBuilder {

	@Inject
	private Logger log;

	private static JAXBContext jaxbContext;

	public IncomingRouteBuilder() {
		try {
			jaxbContext = JAXBContext.newInstance(Message.class, MessageWrapper.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configure() throws Exception {
		from("direct:start.incoming").log("CAMEL received message").setHeader("receivedAt", constant(new Date()))
				.multicast()
					.bean("messageHandler").log("${body}")
				.pipeline()
					.setHeader("restletMethod", constant("PUT"))
					.setHeader("Content-Type", constant(MediaType.APPLICATION_XML))
	
					.marshal(new JaxbDataFormat(jaxbContext)).log("Marshalled body looks like this: ${body}")
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							log.info("processing message " + exchange.getIn().getBody());
	
						}
					}).to("restlet:http://127.0.0.1:8080/Camel-cdi-test/api/message/consume?restletMethod=PUT")
				.pipeline()
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							Message message = (Message) exchange.getIn().getBody();
							exchange.getIn().setBody(new MessageWrapper((Date)exchange.getIn().getHeader("receivedAt"),"Additional data", message));
	
						}
					}).marshal(new JaxbDataFormat(jaxbContext))
					.to("restlet:http://127.0.0.1:8080/Camel-cdi-test/api/message/consumeAnother?restletMethod=PUT");

	}
}
