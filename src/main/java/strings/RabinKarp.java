package strings;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Author Pierre Schaus
 *
 * We are interested in the Rabin-Karp algorithm.
 * We would like to modify it a bit to determine
 * if a word among a list (!!! all words are of the same length !!!)
 * is present in the text.
 * To do this, you need to modify the Rabin-Karp
 * algorithm which is shown below (page 777 of the book).
 * More precisely, you are asked to modify this class
 * so that it has a constructor of the form:
 * public RabinKarp(String[] pat)
 *
 * Moreover the search function must return
 * the index of the beginning of the first
 * word (among the pat array) found in the text or
 * the size of the text if no word appears in the text.
 *
 * Example: If txt = "Here find interesting
 * exercise for Rabin Karp" and pat={"have", "find", "Karp"}
 * the search function must return 5 because
 * the word "find" present in the text and in the list starts at index 5.
 *
 */
public class RabinKarp {


    private String pat; // pattern (only needed for Las Vegas)

    //private long patHash; // pattern hash value

    public Hashtable<Long,String> hashtable = new Hashtable<>();

    private int M; // pattern length
    private long Q; // a large prime
    private int R = 2048; // alphabet size
    private long RM; // R^(M-1) % Q

    public RabinKarp(String[] pat) {

        // CERTAINS TRUC ONT ETE CHANGER SUR LES DEUX LIGNES SUIVANTES
        this.pat = Arrays.toString(pat); // save pattern (only needed for Las Vegas)
        this.M = pat[0].length();
        Q = 4463;
        RM = 1;

        for (int i = 1; i <= M - 1; i++){ // Compute R^(M-1) % Q for use
            RM = (R * RM) % Q; // in removing leading digit.
        }

        for (int i = 0; i < pat.length; i++) {
            long place = hash(pat[i],M);
            this.hashtable.put(place,pat[i]);
        }
    }

     public boolean check(int i,long hash,String txt){
        return hashtable.get(hash).equals(txt.substring(i, i + M));
     } // For Las Vegas, check pat vs txt(i..i-M+1).


    private long hash(String key, int M) { // Compute hash for key[0..M-1].
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }


    public int search(String txt) { // Search for hash match in text.
        int N = txt.length();
        long txtHash = hash(txt, M);

        if(hashtable.containsKey(txtHash) && check(0,txtHash,txt)){
            return 0;
        }
        for (int i = M; i < N; i++) { // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(hashtable.containsKey(txtHash)){
                if(check(i - M + 1,txtHash,txt)){
                    return i - M + 1;
                }
            }
        }
        return N; // no match found
    }
}
