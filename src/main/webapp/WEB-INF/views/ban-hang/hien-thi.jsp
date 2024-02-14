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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
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
<section style="text-align: center">
    <div class="row">
        <div class="col-9">
            <div class="card">
                <div class="card-body">
                    <div class="row outer-border border border-secondary"
                         style="height: 47px; background-color: #f5f5f5">
                        <div class="col-1">
                            <form action="/ban-hang/add-hoa-don" method="post"
                                  style="text-align: center;margin-top: 6px">
                                <button type="submit"><img src="../../../images/plus.png"></button>
                            </form>
                        </div>
                        <div class="col-11">
                            <div class="row">
                                <c:forEach items="${listHoaDon}" var="hd" varStatus="i">
                                    <div class="col-3">
                                        <div class="btn-group ">
                                            <a href="/ban-hang/thong-tin-hoa-don/${hd.id}" class="btn btn-info"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                               style="width: 120%;height: 47px"><label style="margin: 4px">Hóa Đơn
                                                Chờ ${i.index+1}</label></a>
                                            <a href="/ban-hang/remove/${hd.id}" class="btn btn-danger"
                                               style="width: 10px;height: 47px"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <label style="padding-top:5px;padding-right: 20px;margin-right: 20px"
                                                >X</label>
                                            </a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <c:if test="${HoaDon!=null}">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title" style="float: left">Danh sách hóa đơn chi tiết</h4>
                                    <%--            Tìm kiếm               --%>
                                <div class="row">
                                    <div class="col-3">
                                        <a class="btn btn-secondary"
                                           data-bs-toggle="modal"
                                           data-bs-target="#QRScan" style="float: right; height: 45px;color: black">Scan
                                            QR</a>
                                    </div>
                                    <div class="col-3">
                                        <a href="/ban-hang/modal-san-pham"
                                           class="btn btn-secondary"
                                           data-bs-toggle="modal"
                                           data-bs-target="#newSanPham" style="float: right; height: 45px">Thêm sản
                                            phẩm</a>
                                    </div>
                                    <div class="col-6">
                                        <form action="/san-pham/search-con-hoat-dong" method="post">
                                            <div class="input-group" style="width: 100%; float: right">
                                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                                       aria-label="Bạn tìm gì..." name="search">
                                                <div class="input-group-append">
                                                    <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                    <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <table class="table table-striped" style="color: black">
                                        <thead>
                                        <tr>
                                            <th>Ảnh</th>
                                            <th>Tên sản phẩm</th>
                                            <th colspan="5">Thông tin sản phẩm</th>
                                            <th>Đơn giá</th>
                                            <th>Số lượng</th>
                                            <th>Thành tiền</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listHDCT}" var="hdct">
                                            <tr>
                                                <td><img src="../../../uploads/${hdct.idCTSP.hinhAnh.duongDan1}"
                                                         width="40"
                                                         height="40"
                                                         style="border-radius:50% 50% 50% 50%"></td>
                                                <td>${hdct.idCTSP.sanPham.ten}</td>
                                                <td colspan="5">${hdct.idCTSP.chatLieu.ten} -
                                                        ${hdct.idCTSP.thuongHieu.ten} - ${hdct.idCTSP.coAo.ten} -
                                                        ${hdct.idCTSP.kichCo.ten} - ${hdct.idCTSP.mauSac.ten}</td>
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
                                                <td>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <c:if test="${HoaDon!= null}">
            <div class="col-3">
                <form:form action="/ban-hang/xac-nhan/${HoaDon.id}" modelAttribute="HoaDonCho" method="post">
                    <div class="col-12 grid-margin " style="color: black">
                        <div class="card shadow p-3 mb-5 bg-body-tertiary rounded">
                            <div class="card-body">
                                <h4 class="card-title">Thông Tin Hóa Đơn</h4>
                                <form class="form-sample">

                                    <div class="form-group row">
                                        <form:label class="col-sm-12 col-form-label"
                                                    path="ma">Mã Hóa Đơn:</form:label>
                                        <div class="col-sm-12">
                                            <form:input class="form-control" path="ma" readonly="true"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <form:label class="col-sm-12 col-form-label"
                                                    path="nhanVien">Nhân Viên:</form:label>
                                        <div class="col-sm-12">
                                                <%--                                        <input type="text" class="form-control"--%>
                                                <%--                                               value="${HoaDon.nhanVien.hoTen}" readonly>--%>
                                            <form:select path="nhanVien" class="form-control"
                                                         id="selectNhanVien">
                                                <c:forEach items="${listNhanVien}" var="nv">
                                                    <option value="${nv.id}">${nv.hoTen}</option>
                                                </c:forEach>
                                            </form:select>

                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <form:label class="col-sm-12 col-form-label"
                                                    path="khachHang">Khách Hàng:</form:label>
                                        <div class="col-sm-12">
                                            <div class="row">
                                                <div class="col-10">
                                                    <form:select path="khachHang" class="form-control"
                                                                 id="selectKhachHang">
                                                        <c:if test="${HoaDon.khachHang== null}">
                                                            <option selected disabled value="1">
                                                                Khách hàng
                                                            </option>
                                                        </c:if>
                                                        <c:forEach items="${listKhachHang}" var="kh">
                                                            <option value="${kh.id}">${kh.hoTen}
                                                                - ${kh.soDienThoai}</option>
                                                        </c:forEach>
                                                    </form:select>
                                                    <label id="nullKH1" style="color: red"></label>
                                                </div>
                                                <div class="col-2">
                                                    <a href="/ban-hang/modal-khach-hang"
                                                       class="btn"
                                                       data-bs-toggle="modal"
                                                       data-bs-target="#newKhachHang"><img
                                                            src="../../../images/plus.png" alt=""></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <form:label class="col-sm-12 col-form-label"
                                                    path="sdtNguoiNhan">Số điện thoại:</form:label>
                                        <div class="col-sm-12">
                                            <form:input class="form-control" path="sdtNguoiNhan" id="sdthd"/>
                                            <label id="sdthd1" style="color: red"></label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <form:label class="col-sm-12 col-form-label" path="tongTien"
                                                    for="tienCanThanhToan">Tổng tiền:</form:label>
                                        <div class="col-sm-12">
                                            <form:input class="form-control" path="tongTien" readonly="true"
                                                        value="${tong}"
                                                        id="tienCanThanhToan"/>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-sm-12 col-form-label">Tiền Khách Gửi:</label>
                                        <div class="col-sm-12">
                                            <input class="form-control" type="text" id="tienKhachDua"/>
                                            <label id="tienKhachDua1" style="color: red"></label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-12 col-form-label" for="ketQua">Tiền
                                            Thừa:</label>
                                        <div class="col-sm-12">
                                            <input class="form-control" type="text" id="ketQua" readonly/>
                                            <label id="ketQua1" style="color: red"></label>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <button type="submit" class="btn btn-primary"
                                                    id="bthd" onclick="return checkhd()">Thanh toán
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </c:if>
    </div>
