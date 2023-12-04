#include "scratchcards.h"

int solvePuzzlePartTwo(int numbersBefore[MAX_LINES][MAX_SIZE_BEFORE], int numbersAfter[MAX_LINES][MAX_SIZE_AFTER]){
    int indexOfCopies[MAX_LINES];
    for (int i = 0; i < MAX_LINES; i++) {
        indexOfCopies[i] = 1;
    }
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
        for (int j = i; j < i + numberOfWinningNumbers; j++){
            indexOfCopies[j+1] += indexOfCopies[i];
        }
    }
    int sum = 0;
    for (int i = 0; i < MAX_LINES; i++){
        sum += indexOfCopies[i];
    }
    return sum;
}