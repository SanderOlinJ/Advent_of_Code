#include "gear_ratios_01.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
using namespace std;

vector<vector<Number>> numbersPartOne;
vector<vector<SpecialCharacter>> specialCharactersPartOne;

void readFromFilePartOne(const string &filename) {
    ifstream file(filename);
    string line;
    int row = 0;

    while (getline(file, line)) {
        std::stringstream ss(line);
        char ch;
        int num, col = 0;
        numbersPartOne.resize(row + 1);
        specialCharactersPartOne.resize(row + 1);

        while (ss >> ch) {
            if (isdigit(ch)) {
                int startCol = col;
                ss.putback(ch);
                ss >> num;
                int length = int(to_string(num).length()) - 1;
                numbersPartOne[row].push_back({num, length, {row, startCol}});
                col += length;
            } else if (!isspace(ch)) {
                if (ch != '.') {
                    specialCharactersPartOne[row].push_back({row, col});
                }
                numbersPartOne[row].push_back({0, 0, {row, col}});
            }
            col++;
        }
        row++;
    }
}

int solvePuzzlePartOne() {
    readFromFilePartOne("data.txt");
    int sum = 0;
    for (const auto& rowSpecialChars : specialCharactersPartOne) {
        for (const SpecialCharacter& sc : rowSpecialChars) {
            for (int i = max(0, sc.position.row - 1); i <= min((int)numbersPartOne.size() - 1, sc.position.row + 1); i++) {
                for (const Number &num : numbersPartOne[i]) {
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
