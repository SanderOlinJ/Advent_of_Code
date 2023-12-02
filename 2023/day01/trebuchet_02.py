from word2number import w2n

file = open("data.txt", "r")
data = file.read()
data_as_list = data.split("\n")

numberDictionary = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]


def solve_puzzle(array):
    totalSum = 0
    for string in array:
        print(string)
        digits = []
        index = 0
        for char in range(0, len(string)):
            if string[char].isdigit():
                pair = (string[char], index)
                digits.append(pair)
            index += 1

        newDigits = []
        if len(digits) > 0:
            newDigits = [digits[0], digits[-1]]

        for numberAsText in numberDictionary:
            firstOccurrence = string.find(numberAsText)
            lastOccurrence = string.rfind(numberAsText)
            number = w2n.word_to_num(numberAsText)

            if firstOccurrence == -1:
                continue

            if len(newDigits) == 0:
                newDigits = [(str(number), firstOccurrence), (str(number), lastOccurrence)]

            else:
                if newDigits[0][1] > firstOccurrence:
                    newDigits[0] = (str(number), firstOccurrence)
                if newDigits[-1][1] < lastOccurrence:
                    newDigits[-1] = (str(number), lastOccurrence)

        totalSum += int(str(newDigits[0][0]) + str(newDigits[-1][0]))
    return totalSum


if __name__ == '__main__':
    print(solve_puzzle(data_as_list))
