package fundamentals;


/**
 * Author Pierre Schaus
 *
 * We give you the API of a Vector class allowing to access,
 * modify and exchange two elements in constant time.
 * Your task is to implement a method to calculate the median of a Vector.
 *
 * public interface Vector {
 *     // size of the vector
 *     public int size();
 *     // set the value v to the index i of the vector
 *     public void set(int i, int v);
 *     // returns the value at index i of the vector
 *     public int get(int i);
 *     // exchanges the values at positions i and j
 *     public void swap(int i, int j);
 * }
 * You must implement a method that has the following signature:
 * public static int median(Vector a, int lo, int hi)
 *
 * This method calculates the median of vector "a" between the positions "lo" and "hi" (included).
 * You can consider that the vector "a" has always an odd size.
 * To help you, an IntelliJ project with a minimalist test to check if your code computes the right median value is given.
 * Indeed, it is not advised to debug your code via Inginious.
 * Warning It is not forbidden to modify or swap elements of the vector "a" during the calculation (with the get/set/swap methods).
 * It is forbidden to call other methods of the standard Java library. It is also forbidden to make a "new".
 *
 * The evaluation is based on 10 points:
 *  - good return value: 3 points,
 *  - good return value and complexity O(n log n): 5 points,
 *  - good return value and complexity O(n) expected (average case on a random uniform distribution): 10 points.
 *
 *  All the code you write in the text field will be substituted in the place indicated below.
 *  You are free to implement other methods to help you in this class, but the method "median" given above must at least be included.
 */
public class Median {

    public static class Vector {

        private int [] array;
        private int nOp = 0;


        public Vector(int n) {
            array = new int[n];
        }

        public int size() {
            return array.length;
        }

        public void set(int i, int v) {
            nOp++;
            array[i] = v;
        }

        public int get(int i) {
            nOp++;
            return array[i];
        }

        public void swap(int i, int j) {
            nOp++;
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

        public int nOp() {
            return nOp;
        }



    }




    public static int median(Vector a, int lo, int hi) {
        // TODO
         return -1;
    }



}
