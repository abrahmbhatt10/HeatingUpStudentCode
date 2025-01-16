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
        int currentPos = startPos;
        for(int i = startPos + 1; i < temperatures.length; i++){
            if(temperatures[i] > currentTemp){
                if((i - currentPos) <= 2){
                    mRun++;
                }
                else{
                    mRun += calcSubLongestRuns(startPos, temperatures, currentPos, i);
                }
                currentTemp = temperatures[i];
                currentPos = i;
            }
        }
        return mRun;
    }

    private static int calcSubLongestRuns(int startPos, int[] temperatures, int currentPos, int endPos){
        if((endPos - currentPos) < 3){
            return 1;
        }
        int currentTemp = temperatures[startPos];
        int sRun = 0;
        int sPos = currentPos + 1;
        for(int i = currentPos + 1; i < endPos; i++){
            if(temperatures[i] > currentTemp){
                if((i - sPos) <= 2){
                    sRun++;
                }
                else{
                    sRun += calcSubLongestRuns(currentPos + 1, temperatures, sPos, i);
                }
                currentTemp = temperatures[i];
                sPos = i;
            }
        }
        if(sRun > 1){
            return sRun;
        }
        return 0;
    }
}
