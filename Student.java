package Model;

public class Student {
	private int id;
	private  String name;
	private double marks;
	public Student(int id,String name,double marks) {
		setMarks(marks);
		this.id=id;
		this.name=name;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		if(marks<0 || marks>100) {
			throw new IllegalArgumentException("Invalid Marks");
		}
		else {
		this.marks = marks;
		}
	}
	public String getGrade() {
		if(marks>=80) {
			return "A";
		}
			else if(marks>=60) {
				return "B";}
				else if(marks>=40) {
					return "C";
			}
				else {
					return "F";
		}
	}
	public String getResult() {
		if(marks>=40) {
			return "Pass";
		}
		else {
			return "Fail";
		}
	}
		
	}
	
	


