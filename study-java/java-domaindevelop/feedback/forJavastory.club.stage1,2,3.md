Feedback for javastory.club.stage1, 2, 3
===

1. stage2/step4/ui/window/ClubWindow.java에서 find() 메소드와 findOne() 메소드의 차이?
>find는 Menu상에서 Previous하기전까지 while문으로 계속 찾는다.
반면 findOne은 내부적으로 Modify나 Remove에서 개체 하나만 찾기 위한 메소드로 찾든 못찾든 break로 빠져나간다.

2. old 패키지와 window 패키지의 차이?
> 여러 버전의 코딩?
3. 생성자가 private로 선언된 경우/의미
>출처: [Steven J.S Min's Blog](http://stevenjsmin.tistory.com/107)
private 으로 생성자를 선언하게 되면 인스턴스 생성이 불가능하게 된다.
뿐만 아니라 생성자를 호출할 수 없으므로 서브클래스를 만들 수 없게 된다.
그런데도 객체지향에서 생성자를 private으로 만드는 이유는 인스턴스 생성이 무의미하기 때문이다.
예를 들어 java.util package 의 Arrays나 Date, 또는 java.lang package의 Math 클래스 등의 경우는 굳이 인스턴스를 생성할 필요가 없다.
따라서 인스턴스를 사용하지 못하도록 생성자를 private으로 선언하는 것이다.
이때, 쉽게 생성자를 선언하지 않는 것으로 생각할 수 있다.
그러나 생성자를 선언하지 않는 경우에 자바 컴파일러는 기본적으로 default 생성자를 만들기 때문에 객체가 생성 가능한 클래스가 된다.
따라서 인스턴스를 만들지 못하게 막으려면 생성자를 private으로 만들어야 한다.
그런데 이렇게 되면 생성자를 private 으로 선언된 클래스에는 접근할 방법이 생기지 못하게 된다.
이를 위하여 클래스의 method와 field를 static으로 선언하는 것이다.
static으로 선언된 클래스는 인스턴스가 아니라 클래스명 자체로 접근할 수 있기 때문에 인스턴스를 생성하지 않아도 된다.
즉 이를 반대로 생각해보면, static method와 static field로 선언된 클래스는 생성자가 private 으로 선언되어 있다는 것을 알 수 있다.

4. stage2/step1/entity//TravelClub.java의 생성자 안에서 this()?
>TravelClub()생성자를 호출하는 것을 의미, 여기서는 매개변수 있는 생성자 안에서도 디폴트 생성자의 코드가 필요하여 this()로 호출하였음.

5. ClubMemberDetailConsole과 TravelClubDetailConsole의 생성자 Argument로 Scanner와 clubCoordinator를 받아서 쓰는 이유?
>부모 ui인 TravelClubConsole에서 생성하여 사용중인 Scanner를 그대로 물려받아서 쓰기 위함.(왜냐면 같은 사이클 안의 작업이기 때문)

6. stage2/step4/ui/window/MemberWindow.java에서 findAnotherClub() 메소드의 return 값으로 currentClub이 아닌 clubFound를 넘겨주는 이유?
>

8. stage2/step4/storage/MapStorage.java에서 속성으로 private static MapStorage clubStorage;를 선언하는 이유?
>이렇게 선언함으로써 저장소의 객체와 또다른 속성인 clubMap을 전역으로 쓸수 있으면서 재생성되지 않게 하기 위함. 객체를 하나만 선언하여 쓰는걸 singleton이라고 부름.

9. stage2/step4/storage/TravelClubStore.java의 retrieveAll(int offset, int pageSize) 메소드에서
```java
int index = 0;
while(clubIter.hasNext()) {
	if (index == offset) {
		break;
	}
	clubIter.next();
}
```
위 로직이 이해가 잘 안감.
>retrieveAll(int offset, int pageSize)메소드가 아마 단순히 TravelClub리스트 전부를 받는 retrieveAll()메소드와 달리 TravelClub리스트 일부를 offset이라는 인덱스 위치부터 pageSize라는 크기만큼 resultClub이라는 List객체에 담아서 리턴하려는 메소드인 것으로 보임. 그런 의미에서 index가 0으로 초기화 되어 있는 상태에서 offset값이 index와 같을 때 루프문을 빠져나오고 아니면 Iterator를 도는 소스는 뭔가 소스가 빠진 느낌이 듬. 그런 느낌이 든 이유는 메소드 안에서 pageSize라는 파라미터 값이 사용되지 않았기 때문.

10. try~catch, throw, thorws의 차이점
>throws와 throw 둘 다 Exception을 발생시킨다는 것에는 차이가 없다. 하지만 둘 사이의 차이점은 throws는 현재 메서드에서 자신을 호출한 상위 메서드로 Exception을 발생시키고, throw는 억지로 에러를 발생시키고자 할 때 사용한다(현재 메서드, 혹은 상위메서드로). 다시 말하자면 throws 키워드를 사용하는 메서드를 호출한 상위 메서드에서 이러한 에러 처리에 대한 책임을 맡게 되는 것이고 throw는 프로그래머가 사용자 정의 exception을 강제로 발생시켜 메서드 내에서 예외처리를 수행하는 것이다. 이 때 throw는 현재 메서드 내에서 예외를 강제로 발생시키거나 현재 메서드에게 exception 정보를 전달할 수 있다.
try~catch는 예외가 발생할 경우에만 Exception을 발생시키고 throw, throws는 강제로 Exception을 발생시킨다는 차이가 있다.

11. Map, HashMap, LinkedHashMap을 적절히 쓰는 법?
> Map은 key,value 를 쌍으로 갖는 집합체이자 interface이다, 이러한 Map을 implements 한 여러 class 중 하나가 HashMap이며 이와 비슷한 Hashtable이 있다. Hashtable의 모든 data 변경 메소드는 synchronized 되어 있는 반면 HashMap은 직접 synchronized를 선언해줘야 한다. Hashtable은 느리면서 기능이 부족하기에 HashMap을 사용 후 synchronized를 선언하거나 Collections.synchronizedMap(new HashMap(...)); 이런 형태로 쓰인다. LinkedHashMap은 HashMap과 달리 data를 집어 넣을 때 집어넣은 순서를 유지해주는 기능이 추가된 점이 다르다. 그리고 HashMap을 대신 CuncurrentHahsMap가 멀티쓰레드 환경에서 더 선호된다고 한다. 이유는 synchronizedMap 으로 감싸진 HashMap 이나 Hashtable 보다 더 빠르면서도 쓰레드간 동기화를 보장해 주는데 이는 동기화시 hashtable을 전체에 대해 lock을 걸지 않고 조각을 쪼개어 부분부분 lock 걸어쓰레드간 경쟁이 심할시에 이득을 보는 구현이기 때문이다.

12. serialVersionUID란?
> serialVersionUID는 직렬화가 가능한 클래스에 부여하는 고유 번호를 의미한다. serialVersionUID가 부여되는 시점은 각 클래스의 객체가 직렬화되는 시점에 부여되는데, 이때 부여된 고유번호를 역직렬화를 할 때 비교하여 같은 클래스에서 로드된 객체가 맞는지 확인하기 위해 사용된다. 이때 객체 발신자와 수신자의 serialVersionUID가 같지 않으면 InvalidClassException이 발생한다.

13. super()란?
> 부모 클래스의 생성자를 뜻한다. 따라서 자식 클래스에서 부모클래스의 생성자를 호출해서 사용하려고 할 때 쓰인다.
