<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/style.css" rel="stylesheet">
    <title>Document</title>
</head>
<body>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <ul class="nav nav-pills mb-3">
                <li class="nav-item"><a href="#navpills-0" class="nav-link active" data-toggle="tab"
                                        aria-expanded="false">Tất cả</a>
                </li>
                <li class="nav-item"><a href="#navpills-1" class="nav-link" data-toggle="tab" aria-expanded="false">Đã huỷ</a>
                </li>
                <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab" aria-expanded="true">Chờ xác nhận</a>
                </li>
                <li class="nav-item"><a href="#navpills-3" class="nav-link" data-toggle="tab" aria-expanded="true">Chờ giao hàng</a>
                </li>
                <li class="nav-item"><a href="#navpills-4" class="nav-link" data-toggle="tab" aria-expanded="true">Đang vận chuyển</a>
                </li>
                <li class="nav-item"><a href="#navpills-5" class="nav-link" data-toggle="tab" aria-expanded="true">Đã giao hàng</a>
                </li>
                <li class="nav-item"><a href="#navpills-6" class="nav-link" data-toggle="tab" aria-expanded="true">Chờ thanh toán</a>
                </li>
                <li class="nav-item"><a href="#navpills-7" class="nav-link" data-toggle="tab" aria-expanded="true">Đã thanh toán</a>
                </li>
                <li class="nav-item"><a href="#navpills-8" class="nav-link" data-toggle="tab" aria-expanded="true">Hoàn thành</a>
                </li>
            </ul>
            <div class="tab-content br-n pn">
                <div id="navpills-0" class="tab-pane active">
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
                                            <c:forEach items="${listHoaDonAll}" var="All" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${All.ma}</td>
                                                    <td>${All.tenNguoiNhan}</td>
                                                    <td>${All.tongTien}</td>
                                                    <td>${All.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${All.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${All.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${All.trangThaiHoaDon == 1}"> Đã huỷ</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 2}"> Chờ xác nhân</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 3}"> Chờ giao hàng</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 4}"> Đang vận chuyển</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 5}"> Đã giao hàng</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 6}"> Chờ thanh toán</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 7}"> Đã thanh toán</c:if>
                                                        <c:if test="${All.trangThaiHoaDon == 8}"> Hoàn thành</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-1" class="tab-pane">
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
                                        <c:forEach items="${listHoaDonHuy}" var="huy" varStatus="i">
                                            <tr>
                                                <td>${i.index}</td>
                                                <td>${huy.ma}</td>
                                                <td>${huy.tenNguoiNhan}</td>
                                                <td>${huy.tongTien}</td>
                                                <td>${huy.ngayTao}</td>
                                                <td>
                                                    <c:if test="${huy.loaiHoaDon == 0}">Tại quầy</c:if>
                                                    <c:if test="${huy.loaiHoaDon == 1}">Online</c:if>
                                                </td>
                                                <td>
                                                    <c:if test="${huy.trangThaiHoaDon == 1}"> Đã huỷ</c:if>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-2" class="tab-pane">
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
                                            <c:forEach items="${listHoaChoXacNhan}" var="CGH" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${CGH.ma}</td>
                                                    <td>${CGH.tenNguoiNhan}</td>
                                                    <td>${CGH.tongTien}</td>
                                                    <td>${CGH.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${CGH.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${CGH.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${CGH.trangThaiHoaDon == 2}">Chờ xác nhận</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-3" class="tab-pane">
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
                                            <c:forEach items="${listHoaDonChoGiaoHang}" var="CGH" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${CGH.ma}</td>
                                                    <td>${CGH.tenNguoiNhan}</td>
                                                    <td>${CGH.tongTien}</td>
                                                    <td>${CGH.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${CGH.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${CGH.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${CGH.trangThaiHoaDon == 3}"> Chờ giao hàng</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-4" class="tab-pane">
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
                                                        <c:if test="${DVC.trangThaiHoaDon == 4}">Đang vận chuyển</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-5" class="tab-pane">
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
                                            <c:forEach items="${listHoaDaGiaoHang}" var="DGH" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${DGH.ma}</td>
                                                    <td>${DGH.tenNguoiNhan}</td>
                                                    <td>${DGH.tongTien}</td>
                                                    <td>${DGH.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${DGH.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${DGH.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${DGH.trangThaiHoaDon == 5}">Đã giao hàng</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-6" class="tab-pane">
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
                                            <c:forEach items="${listHoaDonChoThanhToan}" var="CTT" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${CTT.ma}</td>
                                                    <td>${CTT.tenNguoiNhan}</td>
                                                    <td>${CTT.tongTien}</td>
                                                    <td>${CTT.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${CTT.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${CTT.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${CTT.trangThaiHoaDon == 6}">Chờ thanh toán</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-7" class="tab-pane">
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
                                            <c:forEach items="${listHoaDonDaThanhToan}" var="DTT" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${DTT.ma}</td>
                                                    <td>${DTT.tenNguoiNhan}</td>
                                                    <td>${DTT.tongTien}</td>
                                                    <td>${DTT.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${DTT.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${DTT.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${DTT.trangThaiHoaDon == 7}">Đã thanh toán</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
                <div id="navpills-8" class="tab-pane">
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
                                            <c:forEach items="${listHoaDonHoanThanh}" var="DHT" varStatus="i">
                                                <tr>
                                                    <td>${i.index}</td>
                                                    <td>${DHT.ma}</td>
                                                    <td>${DHT.tenNguoiNhan}</td>
                                                    <td>${DHT.tongTien}</td>
                                                    <td>${DHT.ngayTao}</td>
                                                    <td>
                                                        <c:if test="${DHT.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${DHT.loaiHoaDon == 1}">Online</c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${DHT.trangThaiHoaDon == 8}"> Đã hoàn thành</c:if>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn mb-1 btn-flat btn-primary">Xem</button>
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
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>