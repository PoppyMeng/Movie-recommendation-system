
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings; 
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    public void addRating(String movieID, double rating) {
        
        Rating r=new Rating(movieID,rating);
        myRatings.put(movieID, r);
    }

    public boolean hasRating(String movieID) {
            return myRatings.containsKey(movieID);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String movieID) {
        if (hasRating(movieID)){
                return myRatings.get(movieID).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();
       for(String movieID : myRatings.keySet()){
            list.add(movieID);
        }
        
        return list;
    }
}
