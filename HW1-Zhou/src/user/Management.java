package user;

//This is an interface for the Admin class, it includes several abstract class
public interface Management {
	
	void create(Course courseName);
	void delete(Course courseName);
	void edit(Course courseName);
	void display(Course course);
	void registerStudent(Student lastName);
	void viewStudent(String cName);
	void viewStudentsCourse(String lastName);
	
}
