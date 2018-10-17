#ifndef DECK_H
#define DECK_H

typedef struct deck *Deck;

Deck createEmptyDeck();

Deck createFullDeck();

void prependToDeck(Deck deck, int suit, int rank);

void appendToDeck(Deck deck, int suit, int rank);

void insertIntoDeck(Deck deck, int position, Card card);

void shuffle(Deck deck, int times);

void sort(Deck deck);

void mergeSort(Card *head);

void split(Card head, Card *cardOne, Card *cardTwo);

Card merge(Card cardOne, Card cardTwo);

Card draw(Deck deck);

void insertIntoHand(Deck deck, Card newCard);

void removeCardFromDeck(Deck deck, int position);

int getSize(Deck deck);

int getOwner(Deck deck);

void setOwner(Deck deck, int owner);

Card getCardAtIndex(Deck deck, int position);

void printAll(Deck deck);

void freeDeck(Deck deck);

#endif