</section>
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
        $('#selectKhachHang').select2();
    });
</script>
<script>
    document.getElementById("tienKhachDua").addEventListener("keyup", function () {
        tinhTienThua();
    });

    function tinhTienThua() {
        var tongTien = parseFloat(document.getElementById("tienCanThanhToan").value);
        var tienKhachDua = parseFloat(document.getElementById("tienKhachDua").value);
        var tienThua = tienKhachDua - tongTien;

        var ketQuaElement = document.getElementById("ketQua");
        if (tienThua >= 0) {
            ketQuaElement.value = tienThua.toFixed(2);
        } else {
            ketQuaElement.value = "Khách đưa không đủ tiền.";
        }
    }

    function checkhd() {
        var sdthd = document.getElementById("sdthd").value;
        var tien = document.getElementById("tienKhachDua").value;
        var ketQua = document.getElementById("ketQua").value;
        var nullKH = document.getElementById("selectKhachHang").value;
        var tongTien = document.getElementById("tienCanThanhToan").value;
        var regex = /^0\d{9}$/;

        if (!regex.test(sdthd)) {
            document.getElementById("sdthd1").innerHTML = "SDT phải 10 số và bắt đầu là số 0";
            document.getElementById("bthd").type = "button";
            return false;
        } else {
            document.getElementById("sdthd1").innerHTML = "";
            if (tien.trim() === '') {
                document.getElementById("tienKhachDua1").innerHTML = "Tiền khách đưa không được để trống";
                document.getElementById("bthd").type = "button";
                return false;
            } else {
                if (tien % 1 !== 0 || tien < 0) {
                    document.getElementById('tienKhachDua1').innerHTML = 'Tiền khách đưa phải là số nguyên dương';
                    document.getElementById('bthd').type = 'button';
                    return false
                } else if ((parseFloat(tien) - parseFloat(toString(tongTien))) > 0) {
                    document.getElementById('tienKhachDua1').innerHTML = 'Tiền khách đưa đang quá lớn';
                    document.getElementById('bthd').type = 'button';
                    return false;
                } else {
                    document.getElementById('tienKhachDua1').innerHTML = '';
                    if (ketQua % 1 !== 0 && ketQua < 0 || ketQua == 'Khách đưa không đủ tiền.') {
                        document.getElementById('bthd').type = 'button';
                        return false;
                    } else {
                        if (nullKH == 1) {
                            document.getElementById('nullKH1').innerHTML = 'Chưa chọn khách hàng';
                            document.getElementById('bthd').type = 'button';
                            return false;
                        } else {
                            document.getElementById('nullKH1').innerHTML = '';
                            document.getElementById('bthd').type = 'summit';
                            return true;
                        }
                    }

                }
            }
        }

    }
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
<script src="../../js/scan-qr.js"></script>
</html>
