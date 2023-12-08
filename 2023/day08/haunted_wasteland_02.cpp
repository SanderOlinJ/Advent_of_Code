#include "haunted_wasteland_02.h"

tuple<vector<string>, string, unordered_map<string, Node>> readFromFilePartTwo(const string &filename){
    ifstream file(filename);
    string line, instructions;
    unordered_map<string, Node> nodes;
    vector<string> startingNodes;
    getline(file, instructions);

    while (getline(file, line)) {
        istringstream ss(line);
        string position, left, right;
        char temp;
        if (!(ss >> position >> temp >> temp >> left >> right)) continue;
        left.pop_back();
        right.pop_back();

        nodes[position] = {left, right};
        if (position.back() == 'A') {
            startingNodes.push_back(position);
        }
    }
    return make_tuple(startingNodes, instructions, nodes);
}

long long int solvePuzzlePartTwo(const string &filename) {
    auto [startingNodes, directions, nodes] = readFromFilePartTwo(filename);
    vector<tuple<int, string, int>> nextNodesWithInstructions;
    bool allFound = false;

    nextNodesWithInstructions.reserve(startingNodes.size());
    for (auto & elem : startingNodes) {
        nextNodesWithInstructions.emplace_back(0, elem, 0);
    }

    while (!allFound){
        allFound = true;
        for (auto &[directionCount, nextPosition, steps] : nextNodesWithInstructions) {
            if (nextPosition.back() == 'Z') {
                continue;
            }
            allFound = false;
            if (directionCount == directions.size()){
                directionCount = 0;
            }
            if (nodes.find(nextPosition) != nodes.end()) {
                const auto& node = nodes[nextPosition];
                nextPosition = directions[directionCount++] == 'L' ? node.left : node.right;
                steps++;
            }
        }
    }

    long long int result = 1;
    for (const auto &node : nextNodesWithInstructions) {
        result = lcm(result, static_cast<long long int>(get<2>(node)));
    }
    return result;
}
