import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> sourceMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> res = new ArrayList<>();
        Random rd = new Random();
        for(int i = 0; i < 10; i++) {
            res.add(sourceMovies.get(rd.nextInt(sourceMovies.size())));
        }
        return res; 
    }
    public void printRecommendationsFor(String webRaterID){
       FourthRatings fr=new FourthRatings("data/ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       
       int minimalRaters = 3;
       int numSimilarMovies = 5;
       ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, numSimilarMovies, minimalRaters);
       System.out.println("<style>*body{font-family: sans-serif;}#myTable{padding: 20px 600px; font-size: 15px; text-align: center; border: 2px solid #696969; margin: margin; border-radius: 25px;}img{height: 50%}</style>");
       if(similarRatings.size() == 0){
           System.out.println("<style>#header{height: 150px;}</style>");
           System.out.println("<div id=\"header\"><h2>Sorry! No movies are available to recommend at this time!</h2></div>");
           return;
       }
       //ArrayList<String> movieToBeRate = getItemsToRate();
       ArrayList<Rating> outID = new ArrayList<>();
       int displayNum = Math.min(5, similarRatings.size());   
       System.out.println("<h1>What to watch - The most similar top movies based on your rating</h1><p1><table id=\"myTable\"><tr><th>Poster</th><th>\t</th><th>Title</th><th>\t</th><th>Genre</th><th>\t</th><th>Year</th><th>\t</th><th>Time(Minute)</th></tr>");       
       
       for(int i=0; i< displayNum; i++) {
            String id = similarRatings.get(i).getItem();
            
            if(RaterDatabase.getRater(webRaterID).hasRating(id)){//avoid rated movies
                continue;
            }
            outID.add( similarRatings.get(i));
            
            String poster = "http://www.dukelearntoprogram.com/capstone/data/" + MovieDatabase.getPoster(id).substring(7);
            //archive 3
            System.out.println("<td><img src=");
            System.out.println("\"" + poster + "\"");
            System.out.println("/> </td>");
           
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getTitle(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getGenres(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getYear(id) + "</td>");
            System.out.println("<td>\t</td>");
            System.out.println("<td>" + MovieDatabase.getMinutes(id) + "</td></tr>");
            
           /* archive version
           System.out.println(               
                "<td><img src = \"" + "http://www.dukelearntoprogram.com/capstone/data/" + MovieDatabase.getPoster(id).substring(7) + "\" width=\"50\" height=\"70\"></td> " +
                "<td>" + MovieDatabase.getYear(id) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
               id + "\">" + MovieDatabase.getTitle(id) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                + String.format("%.1f", similarRatings.get(i).getValue()) + "/10</td>" +
                "<td>" + MovieDatabase.getGenres(id) + "</td>" +
                "<td>" + MovieDatabase.getCountry(id) + "</td>" +
                "</tr> ");
                */
           /*    
           System.out.println( //archive 2          
                "<td><img src = \"" + "http://www.dukelearntoprogram.com/capstone/data/" + MovieDatabase.getPoster(id).substring(7) + "\" width=\"50\" height=\"70\"></td> " +
                "<td>" + MovieDatabase.getYear(id) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
               id + "\">" + MovieDatabase.getTitle(id) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                + String.format("%.1f", similarRatings.get(i).getValue()) + "/10</td>" +
                "<td>" + MovieDatabase.getGenres(id) + "</td>" +
                "<td>" + MovieDatabase.getCountry(id) + "</td>" +
                "</tr> ");
            */    
           
       }
       System.out.println("</table>");
       System.out.println("</p1>");
       System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>");
    
    }    
    
}
