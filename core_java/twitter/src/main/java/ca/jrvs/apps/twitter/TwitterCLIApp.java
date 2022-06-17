package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.NotFoundException;
import ca.jrvs.apps.twitter.dao.Tweet;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.InvalidQueryException;
import ca.jrvs.apps.twitter.service.InvalidTweetException;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class TwitterCLIApp {
    private static String CONSUMER_KEY = System.getenv("consumerKey");
    private static String CONSUMER_SECRET = System.getenv("consumerSecret");
    private static String ACCESS_TOKEN = System.getenv("accessToken");
    private static String TOKEN_SECRET = System.getenv("tokenSecret");

    private static HttpHelper helper;
    private static CrdDao dao;
    private static Service service;
    private static Controller controller;

    public static void run(String @NotNull [] args) throws NotFoundException, URISyntaxException, InvalidQueryException,
            InvalidTweetException, UnsupportedEncodingException {
        if (args[0].equals("GET")) {
            Tweet tweet = controller.showTweet(Arrays.copyOfRange(args, 1, args.length));
            System.out.println(tweet);
        }
        else if (args[0].equals("POST")) {
            Tweet tweet = controller.postTweet(Arrays.copyOfRange(args, 1, args.length));
            System.out.println(tweet);
        }
        else if (args[0].equals("DELETE")) {
            List<Tweet> tweets = controller.deleteTweet(Arrays.copyOfRange(args, 1, args.length));
            for (Tweet t : tweets) {
                System.out.println(t);
            }
        }
        else {
            System.out.println("Invalid argument format. Please use the proper format:\n"
                + "GET <id> [field1] [field2] [field3] ...\n"
                + "POST <text>\n"
                + "DELETE <id1> [id2] [id3] ...");
        }
    }

    public static void main(String[] args) throws InvalidTweetException, NotFoundException,
            UnsupportedEncodingException, URISyntaxException, InvalidQueryException {
        helper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
        dao = new TwitterDao(helper);
        service = new TwitterService(dao);
        controller = new TwitterController(service);

        run(args);
    }
}
