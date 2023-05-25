#include "Student.h"
#include "Course.h"
#include<string>

using namespace std;


namespace PA4
{
    //consturctors
    Student::Student(): name(""), ID(0), courses(nullptr), numCourses(0) {}
    Student::Student(string name, int ID) : name(name), ID(ID), courses(nullptr), numCourses(0) {}
    Student::Student(string name, int ID,Course* courses,int numCourses) : name(name), ID(ID), courses(courses), numCourses(numCourses) {}
    
    //destructors
    Student::~Student() { delete[] courses; }

    //getters
    string Student::getName() const { return name; }
    int Student::getID() const { return ID; }
    Course* Student::getCourses() const { return courses; }
    int Student::getNumCourses() const { return numCourses; }

    //setters
    void Student::setName(string name) { this->name = name; }
    void Student::setID(int ID) { this->ID = ID; }
    void Student::setCourse(Course* courses){this->courses = courses;}
    void Student::setNumCourses(int newNumberCources){this->numCourses = newNumberCources;}
}