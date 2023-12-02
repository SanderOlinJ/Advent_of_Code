testData = ["1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"]
file = open("data.txt", "r")
data = file.read()
data_as_list = data.split("\n")


def solve_puzzle(array):
    totalSum = 0
    for string in array:
        digits = []
        for char in string:
            if char.isdigit():
                digits.append(char)
        if len(digits) > 0:
            totalSum += int(digits[0] + digits[-1])
    print(totalSum)


if __name__ == '__main__':
    print(solve_puzzle(data_as_list))
