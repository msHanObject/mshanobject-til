package trans_namoosori.sprout.java.base01;

import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 
public class Trans_Example09 {

	static Scanner scanner;
	
	public Trans_Example09() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example09 te09 = new Trans_Example09();
		Student student = new Student();
		
		System.out.print("학생의 이름을 입력해주세요: ");
		student.name = scanner.nextLine();
		
		System.out.print(student.name+"학생의 국어 성적을 입력해주세요. ");
		student.korean = scanner.nextDouble();
		
		System.out.print(student.name+"학생의 영어 성적을 입력해주세요. ");
		student.english = scanner.nextDouble();
		
		System.out.print(student.name+"학생의 수학 성적을 입력해주세요. ");
		student.math = scanner.nextDouble();
		
		System.out.println(student.name+"학생의 국어성적은 "+student.korean+",영어 성적은 "+student.english+",수학 성적은 "+student.math+"입니다. ");
		
		double totalGrade = student.korean + student.english + student.math;
		double averageGrade = totalGrade /3;
		
		System.out.print(student.name+"학생의 총점은 "+totalGrade+"이고, 평균은 "+averageGrade+"이므로, 학점은"+ calculateGrade(averageGrade) +"입니다. ");
		
		
	}
	public static String calculateGrade(double average) {
		String strGrade;
		if (average >= 90 && average <= 100) {
			return strGrade ="A학점";
			
		} else if (average >= 80) {
			return strGrade ="B학점";
		} else if (average >= 70) {
			return strGrade ="C학점";
		} else if (average >= 60) {
			return strGrade ="D학점";
		} else {
			return strGrade ="F학점";
		}
	}

}

class Grade{
	double korean,english,math;
	
}
class Student extends Grade{
	String name;
	Grade grade;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
		name="";
	}
	
}