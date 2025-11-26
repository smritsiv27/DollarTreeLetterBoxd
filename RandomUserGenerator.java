/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import java.util.*;
/**
*This class generates a random username for API reviews
*/
public class RandomUserGenerator {
   /**
   *assortment of random adjectives
   */
   private String[] adjectives;
   /**
   *assortment of random nouns
   */
   private String[] nouns;
   /**
   *num of elements in noun and adjective arrays
   */
   private final int LENGTH = 20;
   /**
   *sets up the assorment of adjectives and nouns
   */
   public RandomUserGenerator() {
      this.adjectives = new String[] {"grubby", "animated", "merciful", 
                                      "spurious", "aloof", "slippery", 
                                      "panicky", "illustrious", "elfin", 
                                      "exuberant", "punctual", "imported", 
                                      "well-to-do", "cowardly", "unsightly", 
                                      "hissing", "scintillating", "waggish",
                                      "odd", "hunky-dory"};
   
      this.nouns = new String[] {"Employee", "Society", "Guitar", 
                                 "Insurance", "Bird", "Driver",
                                 "Internet", "Event", "Youth",
                                 "Property", "Impression", "Bath",
                                 "Woman", "Surgery", "Departure",
                                 "Area", "Cousin", "Exam",
                                 "Queen", "Police"};
   }
   /**
   *gets random name
   *@return random name
   */
   public String getRandName() {
      Random rand = new Random();
      String randName = this.adjectives[rand.nextInt(LENGTH)] + this.nouns[rand.nextInt(LENGTH)];
      return randName;
   }
}