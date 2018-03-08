package hash;
import java.util.Scanner;

public class hash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("최소16자리이상 메세지를 입력하세요.");//8자리 메세지가 64bit임으로
		Scanner mes = new Scanner(System.in);//메세지 입력받기
		String message = mes.nextLine();//입력받은 메세지를 스트링변수 message에 넣기
		String binaryMessage="";//binaryMessage 스트링변수 초기화
		String bM="";
		
		char ch[] = message.toCharArray();//message변수 ch[]배열에 넣기
		int ml=message.length();//message의 길이
		int asc[] = new int[ml];//message길이만큼의 int형 배열 asc[] 초기화
		
		
		for (int i=0; i<ml; i++)
		{//message길이 동안 작업
			asc[i]=(int)ch[i];//입력받은 문자들을 int형으로 캐스팅 후 asc[]배열에 넣기
			bM=(String)Integer.toBinaryString(asc[i]);//asc배열의 각 문자의 이진수를 String형으로 캐스팅
			String zero="";//각 문자의 이진수를 8bit형식으로 나타내기 위한 변수
			if(bM.length()!=8)
			{
				//문자의 이진수 길이에서 8bit형식에 모자란 만큼 '0'을 앞에 더해주는 작업
				for(int j=0; j<8-bM.length(); j++)
				{
					zero+='0';
				}
				bM = zero + bM;
			}
			
			binaryMessage +=bM;
			//asc배열의 십진수들을 이진수로 변환 후 binaryMessage에 넣고 앞에 '0'을 추가하여 각 8비트로 표현되게 만듬
		}//입력받은 메세지들을 이진수로 표현하여 binaryMessage변수에 입력됨	
		
		int bml = binaryMessage.length();//binaryMessage의 길이
		int roomcount=0;//이진수로 표현된 메세지를 64bit로 나누어 각 방에 넣으려고 할때 방의 수
		if(bml%64!=0){
			roomcount=bml/64+1;//64bit로 안나눠질때 방의 수
		}
		else roomcount=bml/64;//64bit로 나눠질때 방의 수
				
		String r;//각방에 넣을 메세지
		String room[]=new String[roomcount];//String형의 방 room[]배열을 동적할당함
		int n=1;//64bit때마다 증가하는변수
		
		String padding="1";//64bit씩 나눠서 각 방에 넣고 남은 메세지들에 패딩하는 변수
		for(int cnt=1; cnt<=bml; cnt++)
		{//64bit씩 각방에 메세지를 넣는 작업
				if(cnt==64*n)
				{
					int sp=64*(n-1);
					r=binaryMessage.substring(sp,cnt);
					room[n-1]=r;
					n++;
				}
				else if(cnt==bml && cnt !=64*n)
				{//이진수 길이가 64bit의 배수가 아니고 cnt가 끝자리일때 작업
					
					int sp=64*(n-1);
					r=binaryMessage.substring(sp,cnt);
					
					int pdlength=64-(cnt-sp);//패딩길이
					
					if(pdlength > 1)
					{//패딩길이가 1보다 클때 패딩작업
						for(int i=1; i<pdlength; i++)
							padding +="0";
					}
					room[n-1]=r +padding;
					n++;
				}
		}
		String Xorstr=room[0];//XOR 작업할 변수 초기화
				
		for(int i=1; i<roomcount; i++){//방의 수만큼 XOR작업
			 Xorstr = StringXorCalculater(Xorstr, room[i]);
		}
		
		System.out.println("입력받은 메세지들의 해쉬처리된 값 : " + Xorstr);//최종적으로 XOR된 64bit의 이진수 메세지 값 출력
		//System.out.println(Xorstr.length());

	}
	//스트링 변수끼리 XOR연산 하는 함수
	public static String StringXorCalculater(String s1,String s2){
		  StringBuilder sb = new StringBuilder(); 
		  for(int i=0; i<s1.length() && i<s2.length();i++){ 
		   sb.append((char)(s1.charAt(i) ^ s2.charAt(i))); 
		  }
		  return sb.toString();
		 }

}
