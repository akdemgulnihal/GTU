#ifndef COURSE_H
#define COURSE_H

#include<string>
using namespace std;

namespace PA4
{
    class Student;  // forward declaration
    
    class Course {
    private:
        string name;
        string code;
        Student** students;
        int numStudents=0;

    public:
        //Constructors
        Course();
        Course(string name, string code) ;
        
        //destructors
        ~Course() ;

        //getters
        string getName() const;
        string getCode() const ;
        Student** getStudents() const;
        int getNumStudents() const;

        //setters
        void setName(string name);
        void setCode(string code);
    };
}

#endif