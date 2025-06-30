# Trie
## what is it?
A Trie is a tree-like data structure used to efficiently store and retrieve strings, especially useful for prefix-based
operations like autocomplete, spell-checking, and word validation.

## Core idea
- Each node in a Trie represents a single character
- Paths from the root to leaf nodes represent words or strings

## How it works?
Storing ["cat", "can", "cap"]
```
        (root)
         /  
        c
       / 
      a
    / | \
   t  n  p
```
- Path c -> a -> t = "cat"
- Path c -> a -> n = "can"
- Path c -> a -> p = "cap"

A typical TrieNode contains:
- a map or array of children `Map<Character, TrieNode> or TrieNode[26]`
- a boolean flag indicating if the node marks the end of a word

## When to use it?
- Autocomplete
  - Quickly find all words with a prefix
- Spell checking
  - Fast existence check for a dictionary
- IP routing(binary trie)
  - Match longest prefix of a binary string

## When NOT to use it?
- When we are space constrained
  - Trie has high space usage(especially so for sparse alphabets)
- Small datasets
  - Not optimal for storage and search when the data sets are small
- When we need fast search with exact matches
  - Slower than hash tables in this usage