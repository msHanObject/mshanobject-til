<?php
//폼이 전송되면 인사하기
if ($_POST['user']) {
	print "Hello, ";
	// 'user'라는 폼 매개변수로 제출된 값 출력
	print $_POST['user']."!";
} else {	
	print <<<_HTML_
	<form method="post" action="$_SERVER[PHP_SELF]">
	<!--$_SERVER[PHP_SELF]는 현재 페이지의 URL을 담고 있는 특별한 변수이다.
	예를 들어, 현재 페이지 URL이 http://www.example.com/user/enter.php라면 $_SERVER[PHP_SELF]는 /users/enter.php를 담는다.-->
	이름: <input type="text" name="user"/>
	<br/>
	<button typee="submit">인사</button>
	</form>
	_HTML_;
}
?>
