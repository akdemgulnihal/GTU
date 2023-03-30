#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>

//Flags
#define FLAGS_OMITTED (O_WRONLY | O_CREAT)
#define FLAGS_A (O_WRONLY | O_CREAT | O_APPEND)

int main(int argc, char *argv[]) {
    if (argc < 3) 	//If command-line arguments number is less than 3
    {
        fprintf(stderr, "Usage: %s filename num-bytes [x]\n",argv[0]);	//Then how the command-line arguments should be entered will be printed
        exit(1);
    }

    char *filename = argv[1];	//Taking the file name from the command-line argumnert
    int numBytes = atoi(argv[2]);	//The argument converts to integer
    int omittedOrNot = argc >= 4 && argv[3][0] == 'x';
    int fd;

    
    if (omittedOrNot==0) 
    {
    	//Appending mode
        fd = open(filename, FLAGS_A , 0644);	//User: read & write, Group: Read , Other: read
    }
    else
    {
    	//Without x -> ommitted O_APPEND flag
    	fd = open(filename, FLAGS_OMITTED , 0644);
    }

   //While opening the file,if something is gone wrong. Then printing the message
    if (fd == -1) {
        perror("Open");
        exit(1);
    }

  
    /*off_t -> file offset,measured in bytes from the beginning of a file or
     device.IIn the programming environment which enables large files*/
    for (off_t offset = 0; offset < numBytes; offset++) {
    
    	char buf = rand() % '9' + '0';	//random character
    
        if (omittedOrNot==1) {
            if (lseek(fd, 0, SEEK_END) == -1) //lseek -> on error, the value(off_t) -1 is returned
            {
                perror("lseek");
                exit(1);
            }
        }
        if (write(fd, &buf, 1) == -1) //The write() function attempt to write nbyte bytes from the buffer pointed to by buf to the file associated with the open file descriptor
        {
            perror("write");
            exit(1);
        }
    }

    
    close(fd);
    return 0;
}
