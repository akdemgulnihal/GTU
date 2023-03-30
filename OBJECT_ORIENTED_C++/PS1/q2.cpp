#include<iostream>
#include<cstdlib>
#include<ctime>

using namespace std;

int rolling()
{
    int rollingDice = 1+ rand() % 6 ;
    return rollingDice ;
}

/*
    humanTotalScore is the score of the beginning 
    and after holding or rolling the new dice affects the score, so new score will be returning 
*/

int humanTurn(int humanTotalScore)
{
    int score;  //the score of this round
    char ch;
    bool humanTurnOver = false ;

    cout<<"It's player turn! Enter 'r' to roll.\n";
    cin>>ch;

    score=0; 
    do
    {
        //firtly running the dice
        int roll = rolling();
        
        cout<<"You rolled ' "<< roll <<" '"<<endl;
        //Check the rolled result is 1 or not
        if(roll == 1)
        {
            cout<<"You lose your turn. "<<endl;
            return 0;	//Player cannot take any points.
        }
        else
        {
            score = score + roll; //score and the rolling result are adding
            cout<< "Press 'h' to hold or 'r' to roll again."<< endl;
            
            cin>> ch;	//Taking cahr input for holding or rolling the dice again

            if(ch == 'h') //When the user choose hold, the user turn would be done
            {
                humanTurnOver = true;
            }
            //Do not need for 'r' condition because when user entered 'r'
            //if condition does not occur, so the humanTurnOver is still false
            //then the do while is not done, loop begins again. (also dice will be roll at the beginning of loops)
        }
    } while (humanTurnOver!=true);
    return score;
}

int computerTurn(int computerTotalScore)
{
    int score;  //this round scores
    char ch;
    bool computerTurnOver = false ;    

    cout<< "It's the computer's turn!"<<endl;

    score=0;
    do
    {
        
        int roll = rolling();
        cout<<"The computer rolled ' "<< roll<<" '" <<endl;
        if(roll == 1)
        {
            cout<<"The computer loses its turn. \n";
            return 0;
        }   
        else
        {
            score += roll;
            
            //The score is accumalated until computer score is 20 or more than 20.
            //Also,total scores will be checked. (whether is equal 100 or more than 100)
            if((score >= 20) || (score + computerTotalScore >= 100))
            {
                //computer holds
                cout<<"The computer holds. "<< endl;
                computerTurnOver = true;
            }
        }
    } while (!computerTurnOver);
    return score;
}

int main()
{
//Variable declarations
int humanScore=0, computerScore=0;
bool game=false;

srand(static_cast<int>(time(NULL)));

while (game!=true)
{
    // the first move from human
    humanScore = humanScore + humanTurn(humanScore);
    cout<<"\nYour score :"<<humanScore<<endl<<endl;
    // if the game is not finish, then the computer moves begin
    if(humanScore < 100)
        computerScore += computerTurn(computerScore);
    cout<<"\nComputer score :"<<computerScore<<endl<<endl;

    // Checking the point for game over
    if(humanScore >=  100)
    {
        cout<<" You win!"<<endl;
        game=true;
    }
    else if(computerScore >= 100)
    {
        cout<<"The computer won."<<endl;
        game=true;
    }
}

}

