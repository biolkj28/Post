$(document).ready(function () {

    readAll();
    document.getElementById("createWriter").addEventListener('click', createPost);
    hideLoginAndLogout();
})

function readAll() {
    const tbody = document.getElementById("post-list");
    $.ajax({
        type: "GET",
        url: "/api/post",
        contentType: "application/json",
        success: function (response) {
            $("post-list").empty();
            [...response].forEach((data, num) => {
                drawTr(data, num + 1);
            })
        },
        complete: function () {
            let trs = tbody.children;
            [...trs].forEach((tr) => {
                tr.addEventListener('click', detail);
            })
        }
    })
}
function goLogin(){
    window.location.href = "/user/loginView";
}
function logout(){
    $.removeCookie('token',{path:"/"});
}
function hideLoginAndLogout(){
    if ($.cookie('token')) {
        $("#login-btn").hide();
        $("#logout-btn").show();
    }else{
        $("#login-btn").show();
        $("#logout-btn").hide();
    }
}
function createPost() {
    let datas = document.getElementsByClassName('data');


    let empty = 0;
    [...datas].forEach((data) => {
        if (!!$(data).val() === false) {
            empty++;
        }
    })
    if (empty > 0) {
        alert("입력 되지 않은 항목이 있습니다.")
        return;
    }

    let data = {
        "title": $(datas[0]).val(),
        "contents": $(datas[1]).val()
    }

    $.ajax({
        type: "post",
        url: "/api/createPost",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            if(!!response){
                if(!!response.message){
                    alert (response.message)
                    location.reload();
                }else if(!!response.denied){
                    alert(response.denied)
                    location.href = "/user/loginView"
                }

            }

        },
        error: function (request, status, error) {
           if(request.status === 401){
               alert("로그인을 해주세요");
               window.location.href = '/user/loginView';
           }
        }
    })
}

function detail() {
    let id = this.children[0].innerText;
    location.href = `/detail/page/${id}`
}

function drawTr(data, num) {
    let html = `<tr>
                    <td style="display: none;">${data.id}</td>
                    <td width="5%">${num}</td>
                    <td width="55%">${data.title}</td>
                    <td width="20%">${data.writer}</td>
                    <td width="20%">${data.createAt}</td>
                </tr>
                `
    $('#post-list').append(html)
}


