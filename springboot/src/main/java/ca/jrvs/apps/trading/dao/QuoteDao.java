package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

    private static final String TABLE_NAME = "quote";
    private static final String ID_COLUMN_NAME = "ticker";

    private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public QuoteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
    }

    /**
     * Helper method that saves one quote.
     *
     * @param quote Quote to be added
     */
    private void addOne(Quote quote) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
        int row = simpleJdbcInsert.execute(parameterSource);
        if (row != 1) {
            throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
        }
    }

    /**
     * Helper method that updates one quote.
     *
     * @param quote Quote containing information to be added
     * @return Number of record affected by this operation
     */
    private int updateOne(Quote quote) {
        String update_sql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, " +
                "ask_size=? WHERE ticker=?";
        return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
    }

    /**
     * Helper method that makes SQL update values.
     *
     * @param quote Quote to be updated
     * @return UPDATE_SQL values
     */
    private Object[] makeUpdateValues(Quote quote) {
        Object[] values = {quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(), quote.getAskPrice(),
            quote.getAskSize()};
        return values;
    }

    /**
     * Save the given quote.
     *
     * @param quote Quote to be saved
     * @return The given quote if it was successfully saved
     * @param <S>
     */
    @Override
    public <S extends Quote> S save(S quote) {
        if (existsById(quote.getId())) {
            int updatedRowNo = updateOne(quote);
            if (updatedRowNo != 1) {
                throw new DataRetrievalFailureException("Unable to update quote");
            }
        }
        else {
            addOne(quote);
        }
        return quote;
    }

    /**
     * Save all the given quotes.
     *
     * @param quotes List of quotes to be saved
     * @return The given list of quotes if they were successfully saved
     * @param <S>
     */
    @Override
    public <S extends Quote> Iterable<S> saveAll(Iterable<S> quotes) {
        Iterator<S> iter = quotes.iterator();
        iter.forEachRemaining(q -> save(q));
        return quotes;
    }

    /**
     * Return the quote with the given ticker if it exists.
     *
     * @param ticker Ticker to be queried
     * @return An Optional containing the quote with the given ticker if it exists
     */
    @Override
    public Optional<Quote> findById(String ticker) {
        Quote quote;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "='" + ticker + "'";
        try {
            quote = jdbcTemplate.queryForObject(query, Quote.class);
        }
        catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
        return Optional.of(quote);
    }

    /**
     * Return 'true' if a quote with the given ticker exists and 'false' otherwise.
     *
     * @param ticker Ticker to be queried
     * @return A boolean determining whether a quote with the given ticker exists
     */
    @Override
    public boolean existsById(String ticker) {
        return findById(ticker).isPresent();
    }

    /**
     * Return all quotes.
     *
     * @return List of all quotes
     */
    @Override
    public Iterable<Quote> findAll() {
        String query = "SELECT * FROM " + TABLE_NAME;
        List<Map<String, Object>> data = jdbcTemplate.queryForList(query);
        List<Quote> quotes = new ArrayList<>();
        data.forEach(q -> quotes.add(new Quote(q)));
        return quotes;
    }

    @Override
    public Iterable<Quote> findAllById(Iterable<String> tickers) {
        /*
        Iterator<String> iter = tickers.iterator();
        List<Quote> quoteList = new ArrayList<>();
        do {
            findById(iter.next()).ifPresent(quoteList::add);
        } while (iter.hasNext());
        return quoteList;
        */
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Return the number of quotes.
     *
     * @return The number of quotes in the given table
     */
    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    /**
     * Delete the quote with the given ticker.
     *
     * @param ticker Ticker of the quote to be deleted
     */
    @Override
    public void deleteById(String ticker) {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "='" + ticker + "'";
        jdbcTemplate.execute(query);
    }

    @Override
    public void delete(Quote quote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Quote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Delete all quotes.
     */
    @Override
    public void deleteAll() {
        String query = "DELETE FROM " + TABLE_NAME;
        jdbcTemplate.execute(query);
    }
}
