package graphs;

import java.util.*;


/**
 * You are asked to implement the WordTransformationSP
 * class which allows to find the shortest path
 * from a string A to another string B
 * (with the certainty that there is a path from A to B).
 * To do this, we define a rotation(x, y) operation that
 * reverses the order of the letters between the x and y positions (not included).
 * For example, with A=``HAMBURGER``, if we do rotation(A, 4, 8), we get HAMBEGRUR.
 * So you can see that the URGE sub-string
 * has been inverted to EGRU and the rest of the string
 * has remained unchanged: HAMB + ECRU + R = HAMBEGRUR.
 * Let's say that a rotation(x, y) has a cost of y-x.
 * For example going from HAMBURGER to HAMBEGRUR costs 8-4 = 4.
 * The question is what is the minimum cost to reach a string B from A?
 * So you need to implement a public static int minimalCost(String A, String B)
 * function that returns the minimum cost to reach String B
 * from A using the rotation operation.
 */
public class WordTransformationSP {

    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     */
    /**
     * Rotate the substring between start and end of a given string s
     * eg. s = HAMBURGER, rotation(s,4,8) = HAMBEGRUR i.e. HAMB + EGRU + R
     *
     * @param s
     * @param start
     * @param end
     * @return rotated string
     */
    public static String rotation(String s, int start, int end) {
        return s.substring(0, start) + new StringBuilder(s.substring(start, end)).reverse().toString() + s.substring(end);
    }

    public static class Rot implements Comparable<Rot> {
        String str;
        int cost;

        Rot(String val, int cost){
            this.str = val;
            this.cost = cost;
        }

        @Override
        public int compareTo(Rot o) {
            return this.cost - o.cost;
        }
    }
    /**
     * Compute the minimal cost from string "from" to string "to" representing the shortest path
     *
     * @param from
     * @param to
     * @return
     */
    public static int minimalCost(String from, String to) {
        HashMap<String,Integer> cost_for = new HashMap<>();
        PriorityQueue<Rot> queue = new PriorityQueue<>();
        queue.add(new Rot(from,0));
        cost_for.put(from,0);
        while (!queue.isEmpty()){
            Rot act = queue.poll();
            String word = act.str;
            for (int i = 0; i < word.length() + 1; i++) {
                for (int j = i; j < word.length() + 1; j++) {
                    String rotated = rotation(word,i,j);
                    int cost = act.cost + j - i;
                    if(!cost_for.containsKey(rotated) || cost < cost_for.get(rotated)){
                        cost_for.put(rotated,cost);
                        queue.add(new Rot(rotated,cost));
                    }
                }
            }
        }
        return cost_for.get(to);
    }


}
