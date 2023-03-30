#include<fcntl.h>
#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

#define FLAG ( O_CREAT | O_RDWR)	//Flag type
#define FLAG_W ( O_WRONLY)	

int main()
{
    //openining the file
    int fd1 = open("file.txt",FLAG,0644);
    if(fd1 ==-1)	//while opening if there is an error then this condition wil  be run
    {
    	perror("error!");
    	return -1;
    }
    
      //Writitng this string to the file
    write(fd1,"Testing the file.\n",18);
    
    int fd2 = dup(fd1);	//duplicating fd1 to fd2
    
    int fd3 = open("file.txt",FLAG_W,0644);
    
    printf("fd1-fd2\n");
    //seek the position in file descriptor
    if(lseek(fd1,0,SEEK_SET) == lseek(fd2,0,SEEK_SET) && fcntl(fd1,F_GETFL) == fcntl(fd2,F_GETFL))
    {
    	printf("Same\n");
    }
    else 
    	printf("It's not same.\n");
   
     printf("\nfd2-fd3\n");
     if(lseek(fd3,0,SEEK_SET) == lseek(fd2,0,SEEK_SET) && fcntl(fd3,F_GETFL) == fcntl(fd2,F_GETFL))
    {
    	printf("Same\n");
    }
    else 
    	printf("It's not same.\n");
    
   
    
    close(fd1);
    close(fd2);
    return 0;
}