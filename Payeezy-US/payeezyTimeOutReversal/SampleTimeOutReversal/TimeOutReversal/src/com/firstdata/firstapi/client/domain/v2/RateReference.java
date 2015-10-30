package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by F53PWZ7 on 9/17/2015.
 */
public class RateReference {
    public RateReference(){

    }
    @JsonProperty("rate_id")
    private String rate_id;

    @JsonProperty("dccaccepted")
    private String dccaccepted;

    public String getRate_id() {
        return rate_id;
    }

    public void setRate_id(String rate_id) {
        this.rate_id = rate_id;
    }

    public String getDccaccepted() {
        return dccaccepted;
    }

    public void setDccaccepted(String dccaccepted) {
        this.dccaccepted = dccaccepted;
    }
}
