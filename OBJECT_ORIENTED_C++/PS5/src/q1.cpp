#include<iostream>

using namespace std;

class Polynomial
{
    public:
        Polynomial();   //Creates an empty polynomial
        Polynomial(const Polynomial&);

        //The size of the coefficient array is degree of the polynomial +1
        Polynomial(double coefficient[], int size);
        ~Polynomial();

//use indexed polynomial as r-value to inspect coefficient
// and as l-value to assign coeficient

double &operator[](int degree);
//This is required if we are to have const correctness
const double& operator[](int degree) const;

const Polynomial& operator=(const Polynomial& rhs);
int mysize();

//friend functions:
    friend double evaluate(const Polynomial& play, double arg);
    friend Polynomial operator+(const Polynomial& lsh,
                                const Polynomial& rhs);
    friend Polynomial operator-(const Polynomial& lsh,
                                const Polynomial& rhs);
    friend Polynomial operator*(const Polynomial& lsh,
                                const Polynomial& rhs);
private:
    double *coef;
    int size;
};

int main()
{
    Polynomial empty;
    double one[] = {1};
    Polynomial One(one,1);
    double quad[] = {3 , 2, 1};
    double cubic[] = {1 ,2 ,0 , 3};
    Polynomial q(quad, 3);  //q is 3 + 2*x + x*x
    Polynomial c(cubic,4);  //c is 1 + 2*x + 0*x*x + 3*x*x*x
    Polynomial p = q;   //test copy constructor
    Polynomial r;
    r =q;
    r=c;
    cout<<"Polynmial q"<<endl;
    {for(int i=0; i < 3;i++)
        cout<<"term with degree "<<i    
            <<" has coeficient "<<q[i]<<endl;
    }
    cout<<"Polynmial c"<<endl;
    {for(int i=0; i < 4;i++)
        cout<<"term with degree "<<i    
            <<" has coeficient "<<c[i]<<endl;
    }
    cout<<"value of q(2) is "<<evaluate(q,2)<<endl;
    cout<<"value of p(2) is "<<evaluate(p,2)<<endl;
    cout<<"value of r(2) is "<<evaluate(r,2)<<endl;
    cout<<"value of c(2) is "<<evaluate(c,2)<<endl;

    r = q + c;
    cout<<"value of (q+c)(2) is "<<evaluate(r,2)<<endl;

    r=q-c;
    cout<<"value of (q-c)(2) is "<<evaluate(r,2)<<endl;

    r= q*c;
    cout<<"size of q*c is "<<r.mysize()<<endl;
    cout<<"Polynmial r (=q*c) "<<endl;

    for(int i=0;i<r.mysize();i++)
        cout<<"term with degree "<< i<< " has coefficient "<<r[i]<<endl;
    
    cout<<"value of (q*c)(2) is "<<evaluate(r,2)<<endl;
    
    return 0;
}

int Polynomial::mysize()
{   return size; }

Polynomial::Polynomial():coef(0),size(0){}

const Polynomial& Polynomial::operator=(const Polynomial & rhs)
{
    if(rhs.coef == coef)
        return rhs;
    else
    {
        delete [] coef;
        coef = new double[rhs.size];
        for(int i=0; i< rhs.size;i++)
            coef[i] = rhs.coef[i];
        size=rhs.size;
    }
    return rhs;
}

Polynomial::Polynomial(const Polynomial& rhs):size(rhs.size)
{
    coef = new double[rhs.size];
    for(int i=0; i< rhs.size;i++)
        coef[i] = rhs.coef[i];
}
Polynomial::Polynomial(double coefficient[], int newSize):size(newSize)
{
    coef = new double[size];
    for(int i=0; i< size;i++)
        coef[i] = coefficient[i];
}
Polynomial::~Polynomial()
{
    delete [] coef;
}
const double& Polynomial::operator[](int degree)const
{
    return coef[degree];
}
double& Polynomial::operator[](int degree)
{
    return coef[degree];
}
double max(double lhs,double rhs)
{
    return (lhs > rhs) ? lhs : rhs;
}
Polynomial operator+(const Polynomial& lsh,const Polynomial& rhs)
{
    const int sumSize = max(lsh.size,rhs.size);
    double* sumCoefs = new double[sumSize];

    for(int i=0;i< sumSize;i++)
        sumCoefs[i] = lsh.coef[i] + rhs.coef[i];
    return Polynomial(sumCoefs,sumSize);
}

Polynomial operator-(const Polynomial& lsh,const Polynomial& rhs)
{
    int sumSize = max(lsh.size,rhs.size);
    double* sumCoefs = new double[sumSize];

     for(int i=0;i< sumSize;i++)
        sumCoefs[i] = lsh.coef[i] + rhs.coef[i];
    return Polynomial(sumCoefs,sumSize);   
}
Polynomial operator*(const Polynomial& lsh,const Polynomial& rhs)
{
    int i; int j;
    int prodSize = lsh.size + rhs.size;
    double* prodCoefs = new double[prodSize];

    for(int i=0;i<prodSize;i++)
        prodCoefs[i]=0;
    for(i=0;i<lsh.size;i++)
        for(j=0;j<rhs.size;j++)
            prodCoefs[i+j] += lsh[i] * rhs[j];
    return Polynomial(prodCoefs,prodSize);
}

double evaluate(const Polynomial& poly , double arg)
{
    double value =0;
    int i;

    for(i=poly.size-1; i>=0;i--)
        value = poly[i] + arg *value;

    return value;
}