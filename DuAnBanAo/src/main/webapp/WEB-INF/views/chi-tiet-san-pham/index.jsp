<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/style.css" rel="stylesheet">

    <title>Document</title>

</head>
<body>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên sản phẩm</th>
            <th>Ngày thêm</th>
            <th>Số lượng</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ctsp}" var="ctsp" varStatus="i">
        <tr>
            <th>${i.index+1}</th>
            <td>${ctsp.sanPham.ten}</td>
            <td>${ctsp.ngayTao}</td>
            <td>${ctsp.soLuongTon}</td>

            <c:if test="${ctsp.trangThai==1}">
            <td><span class="badge badge-primary px-2">Đang bán</span>
            </td>
            </c:if>
            <c:if test="${ctsp.trangThai==0}">
            <td><span class="badge badge-danger px-2">Ngừng bán</span>
            </td>
            </c:if>

        </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>