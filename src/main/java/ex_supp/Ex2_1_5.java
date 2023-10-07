package ex_supp;

import java.util.LinkedList;

public class Ex2_1_5 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 5; i >= 0; i--) {
            list.add(i);
        }
        System.out.println(list);
        list.sort(Integer::compare);
        System.out.println(list);
    }
}
