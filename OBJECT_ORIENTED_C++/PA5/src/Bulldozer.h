#include "Robot.h"

class Bulldozer: public Robot
{	
	public:
		//constructor
		Bulldozer();
		//constructor which takes parameters	
		Bulldozer(int newType,int newStrength, int newHit,string newName);	

		// Returns the damage this robot 
		int getDamage();	

		//returns the robot type
		string getType(); 
		
};