/**
* Author:  Wesley Easton
* Date: 02 Mar 2017
* This is the file that contains the main logic of the game
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "ships.h"
#include "game.h"

struct game
{

	char *playerSalvoFile;
	char *opponentSalvoFile;
	char *playerResponseFile;
	char *opponentResponseFile;
	bool defeated;
	bool opponentWin;
	char *response;
	int numberSunk;
	char *playerId;
	int roundCount;
};

/**
 * Creates a new instance of the Game struct.
 */
Game newGame(char *playerSalvoFile, char *opponentSalvoFile, char *playerResponseFile, char *opponentResponseFile, bool defeated, bool opponentWin, char *response, int numberSunk, char* playerId, int roundCount)
{
	Game game = malloc(sizeof(*game));

	game->playerSalvoFile = playerSalvoFile;
	game->opponentSalvoFile = opponentSalvoFile;
	game->playerResponseFile = playerResponseFile;
	game->opponentResponseFile = opponentResponseFile;
	game->defeated = defeated;
	game->opponentWin = opponentWin;
	game->response = response;
	game->numberSunk = numberSunk;
	game->playerId = playerId;
	game->roundCount = roundCount;

	return game;

}

/**
 * Begins the actual gameplay
 */
void runGame(char *playerId)
{
	int direction;
	int rowNum;
	int colNum;
	bool cont;
	int hitCount = 0;
	Game game;
	if((strcmp(playerId, "A") == 0) || (strcmp(playerId, "a") == 0))
	{
		game = newGame("A.salvos", "B.salvos", "A.responses", "B.responses", false, false, "" , 0, playerId, 1);
	}
	else if((strcmp(playerId, "B") == 0) || (strcmp(playerId, "b") == 0))
	{
		game = newGame("B.salvos", "A.salvos", "B.responses", "A.responses", false, false, "" , 0, playerId, 1);
	}
	else
	{
		printf("\nInvalid Player Selection, please enter A or B\n");
		return -1;
	}

	Ships ships;

	ships = startGame();
	genNumbers(&rowNum, &colNum, &direction);

	if((strcmp(game->playerId, "A") == 0) || (strcmp(game->playerId, "a") == 0))
	{
			printMap(ships, game->roundCount);
			genSalvos(ships, game, rowNum, colNum, direction, cont, hitCount, game->response);

	}
	else if((strcmp(game->playerId, "B") == 0) || (strcmp(game->playerId, "b") == 0))
	{
			printf("\nPress Enter to Receive Damage Reports\n");
			getchar();
			printMap(ships, game->roundCount);
			readSalvos(ships, game, &rowNum, &colNum);
			genSalvos(ships, game, rowNum, colNum, direction, cont, hitCount, game->response);
	}

	freeGame(game);
	freeShipMem(ships);
}

/**
 * prints both the player ships and salvos.
 */
void printMap(Ships ships, int roundCount)
{
	int i = 0;
	int j = 0;

	printf("\n\n\t\t   Ships  \t\t\t\t   Salvos \n");
	for(i = 0; i < getGridSize(ships); i++)
	{
		printf("\t");
		for(j = 0; j < getGridSize(ships); j++)
		{
			printf(" %c ", getPlayerMap(ships)[i][j]);
		}
		printf("\t\t");

		for(j = 0; j < getGridSize(ships); j++)
		{
			printf(" %c ", getOpponentMap(ships)[i][j]);
		}
		printf("\n");
	}
	printf("Round Count: %d\n", roundCount);
	printf("\n\n");
}

/**
 * updates where the player has shot at the opponent
 */
void updateOpponentMap(char **map, int rowNum, int colNum, char *response)
{
	if(strcmp(response, "MISS") == 0)
	{
		map[rowNum][colNum] = 'O';
	}
	else if(strcmp(response, "HIT") == 0)
	{
		map[rowNum][colNum] = 'X';
	}
}

/**
 * Generates salvos to send at the oponnent based on various situations
 */
