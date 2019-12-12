document.addEventListener("DOMContentLoaded", function () {

    var pwCheckBtn = document.querySelectorAll('button.pw_check');
    addEventFunc(pwCheckBtn, 'click', showPassword);

    var inputFields = document.querySelectorAll('input[class=field]');
    addEventFunc(inputFields, 'input', inputHandler);
    addEventFunc(inputFields, 'focusout', inputHandler);

    var checkAllInput = document.getElementById('ck_all');
    var requiredCheckboxes = document.querySelectorAll('input[type=checkbox][required]');
    
    var selectedRequiredCheckboxes = countCheckedboxes(requiredCheckboxes);
    addEventFunc(requiredCheckboxes, 'click', function() {
        if (requiredCheckboxes == null || requiredCheckboxes == undefined) {
            return;
        }
        if (event.target.checked === false) {
            checkAllInput.checked = false;
            selectedRequiredCheckboxes--;
        } else {
            selectedRequiredCheckboxes++;
        }
        if (selectedRequiredCheckboxes == requiredCheckboxes.length) {
            checkAllInput.checked = true;
        }
    });

    addEventFunc(checkAllInput, 'change', function () {
        if (checkAllInput == null || checkAllInput == undefined) {
            return;
        }
        if (event.target.checked != true) {
            checkAll(requiredCheckboxes, false);
            selectedRequiredCheckboxes = 0;
        } else {
            checkAll(requiredCheckboxes, true);
            selectedRequiredCheckboxes = requiredCheckboxes.length;
        }
    });
    var sendAuthNumBtn = document.querySelector('input[type=submit][data-button-type=send_auth_num]');
    addEventFunc(sendAuthNumBtn, 'click', authHandler);
});

function authHandler(){
    //인증번호가 잘 보내졌는지 확인하는 함수 필요
    var authInputContainer = document.getElementById('auth_num_inputfield');
    if (this.getAttribute("data-button-type") == 'send_auth_num') {
        this.value = '인증번호 확인';
        this.setAttribute("data-button-type", 'confirm_auth_num');
        //show auth_num_inputfield
        authInputContainer.style.display = 'block';
        
        startAuth();
    } 
    //인증실패시
    /*if ()
        restartAuth(this);
    }*/
    submitDisabledHandler();
}

function restartAuth(element) {
    //hide auth_num_inputfield
    authInputContainer.style.display = 'none';

    element.value = '인증번호 보내기';
    element.setAttribute("data-button-type", 'send_auth_num');

    resetAuth();
}

function resetAuthTime() {
    initTimer(300);
}

function setTime(min, sec) {
    var authMinute = document.getElementById('auth_minute');
    var separator = ' : ';
    authMinute.innerText = min + separator;

    var authSecond = document.getElementById('auth_second');
    authSecond.innerText = sec;
}

function initTimer(seconds){
    var min = parseInt(seconds / 60);
    var sec = parseInt(seconds % 60);
    if (sec < 10) {
        sec = '0' + sec;
    }
    setTime(min, sec);
}

function startAuth() {
    var limitedSeconds = 300;

    timer = setInterval(function() {
        if (limitedSeconds == 0) {
            clearInterval(timer);
            alert('휴대 전화 인증 시간이 만료되었습니다.');
        }
        initTimer(limitedSeconds);
        limitedSeconds--;
    }, 1000);
}

function countCheckedboxes(checkboxes) {
    var counts = 0;
    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked == true) {
            counts++;
        }
    });
    return counts;
}

function addEventFunc(elements, eventName, myFunc) {
    if (elements == null || elements == undefined || elements.length == 0) {
        return;
    }
    
    if (elements.length == undefined) {
        elements.addEventListener(eventName, myFunc);
        return;
    }

    elements.forEach(function(element) {
        element.addEventListener(eventName, myFunc);
    });
}

function checkAll(checkboxes, truefalse) {
    checkboxes.forEach(function(checkbox){
        if (checkbox.checked != truefalse) {
            checkbox.checked = truefalse;
        }
    });
}

function showPassword() {
    if (this == null || this == undefined) {
        return;
    }
    changeDbt(this, 'show_password', 'hide_password');
    // var targetElement = document.getElementById('create_pw');
    var targetElement = this.parentElement.parentElement.getElementsByTagName('input')[0];
    changeInputType(targetElement, 'password', 'text');
}

function changeInputType(element, oldType, newType){
    if (element == null || element == undefined) {
        return;
    }

    if (element.type == oldType) {
        element.type = newType;
    } else{
        element.type = oldType;
    }
}

function changeDbt(element, oldDbt, newDbt) {
    if (element == null || element == undefined) {
        return;
    }

    var dbt = element.getAttribute("data-button-type");

    if (dbt == null || dbt == undefined) {
        return;
    }

    if (dbt == oldDbt) {
        dbt = newDbt;
        changeInnerHTML(element, '<span>비밀번호</span> 숨기기');
    } else {
        dbt = oldDbt;
        changeInnerHTML(element, '<span>비밀번호</span> 표시');
    }

    element.setAttribute("data-button-type", dbt);
}

function changeInnerHTML(element, msg) {
    element.innerHTML = msg;
}

