<#import "parts/common.ftl" as c>
<@c.page>
<#global passwordEqual="">
Add new user
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" name="username"> </label></div>
    <div><label> Password: <input type="text" name="password" id="password"> </label></div>
    <div><label> Repeat Password: <input type="text" name="repeat_password" id="repeat_password" oninput="myFunction()"> </label></div>
    <div><label> Email: <input type="text" name="email"> </label></div>

    <div><input type="submit" value="Sign In"/></div>
</form>
    <p id="demo" value="${passwordEqual}"></p>
    <script>
        function myFunction() {
            var password = document.getElementById("password").value;
            var repeat_password = document.getElementById("repeat_password").value;
            if (password === repeat_password){
                document.getElementById("demo").innerHTML = "password equal";
                submit.set
            } else {
                document.getElementById("demo").innerHTML = "password don't equal";
            }
        }
    </script>
</@c.page>