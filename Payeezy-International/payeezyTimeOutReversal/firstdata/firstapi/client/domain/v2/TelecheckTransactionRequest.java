package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class TelecheckTransactionRequest {
	
	@JsonProperty("tele_check")
	private Telecheck check;
	public Telecheck getCheck() {
		return check;
	}
	public void setCheck(Telecheck check) {
		this.check = check;
	}
	

}
