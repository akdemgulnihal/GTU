#include<iostream>  //Librariers
#include<ctime>
#include<cstdlib>
#include<cstring>

using namespace std;
void errorMessage(int);    //Printing error message for the error types
int generateSecretNumber(int); //Generating secret number for -r type
void play_in_r_type(int);       //when the user want to play in -r type,this function will be run
void play_in_u_type(int );      //when the user want to play in -u type,this function will be run 
int userEntryForGuess(int);        //this function uses for the user input
void  checkUnique(int ,int );   //check the number is unique or not

//This function compare the user input and the secret number
void comparing_theUserEntry_and_secretNumber(int secretNumber, int userGuessNumber,int size);


int main(int argc, char **argv)
{
    if(argc != 3)   //If the number of strings pointed to by argv is not equal 3
    {               //Then the printing this message
        errorMessage(0);   
    }
    else        //If the number of strings pointed to by argv is equal to 3
    {
        
        srand(time(NULL));
        if(!strcmp(argv[1],"-r")  )     //If argv[1] is entered -r
        {
            //If the command line argument is not integer
            for(int i=0;argv[2][i]!='\0';i++)
            {
                if(!(argv[2][i] >= '0' && argv[2][i] <= '9') && !(argv[2][0] <= '-'))
                {
                    errorMessage(0);
                    exit(0);
                }
            }
            
            //If the command line argument is started with zero or minus then this condition will be run.
            if((argv[2][0] == '0' || argv[2][0] <= '-' ))
                errorMessage(0);
            else
            {
                int size = atoi(argv[2]);   //string converting to integer
                if(size<0 || size>=10)
                {
                    errorMessage(0);
                    exit(0);
                }

                play_in_r_type(size);       //game is running in r type
            }
            

        }
        else if(!strcmp(argv[1],"-u"))  //If argv[1] is entered -u
        {   int i=0; 
            bool fl;
            //secret number's each character will be checked for having any charcter except integers
            for(; argv[2][i]!='\0';i++)
            {
                if(argv[2][i] >= 48  && argv[2][i] <= 57 || argv[2][0]=='-')
                {
                    fl=true;    
                }
                else    //if secret number in command line argument is not integer
                {
                    fl=false;   //then flag is false
                    break;
                }
            }
            if(fl==false)   //when flag is false, then error message will be printed
            {
                errorMessage(0);
                exit(0);
            }
            //if command line argumnent is started with zero or minus, then this condition wi,ll be run
            if((argv[2][0] == '0' || argv[2][0] <= '-' )) 
            {
                 errorMessage(0);
                exit(0);
            }

            int secretNumber = atoi(argv[2]);   //command line argumnet converts to integer
            
            play_in_u_type(secretNumber);       //abd then, the program runs in -u type
        }
        else    //If except former condition is occured,then this blocks will be run
        {
            errorMessage(0);
        }
    }
    return 0;
}

void errorMessage(int errorType)   //Error types
{
    if(errorType == 0 )  cerr<<"E0"<<endl;
    else if(errorType == 1) cerr<<"E1"<<endl;
    else if (errorType == 2) cerr<<"E2"<<endl;
    else    cerr<<"E0"<<endl;
}

int generateSecretNumber(int size)
{
    int temp[size];
    bool flag;
    int check,i,j;
    int number;
   
    temp[0] = 1+rand()%9;   //First digit is generated there, because it has not to be zero.
    
    number = temp[0];       //First generated digit assign to number variable

    for(i=1 ; i<size ; i++)     //Other digit's for secret number
    {
        check = 0+rand()%10;    //Generating number
        for(j=0;j<i;j++)        //this loop for Checking the before digit whether unique or not 
        {
            if(check != temp[j])    //If this new generated digit is different from
            {                       //former digit, then flag will be true
                flag = true;
            }
            else                    //otherwise,flag will be false.
            {
                flag=false;
                break;
            }
        }   
        if(flag == true)        //if flag is true, then this generated digit stored in
        {                       //temp array which is for comparison digits
            temp[i] = check;
            number *= 10;         //Due to finding new digit,this digit ought to adding
            number += check;      //to secret number

        }
        else if(flag==false)    //when flag is false, then i should be decreased
        {                       //because this digit should be unique. 
            i--;
        }
    }
    return number;  //returning the generated number
}


