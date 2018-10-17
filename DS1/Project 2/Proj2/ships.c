/**
* Author:  Wesley Easton
* Date: 02 Mar 2017
* This is the file that handles the generation of ships and ship mangement
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "ships.h"
#include "game.h"

struct ships
{
	char *carrier;
	char *battleship;
	char *destroyer;
	char *submarine; 
	char *ptBoat;
	char **playerMap;
	char **opponentMap;
	int gridSize;
	int **coordinates;
	int numSunk;
};

/**
 * This creates a new instance of the Ship Struct
 */
Ships newShips(char *carrier, char *battleship, char *destroyer, char *submarine, char *ptBoat, char **playerMap, char **opponentMap, int gridSize, int **coordinates, int numSunk)
{
	Ships newShips = malloc(sizeof(*newShips));

	newShips->carrier = carrier;
	newShips->battleship = battleship;
	newShips->destroyer = destroyer;
	newShips->submarine = submarine;
	newShips->ptBoat = ptBoat;
	newShips->playerMap = playerMap;
	newShips->opponentMap = opponentMap;
	newShips->gridSize = gridSize;
	newShips->coordinates = coordinates;
	newShips->numSunk = numSunk;

	return newShips;
}

/**
 * Allocates memory for the ships and generates ship positions.
 */
Ships startGame()
{
	time_t t;
	srand((unsigned) time(&t));
	int rowNum;
	int colNum;
	int directionNum;

	Ships currentShips = newShips("CCCCC", "BBBB", "DDD", "SSS", "PP", NULL, NULL, 10, NULL, 0);

	genNumbers(&rowNum, &colNum, &directionNum);

	allocateShipMem(currentShips);
	createMaps(currentShips, '-');
	
	//Generate ships on the map
	genShipLayout(currentShips->carrier, currentShips->playerMap, (strlen(currentShips->carrier) - 1), rowNum, colNum, directionNum, currentShips->coordinates);
	genNumbers(&rowNum, &colNum, &directionNum);
	genShipLayout(currentShips->battleship, currentShips->playerMap, (strlen(currentShips->battleship) - 1), rowNum, colNum, directionNum, currentShips->coordinates);
	genNumbers(&rowNum, &colNum, &directionNum);
	genShipLayout(currentShips->destroyer, currentShips->playerMap, (strlen(currentShips->destroyer) - 1), rowNum, colNum, directionNum, currentShips->coordinates);
	genNumbers(&rowNum, &colNum, &directionNum);
	genShipLayout(currentShips->submarine, currentShips->playerMap, (strlen(currentShips->submarine) - 1), rowNum, colNum, directionNum, currentShips->coordinates);
	genNumbers(&rowNum, &colNum, &directionNum);
	genShipLayout(currentShips->ptBoat, currentShips->playerMap, (strlen(currentShips->ptBoat) - 1), rowNum, colNum, directionNum, currentShips->coordinates);
	
	return currentShips;
}

/**
 * Returns the player map
 */
char **getPlayerMap(Ships ships)
{
	return ships->playerMap;
}

/**
 * Returns the oppenents map
 */
char **getOpponentMap(Ships ships)
{
	return ships->opponentMap;
}

/**
 * Returns the grid size
 */
int getGridSize(Ships ships)
{
	return ships->gridSize;
}

/**
 * Returns the number of ships sunk
 */
int getNumSunk(Ships ships)
{
	return ships->numSunk;
}

/**
 * Adds one to the number of ships sunk
 */
void addSunkenShip(Ships ships)
{
	ships->numSunk++;
}
/**
 * Generates random coordinates and directions for the ships
 */
void genNumbers(int *rowNum, int *colNum, int *directionNum)
{
	*rowNum = rand() % 10;
	*colNum = rand() % 10;
	*directionNum = rand() % 4;
}

/**
 * Generates the ships on the map
 */
void genShipLayout(char *ship, char **map, int sizeLeft, int rowNum, int colNum, int directionNum, int **coordinates)
{
	int size = sizeLeft;
	int count = 1;
	int i = 0;
	
	if(checkArea(map, sizeLeft, rowNum, colNum, directionNum, coordinates, count) == true)
	{
		for(i = 0; i <= size; i++)
		{
			map[coordinates[i][0]][coordinates[i][1]] = ship[i];
		}
	}
	else
	{
		genNumbers(&rowNum, &colNum, &directionNum);
		genShipLayout(ship, map, size, rowNum, colNum, directionNum, coordinates);
	}
	return;
}

