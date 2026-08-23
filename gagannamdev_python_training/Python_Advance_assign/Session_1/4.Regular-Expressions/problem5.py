import re

# Q5. Use re.findall() to extract all words starting with a capital letter.
print("Q5. Use re.findall() to extract all words starting with a capital letter.")
sentence = "Hey guys, I love MY college and also love Friends"

answer5 = re.findall(r"[A-Z]\w*", sentence)

print(answer5)