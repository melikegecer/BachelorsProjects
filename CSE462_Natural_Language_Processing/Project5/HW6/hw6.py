# I used this library to sort a dictionary
import operator

#  This homework explores the similarity of word meaning in terms of the similarity of their contexts.
#  1. Download the corpus for this homework from Course Online. It is a long text file where each line contains the root
#     of the actual word. So the original corpus were morphologically analized and disambiguated. The sentences are
#     separated by blank lines as usual.
#  2. Find the a list of stopwords by doing a frequency analysis and finding the most frequent 50 words in the corpus.
#  3. We define the context of a word as the 4 words around it, 2 words to the left and two word to the right. Context
#     does not cross sentence boundaries.
#  4. Construct a dictionary where a key is a word in the corpus and its value is the list of words appearing in its
#     contexts. Exclude the stopwords.
#  5. For each pair of words (w1;w2), calculate the similarity s(w1;w2) as
#     s(w1;w2) = #common words/sum of context sizes
#  6. List the most similar 20 pairs.
#
#
# let us keep all words in wordList and count value for each word in wordCountDictionary
wordList = []
wordCountDictionary = {}
# read corpus
with open("hw6_corpus", "r", encoding="utf-8") as file:
    # for each line in file
    for lineOfFile in file:
        # get line
        word = lineOfFile.strip()
        # add the word to the wordList
        wordList.append(word)
        # if word exist in dictionary increase its count
        if word in wordCountDictionary:
            wordCountDictionary[word] += 1
        # if word does not exist in dictionary add it with count value 1
        else:
            wordCountDictionary[word] = 1
# let us sort the wordCountDictionary to identify most common words
sortedWordCountDictionary = sorted(wordCountDictionary.items(), key=operator.itemgetter(1))
# finding stop words, the library I used to sort the dictionary sorted in increasing order
# therefore to find most frequent 50 words (assuming that they are our stop words) I need to get last 50 words
fromIndex = len(sortedWordCountDictionary) - 50
toIndex = len(sortedWordCountDictionary)
wordsToBeDeleted = []
for i in range(fromIndex, toIndex):
    wordsToBeDeleted.append(sortedWordCountDictionary[i])
# remove them from wordList
newWordList = []
for word in wordList:
    if word not in wordsToBeDeleted:
        newWordList.append(word)
# construct a dictionary for context of words
# (exclude first 2 words and last 2 words since they do not have 2 word on the left or on the right )
contextDictionary = {}
contextStart = 2
contextEnd = len(newWordList) - 2
keyList = []
for i in range(contextStart, contextEnd):
    keyList.append(newWordList[i])
    contextDictionary[newWordList[i]] = [newWordList[i - 2], newWordList[i - 1], newWordList[i + 1], newWordList[i + 2]]
# calculate similarity
# check two words' common words in context
similarityEnd = len(contextDictionary) - 1
similarityList = {}
for i in range(similarityEnd):
    # get words
    key1 = keyList[i]
    key2 = keyList[i+1]
    wordOne = contextDictionary[key1]
    wordTwo = contextDictionary[key2]
    count = 0
    # calculate common words
    for word1 in wordOne:
        for word2 in wordTwo:
            if word1 == word2:
                count += 1
    # calculate and save similarity
    similarityList[word1 + " " + word2] = count / 8
sortedSimilarityList = sorted(similarityList.items(), key=operator.itemgetter(1))
# print most 20 similar words
result = {}
end = len(sortedSimilarityList)
for i in reversed(range(end - 21, end)):
    print(sortedSimilarityList[i])
