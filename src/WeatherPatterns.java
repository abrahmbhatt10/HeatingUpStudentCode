
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
        // This temperature can be anywhere from -50 to 130.
        int[] vertexLenArr = new int[181];
        for(int i = 0; i < vertexLenArr.length; i++){
            vertexLenArr[i] = 0;
        }
        int vertexPos = 0;
        int longestPath = 0;
        for(int i = 0; i < temperatures.length; i++){
            vertexPos = posTempToIndex(temperatures[i]);
            vertexLenArr[vertexPos] = longestPathTo(i, temperatures, vertexLenArr);
            if(vertexLenArr[vertexPos] > longestPath){
                longestPath = vertexLenArr[vertexPos];
            }
        }
        return longestPath;
    }
    /*
        This function below converts the temperature value from the temperature's array
        to the equivalent position in the vertex length array.
     */
    public static int posTempToIndex(int currentTemp){
        // -50 is vertex index 0
        // 130 is vertex index 180
        return currentTemp+50;
    }

    /*
        Below pseudocode taken from Mr. Blick's slides:
        LongestPathTo(Vertex V):
	    len = 0;
        For each vertex V' leading to V:
		    len = Max( len,  LongestPathTo(V'))
         return 1 + len;
     */
    public static int longestPathTo(int currentPos, int[] temperatures, int[] vertexLenArr){
        int len = 0;
        if(currentPos == 0){
            return 1 + len;
        }
        int currentLen = 0;
        int vertexPos = 0;
        for(int i = 0; i < currentPos; i++){
            if(temperatures[currentPos] > temperatures[i]) {
                vertexPos = posTempToIndex(temperatures[i]);
                if (vertexLenArr[vertexPos] == 0) {
                    //Path not calculated yet
                    vertexLenArr[vertexPos] = maxLongestPathForSub(i, temperatures, vertexLenArr);
                }
                if (len < vertexLenArr[vertexPos]) {
                    len = vertexLenArr[vertexPos];
                }
            }
        }
        return 1 + len;
    }

    public static int maxLongestPathForSub(int currentPos, int[] temperatures, int[] vertexLenArr){
        int[] subLenArr = new int[currentPos];
        /*
            Sets all values in subLenArr to be 0;
        */
        if(currentPos == 0)
        {
            return 1;
        }
        for(int i = 0; i < currentPos; i++){
            subLenArr[i] = 0;
        }
        int vertexPos = 0;
        for(int i = 0; i < currentPos; i++){
            if(temperatures[currentPos] > temperatures[i]){
                vertexPos = posTempToIndex(temperatures[i]);
                subLenArr[i] = vertexLenArr[vertexPos];
                subLenArr[i] += 1;
            }
        }
        int maxPath = 0;
        for(int i = 0; i < currentPos; i++){
            if(maxPath < subLenArr[i]){
                maxPath = subLenArr[i];
            }
        }
        return maxPath;
    }
}
