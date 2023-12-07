#include "camel_cards_02.h"
using namespace std;

vector<Hand> readFromFilePartTwo(const string &filename) {
    vector<Hand> array;
    ifstream file(filename);
    string line;

    while (getline(file, line)) {
        istringstream ss(line);
        string cards;
        int bid;
        if (!(ss >> cards >> bid)) break;
        Hand hand{cards, bid, classifyRankOfHandPartTwo(cards)};
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
                    int valueOfCardAtHand = getValueOfCardPartTwo(hand.cards[j]);
                    int valueOfCardInArray = getValueOfCardPartTwo(array[i].cards[j]);
                    if (valueOfCardAtHand > valueOfCardInArray) {
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

Rank classifyRankOfHandPartTwo(const std::string &stringHand) {
    auto jCount = std::count(stringHand.begin(), stringHand.end(), 'J');
    std::unordered_map<char, int> charCount;

    for (char ch : stringHand) {
        if (ch != 'J') {
            charCount[ch]++;
        }
    }

    Rank bestRank = Rank::None;
    for (const auto& elem : charCount) {
        auto tempCharCount = charCount;
        tempCharCount[elem.first] += int (jCount);

        int pairs = 0;
        bool three = false, four = false, five = false;
        for (const auto& pair : tempCharCount) {
            if (pair.second == 2) pairs++;
            if (pair.second == 3) three = true;
            if (pair.second == 4) four = true;
            if (pair.second == 5) five = true;
        }

        Rank currentRank = Rank::None;
        if (five) currentRank = Rank::FiveOfAKind;
        else if (four) currentRank = Rank::FourOfAKind;
        else if (three && pairs >= 1) currentRank = Rank::FullHouse;
        else if (three) currentRank = Rank::ThreeOfAKind;
        else if (pairs == 2) currentRank = Rank::TwoPairs;
        else if (pairs == 1) currentRank = Rank::OnePair;

        if (currentRank > bestRank) {
            bestRank = currentRank;
        }
    }

    if (jCount == stringHand.size() || (bestRank == Rank::None && jCount > 0)) {
        if (jCount >= 5) return Rank::FiveOfAKind;
        if (jCount == 4) return Rank::FourOfAKind;
        if (jCount == 3) return Rank::ThreeOfAKind;
        if (jCount == 2) return Rank::TwoPairs;
        if (jCount == 1) return Rank::OnePair;
    }

    return bestRank;
}

int getValueOfCardPartTwo(char card) {
    static const std::map<char, int> cardValues = {
            {'2', 2}, {'3', 3}, {'4', 4}, {'5', 5},
            {'6', 6}, {'7', 7}, {'8', 8}, {'9', 9},
            {'T', 10}, {'J', 1}, {'Q', 12}, {'K', 13}, {'A', 14}
    };
    auto it = cardValues.find(card);
    return it != cardValues.end() ? it->second : -1;
}


int solvePuzzlePartTwo(const string &fileName) {
    vector<Hand> vector = readFromFilePartTwo(fileName);
    int sum = 0;
    for (auto i = 0; i < vector.size(); i++){
        sum += int (vector.size() - i ) * vector[i].bid;
    }
    return sum;
}