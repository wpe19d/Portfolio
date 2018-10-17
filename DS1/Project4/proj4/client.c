#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "board.h"

// NOT FINISHED //
int main()
{
    Board board = newBoard(32766);
    printBoardConfig(board);
    if(solveBoard(board, 0, 0))
        printBoardConfig(board);
    
    
}