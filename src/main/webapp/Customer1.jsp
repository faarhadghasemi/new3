<%--
  Created by IntelliJ IDEA.
  User: mamrez
  Date: 10/31/2023
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fa" dir="rtl">
<head>
    <title>داشبورد کاربری تیکت</title>
    <link rel="stylesheet" href="/assets/font/IRANYekanX-Bold.woff2">
    <link rel="stylesheet" href="/assets/font/IRANYekanX-Light.woff2">
    <link rel="stylesheet" href="/assets/font/IRANYekanX-Regular.woff2">
    <link rel="stylesheet" href="src/main/webapp/assets/Customer1/css/style2.css">

</head>
<body>


<div class="top">
    <h1>صفحه ثبت تیکت </h1>
</div>

<div class="content-container">
    <div class="image-container">
        <img src="assets/image/Ticketing%20img.jpg" alt="تصویر شماره1" style="float:right;width:500px;height:300px;">
    </div>

    <div class="form-container">
        <div class="myform">
            <h2>لطفا مشکل خود را به صورت کامل توضیح دهید و دکمه ثبت رو بزنید</h2>
            <form action="/user.do" method="post">
                <label>
                    <p style="font-size:30px;">کاربر شماره1</p>
                </label>
                <input type="text" name="subject" placeholder="موضوع">
                <textarea name="description" placeholder="متن توضیحات" rows="10"></textarea>
                <input type="submit" value="ثبت">
            </form>
        </div>
    </div>
</div>

<%--<!--    <table>-->--%>
<%--<!--        <thead>-->--%>
<%--<!--            <tr>-->--%>
<%--<!--                <th>User Role</th>-->--%>
<%--<!--                <th>ID</th>-->--%>
<%--<!--                <th>UserName</th>-->--%>
<%--<!--                <th>Name</th>-->--%>
<%--<!--                <th>Family</th>-->--%>
<%--<!--            </tr>-->--%>
<%--<!--        </thead>-->--%>
<%--<!--&lt;!&ndash;        <tbody>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;            <% for (User user : UserService.getService().findAll()) { %>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;            <tr>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;                <td><%= user.getUserRoles() %></td>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;                <td><%= user.getId() %></td>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;                <td><%= user.getUserName() %></td>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;                <td><%= user.getName() %></td>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;                <td><%= user.getFamily() %></td>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;            </tr>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;            <% } %>&ndash;&gt;-->--%>
<%--<!--&lt;!&ndash;        </tbody>&ndash;&gt;-->--%>
<%--<!--    </table>-->--%>
<script src="assets/Customer1/js/"></script>
</body>
</html>
