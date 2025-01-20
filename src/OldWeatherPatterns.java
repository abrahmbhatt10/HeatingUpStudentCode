import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class OldWeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        int longestRun = 0;
        int currentRun = 0;
        for(int i = 0; i < temperatures.length; i++){
            currentRun = calcLongestRun(i, temperatures);
            if(currentRun > longestRun){
                longestRun = currentRun;
            }
        }
        return longestRun + 1;
    }

    /*
    The starting point is fixed in the array.
    The function returns the longest run for that starting point.
     */
    private static int calcLongestRun(int startPos, int[] temperatures) {
        if((startPos < 0) || (startPos > temperatures.length)){
            return 0;
        }
        int mRun = 0;
        int previousPos = startPos;
        int currentPos = startPos;
        for(int i = startPos+1; i < temperatures.length; i++){
            if(temperatures[i] > temperatures[currentPos]){
                if((i - currentPos) <= 2){
                    mRun++;
                }
                else{
                    mRun += calcSubLongestRuns(previousPos, temperatures, currentPos, i);
                }
                previousPos = currentPos;
                currentPos = i;
            }
        }
        return mRun;
    }

    /*
        Receives a sub array that is skipped after a dip.
        Figures out whether the dip interval has longer run.
     */
    private static int calcSubLongestRuns(int startPos, int[] temperatures, int currentPos, int endPos){
        int sRun = 1;

        if((endPos - currentPos) < 3){
            return sRun;
        }
        int sPos = startPos;
        int pPos = startPos;
        for(int i = currentPos+1; i < endPos; i++){
            if(temperatures[i] > temperatures[sPos]){
                if((i - sPos) <= 2){
                    sRun++;
                }
                else{
                    sRun += calcSubLongestRuns(pPos, temperatures, sPos, i);
                }
                pPos = sPos;
                sPos = i;
            }
        }
        return sRun;
    }
}
