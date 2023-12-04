#include "gear_ratios_02.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector<vector<Number>> numbersPartTwo;
vector<vector<SpecialCharacter>> specialCharactersPartTwo;

void readFromFilePartTwo(const string &filename) {
    ifstream file(filename);
    string line;
    int row = 0;

    while (getline(file, line)) {
        std::stringstream ss(line);
        char ch;
        int num, col = 0;
        numbersPartTwo.resize(row + 1);
        specialCharactersPartTwo.resize(row + 1);

        while (ss >> ch) {
            if (isdigit(ch)) {
                int startCol = col;
                ss.putback(ch);
                ss >> num;
                int length = int(to_string(num).length()) - 1;
                numbersPartTwo[row].push_back({num, length, {row, startCol}});
                col += length;
            } else if (!isspace(ch)) {
                if (ch == '*') {
                    specialCharactersPartTwo[row].push_back({row, col});
                }
                numbersPartTwo[row].push_back({0, 0, {row, col}});
            }
            col++;
        }
        row++;
    }
}

int solvePuzzlePartTwo() {
    readFromFilePartTwo("data.txt");
    int sum = 0;
    for (const auto& rowSpecialChars : specialCharactersPartTwo) {
        for (const SpecialCharacter& sc : rowSpecialChars) {
            int numberOfAdjacentNumbers = 0;
            int productOfAdjacentNumbers = 1;
            for (int i = max(0, sc.position.row - 1);
                i <= min((int)numbersPartTwo.size() - 1, sc.position.row + 1); i++) {
                for (const Number &num : numbersPartTwo[i]) {
                    if (num.value == 0) continue;
                    int endCol = num.position.col + num.length;
                    bool isAdjacent = abs(endCol - sc.position.col) <= 1
                            || abs(num.position.col - sc.position.col) <= 1;

                    if (isAdjacent) {
                        numberOfAdjacentNumbers++;
                        productOfAdjacentNumbers *= num.value;
                    }
                }
            }
            sum += (numberOfAdjacentNumbers == 2) ? productOfAdjacentNumbers : 0;

        }
    }
    return sum;
}
