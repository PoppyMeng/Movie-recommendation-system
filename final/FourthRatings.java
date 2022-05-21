import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    public FourthRatings() {
        //this("ratings.csv");
    }
    
    public FourthRatings(String ratingsFile){
        RaterDatabase.addRatings(ratingsFile);   
    }
    public int getRaterSize() {
        return  RaterDatabase.getRaters().size();
    
    }
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> raterList = new ArrayList<>();
        double sum = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                raterList.add(rater);
                sum += rater.getRating(id);
            }
        }
        if(raterList.size() < minimalRaters)
            return 0.0;
        return sum / raterList.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> movieWithAvgRating = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                movieWithAvgRating.add(rating);
            }
        }
        return movieWithAvgRating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieWithAvgRating = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : movies) {
            double avgRating = getAverageByID(movieID, minimalRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                movieWithAvgRating.add(rating);
            }
        }
        return movieWithAvgRating;
    }
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> myMovieID = me.getItemsRated();
        double dotP = 0.0;
        for(String movieID : myMovieID) {
            double myRating = me.getRating(movieID);
            double otherRating = r.getRating(movieID);
            if(myRating != -1 && otherRating != -1) {
                dotP += (myRating - 5) * (otherRating - 5);
            }
        }
        return dotP;
    }
    
  
    private ArrayList<Rating> getSimilarities(String raterID){
        //find similarity of given raterID and all rater in database, put all into Rater and in a list
        ArrayList<Rater> raters=RaterDatabase.getRaters();
        ArrayList<Rating> res=new ArrayList<>();
        
        for (Rater rater: raters){
            if( !rater.getID().equals(raterID)){
                double similarity=dotProduct(RaterDatabase.getRater(raterID), rater);
                if (similarity>0){
                    res.add(new Rating (rater.getID(),similarity));
                }
            }
        }
        Collections.sort(res, Collections.reverseOrder());
     
        return res;
    }
    public ArrayList<Rating> getSimilarRatings(String rid, int num, int minimalRaters){
        ArrayList<Rating> res = new ArrayList<>();
        HashMap<String, ArrayList<Double>> movieToRater = new HashMap<>();
        for(int i = 0; i < num; i++) {
            Rating rating = getSimilarities(rid).get(i);
            String raterID = rating.getItem();
            double similarity = rating.getValue();
            //fliter by rating value of simlilar raters
            for(String movieID : MovieDatabase.filterBy(new TrueFilter())) {
                if(RaterDatabase.getRater(raterID).hasRating(movieID)) {
                    ArrayList<Double> al = movieToRater.getOrDefault(movieID, new ArrayList<>());
                    double weightedVal = RaterDatabase.getRater(raterID).getRating(movieID) * similarity;
                    al.add(weightedVal);
                    movieToRater.put(movieID, al);
                }
            }
        }
        for(String movieID: movieToRater.keySet()) {//me is movie ID
            if(movieToRater.get(movieID).size() >= minimalRaters) {
                double sum = 0.0;
                for(double weight : movieToRater.get(movieID)) {
                    sum += weight;
                }
                double s=sum/movieToRater.get(movieID).size();
                Rating rating = new Rating(movieID, s);
                res.add(rating);
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String rid, int num, int minimalRaters, Filter f) {
        ArrayList<Rating> res = new ArrayList<>();
        HashMap<String, ArrayList<Double>> movieToRater = new HashMap<>();
        for(int i = 0; i < num; i++) {
                Rating rating = getSimilarities(rid).get(i);
                String raterID = rating.getItem();
                double similarity = rating.getValue();
                //fliter by rating value of simlilar raters
                for(String movieID : MovieDatabase.filterBy(f)) {
                    if(RaterDatabase.getRater(raterID).hasRating(movieID) ) {
                        ArrayList<Double> al = movieToRater.getOrDefault(movieID, new ArrayList<>());
                        double weightedVal = RaterDatabase.getRater(raterID).getRating(movieID) * similarity;
                        al.add(weightedVal);
                        movieToRater.put(movieID, al);
                    }
                }
            }
        for(String movieID: movieToRater.keySet()) {//me is movie ID
            if(movieToRater.get(movieID).size() >= minimalRaters) {
                double sum = 0.0;
                for(double weight : movieToRater.get(movieID)) {
                    sum += weight;
                }
                double s=sum/movieToRater.get(movieID).size();
                Rating rating = new Rating(movieID, s);
                res.add(rating);
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
}




