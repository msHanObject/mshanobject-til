document.addEventListener("DOMContentLoaded", function () {

    var pwCheckBtn = document.querySelectorAll('button.pw_check');
    addEventFunc(pwCheckBtn, 'click', showPassword);

    var inputField = document.querySelectorAll('input[class=field]');
    addEventFunc(inputField, 'keyup', checkInputFieldValid);
    addEventFunc(inputField, 'focusout', checkInputFieldValid);

    var checkAllInput = document.getElementById('ck_all');
    var requiredCheckboxes = document.querySelectorAll('input[type=checkbox][required]');
    
    var numberOfCheckedRequiredCheckboxes = countCheckedboxes(requiredCheckboxes);
    addEventFunc(requiredCheckboxes, 'click', function() {
        if (event.target.checked === false) {
            checkAllInput.checked = false;
            numberOfCheckedRequiredCheckboxes--;
        } else {
            numberOfCheckedRequiredCheckboxes++;
        }
        if (numberOfCheckedRequiredCheckboxes == requiredCheckboxes.length) {
            checkAllInput.checked = true;
        }
    });

    addEventFunc(checkAllInput, 'change', function () {
        if (event.target.checked != true) {
            checkAll(requiredCheckboxes, false);
            numberOfCheckedRequiredCheckboxes = 0;
        } else {
            checkAll(requiredCheckboxes, true);
            numberOfCheckedRequiredCheckboxes = requiredCheckboxes.length;
        }
    });

});

function countCheckedboxes(checkboxes) {
    var counts = 0;
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked == true) {
            counts++;
        }
    });
    return counts;
}

function addEventFunc(element, eventName, myFunc) {
    var elementLength = element.length;
    if (element == null || element == undefined || elementLength == 0) {
        return;
    }
    
    if (elementLength == undefined) {
        element.addEventListener(eventName, myFunc);
        return;
    }

    for (var i=0; i < elementLength; i++) {
        element[i].addEventListener(eventName, myFunc);
    }
}

function checkAll(checkboxes, truefalse) {
    checkboxes.forEach(function(checkbox){
        if (checkbox.checked != truefalse) {
            checkbox.checked = truefalse;
        }
    });
}

function showPassword() {
    changeDbt(this, 'show_password', 'hide_password');
    var targetElement = document.getElementById('password');
    changeInputType(targetElement, 'password', 'text');
}

function changeInputType(element, origin, changeTo){
    if (element == null || element == undefined) {
        return;
    }

    if (element.type == origin) {
        element.type = changeTo;
    } else{
        element.type = origin;
    }
}

function changeDbt(element, origin, changeTo) {
    if (element == null || element == undefined) {
        return;
    }

    var dbt = element.getAttribute("data-button-type");

    if (dbt == null || dbt == undefined) {
        return;
    }

    if (dbt == origin) {
        dbt = changeTo;
        changeInnerHTML(element, '<span>비밀번호</span> 숨기기');
    } else {
        dbt = origin;
        changeInnerHTML(element, '<span>비밀번호</span> 표시');
    }

    element.setAttribute("data-button-type", dbt);
}

function changeInnerHTML(element, msg) {
    element.innerHTML = msg;
}

function checkInputFieldValid() {
    var targetValue = this.value;

    if (targetValue.length < 0) {
        return;
    }

    if (targetValue.length == 0) {
        replaceClassName(this, 'warning', '');
        replaceClassName(this, 'complete', '');
        return;
    }
    
    var msg = '';
    switch (this.name) {

        case 'id':
            if (!RegExp(/^[A-Za-z0-9]{6,20}$/).test(targetValue)) {
                msg = '아이디를 6~20자의 영문, 숫자로 입력해 주세요.';
                classNameHandler(this, 'complete', 'warning');
            } else {
                msg = '';
                classNameHandler(this, 'warning', 'complete');
            }
            break;
        case 'password':
            if (!RegExp(/^\S{8,20}$/).test(targetValue)) {
                msg = '비밀번호를 8~20자의 영문, 숫자, 특수문자 등으로 입력해 주세요. 공백은 사용할 수 없습니다.';
                classNameHandler(this, 'complete', 'warning');
            } else if (this.getAttribute('id') == 'confirm_pw' && this.value != document.getElementById('create_pw').value) {
                msg = '재입력 비밀번호가 비밀번호와 일치하지 않습니다.';
                classNameHandler(this, 'complete', 'warning');
            } else {
                msg = '';
                classNameHandler(this, 'warning', 'complete');
            }
            break;
    }
}

function classNameHandler(element, removedName, addedName) {
    if (element == null || element == undefined) {
        return;
    }

    var classNameArray = element.className.split(" ");
    if (classNameArray.indexOf(removedName) == -1 && addedName != '') {
        addClassName(element, addedName);
    } else {
        replaceClassName(element, removedName, addedName);
    }
}

function replaceClassName(element, origin, changeTo) {
    if (element == null || element == undefined) {
        return;
    }
    var originPattern = "(?:^|\\s)" + origin + "(?!\\S)";
    var strChangeTo = changeTo;
    var classNameArray = element.className.split(" ");
    if (classNameArray.length != 1) {
        strChangeTo = " " + changeTo;
    }
    var originRex = new RegExp(originPattern, 'g');
    element.className = element.className.replace(originRex, strChangeTo.toString());
}

function addClassName(element, addedName) {
    var classNameArray = element.className.split(" ");
    if (classNameArray.indexOf(addedName) != -1) {
        return;
    } else {
        if (classNameArray.length == 1 && classNameArray[0] == '') {
            element.className += addedName;
        } else {
            element.className += " " + addedName;
        }
    }
}
