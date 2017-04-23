package io.vksn.camel.test.config;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * 
 * @author Timo "Timii" Paananen
 *
 *	CDI producer for producing needed configurations
 *
 */
public class Config {
		
	@Produces
	public Logger getLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	

}
