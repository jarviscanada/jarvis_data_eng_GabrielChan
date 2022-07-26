package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    @Autowired
    public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Update quote table using IEX source.
     *
     * @throws ca.jrvs.apps.trading.dao.ResourceNotFoundException if the ticker is not found in IEX
     * @throws org.springframework.dao.DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException if input is invalid
     */
    public void updateMarketData() {
        // Get all Quotes from DB
        List<Quote> quoteList = findAllQuotes();
        List<Quote> newQuoteList = new ArrayList<>();

        // Get IexQuote for each ticker
        // Convert each IexQuote to Quote
        quoteList.forEach(q -> newQuoteList.add(buildQuoteFromIexQuote(
                marketDataDao.findById(q.getId()).get())));

        // Persist Quote to DB
        quoteDao.saveAll(newQuoteList);
    }

    /**
     * Helper method for mapping an IexQuote to a Quote.
     *
     * @param iexQuote IexQuote to be mapped
     * @return The resulting mapped quote
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
        return new Quote(iexQuote.getSymbol(), iexQuote.getLatestPrice().doubleValue(),
                iexQuote.getIexBidPrice().doubleValue(), iexQuote.getIexBidSize(),
                iexQuote.getIexAskPrice().doubleValue(), iexQuote.getIexAskSize());
    }

    /**
     * Validate and save quotes with the given tickers using the IexQuote DB.
     *
     * @param tickers A list of tickers indicating quotes to be validated
     * @return A list of saved quotes
     */
    public List<Quote> saveQuotes(List<String> tickers) {
        List<Quote> quoteList = new ArrayList<>();

        // Get IexQuotes
        List<IexQuote> iexQuoteList = marketDataDao.findAllById(tickers);

        // Convert each IexQuote to Quote
        iexQuoteList.forEach(q -> quoteList.add(buildQuoteFromIexQuote(q)));

        // Persist the Quote to DB
        return (List<Quote>) quoteDao.saveAll(quoteList);
    }

    /**
     * Validate and save a quote with a given ticker using the IexQuote DB.
     *
     * @param ticker A ticker indicating a quote to be validated
     * @return The validated quote
     */
    public Quote saveQuote(String ticker) {
        IexQuote iexQuote;

        // Get IexQuote
        Optional<IexQuote> optional = marketDataDao.findById(ticker);
        if (optional.isPresent()) {
            iexQuote = optional.get();
        }
        else {
            return null;
        }

        // Convert IexQuote to Quote
        Quote quote = buildQuoteFromIexQuote(iexQuote);

        // Persist Quote to DB
        return quoteDao.save(quote);
    }

    /**
     * Return the IexQuote with the given ticker.
     *
     * @param ticker Ticker to be queried
     * @return IexQuote with the given ticker
     */
    public IexQuote findIexQuoteByTicker(String ticker) {
        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
    }

    public Quote saveQuote(Quote quote) {
        return quoteDao.save(quote);
    }

    /**
     * Return all quotes from the Quote DB.
     *
     * @return A list of all quotes from the Quote DB
     */
    public List<Quote> findAllQuotes() {
        return (List<Quote>) quoteDao.findAll();
    }
}
