package sorting;

import java.*;
import java.util.Arrays;

/**
 * Let a be an array of integers.
 * In this exercise we are interested in finding
 * the two entries i and j such that a[i] + a[j] is the closest from a target x.
 * In other words, there are no entries k,l such that |x - (a[i] + a[j])| > |x - (a[k] + a[l])|.
 * Note that we can have i = j.
 * Your program must return the values a[i] and a[j].
 *
 * For example let a = [5, 10, 1, 75, 150, 151, 155, 18, 75, 50, 30]
 *
 * then for x = 20, your program must return the array [10, 10],
 *      for x = 153 you must return [1, 151] and
 *      for x = 170 you must return [18, 151]
 */
public class ClosestPair {

    /**
      * Finds the pair of integers which sums up to the closest value of x
      *
      * @param a the array in which the value are looked for
      * @param x the target value for the sum
      */
    public static int[] closestPair(int [] a, int x) {
        Arrays.sort(a);
        int low = 0;
        int high = a.length-1;
        int[] res = new int[]{a[low],a[high]};

        while(low < high){
            int curr = a[low] + a[high];
            if(curr < x){
                low += 1;
            }
            if(curr > x){
                high -= 1;
            }
            if(curr == x){
                res[0] = a[low];
                res[1] = a[high];
                return res;
            }
            if(Math.abs(x- (a[low] + a[high])) < Math.abs(x-(res[0]+res[1]))){
                res[0] = a[low];
                res[1] = a[high];
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}
