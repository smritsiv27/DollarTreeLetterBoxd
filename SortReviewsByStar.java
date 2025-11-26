/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
/**
*This class sorts the movie reviews by their star rating
*/
class SortReviewsByStar implements Comparator<Rating> {
   /**
   *compares the star ratings of review
   *@param a review
   *@param b another review
   */
   //also learnt how to do this from GeeksforGeeks - similar use
   public int compare(Rating a, Rating b) {
      return a.getStars() - b.getStars();
   }
}
