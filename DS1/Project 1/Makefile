#Makefile

all:  client.o hangman.o
	gcc client.o hangman.o -o game.exe

client.o: hangman.c hangman.h
	gcc -Wall -c client.c

hangman.o:  hangman.c hangman.h
	gcc -Wall -c hangman.c

clean:	
	rm *.o game.exe
