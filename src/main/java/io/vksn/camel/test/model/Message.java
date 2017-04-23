package io.vksn.camel.test.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author Timo "Timii" Paananen
 *  
 *  Simple model object
 *
 */
@XmlRootElement(namespace="http://vksn.io/test/model")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

	@XmlElement
	private String direction;
	@XmlElement
	private String text = "Hello";
	@XmlElement
	private Date currentTime = new Date();
	
	protected Message() {
		
	}

	public Message(String direct) {
		this.direction = direct;
	}
	
	@Override
	public String toString() {
		return String.format("Message [direction=%s, text=%s, currentTime=%s]", this.direction, this.text, this.currentTime);
	}
	
	public Date getCurrentTime() {
		return currentTime;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	
}
