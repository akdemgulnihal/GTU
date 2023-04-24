#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include<cmath>

using namespace std;

class point{
	public:
		//default constructor
		point() : point(0, 0){}
		//The constructor takes two integer parameters and they are represent  the x and y coordinates of the point.
		point(int pX, int pY): x(pX),y(pY) {}

		void operator=(const point &other);

		// These are two operator overload functions for the input/output streams
		//  friends allows these functions to access the private member variables of the point class.
		friend ostream & operator<<(ostream &out, const point &p);
		friend istream & operator>>(istream &in, point &p);

		//setter
		void set(int _x, int _y) {x = _x; y = _y;}
		//getters
		int getX() const {return x;}
		int getY() const {return y;}

	private:
		int x,y;
};

class pixel{
	public:
		//default constroctor
		pixel() : pixel(0, 0, 0){}

		pixel(const int arr[3]);
		pixel(int _r, int _g, int _b);



		void operator=(const int arr[3]);
		void operator=(const pixel &other);

		// These are two operator overload functions for the input/output streams
		//  friends allows these functions to access the private member variables of the pixel class.
		friend ostream & operator<<(ostream &out, const pixel &p);
		friend istream & operator>>(istream &in, pixel &p);
		
		//setter
		void set(int _r, int _g, int _b) {r = _r; g = _g; b = _b;}
		//getters
		int getR(){ return r;}
		int getG(){ return g;}
		int getB(){ return b;}

		//operator overloading
		int & operator[](int index);
		const int & operator[](int index) const; 

		pixel operator+(const pixel &other) const;
		pixel operator-(const pixel &other) const;

		pixel & valuesLower(int lower);
		pixel & valuesUpper(int upper);
		pixel & values(int lower, int upper);

	private:
		int r,g,b;
};

class ppmImage{
	public:
	
		ppmImage(); //default constrcutor
		ppmImage(const ppmImage &obj);	
		ppmImage(const string &_fileName);	
		ppmImage(const point &_dimensions);

		ppmImage(int _dimensionX, int _dimensionY);

		ppmImage(const point &_dimensions, int _MaxLimit);
		
		ppmImage(int _dimensionX, int _dimensionY, int _MaxLimit);

	
		int Read(string _fileName);	//reading file
		int Write(string _filename) const;	//writing
		

		void Resize(int _dimensionX, int _dimensionY);	//resize using this parameter

		point Size() const {return dimensions;}	

		//getter for max value
		int getMaxLimit() const {return MaxLimit;}
		//setter for max value
		void setMaxLimit(int newMaxLimit) {MaxLimit = newMaxLimit;}

		int SwapPixelChannels(int swapChoice);

	
		vector<pixel> & operator[](int index);
		const vector<pixel> & operator[](int index) const;

		void operator=(const ppmImage &obj); 
		ppmImage operator+(const ppmImage &obj);	//adding objects
		ppmImage operator-(const ppmImage &obj);	//substracts objects

		friend ostream & operator<<(ostream &out, const ppmImage &obj);

		int & operator()(int row, int column, int pIndex);	// accesses the places using this parameter
		const int & operator()(int row, int column, int pIndex) const;	// accesses the places using this parameter, but nothing change when use this operator

	private:
		point dimensions;				
		vector<vector<pixel>> data;	
		int MaxLimit;			
	
};

int test_addition(const string filename_image1, const string filename_image2, const string filename_image3);
int test_subtraction(const string filename_image1, const string filename_image2, const string filename_image3);
int test_print(const string filename_image1);
int read_ppm(const string source_ppm_file_name, ppmImage &destination_object);
int write_ppm(const string destination_ppm_file_name, const ppmImage &source_object);
int swap_channels(ppmImage &obj_object_to_be_modified, int swap_choice);
ppmImage single_color(const ppmImage &source, int color_choice);


