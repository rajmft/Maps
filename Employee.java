
package assignment1;

import java.util.Objects;

public class Employee {
    
    
    private String iD, fName, lName;
    private double salary;
    
    
    Employee(String iD, String fName, String lName, double salary){
        this.iD = iD;
        this.fName = fName;
        this.lName = lName;
        this.salary = salary;
                
    }
    
    public String getID(){
        return iD;
    }
    
    public String getFirstName(){
        return fName;
    }
    
    public String getLastName(){
        return lName;
    }
    
    public double getSalary(){
        return salary;
    }
    
    public void setID(String newID){
        this.iD = newID;
    }
    
    public void setFirstName(String newFName){
        this.fName = newFName;
    }
    
    public void setLastName(String newLName){
        this.lName = newLName;
    }
    
    public void setSalary(double newSalary){
        this.salary = newSalary; 
    }
    
    
    
    
    
    @Override
    public String toString(){
        return "\nID:"+iD+"\t\tFirst Name:"+fName+"\tLast Name:"+lName+"\t\tSalary: "+salary;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.iD);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this.iD, other.iD);
    }
    
    
    
    

	
}

