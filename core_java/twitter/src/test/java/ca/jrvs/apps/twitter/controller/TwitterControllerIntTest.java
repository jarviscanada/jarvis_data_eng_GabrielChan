package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.NotFoundException;
import ca.jrvs.apps.twitter.dao.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.InvalidTweetException;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterControllerIntTest {
    private TwitterController controller;

    @Before
    public void setUp() throws Exception {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao dao = new TwitterDao(httpHelper);
        TwitterService service = new TwitterService(dao);
        controller = new TwitterController(service);
    }

    @Test
    public void postTweet() throws InvalidTweetException, UnsupportedEncodingException, NotFoundException, URISyntaxException {
        String[] args = {"Array is the most #important thing in any programming #language"};
        Tweet tweet = controller.postTweet(args);
        System.out.println(tweet);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
        assertEquals(args[0], tweet.getText());
        assertNotNull(tweet.getEntities().getHashTags());
        assertEquals("important", tweet.getEntities().getHashTags().get(0).getText());
        assertEquals("language", tweet.getEntities().getHashTags().get(1).getText());
    }

    @Test
    public void showTweet() {
        fail("Not Implemented");
    }

    @Test
    public void deleteTweet() {
        fail("Not Implemented");
    }
}