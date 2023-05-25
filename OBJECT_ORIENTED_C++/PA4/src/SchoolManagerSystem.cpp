#include "SchoolManagerSystem.h"
#include<string>
#include<sstream>
#include<iostream>

#include "Course.h"
#include "Student.h"

using PA4::Student;
using PA4::Course;

using namespace std;

namespace PA4
{
   
    //Main menu 
    int SchoolManagerSystem::Main_menu()
    {
        int choice;
        cout<< "0 exit" <<endl
            << "1 student" << endl
            << "2 course" << endl
            << "3 list_all_students" << endl
            << "4 list_all_courses" << endl;

        cin >> choice;
    
        if( choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4  )
        {
            return choice;
        }
        else
        {
            cout << "Wrong Choice !!!" << endl;
            exit(0);
        }
    }

    //During main menu, if entry is 1 then, this menu will be printed
    int SchoolManagerSystem::subMenu1()
    {
        int choice;
        cout<< "0 up" << endl
            << "1 add_student" << endl
            << "2 select_student"<< endl;

        cin >> choice;
        if( choice == 0 || choice == 1 || choice == 2  )
        {
            return choice;
        }
        else
        {
            cout << "Wrong Choice !!!" << endl;
            exit(0);
        }
    }
        //During main menu, if entry is 2 then, this menu will be printed
    int SchoolManagerSystem::subMenu2()
    {
        int choice;
        cout<< "0 up" << endl
            << "1 add_course" << endl
            << "2 select_course"<< endl;
    
        cin >> choice;
        if( choice == 0 || choice == 1 || choice == 2  )
        {
            return choice;
        }
        else
        {
            cout << "Wrong Choice !!!" << endl;
            exit(0);
        }
    }
    
    int SchoolManagerSystem::subSelectMenu()
    {
           int choice;
            cout<< "0 up" << endl
                << "1 delete_student" << endl
                << "3 add_selected_student_to_a_course"<<endl
                << "4 drop_selected_student_from_a_course"<< endl;
    
        cin >> choice;
        if( choice == 0 || choice == 1 || choice == 3 || choice == 4  )
        {
            return choice;
        }
        else
        {
            cout << "Wrong Choice !!!" << endl;
            exit(0);
        }
    }

    int SchoolManagerSystem::subSelectMenu1()
    {
           int choice;
            cout<< "0 up" << endl
                << "1 delete_course" << endl
                << "2 list_students_registered_to_the_selected_course"<<endl
                ;
    
        cin >> choice;
        if( choice == 0 || choice == 1 || choice == 2   )
        {
            return choice;
        }
        else
        {
            cout << "Wrong Choice !!!" << endl;
            exit(0);
        }
    }

    //destructor
    SchoolManagerSystem::~SchoolManagerSystem()
    {
        for (int i = 0; i < numCourses; i++) {
            delete courses[i];
        }
        delete[] courses;
        for (int i = 0; i < numStudents; i++) {
            delete students[i];
        }
        delete[] students;
    }

    //adding the new corse information to courses dynamic array
    //this function takes parameter one of them is coursname ,the other one is the code
    void SchoolManagerSystem::addCourse(string name, string code) 
    {
        Course* course = new Course(name, code);
        Course** newCourses = new Course*[numCourses + 1];
        for (int i = 0; i < numCourses; i++) 
        {
            newCourses[i] = courses[i];
        }
        newCourses[numCourses] = course;
        numCourses++;
        delete[] courses;
        courses = newCourses;
  
    }

      //adding the new student information to students dynamic array in schoolManagerSystem
    //this function takes parameter one of them is student name ,the other one is the id
    void SchoolManagerSystem::addStudent(string name, int ID) 
    {
        Student* student = new Student(name, ID);
        Student** newStudents = new Student*[numStudents + 1];
        for (int i = 0; i < numStudents; i++) {
            newStudents[i] = students[i];
        }
        newStudents[numStudents] = student;
        numStudents++;
        delete[] students;
        students = newStudents;
    }

