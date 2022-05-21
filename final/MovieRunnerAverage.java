/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr=new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        int movieNum=sr.getMovieSize();
        int raterNum=sr.getRaterSize();
        System.out.println("There are "+movieNum +" movies and "+raterNum +" raters");
        
        List<Rating> ar=sr.getAverageRatings(2);
        Collections.sort(ar);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ar){
            System.out.println(r.getValue()+" " + sr.getTitle(r.getItem()));
        }
        
    }
    public void getAverageRatingOneMovie(){
        SecondRatings sr=new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        ArrayList<Rating> ar=sr.getAverageRatings(2);
        String title="The Godfather";
        /*
        for (Rating r:ar){
            if (sr.getTitle(r.getItem()).equals(title)){
                System.out.print("The average rating of "+title+" is: "+r.getValue());  
            }
        }
        */
        String id=sr.getID(title);
        System.out.println( "The average rating of "+title+" is: "+ sr.getAverageByID(id, 2));
    }
    
    
    

}












