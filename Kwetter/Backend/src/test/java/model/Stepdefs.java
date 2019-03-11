package model;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author Jeroen Roovers
 */
public class Stepdefs {

    private List<User> users;
    private List<Kweet> kweets;

    @Given("^there are (\\d+) users$")
    public void there_are_users(int arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        users = new ArrayList<>(arg1);
        Role defaultRole = new Role(false, false, false, false);
        for (int i = 1; i < (arg1 + 1); i++) {
            users.add(new User(
                    String.format("User{0}", i),
                    defaultRole)
            );
        }
    }

    @When("^everyone starts following eachother$")
    public void everyone_starts_following_eachother() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        for (User a : users) {
            for (User b : users) {
                if (a != b) {
                    a.addFollower(b);
                    b.followUser(a);
                }
            }
        }
    }

    @When("^everyone places (\\d+) tweet$")
    public void everyone_places_tweet(int arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        int size = arg1 * users.size();
        kweets = new ArrayList<>(size);
        for (User u : users) {
            for (int i = 1; i < arg1 + 1; i++) {
                kweets.add(
                        new Kweet(
                                u,
                                String.format("Hello I am {0} and this is my kweet nr {1}", u.getUsername(), i)
                        )
                );
            }
        }
    }

    @Then("^everyone is added to eachothers followers lists$")
    public void everyone_is_added_to_eachothers_followers_lists() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        for (User u : users) {
            int expected = users.size() - 1;
            int actualFollowers = u.getFollowedByUsers().size();
            int actualFollowed = u.getFollowingOtherUsers().size();
            Assert.assertEquals(expected, actualFollowers);
            Assert.assertEquals(expected, actualFollowed);
        }
    }

    @Then("^everyone should have (\\d+) followers$")
    public void everyone_should_have_followers(int arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        int expected = arg1;
        for (User u : users) {
            int actualFollowers = u.getFollowedByUsers().size();
            int actualFollowed = u.getFollowingOtherUsers().size();
            Assert.assertEquals(expected, actualFollowers);
            Assert.assertEquals(expected, actualFollowed);
        }
    }

    @Then("^there should be (\\d+) tweets in total$")
    public void there_should_be_tweets_in_total(int arg1) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        int expected = arg1;
        int actual = kweets.size();
        Assert.assertEquals(expected, actual);
    }

}
