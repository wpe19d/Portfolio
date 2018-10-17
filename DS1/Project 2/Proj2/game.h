/**
* Author:  Wesley Easton
* Date: 02 Mar 2017
* This is the header file for game.c
*/

#ifndef GAME_H
#define GAME_H

typedef struct game *Game;

Game newGame(char *salvoFileA, char *salvoFileB, char *responseFileA, char *responseFileB, bool defeated, bool opponentWin, char *response, int numberSunk, char* playerId, int roundCount);

void runGame(char *playerId);

void printMap(Ships ships, int roundCount);

void updateOpponentMap(char **map, int rowNum, int colNum, char *response);

void genSalvos(Ships ships, Game game, int rowNum, int colNum, int direction, bool cont, int hitCount, char *response);

char *readResponse(char *opponentFile);

void readSalvos(Ships ships, Game game, int rowNum, int colNum);

void calculateShot(Ships ships, int *rowNum, int *colNum, int *direction, int *count, bool cont);

void changeDirection(int *direction);

void checkBehind(int *rowNum, int *colNum, int *direction, int *count);

void freeGame(Game game);

#endif