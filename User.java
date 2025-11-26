/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
/**
*This class creates a user
*/
public class User {
   /**
   *username
   */
   private String user;
   /**
   *password
   */
   private String password;
   /**
   *list of user reviews
   */
   private ArrayList<Rating> reviews;
   /**
   *creates a user
   *@param username username
   *@param password password
   */
   public User(String username, String password) {
      this.reviews = new ArrayList<Rating>();
      this.user = username;
      this.password = password;
      if (UserFile.userExists(user)) {
         reviews = UserFile.getUserReviews(user);
      }
   }
   /**
   *gets username
   *@return username
   */
   public String getUser() {
      return user;
   }
   /**
   *gets password
   *@return password
   */
   public String getPassword() {
      return password;
   }
   /**
   *gets user reviews list
   *@return user reviews list
   */
   public ArrayList<Rating> getReviews() {
      return reviews;
   }
   /**
   *adds review to user reviews list
   *@param r user review
   */
   public void addReview(Rating r) {
      this.reviews.add(r);
      UserFile.saveToUserReviewsFile(r, user);
   }
   /**
   *creates string of all user reviews
   *@return string of all user reviews
   */
   public String getReviewsString() {
      String outString = "";
      for (Rating r : this.getReviews()) {
         outString += r.toStringUserDisplay() + "\n";
      }
      return outString;
   }
}