/*
Natalie Kocsis
nkocsis@u.rochester.edu
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Lab5 {
    //list is used to add leaves of trie to list and to print
    public static ArrayList<String> list = new ArrayList<>();
    //list of heights is used to find max height of all the different paths
    public static ArrayList<Integer> heights = new ArrayList<>();
    public static int count = 0;
    public static int height = 0;

    public static class TrieNode {
        private TrieNode left = null;
        private TrieNode right = null;
        private String string;

        //constructor
        public TrieNode(String s) {
            this.string = s;
        }

        public void setLeft(TrieNode t) {
            left = t;
        }

        public TrieNode getLeft() {
            return this.left;
        }

        public void setRight(TrieNode t) {
            right = t;
        }

        public TrieNode getRight() {
            return this.right;
        }

        public boolean isLeaf() {
            return this.getRight() == null && this.getLeft() == null;
        }

        public void setString(String s) {
            string = s;
        }

        public String getString() {
            return this.string;
        }


    }

    public static class Trie {
        private TrieNode root;

        public TrieNode getRoot() {
            return root;
        }

        public void setRoot(TrieNode n) {
            root = n;
        }

    }

    public static boolean insert(Trie t, String st) {
        //not sure insert works for different sized strings
        //System.out.println("trying to insert " + st);

        int num = 0;
        //if root is null or has no node yet, set current to root
        if (t.getRoot() == null) {
            TrieNode n = new TrieNode(st);
            t.setRoot(n);
            return true;
        } else {
            TrieNode current = t.getRoot();
            //for each character in the string, either 0 or 1
            for (int i = 0; i < st.length(); i++) {
                if (st.charAt(i) == '0') {
                    if (current.getLeft() == null) {
                        if (current.getString().length() < 1) { //if it's an internal node
                            TrieNode n = new TrieNode(st);
                            current.setLeft(n);
                            //System.out.println("inserted left at internal node");
                            return true;
                        } else {
                            String s1 = current.getString(); //compare to st
                            //while the current string trying to be added, and the current leaf
                            //we are at are equal, make a path of internal nodes until they are different
                            num = i;
                            for (int j = i; j < s1.length() && j < st.length() && s1.charAt(j) == st.charAt(j); j++) {
                                //if the strings are the exact same, don't do anything
                                if (s1.equals(st)) {
                                    return false;
                                }
                                if (s1.charAt(j) == '0') {
                                    current.setString("");
                                    TrieNode n = new TrieNode("");
                                    current.setLeft(n);
                                    current = current.getLeft();
                                    //System.out.println("inserted internal node to the left");
                                } else if (s1.charAt(j) == '1') {
                                    current.setString("");
                                    TrieNode n = new TrieNode("");
                                    current.setRight(n);
                                    current = current.getRight();
                                    //System.out.println("inserted internal node to the right");
                                }
                                //keeps track of index of string that the two strings differ
                                num = j + 1;
                            }

                            //if the character where the two strings differ equals 0, insert st to the left
                            //and the other string to the right.
                            if (st.charAt(num) == '0') {
                                TrieNode n = new TrieNode(st);
                                current.setLeft(n);
                                //System.out.println(st + " inserted to left");
                                TrieNode n2 = new TrieNode(s1);
                                current.setRight(n2);
                                //System.out.println(s1 + " inserted to right");
                                current.setString("");
                            } else if (st.charAt(num) == '1') {
                                TrieNode n = new TrieNode(st);
                                current.setRight(n);
                                //System.out.println(st + " inserted to right");
                                TrieNode n2 = new TrieNode(s1);
                                current.setLeft(n2);
                                //System.out.println(s1 + " inserted to left");
                                current.setString("");
                            }
                            return true;
                        }
                        //if it is an internal node, traverse through the tree to the left
                    } else {
                        current = current.getLeft();
                    }
                    //if the character in the string is a 1, and the right child is empty
                } else if (st.charAt(i) == '1') {
                    if (current.getRight() == null) {
                        if (current.getString().length() < 1) { //if it's an internal node with no right child
                            TrieNode n = new TrieNode(st);
                            current.setRight(n);
                            //System.out.println("inserted to right of internal node");
                            return true;
                        } else {
                            String s1 = current.getString(); //compare to st
                            //while the two string are the same, create internal node path until they are different
                            num = i;
                            for (int j = i; j < s1.length() && j < st.length() && s1.charAt(j) == st.charAt(j); j++) {
                                if (s1.equals(st)) {
                                    return false;
                                }
                                if (s1.charAt(j) == '0') {
                                    current.setString("");
                                    TrieNode n = new TrieNode("");
                                    current.setLeft(n);
                                    current = current.getLeft();
                                    //System.out.println("inserted internal node to the left");
                                } else if (s1.charAt(j) == '1') {
                                    current.setString("");
                                    TrieNode n = new TrieNode("");
                                    current.setRight(n);
                                    current = current.getRight();
                                    //System.out.println("inserted internal node to the right");
                                }
                                //keeps track of index of character that differs in the two strings,
                                //or when it leaves the while loop
                                num = j + 1;
                            }

                            //the character that is different between the two strings, determines
                            //which gets inserted to left, or right.
                            if (st.charAt(num) == '0') {
                                TrieNode n = new TrieNode(st);
                                current.setLeft(n);
                                //System.out.println(st + " inserted to left");
                                TrieNode n2 = new TrieNode(s1);
                                current.setRight(n2);
                                //System.out.println(s1 + " inserted to right");
                                current.setString("");
                            } else if (st.charAt(num) == '1') {
                                TrieNode n = new TrieNode(st);
                                current.setRight(n);
                                //System.out.println(st + " inserted to right");
                                TrieNode n2 = new TrieNode(s1);
                                current.setLeft(n2);
                                //System.out.println(s1 + " inserted to left");
                                current.setString("");
                            }
                            return true;
                        }

                        //if node to the right of current is an internal node, traverse through
                        //the tree by setting current to the right child.
                    } else {
                        current = current.getRight();
                    }
                }
            }
            return false;
        }
    }


    public static void trieToList(Trie t) {
        //clears list everytime so that duplicates don't occur when print is called more than once
        list.clear();
        printList(t.root);
        //iterates through list created in printList method and prints each element
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

    }

    public static void printList(TrieNode n) {
        if (n == null)
            return;
        if (n.isLeaf()) {
            //adds string to list if it is a leaf
            list.add(n.getString());
        } else {
            //traverses through tree until it hits a leaf using recursion
            if (n.getLeft() != null) {
                printList(n.getLeft());
            }
            if (n.getRight() != null) {
                printList(n.getRight());
            }
        }

    }

    public static String largest(Trie t) {
        TrieNode current = t.getRoot();
        //goes as far right as possible, until it cannot, then checks to go left
        //once it reaches a leaf, the current string is returned.
        while (!current.isLeaf()) {
            if (current.getRight() != null) {
                current = current.getRight();
            } else if (current.getLeft() != null) {
                current = current.getLeft();
            }
        }

        return current.getString();
    }

    //same set up as largest() method but goes left first instead of right
    public static String smallest(Trie t) {
        TrieNode current = t.getRoot();
        while (!current.isLeaf()) {
            if (current.getLeft() != null) {
                current = current.getLeft();
            } else if (current.getRight() != null) {
                current = current.getRight();
            }
        }
        return current.getString();
    }

    //goes as far left or right as possible, correlating to the strings 1's and 0's
    //once it hits a leaf, leaf is returned, otherwise next node is returned
    public static String search(Trie t, String st) {
        TrieNode current = t.getRoot();
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '0') {
                if (current.getLeft() == null) {
                    while (!current.isLeaf()) {
                        if (current.getLeft() != null) {
                            current = current.getLeft();
                        } else if (current.getRight() != null) {
                            current = current.getRight();
                        }
                    }
                    return current.getString();
                }

                if (current.getLeft().isLeaf()) {
                    return current.getLeft().getString();
                } else {
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else if (current.getRight() != null) {
                        current = current.getRight();
                    }
                }
            } else if (st.charAt(i) == '1') {
                if (current.getRight() == null) {
                    while (!current.isLeaf()) {
                        if (current.getLeft() != null) {
                            current = current.getLeft();
                        } else if (current.getRight() != null) {
                            current = current.getRight();
                        }
                    }
                    return current.getString();
                }

                if (current.getRight().isLeaf()) {
                    return current.getRight().getString();
                } else {
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } else if (current.getLeft() != null) {
                        current = current.getLeft();
                    }
                }
            }
        }
        return "error";
    }

    //helper method that calls count() which uses recursion with nodes
    public static int size(Trie t) {
        count = 0;
        return count(t.getRoot());
    }

    //count traverse the tree, and everytime it reaches a leaf, it adds 1 to the count and returns the count
    public static int count(TrieNode n) {
        if(n == null)
            return 0;
        if(n.isLeaf())
        {
            count++;
        } else {
            if (n.getLeft() != null) {
                count(n.getLeft());
            }
            if (n.getRight() !=null) {
                count(n.getRight());
            }
        }
        return count;
    }

    //helper method so that I could use recursion while traversing the tree
    public static int height(Trie t) {
        height = 1;
        return maxDepth(t.getRoot());
    }

    //traverse through the tree, everytime current moves left or right, height increases by one.
    //once a leaf is reached, that specified height number is stored in a list
    public static int maxDepth(TrieNode n) {
        if(n == null)
            return height;
        if(n.isLeaf())
        {
            heights.add(height);
            height = 1;
            //when I set to 0, I mess up traversal, not going back to root
            //or add 1 to height bc its number of nodes not connections idk
        } else {
            if (n.getLeft() != null) {
                height++;
                maxDepth(n.getLeft());
            }
            if (n.getRight() !=null) {
                height++;
                maxDepth(n.getRight());
            }
        }
        //iterates through list and finds max depth of the tree and returns it as the height.
        int max = heights.get(0);
        for (int i = 0; i < heights.size(); i++) {
            if (heights.get(i) > max) {
                max = heights.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        //creates new trie, which is currently empty
        Trie t = new Trie();
        try {
            File file = new File(args[0]);
            Scanner scnr = new Scanner(file);
            while (scnr.hasNext()) {
                //scans the command string and goes to specified case
                String data = scnr.next();
                switch (data) {
                    case "insert":
                        //scans next string which should be the string trying to insert
                        String str = scnr.next();
                        insert(t, str);
                        break;
                    case "print":
                        trieToList(t);
                        break;
                    case "largest":
                        System.out.println(largest(t));
                        break;
                    case "smallest":
                        System.out.println(smallest(t));
                        break;
                    case "search":
                        //scans for string that is being searched
                        String str2 = scnr.next();
                        System.out.println(search(t, str2));
                        break;
                    case "size":
                        System.out.println(size(t));
                        break;
                    case "height":
                        System.out.println(height(t));
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

