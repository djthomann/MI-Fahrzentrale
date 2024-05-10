import os
dirname = os.path.dirname(__file__)
filepath = os.path.join(dirname, 'src/main/resources/')
# print(filepath)

csv = open("./uebersetzungen.csv", "r")

firstLine = csv.readline()

# print(firstLine)

languages = firstLine.strip().split(",")
languages.pop(0)
# print(languages)

csv.readline()

lines = csv.readlines()

messages = {}
for language in languages:
    messages[language] = []

for line in lines:
    if line.startswith("#") or line.startswith(","):
        continue
    line = line.strip()
    if line:
        parts = line.split(",")
        for count, language in enumerate(languages):
            messages[language].append((parts[0], parts[count + 1]))

# print(messages["de"])

for i, language in enumerate(languages):
    if i == 0:
        f1 = open(filepath + "messages_{}.properties".format(language), "x")
        f2 = open(filepath + "messages.properties", "x")
        pairs = messages[language]
        for pair in pairs:
            f1.write(pair[0] + "=" + pair[1] + "\n")  
            f2.write(pair[0] + "=" + pair[1] + "\n")  
        f1.close()
        f2.close()
    else: 
        f = open(filepath + "messages_{}.properties".format(language), "x")
        pairs = messages[language]
        for pair in pairs:
            f.write(pair[0] + "=" + pair[1] + "\n")  
        
        f.close()
    