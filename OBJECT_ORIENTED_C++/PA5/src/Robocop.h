#include "Human.h"

class Robocop:public Human
{
	public:
		//constructors
		Robocop();	
		Robocop(int newType,int newStrength, int newHit,string newName);	

		// Returns the damage this robot 
		int getDamage();	
     	
		//returns the robot type
    	string getType();	
	
};
