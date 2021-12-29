package designs.facebook;

import designs.facebook.entities.Post;
import designs.facebook.entities.User;
import designs.facebook.services.NewsFeed;
import designs.facebook.services.UserManager;

public class FlipProj {

    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        User luscious = userManager.signUpUser("Luscious");
        User albus = userManager.signUpUser("Albus");
        User tom = userManager.signUpUser("Tom");

        /* SCENARIO 1 - NewUserLogin Welcome Msg */
        //First User Login with no Posts
        System.out.println("\n\n==>SCENARIO 1 :  Seeing Luscious's News Feed\n");
        String newsFeed = userManager.loginUser(luscious.getUserId());
        System.out.println(newsFeed);

        //Luscious Add a post
        Post luscious_post_1 = NewsFeed.addAPost(luscious, "Hi I am Luscious");
        Post luscious_post_2 = NewsFeed.addAPost(luscious,"I am lord Voldemort btw 3:)");

        //login another user tom and add posts from tom
        System.out.println("\n\n==> Tom signsup\n");
        newsFeed = userManager.loginUser(tom.getUserId());
        System.out.println(newsFeed);
        Post tom_post_1 = NewsFeed.addAPost(tom, "Hi I am Tom");


        /* SCENARIO 2 - Users sees the message of people he hasn't followed in terms of recency */
        //Second User Login with 2 Posts from unfollowed luscious
        newsFeed = userManager.loginUser(albus.getUserId());
        System.out.println("\n\n==>SCENARIO 2 :  Seeing Albus's News Feed Before following anyone\n");
        System.out.println(newsFeed);




        /* SCENARIO 3 - Users sees the messages of people he follows first */
        //Albus Follows Luscious
        luscious.addFollower(albus);
        System.out.println("\n\n==>SCENARIO 3 : Seeing Albus's News Feed After following Luscious\n");
        System.out.println(NewsFeed.showNewsFeed(albus));


        /* SCENARIO 4 - Albus Likes Luscious oldest post first and then it starts showing up first */
        NewsFeed.upVote(albus,luscious_post_1);
        System.out.println("\n\n==>SCENARIO 4 : Seeing Albus's News Feed After Liking Old Luscious Post\n");
        System.out.println(NewsFeed.showNewsFeed(albus));



        /* SCENARIO 5 - Albus Likes Luscious recent, second post and comments on it so it should show up first */
        System.out.println("\n\n==>SCENARIO 5 : Seeing Albus's News Feed After Liking Recent Luscious Post and Commenting on it\n");
        //upvoting
        //NewsFeed.upVote(albus,luscious_post_2);
        //Commenting
        NewsFeed.addComment(albus,luscious_post_2,"Hey I am scared lol!");
        System.out.println(NewsFeed.showNewsFeed(albus));


        /* SCENARIO 6 - Albus comments on luscious first post also, so now the most recent one post2  should show up first still */
        System.out.println("\n\n==>SCENARIO 6 : Seeing Albus's News Feed After Commenting on Luscious first post as well\n");
        //Commenting
        NewsFeed.addComment(albus,luscious_post_1,"Hey Luscious");
        System.out.println(NewsFeed.showNewsFeed(albus));


        /* SCENARIO 7 - Albus starts following tom also */
        System.out.println("\n\n==>SCENARIO 7 : Albus starts following Tom\n");
        //Commenting
        tom.addFollower(albus);
        System.out.println(NewsFeed.showNewsFeed(albus));

        /* SCENARIO  8 - downvote Luscious first post*/
        System.out.println("\n\n==>SCENARIO  8 - downVote Luscious first post\n");
        //Commenting
        NewsFeed.downVote(tom,luscious_post_1);
        System.out.println(NewsFeed.showNewsFeed(albus));

        /* SCENARIO  9 - adding comment to Tom's post*/
        System.out.println("\n\n==>SCENARIO  9 - adding comment to Tom's post\n");
        //Commenting
        NewsFeed.addComment(albus,tom_post_1,"Hey tom!");
        System.out.println(NewsFeed.showNewsFeed(albus));

    }
}
