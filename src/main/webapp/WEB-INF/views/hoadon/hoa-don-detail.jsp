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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
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
                                        <c:if test="${hoaDon.loaiHoaDon==0}">
                                            <div class="status-bar">
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 0 ? 'pending' : 'default'}">
                                                        Đang
                                                        chờ
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 1 ? 'confirmed' : 'default'}">
                                                        Đã xác
                                                        nhận
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 2 ? 'waiting' : 'default'}">
                                                        Chờ thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 3 ? 'paid' : 'default'}">
                                                        Đã thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 8 ? 'canceled' : 'default'}">
                                                        Đã hủy
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${hoaDon.loaiHoaDon==1}">
                                            <div class="status-bar">
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 2 && hoaDon.trangThaiGiaoHang==1 ? 'waiting' : 'default'}">
                                                        Chờ thanh
                                                        toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 2&& hoaDon.trangThaiGiaoHang==1 ? 'paid' : 'default'}">
                                                        Đã thanh toán
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDon.trangThaiHoaDon == 2|| hoaDon.trangThaiHoaDon==3) && hoaDon.trangThaiGiaoHang==3 ? 'pending' : 'default'}">
                                                        Chờ giao hàng
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDon.trangThaiHoaDon == 2|| hoaDon.trangThaiHoaDon==3) && hoaDon.trangThaiGiaoHang==4 ? 'confirmed' : 'default'}">
                                                        Đang giao hàng
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${(hoaDon.trangThaiHoaDon == 2|| hoaDon.trangThaiHoaDon==3) && hoaDon.trangThaiGiaoHang==6 ? 'paid' : 'default'}">
                                                        Giao hàng hoàn tất
                                                    </div>
                                                </div>
                                                <div class="status">
                                                    <div class="pill ${hoaDon.trangThaiHoaDon == 8 || hoaDon.trangThaiGiaoHang==5? 'canceled' : 'default'}">
                                                        Đã hủy
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <br>
                                    <br>
                                    <h4 class="card-title">Thông tin đơn hàng
                                        <c:if test="${hoaDon.trangThaiHoaDon == 0}">
                                            <button id="batmodalUpdateHoaDon" class="btn btn-primary"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#updateHoaDon">Cập nhập
                                            </button>
                                        </c:if>
                                    </h4>
                                    <div class="basic-form">
                                        <form>
                                            <div class="row">
                                                <div class="col">
                                                    <label>Mã: ${hoaDon.ma}</label>
                                                </div>
                                                <div class="col">
                                                    <label>SĐT người nhận: ${hoaDon.sdtNguoiNhan}</label>
                                                </div>
                                                <div class="col">
                                                    <label>Người đặt: ${hoaDon.khachHang.hoTen}</label>
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row">
                                                <div class="col">
                                                    <label>Tên người nhận: ${hoaDon.tenNguoiNhan}</label>
                                                </div>
                                                <div class="col">
                                                    <label>Trạng thái:
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 0}"> Chờ xác nhận</c:if>
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 1}"> Đã xác nhận</c:if>
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 2}"> Chờ thanh toán</c:if>
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 3}"> Đã thanh toán</c:if>
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 8}"> Đã hủy</c:if>
                                                    </label>
                                                </div>
                                                <div class="col">
                                                    <label>Loại hóa đơn:
                                                        <c:if test="${hoaDon.loaiHoaDon == 0}">Tại quầy</c:if>
                                                        <c:if test="${hoaDon.loaiHoaDon == 1}">Online</c:if>
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
                                            <div class="row">
                                                <div class="col-6">
                                                    <h4 class="card-title">Danh sách sản phẩm</h4>
                                                </div>

                                                <div class="col-6 btn-group" role="group"
                                                     aria-label="Basic example" style="float: right">
                                                    <c:if test="${hoaDon.trangThaiHoaDon==0}">
                                                        <a class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#QRScan"
                                                           style="float: right; height: 45px;color: black">Scan
                                                            QR</a>
                                                        <a href="/ban-hang/modal-san-pham"
                                                           class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#newSanPham"
                                                           style="float: right; height: 45px">Thêm sản
                                                            phẩm</a>
                                                    </c:if>
                                                </div>

                                            </div>
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
                                                                    <td><img
                                                                            src="/images/${HDCT.idCTSP.hinhAnh.duongDan1}"
                                                                            width="40"
                                                                            height="40"></td>
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
                                                <h5>Tổng tiền hàng: ${hoaDon.tongTien} VNĐ</h5>
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
<div class="modal fade" id="updateHoaDon" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/hoa-don/update/${hoaDon.id}" method="post" modelAttribute="hoaDon">
                    <div class="form-group">
                        <form:label class="form-label" path="ma">Mã hoá đơn:</form:label>
                        <form:input class="form-control" path="ma" readonly="true"/>
                        <form:errors path="ma" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="tenNguoiNhan">Người nhận:</form:label>
                        <form:input class="form-control" path="tenNguoiNhan" required="true"/>
                        <form:errors path="tenNguoiNhan" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="sdtNguoiNhan">SĐT người nhận:</form:label>
                        <form:input class="form-control" path="sdtNguoiNhan" required="true"/>
                        <form:errors path="sdtNguoiNhan" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <form:select path="khachHang" class="form-control"
                                     id="selectKhachHang">
                            <c:if test="${hoaDon.khachHang== null}">
                                <option selected disabled value="1">
                                    Khách hàng
                                </option>
                            </c:if>
                            <c:forEach items="${listKhachHang}" var="kh">
                                <option value="${kh.id}">${kh.hoTen}- ${kh.soDienThoai}</option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Cập nhật thông tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="QRScan" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black;text-align: center">
                <div class="ban-hang" style="align-items: center">
                    <video
                            style="border: 1px solid"
                            id="video"
                            autoplay="true"
                            width="200px"
                            height="120px"
                    ></video>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<button style="display: none" type="button" id="batmodalnhapsanpham" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#nhapSoLuong">
    Open modal
