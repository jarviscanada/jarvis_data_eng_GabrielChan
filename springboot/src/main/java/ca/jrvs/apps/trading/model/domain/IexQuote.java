package ca.jrvs.apps.trading.model.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class IexQuote {
    // Include all fields from API example
    private String symbol = null;
    private String companyName = null;
    private String primaryExchange = null;
    private String calculationPrice = null;
    private Float open = null;
    private Integer openTime = null;
    private String openSource = null;
    private Float close = null;
    private Integer closeTime = null;
    private String closeSource = null;
    private Float high = null;
    private Integer highTime = null;
    private String highSource= null;
    private Float low = null;
    private Integer lowTime = null;
    private String lowSource = null;
    private Float latestPrice = null;
    private String latestSource = null;
    private String latestTime = null;
    private Integer latestUpdate = null;
    private Integer latestVolume = null;
    private Float iexRealtimePrice = null;
    private Integer iexRealtimeSize = null;
    private Integer iexLastUpdated = null;
    private Float delayedPrice = null;
    private Integer delayedPriceTime = null;
    private Float oddLotDelayedPrice = null;
    private Integer oddLotDelayedPriceTime = null;
    private Float extendedPrice = null;
    private Float extendedChange = null;
    private Float extendedChangePercent = null;
    private Integer extendedPriceTime = null;
    private Float previousClose = null;
    private Integer previousVolume = null;
    private Float change = null;
    private Float changePercent = null;
    private Integer volume = null;
    private Float iexMarketPercent = null;
    private Integer iexVolume = null;
    private Integer avgTotalVolume = null;
    private Integer iexBidPrice = null;
    private Integer iexBidSize = null;
    private Integer iexAskPrice = null;
    private Integer iexAskSize = null;
    private Float iexOpen = null;
    private Integer iexOpenTime = null;
    private Float iexClose = null;
    private Integer iexCloseTime = null;
    private Integer marketCap = null;
    private Float peRatio = null;
    private Float week52high = null;
    private Float week52low = null;
    private Float ytdChange = null;
    private Integer lastTradeTime = null;
    private String currency = null;
    private Boolean isUSMarketOpen = null;

    public IexQuote (JSONObject jsonObject) {
        // The data being returned may not contain every attribute. Hence, any attribute that could not be found will
        // be assigned the null value by default.
        try {symbol = jsonObject.getString("symbol");} catch (JSONException e) {}
        try {companyName = jsonObject.getString("companyName");} catch (JSONException e) {}
        try {primaryExchange = jsonObject.getString("primaryExchange");} catch (JSONException e) {}
        try {calculationPrice = jsonObject.getString("calculationPrice");} catch (JSONException e) {}
        try {open = jsonObject.getFloat("open");} catch (JSONException e) {}
        try {openTime = jsonObject.getInt("openTime");} catch (JSONException e) {}
        try {openSource = jsonObject.getString("openSource");} catch (JSONException e) {}
        try {close = jsonObject.getFloat("close");} catch (JSONException e) {}
        try {closeTime = jsonObject.getInt("closeTime");} catch (JSONException e) {}
        try {closeSource = jsonObject.getString("closeSource");} catch (JSONException e) {}
        try {high = jsonObject.getFloat("high");} catch (JSONException e) {}
        try {highTime = jsonObject.getInt("highTime");} catch (JSONException e) {}
        try {highSource = jsonObject.getString("highSource");} catch (JSONException e) {}
        try {low = jsonObject.getFloat("low");} catch (JSONException e) {}
        try {lowTime = jsonObject.getInt("lowTime");} catch (JSONException e) {}
        try {lowSource = jsonObject.getString("lowSource");} catch (JSONException e) {}
        try {latestPrice = jsonObject.getFloat("latestPrice");} catch (JSONException e) {}
        try {latestSource = jsonObject.getString("latestSource");} catch (JSONException e) {}
        try {latestTime = jsonObject.getString("latestTime");} catch (JSONException e) {}
        try {latestUpdate = jsonObject.getInt("latestUpdate");} catch (JSONException e) {}
        try {latestVolume = jsonObject.getInt("latestVolume");} catch (JSONException e) {}
        try {iexRealtimePrice = jsonObject.getFloat("iexRealtimePrice");} catch (JSONException e) {}
        try {iexRealtimeSize = jsonObject.getInt("iexRealtimeSize");} catch (JSONException e) {}
        try {iexLastUpdated = jsonObject.getInt("iexLastUpdated");} catch (JSONException e) {}
        try {delayedPrice = jsonObject.getFloat("delayedPrice");} catch (JSONException e) {}
        try {delayedPriceTime = jsonObject.getInt("delayedPriceTime");} catch (JSONException e) {}
        try {oddLotDelayedPrice = jsonObject.getFloat("oddLotDelayedPrice");} catch (JSONException e) {}
        try {oddLotDelayedPriceTime = jsonObject.getInt("oddLotDelayedPriceTime");} catch (JSONException e) {}
        try {extendedPrice = jsonObject.getFloat("extendedPrice");} catch (JSONException e) {}
        try {extendedChange = jsonObject.getFloat("extendedChange");} catch (JSONException e) {}
        try {extendedChangePercent = jsonObject.getFloat("extendedChangePercent");} catch (JSONException e) {}
        try {extendedPriceTime = jsonObject.getInt("extendedPriceTime");} catch (JSONException e) {}
        try {previousClose = jsonObject.getFloat("previousClose");} catch (JSONException e) {}
        try {previousVolume = jsonObject.getInt("previousVolume");} catch (JSONException e) {}
        try {change = jsonObject.getFloat("change");} catch (JSONException e) {}
        try {changePercent = jsonObject.getFloat("changePercent");} catch (JSONException e) {}
        try {volume = jsonObject.getInt("volume");} catch (JSONException e) {}
        try {iexMarketPercent = jsonObject.getFloat("iexMarketPercent");} catch (JSONException e) {}
        try {iexVolume = jsonObject.getInt("iexVolume");} catch (JSONException e) {}
        try {avgTotalVolume = jsonObject.getInt("avgTotalVolume");} catch (JSONException e) {}
        try {iexBidPrice = jsonObject.getInt("iexBidPrice");} catch (JSONException e) {}
        try {iexBidSize = jsonObject.getInt("iexBidSize");} catch (JSONException e) {}
        try {iexAskPrice = jsonObject.getInt("iexAskPrice");} catch (JSONException e) {}
        try {iexAskSize = jsonObject.getInt("iexAskSize");} catch (JSONException e) {}
        try {iexOpen = jsonObject.getFloat("iexOpen");} catch (JSONException e) {}
        try {iexOpenTime = jsonObject.getInt("iexOpenTime");} catch (JSONException e) {}
        try {iexClose = jsonObject.getFloat("iexClose");} catch (JSONException e) {}
        try {iexCloseTime = jsonObject.getInt("iexCloseTime");} catch (JSONException e) {}
        try {marketCap = jsonObject.getInt("marketCap");} catch (JSONException e) {}
        try {peRatio = jsonObject.getFloat("peRatio");} catch (JSONException e) {}
        try {week52high = jsonObject.getFloat("week52high");} catch (JSONException e) {}
        try {week52low = jsonObject.getFloat("week52low");} catch (JSONException e) {}
        try {ytdChange = jsonObject.getFloat("ytdChange");} catch (JSONException e) {}
        try {lastTradeTime = jsonObject.getInt("lastTradeTime");} catch (JSONException e) {}
        try {currency = jsonObject.getString("currency");} catch (JSONException e) {}
        try {isUSMarketOpen = jsonObject.getBoolean("isUSMarketOpen");} catch (JSONException e) {}
    }

    @Override
    public String toString() {
        return "{\n" +
                "symbol: " + symbol + "\n" +
                "companyName: " + companyName + "\n" +
                "primaryExchange: " + primaryExchange + "\n" +
                "calculationPrice: " + calculationPrice + "\n" +
                "open: " + open + "\n" +
                "openTime: " + openTime + "\n" +
                "openSource: " + openSource + "\n" +
                "close: " + close + "\n" +
                "closeTime: " + closeTime + "\n" +
                "closeSource: " + closeSource + "\n" +
                "high: " + high + "\n" +
                "highTime: " + highTime + "\n" +
                "highSource: " + highSource + "\n" +
                "low: " + low + "\n" +
                "lowTime: " + lowTime + "\n" +
                "lowSource: " + lowSource + "\n" +
                "latestPrice: " + latestPrice + "\n" +
                "latestSource: " + latestSource + "\n" +
                "latestTime: " + latestTime + "\n" +
                "latestUpdate: " + latestUpdate + "\n" +
                "latestVolume: " + latestVolume + "\n" +
                "iexRealtimePrice: " + iexRealtimePrice + "\n" +
                "iexRealtimeSize: " + iexRealtimeSize + "\n" +
                "iexLastUpdated: " + iexLastUpdated + "\n" +
                "delayedPrice: " + delayedPrice + "\n" +
                "delayedPriceTime: " + delayedPriceTime + "\n" +
                "oddLotDelayedPrice: " + oddLotDelayedPrice + "\n" +
                "oddLotDelayedPriceTime: " + oddLotDelayedPriceTime + "\n" +
                "extendedPrice: " + extendedPrice + "\n" +
                "extendedChange: " + extendedChange + "\n" +
                "extendedChangePercent: " + extendedChangePercent + "\n" +
                "extendedPriceTime: " + extendedPriceTime + "\n" +
                "previousClose: " + previousClose + "\n" +
                "previousVolume: " + previousVolume + "\n" +
                "change: " + change + "\n" +
                "changePercent: " + changePercent + "\n" +
                "volume: " + volume + "\n" +
                "iexMarketPercent: " + iexMarketPercent + "\n" +
                "iexVolume: " + iexVolume + "\n" +
                "avgTotalVolume: " + avgTotalVolume + "\n" +
                "iexBidPrice: " + iexBidPrice + "\n" +
                "iexBidSize: " + iexBidSize + "\n" +
                "iexAskPrice: " + iexAskPrice + "\n" +
                "iexAskSize: " + iexAskSize + "\n" +
                "iexOpen: " + iexOpen + "\n" +
                "iexOpenTime: " + iexOpenTime + "\n" +
                "iexClose: " + iexClose + "\n" +
                "iexCloseTime: " + iexCloseTime + "\n" +
                "marketCap: " + marketCap + "\n" +
                "peRatio: " + peRatio + "\n" +
                "week52High: " + week52high + "\n" +
                "week52Low: " + week52low + "\n" +
                "ytdChange: " + ytdChange + "\n" +
                "lastTradeTime: " + lastTradeTime + "\n" +
                "currency: " + currency + "\n" +
                "isUSMarketOpen" + isUSMarketOpen + "\n" +
                "}";
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public Float getOpen() {
        return open;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public String getOpenSource() {
        return openSource;
    }

    public Float getClose() {
        return close;
    }

    public Integer getCloseTime() {
        return closeTime;
    }

    public String getCloseSource() {
        return closeSource;
    }

    public Float getHigh() {
        return high;
    }

    public Integer getHighTime() {
        return highTime;
    }

    public String getHighSource() {
        return highSource;
    }

    public Float getLow() {
        return low;
    }

    public Integer getLowTime() {
        return lowTime;
    }

    public String getLowSource() {
        return lowSource;
    }

    public Float getLatestPrice() {
        return latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public Integer getLatestUpdate() {
        return latestUpdate;
    }

    public Integer getLatestVolume() {
        return latestVolume;
    }

    public Float getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public Integer getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public Integer getIexLastUpdated() {
        return iexLastUpdated;
    }

    public Float getDelayedPrice() {
        return delayedPrice;
    }

    public Integer getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public Float getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    public Integer getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    public Float getExtendedPrice() {
        return extendedPrice;
    }

    public Float getExtendedChange() {
        return extendedChange;
    }

    public Float getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public Integer getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public Float getPreviousClose() {
        return previousClose;
    }

    public Integer getPreviousVolume() {
        return previousVolume;
    }

    public Float getChange() {
        return change;
    }

    public Float getChangePercent() {
        return changePercent;
    }

    public Integer getVolume() {
        return volume;
    }

    public Float getIexMarketPercent() {
        return iexMarketPercent;
    }

    public Integer getIexVolume() {
        return iexVolume;
    }

    public Integer getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public Integer getIexBidPrice() {
        return iexBidPrice;
    }

    public Integer getIexBidSize() {
        return iexBidSize;
    }

    public Integer getIexAskPrice() {
        return iexAskPrice;
    }

    public Integer getIexAskSize() {
        return iexAskSize;
    }

    public Float getIexOpen() {
        return iexOpen;
    }

    public Integer getIexOpenTime() {
        return iexOpenTime;
    }

    public Float getIexClose() {
        return iexClose;
    }

    public Integer getIexCloseTime() {
        return iexCloseTime;
    }

    public Integer getMarketCap() {
        return marketCap;
    }

    public Float getPeRatio() {
        return peRatio;
    }

    public Float getWeek52high() {
        return week52high;
    }

    public Float getWeek52low() {
        return week52low;
    }

    public Float getYtdChange() {
        return ytdChange;
    }

    public Integer getLastTradeTime() {
        return lastTradeTime;
    }

    public String getCurrency() {
        return currency;
    }

    public Boolean getUSMarketOpen() {
        return isUSMarketOpen;
    }
}
