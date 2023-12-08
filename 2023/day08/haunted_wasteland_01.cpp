#include "haunted_wasteland_01.h"

pair<string, unordered_map<string, Node>> readFromFilePartOne(const string &filename){
    ifstream file(filename);
    string line, instructions;
    unordered_map<string, Node> nodes;
    getline(file, instructions);

    while (getline(file, line)) {
        istringstream ss(line);
        string position, left, right;
        char temp;
        if (!(ss >> position >> temp >> temp >> left >> right)) continue;
        left.pop_back();
        right.pop_back();

        nodes[position] = {left, right};
    }
    return {instructions, nodes};
}

int solvePuzzlePartOne(const string &filename) {
    auto [directions, nodes] = readFromFilePartOne(filename);

    string nextNode = "AAA";
    size_t directionsIndex = 0;
    int steps = 0;
    while (nextNode != "ZZZ"){
        const auto &node = nodes[nextNode];
        directionsIndex = (directionsIndex) % directions.size();
        nextNode = directions[directionsIndex++] == 'L' ? node.left : node.right;
        steps++;
    }
    return steps;
}
