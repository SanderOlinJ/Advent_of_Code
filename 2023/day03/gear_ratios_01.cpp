#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

struct Position {
    int row, col;
};

struct Number {
    int value, length;
    Position position;
};

struct SpecialCharacter {
    Position position;
};

vector<vector<Number>> numbers;
vector<vector<SpecialCharacter>> specialCharacters;

void readFromFile(const string &filename) {
    ifstream file(filename);
    string line;
    int row = 0;

    while (getline(file, line)) {
        std::stringstream ss(line);
        char ch;
        int num, col = 0;
        numbers.resize(row + 1);
        specialCharacters.resize(row + 1);

        while (ss >> ch) {
            if (isdigit(ch)) {
                int startCol = col;
                ss.putback(ch);
                ss >> num;
                int length = int(to_string(num).length()) - 1;
                numbers[row].push_back({num, length, {row, startCol}});
                col += length;
            } else if (!isspace(ch)) {
                if (ch != '.') {
                    specialCharacters[row].push_back({row, col});
                }
                numbers[row].push_back({0, 0, {row, col}});
            }
            col++;
        }
        row++;
    }
}

int solvePuzzle() {
    int sum = 0;
    for (const auto& rowSpecialChars : specialCharacters) {
        for (const SpecialCharacter& sc : rowSpecialChars) {
            for (int i = max(0, sc.position.row - 1); i <= min((int)numbers.size() - 1, sc.position.row + 1); i++) {
                for (const Number &num : numbers[i]) {
                    if (num.value == 0) continue;
                    if (abs((num.position.col + num.length) - sc.position.col) > 1
                        && abs(num.position.col - sc.position.col) > 1) {
                        continue;
                    }
                    sum += num.value;
                }
            }
        }
    }
    return sum;
}


int main() {
    readFromFile("data.txt");
    cout << solvePuzzle();
    return 0;
}