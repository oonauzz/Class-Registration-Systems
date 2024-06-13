package user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

//This class takes user input and execute the administration and registration behaviors for the admin and student.
public class CourseRegistration {
	
	public static void main(String[] args){
		//Declare three ArrayLists
		ArrayList <Course> allCourses = new ArrayList<>();
		ArrayList <Course> Full = new ArrayList<>();
		ArrayList<Student> allStudents = new ArrayList<>();
		
		//Use a BufferedReader to read the csv file by declaring new FileReader and BufferedReader objects
		String fileName = "MyUniversityCourses (4) (2).csv";
		String line = null;
		try{
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//Skip the first line
			bufferedReader.readLine();
			//For each line, split the String based on "," and instantiate a Course object with the information then add it to allCourses
			while((line = bufferedReader.readLine()) != null) {
				String[] info = line.split(",");
				String Name = info[0];
			    String ID = info[1];
			    int maxStudents = Integer.parseInt(info[2]);
			    int currentStudents = Integer.valueOf(info[3]);
			    ArrayList<Student> studentList = null;
			    String instructor=info[5];
			    int section = Integer.parseInt(info[6]);
			    String location = info[7];
			    Course c = new Course(Name, ID, maxStudents, currentStudents, studentList, instructor, section, location);
			    allCourses.add(c);
			    //System.out.println("Course Name: " + c.courseName + "\nCourse ID: " + c.courseID +
			      //      "\nMax Students: " + c.maxStudents + "\nCurrent Students: " + c.currentStudents +
			        //       "\nInstructor: " + c.instructor + "\nSection: " + c.section + "\nLocation: " + c.location + "\n");
			}
			//Close the BufferedReader object
			bufferedReader.close();
		}
		//Catch any errors here and print statements.
		catch(FileNotFoundException ex){
			System.out.println( "Unable to open file '" + fileName + "'.");			
			ex.printStackTrace();
		}
		catch (IOException ex) {
			System.out.println( "Error reading file '" + fileName + "'.");
			ex.printStackTrace();
		}
		
		//Deserialize the AllCourses.txt and read object as an ArrayList c1
		try
		{  
			// Reading the object from a file
			FileInputStream file = new FileInputStream("AllCourses.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			ArrayList<Course> c1 = (ArrayList<Course>)in.readObject();
			allCourses = c1;
			in.close();
			file.close();
			System.out.println("Object has been deserialized.\n");
		}
		//Print a statement if no file is created to be deserialize.
		catch(IOException ex)
		{
			System.out.println("First run - No file to deserialize yet.\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Declare one Student object and add to allStudents
		Student s1 = new Student("Oona", "Zhou", "Oona", "Zhou", allCourses, null);
		allStudents.add(s1);
		
		//Use a Scanner object to ask if the user is an admin or student
		Scanner scanner  = new Scanner(System.in);
		System.out.println("Are you an Admin or Student? (Note: After each change made, please exit to save/serialize the changes.)");
		String input = scanner.nextLine();
		
		//If user entered "Admin", then ask for first and last names, initialize an Admin object
		if (input.equals("Admin")) {
			System.out.print("Enter your first name: ");
			String firstN1 = scanner.nextLine();
			System.out.print("Enter your last name: ");
			String lastN1 = scanner.nextLine();
			Admin admin = new Admin("Admin", "Admin001", firstN1, lastN1, allCourses, allStudents);
			System.out.println("Admin account has been created! Username and password are set to default.\n");
	        int option;
	        
	        //Use a do-while loop and ask the admin to select from the menu
			do {
				System.out.println("What would you like to do? ");
				System.out.println("Menu: \n(1) Create a course; \n(2) Delete a course; \n(3) Edit a course; \n(4) Display a course; \n(5) Register a student; \n(6) View all courses; \n(7) View courses that are full; \n(8) Write the full courses to a list; \n(9) View students in a course; \n(10) View a student's course; \n(11) Sort courses based on number of students in it; \n(12) Exit.");
				System.out.print("Enter a number option: ");
				option = scanner.nextInt();
				
				//Use a switch to do difference actions based on the number inputed
				switch (option) {
		    		case 1:
		    			//If input one, create a course by entering all information, and add to allCourses
		    			System.out.print("Course name would you like to create: ");
		    			Scanner scn = new Scanner(System.in);
		    			String cn = scn.nextLine();
		    			
		    			System.out.print("Course ID: ");
		    			Scanner scn1 = new Scanner(System.in);
		    			String cid = scn1.nextLine();
		    			
		    			System.out.print("Maximum students: ");
		    			Scanner scn2 = new Scanner(System.in);
		    			int ms = scn2.nextInt();
		    			
		    			System.out.print("Instructor: ");
		    			Scanner scn3 = new Scanner(System.in);
		    			String ci = scn3.nextLine();
		    			
		    			System.out.print("Section: ");
		    			Scanner scn4 = new Scanner(System.in);
		    			int cs = scn4.nextInt();
		    			
		    			System.out.print("Location: ");
		    			Scanner scn5 = new Scanner(System.in);
		    			String cl = scn5.nextLine();
		    			
		    			Course create = new Course(cn,cid,ms,0,null,ci,cs,cl);
		    			admin.create(create);
		    			break;
		    		case 2:
		    			//If input two, Loop through allCourses and remove the entered course
		    			System.out.print("Which course would you like to delete?\n");
		    			Scanner sc2 = new Scanner(System.in);
		    			String temp1 = sc2.nextLine();
		    			Course x = new Course();
		    			for (Course course : allCourses) {
		    				if (course.courseName.equals(temp1)) {
		    					x = course;
		    				}
		    				
		    			}
		    			allCourses.remove(x);
		    			admin.delete(x);
		    			break;
		    		case 3:
		    			//If input three, loop through allCourses to find the course, and edit based on more information
		    			System.out.print("Which course would you like to edit? \n");
		    			Scanner sc3 = new Scanner(System.in);
		    			String temp2 = sc3.nextLine();
		    			for (Course course : allCourses) {
		    				if (course.courseName.equals(temp2)) {
		    					admin.edit(course);
		    				}
		    			}
		    			break;
		    		case 4:
		    			//If input four, display the information of the entered course
		    			System.out.print("Which course would you like to display? Enter course name: ");
		    			Scanner sc4 = new Scanner(System.in);
		    			String temp3 = sc4.nextLine();
		    			for (Course course : allCourses) {
		    				if (course.courseName.equals(temp3)) {
		    					admin.display(course);
		    				}
		    			}
		    			break;
		    		case 5: 
		    			//If input five, register a new student by input the information. Then initialize a Student object and add to allStudents
		    			System.out.println("Enter the first name of the student you would like to register: ");
		    			Scanner sc5 = new Scanner(System.in);
		    			String temp40 = sc5.nextLine();
		    			System.out.println("Enter the last name of the student you would like to register: ");
		    			String temp41 = sc5.nextLine();
		    			Student student = new Student(temp40,temp41,temp40,temp41,null,null);
		    			admin.registerStudent(student);
		    			break;
		    		case 6:
		    			//If input six, call the viewAllCourse in the User class
		    			admin.viewAllCourse();
		    			break;
		    		case 7:
		    			//If input seven, call the viewFullCourse in Admin class
		    			//admin.viewFullCourse();
		    			try
		    			{ 
		    				FileInputStream file0 = new FileInputStream("FullCourses.ser");
		    				ObjectInputStream in0 = new ObjectInputStream(file0);
		    				ArrayList<Course> f1 = (ArrayList<Course>)in0.readObject();
		    				for (Course c:f1) {
		    					System.out.println("Course Name: " + c.courseName + "\nCourse ID: " + c.courseID +
		    			              "\nMax Students: " + c.maxStudents + "\nCurrent Students: " + c.currentStudents +
		    			               "\nInstructor: " + c.instructor + "\nSection: " + c.section + "\nLocation: " + c.location + "\n");
		    					
		    				}
		    				in0.close();
		    				file0.close();
		    			}
		    			//Print a statement if no file is created to be deserialize.
		    			catch(IOException ex)
		    			{
		    				System.out.println("No class is full yet. \n");
		    			} catch (ClassNotFoundException e) {
		    				e.printStackTrace();
		    			}
		    			break;
		    		case 8:
		    			//If input eight, write the full courses to a file with serialization
		    			try {
		    				//Declare FileOutputStram and ObjectOutputStream objects, and write every course object in Full to FullCourses.txt
		    				FileOutputStream file = new FileOutputStream("FullCourses.ser");
		    				ObjectOutputStream out = new ObjectOutputStream(file);
		    				if (Full.size()==0) {
		    					System.out.println("No class is full yet. \n");
		    				}else {
		    					for (Course course:Full) {
		    						out.writeObject(course);
		    					}
		    					out.flush();
		    					out.close();
		    					file.close();
		    					System.out.println("File has been written and deserialized.");
		    				}
		    			} //If catch an exception, print the statement
		    			catch (IOException e) {
		    				System.out.println("IOException is caught.");
		    			}
		    			break;
		    		case 9:
		    			//If input nine, ask the admin to enter a course and then display its studentList
		    			System.out.print("Enter the name of the course that you would like to view the students: ");
		    			Scanner sc6 = new Scanner(System.in);
		    			String temp5 = sc6.nextLine();
		    			for (Course course : allCourses) {
		    				if (course.courseName.equals(temp5)) {
		    					admin.viewStudent(temp5);
		    				}
		    			}
		    			break;
		    		case 10:
		    			//If input ten, ask the admin to enter the last name of a student, and then print his/her registeredCourses list
		    			System.out.print("Enter the last name of the student whose courses you would like to view: ");
		    			Scanner sc7 = new Scanner(System.in);
		    			String temp6 = sc7.nextLine();
		    			for (Student s : allStudents) {
		    				if (s.lastName.equals(temp6)) {
		    					admin.viewStudentsCourse(temp6);
		    				}
		    			}
		    			break;
		    		case 11: 
		    			//If input eleven, call the sortCourses method in admin class, and print based on order.
		    			admin.sortCourses(allCourses);
		            	for (Course course : allCourses) {
		            		System.out.println("Course Name: " + course.courseName + "\nCourse ID: " + course.courseID +
					               "\nMax Students: " + course.maxStudents + "\nCurrent Students: " + course.currentStudents +
					               "\nInstructor: " + course.instructor + "\nSection: " + course.section + "\nLocation: " + course.location);			  
		            	}
		            	break;
				}
			} //If the number entered in 12, break from loop and exit
			while(option!=12); 
			
		}
		
		else if (input.equals("Student")) {
			//If the user is a student, ask the student to input information. Then declare a Student object and add to allStudents
			System.out.print("Enter your username: ");
			String userName = scanner.nextLine();
			System.out.print("Enter your password: ");
			String passWord = scanner.nextLine();
			System.out.print("Enter your first name: ");
			String firstN2 = scanner.nextLine();
			System.out.print("Enter your last name: ");
			String lastN2 = scanner.nextLine();
			Student student = new Student(userName, passWord, firstN2, lastN2, allCourses, null);
			allStudents.add(student);
			//System.out.println(allStudents.size());
			System.out.println("Student account has been created! You are registered in 0 class now.");
			int option2;
			
			//Ask the student to enter a number based on the menu with the Scanner object
			do {
				System.out.println("What would you like to do? ");
				System.out.println("Menu: \n(1) View all courses; \n(2) View all courses you registered in; \n(3) View open courses; \n(4) Register a course; \n(5) Withdraw from a course; \n(6) Exit.");
				System.out.print("Enter a number option: ");
				option2 = scanner.nextInt();
				
				//A switch to perform different actions based on numbers entered
				switch (option2) {
					case 1:
						//If input one, call the viewAllCourse method from User class
						student.viewAllCourse();
						break;
					case 2:
						//If input two, call the viewMyCourse method from Student class
						student.viewMyCourse();
						break;
					case 3:
						//If input three, call the viewOpen method from Student class
						student.viewOpen();
						break;
					case 4:
						//If input four, ask student to input the course name that he/she wants to register in, and add it to his/her registeredCourses
						System.out.print("Enter the course that you would like to register: ");
						Scanner sc8 = new Scanner(System.in);
						String temp7 = sc8.nextLine();
						ArrayList<Student> st3= new ArrayList<>();
						ArrayList<Course> st5 = new ArrayList<>();
						Course b = new Course();
						for (Course course : allCourses) {
							if (course.courseName.equals(temp7)) {
								b = course;
								++ course.currentStudents;
							}
						}
						if (b.studentList != null) {
							for (Student s: b.studentList) {
								st3.add(s);
							}
						}
						if (student.registeredCourse != null) {
							for (Course c : student.registeredCourse) {
								st5.add(c);
							}
						}
						st3.add(student);
						st5.add(b);
						b.setStudentList(st3);
						student.registeredCourse(st5);
						System.out.println("Successful! You are now registered in "+ student.registeredCourse.size() + " class(es). \n");
						break;
					case 5:
						//If input five, ask student to input the course name that he/she wants to withdraw from, and remove it from his/her registeredCourses
						System.out.print("Enter the course that you would like to withdraw: ");
						Scanner sc9 = new Scanner(System.in);
						String temp8 = sc9.nextLine();
						ArrayList<Student> st4= new ArrayList<>();
						ArrayList<Course> st6 = new ArrayList<>();
						Course a = new Course();
						for (Course course : allCourses) {
							if (course.courseName.equals(temp8)) {
								a = course;
								-- course.currentStudents;
							}
						}
						
						if (a.studentList != null) {
							for (Student s: a.studentList) {
								st4.add(s);
							}
						}
						if (student.registeredCourse != null) {
							for (Course c : student.registeredCourse) {
								st6.add(c);
							}
						}
						if (st4 != null) {
							st4.remove(a);
							a.setStudentList(st4);
						}
						if (st6 != null) {
							st6.remove(a);
							student.registeredCourse(st6);
						}
						else {
							System.out.println("No student is registered yet.");
						}
						st4.remove(student);
						a.setStudentList(st4);
						System.out.println("You are now registered in "+ student.registeredCourse.size()+ " class(es).");
						break;
				}
			} //If number entered is 6, break from loop and exit
			while(option2!=6);
		}
		
		//Declare new FileOutputStram and ObjectOutputStream objects, and write every course object in allCourse to AllCourses.txt
		try {
			FileOutputStream file1 = new FileOutputStream("AllCourses.ser");
			ObjectOutputStream out1 = new ObjectOutputStream(file1);
			for (Course course:allCourses) {
				out1.writeObject(allCourses);
			}
			out1.flush();
			out1.close();
			file1.close();
			System.out.println("Object has been serialized.");
		} //If catch an exception, print the statement
		catch (IOException e) {
			System.out.println("IOException is caught.");
		}
	}
}
