/*
* Author:  Wesley Easton
* Date: 25 Jan 2017
* This is the source code for hangman.h.  This file contains the functions and structs that build
* and run the game of hangman.
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "hangman.h"

//Hangman struct definition
struct hangman 
{
	char** puzzleWords;
	int numPuzzles;
	char* currentWord;
	char* guessedWord;
	bool gameOver;
	bool gameWon;
	int missedGuesses;
	char* hangState;
};

//Initializes the hangman struct
Hangman newHangMan(char* puzzleWords, int numPuzzles, char* currentWord, char* guessedWord, bool gameOver, bool gameWon, int missedGuesses, char* hangState)
{
	Hangman newHangMan = malloc(sizeof(*newHangMan));
	newHangMan->puzzleWords = puzzleWords;
	newHangMan->numPuzzles = numPuzzles;
	newHangMan->currentWord = currentWord;
	newHangMan->guessedWord = guessedWord;
	newHangMan->gameOver = gameOver;
	newHangMan->gameWon = gameWon;
	newHangMan->missedGuesses = missedGuesses;
	newHangMan->hangState = hangState;
}

//Creates the hangman game by taking in a puzzle file and loading the game from that file.
Hangman createHangmanGame(char *puzzleFile)
{
	Hangman game = newHangMan(NULL, 0, NULL, NULL, false, false, 0, NULL);
	
	loadPuzzleFile(game, puzzleFile);	
	
	return game;
	
}
//Creates a new random word from array of words.  Also fills out the blanks for HANGMAN and the dashes for the word they are guessing.
void newHangmanPuzzle(Hangman currentHangmanGame)
{
	int randNum;
	int i;
	time_t t;
	srand((unsigned) time(&t));
	randNum = rand() % currentHangmanGame->numPuzzles;
	currentHangmanGame->missedGuesses = 0;
	currentHangmanGame->hangState = realloc(currentHangmanGame->hangState, sizeof(char));
	
	//fills the string with whitespace
	for(i = 0; i < 8; i++)
	{
		strncpy(currentHangmanGame->hangState, " ", i);
	}	
	
	currentHangmanGame->currentWord = currentHangmanGame->puzzleWords[randNum];
	
	
	if(currentHangmanGame->currentWord[strlen(currentHangmanGame->currentWord) - 1] == '\n')
	{
		currentHangmanGame->currentWord[strlen(currentHangmanGame->currentWord) - 1] = '\0';
	}
	
	currentHangmanGame->guessedWord = malloc(strlen(currentHangmanGame->currentWord) + 1* sizeof(char));
	
	strcpy(currentHangmanGame->guessedWord, "-");
	
	for(i = 0; i < strlen(currentHangmanGame->currentWord) - 1; i++)
	{	
		strcat(currentHangmanGame->guessedWord, "-");
	}
	
}

//Loads words from puzzle file that user entered.  Store the words into an array and initializes the 'state' of the game.
void loadPuzzleFile(Hangman currentHangmanGame, char* puzzleFile)
{
	int size = 0;
	char line[100] = "";
	int i = 0;

	fgets(line, 100, puzzleFile);
	size = atoi(line);
	
	currentHangmanGame->numPuzzles = size;
	currentHangmanGame->puzzleWords = malloc(currentHangmanGame->numPuzzles * sizeof(char*));
	currentHangmanGame->hangState = malloc(sizeof(char));
	
	fgets(line, 100, puzzleFile);
	
	while(!feof(puzzleFile))
	{
		currentHangmanGame->puzzleWords[i] = malloc(strlen(line) * sizeof(char));
		strcpy(currentHangmanGame->puzzleWords[i], line);
		fgets(line, 100, puzzleFile);
		i++;
	}
	
	fclose(puzzleFile);
}

//Checks to see if the puzzle is guessed correctly or if max number of guesses has been met
bool isPuzzleOver(Hangman currentHangmanGame)
{
	if(isPuzzleSolved(currentHangmanGame) || currentHangmanGame->missedGuesses == 7)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//checks to see if the puzzle is solved
bool isPuzzleSolved(Hangman currentHangmanGame)
{
	if(strcmp(currentHangmanGame->guessedWord, currentHangmanGame->currentWord) == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//returns the word that the user sees as their guesses.
char* getGuessedWord(Hangman currentHangmanGame)
{
	return currentHangmanGame->guessedWord; 
}

//returns the current word loaded into the puzzle
char* getCurrentWord(Hangman currentHangmanGame)
{
	return currentHangmanGame->currentWord;
}

//returns the number of missed guesses
int getCount(Hangman currentHangmanGame)
{
	return currentHangmanGame->missedGuesses;
}

//checks to see if the letter the user guessed exists in the currently loaded word
bool guessLetter(Hangman currentHangmanGame, char letterToGuess)
{
	int i;
	bool correct = false;
	letterToGuess = tolower(letterToGuess);
	for(i = 0; i < strlen(currentHangmanGame->currentWord); i++)
	{	
		if(currentHangmanGame->currentWord[i] == letterToGuess)
		{
			currentHangmanGame->guessedWord[i] = letterToGuess;
			
			correct = true;
		}
	}
	
	if(!correct)
	{
		currentHangmanGame->missedGuesses++;
	}
	
	return correct;
}

//returns the word HANGMAN with the appropriate amount of letters displayed to the user.
char* getStateOfHangman(Hangman currentHangmanGame)
{
	char word[10] = "HANGMAN";
	
	if(currentHangmanGame->missedGuesses > 0)
	{
		currentHangmanGame->hangState = realloc(currentHangmanGame->hangState, currentHangmanGame->missedGuesses * sizeof(char));
		strncpy(currentHangmanGame->hangState, word, currentHangmanGame->missedGuesses);
	}
	return currentHangmanGame->hangState;
}

//Frees the memory of the program.
void freeHangmanGame(Hangman currentHangmanGame)
{
	int i;
	
	for(i = 0; i < currentHangmanGame->numPuzzles; i++)
	{
		free(currentHangmanGame->puzzleWords[i]);
	}
	
	free(currentHangmanGame->puzzleWords);
	free(currentHangmanGame->guessedWord);
	free(currentHangmanGame);
	free(currentHangmanGame->hangState);
}
