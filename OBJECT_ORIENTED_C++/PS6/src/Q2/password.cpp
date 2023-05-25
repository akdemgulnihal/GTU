#include<iostream>
#include "password.h"

using namespace std;

namespace
{
    string password;

    bool isValid()
    {
        bool nonLetterCheck = false;
        for (int i=0; i<password[i]!='\0';i++)
        {
            if (!isalpha(password[i]))
            {
                nonLetterCheck = true;
                break;
            }
        }

        return password.length() >= 8 && nonLetterCheck;
    }
}
namespace Authenticate
{
    void inputPassword()
    {
        do
        {
            cout << "Enter your password (at least 8 characters " <<
            "and at least one non-letter)" << endl;
            cin >> password ;
        } while (!isValid());
    }
    
    string getPassword()
    {
        return password;
    }
}