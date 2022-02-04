package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.JsonUtils;
import ca.jrvs.apps.twitter.dao.NotFoundException;
import ca.jrvs.apps.twitter.service.InvalidQueryException;
import ca.jrvs.apps.twitter.service.InvalidTweetException;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.dao.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
    @Mock
    Service mockService;

    @InjectMocks
    TwitterController controller;

    @Test
    public void postTweet() throws InvalidTweetException, UnsupportedEncodingException, NotFoundException,
            URISyntaxException {
        String[] args = {"Array is the most #important thing in any programming #language"};
        TwitterController spyController = Mockito.spy(controller);
        Tweet tweet = spyController.postTweet(args);
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