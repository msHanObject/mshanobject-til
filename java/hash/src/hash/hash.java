package hash;
import java.util.Scanner;

public class hash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�ּ�16�ڸ��̻� �޼����� �Է��ϼ���.");//8�ڸ� �޼����� 64bit������
		Scanner mes = new Scanner(System.in);//�޼��� �Է¹ޱ�
		String message = mes.nextLine();//�Է¹��� �޼����� ��Ʈ������ message�� �ֱ�
		String binaryMessage="";//binaryMessage ��Ʈ������ �ʱ�ȭ
		String bM="";
		
		char ch[] = message.toCharArray();//message���� ch[]�迭�� �ֱ�
		int ml=message.length();//message�� ����
		int asc[] = new int[ml];//message���̸�ŭ�� int�� �迭 asc[] �ʱ�ȭ
		
		
		for (int i=0; i<ml; i++)
		{//message���� ���� �۾�
			asc[i]=(int)ch[i];//�Է¹��� ���ڵ��� int������ ĳ���� �� asc[]�迭�� �ֱ�
			bM=(String)Integer.toBinaryString(asc[i]);//asc�迭�� �� ������ �������� String������ ĳ����
			String zero="";//�� ������ �������� 8bit�������� ��Ÿ���� ���� ����
			if(bM.length()!=8)
			{
				//������ ������ ���̿��� 8bit���Ŀ� ���ڶ� ��ŭ '0'�� �տ� �����ִ� �۾�
				for(int j=0; j<8-bM.length(); j++)
				{
					zero+='0';
				}
				bM = zero + bM;
			}
			
			binaryMessage +=bM;
			//asc�迭�� ���������� �������� ��ȯ �� binaryMessage�� �ְ� �տ� '0'�� �߰��Ͽ� �� 8��Ʈ�� ǥ���ǰ� ����
		}//�Է¹��� �޼������� �������� ǥ���Ͽ� binaryMessage������ �Էµ�	
		
		int bml = binaryMessage.length();//binaryMessage�� ����
		int roomcount=0;//�������� ǥ���� �޼����� 64bit�� ������ �� �濡 �������� �Ҷ� ���� ��
		if(bml%64!=0){
			roomcount=bml/64+1;//64bit�� �ȳ������� ���� ��
		}
		else roomcount=bml/64;//64bit�� �������� ���� ��
				
		String r;//���濡 ���� �޼���
		String room[]=new String[roomcount];//String���� �� room[]�迭�� �����Ҵ���
		int n=1;//64bit������ �����ϴº���
		
		String padding="1";//64bit�� ������ �� �濡 �ְ� ���� �޼����鿡 �е��ϴ� ����
		for(int cnt=1; cnt<=bml; cnt++)
		{//64bit�� ���濡 �޼����� �ִ� �۾�
				if(cnt==64*n)
				{
					int sp=64*(n-1);
					r=binaryMessage.substring(sp,cnt);
					room[n-1]=r;
					n++;
				}
				else if(cnt==bml && cnt !=64*n)
				{//������ ���̰� 64bit�� ����� �ƴϰ� cnt�� ���ڸ��϶� �۾�
					
					int sp=64*(n-1);
					r=binaryMessage.substring(sp,cnt);
					
					int pdlength=64-(cnt-sp);//�е�����
					
					if(pdlength > 1)
					{//�е����̰� 1���� Ŭ�� �е��۾�
						for(int i=1; i<pdlength; i++)
							padding +="0";
					}
					room[n-1]=r +padding;
					n++;
				}
		}
		String Xorstr=room[0];//XOR �۾��� ���� �ʱ�ȭ
				
		for(int i=1; i<roomcount; i++){//���� ����ŭ XOR�۾�
			 Xorstr = StringXorCalculater(Xorstr, room[i]);
		}
		
		System.out.println("�Է¹��� �޼������� �ؽ�ó���� �� : " + Xorstr);//���������� XOR�� 64bit�� ������ �޼��� �� ���
		//System.out.println(Xorstr.length());

	}
	//��Ʈ�� �������� XOR���� �ϴ� �Լ�
	public static String StringXorCalculater(String s1,String s2){
		  StringBuilder sb = new StringBuilder(); 
		  for(int i=0; i<s1.length() && i<s2.length();i++){ 
		   sb.append((char)(s1.charAt(i) ^ s2.charAt(i))); 
		  }
		  return sb.toString();
		 }

}
