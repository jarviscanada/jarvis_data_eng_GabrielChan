package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

    @Autowired
    private QuoteDao quoteDao;

    private Quote savedQuote;

    @Before
    public void insertOne() {
        savedQuote = new Quote("AAPL", 10.1d, 10.2d, 10, 10d, 10);
        quoteDao.save(savedQuote);
    }

    @Test
    public void test() {
        List<Quote> quoteList;

        // Test methods for initial data
        assertEquals(1, quoteDao.count());
        assertTrue(quoteDao.existsById("AAPL"));
        assertEquals(savedQuote, quoteDao.findById("AAPL").get());
        assertFalse(quoteDao.existsById("FB"));
        quoteList = (List<Quote>) quoteDao.findAll();
        assertEquals(1, quoteList.size());
        assertTrue(quoteList.contains(savedQuote));

        // Test methods after insertion
        Quote FBQuote = new Quote("FB", 15.3d, 15d, 10, 11d, 10);
        quoteDao.save(FBQuote);
        assertEquals(2, quoteDao.count());
        assertTrue(quoteDao.existsById("FB"));
        assertEquals(FBQuote, quoteDao.findById("FB").get());
        quoteList = (List<Quote>) quoteDao.findAll();
        assertEquals(2, quoteList.size());
        assertTrue(quoteList.contains(FBQuote));

        // Test methods after deletion
        quoteDao.deleteById(("FB"));
        assertEquals(1, quoteDao.count());
        assertFalse(quoteDao.existsById("FB"));
        quoteList = (List<Quote>) quoteDao.findAll();
        assertEquals(1, quoteList.size());
        assertFalse(quoteList.contains(FBQuote));

        quoteDao.deleteAll();
        assertEquals(0, quoteDao.count());
        assertFalse(quoteDao.existsById("AAPL"));
        quoteList = (List<Quote>) quoteDao.findAll();
        assertEquals(0, quoteList.size());
    }

    /*
    @After
    public void deleteOne() {
        quoteDao.deleteById(savedQuote.getId());
    }
    */
}
