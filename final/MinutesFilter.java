
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class MinutesFilter implements Filter{
    private int minMinute;
    private int maxMinute;
    
    public MinutesFilter(int min, int max) {
    	minMinute = min;
    	maxMinute=max;
    }
    
    @Override
    public boolean satisfies(String id) {
    	return (MovieDatabase.getMinutes(id)>= minMinute && MovieDatabase.getMinutes(id)<=maxMinute);
    }
}
