
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class DirectorsFilter implements Filter{
    private String myDirector;
    
    public DirectorsFilter(String dir) {
    	myDirector = dir;
    }
    
    @Override
    public boolean satisfies(String id) {
        String [] dir=myDirector.split(",");
        for( String i:dir){
            if (MovieDatabase.getDirector(id).contains(i)) return true;
        }
    	return false;
    }
}
