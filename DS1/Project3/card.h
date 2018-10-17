#ifndef CARD_H
#define CARD_H

typedef struct card *Card;

enum _suit { HEARTS, SPADES, CLUBS, DIAMONDS };

enum _rank { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, JACK, QUEEN, KING, ACE };

Card createEmptyCard();

Card createCard(enum _suit suit, enum _rank rank, Card nextCard);

Card getNextCard(Card card);

int getSuit(Card card);

int getRank(Card card);

void setValues(Card card, enum _suit suit, enum _rank rank);

void setNextCard(Card card, Card nextCard);

const char* getSuitName(Card card);

const char* getRankName(Card card);

void freeCard(Card card);

#endif