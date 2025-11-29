/**
 * @author Alvin Vasquez, 000857238
 * @version Assignment1W25
 * date: 2025-09-21
 * I Alvin Vasquez, 000857238, certify that this work is my own work and that I did not consult external resources including artificial intelligence software to complete this assignment without due acknowledgement. I further certify that I did not provide my solution to any other students, nor will I provide it to future students taking this course at a later date.
 *
 * Part 3:
 * Sort Algorithm Identification
 * ==================================================
 *     Sort    Sort Algorithm    Big O    Big O
 * Algorithm        Name          (Time)   (Space)
 * --------------------------------------------------
 * aSort        Selection Sort     O(1)    O(n^2)
 * bSort        Counting Sort      O(n)    O(n)
 * cSort        Merge Sort         O(n)    O(n log n)
 * dSort        Insertion Sort     O(1)    O(n^2)
 * eSort        Quick Sort         O(1)    O(n log n)
 * fSort        Bubble Sort        O(1)    O(n^2)
 * --------------------------------------------------
 * */
import java.util.Random;
import java.util.Scanner;
import static java.lang.System.nanoTime;


/**
 * All methods are static to the class - functional style
 *
 * @author mark.yendt
 */

public class Main {

    /**
     * counting comparisons and assignments for each sorting algorithm
     *
     * @author alvin.vasquez
     *
     * */
    private static long comparisonCounter = 0;



    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array where elements are to be swapped.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;
        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }


    /**
     * Validate that an array is sorted,
     *
     * @param array array that might or might not be sorted
     * @return a 6 digit checksum if sorted, -1 if not sorted.
     */
    public static int ckSumSorted(int[] array) {
        int sum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return -1;
            }
            sum += array[i] * i;
        }
        return Math.abs(sum % 1000_000);
    }

    /**
     * Randomly re-arrange any array, if sorted will unsort the array, or shuffle it
     *
     * @param array array that might or might not be sorted
     */
    public static void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int j = rand.nextInt(array.length);
            swap(array, i, j);
        }
    }

    /**
     * ---------------------------- a Sort [Selection Sort]---------------------------------------
     */

    public static long aSort(int[] array) {
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan

        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];

            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area.
            // Apply counter in this inner loop.
            for (index = startScan + 1; index < array.length; index++) {
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                    comparisonCounter++;
                }
            }

            // Swap the element with the smallest value
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
        //return comparisons
        return comparisonCounter;
    }

    /**
     * ---------------------------- b Sort [Counting Sort] ---------------------------------------
     */

    public static long bSort(int array[]) {
        //use the available assignment to count comparisons
        int count = 0;

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            else if (array[i] > max)
                max = array[i];
        }
        int b[] = new int[max - min + 1];
        for (int i = 0; i < array.length; i++)
            b[array[i] - min]++;

        for (int i = 0; i < b.length; i++)
            for (int j = 0; j < b[i]; j++) {
                array[count++] = i + min;
            }
        return count;
    }
    /**
     * ---------------------------- c Sort [Merge Sort]---------------------------------------
     */

    public static long cSort(int inputArray[]) {
        int length = inputArray.length;
        // Create temporary array only once
        int[] workingArray = new int[inputArray.length];

        return doCSort(inputArray, workingArray, 0, length - 1);
    }

    private static long doCSort(int[] inputArray, int[] workingArray, int lowerIndex, int higherIndex) {
        // count the comparisons with the counter
        comparisonCounter = 0;
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doCSort(inputArray, workingArray, lowerIndex, middle);
            // Below step sorts the right side of the array
            doCSort(inputArray, workingArray, middle + 1, higherIndex);
            // Now process both sides
            part2(inputArray, workingArray, lowerIndex, middle, higherIndex);
        }
        return comparisonCounter;
    }

    private static long part2(int[] inputArray, int[] workingArray, int lowerIndex, int middle, int higherIndex) {
        //count the comparisons with the counter
        comparisonCounter = 0;
        for (int i = lowerIndex; i <= higherIndex; i++) {
            workingArray[i] = inputArray[i];
        }
        int i1 = lowerIndex;
        int i2 = middle + 1;
        int newIndex = lowerIndex;
        while (i1 <= middle && i2 <= higherIndex) {
            if (workingArray[i1] <= workingArray[i2]) {
                //increment the counter in this inner loop
                comparisonCounter++;
                inputArray[newIndex] = workingArray[i1];
                i1++;
            } else {
                inputArray[newIndex] = workingArray[i2];
                i2++;
            }
            newIndex++;
        }
        while (i1 <= middle) {
            inputArray[newIndex] = workingArray[i1];
            newIndex++;
            i1++;
        }
        return comparisonCounter;
    }

    /**
     * ---------------------------- d Sort [Insertion Sort] ---------------------------------------
     */

    public static long dSort(int[] array) {
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array
        comparisonCounter = 0; // Count comparisons from the inner while loop

        // The outer loop steps the index variable through
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;

            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            // Increment the counter outside of this inner while loop
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                array[scan] = array[scan - 1];
                scan--;
            }
            comparisonCounter++;
            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        return comparisonCounter;
    }

    /**
     * ---------------------------- e Sort [Quick Sort] ---------------------------------------
     */

    public static long eSort(int array[]) {

        return doESort(array, 0, array.length - 1);
    }

    /**
     * The doESort method uses the Quick Sort algorithm to sort an int array.
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end The ending subscript of the list to sort
     */
    private static long doESort(int array[], int start, int end) {
        int pivotPoint;
        // count comparisons with a counter
        comparisonCounter = 0;


        if (start < end) {
            // Get the pivot point.
            pivotPoint = part1(array, start, end);
            // Sort the first sub list
            doESort(array, start, pivotPoint - 1);
            // Sort the second sub list.
            doESort(array, pivotPoint + 1, end);
            comparisonCounter++;
        }
        return comparisonCounter;
    }

    /**
     * The part1 method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int part1(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript

        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.

        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        // Increment the counter inside the if statement.
        for (int scan = start + 1; scan <= end; scan++) {
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return endOfLeftList;
    }

    /**
     * ---------------------------- f Sort [Bubble Sort] ---------------------------------------
     */

    public static long fSort(int[] array) {
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare
        comparisonCounter = 0; // Count the comparisons

        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.
                    swap(array, index, index + 1);
                }
                // Increment the comparison counter
                comparisonCounter++;
            }
        }
        return comparisonCounter;
    }

    /**
     * A demonstration of recursive counting in a Binary Search
     * @param array - array to search
     * @param low - the low index - set to 0 to search whiole array
     * @param high - set to length of array to search whole array
     * @param value - item to search for
     * @param count - Used in recursion to accumulate the number of comparisons made (set to 0 on initial call)
     *
     * @return a pair - indicating the index of the match, and the # of steps to find it.
     */
    private static int[] binarySearchR(int[] array, int low, int high, int value, int count)
    {
        int middle;     // Mid point of search

        // Test for the base case where the value is not found.
        if (low > high)
            return new int[] {-1,count};

        // Calculate the middle position.
        middle = (low + high) / 2;

        // Search for the value.
        if (array[middle] == value)
            // Found match return the index
            return new int[] {middle, count};

        else if (value > array[middle])
            // Recursive method call here (Upper half of remaining data)
            return binarySearchR(array, middle + 1, high, value, count+1);
        else
            // Recursive method call here (Lower half of remaining data)
            return binarySearchR(array, low, middle - 1, value, count+1);
    }

    /**
     * Demonstration of a brute force counting in Linear Search.
     * @param array to search.
     * @param low set to 0 to search whole array.
     * @param high set to the highest amount of elements to search the whole array.
     * @param count set to 0 to count through the whole array in linear fashion.*/
    public static int[] linearSearch(int[] array, int low, int high, int value, int count){
        // Test for the base case where the value is not found.
        if (low > high)
            return new int[] {-1,count};

        // Iterate the entire array from lowest to highest
        for (int i = low; i < high; i++){
            count++;
            if (array[i] == value){
                return new int[]{i, count};
            }
        }
        return new int[]{-1, count};
    }




    /**
     * The main method will run through all of the Sorts for the prescribed sizes and produce output for parts A and B
     *
     * Part C should be answered at the VERY TOP of the code in a comment
     *
     */

    public static void main(String[] args) {

        /*
         * Source code provided in assignment.
         *
         * You will need to reorganize this code, it doesn't do
         * anything as supplied. You should make use of several
         * methods and avoid putting all of your code in main().
         */

        /* data generation template */

        int arraySize20 = 20;
        int arraySize = 400;
        int arraySize8000 = 8_000;
        int arraySize1_000_000 = 1_000_000;

        // original array setup
        long myStudentID = 857238; // replace with your student id
        Random rand = new Random(myStudentID);
        int[] array = new int[arraySize];
        for(int i =0; i < array.length; i++) {
            array[i] = rand.nextInt(1,arraySize);
        }

        // array with 20 elements setup
        int[] array2 = new int[arraySize20];
        for(int i=0; i < array2.length; i++){
            array2[i] = rand.nextInt(1, arraySize20);
        }

        // array with 8000 elements setup
        int[] array3 = new int[arraySize8000];
        for(int i=0; i < array3.length; i++){
            array3[i] = rand.nextInt(1, arraySize8000);
        }

        // array with 1,000,000 elements setup
        int[] array4 = new int[arraySize1_000_000];
        for(int i=0; i < array4.length; i++){
            array4[i] = rand.nextInt(1, arraySize1_000_000);
        }


        /* output templates */

        int total_runs = (1_000_000 / arraySize);
        int totalRuns20 = (1_000_000 / arraySize20);
        int totalRuns8000 = (1_000_000 / arraySize8000);
        int totalRuns1_000_000 = (1_000_000 / arraySize1_000_000);
        double startTime;
        double finishTime;
        long millisecond = 1_000_000;

        String sortName = "aSort";
        double execTimeMs = 0.0;
        long compares = 10;
        double msPerCompare = 1;
        int checksum = -1;

        String binarySearch = "Binary Search";
        String linearSearch = "Linear Search";
        double searchesPerMs = 0.0;

        boolean choice = true; // The component for the main menu selection
       while (choice){
           Scanner scanner = new Scanner(System.in); // The component for the main menu selection
           System.out.println("\n\t :+: The Algorithms Benchmark :+: ");
           System.out.println("\t==================================");
           System.out.println("\nMain Menu");
           System.out.println("=========");
           System.out.println("Select an option to view");
           System.out.println("========================\n");
           System.out.println("1. Benchmark at 400 elements");
           System.out.println("2. Benchmark at 20 elements");
           System.out.println("3. Benchmark at 8,000 elements");
           System.out.println("4. Test Binary and Linear Search at 1,000,000 elements");
           System.out.println("5. Exit");
           System.out.println("Option:");

           String option = scanner.nextLine();
           switch(option){
               case "1":
                   System.out.printf("\nComparison of sorts, Array size = %,d  total runs = %,d\n",
                           arraySize, total_runs);
                   System.out.println("==============================================================");
                   System.out.println(
                           "Algorithm      Run time     # of compares         ms / compares    checksum");

                   /* aSort = Selection Sort Benchmarking*/
                   System.out.printf("%-10s", sortName);
                   checksum = ckSumSorted(array);
                   startTime = nanoTime();
                   aSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = aSort(array);
                   startTime = nanoTime();
                   aSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* bSort =  Counting Sort Benchmarking*/
                   shuffle(array);
                   checksum = ckSumSorted(array);
                   sortName = "bSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   bSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = bSort(array);
                   startTime = nanoTime();
                   bSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* cSort = Merge Sort Benchmarking*/
                   shuffle(array);
                   checksum = ckSumSorted(array);
                   sortName = "cSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   cSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = cSort(array);
                   startTime = nanoTime();
                   cSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* dSort = Insertion Sort Benchmarking*/
                   shuffle(array);
                   checksum = ckSumSorted(array);
                   sortName = "dSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   dSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = dSort(array);
                   startTime = nanoTime();
                   dSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array));

                   /* eSort = Quick Sort Benchmarking*/
                   shuffle(array);
                   checksum = ckSumSorted(array);
                   sortName = "eSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   eSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = eSort(array);
                   startTime = nanoTime();
                   eSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array));

                   /* fSort = Bubble Sort Benchmarking*/
                   shuffle(array);
                   checksum = ckSumSorted(array);
                   sortName = "fSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   fSort(array);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / total_runs;
                   compares = fSort(array);
                   startTime = nanoTime();
                   fSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array));
                   break;
               case "2":
                   /* output for Array Size 20 Benchmark*/
                   System.out.printf("\nComparison of sorts, Array size = %,d  total runs = %,d\n",
                           arraySize20, totalRuns20);
                   System.out.println("==============================================================");
                   System.out.println(
                           "Algorithm      Run time     # of compares         ms / compares    checksum");


                   /* aSort = Selection Sort Benchmarking*/
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   aSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = aSort(array);
                   startTime = nanoTime();
                   aSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array2.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   checksum = ckSumSorted(array2);
                   System.out.printf("%,12d \n", checksum);

                   /* bSort =  Counting Sort Benchmarking*/
                   shuffle(array2);
                   checksum = ckSumSorted(array2);
                   sortName = "bSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   bSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = bSort(array2);
                   startTime = nanoTime();
                   bSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array2.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* cSort = Merge Sort Benchmarking*/
                   shuffle(array2);
                   checksum = ckSumSorted(array2);
                   sortName = "cSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   cSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = cSort(array2);
                   startTime = nanoTime();
                   cSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array2.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* dSort = Insertion Sort Benchmarking*/
                   shuffle(array2);
                   checksum = ckSumSorted(array2);
                   sortName = "dSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   dSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = dSort(array2);
                   startTime = nanoTime();
                   dSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array2.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array2));

                   /* eSort = Quick Sort Benchmarking*/
                   shuffle(array2);
                   checksum = ckSumSorted(array2);
                   sortName = "eSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   eSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = eSort(array2);
                   startTime = nanoTime();
                   eSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array2.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array2));

                   /* fSort = Bubble Sort Benchmarking*/
                   shuffle(array2);
                   checksum = ckSumSorted(array2);
                   sortName = "fSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   fSort(array2);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns20;
                   compares = fSort(array2);
                   startTime = nanoTime();
                   fSort(array2);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array2));
                   break;
               case "3":
                   /* output for Array Size 8000 Benchmark*/
                   System.out.printf("\nComparison of sorts, Array size = %,d  total runs = %,d\n",
                           arraySize8000, totalRuns8000);
                   System.out.println("==============================================================");
                   System.out.println(
                           "Algorithm      Run time     # of compares         ms / compares    checksum");


                   /* aSort = Selection Sort Benchmarking*/
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   aSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = aSort(array3);
                   startTime = nanoTime();
                   aSort(array3);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   checksum = ckSumSorted(array3);
                   System.out.printf("%,12d \n", checksum);

                   /* bSort =  Counting Sort Benchmarking*/
                   shuffle(array3);
                   checksum = ckSumSorted(array3);
                   sortName = "bSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   bSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = bSort(array3);
                   startTime = nanoTime();
                   bSort(array3);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* cSort = Merge Sort Benchmarking*/
                   shuffle(array3);
                   checksum = ckSumSorted(array3);
                   sortName = "cSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   cSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = cSort(array3);
                   startTime = nanoTime();
                   cSort(array);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum);

                   /* dSort = Insertion Sort Benchmarking*/
                   shuffle(array3);
                   checksum = ckSumSorted(array3);
                   sortName = "dSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   dSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = dSort(array3);
                   startTime = nanoTime();
                   dSort(array3);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array3));

                   /* eSort = Quick Sort Benchmarking*/
                   shuffle(array3);
                   checksum = ckSumSorted(array3);
                   sortName = "eSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   eSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = eSort(array3);
                   startTime = nanoTime();
                   eSort(array3);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array3));

                   /* fSort = Bubble Sort Benchmarking*/
                   shuffle(array3);
                   checksum = ckSumSorted(array3);
                   sortName = "fSort";
                   System.out.printf("%-10s", sortName);
                   startTime = nanoTime();
                   fSort(array3);
                   finishTime = nanoTime();
                   execTimeMs = (finishTime - startTime) / totalRuns8000;
                   compares = fSort(array3);
                   startTime = nanoTime();
                   fSort(array3);
                   finishTime = nanoTime();
                   msPerCompare = (finishTime - startTime) / compares;
                   System.out.printf("%,10.7f ms", (execTimeMs / millisecond), array3.length);
                   System.out.printf("%,14d ops", compares);
                   System.out.printf("%,14.7f ms / op", msPerCompare / millisecond);
                   System.out.printf("%,12d \n", checksum, ckSumSorted(array3));
                   break;
               case "4":
                   /* Binary Vs. Linear Search Output */

                   System.out.println("\n\n== BINARY SEARCH vs. LINEAR SEARCH == \n");

                   /*Binary Search Benchmark*/
                   System.out.println("Testing runtime at value -1 in default count 0");
                   System.out.printf("%-10s", binarySearch);
                   startTime = nanoTime();
                   binarySearchR(array4,0, 1000000, -1,0);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Linear Search Benchmark*/
                   System.out.printf("%-10s", linearSearch);
                   startTime = nanoTime();
                   linearSearch(array4, 0, 1000000, -1, 0);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Binary Search Benchmark*/
                   System.out.println("Testing runtime at value -1 in count 10");
                   System.out.printf("%-10s", binarySearch);
                   startTime = nanoTime();
                   binarySearchR(array4,0, 1000000, -1,10);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Linear Search Benchmark*/
                   System.out.printf("%-10s", linearSearch);
                   startTime = nanoTime();
                   linearSearch(array4, 0, 1000000, -1, 10);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);


                   /*Binary Search Benchmark*/
                   System.out.println("Testing runtime at value -1 in default count 1000");
                   System.out.printf("%-10s", binarySearch);
                   startTime = nanoTime();
                   binarySearchR(array4,0, 1000000, -1,1000);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Linear Search Benchmark*/
                   System.out.printf("%-10s", linearSearch);
                   startTime = nanoTime();
                   linearSearch(array4, 0, 1000000, -1, 1000);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Binary Search Benchmark*/
                   System.out.println("Testing runtime at value -1 in default counting speed 10,000");
                   System.out.printf("%-10s", binarySearch);
                   startTime = nanoTime();
                   binarySearchR(array4,0, 1000000, -1,10000);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Linear Search Benchmark*/
                   System.out.printf("%-10s", linearSearch);
                   startTime = nanoTime();
                   linearSearch(array4, 0, 1000000, -1, 10000);
                   finishTime = nanoTime();
                   searchesPerMs = (finishTime - startTime) / totalRuns1_000_000;
                   System.out.printf("%,10.7f ms\n", searchesPerMs / millisecond);

                   /*Result Findings*/
                   System.out.println("Result Findings:");
                   System.out.println("================");
                   System.out.println("As the iteration count increases\nthe linear search O(n) time complexity is multiplied\nwhich binary search's 'divide and conquer'\ntime complexity of O(log n) slows down.");

                   break;
               case "5":
                   System.out.println("Exiting....");
                   choice = false;
                   break;
               default:
                   System.out.println("Please select a valid choice.");
                   break;
           }
       }
    }
}
