#include<iostream>

using namespace std;

int main()
{	
    //Variable declarations
    int n;
    int i; 
    bool isprime=true;

    // Outer loop,the range will be checked (3 to 100)
    for(n=3 ;n<= 100; n++)
    {
        

        //Inner loop tests whether n is prime or not
        for(i=2; i <= n-1 ; i++)
        {
            //  if n/i has a remainder, then this number (n) is not prime
            if(n % i == 0 )
            {
                isprime = false;
                break;
            }
            else    //if reaminder is not 0(zero), then the number is prime
            {
                isprime = true;                     
            }
        }
        //Check the situation of isprime 
        if(isprime)
        {
            // printing the all prime numbers
            cout<< n << " is a prime number."<<endl;
        }
        
    }
    return 0;
}
 
