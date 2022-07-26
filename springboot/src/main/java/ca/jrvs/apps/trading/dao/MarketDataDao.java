package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

// Ticker is a short form representation of a company name

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    @Autowired
    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
                         MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    /**
     * Return an HTTP client from 'httpClientConnectionManager'
     * @return an HTTP client
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
                .setConnectionManagerShared(true).build();
    }

    /**
     * Send an HTTP GET request and return the body of the response as a String
     * @param uri The URI to send the request to
     * @return An Optional containing the response's body as a String
     * @throws URISyntaxException
     * @throws IOException
     */
    private Optional<String> executeHttpGet(String uri) throws URISyntaxException, IOException {
        HttpClient httpClient = getHttpClient();
        // Create HTTP request
        HttpGet request = new HttpGet(new URI(uri));
        // Execute HTTP request and get response
        HttpResponse response = httpClient.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        // Convert response into a String if successful
        if (statusCode == 200) {
            return Optional.of(EntityUtils.toString(response.getEntity()));
        }
        // Return nothing if not found
        else if (statusCode == 404) {
            return Optional.empty();
        }
        else {
            throw new DataRetrievalFailureException("HTTP request failed: " + statusCode);
        }
    }

    /**
     * Return the IexQuote with the given ticker if it exists.
     * @param ticker Ticker to be queried
     * @return An Optional with the given ticker if it exists
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        }
        else if (quotes.size() == 1) {
            return Optional.of(quotes.get(0));
        }
        else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
    }

    /**
     * Return all the IexQuotes with the given list of tickers.
     * @param ids List of tickers to be queried
     * @return A list of IexQuotes that use the given tickers
     */
    @Override
    public List<IexQuote> findAllById(@NotEmpty Iterable<String> ids) {
        String response;
        //String query = "";
        // Send request for quote batch and receive response
        try {
            // Add and specify ticker in URI
            Iterator<String> iter = ids.iterator();
            String symbols = iter.next();
            while (iter.hasNext()) {
                symbols = symbols + "," + iter.next();
            }
            String query = String.format(IEX_BATCH_URL, symbols);
            response = executeHttpGet(query).get();
        } catch (URISyntaxException | IOException e) {
            //System.out.println(query);
            /*
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                System.out.println(stackTraceElement);
            }
            */
            throw new IllegalArgumentException("Invalid ticker");
        }

        // Convert response to JSON object
        JSONObject IexQuotesJson = new JSONObject(response);
        List<IexQuote> quoteList;
        if (IexQuotesJson.isEmpty()) {
            throw new IllegalArgumentException("Invalid ticker");
        }
        // Convert JSON object to list of IexQuotes
        else {
            quoteList = new ArrayList<>();
            Iterator<String> iter = ids.iterator();
            iter.forEachRemaining(s -> {
                try {
                    quoteList.add(new IexQuote(IexQuotesJson.getJSONObject(s).getJSONObject("quote")));
                } catch (JSONException e) {
                    throw new IllegalArgumentException("Invalid ticker");
                }
            });

        }
        return quoteList;
    }

    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
