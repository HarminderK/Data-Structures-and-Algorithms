import java.util.HashSet;
import java.util.Set;

// Node - Holds a char to represent itself,
// and a String that is equivalent to its (parent Node's String str + Char c) + its own Char c
class Node {

	Node left;
	Node right;
	String str;
	char c;
	int height;

	public Node() {
		left = null;
		right = null;
		str = "";
		height = 0;
		c = '\0';
	}

	public Node(char c, String str, int height, Node left, Node right) {
		this.left = left;
		this.right = right;
		this.str = str;
		this.height = height;
		this.c = c;
	}

}

public class TreePermutation {

	public static void main(String[] args) {

		// Test 0
		String word = "cat";
		TreePermutation test = new TreePermutation(word);

		Set<String> perms = test.getAllPerms();

		
		if(perms.size() == Math.pow(2,word.length())) {
			System.out.println("Pass: cat");
		} else {
			System.out.println("Fail: cat");
		}
		
//		for (String str : perms) {
//
//			System.out.println(str);
//
//		}
		
		
		// Test 1
		word = "testing";
		test = new TreePermutation("testing");

		perms = test.getAllPerms();

		if(perms.size() == Math.pow(2,word.length())) {
			System.out.println("Pass: testing");
		} else {
			System.out.println("Fail: testing");
		}
		
//		for (String str : perms) {
//
//			System.out.println(str);
//
//		}
		
		
	}

	private String word;

	// Initializing Structure
	public TreePermutation(String word) {
		this.word = word;
	}

	// Initializing and starting the recursive permutation
	public Set<String> getAllPerms() {
		Set<String> perms = new HashSet<String>();
		char c = Character.toLowerCase(word.charAt(0));
		char C = Character.toUpperCase(word.charAt(0));

		Node rootLow = new Node(c, c + "", 0, null, null);
		Node rootCap = new Node(C, C + "", 0, null, null);

		getAllPerms(perms, rootLow, word, 1);
		getAllPerms(perms, rootCap, word, 1);

		return perms;

	}

	// Recursive permutation 
	private void getAllPerms(Set<String> perms, Node root, String word, int index) {

		if (index == word.length()) {

			perms.add(root.str);

			return;
		}

		char c = Character.toLowerCase(word.charAt(index));
		char C = Character.toUpperCase(word.charAt(index));

		root.left = new Node(c, root.str + c, root.height + 1, null, null);
		root.right = new Node(C, root.str + C, root.height + 1, null, null);

		index++;

		getAllPerms(perms, root.left, word, index);
		getAllPerms(perms, root.right, word, index);

	}

}
