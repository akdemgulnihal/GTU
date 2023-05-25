#include "Robot.h"
#include "Human.h"

//constructors
Human::Human():Robot(){}
Human::Human(int newType,int newStrength, int newHit,string newName):Robot(newType,newStrength,newHit,newName){}

