/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.net.http.*;
import java.io.*;
import java.net.*;
import org.json.*;
/**
*This class gets information from the TMDb API
*/
public class MoviesAPI {
   /**
   *the API key
   */
   private static final String API_KEY = "3ff1d799bc9079fa02e608351bec6121";
   /**
   *searches for movies based off of user input
   *@param query the user search
   *@param ml the list of movies
   */
   public static void initializeSearch(String query, MovieList ml) {
      String url = "https://api.themoviedb.org/3/search/movie?query=" + query + "&api_key=" + API_KEY;
      String response = getQueryResponse(url);
      JSONObject myObj = new JSONObject(response);
      JSONArray results = myObj.getJSONArray("results");
      createMovies(ml, results);
   }
   /**
   *gets reviews of specified movie
   *@param m the movie
   */
   public static void initializeMovieReviews(Movie m) {
      String url = "https://api.themoviedb.org/3/movie/" + m.getID() + "/reviews?language=en-US&page=1&api_key=" + API_KEY;
      String response = getQueryResponse(url);
      JSONObject myObj = new JSONObject(response);
      JSONArray results = myObj.getJSONArray("results");
      createReviews(m, results);
   }
   /**
   *gets movie from ID of movie
   *@param movieID the movie ID
   *@return the title of the movie
   */
   public static String getMovieByID(int movieID) {
      String url = "https://api.themoviedb.org/3/movie/" + movieID + "?language=en-US&page=1&api_key=" + API_KEY;
      String response = getQueryResponse(url);
      JSONObject myObj = new JSONObject(response);
      String result = myObj.getString("title");
      return result;
   }
   /**
   *gets information from user search
   *@param stringUrl the url of search
   *@return the information
   */
   public static String getQueryResponse(String stringUrl) {
      String resp = null;
      BufferedReader in = null;
      try {
         URL url = new URL(stringUrl);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");   
         conn.setRequestProperty("Content-Type", "application/json");  
         resp = conn.getResponseMessage(); //200 = OK, 4xx their fault, 5xx your fault
         in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } catch(Exception e) {
         System.out.println(e);
      }
      String res = "";
      if(in != null) {
         String line = "";
         try {
            while((line = in.readLine()) != null) {
               res += line;
            }
         } catch (IOException e) {
            System.out.println(e);
         }
      }
      return res;
   }
   /**
   *creates reviews for movie and adds them to movie review list
   *@param m the specified movie
   *@param results information from API
   */
   private static void createReviews(Movie m, JSONArray results) {
      int numResults = results.length();
      String author = "";
      int stars = 0;
      String date = "";
      String movieReview = "";
      for (int i = 0; i < numResults; i++) {
         JSONObject result  = (JSONObject) results.get(i);
         JSONObject authorDetails = (JSONObject) result.get("author_details");
         RandomUserGenerator name = new RandomUserGenerator();
         author = name.getRandName();
         if (authorDetails.has("rating") && !authorDetails.isNull("rating")) {
            stars = authorDetails.getInt("rating") / 2;
         }
         else {
            continue;
         }
         if (result.has("content") && !result.isNull("content")) {
            movieReview = result.getString("content");
         }
         else {
            continue;
         }
         date = result.getString("created_at");
         Rating r = new Rating(author, stars, movieReview, date);
         m.addReview(r);
      }
   }
   /**
   *creates movies and adds them to movie list
   *@param ml the movie list
   *@param results information from API
   */
   private static void createMovies(MovieList ml, JSONArray results) {
      int numResults = results.length();
      int id = 0;
      String desc = "";
      String title = "";
      String posterLoc = "";
      for (int i = 0; i < numResults; i++) {
         JSONObject result  = (JSONObject) results.get(i);
         title = result.getString("title");
         id = result.getInt("id");
         if (result.has("overview") && !result.isNull("overview")) {
            desc = result.getString("overview");
         }
         else {
            continue;
         }
         if (result.has("poster_path") && !result.isNull("poster_path")) {
            posterLoc = result.getString("poster_path");
         }
         else {
            continue;
         }
         URL poster = getPosterURL(posterLoc);
         Movie m = new Movie(id, title, desc, poster);
         ml.addMovie(m);
      }
   }
   /**
   *gets url of poster
   *@param posterLoc location of poster
   *@return url of poster
   */
   private static URL getPosterURL(String posterLoc) {
      try {
         URL url = new URL("https://image.tmdb.org/t/p/original" + posterLoc);
         return url;
      }
      catch (Exception e) {
      }
      return null;
   }
}