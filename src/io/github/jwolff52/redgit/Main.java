package io.github.jwolff52.redgit;

import io.github.jwolff52.redgit.util.LogHelper;
import io.github.jwolff52.redgit.util.reddit.RenewOAUthTimer;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthException;

public class Main {
    private static Credentials credentials;
    private static RedGit redGit;
    private static String subreddit;

    public static void main(String[] args) {
        UserAgent userAgent = UserAgent.of("desktop", "io.github.jwolff52.redgit", "v0.1", args[0]);
        RedditClient redditClient = new RedditClient(userAgent);
        credentials = Credentials.script(args[0], args[1], args[2], args[3]);

        setSubreddit(args[4]);
        try {
            redditClient.authenticate(redditClient.getOAuthHelper().easyAuth(credentials));
        } catch (OAuthException e) {
            LogHelper.severe("Unable to authenticate user, shutting down..." + e);
            System.exit(-1);
        }
        new RenewOAUthTimer();
        redGit = new RedGit(redditClient);
    }

    public static void renewOAuth() {
        try {
            redGit.getRedditClient().authenticate(redGit.getRedditClient().getOAuthHelper().easyAuth(credentials));
        } catch (OAuthException e) {
            LogHelper.severe("Unable to authenticate user, shutting down..." + e);
            System.exit(-1);
        }
    }

    public static String getSubreddit() {
        return subreddit;
    }

    public static void setSubreddit(String subreddit) {
        Main.subreddit = subreddit;
    }
}
