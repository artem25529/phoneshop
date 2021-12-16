<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:page pageTitle="Login">
    <div class="container">
        <div class="row justify-content-center">
            <form action="<c:url value="/appLogin"/>" method="post" id="loginForm">
                Login: <input type="text" name="app_username"><br/><br/>
                Password: <input type="password" name="app_password"><br/><br/>
                <input type="submit" value="Login">
            </form>
        </div>
    </div>

    <script>
        let err = $(location).attr('href');
        if (err.includes('error')) {
            $('#loginForm').before($('<div>')
                    .text('Wrong credentials!')
                    .css('color', 'red'))
        }
    </script>
</tags:page>