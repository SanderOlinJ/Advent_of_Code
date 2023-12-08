#ifndef DAY08_HAUNTED_WASTELAND_01_H
#define DAY08_HAUNTED_WASTELAND_01_H

#include <string>
#include <fstream>
#include <sstream>
#include <iostream>
#include <unordered_map>
#include "common_structs.h"

using namespace std;

pair<string, unordered_map<string, Node>> readFromFilePartOne(const string &filename);
int solvePuzzlePartOne(const string &filename);

#endif //DAY08_HAUNTED_WASTELAND_01_H
