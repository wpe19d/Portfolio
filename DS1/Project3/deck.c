#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "card.h"
#include "deck.h"

struct deck
{
    Card head;
    Card tail;
    int size;
    //0 = none, 1 = player, 2 = computer.
    int owner;
};

Deck createEmptyDeck()
{
    Deck newDeck = malloc(sizeof(*newDeck));
    newDeck->head = NULL;
    newDeck->tail = NULL;
    newDeck->size = 0;
    newDeck->owner = 0;

    return newDeck;
}


Deck createFullDeck()
{
	Deck newDeck = createEmptyDeck();
    int i = 0;
    int j = 0;

    for(i = 0; i < 4; i++)
    {
        for(j = 0; j < 13; j++)
        {
            prependToDeck(newDeck, i, j);
        }
    }
    
    return newDeck;
}

//adds to the beginning of the deck
void prependToDeck(Deck deck, int suit, int rank)
{
    Card newCard = createCard(suit, rank, NULL);
    setNextCard(newCard, deck->head);
    deck->head = newCard;
    if(deck->tail == NULL)
    {
        deck->tail = newCard;
    }
    deck->size++;
}

//adds to the end of the deck
void appendToDeck(Deck deck, int suit, int rank)
{
    Card newCard = createCard(suit, rank, NULL);
    deck->size++;

    if(deck->tail == NULL)
    {
        deck->head = newCard;
        deck->tail = newCard;
        return;
    }
    setNextCard(deck->tail, newCard);
    deck->tail = newCard;

}

//inserts card a certain 'index' in the linked list
void insertIntoDeck(Deck deck, int position, Card card)
{
    int currentPosition = 0;

    if(position == 0)
    {
        prependToDeck(deck, getSuit(card), getRank(card));
        return;
    }

    if(position == deck->size)
    {
        appendToDeck(deck, getSuit(card), getRank(card));
        return;
    }

    if((position < 0) || (position > deck->size))
    {
        return;
    }

    Card newCard = createCard(getSuit(card), getRank(card), NULL);
    Card currentCard = deck->head;

    while(currentPosition++ != position - 1)
    {
        currentCard = getNextCard(currentCard);
    }

    setNextCard(newCard, getNextCard(currentCard));
    setNextCard(currentCard, newCard);
    deck->size++;
}

//Shuffles the cards
void shuffle(Deck deck, int times)
{
    int midpoint = deck->size / 2;
    int count = 0;
    Deck deckOne = createEmptyDeck();

    if(times > 0)
    {
        shuffle(deck, (times - 1));
    }

        while(count < midpoint)
        {
            Card tempCard = draw(deck);
            prependToDeck(deckOne, getSuit(tempCard), getRank(tempCard));
            count++;
        }

        count = 0;

        while(count < deckOne->size)
        {
            insertIntoDeck(deck, count + count, getCardAtIndex(deckOne, count));
            count++;
        }

        freeDeck(deckOne);
        return;
}

//Takes in a deck to be sorted.
void sort(Deck deck)
{
    mergeSort(&deck->head);
    deck->tail = getCardAtIndex(deck, (deck->size - 1));
    return;
}

//calls the split and merge methods to sort the linked list by rank
void mergeSort(Card *deckHead)
{
    Card head = *deckHead;
    Card cardOne;
    Card cardTwo;

    if((head == NULL) || (getNextCard(head) == NULL))
    {
        return;
    }
    split(head, &cardOne, &cardTwo);
    mergeSort(&cardOne);
    mergeSort(&cardTwo);

    *deckHead = merge(cardOne, cardTwo);
}

//splits the linked list apart
void split(Card head, Card *cardOne, Card *cardTwo)
{
    Card fast;
    Card slow;
    slow = head;
    fast = getNextCard(head);

    while(fast != NULL)
    {
        fast = getNextCard(fast);
        if(fast != NULL)
        {
            slow = getNextCard(slow);
            fast = getNextCard(fast);
        }
    }

    *cardOne = head;
    *cardTwo = getNextCard(slow);
    setNextCard(slow, NULL);
}

