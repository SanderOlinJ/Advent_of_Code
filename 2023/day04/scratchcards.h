#ifndef DAY04_SCRATCHCARDS_H
#define DAY04_SCRATCHCARDS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINES 220
#define MAX_SIZE_BEFORE 10
#define MAX_SIZE_AFTER 25

void readFromFile(const char *filename);
int solvePuzzlePartOne(int numbersBefore[MAX_LINES][MAX_SIZE_BEFORE], int numbersAfter[MAX_LINES][MAX_SIZE_AFTER]);
int solvePuzzlePartTwo(int numbersBefore[MAX_LINES][MAX_SIZE_BEFORE], int numbersAfter[MAX_LINES][MAX_SIZE_AFTER]);

#endif //DAY04_SCRATCHCARDS_H
