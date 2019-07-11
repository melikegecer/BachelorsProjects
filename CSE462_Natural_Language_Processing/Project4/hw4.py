# For this homework, use the training and test corpora of the project. You will design a simple POS tagger. Note the
# format format of the corpus. Each word is on a new line. There are empty lines between the sentences. In both corpora,
# the second column in each line is the POS tag. Write a function to read the training corpus and return a dictionary
# where each key is the word and the value is the most common POS tag. Use this dictionary to tag the words in the test
# corpus. If a word is not found in your dictionary use the most common tag. Calculate your accuracy.
#
#
# At first I considered to exclude punctuations, but then I realized that punctuations have tags like words.
# Therefore I included punctuations in dictionary. Surprisingly, I saw that most common tag for train.txt is (,)-comma.
# So, any word which is in test.txt but not in train.txt will be tagged as (,)-comma.
def getDictionary():
    # I need most common tag of dictionary to tag words in test.txt that are not in dictionary
    maxCountAll = 0
    maxTagAll = ""
    # I will keep words and their tags in dictionary
    # {word: {tag1: count1, tag2: count2}}
    # my format for word dictionary will be like I write above
    wordAndTagsDictionary = {}
    # read train.txt file
    with open("train.txt", "r", encoding="utf-8") as file:
        for lineOfFile in file:
            # get line
            lineOfFile = lineOfFile.strip()
            # split line using space
            wordAndTag = lineOfFile.split(" ")
            # first part is the word
            word = wordAndTag[0]
            # second part is the POS tag for that word
            tag = wordAndTag[1]
            # check if a word is in dictionary
            if word in wordAndTagsDictionary:
                # check if tag is in word's tag dictionary
                if tag in wordAndTagsDictionary[word]:
                    # increase that tag's count
                    wordAndTagsDictionary[word][tag] += 1
                else:
                    # add tag with count 1
                    wordAndTagsDictionary[word][tag] = 1
            else:
                # add word and its tag with count 1
                wordAndTagsDictionary[word] = {}
                wordAndTagsDictionary[word][tag] = 1
    # now I should find most common tag for each word
    # I should return result dictionary which has words as key and most common tag's of a word as value
    result = {}
    # get each word in dictionary
    for eachWord in wordAndTagsDictionary:
        maxCount = 0
        max
        # get each word's tag dictionary
        for eachTag in wordAndTagsDictionary[eachWord]:
            # if max is smaller than that tag's count
            if maxCount < wordAndTagsDictionary[eachWord][eachTag]:
                # set new max
                maxCount = wordAndTagsDictionary[eachWord][eachTag]
                # set max tag for that word
                maxTag = eachTag
            # also check maxCountAll
            if maxCountAll < wordAndTagsDictionary[eachWord][eachTag]:
                # set new max all
                maxCountAll = wordAndTagsDictionary[eachWord][eachTag]
                # set max tag all
                maxTagAll = eachTag
        result[eachWord] = maxTag
    # since we need dictionary of words, most common tag
    return result, maxTagAll
#
#
# read test.txt file
with open("test.txt", "r", encoding="utf-8") as file:
    # get dictionary produced by using train.txt file
    wordDictionary = getDictionary()[0]
    # get max tag in dictionary
    maxTag = getDictionary()[1]
    # to calculate accuracy we will check tags of test file and tags from wordDictionary
    countTrueTags = 0
    countFalseTags = 0
    for lineOfFile in file:
        # get line
        lineOfFile = lineOfFile.strip()
        # split line using space
        wordAndTag = lineOfFile.split(" ")
        # first part is the word
        word = wordAndTag[0]
        # second part is the POS tag for that word
        tag = wordAndTag[1]
        # tag the word according to dictionary I got from getDictionary()
        # check if word in dictionary
        if word in wordDictionary:
            trainTag = wordDictionary[word]
        else:
            trainTag = maxTag
        # compare tags
        if tag == trainTag:
            countTrueTags += 1
        else:
            countFalseTags += 1
    print(str(countTrueTags) + " words tagged correctly.")
    print(str(countFalseTags) + " words tagged wrong.")
    print("There are " + str(countTrueTags+countFalseTags) + " words.")
    accuracy = countTrueTags/(countTrueTags+countFalseTags)*100
    print("Accuracy -> " + str("%.2f" % accuracy) + "%")
