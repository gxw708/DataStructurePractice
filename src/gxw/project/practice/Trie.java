package gxw.project.practice;

public class Trie {
	TrieNode root = null;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray())
        {
            if(curr.children[c - 'a'] == null)
            {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word, true);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return search(prefix, false);
    }
    
    private boolean search(String str, boolean isWord)
    {
        TrieNode curr = root;
        for(char c : str.toCharArray())
        {
            if(curr.children[c - 'a'] == null)
                return false;
            curr = curr.children[c - 'a'];
        }
        
        return isWord ? curr.isEnd : true;
    }
    
    static class TrieNode {
        public static final int N = 26;
        public TrieNode[] children = new TrieNode[N];
        public boolean isEnd = false;
    };
    
    public static void main(String args[])
    {
    	Trie trie = new Trie();
    	trie.insert("apple");
    	System.out.println(trie.search("apple"));   // returns true
    	System.out.println(trie.search("app"));     // returns false
    	System.out.println(trie.startsWith("app")); // returns true
    	trie.insert("app");   
    	System.out.println(trie.search("app"));     // returns true
    	
    	System.out.println((int)('a'));
    	System.out.println((int)('.'));
    }
}
