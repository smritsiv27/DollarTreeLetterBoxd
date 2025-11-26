/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.io.*;
import java.util.*;
/**
*This class creates and reads from the login and user reviews files saved within the system
*/
public class UserFile {
   /**
   *stores username (key) and password (value) as key-value pair for logins
   */
   private static HashMap<String, String> loginMap;
   /**
   *stores user ratings
   */
   private static ArrayList<Rating> userRatings = new ArrayList<Rating>();
   /**
   *finds user review in user ratings
   *@param username name of user
   *@param movieID ID of movie
   *@return the user review
   */
   public static Rating findUserReview(String username, int movieID) {
      for (Rating r : userRatings) {
         if (r.getUsername().equals(username) && r.getMovieID() == movieID) {
            return r;
         }
      }
      return null;
   }
   /**
   *gets all saved user reviews from file
   *@param username name of user
   *@return list of user reviews
   */
   public static ArrayList<Rating> getUserReviews(String username) {
      try {
         Scanner scan = new Scanner(new File(username + "_reviews.txt"));
         while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] reviewRecord = line.split(",");
            for (int i = 0; i < reviewRecord.length; i++) {
               reviewRecord[i] = reviewRecord[i].trim();
            }
            Rating r = new Rating (reviewRecord[0], reviewRecord[1], reviewRecord[2], reviewRecord[3], reviewRecord[4]);
            userRatings.add(r);
         }
      }
      catch (Exception e) {
      }
      return userRatings;
   }
   /**
   *saves user review to file
   *@param r review to be saved
   *@param username name of user
   */
   public static void saveToUserReviewsFile(Rating r, String username) {
      try {
         FileOutputStream fos = new FileOutputStream(username + "_reviews.txt", true);
         PrintStream fileStream = new PrintStream(fos);
         fileStream.print(r.toString());
      }
      catch (Exception e) {
      }
   }
   /**
   *saves user login details to file
   *@param username name of user
   *@param password password of user
   */
   public static void saveToUserFile(String username, String password) {
      try {
         PrintStream fileStream = new PrintStream(new FileOutputStream("logins.txt", true));
         fileStream.println(username + "|" + password);
      }
      catch (Exception e) {
         System.out.println("Unable to save login");
      }
   }
   /**
   *checks if a user exists
   *@param username name of user
   *@return true if user exists, false if not
   */
   public static boolean userExists(String username) {
      loginMap = new HashMap<String, String>();
      try {
         File f = new File("logins.txt");
         Scanner reader = new Scanner(f);
         while (reader.hasNextLine()) {
            if (reader.nextLine().split("\\|")[0].equals(username)) {
               return true;
            }
         }
      }
      catch (Exception e) {
         return false;
      }
      return false;
   }
   /**
   *checks if login matches a line in the login file
   *@param username username of user
   *@param password password of user
   *@return true if the login matches, false if it doesn't
   */
   public static boolean loginMatch(String username, String password) {
      try {
         Scanner scan = new Scanner(new File("logins.txt"));
         while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] userRecord = line.split("\\|");
            loginMap.put(userRecord[0], userRecord[1]);
         }
         for (String user : loginMap.keySet()) {
            if (user.equals(username)) {
               if (loginMap.get(user).equals(password)) {
                  return true;
               }
            }
         }
      }
      catch (Exception e) {
      }
      return false;
   }
}