#ifndef DAY07_CAMEL_CARDS_02_H
#define DAY07_CAMEL_CARDS_02_H

#include "common_structs.h"
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <map>

std::vector<Hand> readFromFilePartTwo(const std::string &filename);
Rank classifyRankOfHandPartTwo(const std::string &stringHand);
int getValueOfCardPartTwo(char card);
int solvePuzzlePartTwo(const std::string &fileName);

#endif //DAY07_CAMEL_CARDS_02_H
