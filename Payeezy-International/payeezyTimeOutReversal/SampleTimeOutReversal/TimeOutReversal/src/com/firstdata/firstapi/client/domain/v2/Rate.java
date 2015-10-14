package com.firstdata.firstapi.client.domain.v2;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by F53PWZ7 on 9/16/2015.
 */






    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
    public class Rate  {

        public Rate() {
        }
        @JsonProperty("ratetype")
        private String ratetype;






        @JsonProperty("bin")
        private String bin;

    @JsonProperty("amount")
    private String amount;


    @JsonProperty("currencycode")
    private String currencycode;

        public String getRateType() {
            return ratetype;
        }

        public void setRateType(String ratetype) {
            this.ratetype = ratetype;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }


        public void setBin(String bin){this.bin=bin;}

        public String getBin(){return bin;}


    public String getCurrencyCode() {
        return currencycode;
    }

    public void setCurrencyCode(String currencycode) {
        this.currencycode = currencycode;
    }






    }


