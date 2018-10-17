#Makefile

all:  client.o deck.o card.o game.o
	gcc client.o deck.o card.o game.o -o gofish.exe

client.o:  card.c card.h deck.c deck.h game.c game.h
	gcc -Wall -c -g client.c

card.o: card.c card.h
	gcc -Wall -c -g card.c

deck.o:  deck.c deck.h
	gcc -Wall -g -c deck.c

game.o:  game.c game.h
	gcc -Wall -c -g game.c

clean:	
	rm *.o *.exe
