#ifndef GAME_H
#define GAME_H

typedef struct game *Game;

Game createNewGame();

void introduction();

void runGame();

void createPlayerDeck(Game game);

void createComputerDeck(Game game);

void playerChoiceMenu(Game game);

void computerTurn(Game game);

bool guessIsCorrect(Deck currentPlayerDeck, Deck currentOpponentDeck, char *playerGuess);

bool guessAllowed(Deck deck, char *playerGuess);

void drawFive(Deck deck, Deck playerDeck);

void transferCards(Deck fromDeck, Deck toDeck, int position);

bool checkCard(Card card, char *playerGuess);

void checkForBook(Game game, Deck deck);

void addToBook(Deck deck, Deck bookDeck, int position);

void calculateWinner(Game game);

void printPlayerHand(Deck deck);

void printComputerHand(Deck deck);

void printBook(Deck deck);

void freeGame(Game game);

void closeGame();

#endif