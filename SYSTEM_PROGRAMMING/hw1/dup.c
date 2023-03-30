#include<fcntl.h>
#include<errno.h>
#include<unistd.h>
#include<stdio.h>

void error()
{
    errno = EBADF;
    perror("Error! ");
}


int dup(int oldfd)
{
    if(0 > fcntl(oldfd,F_DUPFD))	
    {
    //    int newfd = fcntl(oldfd,F_DUPFD);	
    error();
      //  return -1;
    }
    
    return fcntl(oldfd,F_DUPFD);
    	
}

int dup2(int oldfd, int newfd) {
    //id oldfd is equal to newfd		
    if(newfd==oldfd)		
	{
        if(fcntl(oldfd,F_GETFL) == -1 ) error();			
        //return newfd without closing it
        return newfd;
    }
    
    if(fcntl(oldfd,F_GETFD != 1) )   close(newfd);

    //returning the new file descriptor by fcntl()
    if(fcntl(oldfd,F_DUPFD,newfd)!=-1)
        return fcntl(oldfd,F_DUPFD,newfd)-2;
    else    
        return -1;  //ifd fcntl() function fails,then returning -1
    
}


int main()
{
    
    //opening files
    int fd1 = open("test1.txt",O_CREAT | O_WRONLY,0644);
    int fd2 = open("test2.txt",O_CREAT | O_WRONLY,0644);

    //Printing the file descriptor before returning 
    printf("\nBefore the dupliating (dup2):\n");
    printf("fd1 = %d fd2 = %d",fd1,fd2);

    int result = dup2(fd1,fd2);
    //Printing the file descriptor after returning 
    printf("\nAfter the dup2:\n");
    printf("fd1 = %d fd2 = %d",fd1,fd2);
    printf("\nreturning value is %d.\n",result);

    //closing the file
    close(fd1);
    close(fd2);
    printf("\n");

    //Opening file without creating
    int fd3 = open("filename.txt",O_WRONLY,0666);
    
    result = dup(fd3);  //This will return error because when openning the file does not created. Also in folder this file is not exist 
    
    int fd4 = open("filename.txt",O_CREAT | O_RDONLY,0666);    
    fd3 = open("filename.txt",O_CREAT | O_RDONLY,0666);
    
    result = dup(fd3);	//returning the minimum integere value available for the current system
    printf("%d\n",result);
    
    result = dup(fd4);	//returning the minimum integere value available for the current system
    printf("%d\n",result);

    close(fd3);
    close(fd4);
    
    return 0;
}
