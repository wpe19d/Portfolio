/**
* Author:  Wesley Easton
* Date: 02 Mar 2017
* This is the header for ships.c
*/

#ifndef SHIPS_H
#define SHIPS_H

typedef struct ships *Ships;

Ships newShips(char *carrier, char *battleship, char *destroyer, char *submarine, char *ptBoat, char **playerMap, char **opponentMap, int gridSize, int **coordinates, int numSunk);

Ships startGame();

void createMaps(Ships ships, char symbol);

char **getPlayerMap(Ships ships);

char **getOpponentMap(Ships ships);

int getGridSize(Ships ships);

int getNumSunk(Ships ships);

void addSunkenShip(Ships ships);

void genNumbers(int *rowNum, int *colNum, int *directionNum);

void genShipLayout(char *ship, char **map, int sizeLeft, int rowNum, int colNum, int directionNum, int **coordinates);

bool checkArea(char **map, int size, int rowNum, int colNum, int directionNum, int **coordinates, int count);

bool checkShips(Ships ships, char shipType, int rowNum, int colNum);

int reverseDirection(int directionNum);

void allocateShipMem(Ships ships);

void freeShipMem(Ships ships);

#endif