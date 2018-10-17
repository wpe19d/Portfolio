#Makefile

all:  client.o board.o
	gcc client.o board.o -o pegs.exe

client.o:  board.c board.h
	gcc -Wall -c -g client.c

board.o: board.c board.h
	gcc -Wall -c -g board.c

# deck.o:  deck.c deck.h
# 	gcc -Wall -g -c deck.c

# game.o:  game.c game.h
# 	gcc -Wall -c -g game.c

clean:	
	rm *.o *.exe
