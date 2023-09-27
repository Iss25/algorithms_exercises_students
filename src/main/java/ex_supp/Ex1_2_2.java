package ex_supp;

import java.util.Stack;
import java.util.StringTokenizer;

public class Ex1_2_2 {
    public static int eval_PostFIX(StringTokenizer token){
        Stack values  = new Stack<Integer>();
        while (token.hasMoreTokens()) {
            String element = token.nextToken();
            if(element.equals("+")){
                int val1 = Integer.parseInt((String) values.pop());
                int val2 = Integer.parseInt((String) values.pop());
                values.push(String.valueOf(val1+val2));
            }
            else if(element.equals(("*"))){
                int val1 = Integer.parseInt((String) values.pop());
                int val2 = Integer.parseInt((String) values.pop());
                values.push(String.valueOf(val1*val2));
            }
            else{
                values.push(element);
            }
        }
        return Integer.parseInt((String) values.pop());
    }

    public static void main(String[] args) {
        String in = "4 20 + 3 5 1 * * +";
        StringTokenizer tokenizer = new StringTokenizer(in);
        int res = eval_PostFIX(tokenizer);
        System.out.println(res);
    }
}
