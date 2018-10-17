CC = gcc
CFLAGS = -Wall -I../include
LIBS = -lstocks -lm
LFLAGS = -L../lib


#	$@ is the name of the file to be made.
#	$? is the names of the changed dependents.

stocks.x: stocks.c 
	$(CC) $(CFLAGS) $? $(LFLAGS) $(LIBS) -o $@ 


.PHONY:clean

clean:
	rm *.o *.x
 



#all: stocks.x

#stocks.x: stats.o stocks.o io.o utils.o
	#ar cr  ../lib/libstocks.a utils.o stats.o io.o
	#rm stats.o io.o utils.o 
	#gcc -std=c99 stocks.o -o stocks.x -L../lib/ -lstocks -lm
	#gcc stocks.c -o stocks.x -I../include -L../lib -lstocks -lm


#stats.o: stats.c
	#gcc -std=c99 -c stats.c -I../include

#io.o: io.c 
	#gcc -std=c99 -c io.c -I../include

#utils.o: utils.c 
	#gcc -std=c99 -c utils.c -I../include

#stocks.o: stocks.c
#	gcc -std=c99 -c stocks.c -I../include

#.PHONY: clean

#clean:
	#rm *.x *.o ../lib/libstocks.a