int userEntryForGuess(int size) //this function,taking an integer from the user
{
    int number,NumberCopy;
    string num;
    cin>>num; 
    string temp;
    temp=num;
    //checking the user input has whether any character or not
    
    

     for(int i=0;temp[i]!='\0';i++)
    {
                 if(!(temp[i] >= '0' && temp[i] <= '9') && temp[0]!='-')
                {
                    errorMessage(2);    exit(0); 
                }
    }
    if(temp[0]=='-'){   errorMessage(0); exit(0);}
    number=stoi(num);    
    NumberCopy=number;
    int userEntrySize=0;

    do{     //finding the lenght of the number
        ++userEntrySize;
        NumberCopy/=10;
    }while(NumberCopy);
    
    //If user enter which has more or less than the size, then error message will be printed
    if(userEntrySize < size || userEntrySize > size )
    {
        errorMessage(1);    exit(0);
        }

    if(number==0)
    {
        errorMessage(0);
        exit(0);
    }

    return number;  // after that returning the integer
}

//Comparint the secret number and the user input
void comparing_theUserEntry_and_secretNumber(int secretNumber, int userGuessNumber,int size,int found) 
{
    string secretNum=to_string(secretNumber);   //converting secret number to string to compare each characters
    string userGuessNum=to_string(userGuessNumber); //converting user input to string to compare each characters

    int misplacedNumber=0,exactNumber=0; //This variables for comparasion result will be stored in there.

    for(int i=0; i<size; i++) //each characters are compared
    {
        if(userGuessNum[i]==secretNum[i])  {exactNumber++;} //when the user guess matchs in place of the secret number,then exact number increases 
        else{
            for(int j=0; j<size ;j++)
            {   //If user guess is correct but the place is not right, then misplaced number is increasing
                if(i!=j && (secretNum[i]==userGuessNum[j])) { misplacedNumber++;} 
            }
        }
        
    }
    if(exactNumber == size )    //when the secret number is found
    {                           //then this message will be run
        cout<<"FOUND "<< found <<endl;
        exit(0);
    }
    else if(found > 100)    //if the user entered more than 100 times
    {                       //then tis message will be run
        cerr<<"FAILED"<<endl;
        exit(0);
    }
    else
    {       //printing the exact and misplaced numbers
            cout<<exactNumber<<" "<<misplacedNumber<<endl;
    }

}

void play_in_r_type(int size)
{
    int found=0;
    //Call this function to generate the secret number
    int secretNumber = generateSecretNumber(size);
    
    do
    {   ++found; //each turn found number is increasing
        int userGuessNumber = userEntryForGuess(size); 
        
        checkUnique(userGuessNumber,size);  //Checking the number is unique or not
    
        //calling this function to compare the secret number and user input
        comparing_theUserEntry_and_secretNumber(secretNumber, userGuessNumber,size,found);
        
    }while(true);
}

void play_in_u_type(int secretNumber)
{
    int found=0;
    //Call this function to generate the secret number
    int secretNumberCopy=secretNumber;
    int size=0;
    
    do{     //finding the secret number lenght
        ++size;
        secretNumberCopy/=10;
    }while(secretNumberCopy);

    if(size<0 || size >=10)
    {
       errorMessage(0);
       exit(0);
      }
      

    checkUnique(secretNumber,size); //Checking the secret number is unique or not

    do{
        ++found;
        int userGuessNumber = userEntryForGuess(size);
       
        checkUnique(userGuessNumber,size);
      
        //calling this function to compare the secret number and user input
        comparing_theUserEntry_and_secretNumber(secretNumber, userGuessNumber,size,found);
        
    }while(true);
}

void  checkUnique(int userGuessNumber,int size)
{
    string checkNumber=to_string(userGuessNumber);

    if(checkNumber[size]!='\0') //If the number of digit of user entry is bigger than
    {                           // the generating number or secret number size, then E0 will be printed
        errorMessage(1);
        exit(0);
    }

        for(int i=0;i<size;i++)
        {   //checking the user input has any charcter except integers
            if(!(checkNumber[i] >= 48 && checkNumber[i] <= 57))
            {   //if it has ,then this error message will be run
                errorMessage(2);
                exit(0);
            }
            if(checkNumber[i]=='\0')    //If the number of digit of user entry is smaller than
            {                           //generating number or secret number, then E0 will be printed
                errorMessage(0);
                exit(0);
            }

            for(int j=i+1; j<size ;j++) //Checking the any digits of the number are same or not
            {
                if(checkNumber[i]==checkNumber[j])  //If any digits are same ,then error message will be printed
                {
                    errorMessage(0);
                    exit(0);
                }
            }
        }
}