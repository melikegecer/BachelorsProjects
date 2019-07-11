import re
#
# Write a python code to read the file into a list where each line is an element in the list.
# You should use ‘utf-8’ as the encoding.
#
# Create empty list.
lines = []
# Open txt and read it.
with open("train.txt", "r", encoding="utf-8") as file:
    # Get each line and add to list.
    for line in file:
        line = line.strip()
        lines.append(line)
#
# Write the code using RegEx to find and print plural words.
#
# We are looking for plural words. In Turkish, plural words should have -ler / -lar suffixes. Therefore we should check
# the words which have -ler / -lar suffixes in it. Some words like -olarak- is not plural, eventhough it has -lar in it.
# But we need lexicon to get a word's meaning. In this homework we do not have lexicon, therefore this regular expression
# likely to find  words like -olarak- which do not support (ler|lar) regular expression.
print("List of plural words:")
for line in lines:
    wordList = re.sub("[^\w]", " ", line).split()
    regularExpression = re.compile("(ler|lar)")
    for word in wordList:
        if regularExpression.search(word):
            print(word)
#
#
#
print("\n\n\n")
#
#
#
# Write the code to find and proper nouns. Note that sentences start with uppercase letters
# but the first word may not be a proper name.
#
# We are looking for proper nouns. Proper nouns always starts with an uppercase letter and have lowercase letters
# afterwards (at least one lowercase letter). Since the very first word of a sentence should start with an uppercase
# letter, we should ignore the first word of each sentence. Again, we do not have word database for this homework.
# Therefore if a proper noun is at the beginning of a sentence, we will not be able see those words.
print("List of proper nouns:")
for line in lines:
    wordList = re.sub("[^\w]", " ", line).split()
    regularExpression = re.compile("\\b[A-Z][a-z]+")
    index = 0
    for word in wordList:
        # Since each line is a sentence, first element of list is first word of sentence
        # and we should ignore first word of each sentences.
        if index != 0:
            if regularExpression.search(word):
                print(word)
        index += 1
#
#
#
print("\n\n\n")
#
#
#
# Write the code to find the average word length.
words = []
wordLengthSum = 0
for line in lines:
    wordList = re.sub("[^\w]", " ", line).split()
    for word in wordList:
        wordLengthSum += len(word)
        words.append(word)
print("average length of words is " + str("%.2f" % round(wordLengthSum / len(words), 2)))