void genSalvos(Ships ships, Game game, int rowNum, int colNum, int direction, bool cont, int count, char *response)
{
	FILE *salvoFname;
	int dummyNum;
	salvoFname = fopen(game->playerSalvoFile, "a");
	char mapSymbol;



	if(salvoFname == NULL )
	{
		printf("\nERROR: %s not found", salvoFname);
	}

	if(game->opponentWin)
	{
		return; 
	}
	else if(strcmp(response, "SUNK") == 0)
	{
		count = 0;
		cont = false;
	}

	if(strcmp(response, "DEFEAT") == 0)
	{
		game->defeated = true;
		return;
	}
	//Calculates the shot if the last shot was a hit.
	else if(strcmp(response, "HIT") == 0)
	{
		cont = false;
		count++;
		calculateShot(ships, &rowNum, &colNum, &direction, &count, cont);
		fprintf(salvoFname, "%d %d\n", rowNum, colNum);
		fclose(salvoFname);
		printf("\nPress Enter to Fire!!!\n");
		getchar();
		game->response = readResponse(game->opponentResponseFile);
		updateOpponentMap(getOpponentMap(ships), rowNum, colNum, game->response);
		readSalvos(ships, game, rowNum, colNum);
		game->roundCount++;
		printMap(ships, game->roundCount);
		cont = true;
		genSalvos(ships, game, rowNum, colNum, direction, cont, count, game->response);
	}
	//calculates the next shot if there was a string of hits and then a miss
	else if(cont)
	{
		int loopCount = 0;
		mapSymbol = getOpponentMap(ships)[rowNum][colNum];

		while(mapSymbol != '-')
		{
			if(loopCount > 20)
			{
				genNumbers(&rowNum, &colNum, &dummyNum);
			}
			changeDirection(&direction);
			calculateShot(ships, &rowNum, &colNum, &direction, &count, cont);
			mapSymbol = getOpponentMap(ships)[rowNum][colNum];
			loopCount++;
		}
		fprintf(salvoFname, "%d %d\n", rowNum, colNum);
		fclose(salvoFname);
		printf("\nPress Enter to Fire!!!\n");
		getchar();
		game->response = readResponse(game->opponentResponseFile);
		updateOpponentMap(getOpponentMap(ships), rowNum, colNum, game->response);
		readSalvos(ships, game, rowNum, colNum);
		game->roundCount++;
		printMap(ships, game->roundCount);
		genSalvos(ships, game, rowNum, colNum, direction, cont, count, game->response);
	}
	//Genereates a shot if there were no hits.
	else
	{
		genNumbers(&rowNum, &colNum, &dummyNum);
		mapSymbol = getOpponentMap(ships)[rowNum][colNum];
		while(mapSymbol != '-')
		{
			mapSymbol = getOpponentMap(ships)[rowNum][colNum];
			genNumbers(&rowNum, &colNum, &dummyNum);
		}
		fprintf(salvoFname, "%d %d\n", rowNum, colNum);
		fclose(salvoFname);
		printf("\nPress Enter to Fire!!!\n");
		getchar();
		game->response = readResponse(game->opponentResponseFile);
		updateOpponentMap(getOpponentMap(ships), rowNum, colNum, game->response);
		readSalvos(ships, game, rowNum, colNum);
		game->roundCount++;
		printMap(ships, game->roundCount);
		genSalvos(ships, game, rowNum, colNum, direction, cont, count, game->response);
	}
}

/**
 * Reads the opponents response file
 */
char *readResponse(char *opponentFile)
{
	FILE *oppFname;
	char *response;
	int i = 0;
	int j = 0;
	int count = 0;
	int c = 0;
	char letter = NULL;

	oppFname = fopen(opponentFile, "r");
	if(oppFname == NULL)
	{
		printf("\nERROR: %s not found", opponentFile);
	}

	fseek(oppFname, 0, SEEK_END);
	count = ftell(oppFname);

	while(i < count) 
	{
		i++;
		fseek(oppFname, -i, SEEK_END);
		c = fgetc(oppFname);
		letter = (char) c;

		if(letter == 'K' || letter == 'S')
		{
			break;
		}
		else if(letter == 'T')
		{
			j = i;
			while(j < count)
			{
				j++;
				fseek(oppFname, -j, SEEK_END);
				c = fgetc(oppFname);
				letter = (char) c;

				if(letter == 'I' || letter == 'A')
				{
					break;
				}
			}
			break;
		}
	}
	
	if(c == 'I')
	{
		response = "HIT";
	}
	else if(c == 'S')
	{
		response = "MISS";
	}
	else if(c == 'K')
	{
		response = "SUNK";
	}
	else if(c == 'A')
	{
		response = "DEFEAT";
		printf("\n\t\t\t    ------------YOU WIN------------\n");
	}
	
	fclose(oppFname);

	return response;
}

/**
 * Reads the opponent salvo filt
 */
