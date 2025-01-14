import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class WeatherPatterns {


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

    private static int calcLongestRun(int startPos, int[] temperatures) {
        if((startPos < 0) || (startPos > temperatures.length)){
            return 0;
        }
        int currentTemp = temperatures[startPos];
        int mRun = 0;
        for(int i = startPos + 1; i < temperatures.length; i++){
            if(temperatures[i] > currentTemp){
                mRun++;
                currentTemp = temperatures[i];
            }
        }
        return mRun;
    }
}
