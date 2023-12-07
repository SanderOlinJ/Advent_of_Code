#include "camel_cards_01.h"
using namespace std;

vector<Hand> readFromFilePartOne(const string &filename) {
    vector<Hand> array;
    ifstream file(filename);
    string line;

    while (getline(file, line)) {
        istringstream ss(line);
        string cards;
        int bid;
        if (!(ss >> cards >> bid)) break;
        Hand hand{cards, bid, classifyRankOfHandPartOne(cards)};
        if (array.empty()) {
            array.push_back(hand);
            continue;
        }
        for (int i = 0; i < array.size(); i++) {
            if (hand.rank > array[i].rank){
                array.insert(array.begin() + i, hand);
                break;

            } else if (hand.rank == array[i].rank) {
                bool isHandStronger = false;
                for (int j = 0; j < hand.cards.size(); j++) {
                    int valueOfCardAtHand = getValueOfCardPartOne(hand.cards[j]);
                    int valueOfCardInArray = getValueOfCardPartOne(array[i].cards[j]);
                    if (getValueOfCardPartOne(hand.cards[j]) > getValueOfCardPartOne(array[i].cards[j])) {
                        isHandStronger = true;
                        break;
                    } else if (valueOfCardAtHand < valueOfCardInArray) {
                        break;
                    }
                }
                if (isHandStronger) {
                    array.insert(array.begin() + i, hand);
                    break;
                }
            }
            if (i == array.size() - 1) {
                array.push_back(hand);
                break;
            }
        }
    }
    file.close();
    return array;
}

Rank classifyRankOfHandPartOne(const std::string &stringHand) {
    std::unordered_map<char, int> charCount;

    for (char ch : stringHand) {
        charCount[ch]++;
    }
    int pairs = 0;
    bool three = false, four = false, five = false;

    for (const auto& pair : charCount) {
        if (pair.second == 2) pairs++;
        if (pair.second == 3) three = true;
        if (pair.second == 4) four = true;
        if (pair.second == 5) five = true;
    }

    if (five) return Rank::FiveOfAKind;
    if (four) return Rank::FourOfAKind;
    if (three && pairs == 1) return Rank::FullHouse;
    if (three) return Rank::ThreeOfAKind;
    if (pairs == 2) return Rank::TwoPairs;
    if (pairs == 1) return Rank::OnePair;

    return Rank::None;
}

int getValueOfCardPartOne(char card) {
    static const std::map<char, int> cardValues = {
            {'2', 2}, {'3', 3}, {'4', 4}, {'5', 5},
            {'6', 6}, {'7', 7}, {'8', 8}, {'9', 9},
            {'T', 10}, {'J', 11}, {'Q', 12}, {'K', 13}, {'A', 14}
    };
    auto it = cardValues.find(card);
    return it != cardValues.end() ? it->second : -1;
}


int solvePuzzlePartOne(const string &fileName) {
    vector<Hand> vector = readFromFilePartOne(fileName);
    int sum = 0;
    for (auto i = 0; i < vector.size(); i++){
        sum += int (vector.size() - i ) * vector[i].bid;
    }
    return sum;
}