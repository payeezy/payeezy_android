package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Level2
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax2_number")
    private String tax2_number;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax2_amount")
    private String tax2_amount;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax1_number")
    private String tax1_number;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax1_amount")
    private String tax1_amount;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("customer_ref")
    private String customer_ref;

    public String getTax2_number ()
    {
        return tax2_number;
    }

    public void setTax2_number (String tax2_number)
    {
        this.tax2_number = tax2_number;
    }

    public String getTax2_amount ()
    {
        return tax2_amount;
    }

    public void setTax2_amount (String tax2_amount)
    {
        this.tax2_amount = tax2_amount;
    }

    public String getTax1_number ()
    {
        return tax1_number;
    }

    public void setTax1_number (String tax1_number)
    {
        this.tax1_number = tax1_number;
    }

    public String getTax1_amount ()
    {
        return tax1_amount;
    }

    public void setTax1_amount (String tax1_amount)
    {
        this.tax1_amount = tax1_amount;
    }

    public String getCustomer_ref ()
    {
        return customer_ref;
    }

    public void setCustomer_ref (String customer_ref)
    {
        this.customer_ref = customer_ref;
    }
}

