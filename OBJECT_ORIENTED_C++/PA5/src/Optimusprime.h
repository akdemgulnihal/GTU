#include "Human.h"

class Optimusprime:public Human
{
	public:
		//constructors
		Optimusprime();	
		Optimusprime(int newType,int newStrength, int newHit,string newName);	

		// Returns the damage this robot 
		int getDamage();	

		//returns the robot type
		string getType();	
};
