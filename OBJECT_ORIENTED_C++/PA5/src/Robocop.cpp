#include "Human.h"
#include "Robocop.h"

//constructors
Robocop::Robocop():Human(){Robot::setStrength(30);	Robot::setHit(40);}
Robocop::Robocop(int newType,int newStrength, int newHit,string newName):Human(newType,newStrength,newHit,newName){}

//This function returns the damage this robot can inflict in one round of combat.
int Robocop::getDamage()	
{	
	int damage;
	
	if(rand()%100<10)	
	{	
		damage=((rand() % Human::getStrength()) + 1)+50; 
	}
	else
	{	
		damage=(rand() % Human::getStrength()) + 1 ;
	}
		cout << " with " << damage <<  endl;

	return damage;
}

//returning the type in string form
string Robocop::getType()
{
	return "robocop";
}

