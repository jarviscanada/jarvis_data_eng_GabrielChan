package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.NotFoundException;
import ca.jrvs.apps.twitter.dao.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {
    private TwitterService service;

    @Before
    public void setUp() {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");
        System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao dao = new TwitterDao(httpHelper);
        service = new TwitterService(dao);
    }

    @Test
    public void postTweet() throws InvalidTweetException, UnsupportedEncodingException, URISyntaxException,
            NotFoundException {
        String hashTag = "#abc";
        String text = "sometext" + hashTag + System.currentTimeMillis();
        Float lat = 1f;
        Float lon = -1f;
        Tweet postTweet = new Tweet (text, lon, lat);
        System.out.println(postTweet);

        Tweet tweet = service.postTweet(postTweet);

        assertEquals(text, tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getCoordinates().size());
        assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
        assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
    }

    @Test
    public void showTweet() throws URISyntaxException, InvalidQueryException, NotFoundException {
        String id = "1479553878219800581";
        String[] fields = {"text", "created_at"};
        Tweet tweet = service.showTweet(id, fields);
        assertNotNull(tweet);
        assertEquals(Long.parseLong(id), Long.parseLong(tweet.getId_str()));
        assertEquals("test post", tweet.getText());
        assertNull(tweet.getCoordinates());
        assertNull(tweet.getEntities().getHashTags());
        assertNull(tweet.getEntities().getUserMentions());
        assertNull(tweet.getRetweetCount());
        assertNull(tweet.getFavouriteCount());
        assertNull(tweet.isFavourited());
        assertNull(tweet.isRetweeted());
    }

    @Test
    public void deleteTweets() throws URISyntaxException, InvalidQueryException {
        String id1 = "1486053698119819271";
        String text1 = "sometext#abc1643137784294";
        String id2 = "1486053007557107716";
        String text2 = "sometext#abc1643137619618";
        String id3 = "1485712202199932930";
        String text3 = "sometext#abc1643056150571";
        String[] ids = {id1, id2, id3};
        List<Tweet> tweets = service.deleteTweets(ids);
        assertNotNull(tweets);
        assertEquals(id1, tweets.get(0).getId_str());
        assertEquals(text1, tweets.get(0).getText());
        assertEquals(id2, tweets.get(1).getId_str());
        assertEquals(text2, tweets.get(1).getText());
        assertEquals(id3, tweets.get(2).getId_str());
        assertEquals(text3, tweets.get(2).getText());
    }
}