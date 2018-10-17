#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include "card.h"
#include "deck.h"
#include "game.h"

struct game
{
    Deck deck;
    Deck playerDeck;
    Deck computerDeck;
    Deck playerBooks;
    Deck computerBooks;
    bool complete;

};

Game createNewGame()
{
    Game newGame = malloc(sizeof(*newGame));
    newGame->deck = createFullDeck();
    newGame->playerDeck = createEmptyDeck();
    newGame->computerDeck = createEmptyDeck();
    newGame->playerBooks = createEmptyDeck();
    newGame->computerBooks = createEmptyCard();
    newGame->complete = false;
}

void introduction()
{
    char userChoice;

    printf("\n--------------------Welcome to Go Fish!--------------------\n\n");
    printf("1. Start New Game\n");
    printf("2. Exit the program\n");
    printf("Please select an option to continue: ");
    fscanf(stdin, " %c", &userChoice);

    if(userChoice == '1')
    {
        runGame();
    }
    else
    {
        closeGame();
    }
}

void runGame()
{
    Game game = createNewGame();
    time_t t;
	srand((unsigned) time(&t));
    shuffle(game->deck, 7);
    createPlayerDeck(game);
    createComputerDeck(game);
    
    while(!game->complete)
    {
        playerChoiceMenu(game);
        if(!game->complete)
        {
            computerTurn(game);
        }
    }

    freeGame(game);
    closeGame();
}

void createPlayerDeck(Game game)
{
    int count = 0;

    while(count < 7)
    {
        Card tempCard = draw(game->deck);
        prependToDeck(game->playerDeck, getSuit(tempCard), getRank(tempCard));
        count++;
    }
    sort(game->playerDeck);
    setOwner(game->playerDeck, 1);
    setOwner(game->playerBooks, 1);
}

void createComputerDeck(Game game)
{
    int count = 0;

    while(count < 7)
    {
        Card tempCard = draw(game->deck);
        prependToDeck(game->computerDeck, getSuit(tempCard), getRank(tempCard));
        count++;
    }
    sort(game->computerDeck);
    setOwner(game->computerDeck, 2);
    setOwner(game->computerBooks, 2);
}

//player menu.  Continues as long as player's guess is correct.
void playerChoiceMenu(Game game)
{
    //checks for win condition
    if((getSize(game->playerDeck) == 0) && (getSize(game->deck) == 0))
    {
        game->complete = true;
        calculateWinner(game);
        return;
    }
    char userChoice;
    char userGuess[10];

    if(getSize(game->deck) == 0)
    {
        calculateWinner(game);
        return;
    }
    printf("\n------ player deck ------\n");
    printAll(game->playerDeck);

    printf("\n1. Make a Guess\n");
    printf("2. View Your Books\n");
    printf("3. View Computers Books\n");
    printf("Please Select an Option: ");
    fscanf(stdin, " %c", &userChoice);

    if(userChoice == '1')
    {
        printf("\nPlease Enter the Rank you would like to guess: ");
        fscanf(stdin, "%s", userGuess);

        //if player gueeses outside of the cards they have
        if(!guessAllowed(game->playerDeck, userGuess))
        {
            printf("\nPlease pick a card that you alread have\n");
            return;
        }
        
        if(guessIsCorrect(game->playerDeck, game->computerDeck, userGuess))
        {
            printf("\nGOOD GUESS!!\n\n");
            checkForBook(game, game->playerDeck);

            if(getSize(game->playerDeck) == 0)
            {
                drawFive(game->deck, game->playerDeck);
                checkForBook(game, game->playerDeck);
            }
            playerChoiceMenu(game);
        }
        else
        {
            printf("\n----------GO FISH!!!----------\n\n");
            Card tempCard = draw(game->deck);
            printf("You drew %s %s\n", getSuitName(tempCard), getRankName(tempCard));
            insertIntoHand(game->playerDeck, tempCard);
            checkForBook(game, game->playerDeck);
            
            //checks if the card matches what the player guessed
            if(checkCard(tempCard, userGuess))
            {
                printf("\nLUCK OF THE DRAW\n");
                printf("%s\n%s\n", getSuitName(tempCard), getRankName(tempCard));

                if(getSize(game->playerDeck) == 0)
                {
                    drawFive(game->deck, game->playerDeck);
                    checkForBook(game, game->playerDeck);
                }
                playerChoiceMenu(game);
            }
        }

        return;
    }
    else if(userChoice == '2')
    {
        printf("\nPlayer Books: \n");
        if(getSize(game->playerBooks) > 0)
        {
            printAll(game->playerBooks);
        }
        else
        {
            printf("\nEMPTY\n\n");
        }
        playerChoiceMenu(game);
    }
    else
    {
        printf("\nComputer Books: \n\n");
        if(getSize(game->computerBooks) > 0)
        {
            printAll(game->computerBooks);
        }
        else
        {
            printf("\nEMPTY\n\n");
        }
        playerChoiceMenu(game);
    }
}