//******************* MAIN FUNCTION **********************************************
int main(int argc, char ** argv){

	if(argc > 5)	//Checking the number of argumnet , if it is greater than 5
	{
		cout<<"Too many arguments are entered!\n";
		exit(1);
	}
	else if(argc < 3)	//Checking the number of argumnet , if it is less than 3
	{
		cout<<"You need three arhumnet at least!\n";
		exit(1);
	}
	else{	

		int choiceNumber;								
		string file_name1,file_name2 = "file_name2.ppm" ,file_name3 ;	

		choiceNumber = stoi(argv[1]); //taking the argument for the choice

		//creating some objects
		ppmImage object;		
		ppmImage theOther;

		//assign the second index of command line argument to this variable 
		file_name1 = argv[2];

		if(argc == 4)	//if the number of command argument line is 4
		{
			file_name2 = argv[3];	//taking third argument and assign it
		}
		else if(argc == 5)	//if the number of command argument line is 5
		{
			file_name2 = argv[3];	//taking third argument and assign it
			file_name3 = argv[4];	//taking fourth argument and assign it
		}
		
		switch(choiceNumber){
			case 1:
				if(argc != 5) return 0;
				test_addition(file_name1, file_name2, file_name3);	//reads images from filename_image1 and filename_image2. 
				break;												//Adds them and saves the resulting image to filename_image3

			case 2:
				if(argc != 5) return 0;
				test_subtraction(file_name1, file_name2, file_name3);	//reads images from filename_image1 and filename_image2.
				break;													//Substracts them and saves the resulting image to filename_image3
			case 3:
				read_ppm(file_name1, object);	//read ppm_file_name1 using function read_ppm
				swap_channels(object, 2);		// swap red and blue channels. use swap_channels function
				write_ppm(file_name2, object);	//write the data of the new object to a file named "ppm_file_name2". use write_ppm function.
				break;
			case 4:
				read_ppm(file_name1, object);	//read ppm_file_name1 using function read_ppm
				swap_channels(object, 3);		// swap green and blue channels. use swap_channels function
				write_ppm(file_name2, object);	//write the data of the new object to a file named "ppm_file_name2". use write_ppm function.
				break;
			case 5:
				read_ppm(file_name1, object);	//read ppm_file_name1 using function read_ppm
				theOther = single_color(object, 1);	//Red channel is preserved
				write_ppm(file_name2, theOther);	//write the data of the new object to a file named "ppm_file_name2". use write_ppm function.
				break;
			case 6:
				read_ppm(file_name1, object);	//read ppm_file_name1 using function read_ppm
				theOther = single_color(object, 2);	// Green channel is preserved
				write_ppm(file_name2, theOther);	//write the data of the new object to a file named "ppm_file_name2". use write_ppm function.
				break;
			case 7:
				read_ppm(file_name1, object);	//read ppm_file_name1 using function read_ppm
				theOther = single_color(object, 3);	//Blue channel is preserved
				write_ppm(file_name2, theOther);	//write the data of the new object to a file named "ppm_file_name2". use write_ppm function.
				break;
			default:
				cout << "Invalid entry!!!\n";	//If the choice is worng , then this message will be printed
			}
	}

	return 0;
}



//********************* STANDALONE FUNCTIONS ************************************

// reads ppm data from file named as source_ppm_file_name. stores data in destination_object
// which is already created outside of the function.
int read_ppm(const string source_ppm_file_name, ppmImage &destination_object)
{
	return destination_object.Read(source_ppm_file_name);	
}


// writes ppm data from source_object to the file named destination_ppm_file_name.
int write_ppm(const string destination_ppm_file_name, const ppmImage &source_object)
{
	return source_object.Write(destination_ppm_file_name);	
}

// reads images from filename_image1 and filename_image2. Adds them and saves the resulting
// image to filename_image3
int test_addition(const string filename_image1, const string filename_image2, const string filename_image3)
{
	ppmImage obj1(filename_image1), obj2(filename_image2); //Reading this files via objects
	ppmImage sumobj = obj1 + obj2;					//Then these objects add and the result will be stored in the sumObj
	return write_ppm(filename_image3, sumobj);		//After that this written to filename_iamge3		
}