function inputHandler() {
    if (this == null || this == undefined) {
        return;
    }
    var inputValue = this.value;
    if (inputValue.length == 0) {
        replaceClassName(this, 'warning', '');
        replaceClassName(this, 'complete', '');
        removeTipElement(this);
        replaceClassName(this.parentElement, 'tip_space', '');
        return;
    }
    checkInputValid(this);
    submitDisabledHandler();
}

var inputRegExp = {
    id: { regexp: /^[A-Za-z0-9]{6,20}$/, message: '아이디를 6~20자의 영문, 숫자로 입력해 주세요.' },
    password: { regexp: /^\S{8,20}$/, message: '비밀번호를 8~20자의 영문, 숫자, 특수문자 등으로 입력해 주세요.\n 공백은 사용할 수 없습니다.' },
    create_id: {regexp: /^[A-Za-z0-9]{6,20}$/, message: '아이디를 6~20자의 영문, 숫자로 입력해 주세요.'},
    create_pw: {regexp: /^\S{8,20}$/, message: '비밀번호를 8~20자의 영문, 숫자, 특수문자 등으로 입력해 주세요.\n 공백은 사용할 수 없습니다.'},
    confirm_pw: {regexp: /^\S{8,20}$/, message: '재입력 비밀번호가 비밀번호와 일치하지 않습니다.'},
    email: {regexp: /^[\w.-]+@[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?(\.[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$/, message: '이메일을 정확히 입력해 주세요.'},
    phone_num: { regexp: /^01[016789]\d{7,8}$/, message: '휴대전화 번호는 -없이,\n 숫자 10자리 또는 11자리로 입력해 주세요.'},
    auth_num: { regexp: /^\d{4}$/, message: '인증번호 숫자 4자를 입력해주세요.'}
}

var tipMessage = '';

function checkInputValid(input) {
    if (input.name == 'confirm_pw') {
        if (input.value != document.getElementById('create_pw').value) {
            setWarningTip(input);
        } else {
            setCompleteTip(input);
        }
        tipMsgHandler(input, tipMessage);
        return;
    }
    if (!RegExp(inputRegExp[input.name].regexp).test(input.value)) {
        setWarningTip(input);
    } else {
        setCompleteTip(input);
    }
    tipMsgHandler(input, tipMessage);
}

function setWarningTip(input) {
    setTipMessage(inputRegExp[input.name].message);
    classNameHandler(input, 'complete', 'warning');
    addClassName(input.parentElement, 'tip_space');
}

function setCompleteTip(input) {
    setTipMessage('');
    classNameHandler(input, 'warning', 'complete');
    classNameHandler(input.parentElement, 'tip_space', '');
}

function setTipMessage(message) {
    tipMessage = message;
}


function submitDisabledHandler(){
    var requiredInputFields = document.querySelectorAll('input.field[required]');
    var requiredInputFieldsLength =requiredInputFields.length;
    var completedInputs = 0;
    requiredInputFields.forEach(function(element) {
        if (element.parentElement.style.display == 'none'){
            requiredInputFieldsLength--;
            return;
        }
        var classNameArray = element.className;
        if (classNameArray.indexOf('complete') != -1) {
            completedInputs++;
        }
    });
    var submit = document.querySelector('input[type=submit]');
    if (completedInputs == requiredInputFieldsLength){
        submit.disabled = false;
    } else {
        submit.disabled = true;
    }
}

function removeTipElement(element) {
    var tipElement = element.parentElement.lastChild;
    if (tipElement.className == 'tip'){
        tipElement.remove();
    }
}

function tipMsgHandler(element, message) {
    var tipElement = element.parentElement.lastChild;
    var newMsgNode = document.createTextNode(message);

    if (tipElement.className != 'tip'){
        tipElement = document.createElement("p");
        tipElement.className += 'tip';
        tipElement.appendChild(newMsgNode);
    } else {
        tipElement.innerText = message;
    }
    element.parentElement.appendChild(tipElement);
}

function classNameHandler(element, oldClass, newClass) {
    if (element == null || element == undefined) {
        return;
    }

    var classNameArray = element.className.split(" ");
    if (classNameArray.indexOf(oldClass) == -1 && newClass != '') {
        addClassName(element, newClass);
    } else {
        replaceClassName(element, oldClass, newClass);
    }
}

function replaceClassName(element, oldClass, newClass) {
    if (element == null || element == undefined) {
        return;
    }
    var classNameArray = element.className.split(" ");
    if (classNameArray.length != 1 && newClass != '') {
        newClass = " " + newClass;
    }
    var oldClassPattern = "(?:^|\\s)" + oldClass + "(?!\\S)";
    var oldClassRegExp = new RegExp(oldClassPattern, 'g');
    element.className = element.className.replace(oldClassRegExp, newClass);
}

function addClassName(element, newClass) {
    var classNameArray = element.className.split(" ");
    if (classNameArray.indexOf(newClass) != -1) {
        return;
    } else {
        if (classNameArray.length == 1 && classNameArray[0] == '') {
            element.className += newClass;
        } else {
            element.className += " " + newClass;
        }
    }
}
