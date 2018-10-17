#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "board.h"

struct board
{
    int **boardConfig;
    int numberOfPegs;
    int turn;
};

Board newBoard(int number)
{

    Board newBoard = malloc(sizeof(*newBoard));
    newBoard->numberOfPegs = 15;
    newBoard->turn = 0;
    allocateBoardConfigMemory(newBoard);
    convertToBinary(number, newBoard);

    return newBoard;
}

void convertToBinary(int number, Board board)
{
    int count = 0;
    int colCount = 0;
    int rowCount = 4;
    int colValue = 0;
    int rowValue = 0;
    int dummyCol = 4;
    int dummyRow = 4;
    int binaryValue = 0;

    while(count < board->numberOfPegs) 
    {
        if(colValue == -1)
        {
            colCount++;
            rowValue = 0;
            colValue = colCount;
        }
        if(dummyRow == 5)
        {
            if(rowCount != 1)
            {
                rowCount--;
            }
            dummyCol = 4;
            dummyRow = rowCount;
        }

        binaryValue = number & 1;
        number = number >> 1;

        board->boardConfig[rowValue][colValue] = binaryValue;
        board->boardConfig[dummyRow][dummyCol] = -1;
        
        colValue--;
        rowValue++;
        dummyCol--;
        dummyRow++;
        count++;
    }
}

bool solveBoard(Board board, int rowValue, int colValue)
{
    int i = 0;
    int j = 0;
    bool backTrack = false;
    
    if(board->turn == 13)
    {
        return true;
    }

    checkArrayIndexes(&rowValue, &colValue, backTrack);
    if(board->boardConfig[rowValue][colValue] == -1 || board->boardConfig[rowValue][colValue] == 0)
    {
        solveBoard(board, rowValue, colValue + 1);
    }

    for(i = 1; i < 6; i++)
    {
        if(i == 1)
        {
            if(ableToJump(board, rowValue + 2, colValue, rowValue + 1, colValue))
            {
                board->boardConfig[rowValue + 2][colValue] = 1;
                board->boardConfig[rowValue][colValue] = 0;
                board->boardConfig[rowValue + 1][colValue] = 0;
                board->turn++;
                printBoardConfig(board);
                if(solveBoard(board, rowValue, colValue + 1))
                    return true;
            }
        }
        else if(i == 2)
        {
            if(ableToJump(board, rowValue - 2, colValue, rowValue -1, colValue))
            {
                board->boardConfig[rowValue - 2][colValue] = 1;
                board->boardConfig[rowValue][colValue] = 0;
                board->boardConfig[rowValue - 1][colValue] = 0;
                board->turn++;
                printBoardConfig(board);
                if(solveBoard(board, rowValue, colValue + 1))
                    return true;
            }
        }
        else if(i == 3)
        {
            if(ableToJump(board, rowValue, colValue + 2, rowValue, colValue + 1))
            {
                board->boardConfig[rowValue][colValue + 2] = 1;
                board->boardConfig[rowValue][colValue] = 0;
                board->boardConfig[rowValue][colValue + 1] = 0;
                board->turn++;
                printBoardConfig(board);
                if(solveBoard(board, rowValue, colValue + 1))
                    return true;
            }
        }
        else if(i == 4)
        {
            if(ableToJump(board, rowValue, colValue - 2, rowValue, colValue -1))
            {
                board->boardConfig[rowValue][colValue - 2] = 1;
                board->boardConfig[rowValue][colValue] = 0;
                board->boardConfig[rowValue][colValue - 1] = 0;
                board->turn++;
                printBoardConfig(board);
                if(solveBoard(board, rowValue, colValue + 1))
                    return true;
                
            }
        }
        else
        {
            solveBoard(board, rowValue, colValue + 1);
        }     
    }    
    
    return false;
}

bool ableToJump(Board board, int rowValue, int colValue, int midRowValue, int midColValue)
{
    if((rowValue > -1 && rowValue < 5) && (colValue > -1 && colValue < 5) && board->boardConfig[rowValue][colValue] == 0)
    {
        if((midRowValue > -1 && midRowValue < 5) && (midColValue > -1 && midColValue < 5) && board->boardConfig[midRowValue][midColValue] == 1)
            return true;
    }

    return false;
}

void checkArrayIndexes(int *rowValue, int *colValue, bool backTrack)
{
    if(*colValue == 5)
    {
        *colValue = 0;
        *rowValue = *rowValue + 1;
    }
}

void printBoardConfig(Board board)
{
    int colCount = 0;
    int colValue = 0;
    int rowValue = 0;
    int i = 0;
    int j = 0;
    int count = 1;
    int space = 4;

    printf("Step: %d\n", board->turn);
    for(i = 0; i < 5; i++)
    {
        for(j = 0; j < space; j++)
	    {
		    printf(" ");
	    }
        for(j = 0; j < count; j++)
        {
            if(colValue == -1)
            {
                colCount++;
                rowValue = 0;
                colValue = colCount;
            }
            if(board->boardConfig[rowValue][colValue] == 0)
            {
                printf("- ", board->boardConfig[rowValue][colValue]);
            }
            else
            {
                printf("%d ", board->boardConfig[rowValue][colValue]);
            }
            colValue--;
            rowValue++;
        }

        space--;
        count++;
        printf("\n");
    }
}

void allocateBoardConfigMemory(Board board)
{
    int i = 0;

    board->boardConfig = malloc(5 * sizeof(char*));
    for(i = 0; i < 5; i++)
    {
        board->boardConfig[i] = malloc(5 * sizeof(char));
    }
}

void freeBoard(Board board)
{
    int i = 0;

    for(i = 0; i < 5; i++)
    {
        free(board->boardConfig[i]);
    }

    free(board->boardConfig);
    free(board);
}