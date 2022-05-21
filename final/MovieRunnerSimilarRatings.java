
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;

/**
 * Write a description of M​ovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printSimilarRatings(){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       String raterID = "65";
       int minimalRaters = 5;
       int numSimilarRaters = 20;
       ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
       Collections.sort(ratings, Collections.reverseOrder());
       System.out.println("Movies recommended: ");
       for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ", similarity score: " + rating.getValue());
       }
      
    }
    public void printSimilarRatingsByGenre(){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       Filter f=new GenreFilter("Action");
       
       String raterID = "65";
       int minimalRaters = 5;
       int numSimilarRaters = 20;
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, f);
       Collections.sort(ratings, Collections.reverseOrder());
       System.out.println("Movies recommended: ");
       for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ", similarity score: " + rating.getValue());
            System.out.println("Genre: "+MovieDatabase.getGenres(rating.getItem()));
       }
    }
    public void printSimilarRatingsByDirector(){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       Filter f=new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
       
       String raterID = "1034";
       int minimalRaters = 3;
       int numSimilarRaters = 10;
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, f);
       Collections.sort(ratings, Collections.reverseOrder());
       System.out.println("Movies recommended: ");
       for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ", similarity score: " + rating.getValue());
            System.out.println("Directors: "+MovieDatabase.getDirector(rating.getItem()));
       }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       
       AllFilters f=new AllFilters();
       f.addFilter(new GenreFilter("Adventure"));
       f.addFilter(new MinutesFilter(100, 200));
       String raterID = "65";
       int minimalRaters = 5;
       int numSimilarRaters = 10;
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, f);
       Collections.sort(ratings, Collections.reverseOrder());
       System.out.println("Movies recommended: ");
       for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ", similarity score: " + rating.getValue());
            System.out.println("Genres: "+MovieDatabase.getGenres(rating.getItem()));
            System.out.println("Movie length: "+MovieDatabase.getMinutes(rating.getItem())+" minutes");
       
       }
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       
       AllFilters f=new AllFilters();
       f.addFilter(new YearAfterFilter(2000));
       f.addFilter(new MinutesFilter(80, 100));
       String raterID = "65";
       int minimalRaters = 5;
       int numSimilarRaters = 10;
       ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, f);
       Collections.sort(ratings, Collections.reverseOrder());
       System.out.println("Movies recommended: ");
       for(Rating rating : ratings) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ", similarity score: " + rating.getValue());
            System.out.println("Year: "+MovieDatabase.getYear(rating.getItem()));
            System.out.println("Movie length: "+MovieDatabase.getMinutes(rating.getItem())+" minutes");
       }
    }
    
}
