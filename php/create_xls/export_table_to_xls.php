<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
var tableToExcel = (function () {
    var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
    , base64 = function (s) {
        return window.btoa(unescape(encodeURIComponent(s)))
    }
    , format = function (s, c) {
        return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; })
    }
    return function (table, name) {
        if (!table.nodeType) table = document.getElementById(table)
        var ctx = { worksheet: name || 'Worksheet', table: table.innerHTML }
        saveAs(uri + base64(format(template, ctx)));
    }
})()
function saveAs(uri) {
    var link = document.createElement('a');
    if (typeof link.download === 'string') {
        link.href = uri;
        // Set file name as Requested file name + date format
        link.setAttribute('download', '<?= $_REQUEST['file_name'] . '_' . date('Ymd') ?>.xls');

        // Firefox requires the link to be in the body
        document.body.appendChild(link);

        // Simulate click
        link.click();

        // Remove the link when done
        document.body.removeChild(link);
    } else {
        window.open(uri);
    }
}
</script>
</head>

<body>
<input type="button" onclick="tableToExcel('target_table', 'Sheet1')" value="Export to Excel">
<table id="target_table" rules="all" frame="border" border="1">
<?php
// thead > th
$thead_th = array();
if (isset($_REQUEST['thead_th'])) {
    $thead_th = unserialize($_REQUEST['thead_th']);
}
// tbody > td
$tbody_td = array();
if (isset($_REQUEST['tbody_td'])) {
    $tbody_td = unserialize($_REQUEST['tbody_td']);
}
?>
    <caption><?= $_REQUEST['file_name'] ?></caption>
    <thead>
        <tr>
<?php
foreach ($thead_th as $th) { ?>
            <th><?= $th ?></th>
<?php
} ?>
        </tr>
    </thead>
    <tbody>
<?php
foreach ($tbody_td as $tr) { ?>
        <tr>
<?php
    foreach ($tr as $td) { ?>
            <td style="mso-number-format:'\@'"><?= $td ?></td>
<?php
    } ?>
        </tr>
<?php
} ?>
    </tbody>
</table>
<script>
// 버튼 클릭시 다운 대신에 바로 다운받기 적용
$(document).ready(function() {
    tableToExcel('target_table', 'sheet1');
});
</script>
</body>

</html>
