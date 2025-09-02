# Suffix Tree
## what is it?
A suffix tree is a compressed trie of all suffixes of a given string.

For a string S of length n, it allows:
- fast pattern searching
- substring queries
- longest repeated substring
- long common substring(with multiple strings)

## core idea
let's say the string is: banana$($ is a unique end marker)

All suffixes:
```
banana$
anana$
nana$
ana$
na$
a$
$
```
A suffix tree stores all these suffixes in a compressed tree structure where each edge is labeled with a substring.
```
         (root)
        /  |   \
     a$  ana$  ...
     |    |
     $   na$ ...
         |
        $ ...
```

### suffix tree properties
- has n leaves for a string of length n
- each leaf represents a suffix
- no two edges out of the same node begin with the same character
- paths from root to leaf represent suffixes of the string

- NA: 1043
- EU: 291
- FE: 177
- CN: 116