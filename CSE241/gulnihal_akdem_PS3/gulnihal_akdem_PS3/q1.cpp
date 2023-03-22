#include<iostream>
#include<cstdlib>

using namespace std;



class HotDogStand
{
    public:
        HotDogStand();     //default constructor
        HotDogStand(int nID,int nNumberOfSold); //constructor which has parameters
        //Getters
        int getID();
        int getNumberSold();
        static int getTotatlSold();

        //setter
        void setID(int nID);
        void JustSold();
        
    private:
        int numberOfSold;    //number of sold
        int ID;         //hot dog stand id number
        static int totalSoldHotDog;
};

//Hint say that static variable must be initialized outside of the class definition
int HotDogStand::totalSoldHotDog = 0;

HotDogStand::HotDogStand(): numberOfSold(0),ID(0) {}

HotDogStand::HotDogStand(int nID,int nNumberOfSold): ID(nID),numberOfSold(nNumberOfSold){}

//Using getters because of private variavles
int HotDogStand::getID(){   return ID;  }
int HotDogStand::getNumberSold(){   return numberOfSold;    }

int HotDogStand::getTotatlSold(){    return totalSoldHotDog; }

void HotDogStand::setID(int nID){   ID=nID;     }   //Setting the ID

void HotDogStand::JustSold()    //when the stands sold  hotdog, the number of solding hotdog is increasing
{                               //the total number of solding hotdog is increasing,as well
    numberOfSold++;
    totalSoldHotDog++;
}

int main()
{
    HotDogStand obj1(1,0),obj2(2,0),obj3(3,0),obj4(4,0);

    //stands sold some hotdogs
    obj1.JustSold();
    obj2.JustSold();
    obj3.JustSold();

    //Printing the solding numbers
    cout<<"Stand Id:" <<obj1.getID()<<", sold:"<< obj1.getNumberSold() <<" hotdog."<<endl
        <<"Stand Id:"<<obj2.getID() <<", sold:"<< obj2.getNumberSold() <<" hotdog."<<endl
        <<"Stand Id:"<<obj3.getID() <<", sold:"<< obj3.getNumberSold() <<" hotdog."<<endl
         <<"Stand Id:"<<obj4.getID() <<", sold:"<< obj4.getNumberSold() <<" hotdog."<<endl
        <<"Total sold number is "<< HotDogStand::getTotatlSold()<<"."<<endl;

    //stands sold some hotdogs
    obj2.JustSold();
    obj3.JustSold();
    obj3.JustSold();
    obj3.JustSold();
    obj3.JustSold();
    obj1.JustSold();
    obj4.JustSold();

    //Printing the solding numbers
    cout<<endl<<"Stand Id:" <<obj1.getID()<<", sold:"<< obj1.getNumberSold() <<" hotdog."<<endl
        <<"Stand Id:"<<obj2.getID() <<", sold:"<< obj2.getNumberSold() <<" hotdog."<<endl
        <<"Stand Id:"<<obj3.getID() <<", sold:"<< obj3.getNumberSold() <<" hotdog."<<endl
         <<"Stand Id:"<<obj4.getID() <<", sold:"<< obj4.getNumberSold() <<" hotdog."<<endl
        <<"Total sold number is "<< HotDogStand::getTotatlSold()<<"."<<endl;

    return 0;
}