void readSalvos(Ships ships, Game game, int rowNum, int colNum)
{
	FILE *oppFname;
	FILE *respFile;
	int i = 0;
	int j = 0;
	char locationChar = "";
	bool hit = false;
	int count = 0;
	int c = 0;
	
	oppFname = fopen(game->opponentSalvoFile, "r");
	respFile = fopen(game->playerResponseFile, "a");

	if(oppFname == NULL)
	{
		printf("\nERROR: %s not found", game->opponentSalvoFile);
	}
	
	fseek(oppFname, 0, SEEK_END);
	count = ftell(oppFname);
	while(i < count) 
	{
		i++;
		fseek(oppFname, -i, SEEK_END);
		c = fgetc(oppFname);
		colNum = c - '0';

		if(colNum > -1 && colNum < 10)
		{
			j = i;
			while(j < count)
			{
				j++;
				fseek(oppFname, -j, SEEK_END);
				c = fgetc(oppFname);
				rowNum = c - '0';

				if(rowNum > -1  && rowNum < 10)
				{
					break;
				}
			}
			break;
		}
	}

	locationChar = getPlayerMap(ships)[rowNum][colNum];
	if(locationChar != '-')
	{
		hit = checkShips(ships, locationChar, rowNum, colNum);
		if(hit)
		{
			fprintf(respFile, "HIT\n");
		}
		else
		{
			fprintf(respFile, "SUNK\n");
			addSunkenShip(ships);
		}

		if(getNumSunk(ships) == 5)
		{
			fprintf(respFile, "DEFEAT\n");
			game->opponentWin = true;
			printf("\n\t\t\t    ------------DEFEAT------------\n");
			return 0;
		}
	}
	else
	{
		fprintf(respFile, "MISS\n");
	}

	fclose(oppFname);
	fclose(respFile);
}

/**
 * Calculates the salvos
 */
void calculateShot(Ships ships, int *rowNum, int *colNum, int *direction, int *count, bool cont)
{
	bool change = false;
	
	//Check boundaries
	if(*direction == 0 && *rowNum > 0)
	{
		if(cont && *colNum < 9)
		{
			*colNum = *colNum + 1;
		}
		*rowNum = *rowNum - 1;
	}
	else if(*direction == 1 && *colNum < 9)
	{
		if(cont && *rowNum < 9)
		{
			*rowNum = *rowNum + 1;
		}
		*colNum = *colNum + 1;
	}
	else if(*direction == 2 && *rowNum < 9)
	{
		if(cont && *colNum > 0)
		{
			*colNum = *colNum - 1;
		}
		*rowNum = *rowNum + 1;
	}
	else if(*direction == 3 && *colNum > 0)
	{
		if(cont && *rowNum > 0)
		{
			*rowNum = *rowNum - 1;
		}
		*colNum = *colNum - 1;
	}
	else
	{
		if(*direction == 0 && *rowNum < 1)
		{
			if(cont)
			{
				if((*colNum + 2) > 9)
				{
					*colNum = *colNum + 1;
					*rowNum = *rowNum + 1;
				}
				else
				{
					*colNum = *colNum + 2;
				}
			}
			else
			{
				*rowNum = *rowNum + *count;
				change = true;
			}
		}
		else if(*direction == 1  && *colNum > 8)
		{
			if(cont)
			{
				if((*rowNum + 2) > 9)
				{
					*rowNum = *rowNum + 1;
					*colNum = *colNum - 1;
				}
				else
				{
					*rowNum = *rowNum + 2;
				}
			}
			else
			{
				*colNum = *colNum - *count;
				change = true;
			}
		}
		else if(*direction == 2 && *rowNum > 8)
		{
			if(cont)
			{
				if((*colNum - 2) < 0)
				{
					*rowNum = *rowNum - 1;
					*colNum = *colNum - 1;
				}
				else
				{
					*colNum = *colNum - 2;
				}
			}
			else
			{
				*rowNum = *rowNum - *count;
				change = true;
			}
		}
		else if(*direction == 3 && *colNum < 1)
		{
			if(cont)
			{
				if((*rowNum - 2) <  0)
				{
					*rowNum = *rowNum - 1;
					*colNum = *colNum + 1;
				}
				else
				{
					*rowNum = *rowNum + 2;
				}
			}
			*colNum = *colNum + *count;
			change = true;
		}

		if(change)
		{
			*direction = reverseDirection(*direction);
		}
	}
}

/**
 * Reverses the directio of the shot
 */
void changeDirection(int *direction)
{
	if(*direction == 0)
	{
		*direction = *direction + 1;
	}
	else if(*direction == 1)
	{
		*direction = *direction + 1;
	}
	else if(*direction == 2)
	{
		*direction = *direction + 1;
	}
	else
	{
		*direction = 0;
	}
}

/**
 * frees the game struct
 */
void freeGame(Game game)
{
	free(game);
}

