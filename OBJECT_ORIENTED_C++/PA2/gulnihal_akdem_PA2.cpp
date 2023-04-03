#include<iostream>
#include<fstream>
#include<string.h>
#include<vector>

using namespace std;

//Main and subMenus
int mainMenu();
int openAnImageMenu();
int saveAnImageMenu();
int scriptsMenu();
int convertGrayScaleMenu();

string userEntryFilename();

class RGB
{
    public:
    //Constructor to initialize the r , g and b values
    RGB(float r = 0.0 ,float g = 0.0,float b = 0.0)
    {
        R=r;    
        G=g;
        B=b;
    }
    float R,G,B;
};


class Image
{
    public:
        void readImageFile(string);  //Reading the file
        void writeImageFile(string); //Writing to the file
        void convertToGrayscale(float,float,float); //Converting each color to grayscale

        //Getters
        int getWidth(){return width;}
        int getHeight(){return height;}
        int getMaxValue(){return maxValue;}
        vector< RGB > getVector(){return data;}

    private:
        int width;
        int height;
        int maxValue;
        vector< RGB > data;
};


int main()
{
    Image obj1;

    int check;
    do{
        check = mainMenu();
        while(check < 0 | check > 3)    //If user do not enter 0,1,2 or 3
        {
            check = mainMenu();
            
        }
        if(check == 0 )     exit(0);    //If the user chose 0, then the program will be terminated
        
        int checkSubMenu;        //Declarint for subMenu choices
        string filename;         //Declarint for the name of file
        float c_r, c_g, c_b;    //Declaring these variables for the coefficients

        do{ //This loop for menu
            
            switch(check)   //According to the choice of the menu
            {
                //if the user chose the first option from the menu
                case 1: 
                    checkSubMenu = openAnImageMenu();   //printed the open an image menu and taking the choice from the user and this value is returned.
                    if(checkSubMenu == 1)           //If the user enter 1i s/he do not want to go back to the main menu

                    {
                        filename = userEntryFilename(); //Taking a string from the user and opening a file with this string name
                        obj1.readImageFile(filename);   //and this file is being read because of this calling of the function
                        
                    }
                    break;
                case 2:
                    checkSubMenu = saveAnImageMenu();   //printed the save an image menu and taking the choice from the user and this choice is returned back.
                    if(checkSubMenu == 1)   //ıf the choice is 1, then the user do not want to go back to the main menu
                    {                       
                        filename = userEntryFilename(); //Taking a string from the user and opening a file with this string name 
                        obj1.writeImageFile(filename);  //and then writing the data to this file because of this function call
                    }
                    break;
                case 3:
                    checkSubMenu = scriptsMenu();   //printed the convert to grayscale menu and then this function is returned a value
                    if(checkSubMenu == 1)           //if returning value is 1,that's mean the user do not want to go to the main menu
                    {   
                        int checkConvert;
                        do
                        {
                        checkConvert = convertGrayScaleMenu();
                        
                        
                        if(checkConvert == 1)
                        {
                        //taking coefficients from the user
                        cin>> c_r >> c_g >> c_b;
                        //checking the coefficients are in specific range [0,1)
                        while(c_r < 0 || c_r >=1 || c_g < 0 || c_g >=1 || c_b < 0 || c_b >=1  )
                        {
                            //Taking the coefficients until user enters correct input
                            cout<<"Enter three coeffients again in the range [0,1):"<<endl;
                            cin>> c_r >> c_g >> c_b;
                        }       
                        obj1.convertToGrayscale(c_r, c_g, c_b);
                        }
                        }while(checkConvert!=0);
                    }
                    
                    break;
               
            }
        }while(checkSubMenu!=0); //If checkSubMenu==0,then up to the main menu

    }while (check != 0);    //Checking the main menu choice until not 0 (exit)    
        

    return 0;
}

//main menu function
int mainMenu()
{
    int chooseMainMenu;
    cout<<"MAIN MENU"<<endl
        <<"0 - Exit"<<endl
        <<"1 - Open An Image(D)"<<endl
        <<"2 - Save Image Data(D)"<<endl
        <<"3 - Scripts(D)"<<endl;
    cin>>chooseMainMenu; 
    return chooseMainMenu;
}

//open an image menu 
int openAnImageMenu()
{
    int choose; //when user enter a value for open image  menu ,this value assign to this variable
    cout<<"OPEN AN IMAGE MENU"<<endl
        <<"0 - UP"<<endl
        <<"1 - Enter The Name Of The Image File"<<endl;
    cin>>choose;    //getting input from the keyboard
    return choose;  //returning the user input
}

