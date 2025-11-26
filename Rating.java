/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
import java.text.*;
import java.time.*;
/**
*This class creates and gets details about a review
*/
public class Rating {
   /**
   *the format of the date in the reviews
   */
   private final DateFormat NEW_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
   /**
   *name of the user
   */
   private String username;
   /**
   *ID of movie
   */
   private int movieID;
   /**
   *the date written
   */
   private String date;
   /**
   *star rating
   */
   private int stars;
   /**
   *review text
   */
   private String review;
   /**
   *constructs review from API data
   *@param username name of user
   *@param stars star rating
   *@param review review text
   *@param dateString date the review was written
   */
   public Rating(String username, int stars, String review, String dateString) {
      try {
         this.username = username;
         this.stars = stars;
         this.review = review;
         DateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
         this.date = NEW_FORMAT.format(oldFormat.parse(dateString));
      }
      catch (ParseException e) {
         System.out.println("Unable to parse date string. Using current date instead");
         this.date = NEW_FORMAT.format(new Date());
      }
   }
   /**
   *constructs review made by user
   *@param username name of user
   *@param movieID ID of movie
   *@param stars star rating
   *@param review review text
   */
   public Rating(String username, int movieID, int stars, String review) {
      this.username = username;
      this.movieID = movieID;
      this.stars = stars;
      this.review = review;
      this.date = NEW_FORMAT.format(new Date());
   }
   /**
   *constructs review form user review save file
   *@param username name of user
   *@param movieID ID of movie
   *@param stars star rating
   *@param review review text
   *@param dateString date review was written
   */
   public Rating (String username, String movieID, String stars, String review, String dateString) {
      this.username = username;
      this.movieID = Integer.parseInt(movieID);
      this.stars = Integer.parseInt(stars);
      this.review = review;
      this.date = dateString;
   }
   /**
   *gets star rating
   *@return star rating
   */
   public int getStars() {
      return stars;
   }
   /**
   *gets name of user
   *@return name of user
   */
   public String getUsername() {
      return username;
   }
   /**
   *gets ID of movie
   *@return ID of movie
   */
   public int getMovieID() {
      return movieID;
   }
   /**
   *gets name of movie
   *@return name of movie
   */
   public String getMovieName() {
      return MoviesAPI.getMovieByID(movieID);
   }
   /**
   *gets date of review
   *@return date of review
   */
   public Date getDate() {
      Date d = null;
      try {
         d = NEW_FORMAT.parse(date);
      }
      catch (Exception e) {
      }
      return d;
   }
   /**
   *creates string displayed on user profile
   *@return string of user reviews
   */
   public String toStringUserDisplay() {
      String outString = "";
      outString += getMovieName() + "\n" + stars + " STARS\n" + date + "\n" + review + "\n";
      return outString;
   }
   /**
   *creates string displayed in movie reviews
   *@return string of reviews
   */
   public String toStringMovieDisplay() {
      String outString = "";
      outString += username + "\n" +  stars + " STARS\n" + date + "\n" + review + "\n";
      return outString;
   }
   /**
   *creates string of reviews to be written to save file
   *@return string with all review information
   */
   @Override
   public String toString() {
      String outString = "";
      outString += username + "," + movieID + "," + stars + "," + review + "," + date + "\n";
      return outString;
   }
}