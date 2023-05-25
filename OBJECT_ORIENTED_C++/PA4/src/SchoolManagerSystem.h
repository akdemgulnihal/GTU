#ifndef SCHOOLMANAGERSYSTEM_H
#define SCHOOLMANAGERSYSTEM_H

#include "Student.h"
#include "Course.h"

using PA4::Student;
using PA4::Course;

namespace PA4
{	
    class Student;
	class Course;
    
    class SchoolManagerSystem
    {
	
    private:
        Student** students;
        int numStudents;
        Course** courses;
        int numCourses;

        Student  selectedStudent;
        Course  selectedCourse;

        //menu stuffs
		int Main_menu();
		int subMenu1();
		int subMenu2();
		int subSelectMenu();
        int subSelectMenu1();

    public:
       
   //default constructor    
    SchoolManagerSystem() : courses(nullptr), numCourses(0), students(nullptr), numStudents(0) {}
    
    //destrcutor
    ~SchoolManagerSystem() ;
     
    //adding the new student or course
    void addCourse(string name, string code) ;
    void addStudent(string name, int ID) ;

    //removing the new student or course
    void removeStudent(string name, int ID) ;
    void removeCourse(string , string);

    //printing stuffs
    void printStudents() const ;
    void printCourses() const ;

    void run();
    
    };
}

#endif