# Read about Turkish vowel harmony. Implement a method that takes in a word parameter
# and return true if the word has vowel harmony. You need to implement only main harmony.
# Use your method on the corpus associated with the previous assignment. Count the words
# that have vowel harmony and those that do not.
#
# Some terms that I used
# back vowel: kalın ünlü; a, ı, o, u
# compound word: birleşik kelime
# front vowel: ince ünlü; e, i, ö, ü
# monosyllable: tek heceli
#
# In Turkish, a word supports main harmony if the word's vowels are all back vowels or front
# vowels, such as alın (back vowels) or isim (front vowels). In some cases we do not
# check if a word satisfies main harmony or not. Therefore, I will have three different
# cases and I changed my method return value type from boolean to integer. The checkHarmony
# method will return an integer value;
#   -1 for a word which does not support main harmony
#    0 for a word which should not be checked if it satisfies main harmony or not
#    1 for a word which supports main harmony
#    2 for a word which does not have any vowels at all, possibly abbreviation like TBMM
#
# According to TDK (Türk Dil Kurumu, Turkish Language Institution), a word should not
# be checked for main harmony when it is monosyllable or when it is a compound word
# (such as bilgi-sayar). We do not have any corpus which can help us to identify
# compound words, for this homework. So, I will check if a word is monosyllable or not and
# main harmony rule.
#
def checkHarmony(word):
    countBackVowel = 0
    countFrontVowel = 0
    for s in word:
        if ('a' in s) or ('ı' in s) or ('o' in s) or ('u' in s):
            countBackVowel += 1
        elif ('e' in s) or ('i' in s) or ('ö' in s) or ('ü' in s):
            countFrontVowel += 1
        # if there is more than one back vowels and front vowels
        # then it does not support main harmony
        if countFrontVowel > 0 and countBackVowel > 0:
            return -1
    # Checking if the word is monosyllable
    # if word has only one vowel than it is monosyllable
    if countBackVowel == 1 ^ countFrontVowel == 1:
        return 0
    if countBackVowel == 0 and countFrontVowel == 0:
        return 2
    return 1
#
#
# Read text file from previous homework.
# Each line is recorded into list as an element.
import re
lines = []
wordList = []
with open("train.txt", "r", encoding="utf-8") as file:
    for lineOfFile in file:
        lineOfFile = lineOfFile.strip()
        lines.append(lineOfFile)
#
# Split each line by using spaces and add them to a word list.
for line in lines:
    words = re.sub("[^\w]", " ", line).split()
    for word in words:
        wordList.append(word)
#
countNoSupport = 0
countNotCheck = 0
countSupport = 0
countNoVowels = 0
for word in wordList:
    if checkHarmony(word) == -1:
        countNoSupport += 1
    if checkHarmony(word) == 0:
        countNotCheck += 1
    if checkHarmony(word) == 1:
        countSupport += 1
    if checkHarmony(word) == 2:
        countNoVowels += 1
#
print(str(countSupport) + ' words support vowel harmony. ')
print(str(countNoSupport) + ' words do not support vowel harmony.')
print(str(countNotCheck) + ' words did not check for main harmony, because they are monosyllable.')
print(str(countNoVowels) + ' words do not contain any vowels.')
print(str(len(wordList)) + ' words in total.')
