<?php
$request_header_contents = "'https://technote.kr/45' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:74.0) Gecko/20100101 Firefox/74.0' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' -H 'Accept-Language: en-US,en;q=0.5' --compressed -H 'Referer: https://www.google.com/' -H 'DNT: 1' -H 'Connection: keep-alive' -H 'Cookie: wcs_bt=14e789da4fa77d8:1583738488; REACTION_GUEST=01682080068b399e4e27a6f5ef4f9f1d8537a168' -H 'Upgrade-Insecure-Requests: 1' -H 'Pragma: no-cache' -H 'Cache-Control: no-cache'";

$request_headers = explode(' -H ', $request_header_contents);
$rh_counts = count($request_headers);

for ($i=0; $i < $rh_counts; $i++) {
	if ($i != $rh_counts -1) {
		$subjects .= $request_headers[$i] . ' -H ';
	} else {
		$subjects .= $request_headers[$i];
	}
}

$command =  'curl ' . $subjects;
exec($command, $output);
echo $output;
