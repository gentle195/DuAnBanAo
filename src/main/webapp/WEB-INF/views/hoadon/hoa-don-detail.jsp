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
<c:if test="${thongBao != null}">
    <div id="modalError" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-error swal2-animate-error-icon" style="display: block;">
                                    <span class="swal2-x-mark swal2-animate-x-mark"><span
                                            class="swal2-x-mark-line-left"></span><span
                                            class="swal2-x-mark-line-right"></span></span></div>
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBao}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${thongBaoThanhCong != null}">
    <div id="modalSuccess" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-success swal2-animate-success-icon"
                                 style="display: block;">
                                <div class="swal2-success-circular-line-left"
                                     style="background: rgb(255, 255, 255);"></div>
                                <span class="swal2-success-line-tip swal2-animate-success-line-tip"></span> <span
                                    class="swal2-success-line-long swal2-animate-success-line-long"></span>
                                <div class="swal2-success-ring"></div>
                                <div class="swal2-success-fix" style="background: rgb(255, 255, 255);"></div>
                                <div class="swal2-success-circular-line-right"
                                     style="background: rgb(255, 255, 255);"></div>
                            </div>
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoThanhCong}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <ul class="nav nav-pills mb-3" id="setting-panel" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                       aria-controls="description" aria-selected="true">Thông tin hóa đơn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hien-thi" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Tất cả</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-xac-nhan" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Chờ xác
                        nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/da-xac-nhan" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Đã xác
                        nhận</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/cho-giao-hang" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Chờ giao
                        hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/dang-van-chuyen" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Đang vận
                        chuyển</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/huy" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Huỷ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/hoa-don/hoan-thanh" role="tab"
                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hoàn
                        thành</a>
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
                                        <div class="status-bar">
                                            <div class="status">
                                                <div class="pill ${hoaDon.trangThaiGiaoHang==1 ? 'waiting' : 'default'}">
                                                    Chờ xác nhận
                                                </div>
                                            </div>
                                            <div class="status">
                                                <div class="pill ${hoaDon.trangThaiGiaoHang==2 ? 'waiting' : 'default'}">
                                                    Đã xác nhận
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

                                        <%--                                        trạng thái chờ xác nhận--%>
                                        <c:if test="${hoaDon.trangThaiHoaDon == 0 && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==0}">
                                            <a href="/hoa-don/update-tt-1/${hoaDon.id}" class="btn btn-warning">Xác nhận
                                                hóa đơn
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3) && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1}">
                                            <a href="/hoa-don/update-tt-1-online/${hoaDon.id}" class="btn btn-warning">Xác
                                                nhận hóa đơn online
                                            </a>
                                        </c:if>
                                        <c:if test="${hoaDon.trangThaiHoaDon == 0}">
                                            <a href="/hoa-don/update-tt-8/${hoaDon.id}" class="btn btn-danger">Huỷ hóa
                                                đơn tại quầy
                                            </a>
                                        </c:if>

                                        <%--                                        trạng thái đã xác nhận--%>
                                        <c:if test="${hoaDon.trangThaiHoaDon == 1}">
                                            <a href="/hoa-don/update-tt-0/${hoaDon.id}" class="btn btn-outline-warning">
                                                <- Quay lại trạng thái trước
                                            </a>
                                        </c:if>

                                        <c:if test="${hoaDon.trangThaiHoaDon == 1}">
                                            <a href="/hoa-don/update-tt-3/${hoaDon.id}" class="btn btn-warning">Thanh
                                                toán hoá đơn
                                            </a>
                                        </c:if>
                                        <c:if test="${hoaDon.trangThaiHoaDon == 1}">
                                            <a href="/hoa-don/update-tt-8/${hoaDon.id}" class="btn btn-danger">Huỷ hoá
                                                đơn tại quầy
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1 && hoaDon.trangThaiGiaoHang== 2}">
                                            <a href="/hoa-don/update-cho-giao-hang/${hoaDon.id}" class="btn btn-danger">
                                                Chờ giao hàng
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1 && hoaDon.trangThaiGiaoHang== 2}">
                                            <a href="/hoa-don/update-ve-cho-xac-nhan/${hoaDon.id}" class="btn btn-danger">
                                                Quay về chờ xác nhận
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1 && hoaDon.trangThaiGiaoHang== 3}">
                                            <a href="/hoa-don/update-dang-van-chuyen/${hoaDon.id}"
                                               class="btn btn-danger">
                                                Đang vận chuyển
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1 && hoaDon.trangThaiGiaoHang== 3}">
                                            <a href="/hoa-don/update-ve-cho-giao-hang/${hoaDon.id}"
                                               class="btn btn-danger">
                                                Quay về chờ giao hàng
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1 && hoaDon.trangThaiGiaoHang== 4}">
                                            <a href="/hoa-don/update-giao-hang-hoan-tat/${hoaDon.id}"
                                               class="btn btn-danger">
                                                Giao hàng hoàn tất
                                            </a>
                                        </c:if>
                                        <c:if test="${(hoaDon.trangThaiHoaDon == 2 || hoaDon.trangThaiHoaDon == 3)
                                        && listHoaDonChiTiet.size()>=1 && hoaDon.loaiHoaDon==1}">
                                            <a href="/hoa-don/update-huy-hoa-don-online/${hoaDon.id}"
                                               class="btn btn-danger">
                                                Hủy hóa đơn online
                                            </a>
                                        </c:if>
                                        <%--                                        trạng thái thanh toán--%>
                                        <%--                                        <c:if test="${hoaDon.trangThaiHoaDon == 3}">--%>
                                        <%--                                            <a href="/hoa-don/update-tt-0/${hoaDon.id}" class="btn btn-outline-warning">--%>
                                        <%--                                                <-Quay lại trạng thái ban đầu--%>
                                        <%--                                            </a>--%>
                                        <%--                                        </c:if>--%>

                                        <%--                                        trạng thái huỷ--%>
                                        <%--                                        <c:if test="${hoaDon.trangThaiHoaDon == 8}">--%>
                                        <%--                                            <a href="/hoa-don/khoi-phuc/${hoaDon.id}" class="btn btn-outline-warning">--%>
                                        <%--                                                Khôi phục--%>
                                        <%--                                            </a>--%>
                                        <%--                                        </c:if>--%>
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
                                                        <c:if test="${hoaDon.trangThaiHoaDon == 3 && hoaDon.trangThaiGiaoHang==6}"> Hoàn tất</c:if>
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
                                                    <c:if test="${(hoaDon.trangThaiHoaDon==0 || hoaDon.trangThaiHoaDon==1 )
                                                    || ((hoaDon.trangThaiHoaDon==2 || hoaDon.trangThaiHoaDon==3)
                                                    && hoaDon.loaiHoaDon==1 && (hoaDon.trangThaiGiaoHang == 1 ||hoaDon.trangThaiGiaoHang == 2 ||hoaDon.trangThaiGiaoHang == 3))}">
                                                        <a class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#QRScan"
                                                           style="float: right; height: 45px;color: black">Scan
                                                            QR</a>
                                                        <a class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#newSanPham"
                                                           style="float: right; height: 45px;color: black">Thêm sản
                                                            phẩm</a>
                                                    </c:if>
                                                    <c:if test="${hoaDon.phieuGiamGia == null && listHoaDonChiTiet.size()>=1}">

                                                        <a class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#phieuGiamGia"
                                                           style="height: 45px; color: black">Chọn
                                                            PGG</a>
                                                    </c:if>
                                                    <c:if test="${hoaDon.phieuGiamGia != null && listHoaDonChiTiet.size()>=1}">

                                                        <a class="btn btn-secondary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#phieuGiamGia"
                                                           style="height: 45px; color: black">Chọn
                                                            PGG</a>

                                                        <a class="btn btn-secondary"
                                                           href="/ban-hang/bo-phieu/${HoaDonCho.id}"
                                                           style="height: 45px; color: black">Bỏ PGG</a>
                                                    </c:if>
                                                </div>

                                            </div>
                                            <div class="basic-form">
                                                <div class=" col-xl-12">
                                                    <div class="table-responsive">
                                                        <table class="table table-striped table-bordered zero-configuration"
                                                               style="color: black">
                                                            <thead>
                                                            <tr>
                                                                <th>Ảnh</th>
                                                                <th>Tên sản phẩm</th>
                                                                <th>Thông tin sản phẩm</th>
                                                                <th>Đơn giá</th>
                                                                <th>Số lượng</th>
                                                                <th>Thành tiền</th>
                                                                <c:if test="${hoaDon.trangThaiHoaDon==0}">
                                                                    <th></th>
                                                                </c:if>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="table-search-hoa-don-chi-tiet">
                                                            <c:forEach items="${listHoaDonChiTiet}" var="hdct">
                                                                <tr>
                                                                    <td><img
                                                                            src="../../../uploads/${hdct.idCTSP.hinhAnh.duongDan1}"
                                                                            width="40"
                                                                            height="40"
                                                                            style="border-radius:50% 50% 50% 50%"></td>
                                                                    <td>${hdct.idCTSP.sanPham.ten}</td>
                                                                    <td>${hdct.idCTSP.chatLieu.ten} -
                                                                            ${hdct.idCTSP.thuongHieu.ten}
                                                                        - ${hdct.idCTSP.coAo.ten} -
                                                                            ${hdct.idCTSP.kichCo.ten}
                                                                        - ${hdct.idCTSP.mauSac.ten}</td>
                                                                    <td>
                                                                        <script>
                                                                            var donGia = ${hdct.donGia};
                                                                            document.write(donGia.toLocaleString('vi-VN'));
                                                                        </script>
                                                                        VND
                                                                    </td>
                                                                    <td>${hdct.soLuong}</td>
                                                                    <td>
                                                                        <script>
                                                                            var tongTien = ${hdct.donGia * hdct.soLuong};
                                                                            document.write(tongTien.toLocaleString('vi-VN'));
                                                                        </script>
                                                                        VND
                                                                    </td>
                                                                    <c:if test="${hoaDon.trangThaiHoaDon==0}">
                                                                        <td>
                                                                            <a href="/hoa-don/cap-nhat-so-luong/${hdct.id}"
                                                                               class="btn btn-warning btn-icon-text"
                                                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Cập
                                                                                nhật số lượng</a>
                                                                            <c:if test="${listHoaDonChiTiet.size()>1}">
                                                                                <a href="/hoa-don/delete-hoa-don-chi-tiet/${hdct.id}"
                                                                                   tyle="text-decoration: none;color: black"
                                                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                                                ><img src="../../../images/delete.png"
                                                                                      width="24px"
                                                                                      height="24px"></a>
                                                                            </c:if>

                                                                        </td>
                                                                    </c:if>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <br>
                                                <h5>Tổng tiền hàng:
                                                    <script>
                                                        var donGia = ${hoaDon.tongTien};
                                                        document.write(donGia.toLocaleString('vi-VN'));
                                                    </script>
                                                    VNĐ
                                                </h5>
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
<div class="modal fade" id="phieuGiamGia" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div>
                            <div class="card-body">
                                <div></div>
                                <h4 class="card-title" style="float: left">Phiếu giảm giá</h4>
                                <div class="table-responsive">
                                    <div>
                                        <table class="table table-striped table-bordered zero-configuration"
                                               style="min-width: 1200px; color: black;text-align: center">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Mã</th>
                                                <th>Tên phiếu</th>
                                                <th>Giá trị giảm</th>
                                                <th>Hóa đơn tối thiểu</th>
                                                <th>Giảm tối đa</th>
                                                <th>Ngày bắt đầu</th>
                                                <th>Ngày kết thúc</th>
                                                <th>Số Lượng Tồn</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody style="text-align: center">
                                            <c:forEach items="${listPGG}" var="pgg" varStatus="i">
                                                <tr>
                                                    <td>${i.index+1}</td>
                                                    <td>${pgg.ma}</td>
                                                    <td>${pgg.ten}</td>
                                                    <td>${pgg.tienGiam} %</td>
                                                    <td>
                                                        <script>
                                                            var donGia = ${pgg.giamToiThieu};
                                                            document.write(donGia.toLocaleString('vi-VN'));
                                                        </script>
                                                        VND
                                                    </td>
                                                    <td>
                                                        <script>
                                                            var donGia1 = ${pgg.giamToiDa};
                                                            document.write(donGia1.toLocaleString('vi-VN'));
                                                        </script>
                                                        VND
                                                    </td>
                                                    <td>${pgg.ngayBatDau}</td>
                                                    <td>${pgg.ngayketThuc}</td>
                                                    <td>${pgg.soLuong}</td>
                                                    <td>
                                                        <a href="/hoa-don/chon-phieu-giam-gia/${pgg.id}"
                                                           class="btn btn-warning btn-icon-text">Chọn</a>
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
                <form:form action="/hoa-don/them-san-pham" method="post"
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
                            <form action="/hoa-don/loc" method="post"
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
                                                        <a href="/hoa-don/nhap-so-luong/${ctsp.id}"
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
<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../js/scan-qr-2.js"></script>
</html>
