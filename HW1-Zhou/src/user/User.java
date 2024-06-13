/**
 * 
 */
package user;

import java.util.ArrayList;
/**
 * 
 */
//This is the User class - parent class of Admin & Student, it also implements the Serializable interface
public abstract class User implements java.io.Serializable{
	
	//This declares the variables of a User class
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected ArrayList<Course> allCourses;

	//Constructor for the User class with 5 parameters
	protected User(String username, String password, String firstName, String lastName, ArrayList<Course> courses) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.allCourses = allCourses;
	}

	//The viewAllCourse loops through allCourses and prints out all courses' information.
	public void viewAllCourse() {
		for (Course course : this.allCourses) {
			System.out.println("Course Name: " + course.courseName + "\nCourse ID: " + course.courseID +
		               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
		               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location + "\n");
		}
		System.out.println("Here are all the courses."+"\n");
	}
}	


