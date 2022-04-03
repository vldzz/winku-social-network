function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var rememberMe = document.getElementById("rememberMe").checked;
    var data = {
        username: username,
        password: password,
        rememberMe: rememberMe
    };
    $.ajax({
        type: "POST",
        url: "/auth/login",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            if (data.status == 200) {
                alert("Login successful");
                setCookie("token", data.token, 30);
                // window.location.href = "hello";
            } else {
                alert("-" + data.status + ": " + data.message);
            }
        },
        error: function (data) {
            console.log(data);
            alert("Error " + data.status + ": " + data.responseText);
        }
    });
}

function register() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var data = {
        username: username,
        password: password,
        confirmPassword: confirmPassword
    };
    $.ajax({
        type: "POST",
        url: "/auth/register",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            if (data.status == 200) {
                alert("Register successful");
                setCookie("token", data.token, 30);
                // window.location.href = "hello";
            } else {
                alert("-" + data.status + ": " + data.message);
            }
        },
        error: function (data) {
            console.log(data);
            alert("Error " + data.status + ": " + data.responseText);
        }
    });
}