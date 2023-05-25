#include "Robot.h"
#include "Bulldozer.h"

//constructors
Bulldozer::Bulldozer():Robot(){ Robot::setStrength(50);	Robot::setHit(200);} 
Bulldozer::Bulldozer(int newType,int newStrength, int newHit,string newName):Robot(newType,newStrength,newHit,newName){}

//This function returns the damage this robot can inflict in one round of combat.
int Bulldozer::getDamage()		
{
	int damage;

	// Bulldozer robot inflicts damage that is a random number r, where 0 < r <= strength
	damage = (rand() % Robot::getStrength()) + 1;	
	
	cout <<  " with "<<  damage <<  endl;

	return damage;
}

//returning the type in string form
string Bulldozer::getType()
{
	return "bulldozer";
}


