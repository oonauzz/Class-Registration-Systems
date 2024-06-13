/**
 * 
 */
package user;

import java.io.*;
import java.util.*;
/**
 * 
 */

//Admin class is a subclass of User and implements the Management interface
public class Admin extends User implements Management{
	
	//declare the Full and allStudents ArrayList
	ArrayList<Course> Full = new ArrayList<>();
	ArrayList<Student> allStudents = new ArrayList<>();;

	//The Admin constructor inherits the User constructor with 6 parameters and add the allStudents parameter 
	protected Admin(String user, String pw, String fName, String lName, ArrayList<Course> allCourses, ArrayList<Student> allStudents){
		super(user, pw, fName, lName, allCourses);
		this.allStudents = allStudents;
		this.allCourses = allCourses;
	}
	
	//The Create method adds a course into the allCourses ArrayList
	public void create(Course courseName) {
		allCourses.add(courseName);
		System.out.println("Course created! \n");
	}
	
	//The Delete methods removes a course from the allCourses ArrayList
	public void delete(Course courseName) {
		System.out.println("Course removed! \n");
	}
	
	//The Edit method asks the user to choose from the numerical menu to select a parameter to edit
	public void edit(Course cName) { 
		Scanner sc= new Scanner(System.in);
		System.out.println("What do you want to edit?");
		System.out.print("(1) Maximum number of students can register; \n(2) Current number of students registered; \n(3) Add student to course; \n(4) Delete student from course; \n(5) Course instructor; \n(6) Section number; \n(7) Course location. \n");
		System.out.println("Enter the number: ");
		int edit = sc.nextInt();
		
		//Based on the number the scanner input takes, each number refers to a case
		switch (edit) {
			//Asks the user to enter a new Max Number of Student for the course
	    	case 1:
	    		System.out.print("Enter the maximum number of students:");
	    		Scanner in = new Scanner(System.in);
	    		int max = in.nextInt();
	    		cName.maxStudents = max;
	    		System.out.println("Successful edit! \n");
	    		break;
	    	//Tell the user that once a student is added or removed from a course, the Current Students will be updated.
	    	case 2:
	    		System.out.print("Please add or remove a student first. This will be updated.");
	    		break;
	    	//Asks the user to add a student by inputing the information
	    	case 3:
	    		System.out.print("Add student, enter first name: ");
	    		Scanner in2 = new Scanner(System.in);
				String firstAdd = in2.nextLine();
				System.out.print("Enter last name: ");
				Scanner in3 = new Scanner(System.in);
				String lastAdd = in3.nextLine();
				Student newStudent = new Student(firstAdd, firstAdd, firstAdd, lastAdd, allCourses, null);
				
				ArrayList<Student> st1= new ArrayList<>();
				if (cName.studentList != null) {
					for (Student s: cName.studentList) {
						st1.add(s);
					}
				}
				st1.add(newStudent);
				cName.setStudentList(st1); 
				++ cName.currentStudents;
	    		System.out.println("Successful edit! \n");	
	    		break;
	    	//Asks the user to remove a student from the course, first loop through allStudent and find the student, then remove the student from studentList of course
	    	case 4: 
	    		System.out.print("Delete student, enter first name: ");
	    		Scanner in4 = new Scanner(System.in);
				String firstDel = in4.nextLine();
				System.out.print("Enter last name: ");
				Scanner in5 = new Scanner(System.in);
				String lastDel = in5.nextLine();
				ArrayList<Student> st2= new ArrayList<Student>();
				Student y = new Student(null,null,null,null,null,null);
				for (Student student : allStudents) {
					if ((student.firstName.equals(firstDel)) && (student.lastName.equals(lastDel))){
						y = student;
					}
				} 
				if (cName.studentList != null) {
					for (Student s: cName.studentList) {
						st2.add(s);
					}
				}
				
				if (st2 != null) {
					st2.remove(y);
					cName.setStudentList(st2);
					-- cName.currentStudents;
				}else {
					System.out.println("No student is registered yet.");
				}
				
	    		System.out.println("Successful edit! \n");	
	    		break;
	    	//Asks the user to input a new course instructor
	    	case 5:
	    		System.out.print("Enter new Course Instructor:");
	    		Scanner in6 = new Scanner(System.in);
	    		String ins= in6.nextLine();
	    		cName.instructor = ins;  
    	    	System.out.println("Successful edit! \n");
	    		break;
	    	//Asks the user to input a new course section number
	    	case 6:
	    		System.out.print("Enter new Section Number:");
	    		Scanner in7 = new Scanner(System.in);
	    		int sec = in7.nextInt();
	    		cName.section = sec;
	    		System.out.println("Successful edit! \n");
	    		break;
	    	//Asks the user to input a new course location
	    	case 7:
	    		System.out.print("Enter new Course Location:");
	    		Scanner in8 = new Scanner(System.in);
	    		String loc= in8.nextLine();
	    		cName.location = loc;
	    		System.out.println("Successful edit! \n");
	    		break;
	    	//If doesn't recognize the number inputed, then print "Invalid Entry"
	    	default:
	    		System.out.println("Invalid entry. \n");
		}
	}
	
	//Display a course by printing out all the parameters
	public void display(Course course) {
		System.out.println("\nCourse Name: " + course.courseName + "\nCourse ID: " + course.courseID +
	               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
	               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location + "\n");
	}
	
	//Register a student by adding the student into the allStudent list
	public void registerStudent(Student student) {
		allStudents.add(student);
		System.out.println("Student registered!\n");
	}
	
	//Loop through allCourse and find courses whose currentStudent equals its maxStudents, then print out the parameters of the course
	public void viewFullCourse() {
		for (Course course : allCourses) {
			if (course.currentStudents == course.maxStudents) {
				Full.add(course);
				System.out.println("Here are all the full courses");
				System.out.println("Course Name: " + course.courseName + "\nCourse ID: " + course.courseID +
			               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
			               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location);	
			}
		}
		if (Full==null) {
			System.out.println("No classes are full yet." + "\n");	
		}
	}

	//Loop through allCourses and find the course, if nobody is registered then print a statement, else print the list of students
	public void viewStudent(String cName) {
		for (Course course : allCourses) {
			if (course.courseName.equals(cName)) {
				if (course.studentList.isEmpty()) {
					System.out.println("No student has registered in this class yet." + "\n");	
				}
				else {
					System.out.println(course.studentList);
				}
			}
		}
	}
	
	//Loop through allStudents and find the student, then print his/her registeredCourses
	public void viewStudentsCourse(String lastName) {
		for (Student student : allStudents) {
			if (student.lastName.equals(lastName)) {
				if (student.registeredCourse.isEmpty()) {
					System.out.println("This student hasn't registered in any class yet." + "\n");	
				}
				else {
					System.out.println(student.registeredCourse);
				}
			}
		}
	}
	
	//Use the collections.sort method and a new Comparator, to compare the currentStudents of every 2 courses, lastly print all courses based on their sorted currentStudents parameter
	public static void sortCourses(ArrayList<Course> allCourses) {
		Collections.sort(allCourses, new Comparator<Course>() {
            @Override
            public int compare(Course course1, Course course2) {return Integer.compare(course1.currentStudents, course2.currentStudents);}
		});
		for(Course cur : allCourses) {
			System.out.println(cur.currentStudents);
		}
    }
}
