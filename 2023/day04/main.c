#include "scratchcards.h"

int main() {
    readFromFile("data.txt");
    printf("%d", solvePuzzlePartOne());
    return 0;
}