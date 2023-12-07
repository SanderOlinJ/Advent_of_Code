#ifndef DAY07_CAMEL_CARDS_01_H
#define DAY07_CAMEL_CARDS_01_H

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

std::vector<Hand> readFromFilePartOne(const std::string &filename);
Rank classifyRankOfHandPartOne(const std::string &stringHand);
int getValueOfCardPartOne(char card);
int solvePuzzlePartOne(const std::string &fileName);

#endif //DAY07_CAMEL_CARDS_01_H
