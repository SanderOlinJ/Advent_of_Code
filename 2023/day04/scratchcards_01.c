#include "scratchcards.h"

int numbersBefore[MAX_LINES][MAX_SIZE_BEFORE];
int numbersAfter[MAX_LINES][MAX_SIZE_AFTER];

void readFromFile(const char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    char line[256];
    int currentLine = 0;
    while (fgets(line, sizeof(line), file) && currentLine < MAX_LINES) {
        line[strcspn(line, "\n")] = 0;
        char *firstPart = strtok(line, "|");
        char *secondPart = strtok(NULL, "|");

        firstPart = strchr(firstPart, ':') + 2;

        char *token;
        int countBefore = 0;
        token = strtok(firstPart, " ");
        while (token != NULL && countBefore < MAX_SIZE_BEFORE) {
            numbersBefore[currentLine][countBefore] = atoi(token);
            countBefore++;
            token = strtok(NULL, " ");
        }

        int countAfter = 0;
        token = strtok(secondPart, " ");
        while (token != NULL && countAfter < MAX_SIZE_AFTER) {
            numbersAfter[currentLine][countAfter] = atoi(token);
            countAfter++;
            token = strtok(NULL, " ");
        }
        currentLine++;
    }

    fclose(file);
}

int solvePuzzlePartOne(){
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