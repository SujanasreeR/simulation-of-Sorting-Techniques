import java.util.Arrays;

/**
 * This Program uses Stdlib.jar library from https://introcs.cs.princeton.edu/java/stdlib/
 * licensed under GPLv3 to render visualizations
 * 
 * More details about the license can be found at: http://www.gnu.org/copyleft/gpl.html
 */
public class Sorting {

    private static int RUNS = 5;

    public static void main (String[] args){

        /*long startTime,finishTime;
        SortHelper sortHelper = new SortHelper();
        DataSetProvider dataSetProvider = new DataSetProvider();

        for (DataSetProvider.DataSetType dataSet : DataSetProvider.DataSetType.values()) {
            for (SortHelper.Algorithm algorithm : SortHelper.Algorithm.values()) {
                for (int j = 1000; j <= 1000000; j *= 10) {
                    //Random Number Generator
                    int[][] arrays = new int[5][];
                    for (int k = 0; k < RUNS; k++) {
                        arrays[k] = dataSetProvider.getDataSetForType(dataSet, j);
                    }
                    //start timer for performance
                    startTime = System.currentTimeMillis();
                    for (int k = 0; k < RUNS; k++) {
                        sortHelper.sort(algorithm, arrays[k]);
                    }
                    //end timer for performance
                    finishTime = System.currentTimeMillis();
                    double timePerRun = (double) (finishTime - startTime) / RUNS;
                    System.out.println(algorithm.name() + " sort time for dataset: "+ dataSet.name() +" with input size: " + j + "(ms):" + timePerRun);

                    //calculating sortedness value
                    int[] unsorted = dataSetProvider.getDataSetForType(dataSet, j);
                    long degree = 0;
                    for (int k = 0; k < arrays[0].length; k++) {
                        if (arrays[0][k] != unsorted[k]) {
                            degree++;
                        }
                    }
                    System.out.println(algorithm.name() + " degree of sortedness for dataset: "+ dataSet.name() +" with input size: " + j + "(ms):" + degree);
                }
            }
        }*/
        int n = 200;
        DataSetProvider dataSetProvider = new DataSetProvider();
        int[] a = dataSetProvider.getDataSetForType(DataSetProvider.DataSetType.SYNTHETIC_ALMOST_SORTED, n);
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) max = a[i];
            if (a[i] < min) min = a[i];
        }
        StdDraw.setCanvasSize(800, 360);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setYscale(min-1, max+30);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.004);
        System.out.println(Arrays.toString(a));
        SortHelper sortHelper = new SortHelper();
        sortHelper.sort(SortHelper.Algorithm.QUICK, a);
        System.out.println(Arrays.toString(a));
    }

}