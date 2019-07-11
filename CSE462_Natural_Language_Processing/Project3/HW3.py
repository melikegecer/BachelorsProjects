# For this homework, use the training corpus of the project. You will calculate the bigram probabilities using this
# corpus. Note the format format of the corpus. Each sentence is on a new line. Calculate the bigram probabilities
# and save them on a file in the format.
# …
# the man 0.0034
# the tree 0.00012
# …
#
# read text file and create a list where each sentence is an element
import re
lines = []
wordList = []
pairOccurrence = {}
wordOccurrence = {}
with open("hw3_corpus.txt", "r", encoding="utf-8") as file:
    for lineOfFile in file:
        lineOfFile = lineOfFile.strip()
        lines.append(lineOfFile)
#
# initialize occurrence of ^(circumflex) and *(asterisk)
wordOccurrence['*'] = 0
wordOccurrence['^'] = 0
# Split each line by using spaces and add them to a word list.
for line in lines:
    words = re.sub("[^\w]", " ", line).split()
    # asterisk symbol represents the start of the sentence
    wordList.append("*")
    wordOccurrence['*'] += 1
    # while adding each word into a list, count their occurrence
    for word in words:
        wordList.append(word)
        # if the word exist increase its count by one
        if word in wordOccurrence:
            wordOccurrence[word] += 1
        # if word does not exist, initialize its occurrence with one
        else:
            wordOccurrence[word] = 1
    # circumflex symbol represents the start of the sentence
    wordList.append("^")
    wordOccurrence['^'] += 1
#
# calculate (word1,word2) occurrence
# I used space for separator
for i in range(0, len(wordList)-1):
    # since ^(circumflex) represents end of sentence, we do not check (^ + something).
    # therefore I skipped it.
    if wordList[i] is not "^":
        pair = wordList[i] + " " + wordList[i+1]
        if pair in pairOccurrence:
            pairOccurrence[pair] += 1
        else:
            pairOccurrence[pair] = 1
#
# get keys of pairOccurrence as list
keyList = []
for key in pairOccurrence:
    keyList.append(key)
wordProbability = {}
#
for i in range(0, len(pairOccurrence)):
    # format is (word1,word2) so split them
    word12 = keyList[i].split(" ")
    word1 = word12[0]
    word2 = word12[1]
    # calculate probability (num. of (word1,word2))/(num. of word1)
    wordProbability[keyList[i]] = pairOccurrence[keyList[i]] / wordOccurrence[word1]
#
# save to file
probKeyList = []
for key in wordProbability:
    probKeyList.append(key)
#
fi = open("LanguageModel.txt", "w", encoding="utf-8")
for i in range(0, len(wordProbability)):
    fi.write(probKeyList[i] + " " + str(wordProbability[probKeyList[i]]) + "\n")
fi.close()
