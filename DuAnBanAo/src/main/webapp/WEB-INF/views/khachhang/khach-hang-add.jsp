<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="css/style.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
</head>
<body>

<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" href="/khach-hang/hien-thi" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm
                        thông tin
                        khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                       aria-controls="description" aria-selected="true">Thêm thông tin khách hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/khach-hang/hien-thi-delete" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Khách hàng
                        đã ngừng</a>
                </li>
            </ul>
        </div>
    </div>
</div>


<div class="card">
    <div class="card-body">
        <form:form action="/khach-hang/add" method="post" modelAttribute="khachHang">
            <div class="form-group">
                <form:label class="form-label" path="hoTen">Họ và tên:</form:label>
                <form:input class="form-control" path="hoTen"/>
                <form:errors path="hoTen" cssStyle="color: red"></form:errors>
            </div>
            <div class="form-group">
                <div class="form-control">
                    <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                    <form:radiobutton path="gioiTinh" value="true" checked="true" />Nam
                    <form:radiobutton path="gioiTinh" value="false"/> Nữ
                    <form:errors path="gioiTinh" cssStyle="color: red"></form:errors>
                </div>
            </div>
            <div class="form-group">
                <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                <form:input class="form-control" path="ngaySinh" type="date"/>
                <form:errors path="ngaySinh" cssStyle="color: red"></form:errors>
            </div>
            <div class="form-group">
                <form:label class="form-label" path="soDienThoai">Số điện thoại:</form:label>
                <form:input class="form-control" path="soDienThoai"/>
                <form:errors path="soDienThoai" cssStyle="color: red"></form:errors>
            </div>
            <div class="form-group">
                <form:label class="form-label" path="email">Email:</form:label>
                <form:input class="form-control" path="email"/>
                <form:errors path="email" cssStyle="color: red"></form:errors>
            </div>
            <div class="form-group">
                <form:label class="form-label" path="taiKhoan">Tài khoản:</form:label>
                <form:input class="form-control" path="taiKhoan"/>
                <form:errors path="taiKhoan" cssStyle="color: red"></form:errors>
            </div>
            <div class="form-group">
                <form:label class="form-label" path="matKhau">Mật khẩu:</form:label>
                <form:input class="form-control" path="matKhau"/>
                <form:errors path="matKhau" cssStyle="color: red"></form:errors>
            </div>
            <button type="submit" class="btn btn-primary mr-2"
                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                Thêm thông tin
            </button>
        </form:form>
    </div>
</div>
</div>
</body>
</html>