// reads images from filename_image1 and filename_image2. Subtracts filename_image2 from
// filename_image1 saves the resulting image to filename_image3
int test_subtraction(const string filename_image1, const string filename_image2, const string filename_image3)
{
	ppmImage obj1(filename_image1), obj2(filename_image2);	//Reading this files via objects
	ppmImage sumobj = obj1 - obj2;						//Then these objects substract and the result will be stored in the sumObj
	return write_ppm(filename_image3, sumobj);			//After that this written to filename_iamge3	
}

int swap_channels(ppmImage &obj_object_to_be_modified, int swap_choice)
{
	// 'obj_object_to_be_modified: a reference to a ppmImage object that will be modified by the function
	// swap choice determines which pixels should be swap each other
	return obj_object_to_be_modified.SwapPixelChannels(swap_choice);	
}


ppmImage single_color(const ppmImage &source, int color_choice)
{
	point dimensions = source.Size();			// the dimensions of the source	image are gotten using the Size getter.
												// which returns a point object representing the width and height of the image

	ppmImage copy(dimensions, source.getMaxLimit());	// the ppmImage object(copy) is created with the same dimensions and maximum color limit as the source image.

	//using two nested for loops, and extracts the value of the specified color channel (color_choice) from each pixel.
	for(int i = 0; i < dimensions.getY(); i++)
	{					
		for(int j = 0; j < dimensions.getX(); j++)
		{					
			copy(i, j, color_choice) = source(i, j, color_choice);		//This value is then assigned to the 'copy' image									
		}
	}
	return copy;	//Then, 'copy' image is returned
}

int test_print(const string filename_image1)
{
	ppmImage obj(filename_image1);	 //This constructor reads the PPM file and stores them in the ppmImage object.
	cout << obj;					// This operator overload function is defined in the ppmImage class and prints the contents of the image to the terminal.
	cout<<endl;
	return 1;
}


//************************** point's class functions **************************************


//	other object x and y  values sets to the given point's of x and y respectively
void point::operator=(const point &other)
{
	x = other.x;
	y = other.y;
}

// out : reference to an output stream (ostream&)
// p: a constant reference to a 'point' object (const point&)
// This function defines an overloaded stream insertion operator (<<) for the point class.
// This operator  is to allow an object of the point class to be printed to an output stream 
ostream & operator<<(ostream &out, const point &p)
{
	out << p.x << " " << p.y;	
	return out;					
}

// in: a reference to an input stream (istream&)
// p: a reference to a point object (point&)
// This function  defines an overloaded stream extraction operator (>>) for the point class.
// The aim is to allow input from an input stream (istream) into an object of the point class using the standard input syntax (>>).
istream & operator>>(istream &in, point &p)
{
	in >> p.x >> p.y;	
	return in;
}


/*************************** pixel's class functions *************************************/

//Inside the constructor,  call another constructor of the pixel class that takes three integers as arguments.
// The three integers passed to the other constructor are the values of arr[0], arr[1], and arr[2], respectively
pixel::pixel(const int arr[3]) : pixel(arr[0], arr[1], arr[2]){}

//The constructor takes three integer arguments _r, _g, and _b, which represent the red, green, and blue color channels of the pixel.
// and initialize the member variables r, g, and b with the values of _r, _g, and _b, respectively. 
pixel::pixel(int _r, int _g, int _b) : r(_r), g(_g), b(_b){}


//The operator takes a single argument arr, which is an array of three integers representing the values for the red, green, and blue color channels of the pixel.
// Inside the operator function, the values of r, g, and b are set to the corresponding values in the arr array using the index operator [].
void pixel::operator=(const int arr[3])
{
	r = arr[0]; 
	g = arr[1]; 
	b = arr[2];
}

//The operator takes a pixel object
//Inside the operator function, the r, g, and b values of the current pixel object are set to the corresponding values of the other pixel object.
void pixel::operator=(const pixel &other){
	r = other.r;
	g = other.g;
	b = other.b;
}