/**
 * Checks the area that the ship could occupy.  Returns true or false based on if the ship can fit.
 */
bool checkArea(char **map, int size, int rowNum, int colNum, int directionNum, int **coordinates, int count)
{
	if(map[rowNum][colNum] == '-' && size == 0)
	{
		coordinates[size][0] = rowNum;
		coordinates[size][1] = colNum;
		count = 0;
		return true;
	}
	else if(map[rowNum][colNum] == '-')
	{
		coordinates[size][0] = rowNum;
		coordinates[size][1] = colNum;
		size--;

		if(directionNum == 0 && rowNum > 0)
		{
			rowNum--;
		}
		else if(directionNum == 1 && colNum < 9)
		{
			colNum++;
		}
		else if(directionNum == 2 && rowNum < 9 )
		{
			rowNum++;
		}
		else if(directionNum == 3 && colNum > 0)
		{
			colNum--;
		}
		else
		{
			if(directionNum == 0 && rowNum == 0)
			{
				rowNum = rowNum + count;
			}
			else if(directionNum == 1  && colNum == 9)
			{
				colNum = colNum - count;
			}
			else if(directionNum == 2 && rowNum == 9)
			{
				rowNum = rowNum - count;
			}
			else if(directionNum == 3 && colNum == 0)
			{
				colNum = colNum + count;
			}
		directionNum = reverseDirection(directionNum);
		}
		count++;
		checkArea(map, size, rowNum, colNum, directionNum, coordinates, count);
	}
	else
	{
		return false;
	}
}

/**
 * Reverses the direction that the ship will go.
 */
int reverseDirection(int directionNum)
{
	if(directionNum == 0)
	{
		directionNum = 2;
	}
	else if(directionNum == 1)
	{
		directionNum = 3;
	}
	else if(directionNum == 2)
	{
		directionNum = 0;
	}
	else if(directionNum == 3)
	{
		directionNum = 1;
	}
	return directionNum;
}

/**
 * Checks if a ship is will be hit by the input salvo coord.  Returns 
 * true or false based on whether or not a ship was hit.
 */
bool checkShips(Ships ships, char shipType, int rowNum, int colNum)
{
	int i = 0;
	int j = 0;
	bool hit = false;
	ships->playerMap[rowNum][colNum] = 'X';
	for(i = 0; i < 10; i++)
	{
		for(j = 0; j < 10; j++)
		{
			if(ships->playerMap[i][j] == shipType)
			{
				hit = true;

				if(hit)
				{
					break;
				}
			}
		}
		if(hit)
		{
			break;
		}
	}

	return hit;
}

/**
 * Creates blank maps
 */
void createMaps(Ships ships, char symbol)
{
	int i = 0;
	int j = 0;

	for(i = 0; i < ships->gridSize; i++)
	{
		for(j = 0; j < ships->gridSize; j++)
		{
			ships->playerMap[i][j] = symbol;
		}
	}
	for(i = 0; i < ships->gridSize; i++)
	{
		for(j = 0; j < ships->gridSize; j++)
		{
			ships->opponentMap[i][j] = symbol;
		}
	}
}

/**
 * Allocates memory for the Ship Struct
 */
void allocateShipMem(Ships ships)
{
	int i = 0;

	ships->playerMap = malloc(10 * sizeof(char*));

	for(i = 0; i < ships->gridSize; i++)
	{
		ships->playerMap[i] = malloc(ships->gridSize * sizeof(char));
	}

	ships->opponentMap = malloc(ships->gridSize * sizeof(char*));

	for(i = 0; i < ships->gridSize; i++)
	{
		ships->opponentMap[i] = malloc(ships->gridSize * sizeof(char));
	}

	ships->coordinates = malloc(5 * sizeof(int*));

	for(i = 0; i < 5; i++)
	{
		ships->coordinates[i] = malloc(2 * sizeof(int));
	}
}

/**
 * Frees the arrays and structs
 */
void freeShipMem(Ships ships)
{
	int i = 0;

	for(i = 0; i < ships->gridSize; i++)
	{
		free(ships->playerMap[i]);
	}

	free(ships->playerMap);

	for(i = 0; i < ships->gridSize; i++)
	{
		free(ships->opponentMap[i]);
	}

	for(i = 0; i < 2; i++)
	{
		free(ships->coordinates[i]);
	}

	free(ships->coordinates);
	free(ships->opponentMap);
	free(ships);
}
