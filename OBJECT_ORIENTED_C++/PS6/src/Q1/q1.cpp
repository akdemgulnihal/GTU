#include <iostream>
#include <string>

using namespace std;

class DynamicStringArray
{
    private:
        string* dynamicArray;
        int size;
    public:
        DynamicStringArray() : dynamicArray(NULL), size(0) {}
        int getSize() const { return size; }
        void addEntry(string entry);
        bool deleteEntry( string entry);
        string getEntry(int index) ;
        DynamicStringArray(const DynamicStringArray& otherObj);
        ~DynamicStringArray();
        DynamicStringArray& operator=(const DynamicStringArray& rightSide);
};

//Copy constructor
DynamicStringArray::DynamicStringArray(const DynamicStringArray& other)
{
    size = other.size;  //copying the size
    if(size == 0)
    {
        dynamicArray = NULL;
    }
    else
    {
        dynamicArray = new string[size];
    for (int i = 0; i < size; i++) 
        dynamicArray[i] = other.dynamicArray[i];
    }
    
}

DynamicStringArray::~DynamicStringArray() 
{
    if(dynamicArray != NULL)
        delete[] dynamicArray; 
}

DynamicStringArray& DynamicStringArray::operator=(const DynamicStringArray& other) 
{
    if(this->dynamicArray != NULL)
    {
         delete[] dynamicArray;  
    }
    if(other.size == 0)
    {
        this->size = 0;
        this->dynamicArray = NULL;
    }
    else
    {
        this->size = other.size;
        this->dynamicArray = new string[size];
        for (int i = 0; i < size; i++) 
        {
            dynamicArray[i] = other.dynamicArray[i];    //t the dynamic array is properly copied to the target object
        }
    }

    return *this;  
}

void DynamicStringArray::addEntry( string entry)
 {
    string* newArray = new string[size + 1]; //increment the size ,and create new dynamic array
    for (int i = 0; i < size; i++) 
    {
        newArray[i] = dynamicArray[i];  //dynamic array copying to the newArray
    }
    newArray[size] = entry;
    delete[] dynamicArray;
    dynamicArray = newArray;
    size++;
}

bool DynamicStringArray::deleteEntry( string entry) {
    int indexToDelete = -1;
    for (int i = 0; i < size; i++) {
        if (dynamicArray[i] == entry) {
            indexToDelete = i;
            break;
        }
    }
    if (indexToDelete == -1) {
        return false;
    }
    string* newArray = new string[size - 1];
    int j = 0;
    for (int i = 0; i < size; i++) {
        if (i != indexToDelete) {
            newArray[j] = dynamicArray[i];
            j++;
        }
    }
    delete[] dynamicArray;
    dynamicArray = newArray;
    size--;
    return true;
}

string DynamicStringArray::getEntry(int index) 
{
    if (index < 0 || index >= size)
        return NULL;
    return dynamicArray[index];
}

int main()
{
    DynamicStringArray names;
    // List of names
    names.addEntry("Frank");
    names.addEntry("Wiggum");
    names.addEntry("Nahasapeemapetilon");
    names.addEntry("Quimby");
    names.addEntry("Flanders");
    // Output list
    cout << "List of names:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    // Add and remove some names
    names.addEntry("Spuckler");
    cout << "After adding a name:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    names.deleteEntry("Nahasapeemapetilon");
    cout << "After removing a name:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    names.deleteEntry("Skinner");
    cout << "After removing a name that isn't on the list:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    names.addEntry("Muntz");
    cout << "After adding another name:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl
    ;
    // Remove all of the names by repeatedly deleting the last one
    while (names.getSize() > 0) {
        names.deleteEntry(names.getEntry(names.getSize() - 1));
    }
    cout << "After removing all of the names:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    names.addEntry("Burns");
    cout << "After adding a name:" << endl;
    for (int i = 0; i < names.getSize(); i++)
        cout << names.getEntry(i) << endl;
    cout << endl;

    cout << "Testing copy constructor" << endl;
    DynamicStringArray names2(names);
    // Remove Burns from names
    names.deleteEntry("Burns");
    cout << "Copied names:" << endl;
    for (int i = 0; i < names2.getSize(); i++)
        cout << names2.getEntry(i) << endl;
    cout << endl;

    cout << "Testing assignment" << endl;
    DynamicStringArray names3 = names2;
    // Remove Burns from names2
    names2.deleteEntry("Burns");
    cout << "Copied names:" << endl;
    for (int i = 0; i < names3.getSize(); i++)
        cout << names3.getEntry(i) << endl;
    cout << endl;

    cout << "Enter a character to exit." << endl; 
    char wait;
    cin >> wait;
    return 0;
}