// out : reference to an output stream (ostream&)
// p: a constant reference to a 'pixel' object (const piexl&)
// This function defines an overloaded stream insertion operator (<<) for the pixel class.
// This operator  is to allow an object of the pixel class to be printed to an output stream 
ostream & operator<<(ostream &out, const pixel &p){
	out << p.r << " " << p.g << " " << p.b;	
	return out;
}

// in: a reference to an input stream (istream&)
// p: a reference to a pixel object (pixel&)
// This function  defines an overloaded stream extraction operator (>>) for the pixel class.
// The aim is to allow input from an input stream (istream) into an object of the pixel class using the standard input syntax (>>).
istream & operator>>(istream &in, pixel &p)
{
	in >> p.r >> p.g >> p.b;	
	return in;
}

int & pixel::operator[](int index)
{
	//checks the value of the index parameter and returns  color channel of the pixel object
	if (index == 0)
	{
		return r;
	}
	 else if (index == 1) {
		return g;
	}
	 else if (index == 2) {
		return b;
	} 
	else
	{
		throw "Reach the limit!";
	}
}

const int & pixel::operator[](int index) const
{
	//checks the value of the index parameter and returns  color channel of the pixel object
	if (index == 0)
	 {
		return r;
	}
	 else if (index == 1) {
		return g;
	}
	 else if (index == 2) {
		return b;
	} 
	else
	{
		throw "Reach the limit!";
	}
}



//overloads the addition operator 
//Taking a pixel object other as input and returns a new pixel object 
//that is the result of adding the color channels of this and other pixel objects
pixel pixel::operator+(const pixel &other) const
{
	return pixel(r + other.r, g + other.g, b + other.b);
}

//overloads the substraction operator 
//Taking a pixel object other as input and returns a new pixel object 
//that is the result of substracting the color channels of this and other pixel objects
pixel pixel::operator-(const pixel &other) const
{
	return pixel(r - other.r, g - other.g, b - other.b);
}

pixel & pixel::valuesLower(int lower)
{	
	if( r < lower) r = lower; 	else r = r;
	if(g < lower ) g = lower;	else g = g;
	if(b < lower ) b = lower;	else b = b;

	return *this;
}

pixel & pixel::valuesUpper(int upper)
{
	if( r > upper) r = upper; 	else r = r;
	if(g > upper ) g = upper;	else g = g;
	if(b > upper ) b = upper;	else b = b;

	return *this;
}

pixel & pixel::values(int lower, int upper)
{
	valuesLower(lower);
	valuesUpper(upper);
	return *this;
}


//********************** ppmImage *********************************************

ppmImage::ppmImage() : ppmImage(point(0, 0), 255){}

ppmImage::ppmImage(const ppmImage &obj) : ppmImage(obj.dimensions, obj.MaxLimit)
{
	*this = obj;	
}

ppmImage::ppmImage(const string &_fileName) : ppmImage(point(0, 0), 255)
{
	Read(_fileName);
}

ppmImage::ppmImage(const point &_dimensions) : ppmImage(_dimensions, 255){}


ppmImage::ppmImage(int _dimensionX, int _dimensionY) : ppmImage(point(_dimensionX, _dimensionY), 255){}


ppmImage::ppmImage(const point &_dimensions, int _MaxLimit) 
		: dimensions(_dimensions), data(), MaxLimit(_MaxLimit)
{
	data.resize(dimensions.getY());	

	for(int i = 0; i < dimensions.getY(); i++)	
		data[i].resize(dimensions.getX());
}

ppmImage::ppmImage(int _dimensionX, int _dimensionY, int _MaxLimit) 
		: ppmImage(point(_dimensionX, _dimensionY), _MaxLimit)
{
	
}

