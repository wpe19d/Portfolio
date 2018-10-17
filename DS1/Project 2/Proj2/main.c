/*
* Author:  Wesley Easton
* Date: 02 Mar 2017
* This is the main file for the program.  It will begin execution of the game
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "ships.h"
#include "game.h"

int main(int argc, char* argv[])
{
	if(argc == 1)
	{
		printf("\nInvalid Player Selection, please enter A or B\n");
		return -1;
	}

	runGame(argv[1]);

	return 0;
}