
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Poppy Meng
 * @version 2/17/2022
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsFile){
        FirstRatings fr=new FirstRatings();
        myRaters=fr.loadRaters(ratingsFile);   
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
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ar=new ArrayList<>();
        for (String id: movieIDs){
             double idRating=getAverageByID(id ,minimalRaters);
             if( idRating !=0.0){
                 Rating rating=new Rating(id, idRating);
                 ar.add(rating);
             }
        }
        return ar;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter f){
        ArrayList<String> ids=MovieDatabase.filterBy(f);
        ArrayList<Rating> res=new ArrayList<>();
        for (String id:ids){
           double r=getAverageByID(id, minimalRaters);
           if (r!=0){
               res.add(new Rating(id,r));
           }
                
        }
        return res;   
    }
    
}




