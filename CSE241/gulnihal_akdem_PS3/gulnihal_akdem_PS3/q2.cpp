#include<iostream>
#include<vector>
#include<cstdlib>
using namespace std;

int main()
{
    int numberOfSuitors,position=0;

    cout<<"Enter the number of suitors: ";
    cin>>numberOfSuitors;

    vector<int> suitors(numberOfSuitors);   //Declaration of the vector(and its size taken from the user)

    for(int i=00; i < numberOfSuitors ; i++)
        suitors[i] = i+1;

    if(numberOfSuitors==0 || numberOfSuitors < 0)
    {
        cerr<<"There is no suitors or entered minus number."<<endl;
        exit(0);
    }
    
    else
    {   
        for(; 0 < suitors.size();  )
        {   
            //when position reaches the size of vector ,it should be rturn the beginning ,t
                //that's why position should be assign to zero    
            if(position == suitors.size())     position=0;  

            for(int i=0; i<2; i++)
            {
                position++; 

                //when position reaches the size of vector ,it should be rturn the beginning ,t
                //that's why position should be assign to zero                
                if(position == suitors.size())     position = 0;
            }

            //erasing the suitor
            suitors.erase(suitors.begin()+position);
            
        }   
        cout<<"winner suitor number is "<<suitors[0]<<"."<<endl;
    }

    return 0;
}