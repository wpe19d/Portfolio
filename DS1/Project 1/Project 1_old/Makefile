#Makefile

all:  game.o hangman.o
	gcc game.o hangman.o -o game.exe

game.o: hangman.c hangman.h
	gcc -Wall -c game.c

hangman.o:  hangman.c hangman.h
	gcc -Wall -c hangman.c

clean:	
	rm *.o game.exe
