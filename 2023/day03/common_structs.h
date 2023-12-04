// common_structs.h
#ifndef COMMON_STRUCTS_H
#define COMMON_STRUCTS_H

struct Position {
    int row, col;
};

struct Number {
    int value, length;
    Position position;
};

struct SpecialCharacter {
    Position position;
};

#endif // COMMON_STRUCTS_H
