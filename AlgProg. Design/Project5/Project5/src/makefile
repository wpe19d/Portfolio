all: stocks.x

stocks.x: stats.o io.o utils.o
	ar cr ../lib/libstocks.a stats.o io.o utils.o
	rm *.o
	gcc stocks.c -o stocks.x -I../include -L../lib -lstocks -lm
	

stats.o: stats.c
	gcc -std=c99 -c stats.c -I../include

io.o: io.c
	gcc -std=c99 -c io.c -I../include

utils.o: utils.c
	gcc -std=c99 -c utils.c -I../include

clean:
	rm *.x ../lib/*.a
