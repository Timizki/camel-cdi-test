package io.vksn.camel.test.service;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.vksn.camel.test.model.Message;
import io.vksn.camel.test.model.MessageWrapper;


/**
 * 
 * @author Timo "Timii" Paananen
 * 
 * Rest resources that can consume <code>Message</code> and <code>MessageWrapper</code> objects
 *
 */
@Path("/message")
public class RestResource {

	@Inject
	private Logger log;
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/consume")
	public Response consume(Message message) {
		//Processing might take sometime....
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {}
		log.info(String.format("Rest service received message %S", message));
		return Response.ok().build();		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/consumeAnother")
	public Response consumeAnother(MessageWrapper message) {
		log.info(String.format("Another Rest service received message %S", message));
		return Response.ok().build();		
	}
}
