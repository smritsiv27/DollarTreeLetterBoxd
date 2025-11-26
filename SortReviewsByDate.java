/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
/**
*This class sorts the movie reviews by their date
*/
public class SortReviewsByDate implements Comparator<Rating> {
   /**
   *compares the dates of reviews
   *@param a a review
   *@param b another review
   */
   //learnt how to do this from GeeksforGeeks
   //comparator compares the dates of each review
   //paired w/ Collections.sort, which uses this comparison to compare all reviews passed in
   public int compare(Rating a, Rating b) {
      return a.getDate().compareTo(b.getDate());
   }
}