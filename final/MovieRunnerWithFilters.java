
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;
public class MovieRunnerWithFilters {
    public void printAverageRatingsByYearAfterAndGenre(){
        
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        AllFilters af=new AllFilters();
        af.addFilter(new YearAfterFilter(1980));
        af.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, af);
        System.out.println(+ratings.size() +" movie(s) matched");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" " + MovieDatabase.getYear(r.getItem())+ " "+ MovieDatabase.getTitle(r.getItem()) );
            System.out.println("    "+ MovieDatabase.getGenres(r.getItem()));
        } 
        
    }
    public void printAverageRatingsByDirectorsAndMinutes(){
        
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        AllFilters af=new AllFilters();
        af.addFilter(new MinutesFilter(30,170));
        af.addFilter(new  DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze,Francis Ford Coppola"));
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, af);
        System.out.println(+ratings.size() +" movie(s) matched");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" Time: " + MovieDatabase.getMinutes(r.getItem())+ " "+ MovieDatabase.getTitle(r.getItem()) );
            System.out.println("    " +MovieDatabase.getDirector(r.getItem()));
        } 
        
    }
    
    
    public void printAverageRatings(){
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        ArrayList<Rating> ar=tr.getAverageRatings(1);
        System.out.println("found "+ar.size() +" movies");
        Collections.sort(ar);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ar){
            System.out.println( r.getValue()+" " + MovieDatabase.getTitle(r.getItem()) );
        }
        
    }
    public void printAverageRatingsByYear(){
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,new YearAfterFilter(2000));
        System.out.println("found "+ratings.size() +" movies");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" " + MovieDatabase.getYear(r.getItem())+ " "+ MovieDatabase.getTitle(r.getItem()) );
        } 
    }
    public void printAverageRatingsByGenre(){
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,new GenreFilter("Crime"));
        System.out.println("found "+ratings.size() +" movies");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()) );
            System.out.println("    " +MovieDatabase.getGenres(r.getItem()));
        } 
    }
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,new MinutesFilter(110,170));
        System.out.println("found "+ratings.size() +" movies");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" " + MovieDatabase.getTitle(r.getItem()) );
        } 
    }
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr=new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        int movieNum= MovieDatabase.size();
        System.out.println("Read data for " + movieNum +" movies");
        int raterNum= tr.getRaterSize();
        System.out.println("Read data for " + raterNum +" raters");
        
        ArrayList<Rating> ratings=tr.getAverageRatingsByFilter(1,new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));
        System.out.println("found "+ratings.size() +" movies");
        Collections.sort(ratings);
        //PriorityQueue <Rating> pq=new PriorityQueue<>((a,b)->a.getValue()-b.getValue());
        for (Rating r:ratings){
            System.out.println( r.getValue()+" " + MovieDatabase.getTitle(r.getItem()) );
            System.out.println("    " +MovieDatabase.getDirector(r.getItem()));
        } 
    }
}