//save an inage menu
int saveAnImageMenu()
{
    int choose; //when user enter a value for save image data menu ,this value assign to this variable
    cout<<"SAVE IMAGE DATA MENU"<<endl
        <<"0 - UP"<<endl
        <<"1 - Enter A File Name"<<endl;
    cin>>choose;    //getting input from the keyboard
    return choose;  //returning the user input
}

//printing the scriptsMenu
int scriptsMenu()
{
    int choose; //when user enter a value for script menu ,this value assign to this variable
    cout<<"SCRIPTS MENU"<<endl
        <<"0 - UP"<<endl
        <<"1 - Convert To Grayscale(D)"<<endl;
    cin>>choose;    //getting input from the keyboard
    return choose;  //returning the user input
}

//converting to grayscale menu
int convertGrayScaleMenu()
{
    int choose; //when user enter a value for convert to grayscale menu ,this value assign to this variable
    cout<<"CONVERT TO GRAYSCALE MENU"<<endl
        <<"0 - UP"<<endl
        <<"1 - Enter Coefficients For RED GREEN And BLUE Channels"<<endl;
    cin>>choose;    //getting input from the keyboard
    return choose;  //returning the user input
}

string userEntryFilename()
{
    string str; //string for the name of the file
    cin>>str;   //getting input from the keyboard
    return str;     //filename is returned
}

//that member function uses for reading data from the file
void Image::readImageFile(string filename)   //taking string(filename) parameter to open a file which name is this string value
{
    ifstream fin(filename); //Open the file
    
    if(!fin.is_open())  //Checking the file is opened succesfully or not.
    {
        cout<<"Error: failed to open the file!"<<endl;
        exit(1);
    }
    string format;  
    fin>>format;    //Taking first string in .ppm (image format is P3)
    if(format != "P3") {cout<<"It's not image format"<<endl;}

    //row,column and maximum number is taken from the file and then assign to private variable of the Image class
    fin>>width;
    fin>>height;
    fin>>maxValue;

    //total number of pixels,calculated by using related getters
    int size = getWidth() * getHeight();
    data.resize(size);
    int pixel1,pixel2,pixel3;
    
    for(int i=0;i<size;i++)
    {   //taking r,g and b from the file
        fin>>pixel1>>pixel2>>pixel3;
        int r = pixel1;
        int g = pixel2;
        int b = pixel3;
        RGB color(r,g,b);   //created object,and assign values which taking from former steps in this class variable
        data.at(i) = color;//all data from the file is stored in data vector which is in Image class
    }
    //closing the file
    fin.close();
    
}

//writing cuurent data from the vector(vactor in Image class) to the file whose name is filename string
void Image::writeImageFile(string filename)
{   
    ofstream fout(filename); //Opening file in writing mode
    if(!fout.is_open()) //if there is problem about opening
    {
        cout<<"Error: Failed to create a file!"<<endl;
        exit(1);
    }
    fout<<"P3"<<endl;   //writing P3 to the file
    fout<<getWidth()<<" "<<getHeight()<<endl    //writing the row and column numbers to the file
        <<getMaxValue()<<endl;  //writing the max value to the file
    

    int k=0;
    int he=getHeight();
    //this loop writes data from the vector to the file
    for(RGB pixel_color : data)
    {
        if(k==he)   //when iterated number(k) is equal to the height,
        {           //A new line will be entered.
            fout<<endl;
            k=0;    //then,due to going new line column ought to be zero
        }
        //One square information will be written to the determined file
        fout<< pixel_color.R <<" "<< pixel_color.G<<" "<< pixel_color.B<<" ";
        k++; //column number is increased.
    }

    //closing the file
    fout.close();
    
}

//this function update the image data according to the coeffiecients
void Image::convertToGrayscale(float c_r,float c_g,float c_b)
{
    int height=getHeight(); //Getting the private variale from the Image class and assign to this variable
    int i=0;    //some local variables
    int size = getWidth() * height; //the number of pixels totally

    for(RGB color:data)
    {
        //the equations are occured due to the pdf       
        float red = color.R * c_r; 
        float green = color.G * c_g;
        float blue = color.B *c_b;

        //r,g and b are should be same
        int newValue = red + green + blue;
        if(newValue>255) newValue=255;

        //temporary object to not overlap existed object
        RGB temp(newValue,newValue,newValue);
        data.at(i)=temp; //and this object's r,g and b are assigned to the vector(data vector in Image class)
        i++;
    } 
    
}