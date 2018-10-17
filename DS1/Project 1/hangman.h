/*
* Author:  Wesley Easton
* Date: 25 Jan 2017
* This is the header file that allows the client to interact with the program.
*/

#ifndef HANGMAN_H
#define HANGMAN_H

typedef struct hangman *Hangman;

Hangman newHangMan(char* puzzleWords, int numPuzzles, char* currentWord, char* guessedWord, bool gameOver, bool gameWon, int missedGuesses, char* hangState);

Hangman createHangmanGame(char *puzzleFile);

void newHangmanPuzzle(Hangman currentHangmanGame);

void loadPuzzleFile(Hangman currenthangmanGame, char *puzzleFIle);

bool isPuzzleOver(Hangman currentHangmanGame);

bool isPuzzleSolved(Hangman currentHangmanGame);

char* getGuessedWord(Hangman currentHangmanGame);

char* getCurrentWord(Hangman currentHangmanGame);

int getCount(Hangman currentHangmanGame);

bool guessLetter(Hangman currentHangmanGame, char letterToGuess);

char* getStateOfHangman(Hangman currentHangmanGame);

void freeHangmanGame(Hangman currentHangmanGame);

#endif
