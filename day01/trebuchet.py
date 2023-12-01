
file = open("day01-data.txt", "r")
data = file.read()
data_as_list = data.split("\n")


def find_and_add_digits_in_strings(array):
    numbersSum = 0
    for string in array:
        firstDigit = 0
        lastDigit = 0
        firstDigitFound = False

        for i in string:
            if i.isnumeric():
                lastDigit = i
                if not firstDigitFound:
                    firstDigit = i
                    firstDigitFound = True

        numbersInString = int(str(firstDigit) + str(lastDigit))
        numbersSum += numbersInString

    return numbersSum


if __name__ == '__main__':
    print(find_and_add_digits_in_strings(data_as_list))

