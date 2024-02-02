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
<div class="row">
    <div class="col-lg-12">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Thông tin đơn hàng</h4>
                <div class="basic-form">
                    <form>
                        <div class="row">
                            <div class="col-4">
                                <label>Mã: ${hoaDonDetail.ma}</label>
                            </div>
                            <div class="col">
                                <label>SĐT người nhận: ${hoaDonDetail.sdtNguoiNhan}</label>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col">
                                <label>Tên người nhận: ${hoaDonDetail.tenNguoiNhan}</label>
                            </div>
                            <div class="col">
                                <label>Trạng thái:
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 1}"> Đã huỷ</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 2}"> Chờ xác nhận</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 3}"> Chờ giao hàng</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 4}"> Đang vận chuyển</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 5}"> Đã giao hàng</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 6}"> Chờ thanh toán</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 7}"> Đã thanh toán</c:if>
                                    <c:if test="${hoaDonDetail.trangThaiHoaDon == 8}"> Hoàn thành</c:if>
                                </label>
                            </div>
                            <div class="col">
                                <label>Trạng thái:
                                <c:if test="${hoaDonDetail.loaiHoaDon == 0}">Tại quầy</c:if>
                                <c:if test="${hoaDonDetail.loaiHoaDon == 1}">Online</c:if>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Danh sách sản phẩm</h4>
                <div class="basic-form">
                    <div class=" col-xl-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Ảnh</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Màu</th>
                                            <th>Cổ</th>
                                            <th>Kích cỡ</th>
                                            <th>Số lượng</th>
                                            <th>Giá</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listHoaDonChiTiet}" var="HDCT" varStatus="i">
                                            <tr>
<%--                                                <td>${i.index}</td>--%>
<%--                                                <td>${HDCT.}</td>--%>
<%--                                                <td>${HDCT.idCTSP}</td>--%>
<%--                                                <td>${HDCT.tongTien}</td>--%>
<%--                                                <td>${HDCT.ngayTao}</td>--%>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h5>Tổng tiền hàng: ${hoaDonDetail.tongTien} VNĐ</h5>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
