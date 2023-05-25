#ifndef STUDENT_H
#define STUDENT_H

#include <iostream>
#include <string>

using namespace std;
namespace PA4
{
    class Course; // forward declaration

    class Student {
    private:
        string name;
        int ID;
        Course* courses;
        int numCourses=0;

    public:
        //Constructors
        Student() ;
        Student(string name, int ID);
        Student(string name, int ID, Course* courses,int numCourses);
        
        //Destructor
        ~Student();

        //getters
        string getName() const;
        int getID() const ;
        Course* getCourses() const;
        int getNumCourses() const;

        //setter
        void setName(string name);
        void setID(int ID) ;
        void setCourse(Course* courses);
        void setNumCourses(int newNumberCources);


    };
}
#endif