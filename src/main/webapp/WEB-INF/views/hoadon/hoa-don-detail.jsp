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
    <style>
        .status-bar {
            display: flex;
            justify-content: center; /* Chuyển thanh trạng thái ra giữa */
            background-color: #DDDDDD;
            padding: 10px;
            border-radius: 50px 50px 50px 50px;
            margin: 0 auto; /* Đưa thanh trạng thái vào giữa trang */
            max-width: 1700px; /* Đặt chiều rộng tối đa của thanh trạng thái */
            border: 1px solid black;
        }

        .status {
            flex: 1;
            text-align: center;
        }

        .pill {
            border: 2px solid blue;
            display: inline-block;
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: bold;
            color: #000;
            margin-right: 1cm; /* Khoảng cách giữa các viên thuốc */
            background-color: white; /* Màu xám mặc định cho tất cả các trạng thái */
        }

        .status-bar .status:hover .pill.default {
            background-color: #00ff00;
        }

        .pending {
            color: black;
            background-color: greenyellow;
        }

        /* Màu xanh cho trạng thái Đang chờ */
        .confirmed {
            color: black;
            background-color: yellow;
        }

        /* Màu xanh cho trạng thái Đã xác nhận */
        .waiting {
            color: black;
            background-color: yellowgreen;
        }

        /* Màu xanh cho trạng thái Chờ thanh toán */
        .paid {
            color: black;
            background-color: cyan;
        }

        /* Màu xanh cho trạng thái Đã thanh toán */
        .canceled {
            color: black;
            background-color: red;
        }

        /* Màu xanh cho trạng thái Đã hủy */
        .default {
            color: black;
        }
    </style>
    <title>Document</title>
</head>
<body>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                       aria-controls="description" aria-selected="true">Thông tin hóa đơn</a>
                </li>
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
                    <a class="nav-link" href="/hoa-don/dang-van-chuyen" role="tab">Đang vận chuyển</a>
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
                            <div class="row">
                                <div class="col-lg-12">
                                    <br>
                                    <br>
                                    <div>
                                        <c:if test="${hoaDonDetail.loaiHoaDon==0}">
                                            <div class="status-bar">
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 0 ? 'pending' : 'default'}">
                                                        Đang
                                                        chờ
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 1 ? 'confirmed' : 'default'}">
                                                        Đã xác
                                                        nhận
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 2 ? 'waiting' : 'default'}">
                                                        Chờ thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 3 ? 'paid' : 'default'}">
                                                        Đã thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 8 ? 'canceled' : 'default'}">
                                                        Đã hủy
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${hoaDonDetail.loaiHoaDon==1}">
                                            <div class="status-bar">
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 2 && hoaDonDetail.trangThaiGiaoHang==1 ? 'waiting' : 'default'}">
                                                        Chờ thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 2&& hoaDonDetail.trangThaiGiaoHang==1 ? 'paid' : 'default'}">
                                                        Đã thanh toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDonDetail.trangThaiHoaDon == 2|| hoaDonDetail.trangThaiHoaDon==3) && hoaDonDetail.trangThaiGiaoHang==3 ? 'pending' : 'default'}">
                                                        Chờ giao hàng
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDonDetail.trangThaiHoaDon == 2|| hoaDonDetail.trangThaiHoaDon==3) && hoaDonDetail.trangThaiGiaoHang==4 ? 'confirmed' : 'default'}">
                                                        Đang giao hàng
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDonDetail.trangThaiHoaDon == 2|| hoaDonDetail.trangThaiHoaDon==3) && hoaDonDetail.trangThaiGiaoHang==6 ? 'paid' : 'default'}">
                                                        Giao hàng hoàn tất
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDonDetail.trangThaiHoaDon == 8 || hoaDonDetail.trangThaiGiaoHang==5? 'canceled' : 'default'}">
                                                        Đã hủy
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <br>
                                    <br>
                                    <h4 class="card-title">Thông tin đơn hàng</h4>
                                    <div class="basic-form">
                                        <form>
                                            <div class="row">
                                                <div class="col">
                                                    <label>Mã: ${hoaDonDetail.ma}</label>
                                                </div>
                                                <div class="col">
                                                    <label>SĐT người nhận: ${hoaDonDetail.sdtNguoiNhan}</label>
                                                </div>
                                                <div class="col">
                                                    <label>Người đặt: ${hoaDonDetail.khachHang.hoTen}</label>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col">
                                                    <label>Tên người nhận: ${hoaDonDetail.tenNguoiNhan}</label>
                                                </div>
                                                <div class="col">
                                                    <label>Trạng thái:
                                                        <c:if test="${hoaDonDetail.trangThaiHoaDon == 0}"> Chờ xác nhận</c:if>
                                                        <c:if test="${hoaDonDetail.trangThaiHoaDon == 1}"> Đã xác nhận</c:if>
                                                        <c:if test="${hoaDonDetail.trangThaiHoaDon == 2}"> Chờ thanh toán</c:if>
                                                        <c:if test="${hoaDonDetail.trangThaiHoaDon == 3}"> Đã thanh toán</c:if>
                                                        <c:if test="${hoaDonDetail.trangThaiHoaDon == 8}"> Đã hủy</c:if>
                                                    </label>
                                                </div>
                                                <div class="col">
                                                    <label>Loại hóa đơn:
                                                        <c:if test="${hoaDonDetail.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${hoaDonDetail.loaiHoaDon == 1}">Online</c:if>
                                                    </label>
                                                </div>
                                            </div>
                                        </form>
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
                                                    <div class="table-responsive">
                                                        <table class="table table-striped table-bordered zero-configuration"
                                                               style="color: #0b0b0b">
                                                            <thead>
                                                            <tr>
                                                                <th>#</th>
                                                                <%--                                            <th>Ảnh</th>--%>
                                                                <th>Tên sản phẩm</th>
                                                                <th>Màu</th>
                                                                <th>Cổ</th>
                                                                <th>Kích cỡ</th>
                                                                <th>Thương kiệu</th>
                                                                <th>Chất liệu</th>
                                                                <th>Số lượng</th>
                                                                <th>Giá</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <c:forEach items="${listHoaDonChiTiet}"
                                                                       var="HDCT" varStatus="i">
                                                                <tr>
                                                                    <td>${i.index}</td>
                                                                        <%--                                                <td><img src="/images/${HDCT.idCTSP.hinhAnh.duongDan1}" width="40"--%>
                                                                        <%--                                                         height="40"></td>--%>
                                                                    <td>${HDCT.idCTSP.sanPham.ten}</td>
                                                                    <td>${HDCT.idCTSP.mauSac.ten}</td>
                                                                    <td>${HDCT.idCTSP.coAo.ten}</td>
                                                                    <td>${HDCT.idCTSP.kichCo.ten}</td>
                                                                    <td>${HDCT.idCTSP.thuongHieu.ten}</td>
                                                                    <td>${HDCT.idCTSP.chatLieu.ten}</td>
                                                                    <td>${HDCT.soLuong}</td>
                                                                    <td>${HDCT.soLuong * HDCT.donGia} VNĐ</td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <br>
                                                <h5>Tổng tiền hàng: ${hoaDonDetail.tongTien} VNĐ</h5>
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
</div>
</body>
</html>
