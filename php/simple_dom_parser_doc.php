<?php
$title = 'Example for PHP Simple HTML DOM Parser';
include_once '/var/www/html/header.html';
include_once '/var/www/simple_html_dom_1_9_1/simple_html_dom.php';

echo "<h1>$title</h1>";
echo '<h2>DOM object 생성</h2>';
echo '
<h3># URL로 부터 DOM object 생성하기</h3>
<pre>
    <code>$html = file_get_html(\'https://www.naver.com/\');</code>
</pre>';

echo '
<h3># HTML파일로 부터 DOM object 생성하기</h3>
<pre>
    <code>$html = file_get_html(\'test.html\');</code>
</pre>';

$hello_world = '
<html>
    <body>
        <h1>Hello!</h1>
        <div id="foo">foo<span id="bar" class="buz">bar</h3></div>
        <a href="https://www.google.com/" title="구글">Google</a>
        <a href="https://www.naver.com/" title="네이버">Naver</a>
        <input type="radio" id="r1" name="r2" value="first" checked>
        <input type="radio" id="r2" name="r2" value="second">
        <img src="https://www.php.net/images/logos/php-logo.svg" width="10%" height="10%" title="PHP" alt="php">
        <img src="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png" alt="google">
        <ul class="b">
            <li><a href="#">#1</a></li>
            <!-- <li><span>#2</h3></li> -->
            <li><a href="#">#2</a><li>
        </ul>
        <div>div_1
            <div>div_2
                <div>div_3</div>
            </div>
        </div>
        <table class="hello">
            <tr>
                <th>language</th>
                <th>greeting</th>
            </tr>
            <tr>
                <td class="b">english</td>
                <td>hello</td>
            </tr>
            <tr>
                <td class="b2" align="center">한국어</td>
                <td>안녕하세요</td>
            </tr>
        </table>
        <input type="email" id="email" name="email" value="example@example.com">
    </body>
</html>
';
$html = str_get_html($hello_world);
echo '
<h3># string으로 부터 DOM object 생성하기</h3>
<pre>
    <samp>$hello_word = \'' . htmlentities($hello_world) . '\';</samp>
    <code>$html = str_get_html($hello_world);</code>
</pre>';

echo '
<h3># 모든 anchor들을 찾아서, 배열 형태로 리턴받기</h3>
<pre>
    <code>$anchors = $html->find(\'a\');</code>
</pre>';

echo '
<h3># N번째 anchor를 찾아서 리턴받기(만약 찾지 못했으면 null 리턴)</h3>
<h4>첫 번째 anchor 찾기</h4>
<pre>
    <code>$first_anchor = $html->find(\'a\', 0);</code><samp> // ' . $html->find('a', 0) . '</samp>
</pre>
<h4>두 번째 anchor 찾기</h4>
<pre>
    <code>$second_anchor = $html->find(\'a\', 1);</code><samp> // ' . $html->find('a', 1). '</samp>
</pre>
<h4>마지막 anchor 찾기</h4>
<pre>
    <code>$last_anchor = $html->find(\'a\', -1);</code><samp> // ' . $html->find('a', -1) . '</samp> 
</pre>';

echo '
<h3># id 속성이 있는 모든 div를 찾아서 리턴받기</h3>
<pre>
    <code>$divs_has_id = $html->find(\'div[id]\');</code><samp> // ' . $html->find('div[id]') . '</samp>
</pre>';

echo '
<h3># id 속성의 값이 bar인 첫 번째 span을 찾아서 리턴받기</h3>
<pre>
    <code>$first_span_has_id_bar = $html->find(\'span[id=bar]\', 0);</code><samp> // ' . $html->find('span[id=bar]', 0) . '</samp>
</pre>';

echo '
<h3># 모든 input을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$inputs = $html->find(\'input\');</code><samp><ul>';
foreach ($html->find('input') as $e) {
    echo '<li>' . htmlentities($e->outertext) . '</li>';
}
echo '</ul></samp></pre>';

echo '
<h3># 모든 img를 찾아서 src, alt 속성 출력하기</h3>
<pre><code>
foreach ($html->find(\'img\') as $e) {
    echo \'src=\' . $e->src . \' alt=\' . $e->alt . \'\n\';
}
</code><samp><ul>';
foreach ($html->find('img') as $e) {
    echo '<li>src=' . $e->src . ' alt=' . $e->alt . '</li>';
}
echo '</ul></samp></pre>';

echo '
<h3># id 속성의 값이 r1인 첫 번째 요소의 value 값 리턴받기</h3>
<pre>
    <code>$value = $html->find(\'#r1\', 0)->value;</code><samp> // ' . $html->find('#r1', 0)->value . '</samp>
</pre>';

echo '
<h3># class 속성의 값이 buz인 첫 번째 요소의 innertext값 리턴받기</h3>
<pre>
    <code>$value = $html->find(\'.buz\', 0)->innertext;</code>
</pre>';

echo '
<h3># id속성을 가지고 있는 모든 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es_has_id_attr = $html->find(\'*[id]\');</code><samp> // ' . $html->find('*[id]') . '</samp>
</pre>';

echo '
<h3># 모든 anchors와 div 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$all_anchor_and_div = $html->find(\'a, div\');</code>
</pre>';

echo '
<h3># title 속성을 갖고 있는 모든 anchor와 image 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$all_anchor_and_img_has_title = $html->find(\'a[title], img[title]\');</code>
</pre>';

echo '
<h3># ul에 있는 모든 li를 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$lis_in_ul = $html->find(\'ul li\');</code>
</pre>';

echo '
<h3># 내재된 div들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es = $html->find(\'div div div\');</code>
</pre>';

echo '
<h3># class 속성 값이 hello인 table 요소 안의 td 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es = $html->find(\'table.hello td\');</code>
</pre>';

echo '
<h3># table 요소 안에 td 요소 중 align 속성의 값이 center인 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es = $html->find(]\'table td[align=center]\');</code>
</pre>';

echo '
<h3># type 속성의 값이 submit이 아닌 마지막 input 요소를 찾아서 리턴받기</h3>
<pre>
    <code>$html->find(\'input[type!=submit]\', -1);</code>
</pre>';

echo '
<h3># class 속성이 b로 시작하는 모든 요소를 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es = $html->find(\'[class^=b]\');</code>
</pre>';

echo '
<h3># name 속성의 값이 2로 끝나는 모든 요소들을 찾아서 배열로 리턴받기</h3>
<pre>
    <code>$es = $html->find(\'[name$=2]\');</code>
</pre>';

echo '
<h3>have the specified attribute and it contains a certain value.</h3>
<pre>
	<code>$es = $html->find(\'[class*=b]\');</code>
</pre>';

# Find all text blocks
//$es = $html->find('text');
//foreach ($es as $e) {
//    echo $e->outertext;
//}

# Find all comment blocks
//$es = $html->find('comment');
//foreach ($es as $e) {
//    echo $e;
//}

# Find first anchor and get a attribute
//echo $html->find('a', 0)->href;

# Find first anchor and set a attribute
//echo $html->find('a', 0)->href = 'my link';

# Remove a attribute, set it's value as null
//$e1 = $html->find('input', 0)->id = null;
//$e2 = $html->find('input', -1)->id = 'new';
//var_dump($e1);
//var_dump($e2);

# Determine whether a attribute exist?
//$e = $html->find('a', -1);
//if(isset($e->href)) {
//    echo $e->href;
//}

# Extract contents from HTML
//echo $html->plaintext;

# Wrap a element


# Traverse the DOM tree


include_once '/var/www/html/footer.html';
