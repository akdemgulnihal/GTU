#include<iostream>
#include<cmath>

using namespace std;

class MyInteger
{
    public:
        MyInteger(int newValue);
        int operator[](int i);

        void setValue(int newValue);
        int getValue();

    private:
        int value;
};

MyInteger::MyInteger(int newValue): value(newValue) {}

int MyInteger::operator[](int i)
{
    
    int temp = pow(10,i);
    if(i < 0 || temp > value)   return -1;
    int digit = (value/temp) % 10;
    
    return digit;
}

void MyInteger::setValue(int newValue)
{
    value = newValue;
}

int MyInteger::getValue()
{
    return value;
}

int main()
{
    MyInteger obj1(418);
    cout<<obj1[0]<<" "<<obj1[1]<<" "<<obj1[2]<<endl;
    cout<<obj1[3]<<endl;
    cout<<obj1[-1]<<endl;

    return 0;
}