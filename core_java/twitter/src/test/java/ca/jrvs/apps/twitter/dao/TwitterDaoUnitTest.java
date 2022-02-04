package ca.jrvs.apps.twitter.dao;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
    @Mock
    HttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void showTweet() throws Exception {
        //test failed request
        String hashTag = "#abc";
        //adding timestamp as twitter doesn't allow duplicate tweets
        String text = "sometext" + hashTag + System.currentTimeMillis();
        //exception is expected here
        when(mockHelper.httpPost(isNotNull(), any())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(new Tweet(text));
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //Test happy path
        //however, we don't want to call parseResponseBody.
        //we will make a spyDao which can fake parseResponseBody return value
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],"
                + "      \"user_mentions\":[]"
                + "   },\n"
                + "   \"coordinates\":null,"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull(), any())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonUtils.getTweetFromJson(tweetJsonStr);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
        Tweet tweet = spyDao.create(new Tweet(text));
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void postTweet() throws Exception {
        fail("Not Implemented");
    }


    @Test
    public void deleteTweet() throws Exception {
        fail("Not Implemented");
    }
}