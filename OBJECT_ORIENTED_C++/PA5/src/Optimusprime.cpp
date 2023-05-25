#include "Human.h"
#include "Optimusprime.h"

//constructors
Optimusprime::Optimusprime():Human(){Robot::setStrength(100);	Robot::setHit(100);}
Optimusprime::Optimusprime(int newType,int newStrength, int newHit,string newName):Human(newType,newStrength,newHit,newName){}

//This function returns the damage this robot can inflict in one round of combat.
int Optimusprime::getDamage()	
{
	int damage;

	if(rand()%100<10)	
	{	
		damage=((rand() % Human::getStrength()) + 1)+50;
		
		if(rand()%100<15)	
			damage*=2;
	}
	else
	{	
		damage=(rand() % Human::getStrength()) + 1 ;
		
		if(rand()%100<15)	
			damage*=2;
	}
	
	cout <<  " with "<<  damage <<  endl;

	return damage;
}

//returning the type in string form
string Optimusprime::getType()
{
	return "optimusprime";
}

