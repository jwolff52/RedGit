package io.github.jwolff52.redgit;

import io.github.jwolff52.redgit.util.LogHelper;
import net.dean.jraw.ApiException;
import net.dean.jraw.RedditClient;
import net.dean.jraw.fluent.FluentRedditClient;
import net.dean.jraw.models.LoggedInAccount;

public class RedGit {
    private LoggedInAccount redditAccount;
    private RedditClient redditClient;
    private FluentRedditClient fluentRedditClient;

    public RedGit(RedditClient redditClient) {
        redditAccount = redditClient.me();
        this.redditClient = redditClient;
        fluentRedditClient = new FluentRedditClient(this.redditClient);

        if(redditAccount.hasModMail()) {
            LogHelper.info("You've got mail!");
//            InboxPaginator modMail = fluentRedditClient.me().inbox().read();
//            modMail.setWhere("moderator/unread");
        }

        try {
            fluentRedditClient.subreddit("weebobot").submit("This is a test submission, this area will contain the info section of the github issue", "Issue #32 - Title of issue");
        } catch (ApiException e) {
            LogHelper.warning("Unable to create post for issue #32" + e);
        }

    }

    public RedditClient getRedditClient() {
        return redditClient;
    }
}
