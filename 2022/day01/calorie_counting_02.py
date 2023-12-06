with open("data.txt", "r") as f:
    calories = []
    currentCalories = 0
    for line in f:
        if line[0].isnumeric():
            currentCalories+=int(line)
        else:
            calories.append(currentCalories)
            currentCalories = 0
    calories.append(currentCalories)
    calories.sort(reverse=True)
    print(sum(calories[:3]))
