#include "Robot.h"

class Roomba: public Robot
{	
	public:
		//constructors
		Roomba();	
		Roomba(int newType,int newStrength, int newHit,string newName);	

		// Returns the damage this robot 
		int getDamage();	

		//returns the robot type
		string getType();	
  
};