int ppmImage::Read(string _fileName)
{	
	int flag = 1;		
	string magic;	
	ifstream fin(_fileName);

	fin >> magic;	

	if(magic != "P3")
		flag = 0;

	if(magic ==  "P3"){		
	
			fin >> dimensions;	//Taking row and column from the file .
			
			data.resize(dimensions.getY());		

			fin >> MaxLimit;	//Taking maximum nuber from the file
								
			pixel tempPixel;	//creating pixel object because in ppmImage class vector of vector's type is pixel								

			for(int i = 0; i < dimensions.getY(); i++)
			{	
				data[i].resize(dimensions.getX());

				for(int j = 0; j < dimensions.getX(); j++)
				{	
					fin >> tempPixel;	

					if(	   (tempPixel.getR() >255 && tempPixel.getG() >255 && tempPixel.getB() >255) 
						|| (0 > tempPixel.getR() && 0 > tempPixel.getG() &&0 > tempPixel.getB() ) 
						|| fin.eof()){
						flag = 0;	
						break;				
					}
					
					data[i][j] = tempPixel;
				}
				if(!flag) break;
			}
	}
	fin.close();	//closing the file
	return flag;	//returning value for checking this blog is executed successfully or not
}

int ppmImage::Write(string _filename) const{

	int flag = 1;	

	ofstream fout(_filename);	

	fout << *this;	

	fout<<"\n";
	fout.close();	

	return flag;
}



void ppmImage::Resize(int _dimensionX, int _dimensionY)
{
	dimensions.set(_dimensionX, _dimensionY);//which are the new dimensions of the image in terms of width and height respectively.
											
	data.resize(dimensions.getY());	
	// loop is used to resize each row in data to match the new width.
	for(int i = 0; i < dimensions.getY(); i++)	
		data[i].resize(dimensions.getX());
}

int ppmImage::SwapPixelChannels(int swapChoice){

	int flag = 1;	

	if(!(swapChoice > 0 && swapChoice < 4))
	{
		cout<<"Entered wrong swapChoice!\n";
		flag = 0;
		exit(1);
	}
	int first,second,temp;
	//Checking swap choice
	if(swapChoice == 1)	// swap red green
	{
		first = 1;
		second = 2;
	}
	else if(swapChoice == 2)	//swap red blue
	{
		first = 1;
		second = 3;
	}
	else if(swapChoice ==3)	//swap green blue
	{
		first = 2;
		second = 3;
	}


	if(swapChoice > 0 && swapChoice < 4){	//swapping occurs in this nested loop
		for(int i = 0; i < dimensions.getY(); i++)
		{		
			for(int j = 0; j < dimensions.getX(); j++)
			{	
				int temp = (*this)(i, j, first);
				(*this)(i, j, first) = (*this)(i, j, second); 
				(*this)(i, j, second) = temp; 
			}
		}
	}
	else	flag=0;

	return flag;
}

//This function overloads the [] operator for the ppmImage
//to allow access to the pixel vector stored in the data member of the object
// The function takes an integer index as a parameter and returns a constant reference to the pixel vector stored in the data member at that index.
vector<pixel> & ppmImage::operator[](int index)
{
	return data[index];	
}

//This function overloads the [] operator for the ppmImage
//to allow access to the pixel vector stored in the data member of the object
// The function takes an integer index as a parameter and returns a constant reference to the pixel vector stored in the data member at that index.
//the object's data is not modified unintentionally or without proper access control.
const vector<pixel> & ppmImage::operator[](int index) const
{
	return data[index];
}

//the assignment operator for the ppmImage class
void ppmImage::operator=(const ppmImage &obj)
{
	int x1 = obj.dimensions.getX();
	int y1 = obj.dimensions.getY();

	//the dimensions of the current object are resized to match the dimensions of obj
	Resize(x1,y1);	

	//set to the maximum pixel value of obj.
	MaxLimit = obj.MaxLimit;


	//The loop iterates over each row and column in the image, and for each pixel,
	// it assigns the value of the corresponding pixel in obj to the current object.
	for(int i = 0; i < dimensions.getY(); i++)
	{		
		for(int j = 0; j < dimensions.getX(); j++)
		{	
			data[i][j] = obj[i][j];				
		}
	}
}