</button>
<div class="modal" id="nhapSoLuong">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/ban-hang/them-san-pham" method="post"
                           modelAttribute="chiTiet" enctype="multipart/form-data">
                    <div style="display: none">
                        <form:input class="form-control" path="ma"/>
                        <form:input class="form-control" path="id"/>
                        <form:input class="form-control" path="sanPham"/>
                        <form:input class="form-control" path="thuongHieu"/>
                        <form:input class="form-control" path="mauSac"/>
                        <form:input class="form-control" path="kichCo"/>
                        <form:input class="form-control" path="coAo"/>
                        <form:input class="form-control" path="hinhAnh"/>
                        <form:input class="form-control" path="chatLieu"/>
                        <form:input class="form-control" path="ngayTao"/>
                        <form:input class="form-control" path="nguoiTao"/>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                        <form:input class="form-control" path="soLuongTon" readonly="true"/>
                        <form:errors path="soLuongTon" cssStyle="color: red"></form:errors>
                    </div>
                    <div class="form-group">
                        <label class="form-label">Nhập số lượng: </label>
                        <input type="number" class="form-control" name="soLuong" required min="1">
                        <label style="color: red">${thongBaoSoLuong}</label>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                            Thêm sản phẩm
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-bs-target="#newSanPham" data-bs-toggle="modal">
                    Trở về
                </button>
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Close
                </button>
            </div>
        </div>
    </div>
</div>
<button style="display: none" type="button" id="batmodallocsanpham" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#newSanPham">
    Open modal
