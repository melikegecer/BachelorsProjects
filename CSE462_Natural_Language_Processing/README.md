# CSE 462 - Natural Language Processing
Technologies used; Python, PyCharm Edu
##### Project1
Write the code using RegEx to find and print plural words. We are looking for plural words. In Turkish, plural words should have -ler / -lar suffixes. Therefore we should check the words which have -ler / -lar suffixes in it. Some words like -olarak- is not plural, eventhough it has -lar in it. But we need lexicon to get a word's meaning. In this homework we do not have lexicon, therefore this regular expression likely to find  words like -olarak- which do not support (ler|lar) regular expression.
<br>

##### Project2
Read about Turkish vowel harmony. Implement a method that takes in a word parameter and return true if the word has vowel harmony. You need to implement only main harmony. Use your method on the corpus associated with the previous assignment. Count the words that have vowel harmony and those that do not.
<br>

##### Project3
For this homework, use the training corpus of the project. You will calculate the bigram probabilities using this corpus. Note the format format of the corpus. Each sentence is on a new line. Calculate the bigram probabilities and save them on a file in the format.
<br>

##### Project4
For this homework, use the training and test corpora of the project. You will design a simple POS tagger. Note the format format of the corpus. Each word is on a new line. There are empty lines between the sentences. In both corpora, the second column in each line is the POS tag. Write a function to read the training corpus and return a dictionary where each key is the word and the value is the most common POS tag. Use this dictionary to tag the words in the test corpus. If a word is not found in your dictionary use the most common tag. Calculate your accuracy.
<br>

##### Project5
This homework explores the similarity of word meaning in terms of the similarity of their contexts.
1. Download the corpus for this homework from Course Online. It is a long text file where each line contains the root of the actual word. So the original corpus were morphologically analized and disambiguated. The sentences are separated by blank lines as usual.
2. Find the a list of stopwords by doing a frequency analysis and finding the most frequent 50 words in the corpus.
3. We define the context of a word as the 4 words around it, 2 words to the left and two word to the right. Context does not cross sentence boundaries.
4. Construct a dictionary where a key is a word in the corpus and its value is the list of words appearing in its contexts. Exclude the stopwords.
5. For each pair of words (w1;w2), calculate the similarity s(w1;w2) as s(w1;w2) = #common words/sum of context sizes 
6. List the most similar 20 pairs.
<br>
