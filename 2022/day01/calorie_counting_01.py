with open("data.txt", "r") as f:
    mostCalories = 0
    currentCalories = 0
    for line in f:
        if line[0].isnumeric():
            currentCalories+=int(line)
        else:
            mostCalories = max(mostCalories, currentCalories)
            currentCalories = 0
    mostCalories = max(mostCalories, currentCalories)
    print(mostCalories)
