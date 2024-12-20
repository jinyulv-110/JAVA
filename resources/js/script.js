document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 阻止表单默认提交行为

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    //输出用户名和密码到控制台
    console.log('Username:', username);
    // 使用axios 向LoginServlet发送post请求，并携带参数username 和 password
    axios({
        method: 'post',
        url: '/login?username='+username + '&password='+password,
    })
        .then(function (response) {
            // 响应成功
            console.log(response.data);
        });
    console.log('Password:', password);

});
