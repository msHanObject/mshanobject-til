## PHP tags

When PHP parses a file, it looks for opening and closing tags, which are <?php and ?> which tell PHP to start and stop  interpreting the code between them. Parsing in this manner allows PHP to be embedded in all sorts of different documents, as everything outside of a pair of opening and closing tags is ignored by the PHP parser.

PHP includes a short echo tag <?= which is a short-hand to the more verbose <?php echo.

PHP also allows for short open tag <? (which is discouraged since it is only available if enabled using thee short\_open\_tag `php.ini` configuration file direective, or if PHP was configured with the **--enable-short-tags** option).

If a file contains only PHP code, it is preferable to omit the PHP closing tag at the end of the file. This prevents accidental whitespace or new lines being added the PHP closing tag, which may cause unwanted effects because PHP will start output buffering when there is no intention from the programmer to send any output at that point in the script.

```php
<?php
echo "Hello worl";

// ... more code

echo "Last statement";

// the script ends here with no PHP closing tag
```

## Escaping from HTML

Everything outside of a pair of opening and closing tags is ignored by the PHP parser which allows PHP files to have mixed content. This allows PHP to be embeded in HTML documents, for example to create templates.

```
<p>THis is going to be ignored by PHP and displayed by the browser.</p>
<?php echo 'While this is going to be parsed.'; ?>
<p> This will also be ignored by PHP and displayed by the browser.</p>
```

This works as expected, because when the PHP interpreter hits the ?> closing tags, it simply starts outputting whatever it finds (except for an immediately following newline -see [instruction separation](https://www.php.net/manual/en/language.basic-syntax.instruction-separation.php "InstructionSeparation") until it hits another opening tag unless in the middle of a conditional statement in which case the interpreter will determine the outcome of the conditional before making a deecision of what to skip over. See the next example.
Using structures with conditions

### **Example #1 Advanced escaping using conditions**

```
<?php if ($expression == true) :?>
This is will show if the expression is ture.
<?php else: ?>
Otherwise this will show.
<?php endif; ?>
```

In this example PHP will skip the blocks where the condition is not met, even though they are outside of the PHP open/close tags; PHP skips them according to the condition since the PHP interpreter will jump over blocks contained within a condition that is not met.
For outputting large blocks of text, dropping out of PHP parsing mode is generally more efficient than sending all of the text though [echo](https://www.php.net/manual/en/function.echo.php) or [print](https://www.php.net/manual/en/function.print.php).

In PHP 5, there are up to five different pairs of opening and closing tags available in PHP, depending on how PHP is configured. Two of these, <?php ?> and <script language="php"></script>, are always available. There is also the short echo tag <?= ?>, which is always available in PHP 5.4.0 and later.

The other two are short tags and ASP style tags. As such, while some people find short tags and  ASP style convenient, they aree less portable, and generally nor recommended.

> **Note:**
Also note that if you are embedding PHP within XML or XHTML you will need to use the <?php ?> tags to remain compliant with standards.

PHP 7 removes support for ASP tags and <script language="php"></script> tags. As such, we recommend only using <?php ?> and <?= ?> when writing PHP code to maximise compatibility.

### **Example #2 PHP Opening  and Closing Tags

```
1. <?php echo 'if you want to server PHP code in XHTML or XML documents, use these tags'; ?>

2. You can use the short echo tag to <?= 'print this string' ?>.
	It's always enabled in PHP 5.4.0 and later, and is equivalent to <?php echo 'print this string' ?>.

3. <? echo 'this code is within short tags, but will only work'. 'if short_open_tag is enabled'; ?>

4. <script language="php">
	echo 'some editors (like FrontPage) don\'t like processing instructions within these tags';
	</script>
	This syntax is removed in PHP 7.0.0.

5. <% echo 'You may optionally use ASP-style tags'; %>
	Code within these tags <%= $variable; %> is a shortcut for this code <% echo $variable; %>
	Both of these syntaxes are removed in PHP 7.0.0.
```
Short tags(example three) are only available when they are enabled via the short_open_tag `php.ini` configuration file directive, or if PHP was configured with **--enable-short-tags** option.

ASP style tags (example five) are only available when they are enabled via asp_tags `php.ini` configuration file directive, and have been removed in PHP 7.0.0.

**Note:**
Using short tags should be avoided when developing applications or libraries that are meant for redistribution, or deployment on PHP servers which are not under your control, because short tags may not be supported on the target server. For portable, redistributable code, be sure not to use short tags.

**Note:**
In PHP 5.2. and earlier, the parser does not allow the <?php opening tag to be the only thing in a file. This is allowed as of PHP 5.3 provided there are one or more whitespace characters after the opening tag.

**Note:**
Starting with PHP 5.4, short echo tag <?= is always recognized and valid, regardless of the short_open_tag setting.

## Instruction separation

AS in C or Perl, PHP requires instructions to be terminated with a semicolon at the end of each statement. The closing tag of a block of PHP code automatically implies a semicolon; you do not need to have 
a semicolon terminating the last line of a PHP block. The closing tag for the block will include the immediately trailing newline if one is present.

**Note:**
The closing tag of a PHP block at the end of a file is optional, and in some cases omitting it is helpful when using [include](https://www.php.net/manual/en/function.include.php) or [require](https://www.php.net/manual/en/function.require.php), so unwanted whitespace will not occur at the end of files, and you will still be able to add headers to the response later. It is also handy if you use output buffering and would not like to see added unwanted whitespace at the end of the parts generated by the included files.

## Comments

PHP supports 'C', 'C++; and Unix shell-style (Perl style) comments. For example:

```php
<?php
	echo 'This is a test'; //This is a one-line c++ style comment
	/* This is a multi line comment
		yet another line of comment */
	echo 'This is yet another test';
	echo 'One Final Test'; #This is a one-line shell-style comment
?>
```

The "one-line" comment sytles only comment to the end of the line or the current block of PHP code, whichever comes first. This means that HTML code after // ...?> or # ...?> WILL be printed: ?> breaks out of PHP mode and returns to HTML mode, and // or # cannot influence that. If the [asp_tags](https://www.php.net/manual/en/ini.core.php#ini.asp-tags) configuration directive is enabled, it behaves the same with // %> and # %>. However, the </script> tag doesn't braek out of PHP mode in a one-line comment.

```
<h1> This is an <?php # echo 'simple';?> exmaple</h1>
<p>The header above will say 'This is an example'.</p>
```

'C' sytle comments end at the first \*/ encountered. Make sure you don't nest 'C' style comments. It is easy to make this mistake if you are trying to comment out a large block of code.
