#include "Robot.h"
#include "Roomba.h"

//constructors
Roomba::Roomba():Robot(){Robot::setStrength(3);	Robot::setHit(10);}
Roomba::Roomba(int newType,int newStrength, int newHit,string newName):Robot(newType,newStrength,newHit,newName){}

//This function returns the damage this robot can inflict in one round of combat.
int Roomba::getDamage()
{	
	int damage=0;
	
	for(int i=0;i<2;i++)	
	{	
		damage += (rand() % Robot::getStrength()) + 1;	
	}
	
	cout <<  " with "<<  damage <<  endl;

	return damage;
}

//returning the type in string form
string Roomba::getType()
{
	return "roomba";
}
