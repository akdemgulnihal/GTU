#ifndef ROBOT_H
#define ROBOT_H
#include <iostream>
#include <vector>
#include <string>

const int WORLDSIZE = 10;
using namespace std;

class Robot;	//forawrd class declaration

class World
{	
	public:
		//constructor
		World();
		//destructor
		~World();
	
		void printGrid();	//printing the grid

		void setInitial(Robot* rbt); 

		//getter
		Robot* getAt(int x, int y);
		void setAt(int x,int y,Robot *org);

		//function for move
		string mo(int ,int);

		//counting the not null values
		int counter();

	private:
		Robot* grid[WORLDSIZE][WORLDSIZE];
};


class Robot
{
	friend class World;
	private:

		int type;
		int strength;	
		int hitpoints;	
		string name;
		virtual string getType();	
	protected:
		int x,y;
        bool moved;
		
	public:
		//default constructor
		Robot();	
		//constructor
		Robot(int newType,int newStrength, int newHit,string newName);	
		virtual int getDamage()=0;	

		//getters of strength , hit and name
		int getStrength();
		int getHit();
		string getName(){ return name;}


		//set the strength and hit points
		void setStrength(int newStrength);
		void setHit(int newHit);
		
		//match function between robots
		friend string match(Robot* obj1,Robot* obj2);	
	   
	   //setter for coordinates
	   	void setX(int newX){x=newX;}
	   	void setY(int newY){y=newY;}

		//getters for coordinates
	   	int getX(){return x;}
	  	int getY(){return y;}
	   
	
};

#endif