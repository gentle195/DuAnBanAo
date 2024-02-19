<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
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
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <ul  class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hien-thi" role="tab">Tất cả</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-xac-nhan" role="tab">Chờ xác nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/da-xac-nhan" role="tab">Đã xác nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-giao-hang" role="tab">Chờ giao hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="/hoa-don/cho-giao-hang"
                       role="tab" aria-controls="description" aria-selected="false">Đang vận chuyển</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/huy" role="tab">Huỷ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hoan-thanh" role="tab">Hoàn thành</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="row align-items-center">
                        <div class=" col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Mã</th>
                                                <th>Tên người nhận</th>
                                                <th>Tổng tiền</th>
                                                <th>Ngày tạo</th>
                                                <th>Loại hoá đơn</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${listHoaDonDangVanChuyen}" var="DVC" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${DVC.ma}</td>
                                                    <td>${DVC.tenNguoiNhan}</td>
                                                    <td>${DVC.tongTien}</td>
                                                    <td>${DVC.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${DVC.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${DVC.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${DVC.trangThaiGiaoHang == 4}">Đang vận chuyển</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1  btn-outline-primary">
                                                            <a href="/hoa-don/detail/${All.id}">Xem</a></button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center pagination-lg">
                                                <li class="page-item"><a class="page-link"
                                                                         href="/hoa-don/dang-van-chuyen?pageNum=0">First</a></li>
                                                <c:forEach begin="1" end="${total}" varStatus="status">
                                                    <li class="page-item">
                                                        <a href="/hoa-don/dang-van-chuyen?pageNum=${status.index -1}"
                                                           class="page-link">${status.index}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="page-item"><a class="page-link"
                                                                         href="/hoa-don/dang-van-chuyen?pageNum=${total-1}">Last</a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>