import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;




public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies=new ArrayList<>();
    
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {
            String id = i.get("id");
            String title = i.get("title");
            String year = i.get("year");
            String country = i.get("country");
            String genre = i.get("genre");
            String director = i.get("director");
            int minutes = Integer.parseInt(i.get("minutes"));
            String poster = i.get("poster");
            //double rating=Double.parseDouble(i.get("rating"));
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }
    public void testLoadMovies() {
        
        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
        //for (Movie movie : movies) {
           // System.out.println(movie);
       // }
        System.out.println("The size of movie list is = " + movies.size());
        int c1=0;
        int c2=0;
        for( Movie movie: movies){
            if (movie.getGenres().contains("Comedy")){
                c1++;
            }
            if (movie.getMinutes()>150){
                c2++;
            }
        System.out.println(c1);
        System.out.println(c2);
    
        }
        Map<String, Integer> map=new HashMap<>();
        int maxNum=0;  
        for (Movie m:movies){
            String dirs=m.getDirector();
            String [] dirList=dirs.split(", ");
            for (String dir: dirList){
                if (!map.containsKey(dir)){
                    map.put(dir, 1);
                }else{
                    map.put(dir, 1+map.get(dir));
                 
                }
                if(map.get(dir)>maxNum){
                    maxNum=map.get(dir);
                }
            }
            
        }
        System.out.println("the maximum number of movies by any director is "+maxNum);
        System.out.println("Below are the director names that directed " +maxNum +" movies");
    
        for (String dir:map.keySet()){
            if(map.get(dir)==maxNum){
                System.out.println(dir);
            }
        }
        
    }
    public ArrayList<Rater> loadRaters (String fileName){
        
        ArrayList<Rater> raterList=new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for ( CSVRecord record: parser){
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            double rating=Double.parseDouble(record.get("rating"));
            boolean flag=false;
            for (Rater r: raterList){
                if (r.getID().equals(rater_id)){
                    r.addRating(movie_id, rating);
                    flag=true;
                    break;
                }
            }
            if (flag==false){
                Rater n=new Rater(rater_id);
                n.addRating(movie_id, rating);
                raterList.add(n);
            }
            
        }
                
        return raterList;
    }
    public void testLoadRaters (){
         ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
         System.out.println("Total number of raters is: "+ raters.size());
         int max=0;
         int count=0;
         ArrayList<String> names=new ArrayList<>();
         String particular_movie= "1798709";
         HashSet<String> movies=new HashSet<>();
         for (Rater r: raters){
             /*
             System.out.println(r.getID() +" has " + r.numRatings()+ " ratings");
             
             ArrayList<String> ids=r.getItemsRated();
             
             for (String id:ids){
                 System.out.println("Movie id: " +id);
                 System.out.println("rating " + r.getRating(id));
             }
             */
            //2 blue
            String particular_id="2";
            if (r.getID().equals(particular_id)){
                System.out.println(particular_id+" has "+ r.numRatings()+" ratings");
            }
            if( r.numRatings()>max){
                max=r.numRatings();
                
                names=new ArrayList<>();
                names.add(r.getID());
            }else if (r.numRatings()==max){
                
                names.add(r.getID());
            }
            //4 red
            ArrayList<String> movieIDs=r.getItemsRated();
             
            if (movieIDs.contains(particular_movie)){
                count++;
            }  
            //5 yellow
            movieIDs.forEach(e->movies.add(e));
            
        }
        //3 purple
        System.out.println(names.size() +" rater(s) have the maximun rating number of ratings among all the raters, which is "
            +max + " ratings");
        System.out.println("The user(s) are: ");
        names.forEach(System.out::println);
        System.out.println(particular_movie+ " is rated by " +count+" raters");
       System.out.println("There are " +movies.size() +" movies in total");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}