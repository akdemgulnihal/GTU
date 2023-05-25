#include "Course.h"
#include<string>

using namespace std;

namespace PA4
{
    //Declaration of constructors
    Course::Course() : name(""), code(""), students(nullptr), numStudents(0) {}
    Course::Course(string name, string code) : name(name), code(code), students(nullptr), numStudents(0) {}

    //Declaration of destructor
    Course::~Course() { delete[] students; }

    //Declaration of getters
    string Course::getName() const { return name; }
    string Course::getCode() const { return code; }
    Student** Course::getStudents() const { return students; }
    int Course::getNumStudents() const { return numStudents; }

    //Declaration of setters
    void Course::setName(string name) { this->name = name; }
    void Course::setCode(string code) { this->code = code; }

   
}