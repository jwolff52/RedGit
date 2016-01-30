package io.github.jwolff52.redgit;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jwolff52.redgit.util.LogHelper;
import net.dean.jraw.ApiException;
import net.dean.jraw.RedditClient;
import net.dean.jraw.fluent.FluentRedditClient;
import net.dean.jraw.managers.ModerationManager;
import net.dean.jraw.models.FlairTemplate;
import net.dean.jraw.models.LoggedInAccount;
import net.dean.jraw.models.Submission;

public class RedGit {
    private LoggedInAccount redditAccount;
    private RedditClient redditClient;
    private FluentRedditClient fluentRedditClient;
    private ModerationManager moderationManager;

    private final Submission BUG_FLAIR_SUBMISSION;

    public RedGit(RedditClient redditClient) {
        redditAccount = redditClient.me();
        this.redditClient = redditClient;
        fluentRedditClient = new FluentRedditClient(this.redditClient);
        moderationManager = new ModerationManager(redditClient);

        BUG_FLAIR_SUBMISSION = redditClient.getSubmission("43d72x");

        if(redditAccount.hasModMail()) {
            LogHelper.info("You've got mail!");
//            InboxPaginator modMail = fluentRedditClient.me().inbox().read();
//            modMail.setWhere("moderator/unread");
        }

        try {
            Submission submission = fluentRedditClient.subreddit(Main.getSubreddit()).submit("This is a test submission, this area will contain the info section of the github issue", "Issue #32 - Title of issue");

            ObjectMapper mapper = new ObjectMapper();

            LogHelper.info(redditAccount.data("{after: null, before: null, count: 25, limit: 25, name: \"weebobot\", show: \"all\"}")); // Doesn't work, need to figure out how to send a get request using this api...

            //curl -X -d "{after: null, before: null, count: 25, limit: 25, name: "weebobot", show: 'all'}" https://reddit.com/api/r/weebobot/api/flairlist

//            moderationManager.setFlair(Main.getSubreddit(), new FlairTemplate(BUG_FLAIR_SUBMISSION.getSubmissionFlair()), null, submission); //The line that needs to update the flair once i figure out how to properly create the JsonNode

            LogHelper.info("Successfully posted message with submission ID: " + submission.getId());
        } catch (ApiException e) {
            LogHelper.warning("Unable to create post for issue #32" + e);
        }

    }

    public RedditClient getRedditClient() {
        return redditClient;
    }

    private FlairTemplate getBugFlair() {
        try {
            for(FlairTemplate ft : fluentRedditClient.subreddit(Main.getSubreddit()).flair().options()) {
                LogHelper.info(ft.getCssClass());
                if(ft.getCssClass().equalsIgnoreCase("bugreport")) {
                    return ft;
                }
            }
        } catch (ApiException e) {
            LogHelper.warning("Error getting template for Bug Reports");
        }
        return null;
    }
}
