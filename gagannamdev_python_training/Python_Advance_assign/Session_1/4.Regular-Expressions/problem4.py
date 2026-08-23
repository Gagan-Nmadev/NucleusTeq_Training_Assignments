import re

# Q4. Use re.search() to check whether a word exists in a sentence.

print("Q4. Use re.search() to check whether a word exists in a sentence.")
word = "GTA"
sentence = "I love my GTA 5"

if re.search(word, sentence):
    print("Word Found")
else:
    print("Word Not Found")