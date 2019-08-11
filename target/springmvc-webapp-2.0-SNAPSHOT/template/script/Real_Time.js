function Time() {
    var today = new Date();
    var y = today.getFullYear();
    var m = today.getMonth() + 1;
    var d = today.getDate();
    var h = today.getHours();
    var min = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    d = checkTime(d);
    h = checkTime(h);
    min = checkTime(min);
    s = checkTime(s);
    document.getElementById('time').value = y + "-" + m + "-" + d + " " + h + ":" + min + ":" + s;
    var t = setTimeout(Time, 1000);
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

