package exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by weilan_wu on 3/19/16.
 */
public class Dictionary {

    public static Dictionary getInstance() {
        return LazyHolder.INSTANCE;
    }

    public boolean checkWord(String word) {
        return trie.lookup(word);
    }

    public boolean checkPrefix(String word) {
        return trie.startsWith(word);
    }

    public String getWord(String prefix) {
        return trie.getWordForPrefix(prefix);
    }

    private static final String DICTIONARY_WORD_LIST_FILE = "/usr/share/dict/words";

    private Trie trie;

    private Dictionary(String fileName) {
        trie = new Trie();
        this.loadFromFile(fileName);
    }

    private static class LazyHolder {
        private static final Dictionary INSTANCE = new Dictionary(DICTIONARY_WORD_LIST_FILE);
    }

    private void loadFromFile(String fileName) {
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            String word;
            System.out.println("Importing dictionary data...");
            while((word = bufferedReader.readLine()) != null) {
                if (word.length() > 4 ) {
                    trie.insert(word);
                }
            }
            System.out.println("Done importing dictionary data.\n");

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.err.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.err.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    /** Test Driver */
    public static void main(String[] args) {
        Dictionary dict = Dictionary.getInstance();
        Scanner scan = new Scanner(System.in);
        String word = null;
        while( !"quit".equals(word) ) {
            System.out.print("\n>");
            word=scan.nextLine();
            if (dict.checkWord(word)) {
                System.out.println(word + " is found in the dictionary");
            } else {
                System.err.println(word + " is NOT found in the dictionary");
            }
        }
    }
}