// the addition operator for two ppmImage objects.
//  It returns a new ppmImage object that contains the result of the addition operation.
ppmImage ppmImage::operator+(const ppmImage &obj)
{
	ppmImage sumobj;	

	//getting x and y values to comapre objects dimentions
	int x1=dimensions.getX();
	int x2 = obj.dimensions.getX();
	int y1=dimensions.getY();
	int y2 = obj.dimensions.getY();

		if(x1 == x2 && y1 == y2){	//comparing the dimensions and obj.dimensions
		sumobj.Resize(x1,x2);
		
		int tempMaxValue;

		//greater one among the objects stored in MaxLimit
		if(MaxLimit > obj.MaxLimit)
			tempMaxValue = MaxLimit;
		else	
			tempMaxValue = obj.MaxLimit;


		sumobj.setMaxLimit(tempMaxValue);

		pixel sumPixel;	//It is for sum of each pixels


		for(int i = 0; i < dimensions.getY(); i++){
			for(int j = 0; j < dimensions.getX(); j++){
				sumPixel = data[i][j] + obj[i][j];				// Adding the pixels 		
				sumobj[i][j] = sumPixel.valuesUpper(tempMaxValue);	// setting sum image's according to tempMaxValue
			}
		}
	}

	return sumobj; //returning this object
}

// the subtraction operator for two ppmImage objects.
//  It returns a new ppmImage object that contains the result of the subtraction operation.
ppmImage ppmImage::operator-(const ppmImage &obj)
{
	ppmImage substraction;	
	
	//getting x and y values to comapre objects dimentions
	int x1=dimensions.getX();
	int x2 = obj.dimensions.getX();
	int y1=dimensions.getY();
	int y2 = obj.dimensions.getY();

	if( x1 == x2 && y1 == y2 ){	//comparing the dimensions and obj.dimensions
		substraction.Resize(x1,x2);	
		
		int tempMaxValue;

		//greater one among the objects stored in MaxLimit
		if(MaxLimit > obj.MaxLimit)
			tempMaxValue = MaxLimit;
		else	
			tempMaxValue = obj.MaxLimit;
			

		substraction.setMaxLimit(tempMaxValue);

		pixel subsPixel;	//It is for substarction of each pixels

	
		for(int i = 0; i < dimensions.getY(); i++){
			for(int j = 0; j < dimensions.getX(); j++){
				subsPixel = data[i][j] - obj[i][j];				// Substarct the pixels 
				substraction[i][j] = subsPixel.valuesLower(0);		// setting sum image's according to tempMaxValue
			}
		}
	}
	return substraction;	//returning this object
}

// out : reference to an output stream (ostream&)
// obj: a constant reference to a 'ppmImage' object (const ppmImage&)
// This function defines an overloaded stream insertion operator (<<) for the ppmImgae class.
// This operator  is to allow an object of the ppmImgae class to be printed to an output stream 
ostream & operator<<(ostream &out, const ppmImage &obj)
{
	out << "P3\n";
	out	<< obj.dimensions << "\n";
	out	<< obj.MaxLimit << "\n";
		
	for(int i = 0; i < obj.dimensions.getY(); i++)
	{
		for(int j = 0; j < obj.dimensions.getX(); j++)
			out << obj[i][j] << "  ";
		out << "\n";
	}
	return out;
}

int & ppmImage::operator()(int row, int column, int pIndex)
{
	//The data variable of the ppmImage class is a two-dimensional vector of pixels,
	// so data[row][column] returns the pixel at the specified row and column. 
	//The [pIndex - 1] part of the expression then accesses the specified component of the pixel
	return data[row][column][pIndex - 1];	//The -1 is needed because the index starts at 0 in the pixel class.
}

const int & ppmImage::operator()(int row, int column, int pIndex) const
{
	//The data variable of the ppmImage class is a two-dimensional vector of pixels,
	// so data[row][column] returns the pixel at the specified row and column. 
	//The [pIndex - 1] part of the expression then accesses the specified component of the pixel
	return data[row][column][pIndex - 1];	//The -1 is needed because the index starts at 0 in the pixel class.
}

