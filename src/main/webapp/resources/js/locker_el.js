var o;
var unlock ="http://"+window.location.host+"/MyJaxRs/resources/images/unlocked.png";
var lock = "http://"+window.location.host+"/MyJaxRs/resources/images/locked.png";
var locker;
var elems1 = document.getElementsByClassName('inputText');
var elems2 = document.getElementsByClassName('inputTextAC');
window.onload = function (e) {
    o=document.createElement('img'); 
    o.setAttribute('src', lock);
    o.setAttribute('onclick', "javascript:locking()");
    o.setAttribute('id', "locker");
    document.getElementById('lock_el').appendChild(o);
    locker = document.getElementById('locker');
    lock_on();
};
function lock_on() {
    for (var i = 0; i < elems1.length; i++) {
        elems1[i].setAttribute('readonly', 'true');
        elems1[i].setAttribute('style','background: white');
    }
    for (var i = 0; i < elems2.length; i++) {
        var ac_id = elems2[i].getAttribute('id');
        var ac = elems2[i].getElementsByTagName('input');
        for (var j = 0; j < ac.length; j++) {
            if (ac[i].getAttribute('id') === (ac_id + '_input')) {
                ac[i].setAttribute('readonly', 'true');
                ac[i].setAttribute('style','background: white');
            }
        }
    }
};
function lock_off() {
    for (var i = 0; i < elems1.length; i++) {
        elems1[i].removeAttribute('readonly');
        elems1[i].setAttribute('style','background: highlight');
    }
    for (var i = 0; i < elems2.length; i++) {
        var ac_id = elems2[i].getAttribute('id');
        var ac = elems2[i].getElementsByTagName('input');
        for (var j = 0; j < ac.length; j++) {
            if (ac[i].getAttribute('id') === (ac_id + '_input')) {
                ac[i].removeAttribute('readonly');
                ac[i].setAttribute('style','background: highlight');
            }
        }
    }
};
function locking() {
    if (locker.getAttribute('src') === unlock) {
        locker.setAttribute('src', lock);
        document.getElementById('hiddenCB').click();
        lock_on();
    } else {
        locker.setAttribute('src', unlock);
        lock_off();
    }
};