#include "Robot.h"

//Creating grid with null value
World::World()
{
    for(int i=0; i<WORLDSIZE;i++)
    {
        for (int j = 0; j < WORLDSIZE; j++)
        {
            grid[i][j] = NULL;
        }
        
    }
}

//destruct the grid
World::~World()
{
    for(int i=0; i<WORLDSIZE;i++)
    {
        for (int j=0; j < WORLDSIZE; j++)
        {
            if(grid[i][j] != NULL)  delete(grid[i][j]);
        }        
    }
}

//Pirnting the grid with values
void World::printGrid()
{
	 for(int i=0; i<WORLDSIZE;i++)
    {
        for (int j=0; j < WORLDSIZE; j++)
        {
            if(grid[i][j] != NULL) { cout<<grid[i][j]->getName()<<"        ";}
			else	cout<<".        ";
        }        
		cout<<endl<<endl;
    }
}

//Counting the not empty cells in grid
int World::counter()
{
	int cTemp=0;
	 for(int i=0; i<WORLDSIZE;i++)
    {
        for (int j = 0; j < WORLDSIZE; j++)
        {
            if(grid[i][j] != NULL)
			{
				cTemp++;
			}
        }
        
    }
	return cTemp;
}


//This function about movement of the robot
//these parameters are about coordinate for grid
// dir:  taking the random number for direction
// first four if statements for when grid is empty 
// other four if statements for when grid is not empty
//when cell is empty/null robot directly go there
//but if cell is not null, then the robot fight the other robot , until one robot will be dead
// and the winner robot name will be returned
string World::mo(int x,int y)
{
	int dir = rand() % 4;	//taking random number , the range is between 0 to 4

	//when dir = 0, and the below row is null and x is not equal 9
	// then this statement will be run
	if(dir == 0 && grid[x+1][y] == NULL && x != 9)	//down
	{	
		grid[x][y]->setX(x+1);	//due to changing the row, modifying the x in grid
		grid[x+1][y] = grid[x][y];	//then this grid move to empty cell
		grid[x][y] = NULL; 			//and the displaced objects' old place become null
		
		return grid[x+1][y]->getName();	// and returning the name of robot
	}
	//when dir = 1, and the top row is NULL and x is not equal 0
	// then this statement will be run
	else if(dir==1 && grid[x-1][y] == NULL && x != 0)	//up
	{
		grid[x][y]->setX(x-1);	//due to changing the row, modifying the x in grid
		grid[x-1][y] = grid[x][y];	//then this grid move to empty cell
		grid[x][y] = NULL; 			//and the displaced objects' old place become null
		
		return grid[x-1][y]->getName();	// and returning the name of robot
	}
	//when dir = 2, and the next column is NULL and y is not equal 9
	// then this statement will be run
	else if(dir==2 && grid[x][y+1] == NULL && y != 9)	//right
	{
		grid[x][y]->setY(y+1);	//due to changing the column, modifying the y in grid
		grid[x][y+1] = grid[x][y];	//then this grid move to empty cell
		grid[x][y] = NULL; 			//and the displaced objects' old place become null
		
		return grid[x][y+1]->getName();	// and returning the name of robot
	}
	//when dir = 3, and the former column is NULL and y is not equal 9
	// then this statement will be run
	else if(dir==3 && grid[x][y-1] == NULL && y != 0)	//left
	{		
		grid[x][y]->setY(y-1);	//due to changing the row, modifying the y in grid
		grid[x][y-1] = grid[x][y];	//then this grid move to empty cell
		grid[x][y] = NULL;			//and the displaced objects' old place become null

		return grid[x][y-1]->getName(); 	// and returning the name of robot
	}


	//when dir = 0, and the below row is NOT NULL and x is not equal 9
	// then this statement will be run
	else if(dir == 0 && grid[x+1][y] != NULL && x != 9)	//down
	{
		//creating temporary robot objects  
		Robot * r1 = grid[x][y];
		Robot * r2 = grid[x+1][y];

		/*
		cout<<endl<<"---------------------------------------"<<endl;
		cout<<r1->getName()<<endl <<r2->getName()<<endl;
		cout<<"---------------------------------------"<<endl;
		*/

		// figth the robots via this function and the winner robots' name returned
		string str = match(r1,r2);

		//if the winner robot name is same as in r1 objects' name
		if(str == r1->getName())
		{
			grid[x][y]->setX(x+1);	//due to changing the row, modifying the x in grid
			grid[x+1][y] = grid[x][y];	//then this grid[x][y] assined to the grid[x+1][y] 
			grid[x][y] =NULL;	//and the displaced objects' old place become null
			
			return grid[x+1][y]->getName();	// and returning the name of robot
		}
		else	//if the winner robot name is same as in r2 objects' name
		{		//the remove the r1 object from the grid
			grid[x][y]=NULL;
			
			return grid[x+1][y]->getName();	// and returning the name of robot
		}

	}
	else if(dir==1 && grid[x-1][y] != NULL && x != 0)	//up
	{
		//creating temporary robot objects  
		Robot * r1 = grid[x][y];
		Robot * r2 = grid[x-1][y];

		/*
		cout<<endl<<"---------------------------------------"<<endl;
		cout<<r1->getName()<<endl <<r2->getName()<<endl;
		cout<<"---------------------------------------"<<endl;
		*/

		// figth the robots via this function and the winner robots' name returned
		string str =match(r1,r2);
		
		//if the winner robot name is same as in r1 objects' name
		if(str == r1->getName())
		{
			grid[x][y]->setX(x-1);	//due to changing the row, modifying the x in grid
			grid[x-1][y]=grid[x][y];	//then this grid[x][y] assined to the grid[x-1][y]
			grid[x][y]=NULL;	//and the displaced objects' old place become null
			
			return grid[x-1][y]->getName();	// and returning the name of robot
		}
		else	//if the winner robot name is same as in r2 objects' name
		{		//the remove the r1 object from the grid
			grid[x][y]=NULL;	
		
			return grid[x-1][y]->getName();	// and returning the name of robot
		}
	}

	else if(dir==2 && grid[x][y+1] != NULL && y != 9)	//right
	{
		//creating temporary robot objects 
		Robot * r1 = grid[x][y];
		Robot * r2 = grid[x][y+1];

		/*
		cout<<endl<<"---------------------------------------"<<endl;
		cout<<r1->getName()<<endl <<r2->getName()<<endl;
		cout<<"---------------------------------------"<<endl;
		*/

		// figth the robots via this function and the winner robots' name returned
		string str =match(r1,r2);
		
		//if the winner robot name is same as in r1 objects' name
		if(str == r1->getName())
		{
			grid[x][y]->setY(y+1);	//due to changing the column, modifying the y in grid
			grid[x][y+1]=grid[x][y];	//then this grid[x][y] assined to the grid[x][y+1]
			grid[x][y]=NULL;	//and the displaced objects' old place become null
			
			return grid[x][y+1]->getName();	// and returning the name of robot
		}
		else	//if the winner robot name is same as in r2 objects' name
		{		//the remove the r1 object from the grid
			grid[x][y]=NULL;	
			
			return grid[x][y+1]->getName();	// and returning the name of robot
		}

	}
	else if(dir==3 && grid[x][y-1] != NULL && y != 0)	//left
	{		
		//creating temporary robot objects 
		Robot * r1 = grid[x][y];
		Robot * r2 = grid[x][y-1];

		/*
		cout<<endl<<"---------------------------------------"<<endl;
		cout<<r1->getName()<<endl <<r2->getName()<<endl;
		cout<<"---------------------------------------"<<endl;
		*/

		// figth the robots via this function and the winner robots' name returned
		string str =match(r1,r2);
		
		//if the winner robot name is same as in r1 objects' name
		if(str == r1->getName())
		{
			grid[x][y]->setY(y-1);	//due to changing the column, modifying the y in grid
			grid[x][y-1]=grid[x][y];	//then this grid[x][y] assined to the grid[x][y-1]
			grid[x][y]=NULL;
			
			return grid[x][y-1]->getName();	// and returning the name of robot
		}
		else	//if the winner robot name is same as in r2 objects' name
		{		//the remove the r1 object from the grid
			grid[x][y]=NULL;	
			
			return grid[x][y-1]->getName();	// and returning the name of robot
		}

	}
	else
	{
		return grid[x][y]->getName();	// and returning the name of robot
	}
}