//sorts and merges linked list back together
Card merge(Card cardOne, Card cardTwo)
{
    Card result = createEmptyCard();
    if(cardOne == NULL)
    {     
        return cardTwo;
    }
    else if(cardTwo == NULL)
    {
        return cardOne;
    }
    if(getRank(cardOne) <= getRank(cardTwo))
    {
        result = cardOne;
        if(getNextCard(cardOne) == NULL)
        {
            setNextCard(result, merge(NULL, cardTwo));
        }
        else
        {
            setNextCard(result, merge(getNextCard(cardOne), cardTwo));
        }
    }
    else
    {
        result = cardTwo;
        if(getNextCard(cardTwo) == NULL)
        {
            setNextCard(result, merge(cardOne, NULL));
        }
        else
        {
            setNextCard(result, merge(cardOne, getNextCard(cardTwo)));
        }
    }
    return result;
}

//draws one card from the top of the deck
Card draw(Deck deck)
{
    if(deck->head != NULL)
    {
        Card tempCard = deck->head;
        removeCardFromDeck(deck, 0);
        return tempCard;
    }
}

//inserts a card into a sorted hand of cards
void insertIntoHand(Deck deck, Card newCard)
{
    int count = 0;
    bool inserted = false;
    
    while(count < deck->size)
    {
        Card tempCard = getCardAtIndex(deck, count);

        if(getRank(newCard) <= getRank(tempCard))
        {
            insertIntoDeck(deck, count, newCard);
            inserted = true;
            break;
        }
        count++;
    }

     if(!inserted)
     {
         appendToDeck(deck, getSuit(newCard), getRank(newCard));
     }
}

//removes a card from a deck.
void removeCardFromDeck(Deck deck, int position)
{
    int currentPosition = 0;

    if(!(deck->size > 0) || (position < 0) || (position >= deck->size))
    {
        printf("ERROR:  Trying to remove Card at invalid position\n");
        exit(1);
    }
    deck->size--;

    if(position == 0)
    {
        deck->head = getNextCard(deck->head);
        return;
    }

    Card currentCard = deck->head;

    while(currentPosition < (position -1))
    {
        currentPosition++;
        currentCard = getNextCard(currentCard);
    }

    if(position == deck->size)
    {
        deck->tail = currentCard;
    }

    Card tempCard = getNextCard(currentCard);
    setNextCard(currentCard, getNextCard(getNextCard(currentCard)));
    freeCard(tempCard);
}

int getSize(Deck deck)
{
    return deck->size;
}

int getOwner(Deck deck)
{
    return deck->owner;
}

void setOwner(Deck deck, int owner)
{
    deck->owner = owner;
}

//retrieves a card at a certain position (index) in the linked list
Card getCardAtIndex(Deck deck, int position)
{
    int i = 0;
    Card currentCard = deck->head;

    for(i = 0; i < position; i++)
    {
        currentCard = getNextCard(currentCard);
    }

    return currentCard;
}

//prints all cards in a deck
void printAll(Deck deck)
{
    int count = 0;
    Card currentCard = deck->head;
    printf("\n");
    while(currentCard != NULL)
    {
        Card nextCard = getNextCard(currentCard);

        printf("%s ", getSuitName(currentCard));
        printf("%s  |  ", getRankName(currentCard));

        count++;
        
        if(count == 7)
        {
            printf("\n\n");
            count = 0;
        }

        currentCard = nextCard;
    }
    printf("\n");
    
}

void freeDeck(Deck deck)
{
     Card currentCard = currentCard = deck->head;;
    
    while(currentCard != NULL)
    {
        Card nextCard = getNextCard(currentCard);
        free(currentCard);
        currentCard = nextCard;
    }

    free(deck);
}