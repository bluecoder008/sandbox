package exercise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Trie {

    class TrieNode {

        public TrieNode() {
            isWord = false;
            children = new HashMap<Character, TrieNode>();
        }

        /*public TrieNode(Character ch) {
            this();
            if (ch != null) {
                data = ch;
            }
        }*/

        public TrieNode childNode(char ch) {
            TrieNode retNode = children.get(ch);
            if (retNode == null) {
                retNode = new TrieNode();
                children.put(ch, retNode);
            }
            return retNode;
        }

        public TrieNode getChildNode(char ch) {
            return children.get(ch);
        }

        public void setWord(boolean isWord) {
            this.isWord = isWord;
        }

        public String getWord() {
            return data;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setData(String data) {
            this.data = data;
        }

        private String data;
        private boolean isWord;
        private Map<Character, TrieNode> children;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the Trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.childNode(ch);
        }
        current.setWord(true);
        current.setData(word);
    }

    /**
     * Look up a word in the Trie
     */
    public boolean lookup(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildNode(ch);
            if (current == null) {
                return false;
            }
        }
        return current.isWord();
    }

    /**
     * Check if a word in the Trie starts with a specific prefix
     */
    public boolean startsWith(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildNode(ch);
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given a String as prefix, which is assumed to be present in the Trie
     * returns a Word that starts with the prefix, or null if no such a word is found.
     *
     * @param prefix String for which words start with.
     * @return the first word that starts with the prefix, or null if no such word is found
     *
     */
    public String getWordForPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.getChildNode(ch);
            if (current == null) {
                return null;
            }
        }
        if (current.isWord()) {
            return current.getWord();
        }
        // Now traverse further over all descendants to find a Word
        Queue<TrieNode> aQueue = new LinkedList<TrieNode>();
        aQueue.offer(current);
        while(!aQueue.isEmpty()) {
            TrieNode currentNode = aQueue.poll();
            if(currentNode.isWord()) {
                return currentNode.getWord();
            }
            for (TrieNode childNode : currentNode.children.values()) {
                aQueue.offer(childNode);
            }
        }
        return null;
    }


    /**
     * Test Driver (TDD, sort of...)
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("world");

        /* The expected output for the following code is:

            trie.lookup("hello"):true
            trie.lookup("world"):true
            trie.lookup("hell"):false
            trie.lookup("word"):false
            trie.startsWith("wor"):true
            trie.startsWith("work"):false
            trie.getWordForPrefix("wo"):world

         */

        System.out.println("trie.lookup(\"hello\"):" + trie.lookup("hello"));
        System.out.println("trie.lookup(\"world\"):" + trie.lookup("world"));
        System.out.println("trie.lookup(\"hell\"):" + trie.lookup("hell"));
        System.out.println("trie.lookup(\"word\"):" + trie.lookup("word"));
        System.out.println("trie.startsWith(\"wor\"):" + trie.startsWith("wor"));
        System.out.println("trie.startsWith(\"work\"):" + trie.startsWith("work"));
        System.out.println("trie.getWordForPrefix(\"wo\"):" + trie.getWordForPrefix("wo"));

        System.out.println("Successfully tested basic functionality of Trie class");
    }
}