</button>
<div class="modal fade" id="newSanPham" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div>
                            <form action="/ban-hang/loc" method="post"
                                  onsubmit="return checkLoc()">
                                <div class="row">
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="sanPham" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectSanPham">
                                                <option selected disabled>Sản phẩm</option>
                                                <c:forEach items="${listSanPham}" var="sp" varStatus="i">
                                                    <option value="${sp.id}">${sp.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="chatLieu" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectChatLieu">
                                                <option selected disabled>Chất liệu</option>
                                                <c:forEach items="${listChatLieu}" var="cl" varStatus="i">
                                                    <option value="${cl.id}">${cl.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="mauSac" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectMauSac">
                                                <option selected disabled>Màu sắc</option>
                                                <c:forEach items="${listMauSac}" var="ms" varStatus="i">
                                                    <option value="${ms.id}">${ms.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="kichCo" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectKichCo">
                                                <option selected disabled>Kích cỡ</option>
                                                <c:forEach items="${listKichCo}" var="kc" varStatus="i">
                                                    <option value="${kc.id}">${kc.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="coAo" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectCoAo">
                                                <option selected disabled>Cổ áo</option>
                                                <c:forEach items="${listCoAo}" var="ca" varStatus="i">
                                                    <option value="${ca.id}">${ca.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div class="form-group">
                                            <select name="thuongHieu" class="form-control"
                                                    style="font-weight: bold; width: 100%"
                                                    id="selectThuongHieu">
                                                <option selected disabled>Thương hiệu</option>
                                                <c:forEach items="${listThuongHieu}" var="th" varStatus="i">
                                                    <option value="${th.id}">${th.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-primary mr-2"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            Lọc Thông Tin
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div class="card-body">
                                <div></div>
                                <h4 class="card-title" style="float: left">Sản Phẩm</h4>
                                <%--            Tìm kiếm               --%>
                                <%--                                <div class="input-group"--%>
                                <%--                                     style="width: 40%; float: right">--%>
                                <%--                                    <input type="text"--%>
                                <%--                                           class="form-control"--%>
                                <%--                                           name="search-imei"--%>
                                <%--                                           id="imeiSearchInput"--%>
                                <%--                                           placeholder="Tìm kiếm sản phẩm">--%>
                                <%--                                    <div class="input-group-append">--%>
                                <%--                                        <button class="btn btn-sm btn-primary"--%>
                                <%--                                                type="button"--%>
                                <%--                                                id="searchImei"--%>
                                <%--                                        >Tìm kiếm--%>
                                <%--                                        </button>--%>
                                <%--                                    </div>--%>
                                <%--                                </div>--%>
                                <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <div>
                                        <table class="table table-striped table-bordered zero-configuration"
                                               style="min-width: 1200px; color: black;text-align: center">
                                            <thead>
                                            <tr>
                                                <th>Mã</th>
                                                <th>Ảnh Sản Phẩm</th>
                                                <th>Tên Sản Phẩm</th>
                                                <th>Thương Hiệu</th>
                                                <th>Cổ Áo</th>
                                                <th>Chất Liệu</th>
                                                <th>Kích Thước</th>
                                                <th>Màu Sắc</th>
                                                <th>Giá Bán</th>
                                                <th>Số Lượng Tồn</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody class="san_pham_search" style="text-align: center"
                                                   id="banglocthaydoi">
                                            <c:forEach items="${listCTSP}" var="ctsp">
                                                <tr>
                                                    <td>${ctsp.ma}</td>
                                                    <td>
                                                        <img src="../../../uploads/${ctsp.hinhAnh.duongDan1}" width="40"
                                                             height="40"
                                                             style="border-radius:50% 50% 50% 50%">
                                                    </td>
                                                    <td>${ctsp.sanPham.ten}</td>
                                                    <td>${ctsp.thuongHieu.ten}</td>
                                                    <td>${ctsp.coAo.ten}</td>
                                                    <td>${ctsp.chatLieu.ten}</td>
                                                    <td>${ctsp.kichCo.ten}</td>
                                                    <td>${ctsp.mauSac.ten}</td>
                                                    <td>
                                                        <script>
                                                            var donGia = ${ctsp.giaBan};
                                                            document.write(donGia.toLocaleString('vi-VN'));
                                                        </script>
                                                        VND
                                                    </td>
                                                    <td>${ctsp.soLuongTon}</td>
                                                    <td>
                                                        <a href="/ban-hang/nhap-so-luong/${ctsp.id}"
                                                           class="btn btn-warning btn-icon-text">Nhập số lượng</a>
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

</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectSanPham').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectThuongHieu').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectCoAo').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectMauSac').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectChatLieu').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectKichCo').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
</script>
<script>
    <c:if test="${batmodallocsanpham==0}">
    document.getElementById('batmodallocsanpham').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalnhapsanpham==0}">
    document.getElementById('batmodalnhapsanpham').click();
    </c:if>
</script>
<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../js/scan-qr-2.js"></script>
</html>
