#ifndef DAY07_COMMON_STRUCTS_H
#define DAY07_COMMON_STRUCTS_H

#include <string>

enum class Rank {
    None,
    OnePair,
    TwoPairs,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind
};

struct Hand {
    std::string cards;
    int bid;
    Rank rank;
};

#endif //DAY07_COMMON_STRUCTS_H
