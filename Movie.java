/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
import org.json.*;
import java.net.http.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.text.*;
/**
*This class creates a movie from API data and stores reviews
*/
public class Movie {
   /**
   *title of the movie
   */
   private String title;
   /**
   *poster of the movie
   */
   private URL poster;
   /**
   *description of the movie
   */
   private String desc;
   /**
   *ID of the movie
   */
   private int ID;
   /**
   *where the movie ratings are stored
   */
   private ArrayList<Rating> reviews;
   /**
   *constructs movie from API data
   *@param id movie ID
   *@param title movie title
   *@param desc movie description
   *@param poster movie poster
   */
   public Movie(int id, String title, String desc, URL poster) {
      this.reviews = new ArrayList<Rating>();
      this.ID = id;
      this.title = title;
      this.desc = desc;
      this.poster = poster;
   }
   /**
   *calculates average rating of movie from user reviews
   *@param username name of the user
   *@return average rating
   */
   public String getAvgRating(String username) {
      double total = 0.0;
      int count = 0;
      for (Rating r : this.getReviews(username)) {
         count++;
         total += r.getStars();
      }
      DecimalFormat value = new DecimalFormat("0.0");  
      if (count > 0) {
         return value.format(total / count);
      }
      else {
         return "0.0";
      }
   }
   /**
   *gets movie ID
   *@return ID
   */
   public int getID() {
      return ID;
   }
   /**
   *gets movie title
   *@return title
   */
   public String getTitle() {
      return title;
   }
   /**
   *gets movie description
   *@return desrcription
   */
   public String getDesc() {
      return desc;
   }
   /**
   *gets movie poster
   *@return poster
   */
   public URL getPoster() {
      return poster;
   }
   /**
   *gets reviews of the movie
   *@param username name of the user
   *@return list of reviews
   */
   public ArrayList<Rating> getReviews(String username) {
      if (reviews.size() == 0) {
         MoviesAPI.initializeMovieReviews(this);
         Rating r = UserFile.findUserReview(username, this.ID);
         if (r != null) {
            reviews.add(r);
         }
      }
      return reviews;
   }
   /**
   *adds a user review to the movie reviews list
   *@param r the user review
   */
   public void addReview(Rating r) {
      this.reviews.add(r);
   }
   /**
   *creates and formats string of movie reviews
   *@param username name of the user
   *@return the string of movie reviews 
   */
   public String getReviewsString(String username) {
      String outString = "";
      for (Rating r : this.getReviews(username)) {
         outString += r.toStringMovieDisplay() + "\n";
      }
      return outString;
   }
}