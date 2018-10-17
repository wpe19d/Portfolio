#Makefile

all:  main.o ships.o game.o
	gcc main.o ships.o game.o -o battleship.exe

main.o: ships.c ships.h game.c game.h
	gcc -Wall -c main.c

ships.o:  ships.c ships.h
	gcc -Wall -c ships.c

game.o: game.c game.h
	gcc -Wall -c game.c

clean:	
	rm *.o *.exe
