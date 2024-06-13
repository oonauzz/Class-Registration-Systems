package user;

import java.io.*;
import java.util.ArrayList;

//The Serialization class performs serialization & deserialization on class objects
public class Serialization {
	public static void main(String args[]) {
		//instantiate an Employee object
		Course serializedCourse = new Course();
		Course de = null;
		try {
		//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Courses.ser");
					
		//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
					
		//Writes the specific object to the OOS
			oos.writeObject(serializedCourse);
					
		//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete.");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
				
		//Now we will deserialize the same object
		try{
		//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream("Courses.ser");
				      
		//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
				      
		//Cast as Employee. readObject will take the object from ObjectInputStream
			de = (Course)ois.readObject();
			ois.close();
			fis.close();
		}
		//Catches any exceptions and errors
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}
		//Print out the deserialized Course object
		System.out.println("Course Name: " + de.courseName + "\nCourse ID: " + de.courseID +
	               "\nMax Students: " + de.maxStudents + "\nCurrent Students: " + de.currentStudents +
	               "\nInstructor: " + de.instructor + "\nSection: " + de.section + "\nLocation: " + de.location + "\n");		    				 
		}
}

//The Course class implements the Serializable interface
class Course implements java.io.Serializable{
	
	//Declare the variables of a course object
	protected String courseName;
	protected String courseID;
	protected int maxStudents;
	protected int currentStudents;
	protected String instructor;
	protected int section;
	protected String location;
	protected ArrayList<Student> studentList = new ArrayList<>();
	
	//Two constructors with one that takes input and another that doesn't
	public Course() {
		
	}
	
	public Course(String courseName, String courseID, int maxStudents, int currentStudents, ArrayList<Student> studentList, String instructor, int section, String location) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxStudents = maxStudents;
		this.currentStudents = currentStudents;
		this.studentList = studentList;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}
	
	//Setters and Getters for Course variables
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
    	return courseName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseID() {
    	return courseID;
    }
    
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getMaxStudents() {
    	return maxStudents;
    }
    
    public void setCurrentStudents(int currentStudents) {
        this.currentStudents = currentStudents;
    }

    public int getCurrentStudents() {
    	return currentStudents;
    }
    
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
    public ArrayList<Student> getStudentList() {
    	return studentList;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public String getInstructor() {
    	return instructor;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getSection() {
    	return section;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
	
    public String getLocation() {
    	return location;
    }
}