//default constructor
Robot::Robot()
{
	type=4;
	strength=0;
	hitpoints=0;
	name = "unknown"; 
}

//constructor which has some parameters
Robot::Robot(int newType,int newStrength, int newHit,string newName)
{	
	//assigning the new valuew to class private variables
	type = newType;	
	strength=newStrength;
	hitpoints=newHit;
	name = newName;
}

// In the main, after creating objects, then this function calls
// To place all robots to grid randomly until all robots places to the grid   
void World::setInitial(Robot* rbt)
{
	bool flag = false;	//this flag for cell is null or not,
	while(flag != true)	//until robot is placing the empty cell
	{
		int x = rand() % 10;	//taking a random number for row number
		int y = rand() % 10;	//taking a random number for number number
		
		if(grid[x][y] == NULL)	//when cell is empty
		{
			grid[x][y] = rbt;	//then the object assingned to this cell
			flag = true;				
			rbt->setX(x);		//setting the x value
			rbt->setY(y);		//setting the y value
		}
	}
	
}

//getter to return grid or null value
Robot* World::getAt(int x,int y)
{
    if((x >=0) && (x < 10) && (y >= 0) && (y <10) )
        return grid[x][y];
    return NULL;
}

//setter for grid
void World::setAt(int x,int y,Robot *org)
{
    if((x >=0) && (x < 10) && (y >= 0) && (y <10))
        grid[x][y]=org;
}




