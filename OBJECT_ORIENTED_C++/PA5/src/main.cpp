//header files
#include "Robot.h"
#include "Bulldozer.h"
#include "Roomba.h"
#include "Human.h"
#include "Optimusprime.h"
#include "Robocop.h"

//some libraries
#include <cstdlib>
#include <ctime>
#include <vector>
#include <iostream>
using namespace std;

const int initial_count_of_each_robot_type = 5;

//main function
int main()
{	
	srand(time(NULL));
	
	//create 5 objects for each robot 
	Robot* bulldozer[initial_count_of_each_robot_type];
	Robot* roomba[initial_count_of_each_robot_type];
	Robot* robocop[initial_count_of_each_robot_type];
	Robot* optimusprime[initial_count_of_each_robot_type];
	Robot* r;

	World o;	//world class object
	
	//initalizing the robots
	bulldozer[0] = new Bulldozer(3,20,60,"bulldozer_1");
	roomba[0] = new Roomba(1,6,25,"roomba_1");
	robocop[0] = new Robocop(2,7,50,"robocop_1");
	optimusprime[0] = new Optimusprime(0,47,27,"optimusprime_1");

	bulldozer[1] = new Bulldozer(3,10,80,"bulldozer_2");
	roomba[1] = new Roomba(1,67,200,"roomba_2");
	robocop[1] = new Robocop(2,70,70,"robocop_2");
	optimusprime[1] = new Optimusprime(0,40,52,"optimusprime_2");

	bulldozer[2] = new Bulldozer(3,100,200,"bulldozer_3");
	roomba[2] = new Roomba(1,15,25,"roomba_3");
	robocop[2] = new Robocop(2,82,300,"robocop_3");
	optimusprime[2] = new Optimusprime(0,70,52,"optimusprime_3");

	bulldozer[3] = new Bulldozer(3,70,6,"bulldozer_4");
	roomba[3] = new Roomba(1,60,20,"roomba_4");
	robocop[3] = new Robocop(2,24,350,"robocop_4");
	optimusprime[3] = new Optimusprime(0,44,80,"optimusprime_4");
	
	bulldozer[4] = new Bulldozer(3,35,65,"bulldozer_5");
	roomba[4] = new Roomba(1,41,30,"roomba_5");
	robocop[4] = new Robocop(2,18,90,"robocop_5");
	optimusprime[4] = new Optimusprime(0,42,155,"optimusprime_5");

	


	//robots randomly place into grid via setInitial function
	 for(int i=0;i<5;i++)
	 {
	 	o.setInitial(bulldozer[i]);
		o.setInitial(roomba[i]);	
	 	o.setInitial(robocop[i]);
		o.setInitial(optimusprime[i]);	
	 }

	
	
	//taking the coordinatews of the robot object
	int x1=bulldozer[0]->getX();
	int y1=bulldozer[0]->getY();

	//and then taken robot objects' coordinates go via this function
	//after that this function returned which robot win the fight or moved into NULL cell
	string str = o.mo(x1,y1);


	int nX,nY;
	int c = o.counter(); //counting the not null cells in the grid

	
	while(true)
	{
		//the returning name of robot is checked in which coordinates and these values store in nX and nY 
		for(int i=0;i<5;i++)
		{
			
			if(str == bulldozer[i]->getName())	//comparing the returning name and robot objects in bulldozer type
			{
				nX= bulldozer[i]->getX();	
				nY= bulldozer[i]->getY();		
				break;
			}
			else if(str == roomba[i]->getName())		//comparing the returning name and robot objects in roomba type
			{
				nX= roomba[i]->getX();
				nY= roomba[i]->getY();
				break;
			}
			else if(str == robocop[i]->getName())		//comparing the returning name and robot objects in robocop type
			{
				nX= robocop[i]->getX();
				nY= robocop[i]->getY();
				break;
			}
			else if(str == optimusprime[i]->getName())		//comparing the returning name and robot objects in optimusprime type
			{
				nX= optimusprime[i]->getX();
				nY= optimusprime[i]->getY();
				break;
			}
		}

		str = o.mo(nX,nY);	
		c=o.counter();		//counting the not null cells in the grid

		if(c==1)
		{	
			//printing the final winner
			/*
			cout<<endl<<endl<<"#################################################"<<endl;
			cout<<"FINAL WINNER IS "<<str<<endl;
			cout<<"#################################################"<<endl<<endl;
			*/
			break;
		}
	}


	return 0;

}