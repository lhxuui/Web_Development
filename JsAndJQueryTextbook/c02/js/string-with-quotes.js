var title;
var message;
/* string can live in single or double quotes
if want to use single quotes in a string,surround the string with double quotes
and vice versa*/
title = "Molly's Special Offers";
//backslash(\) to escape the quotation characters
message = '<a href=\"sale.html\">25% off!</a>';

var elTitle = document.getElementById('title');
elTitle.innerHTML = title;
var elNote = document.getElementById('note');
elNote.innerHTML = message