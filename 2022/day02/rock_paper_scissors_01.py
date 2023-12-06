with open("data.txt", "r") as f:
    score = 0
    for line in f:
        score += (((ord(line[2]))%ord(line[0])%3+5)%3)*3 + ord(line[2])%4 + 1
    print(score)