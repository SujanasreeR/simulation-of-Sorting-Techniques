public class SortHelper {

    enum Algorithm {
        INSERTION,
        BUBBLE,
        SELECTION,
        MERGE,
        QUICK
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //Insertion Sort
    private void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j-1);
                showInsertion(array, i, j-1);
            }
        }
        showSorted(array, Algorithm.INSERTION);
    }

    //Bubble sort
    private void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j+1);
                }
                showBubble(array, j, array.length - i - 1);
            }
        }
        showSorted(array, Algorithm.BUBBLE);
    }

    //Selection sort
    private void selectionSort(int[] array) {
        for (int i = 0; i < array.length ; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
                showSelection(array, i, j);
            }
        }
        showSorted(array, Algorithm.SELECTION);
    }

    //MergeSort
    private void mergeSort(int[] array) {
        mergeSortHelper(array, 0, array.length);
    }

    private void mergeSortHelper(int[] array, int low, int high)
    {
        //System.out.println("low: "+low+" high: "+high);
        int n = high - low;
        if (n <= 1)
            return;
        int mid = low + n/2;
        //System.out.println("mid: "+mid);
        mergeSortHelper(array, low, mid);
        mergeSortHelper(array, mid, high);
        //System.out.println("merging: [ "+low+", "+high+" ]");
        // merge two sorted sub arrays
        int[] temp = new int[n];
        int i = low, j = mid;
        for (int k = 0; k < n; k++)
        {
            if (i == mid)
                temp[k] = array[j++];
            else if (j == high)
                temp[k] = array[i++];
            else if (array[j]<array[i])
                temp[k] = array[j++];
            else
                temp[k] = array[i++];
            showIndices(array, i, j);
        }
        for (int k = 0; k < n; k++) {
            array[low + k] = temp[k];
        }
        showSorted(array, low, high-1, Algorithm.MERGE);
    }

    private void quickSort(int[] a) {
        //System.out.println("input: "+ Arrays.toString(a));
        quickSortHelper(a,0,a.length-1);
        //showSorted(a);
        //System.out.println("output: "+ Arrays.toString(a));
    }

    private void quickSortHelper(int[] a, int low, int high) {

        if (high <= low) {
            return;
        }
        int lt = low;
        int gt = high;
        int i = lt+1;
        int pivot = a[low];
        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a,i++,lt++);
                showIndices(a, lt, i, gt);
            } else if(a[i] > pivot) {
                swap(a,i,gt--);
                showIndices(a, lt, i, gt);
            } else {
                i++;
            }
        }
        quickSortHelper(a,low,lt-1);
        quickSortHelper(a,gt+1,high);
        showSorted(a, low, high, Algorithm.QUICK);
    }

    void sort(Algorithm algorithm, int[] array) {
        switch (algorithm) {
            case INSERTION:
                insertionSort(array);
                break;
            case SELECTION:
                selectionSort(array);
                break;
            case BUBBLE:
                bubbleSort(array);
                break;
            case MERGE:
                mergeSort(array);
                break;
            case QUICK:
                quickSort(array);
            default:
                break;
        }
    }

    private void showInsertion(int[] a, int i, int j) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int k = 0; k <= i; k++) {
            if (j == k) continue;
            StdDraw.line(k, 0, k, a[k]);
        }
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = i+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(j, 0, j, a[j]);
        showTextForAlgorithm(Algorithm.INSERTION);
        StdDraw.show();
    }

    private void showBubble(int[] a, int i, int j) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k <= j; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(i, 0, i, a[i]);
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int k = j+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        showTextForAlgorithm(Algorithm.BUBBLE);
        StdDraw.show();
    }

    private void showSelection(int[] a, int i, int j) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(i, 0, i, a[i]);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = i+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(j, 0, j, a[j]);
        showTextForAlgorithm(Algorithm.SELECTION);
        StdDraw.show();
    }

    private void showIndices(int[] a, int i, int j) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(i, 0, i, a[i]);
        if (j < a.length) {
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.line(j, 0, j, a[j]);
        }
        showTextForAlgorithm(Algorithm.MERGE);
        StdDraw.show();
    }

    private void showIndices(int[] a, int lt, int i, int gt) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(lt, 0, lt, a[lt]);
        if (i < a.length) {
            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.line(i, 0, i, a[i]);
        }
        if (gt < a.length) {
            StdDraw.setPenColor(StdDraw.BOOK_RED);
            StdDraw.line(gt, 0, gt, a[gt]);
        }
        showTextForAlgorithm(Algorithm.QUICK);
        StdDraw.show();
    }

    private void showSorted(int[] a, int i, int j, Algorithm algorithm) {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int k = i; k <= j; k++)
            StdDraw.line(k, 0, k, a[k]);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = j+1; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
        showTextForAlgorithm(algorithm);
        StdDraw.show();
    }

    private void showTextForAlgorithm(Algorithm algorithm) {
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.text(100, 210, algorithm.name());
    }

    private void showSorted(int[] a, Algorithm algorithm) {
        showSorted(a, 0, a.length-1, algorithm);
    }
}
