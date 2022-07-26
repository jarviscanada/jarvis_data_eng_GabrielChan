package ca.jrvs.apps.trading.model.domain;

import java.util.Map;

public class Quote implements Entity<String> {
    private String ticker;
    private Double lastPrice = null;
    private Double bidPrice = null;
    private Integer bidSize = null;
    private Double askPrice = null;
    private Integer askSize = null;

    public Quote(String ticker, Double lastPrice, Double bidPrice, Integer bidSize,
                 Double askPrice, Integer askSize) {
        this.ticker = ticker;
        this.lastPrice = lastPrice;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    public Quote(Map<String, Object> quote) {
        ticker = (String) quote.get("ticker");
        lastPrice = (Double) quote.get("lastPrice");
        bidPrice = (Double) quote.get("bidPrice");
        bidSize = (Integer) quote.get("bidSize");
        askPrice = (Double) quote.get("askPrice");
        askSize = (Integer) quote.get("askSize");
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Quote &&
                ticker.equals(((Quote) object).getId()) &&
                lastPrice.equals(((Quote) object).getLastPrice()) &&
                bidPrice.equals(((Quote) object).getBidPrice()) &&
                bidSize.equals(((Quote) object).getBidSize()) &&
                askPrice.equals(((Quote) object).getAskPrice()) &&
                askSize.equals(((Quote) object).getAskSize()));
    }

    @Override
    public String toString() {
        return "{\n" +
                "ticker: " + ticker + "\n" +
                "lastPrice: " + lastPrice + "\n" +
                "bidPrice: " + bidPrice + "\n" +
                "bidSize: " + bidSize + "\n" +
                "askPrice: " + askPrice + "\n" +
                "askSize: " + askSize + "\n" +
                "}";
    }

    @Override
    public String getId() {
        return ticker;
    }

    @Override
    public void setId(String s) {
        ticker = s;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getBidSize() {
        return bidSize;
    }

    public void setBidSize(Integer bidSize) {
        this.bidSize = bidSize;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Integer getAskSize() {
        return askSize;
    }

    public void setAskSize(Integer askSize) {
        this.askSize = askSize;
    }
}
