package user;

import java.util.ArrayList;

//THe Student class inherits from User class and implements the Enrollment interface
public class Student extends User implements Enrollment{
	
	//Declares the registeredCourse parameter for every Student
	ArrayList<Course> registeredCourse = new ArrayList();
	
	//This constructor inherits from User class and adds a new parameter
	protected Student(String user, String pw, String fName, String lName, ArrayList<Course> allCourses, ArrayList<Course> regCourses){
		super(user, pw, fName, lName, allCourses);
		this.allCourses = allCourses;
		this.registeredCourse = registeredCourse;
	}
	
	//Setters and Getters for parameters
    public void registeredCourse(ArrayList<Course> al) {
        this.registeredCourse = al;
    }
    
	public ArrayList<Course> getCourses() {
        return registeredCourse;
    }
	
	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	//ViewOpen method loops through allCourses and find courses whose currentStudent is less than its maxStudent, and prints out the course information
	public void viewOpen() {
		for (Course course : allCourses) {
			if (course.currentStudents < course.maxStudents) {
				System.out.println("Course Name: " + course.courseName + "\nCourse ID: " + course.courseID +
			               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
			               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location + "\n");
			}
		}
	}
	
	//viewMyCourse loops through registieredCourse and prints out all courses' information
	public void viewMyCourse() {
		if (registeredCourse == null) {
			System.out.println("You haven't registered for any courses yet." + "\n");
		}
		else {
			System.out.println("Here are all the courses you registered:");
			for(Course course: registeredCourse) {
				System.out.println("Course Name: " + course.courseName + "\nCourse ID: " + course.courseID +
		               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
		               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location + "\n");
			}
		}
	}

}
