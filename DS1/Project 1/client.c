/*
* Author:  Wesley Easton
* Date: 25 Jan 2017
* This program lets the user play the game of hangman.  This is the main file that allows the client
* to interact with the program.
*/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include "hangman.h"


int main(void)
{
	FILE* fname;
	char *filename = malloc(30 * sizeof(char));
	int userContinue = 0;
	char userGuess;
	bool check;
	Hangman game;
	
	
	printf("\n\n\t\t------------------ Welcom to Hangman!------------------\n\n");
	
	//Do while loop for user menu choice
	do{
		//If the user starts a new game or chooses to load a new file for questions.
		if(userContinue == 0 || userContinue == 1)
		{
			printf("Please enter the name of you puzzle file: ");
			fscanf(stdin, "%s", filename);
			filename = realloc(filename, strlen(filename) * sizeof(char));
			fname = fopen(filename, "r");
			
			if(fname == NULL) 
			{
				filename[strcspn(filename, "\n")] = 0;
				printf("ERROR: %s not found\n", filename);
				return(-1);
			}
			game = createHangmanGame(fname);
		}

		newHangmanPuzzle(game);
		
		//Runs the game until the user has guessed the correc word or loses.
		do
		{
			//Guessing Menu
			printf("\nCurrent Puzzle:%s\n", getGuessedWord(game));
			printf("Missed Guesses: %s\n", getStateOfHangman(game));
			printf("Please Guess a Letter:");
			fscanf(stdin, " %c", &userGuess);
			
			if(guessLetter(game, userGuess))
			{
				printf("Correct!\n");
			}
			
		}while(!isPuzzleOver(game));
		
		//Checks to see if the user won or not.
		if(isPuzzleSolved(game))
		{
			printf("--------You won!!!!!!!!!!!--------\n\n");
		}
		else
		{
			printf("--------%s--------\n", getStateOfHangman(game));
			printf("The correct word was: %s\n", getCurrentWord(game));
		}
		
		//End menu choice after a game is complete
		printf("Would you like to:\n");
		printf("\t1. Load a new file of puzzles\n");
		printf("\t2. Play a New Game\n");
		printf("\t3. Quit and exit\n");
		printf("Please make a selection:");
		fscanf(stdin, " %d", &userContinue);
		
		
	}while(userContinue < 3);
	
	//frees the arrays and structs
	printf("\n\n\t\tThanks for Playing!\n\n\n");
	free(filename);
	freeHangmanGame(game);
	
	return 0;
}



