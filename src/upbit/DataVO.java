package upbit;

import org.codehaus.jackson.annotate.JsonProperty;

public class DataVO {

	@JsonProperty
	String code;
	@JsonProperty
	String candleDateTime; 
	@JsonProperty
	String candleDateTimeKst; 
	@JsonProperty
	String openingPrice;
	@JsonProperty
	String highPrice;
	@JsonProperty
	String lowPrice;
	@JsonProperty
	Double tradePrice;
	@JsonProperty
	String candleAccTradeVolume;
	@JsonProperty
	String candleAccTradePrice;
	@JsonProperty
	String timestamp;
	@JsonProperty
	String tickCount;
	@JsonProperty
	String sequentialId; 

	@JsonProperty
	String unit; 

	@JsonProperty
	double a; 
	@JsonProperty
	double b;
	@JsonProperty
	double diff;

}
