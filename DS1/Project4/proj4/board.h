#ifndef CARD_H
#define CARD_H

typedef struct board *Board;

void convertToBinary(int number, Board board);

bool solveBoard(Board board, int rowValue, int colValue);

bool ableToJump(Board board, int rowValue, int colValue, int midRowValue, int midColValue);

void checkArrayIndexes(int *rowValue, int *colValue, bool backTrack);

void printBoardConfig(Board board);

void allocateBoardConfigMemory(Board board);

void freeBoard(Board board);

#endif