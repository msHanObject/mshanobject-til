package trans_namoosori.sprout.java.base01;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author nulgraces
 *
 */
//	T) 주어진 날수를 주와 날로 바꾸고 현재날짜로 부터 지난 날짜 구하기
public class Trans_Example06 {

	static Scanner scanner;
	static int dayNum;
	public Trans_Example06() {
		super();
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trans_Example06 te06 = new Trans_Example06();
		Date date = new Date();
		inputDay();//날수 입력
		
		int weeks = dayNum / 7;//날수를 주로 변경한 값
		int days = dayNum % 7;//날수를 주로 변경하고 남은 일수
		System.out.println(dayNum+"일은 "+ weeks + "주 " + days + "일 입니다.");
		
		System.out.println("현재 날짜: "+date.getYear()+"년 "+date.getMonth()+"월 "+date.getDay()+"일 입니다.");
	
		int daysOfMonth=0;//한달의 일수
		int increasedDay = 0;//증가된 일수
		int increasedMonth = 0;//증가된 월수
		
		int firstDay = date.day;//초기 일수 설정
		int firstMonth = date.month;//초기 월수 설정
		int surplusD=0;//남은 일수
		int surplusM=0;//남은 월수
		
		for(int i=0; i<dayNum; i++) {//현재 날짜로부터 입력된 dayNum이 지난 날짜 계산 
			
			//해당 월의 일수 ndays에 입력
			if (date.month<8 && date.month%2 !=0 || date.month >=8 && date.month%2 ==0) {
				daysOfMonth= 31;
			}else if(date.month==2) {
				daysOfMonth = 28;
			}else {
				daysOfMonth= 30;
			}
			
			surplusD = daysOfMonth-firstDay;//해당 일로부터 그 달의 남은 일수
			surplusM = 12-firstMonth; //해당 월로부터 그 연도의 남은 월수				
			date.day++;//현재 일수 증가
			increasedDay++;//증가된 요일수 카운팅
			
			if(increasedDay == surplusD+1) {//달이 바뀔때
				date.month++;
				increasedMonth++;
				date.day=1;
				increasedDay=0;
				firstDay=1;
			}
			if(increasedMonth == surplusM+1) {//년도가 바뀔때
				date.year++;
				date.month=1;
				firstMonth=1;
				increasedMonth=0;
			}
			
		}
		//dayNum이 지난 날짜 출력
		System.out.println(dayNum+"일이 지난 날: "+date.year+"년 "+date.month+"월 "+date.day+"일 입니다.");
	}
	
	public static void inputDay() {//날 수 입력 함수
		System.out.print("날 수를 입력하세요: ");
		dayNum = scanner.nextInt();
	}
	
	
}

class Date {//날짜 클래스

	GregorianCalendar cal;
	int year,month,day;
	
	public Date() {
		super();
		// TODO Auto-generated constructor stub
		cal = new GregorianCalendar();
	}
	
	public int getYear() {
		this.year = cal.get(Calendar.YEAR);
		return this.year;
	}
	
	public int getMonth() {
		this.month = cal.get(cal.MONTH)+1;
		return this.month;
	}
	
	public int getDay() {
		this.day = cal.get(cal.DAY_OF_MONTH);
		return this.day;
	}	
}