/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
/**
*This class creates a list of movies
*/
public class MovieList {
   /**
   *the list of movies
   */
   private ArrayList<Movie> movieList;
   /**
   *creates movie list from API data
   *@param query user search
   */
   public MovieList(String query) {
      this.movieList = new ArrayList<Movie>();
      MoviesAPI.initializeSearch(query, this);
   }
   /**
   *gets the movie list
   *@return movie list
   */
   public ArrayList<Movie> getMovieList() {
      return movieList;
   }
   /**
   *adds movie to the list
   *@param m movie to be added
   */
   public void addMovie(Movie m) {
      movieList.add(m);
   }
   /**
   *gets the movie from the movie ID
   *@param id movie ID
   *@return movie
   */
   public Movie getMovieByID(int id) {
      for (Movie m : movieList) {
         if (m.getID() == id) {
            return m;
         }
      }
      return null;
   }
}