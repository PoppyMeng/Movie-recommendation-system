
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String movieFile, String ratingsFile){
        FirstRatings fr=new FirstRatings();
        myMovies=fr.loadMovies(movieFile);
        myRaters=fr.loadRaters(ratingsFile);
          
    }
    public int getMovieSize(){
        return myMovies.size();
        
    }
    public int getRaterSize(){
        return myRaters.size();
    }
    public double getAverageByID(String movieId, int minimalRaters){//a movie average rating if it has min raters
        double total=0.0;
        int count=0;
       
        for (Rater r:myRaters){
            if (r.hasRating(movieId)){
                count++;
                total+=r.getRating(movieId);
            }
        }
        if (count<minimalRaters){
            return 0.0;
        }
        return total/count;  
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){//average rating for every single movie if they have minimal raters
        
        ArrayList<Rating> ar=new ArrayList<>();
        for (Movie movie: myMovies){
             double idRating=getAverageByID(movie.getID() ,minimalRaters);
             if( idRating !=0.0){
                 Rating rating=new Rating(movie.getID(), idRating);
                 ar.add(rating);
             }
        }
        return ar;
    }
    public String getTitle(String movieID){
        for (Movie movie: myMovies){
            if (movie.getID().equals(movieID)){
                return movie.getTitle();
            }
        }
        return ("Movie ID not found!");
    }
    public String getID(String movieTitle){
       for (Movie movie:myMovies){
           if (movie.getTitle().equals(movieTitle)) {
               return movie.getID();
           }
       }
       return ("NO SUCH TITLE");
    }
    
}


















