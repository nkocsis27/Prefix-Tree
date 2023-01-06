# Prefix-Tree
Creation of a prefix-tree or trie data structure. Includes list of commands to manipulate the tree such as  insert, search, height, smallest and more. 

insert(trie, st): inserts a string st into a non-empty trie trie. Returns either true or false indicating whether or not the insertion is successful.

trieToList(trie) creates a list of strings in trie in increasing lexicographic order. You are not allowed to use any kind of sort methods to sort the list. You may
consider using the List ADT implemented for previous coding assignments.
3. largest( trie ) returns the largest string in lexicographic order from the set of
strings stored in trie. You can assume that trie is not empty.
4. smallest( trie ) returns the smallest string in lexicographic order from the set
of strings stored in trie. You may assume that trie is not empty.
5. search( trie, st ) returns the string in trie that has the longest (and closest)
prefix match with st as described in Section 1.2. You may assume that trie is not
empty.
6. size( trie ) returns the number of strings stored in the trie.
7. height( trie ) returns the height of the trie
