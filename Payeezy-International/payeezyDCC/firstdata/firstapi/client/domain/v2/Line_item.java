package com.firstdata.firstapi.client.domain.v2;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Line_item
{
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax_type")
	private String tax_type;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("unit_cost")
	private String unit_cost;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("discount_amount")
	private String discount_amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("line_item_total")
	private String line_item_total;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("unit_of_measure")
	private String unit_of_measure;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("discount_indicator")
	private String discount_indicator;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("gross_net_indicator")
	private String gross_net_indicator;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("description")
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("commodity_code")
	private String commodity_code;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("product_code")
	private String product_code;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax_amount")
	private String tax_amount;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("quantity")
	private String quantity;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("tax_rate")
	private String tax_rate;
	
	public String getTax_type ()
	{
	return tax_type;
	}
	
	public void setTax_type (String tax_type)
	{
	this.tax_type = tax_type;
	}
	
	public String getUnit_cost ()
	{
	return unit_cost;
	}
	
	public void setUnit_cost (String unit_cost)
	{
	this.unit_cost = unit_cost;
	}
	
	public String getDiscount_amount ()
	{
	return discount_amount;
	}
	
	public void setDiscount_amount (String discount_amount)
	{
	this.discount_amount = discount_amount;
	}
	
	public String getLine_item_total ()
	{
	return line_item_total;
	}
	
	public void setLine_item_total (String line_item_total)
	{
	this.line_item_total = line_item_total;
	}
	
	public String getUnit_of_measure ()
	{
	return unit_of_measure;
	}
	
	public void setUnit_of_measure (String unit_of_measure)
	{
	this.unit_of_measure = unit_of_measure;
	}
	
	public String getDiscount_indicator ()
	{
	return discount_indicator;
	}
	
	public void setDiscount_indicator (String discount_indicator)
	{
	this.discount_indicator = discount_indicator;
	}
	
	public String getGross_net_indicator ()
	{
	return gross_net_indicator;
	}
	
	public void setGross_net_indicator (String gross_net_indicator)
	{
	this.gross_net_indicator = gross_net_indicator;
	}
	
	public String getDescription ()
	{
	return description;
	}
	
	public void setDescription (String description)
	{
	this.description = description;
	}
	
	public String getCommodity_code ()
	{
	return commodity_code;
	}
	
	public void setCommodity_code (String commodity_code)
	{
	this.commodity_code = commodity_code;
	}
	
	public String getProduct_code ()
	{
	return product_code;
	}
	
	public void setProduct_code (String product_code)
	{
	this.product_code = product_code;
	}
	
	public String getTax_amount ()
	{
	return tax_amount;
	}
	
	public void setTax_amount (String tax_amount)
	{
	this.tax_amount = tax_amount;
	}
	
	public String getQuantity ()
	{
	return quantity;
	}
	
	public void setQuantity (String quantity)
	{
	this.quantity = quantity;
	}
	
	public String getTax_rate ()
	{
	return tax_rate;
	}
	
	public void setTax_rate (String tax_rate)
	{
	this.tax_rate = tax_rate;
	}
}

