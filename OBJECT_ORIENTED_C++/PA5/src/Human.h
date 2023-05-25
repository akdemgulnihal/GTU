#ifndef HUMAN_H
#define HUMAN_H

#include "Robot.h"

class Human: public Robot
{	
	public:
		//costructors
		Human();	
		Human(int newType,int newStrength, int newHit,string newName);

		int getDamage()=0;	

		
		
};

#endif