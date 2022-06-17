package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {
    private TwitterDao dao;
    public Float lon, lat;

    @Before
    public void setUp() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        dao = new TwitterDao(httpHelper);
        lat = 1f;
        lon = -1f;
        // Need to determine how to create a tweet and obtain the Twitter ID of the immediately created tweet to test
        // deletion.
    }

    @Test
    public void create() throws URISyntaxException, UnsupportedEncodingException, NotFoundException {
        String hashTag = "#abc";
        String text = "sometext" + hashTag + System.currentTimeMillis();
        Tweet postTweet = new Tweet (text);
        System.out.println(postTweet);

        Tweet tweet = dao.create(postTweet);

        assertEquals(text, tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
        //assertTrue(hashTag.contains(tweet.getEntities().getHashTags().get(0).getText()));
    }

    @Test
    public void findById() throws URISyntaxException, NotFoundException {
        String id = "1479553878219800581";
        Tweet tweet = dao.findById(id);
        assertNotNull(tweet);
        assertEquals("test post", tweet.getText());
    }

    @Test
    public void deleteById() throws URISyntaxException, NotFoundException, UnsupportedEncodingException {
        Tweet tweet = dao.create(new Tweet("To be deleted"));
        String id = tweet.getId_str();
        Tweet result = dao.deleteById(id);
        assertNotNull(result);
        assertEquals(id, String.valueOf(result.getId()));
    }
}