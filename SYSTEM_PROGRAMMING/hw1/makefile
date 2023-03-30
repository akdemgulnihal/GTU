CC = gcc
CFLAGS = -Wall 

all: clean clear appendMeMore dup part3

appendMeMore: appendMeMore.c
	@echo "Compiling the part1..."
	@$(CC) $(CFLAGS) -o $@ $^
runAppendMeMore:
	@echo "Running...\n"
	@./appendMeMore f1 1000000 & ./appendMeMore f1 1000000
	@./appendMeMore f2 1000000 x & ./appendMeMore f2 1000000 x
list:
	@echo "Listing the files."
	@ls -l f1 f2
	
dup: dup.c
	@echo "\nCompiling the part2...\n"
	@$(CC) $(CFLAGS) -o $@ $^
runDup:
	@echo "Running..."
	@./dup
	
part3: part3.c
	@echo "Compiling the part3...\n"
	@$(CC) $(CFLAGS) -o $@ $^
runPart3:
	@echo "Running...\n"
	@./part3
	
clean:
	@rm -f f1 f2 appendMeMore dup part3 *.txt
clear:
	@clear
