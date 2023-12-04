#include "scratchcards.h"

int solvePuzzlePartOne(int numbersBefore[MAX_LINES][MAX_SIZE_BEFORE], int numbersAfter[MAX_LINES][MAX_SIZE_AFTER]){
    int totalPoints = 0;
    for (int i = 0; i < MAX_LINES; i++) {
        int numberOfWinningNumbers = 0;
        for (int j = 0; j < MAX_SIZE_BEFORE; j++) {
            for (int k = 0; k < MAX_SIZE_AFTER; k++) {
                if (numbersBefore[i][j] == numbersAfter[i][k]) {
                    numberOfWinningNumbers++;
                    break;
                }
            }
        }
        totalPoints += 1 << (numberOfWinningNumbers - 1);
    }
    return totalPoints;
}