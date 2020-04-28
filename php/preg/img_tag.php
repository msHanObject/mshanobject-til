<?php
$element = '<IMG ID="ABC" CLASS="ABC" SRC="ABC.PNG" TITLE="ABC" alt="ABC">';
$img_tag_pattern = '#<\s*[iI][mM][gG]\s+(?:\b[\w-]+\b\s*=\s*"\s*([^>"]*?)"\s*)*\/?\s*>#';
preg_match_all($img_tag_pattern, $element, $matches);
print_r($matches);
