<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Add icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/ce3fb13d84.js" crossorigin="anonymous"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        .input-container {
            display: -ms-flexbox;
            /* IE10 */
            display: flex;
            width: 100%;
            height: 50px;
            margin-bottom: 15px;
        }

        .icon {
            padding: 10px;
            background: #fafafa;
            color: #cccccc;
            min-width: 50px;
            line-height: 25px;
            text-align: center;
            max-width: 100%;
            height: auto;
            border: 1px solid #aaaaaa;
            border-right: none;
        }

        .input-field {
            width: 100%;
            padding: 10px;
            outline: none;
        }

        .input-container:focus {
            border: 2px solid dodgerblue;
        }

        /* Set a style for the submit button */
        .btn {
            background-color: dodgerblue;
            color: white;
            padding: 15px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        a {
            color: black;
        }


        .btn:hover {
            opacity: 1;
        }

        [id*='ch'] {
            color: red;
        }

        #border {
            border: 2px red solid;
        }
    </style>
</head>

<body>

    <form action="/action_page.php" style="max-width:500px;margin:auto" name="form1">
        <h2><img src="img/coopang.png" alt=""
                style="width:180px; height:50px; position: relative; left: 150px; margin-bottom: -20px;"></h2>
        <div class="input-container">
            <i class="far fa-envelope icon"></i>
            <input class="input-field" type="text" placeholder="아이디(이메일)" onblur="emailcheck(this)" name="email">
        </div>

        <script>
            var emailchecker = 0;
            function isEmail(asValue) {
                var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
                return regExp.test(asValue);
            }

            function emailcheck(obj) {
                var str = obj.value;
                var parent = obj.parentNode;
                if (str == "") {
                    if (emailchecker == 0) {
                        if (document.querySelector("#chemail") != null) {
                            console.log(document.querySelector("#chemail"));
                            document.querySelector("#chemail").remove();
                            parent.setAttribute("id", "");
                        }
                        parent.insertAdjacentHTML('afterend', '<p id="chemail">이메일을 입력하세요.</p>')
                        emailchecker++;
                        parent.setAttribute("id", "border");
                    }
                }
                else {
                    if (emailchecker == 1) {
                        emailchecker--;
                        var remove = document.getElementById("chemail");
                        parent.setAttribute("id", "");
                        remove.remove();
                    }
                    if (isEmail(str)){
                        if (document.querySelector("#chemail") != null) {
                            console.log(document.querySelector("#chemail"));
                            document.querySelector("#chemail").remove();
                            parent.setAttribute("id", "");
                        }
                    }else{
                        if (document.querySelector("#chemail") != null) {
                            console.log(document.querySelector("#chemail"));
                            document.querySelector("#chemail").remove();
                            parent.setAttribute("id", "");
                        }
                        parent.insertAdjacentHTML('afterend', '<p id="chemail">이메일 형식이 올바르지 않습니다.</p>')
                        emailchecker++;
                        parent.setAttribute("id", "border");
                    }
                }
            }
        </script>


        <div class="input-container">
            <i class="fas fa-lock icon"></i>
            <input class="input-field" type="password" placeholder="비밀번호(영문 숫자 특수문자 2가지 이상 6~15자 이내)"
                onblur="pwcheck(this)" name="pw">
        </div>


        <div class="input-container">
            <i class="fas fa-lock icon"></i>
            <input class="input-field" type="password" placeholder="비밀번호 확인" onblur="pwcheck2(this)" name="chpw">
        </div>
        <script>
            var pwchecker = 0;
            function pwcheck(obj) {
                var str = obj.value;
                var parent = obj.parentNode;
                var checker = document.form1.chpw.value;
                var num = str.search(/[0-9]/g);
                var eng = str.search(/[a-z]/ig);
                var spe = str.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

                if (str.length < 6 || str.length > 15) {
                    if (pwchecker == 0) {
                        if (document.querySelector("#chpw") != null) {
                            console.log(document.querySelector("#chpw"));
                            document.querySelector("#chpw").remove();
                            parent.setAttribute("id", "");
                        }

                        parent.insertAdjacentHTML('afterend', '<p id="chpw">비밀번호는 6~15자 이내로 입력하셔야 합니다.</p>')
                        pwchecker++;
                        parent.setAttribute("id", "border");
                    }
                }
                else {
                    if (pwchecker == 1) {
                        pwchecker--;
                        var remove = document.querySelector("#chpw");
                        remove.remove();
                        parent.setAttribute("id", "");
                    }
                    if (str == checker) {
                        if (pwchecker2 == 1) {
                            pwchecker2--;
                            var remove = document.querySelector("#chpw2");
                            document.form1.chpw.parentNode.setAttribute("id", "");
                            remove.remove();
                        }
                    } else if (checker != "") {
                        if (pwchecker2 == 0) {
                            document.form1.chpw.parentNode.insertAdjacentHTML('afterend', '<p id="chpw2">비밀번호가 일치하지 않습니다.</p>')
                            pwchecker2++;
                            document.form1.chpw.parentNode.setAttribute("id", "border");
                        }
                    }
                    if (num < 0 || eng < 0 || spe < 0) {
                        if (document.querySelector("#chpw") != null) {
                            document.querySelector("#chpw").remove();
                            parent.setAttribute("id", "");
                        }

                        parent.insertAdjacentHTML('afterend', '<p id="chpw">영문,숫자,특수문자를 혼합하여 입력해주세요.</p>')
                        parent.setAttribute("id", "border");
                    } else {
                        if (document.querySelector("#chpw") != null) {
                            document.querySelector("#chpw").remove();
                            parent.setAttribute("id", "");
                        }
                    }
                }

            }
            var pwchecker2 = 0;
            function pwcheck2(obj) {
                var str = obj.value;
                var parent = obj.parentNode;
                var checker2 = document.form1.pw.value;
                if (str != '') {
                    if (str == checker2) {
                        if (pwchecker2 == 1) {
                            pwchecker2--;
                            var remove = document.querySelector("#chpw2");
                            remove.remove();
                            parent.setAttribute("id", "");
                        }
                    } else {
                        if (pwchecker2 == 0) {
                            parent.insertAdjacentHTML('afterend', '<p id="chpw2">비밀번호가 일치하지 않습니다.</p>')
                            pwchecker2++;
                            parent.setAttribute("id", "border");
                        }
                    }
                }
            }
        </script>

        <div class="input-container">
            <i class="far fa-user icon"></i>
            <input class="input-field" type="text" placeholder="이름" onblur="namecheck(this)" name="name">
        </div>
        <script>
            var namechecker = 0;
            function namecheck(obj) {
                var str = obj.value;
                var parent = obj.parentNode;
                if (str == "") {
                    if (namechecker == 0) {
                        parent.insertAdjacentHTML('afterend', '<p id="chname">이름을 정확히 입력하세요.</p>')
                        namechecker++;
                        parent.setAttribute("id", "border");
                    }
                }
                else {
                    if (namechecker == 1) {
                        namechecker--;
                        var remove = document.querySelector("#chname");
                        remove.remove();
                        parent.setAttribute("id", "");
                    }
                }

            }
        </script>
        <div class="input-container">
            <i class="fas fa-mobile-alt icon"></i>
            <input class="input-field" type="text" placeholder="휴대폰 번호" onblur="phonecheck(this)" name="phone">
        </div>
        <script>
            var phonechecker = 0;
            function isPhone1(asValue) {
                var regExp = /^\d{3}\d{3,4}\d{4}$/;
                return regExp.test(asValue);
            }
            function isPhone2(asValue) {
                var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
                return regExp.test(asValue);
            }
            function phonecheck(obj) {
                var str = obj.value;
                var parent = obj.parentNode;
                if (str == "") {
                    if (phonechecker == 0) {
                        if (document.querySelector("#chphone") != null) {
                            document.querySelector("#chphone").remove();
                            parent.setAttribute("id", "");
                        }
                        parent.insertAdjacentHTML('afterend', '<p id="chphone">휴대폰 번호를 정확하게 입력하세요.</p>')
                        phonechecker++;
                        parent.setAttribute("id", "border");
                    }
                }
                else {
                    if (phonechecker == 1) {
                        phonechecker--;
                        var remove = document.querySelector("#chphone");
                        parent.setAttribute("id", "");
                        remove.remove();
                    }
                    if(isPhone1(str) || isPhone2(str)){
                        if (document.querySelector("#chphone") != null) {
                            document.querySelector("#chphone").remove();
                            parent.setAttribute("id", "");
                        }
                    }else{
                        if (document.querySelector("#chphone") != null) {
                            document.querySelector("#chphone").remove();
                            parent.setAttribute("id", "");
                        }
                        parent.insertAdjacentHTML('afterend', '<p id="chphone">휴대폰 번호 형식이 올바르지 않습니다.</p>')
                        phonechecker++;
                        parent.setAttribute("id", "border");
                    }
                }
            }
        </script>
        <button type="submit" class="btn">동의하고 가입하기</button>

        본인은 <Strong>만 14세 이상</Strong>이며, <a href="">쿠팡 이용약관</a>, <a href="">전자금융거래이용약관</a>, <a href="">개인정보 수집 및 이용</a>,
        <a href="">개인정보 제공 내용</a>을 확인 하였으며, <strong>동의합니다.</strong>
        <footer style="text-align: center; margin-top: 20px;">©Coupang Corp. All rights reserved.</footer>
    </form>

</body>

</html>