string Robot::getType()
{
	return "unknown";
}

//getter for strenght
int Robot::getStrength(){ return strength; }

//getter for hitpoint
int Robot::getHit(){ return hitpoints; }

//setting the strength
void Robot::setStrength(int newStrength){ strength=newStrength; }

//setting the hitpoint
void Robot::setHit(int newHit){ hitpoints=newHit; }


//two robot fight int his function
string match(Robot* obj1,Robot * obj2)	
{	
	bool isFirstStage=true;
	int obj1Damage,obj2Damage;	//damage values stored in this variable
	int hitPoints1= obj1->getHit();	//getting the hitpoint of obj1 and stored in hitPoint2
	int hitPoints2= obj2->getHit();	//getting the hitpoint of obj2 and stored in hitPoint2

	cout<<endl<<endl;
	
	while(hitPoints1>0 && hitPoints2>0)	
	{	
		if(hitPoints1<0 || hitPoints2<0)
		{break;}

		//printing the which robot attack which robot 
		cout<<obj1->getName()<<"("<<obj1->getHit()<<") hits ";
		cout<<obj2->getName()<<"("<<obj2->getHit()<<")";	
		
		//first attacker damage value is calculating and stored in obj1Damage
		obj1Damage=obj1->getDamage();
		//then damage value substract from hitPoints2
		hitPoints2-=obj1Damage;	
		
		//setting the hitpoint for obj2
		obj2->setHit(hitPoints2);
		

		//the new hitpoints of obj2 is printed.
		cout<<"The new hitpoints of "<<obj2->getName()<< " is "<< hitPoints2<<endl;

		if(obj1->getType()=="roomba")
		{
			if(hitPoints2<=0 )	
			{
				//then the winner is first robot and victim is second robot
				//cout<<endl<<"Winner of this round is "<<obj1->getName()<<endl<<endl;
				cout<<obj2->getName()<<" is dead."<<endl;
				return obj1->getName();
				break;
			}

			//printing the which robot attack which robot 
			cout<<obj1->getName()<<"("<<obj1->getHit()<<") hits ";
			cout<<obj2->getName()<<"("<<obj2->getHit()<<")";

			//first attacker damage value is calculating and stored in obj1Damage
			obj1Damage=obj1->getDamage();
			//then damage value substract from hitPoints2
			hitPoints2-=obj1Damage;	
			
			//setting the hitpoint for obj2
			obj2->setHit(hitPoints2);
			

			//the new hitpoints of obj2 is printed.
			cout<<"The new hitpoints of "<<obj2->getName()<< " is "<< hitPoints2<<endl;
			
			

		}

		//when hitpoints2 is less than 0 or equal to zero 
		if(hitPoints2<=0 )	
		{
			//then the winner is first robot and victim is second robot
			//cout<<endl<<"Winner of this round is "<<obj1->getName()<<endl<<endl;
			cout<<obj2->getName()<<" is dead."<<endl;
			return obj1->getName();
				break;
		}

		
		cout<<obj2->getName()<<"("<<obj2->getHit()<<") hits ";
		cout<<obj1->getName()<<"("<<obj1->getHit()<<")";

		//second attacker damage value is calculating and stored in obj2Damage
		obj2Damage=obj2->getDamage();
		//then damage value substract from hitPoints1
		hitPoints1-=obj2Damage;	

		//setting the hitpoint for obj1
	 	obj1->setHit(hitPoints1);
		

		//the new hitpoints of obj1 is printed.
		cout<<"The new hitpoints of "<<obj1->getName()<< " is "<< hitPoints1<<endl;
		
		if(obj2->getType()=="roomba")
		{
			if(hitPoints1<=0 )	
			{
				//then the winner is first robot and victim is second robot
				//cout<<endl<<"Winner of this round is "<<obj1->getName()<<endl<<endl;
				cout<<obj1->getName()<<" is dead."<<endl;
				return obj2->getName();
				break;
			}

			cout<<obj2->getName()<<"("<<obj2->getHit()<<") hits ";
			cout<<obj1->getName()<<"("<<obj1->getHit()<<")";

			//second attacker damage value is calculating and stored in obj2Damage
			obj2Damage=obj2->getDamage();
			//then damage value substract from hitPoints1
			hitPoints1-=obj2Damage;	

			//setting the hitpoint for obj1
			obj1->setHit(hitPoints1);
			

			//the new hitpoints of obj1 is printed.
			cout<<"The new hitpoints of "<<obj1->getName()<< " is "<< hitPoints1<<endl;

		


		}



		//when hitpoints1 is less than 0 or equal to zero
		if(hitPoints1<=0)	
		{
			//then the winner is second robot and victim is first robot
			//cout<<endl<<endl<<"Winner of this round is "<<obj2->getName()<<endl<<endl;
			cout<<obj1->getName()<<" is dead."<<endl;
			return obj2->getName();
				break;
		}
	}
	return "no-one";
}
