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
 *  Simple model object that wraps <code>Message</code> object and adds some additional information
 *
 */
@XmlRootElement(namespace="http://vksn.io/model/wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageWrapper {
	@XmlElement
	private Date receivedAt;
	
	@XmlElement
	private String additionalData;
	@XmlElement
	private Message message;

	public MessageWrapper() {

	}

	public MessageWrapper(Date receicedAt, String additionalData, Message message) {
		this.receivedAt = receicedAt;
		this.additionalData = additionalData;
		this.message = message;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("MessageWrapper [receivedAt=%s,additionalData=%s, message=%s]", receivedAt, additionalData, message);
	}

}