    //removing the student from the schoolmanagersystem
    void SchoolManagerSystem::removeStudent(string name,int ID)
    {
    int indexToDelete = -1;
    Student* temp = new Student(name,ID);
   
    for (int i = 0; i < numStudents; i++) {
        if (students[i]->getName() == name && students[i]->getID() == ID) {
            indexToDelete = i;
            break;
        }
    }
    int i,j=0;

        for(i=0;i<indexToDelete;i++)
        {
            students[j]=students[i];
            j++;
        }
        if(i==indexToDelete)
        {
            delete students[indexToDelete];
            i++;
        }
            
        for(;i<numStudents;i++)
        {
           students[j]=students[i];
           j++;
        }
    

    numStudents--;
    
}


//removing the course from the schoolmanagersystem
 void SchoolManagerSystem::removeCourse(string code,string name)
    {
    int indexToDelete = -1;
    Course* temp = new Course(code,name);
   
    for (int i = 0; i < numCourses; i++) {
        if (courses[i]->getName() == code && courses[i]->getCode()==name) {
            indexToDelete = i;
            break;
        }
    }
 
        int i=0,j=0;    
        for(i=0;i<indexToDelete;i++)
        {
           courses[j]=courses[i];
           j++;
           
        }
        if(i==indexToDelete)
        {
            delete courses[indexToDelete];
            i++;
        }
        for(;i<numCourses;i++)
        {
            courses[j]=courses[i];
            j++;
        }

    numCourses--;
   
}


    //Printing the all students and its information
    void SchoolManagerSystem::printStudents() const
    {
        cout<<endl;
        for (int i = 0; i < numStudents; i++) 
            cout << students[i]->getName()  << endl;
        cout<<endl;
    }

    //Printing the all courses and its information
    void SchoolManagerSystem::printCourses() const
     {
        cout<<endl;
        for (int i = 0; i < numCourses; i++) 
            cout << courses[i]->getName()  <<" " << courses[i]->getCode()  << endl;
        cout<<endl;
    }
    
    //run the menus and sub menus
    void SchoolManagerSystem::run()
	{
         int flagMainMenu , checkSubMenu = 0, s = 0 , c = 0 ;
         string name,surname,code;
		 string coursename ; 
		 int id;
         
        do{
           flagMainMenu = Main_menu();
          do{
                 switch(flagMainMenu)   //According to the choice of the Main Menu
                {
                    case 1: 
                        checkSubMenu = subMenu1();
                       if(checkSubMenu == 1)
                         { 
                           cin.ignore();
                           getline(cin, name); 
                            
                           stringstream ss;
                           ss<<name;
                           string temp;
                           int found;
                           while(!ss.eof())
                           {
                               ss >>temp;
                               if(stringstream(temp) >> found)
                                   id=found;
                               temp="";
                           }

                           addStudent(name,id);     // Calling the addStudent function to add new student to list
                        }
                       else if(checkSubMenu == 2)
                        {
                            cin.ignore();
                            getline(cin, name); 
                            stringstream ss;
                            ss<<name;
                            string temp;
                            int found;
                            while(!ss.eof())
                            {
                                ss >>temp;
                                if(stringstream(temp) >> found)
                                    id=found;
                                    temp="";
                                }
                            int check = subSelectMenu();
                            if(check==1)
                            {
                                removeStudent(name,id); // Calling the removeStudent function to remove certain student to list
                            }
                            else if(check==3)
                            {
                                int num;
                               if(selectedStudent.getCourses() ==nullptr)
                               {
                                cout<<"0 UP\n";
                                    for(int i=0;i < numCourses;i++)
                                    {
                                        cout<< i+1 <<" "<< courses[i]->getName()<<" "<<courses[i]->getCode()<<endl;
                                    }
                                    cin>>num;

                                    Course* assi = new Course[1];


                                    if(num > 0 && num < numCourses)
                                    {
                                        assi->setName(courses[num-1]->getName());
                                        assi->setCode(courses[num-1]->getCode());

                                        selectedStudent.setCourse(assi);

                                        delete[] assi;
                                    }
                               }
                               else{}   
                           }
                         }
                         break;
                     case 2:
                   
                         checkSubMenu = subMenu2();
                         if(checkSubMenu == 1)
                         {
                            cin>>code;
                            cin.ignore();
                            getline(cin, coursename);

                            addCourse(code,coursename);      // Calling the addcourse function to add new course to list
                         }
                     else if(checkSubMenu == 2)
                         {
                            cin>>code;
                             cin.ignore();
                             getline(cin, coursename);

                            int check = subSelectMenu1();
                            
                            if(check==1)
                            {
                                removeCourse(code,coursename);  //removing the corse via this function call
                            }
                            else if(check==2){}         
                                  
                         }
                         break;
                     case 3:
                         printStudents();   //call this function to print the list of student
                         break;
                     case 4:
                        printCourses(); //call this function to print the list of course
                         break;
                    case 0:
                        exit(1);     
                 }
             }while(checkSubMenu != 0); //If checkSubMenu==0,then up to the main menu

         }while (flagMainMenu != 0);    //Checking the main menu choice until not 0 (exit)


    }

     
    

    
}