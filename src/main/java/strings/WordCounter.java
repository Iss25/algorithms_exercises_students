package strings;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implement the class WordCounter that counts the number of occurrences
 * of each word in a given piece of text.
 * Feel free to use existing java classes.
 */
public class WordCounter implements Iterable<String> {

    private Map<String, Integer> wordLST;
    public WordCounter() {
        wordLST = new TreeMap<>();
    }

    /**
     * Add the word so that the counter of the word is increased by 1
     */
    public void addWord(String word) {
        if(wordLST.containsKey(word)){
            wordLST.put(word,wordLST.get(word)+1);
        }
        else {
            wordLST.put(word,1);
        }
    }

    /**
     * Return the number of times the word has been added so far
     */
    public int getCount(String word) {
        if (wordLST.containsKey(word)){
            return wordLST.get(word);
        }
        return 0;
    }

    // iterate over the words in ascending lexicographical order
    @Override
    public Iterator<String> iterator() {
        return wordLST.keySet().iterator();
    }
}