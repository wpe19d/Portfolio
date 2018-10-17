#include <stdlib.h>
#include "card.h"
#include "deck.h"

struct card
{
    enum _suit suit;
    enum _rank rank;
    Card nextCard;
};

Card createEmptyCard()
{
    Card newCard = malloc(sizeof(*newCard));

    return newCard;
}

Card createCard(enum _suit suit, enum _rank rank, Card nextCard)
{
    Card newCard = malloc(sizeof(*newCard));

    newCard->suit = suit;
    newCard->rank = rank;
    newCard->nextCard = nextCard;

    return newCard;
}

Card getNextCard(Card card)
{
    return card->nextCard;
}

int getSuit(Card card)
{
    return card->suit;
}

int getRank(Card card)
{
    return card->rank;
}

void setValues(Card card, enum _suit suit, enum _rank rank)
{
    card->suit = suit;
    card->rank = rank;
}

void setNextCard(Card card, Card nextCard)
{
    card->nextCard = nextCard;
}

//returns the Suit name in string form
const char* getSuitName(Card card)
{
    switch(card->suit)
    {
        case HEARTS : return "HEARTS";
        case SPADES : return "SPADES";
        case CLUBS : return "CLUBS";
        case DIAMONDS : return "DIAMONDS";
        default : return "NOT FOUND";
    }
}

//returns the Rank name in string form
const char* getRankName(Card card)
{
    switch(card->rank)
    {
        case ONE : return "ONE";
        case TWO : return "TWO";
        case THREE : return "THREE";
        case FOUR : return "FOUR";
        case FIVE : return "FIVE";
        case SIX : return "SIX";
        case SEVEN : return "SEVEN";
        case EIGHT : return "EIGHT";
        case NINE : return "NINE";
        case JACK : return "JACK";
        case QUEEN : return "QUEEN";
        case KING : return "KING";
        case ACE : return "ACE";
        default : return "NOT FOUND";
    }
}

void freeCard(Card card)
{
    free(card);
}
