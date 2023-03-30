#include<iostream>
using namespace std;

class Vector2D
{
    public:
        Vector2D(int x,int y);
        int getX();
        int getY();

        void setX(int newX);
        void setY(int newY);

    int operator*(Vector2D& other);

    private:
        int x;
        int y;

};

Vector2D::Vector2D(int x,int y): x(x), y(y){}

int Vector2D::getX(){   return x;}
int Vector2D::getY(){   return y;}

void Vector2D::setX(int newX)
{
    x = newX;
}

void Vector2D::setY(int newY)
{
    y = newY;
}

int Vector2D::operator*(Vector2D& other)
{
    return x* other.x + y * other.y;
}

int main()
{
    Vector2D v1(10,0),v2(0,10),v3(10,10),v4(5,4),v5(4,0),v6(0,5),v7(6,9),v8(3,7);
    cout<<"("<<v1.getX()<<","<<v1.getY()<<") * ("
            <<v2.getX()<<","<<v2.getY()<<") = "<<v1*v2<<endl
            <<"("<<v3.getX()<<","<<v3.getY()<<") * ("
            <<v4.getX()<<","<<v4.getY()<<") = "<<v3*v4<<endl
            <<"("<<v5.getX()<<","<<v5.getY()<<") * ("
            <<v6.getX()<<","<<v6.getY()<<") = "<<v5*v6<<endl
            <<"("<<v7.getX()<<","<<v7.getY()<<") * ("
            <<v8.getX()<<","<<v8.getY()<<") = "<<v7*v8<<endl;
    return 0;
}