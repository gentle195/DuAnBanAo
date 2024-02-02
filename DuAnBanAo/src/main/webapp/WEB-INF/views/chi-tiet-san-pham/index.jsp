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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/font-awesome.min.css">
    <title>Document</title>

</head>
<body>
<h3>Sản phẩm</h3>
<div class="card">

    <div class="card-body">
        <div class="row">
            <div class="col-6">
                <div class="form-group">
                    <input type="text" class="form-control input-rounded" placeholder="Nhập sản phẩm cần tìm kiếm...">
                </div>
            </div>
            <div class="col-6">
                <a href="/chi-tiet-san-pham/add" style="float:right;" class="btn mb-1 btn-outline-primary">Thêm mới</a>
            </div>
        </div>


        <div class="bootstrap-carousel">
            <div class="carousel slide" data-ride="carousel">
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
                                    <td><span class="badge badge-success px-2">Đang bán</span>
                                    </td>
                                </c:if>
                                <c:if test="${ctsp.trangThai==0}">
                                    <td><span class="badge badge-danger px-2">Ngừng bán</span>
                                    </td>
                                </c:if>
                                <td>
                                    <a href="">Chi tiết</a>
                                </td>

                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>