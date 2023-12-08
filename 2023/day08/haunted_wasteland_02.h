#ifndef DAY08_HAUNTED_WASTELAND_02_H
#define DAY08_HAUNTED_WASTELAND_02_H

#include <string>
#include <vector>
#include <unordered_map>
#include <fstream>
#include <sstream>
#include <numeric>
#include "common_structs.h"

using namespace std;

tuple<vector<string>, string, unordered_map<string, Node>> readFromFilePartTwo(const string &filename);
long long int solvePuzzlePartTwo(const string &filename);

#endif //DAY08_HAUNTED_WASTELAND_02_H
