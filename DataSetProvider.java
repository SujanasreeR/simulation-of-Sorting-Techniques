import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class DataSetProvider {
    enum DataSetType {
        SYNTHETIC_UNIFORM,
        SYNTHETIC_GAUSSIAN,
        SYNTHETIC_ALMOST_SORTED,
        CHECKOUTS,
        PARKING_VIOLATION
    }
    private static int MAX_SIZE = 10*1000*1000;
    int[] checkoutsByTitle = new int[MAX_SIZE];// 1 Million max
    int[] parkingViolationCode = new int[MAX_SIZE];// 1 Million max

    DataSetProvider() {
        readColumnDataFromFile("res/Checkouts_By_Title.txt", 0, checkoutsByTitle);
        readColumnDataFromFile("res/parking_violation_code.txt", 0, parkingViolationCode);
    }

    private void readColumnDataFromFile(String fileName, int column, int[] array) {
        System.out.println(fileName);
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading file: "+ fileName);
            return;
        }
        String line;
        int i = 0;
        try {
            while((line = in.readLine()) != null && i < MAX_SIZE) {
                array[i++] = Integer.parseInt(line.split(",")[column]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e);
            }
        }
    }

    private int[] getUniformRandomNumbers(int num) {
        Random random = new Random();
        random.setSeed(20);
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = random.nextInt(num);
        }
        //System.out.println("data set:" +Arrays.toString(result));
        return result;
    }

    private int[] getGaussianRandomNumbers(int num) {
        Random random = new Random();
        random.setSeed(5);
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = (int)Math.round(random.nextGaussian() * num/6.1) + num/2;
            //result[i] = (int)Math.round(random.nextGaussian()*num);
        }
        //System.out.println("data set:" +Arrays.toString(result));
        return result;
    }

    private int[] getAlmostSortedNumbers(int num) {
        Random random = new Random();
        random.setSeed(5);
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i;
        }
        //Swap last element with random one
        swap(result, random.nextInt(num), result.length-1);

        return result;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int[] copyDataFromSource(int[] source, int num) {
        if (num > MAX_SIZE) {
            throw new IllegalArgumentException("size > allowed max size: " + MAX_SIZE);
        }
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = source[i];
        }
        return result;
    }

    public int[] getDataSetForType(DataSetType type, int n) {
        switch (type) {
            case SYNTHETIC_UNIFORM:
                return getUniformRandomNumbers(n);
            case SYNTHETIC_GAUSSIAN:
                return getGaussianRandomNumbers(n);
            case SYNTHETIC_ALMOST_SORTED:
                return getAlmostSortedNumbers(n);
            case CHECKOUTS:
                return copyDataFromSource(checkoutsByTitle, n);
            case PARKING_VIOLATION:
                return copyDataFromSource(parkingViolationCode, n);
            default:
                return new int[0];
        }
    }
}
