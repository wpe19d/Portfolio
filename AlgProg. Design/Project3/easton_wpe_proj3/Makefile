all: stocks.x

stocks.x: stats.o stocks.o io.o
	gcc stats.o stocks.o io.o -o stocks.x

stats.o: stats.h stats.c
	gcc -c stats.c
	
io.o: io.c io.h
	gcc -c io.c

stocks.o: stats.h io.h stocks.c
	gcc -c stocks.c 

clean:
	rm *.x
