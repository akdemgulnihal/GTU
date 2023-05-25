#include<iostream>
#include "user.h"
#include "password.h"

using namespace std;

using Authenticate::inputUserName;
using Authenticate::getUserName;
using Authenticate::inputPassword;
using Authenticate::getPassword;


int main()
{
    inputUserName();
    inputPassword();
    cout << "Your username is " << getUserName() <<
            " and your password is: " <<
            getPassword() << endl;
    return 0;

}