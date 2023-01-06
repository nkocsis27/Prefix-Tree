# Prefix-Tree
Creation of a prefix-tree or trie data structure. Includes list of commands to manipulate the tree such as  insert, search, height, smallest and more. 

Lab 5 was about creating a trie tree, but with only 1's and 0's as branches. My main reads the file one word at a time, and processing that word in a switch statement which may lead the scanner to scan for additional information. I assumed commands would be given correctly, so there is no checks for missing data when the user uses the search command. The switch statement will call the method correlating to that sepcific command. 

The first method insert() is the longest as it builds the trie tree. The first thing we do is check if the root of the tree is null, and if it is, we set the root to that specificed string and create a new node. Then I iterate through each character of the string to decide which direction of the tree to go, left or right. If the current is an interanl node and the direction I'm trying to go in is null, I create a new node for that spot. Otherwise, I traverse until I hit a leaf. Once I hit a leaf, I compare the two strings and build a path of internal nodes until the two strings differ, at which I place the two strings at their specified spot. 

trieToList() is mainly a helper method which calls printList(). printList() goes through the tree until a leaf is hit, at which point that string is added to the list. Then, when the method returns, trieToList() prints all the elements in the list. 

largest() method goes as far right as possible in the tree, until it hits a leaf. Only time this method goes left is if right equals null.

smallest() does the same concept at largest, but left is prioritized. 

The search() method goes through each character of the string being searched and checks that direction. If it is a leaf, that string is returned. If it is not a leaf but an internal node, current is set to that direction and the traversal continues. If the search stops at an internal node, it picks the closest leaf node and returns that string.

I use size() method as a helper method for count(), which counts the number of leaf nodes in the trie. 

height() is a helper method for maxDepth, which keeps track of all the leaf node's depth and then finds the max to return the height of the tree. The height is based on NODES not connections. 

*****
COMMANDS
insert(trie, st): inserts a string st into a non-empty trie trie. Returns either true or false indicating whether or not the insertion is successful.

trieToList(trie): creates a list of strings in trie in increasing lexicographic order. (traversal of tree not a sorting algorithm)

largest(trie): returns the largest string in lexicographic order from the set of
strings stored in trie. You can assume that trie is not empty.

smallest(trie): returns the smallest string in lexicographic order from the set
of strings stored in trie. You may assume that trie is not empty.

search(trie, st): returns the string in trie that has the longest (and closest)
prefix match with st. You may assume that trie is not empty.

size(trie): returns the number of strings stored in the trie.

height(trie): returns the height of the trie