void computerTurn(Game game)
{
    //checks for win condition
    if((getSize(game->computerDeck) == 0) && (getSize(game->deck) == 0))
    {
        game->complete = true;
        calculateWinner(game);
        return;
    }
    else if(game->complete)
    {
        return;
    }

    char computerGuess[10];
    int randNumber = rand() % getSize(game->computerDeck);

    strcpy(computerGuess, getRankName(getCardAtIndex(game->computerDeck, randNumber)));

    printf("\nComputer guess %s\n", computerGuess);

    if(guessIsCorrect(game->computerDeck, game->playerDeck, computerGuess))
    {
        printf("\nComputer Guesses Corretctly!\n\n");
        checkForBook(game, game->computerDeck);
        if(getSize(game->computerDeck) == 0)
        {
            drawFive(game->deck, game->computerDeck);
            checkForBook(game, game->computerDeck);
        }
        computerTurn(game);
    }
    else
    {
        printf("\n----------GO FISH!!!----------\n\n");
        Card tempCard = draw(game->deck);
        
        insertIntoHand(game->computerDeck, tempCard);
        if(checkCard(tempCard, computerGuess))
        {
            printf("\n----------LUCK OF THE DRAW----------\n");
            printf("%s\n%s\n", getSuitName(tempCard), getRankName(tempCard));

            checkForBook(game, game->computerDeck);
            if(getSize(game->computerDeck) == 0)
            {
                drawFive(game->deck, game->computerDeck);
                checkForBook(game, game->computerDeck);
            }
            computerTurn(game);
        }
    }
    return;
}

//checks if the guess is correct
bool guessIsCorrect(Deck currentPlayerDeck, Deck currentOpponentDeck, char *playerGuess)
{
    int i = 0;
    bool found = false;
    Card tempCard = createEmptyCard();
    while(i < strlen(playerGuess))
    {
        playerGuess[i] = toupper(playerGuess[i]);
        i++;
    }

    i = 0;

    while(i < getSize(currentOpponentDeck))
    {
        tempCard = getCardAtIndex(currentOpponentDeck, i);
        if(checkCard(tempCard, playerGuess))
        {            
            transferCards(currentOpponentDeck, currentPlayerDeck, i);
            found =  true;
            i--;
        }
        i++;
    }

    return found;
}

//checks if the guess is allowed
bool guessAllowed(Deck deck, char* playerGuess)
{
    int i = 0;
    Card tempCard = createEmptyCard();
    while(i < strlen(playerGuess))
    {
        playerGuess[i] = toupper(playerGuess[i]);
        i++;
    }

    i = 0;

    while(i < getSize(deck))
    {
        tempCard = getCardAtIndex(deck, i);
        if(checkCard(tempCard, playerGuess))
        {            
            return true;
        }
        i++;
    }
    
    return false;
}

//draws five cards form the main deck
void drawFive(Deck deck, Deck playerDeck)
{
    int count = 0;
    if(getSize(deck) > 0)
    {
        while(count < 5)
        {
            if(getSize(deck) == 0)
            {
                break;
            }

            Card tempCard = draw(deck);
            insertIntoHand(playerDeck, tempCard);
        }
    }
}

//transfers cards form one deck to another
void transferCards(Deck fromDeck, Deck toDeck, int position)
{
    Card tempCard = getCardAtIndex(fromDeck, position);
    insertIntoHand(toDeck, tempCard);
    removeCardFromDeck(fromDeck, position);
}

//checks if card matches the player's guess
bool checkCard(Card card, char *playerGuess)
{
    if(strcmp(getRankName(card), playerGuess) == 0)
    {
        return true;
    }
    return false;
}

//checks if a book exists in a deck
void checkForBook(Game game, Deck deck)
{
    int matched = 0;
    int count = 0;
    
    while(count < (getSize(deck) - 1))
    {
        Card tempCard = getCardAtIndex(deck, count);
        if(getRank(tempCard) == getRank(getNextCard(tempCard)))
        {
            matched++;
            if(matched == 3)
            {
                if(getOwner(deck) == 1)
                {
                    addToBook(deck, game->playerBooks, (count - 2));
                }
                else
                {
                    addToBook(deck, game->computerBooks, (count - 2));
                }
            }
        }
        else
        {
            matched = 0;
        }

        count++;
    }
}

//adds cards to a book
void addToBook(Deck deck, Deck bookDeck, int position)
{
    int count = 0;

    while(count < 4)
    {
        Card tempCard = getCardAtIndex(deck, position);
        prependToDeck(bookDeck, getSuit(tempCard), getRank(tempCard));
        removeCardFromDeck(deck, position);
        count++;
    }
    sort(bookDeck);
}

//calculates the winner
void calculateWinner(Game game)
{
    if(getSize(game->computerBooks) < getSize(game->playerBooks))
    {
        printf("\n\n--------YOU HAVE WON!!!!!--------\n\n");
        printf("Your books\n");
        printAll(game->playerBooks);

        printf("\nComputer Books\n");
        printAll(game->computerBooks);
    }
    else
    {
        printf("\n\n--------YOU HAVE LOST!!!!!--------\n\n");
        printf("Your books\n");
        printAll(game->playerBooks);

        printf("\nComputer Books\n");
        printAll(game->computerBooks);
    }
}

void printPlayerHand(Deck deck)
{
    printf("Player Hand: \n");
    printAll(deck);
}

void printComputerHand(Deck deck)
{
    printf("Computer's Hand: \n");
    printAll(deck);
}

void printBook(Deck deck)
{
    printf("Cards in the Book");
    printAll(deck);
}

void freeGame(Game game)
{
    free(game);
}

void closeGame()
{
    printf("\n\n-----------------------Goodbye!----------------------\n\n");
    return(0);
}
