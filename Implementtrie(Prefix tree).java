// Problem Statement:
// Implement the Trie class:
// Trie() Initializes the trie object.
// void insert(String word) Inserts the string word into the trie.
// boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
// boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

// Approach:
// A Trie (Prefix Tree) is a tree-like data structure used to store a dynamic set of strings where keys are usually strings.
// Each node in the trie represents a character, and the path from the root to a node represents a prefix.
// The root typically represents an empty string. Each node has an array of children, one for each possible character (e.g., 26 for lowercase English letters).
// A boolean flag `isEndOfWord` is used in each node to mark if a word ends at that particular node.

// TrieNode Class:
// Each TrieNode will contain:
// 1. `children`: An array of TrieNode references, typically of size 26 for lowercase English letters. `children[i]` would point to the next node if the character is 'a' + i.
// 2. `isEndOfWord`: A boolean flag, true if a word ends at this node, false otherwise.

// Trie Class Methods:

// 1. `Trie()`: Constructor
//    Initializes the root node of the trie. The root node itself does not represent any character but serves as the entry point.

// 2. `insert(String word)`:
//    - Start from the `root` node.
//    - For each character `c` in the `word`:
//      - Calculate the index `idx = c - 'a'`.
//      - If `currentNode.children[idx]` is null, create a new `TrieNode` and assign it to `currentNode.children[idx]`.
//      - Move `currentNode` to `currentNode.children[idx]`.
//    - After iterating through all characters, set `currentNode.isEndOfWord` to true to mark the end of the inserted word.

// 3. `search(String word)`:
//    - This method checks if the `word` exists completely in the trie.
//    - Start from the `root` node.
//    - For each character `c` in the `word`:
//      - Calculate the index `idx = c - 'a'`.
//      - If `currentNode.children[idx]` is null, it means the character path does not exist, so the word is not in the trie. Return `false`.
//      - Move `currentNode` to `currentNode.children[idx]`.
//    - After iterating through all characters, if we reached the end of the word, return `currentNode.isEndOfWord`. This ensures that we only return true if a full word ends at this node, not just a prefix.

// 4. `startsWith(String prefix)`:
//    - This method checks if any word in the trie starts with the given `prefix`.
//    - Start from the `root` node.
//    - For each character `c` in the `prefix`:
//      - Calculate the index `idx = c - 'a'`.
//      - If `currentNode.children[idx]` is null, it means the character path does not exist, so no word starts with this prefix. Return `false`.
//      - Move `currentNode` to `currentNode.children[idx]`.
//    - If we successfully traverse the entire `prefix`, it means a path for the prefix exists. Return `true`. (We don't care about `isEndOfWord` here, as any word extending from this prefix makes `startsWith` true).

// Time Complexity:
// Let L be the length of the `word` or `prefix`.
// - `Trie()`: O(1)
// - `insert(String word)`: O(L) - we traverse each character of the word once.
// - `search(String word)`: O(L) - we traverse each character of the word once.
// - `startsWith(String prefix)`: O(L) - we traverse each character of the prefix once.

// Space Complexity:
// In the worst case, if no words share prefixes, each character of every word creates a new node.
// - `insert(String word)`: O(L) for creating new nodes if the path doesn't exist.
// - Total Space: O(Total number of characters in all inserted words).
//   In the worst case, for N words of average length L, it can be O(N * L).
//   Given constraints: word/prefix length <= 2000, 3 * 10^4 calls. Max total characters could be 3 * 10^4 * 2000, which is too large.
//   However, the constraint says "sum(word.length) <= 2 * 10^4" for some problems, but here it's "word.length, prefix.length <= 2000" and "At most 3 * 10^4 calls".
//   The total number of nodes in the trie is proportional to the sum of lengths of all unique prefixes inserted.
//   If all words are unique and don't share prefixes (e.g., "a", "b", "c", ...), space is O(sum of lengths of all words).
//   If words share prefixes heavily (e.g., "apple", "app", "apricot"), space is less.
//   Maximum total nodes is bounded by the total length of all inserted words if they were concatenated and de-duplicated.
//   A more practical estimate for space: If there are `C` total unique characters across all inserted words, and each node has 26 children array, space would be `C * 26 * sizeof(TrieNode reference)`.
//   Roughly, O(Total number of distinct characters in all unique prefixes).

class Trie {

    class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; 
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currentNode = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode currentNode = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (currentNode.children[index] == null) {
                return false; 
            }
            currentNode = currentNode.children[index];
        }
        return currentNode.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }
            currentNode = currentNode.children[index];
        }
        return true;
    }
}
