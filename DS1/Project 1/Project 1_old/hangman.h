#ifndef HANGMAN_H
#define HANGMAN_H

typedef struct hangman *Hangman;

Hangman createHangmanGame(char *puzzleFile);

void newHangmanPuzzle(Hangman currentHangmanGame);

void loadPUzzleFile(Hangman currenthangmanGame, char *puzzleFIle);

bool isPUzzleOver(Hangman currentHangmanGame);

bool isPuzzleSolved(Hangman currentHangmanGame);

char* getGuessedWord(Hangman currentHangmanGame);

char* getStateOfHangman(Hangman currentHangmanGame);

void freeHangmanGame(Hangman currentHangmanGame);

#endif HANGMAN_H
