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
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <div>
                <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">

                    <li class="nav-item">
                        <a class="nav-link" href="/san-pham/hien-thi" role="tab">Thông
                            tin
                            sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Danh sách chi tiết sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/san-pham/them-chi-tiet-san-pham" role="tab">Thêm chi
                            tiết
                            sản phẩm</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <div>
                                    <form:form method="post" modelAttribute="sanPham">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group">
                                                    <form:label class="form-label" path="ma">Mã sản phẩm:</form:label>
                                                    <form:input class="form-control" path="ma" readonly="true"/>
                                                    <form:errors path="ma" cssStyle="color: red"></form:errors>
                                                </div>
                                                <div class="form-group">
                                                    <form:label class="form-label" path="ten">Tên sản phẩm:</form:label>
                                                    <form:input class="form-control" path="ten" readonly="true"/>
                                                    <form:errors path="ten" cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <div class="form-group">
                                                    <form:label class="form-label" path="ngayTao">Ngày Tạo:</form:label>
                                                    <form:input class="form-control" path="ngayTao" type="date"
                                                                readonly="true"/>
                                                    <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
                                                </div>
                                                <div class="form-group">
                                                    <form:label class="form-label" path="ngaySua">Ngày Sửa:</form:label>
                                                    <form:input class="form-control" path="ngaySua" type="date"
                                                                readonly="true"/>
                                                    <form:errors path="ngaySua" cssStyle="color: red"></form:errors>
                                                </div>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                                <form action="/san-pham/chi-tiet-san-pham/loc" method="post"
                                      onsubmit="return checkLoc()">
                                    <div class="row">
                                        <div class="col-3">
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
                                        <div class="col-2">
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
                                        <div class="col-2">
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
                                        <div class="col-2">
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
                                        <div class="col-3">
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
                                        <div class="col-12" style="text-align: center">
                                            <button type="submit" class="btn btn-primary mr-2"
                                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                Lọc Thông Tin
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <br><br>
                                <h4 class="card-title" style="float: left">Danh sách chi tiết sản phẩm</h4>
                                <%--            Tìm kiếm               --%>
                                <%--                                <form action="/san-pham/search-con-hoat-dong" method="post">--%>
                                <%--                                    <div class="input-group" style="width: 30%; float: right">--%>
                                <%--                                        <input type="text" class="form-control" placeholder="Bạn tìm gì..."--%>
                                <%--                                               aria-label="Bạn tìm gì..." name="search">--%>
                                <%--                                        <div class="input-group-append">--%>
                                <%--                                            <button class="btn btn-sm btn-primary" type="submit">Search</button>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                </form>--%>
                                <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered zero-configuration"
                                           style="color: black;width: 2000px">
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
                                            <th>Tình Trạng</th>
                                            <th>Ngày Tạo</th>
                                            <th>Ngày Sửa</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <br>
                                        <br>
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
                                                    <c:if test="${ctsp.trangThai==0}">Còn hàng</c:if>
                                                    <c:if test="${ctsp.trangThai==1}">Hết hàng</c:if>
                                                    <c:if test="${ctsp.trangThai==2}">Ngừng kinh doanh</c:if>
                                                </td>
                                                <td>${ctsp.ngayTao}</td>
                                                <td>${ctsp.ngaySua}</td>
                                                <td>
                                                    <a href="/san-pham/chi-tiet-san-pham/detail/${ctsp.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Detail</a>
                                                    <a href="/san-pham/chi-tiet-san-pham/view-update/${ctsp.id}"
                                                       class="btn btn-warning btn-icon-text"
                                                       tabindex="-1"
                                                       role="button"
                                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                        <i class="ti-file btn-icon-prepend"></i>
                                                        Update</a>
                                                    <c:if test="${ctsp.trangThai==1}">
                                                        <a href="/san-pham/chi-tiet-san-pham/delete/${ctsp.id}"
                                                           class="btn btn-danger btn-icon-text"
                                                           tabindex="-1"
                                                           role="button"
                                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                            <i class="ti-reload btn-icon-prepend"></i>
                                                            Status</a>
                                                    </c:if>
                                                    <a href="/san-pham/chi-tiet-san-pham/show-qr/${ctsp.id}"
                                                       class="btn btn-info btn-icon-text"
                                                       data-bs-toggle="modal"
                                                       data-bs-target="#showQR_${ctsp.id}">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                             fill="currentColor" class="bi bi-qr-code-scan"
                                                             viewBox="0 0 16 16">
                                                            <path d="M0 .5A.5.5 0 0 1 .5 0h3a.5.5 0 0 1 0 1H1v2.5a.5.5 0 0 1-1 0v-3Zm12 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0V1h-2.5a.5.5 0 0 1-.5-.5ZM.5 12a.5.5 0 0 1 .5.5V15h2.5a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5v-3a.5.5 0 0 1 .5-.5Zm15 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H15v-2.5a.5.5 0 0 1 .5-.5ZM4 4h1v1H4V4Z"/>
                                                            <path d="M7 2H2v5h5V2ZM3 3h3v3H3V3Zm2 8H4v1h1v-1Z"/>
                                                            <path d="M7 9H2v5h5V9Zm-4 1h3v3H3v-3Zm8-6h1v1h-1V4Z"/>
                                                            <path d="M9 2h5v5H9V2Zm1 1v3h3V3h-3ZM8 8v2h1v1H8v1h2v-2h1v2h1v-1h2v-1h-3V8H8Zm2 2H9V9h1v1Zm4 2h-1v1h-2v1h3v-2Zm-4 2v-1H8v1h2Z"/>
                                                            <path d="M12 9h2V8h-2v1Z"/>
                                                        </svg>
                                                        QR Code</a>
                                                    <div class="modal fade" id="showQR_${ctsp.id}" tabindex="-1"
                                                         aria-labelledby="exampleModalLabelQR"
                                                         aria-hidden="true" data-backdrop="static">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h2 class="modal-title" id="exampleModalLabelQR">QR
                                                                        Code</h2>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <table class="table" id="table_id">
                                                                        <tbody id="listCTSP_${ctsp.id}"
                                                                               class="ctsp_search">
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary"
                                                                            data-bs-dismiss="modal">Close
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
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
<button style="display: none" type="button" id="batmodaldetailchitiet" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#detailCoAo">
    Open modal
</button>
<div class="modal" id="detailCoAo">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form method="post" modelAttribute="chiTiet">
                    <div class="row">
                        <div class="col-6">
                            <div class="form-group">
                                <label class="form-label">Tên sản phẩm:</label>
                                <input class="form-control" readonly="true" value="${tenSP}"/>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Tên thương hiệu:</label>
                                <input class="form-control" readonly="true" value="${tenTH}"/>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Tên chất liệu:</label>
                                <input class="form-control" readonly="true" value="${tenCL}"/>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Tên cổ áo:</label>
                                <input class="form-control" readonly="true" value="${tenCA}"/>
                            </div>
                            <div class="form-group">
                                <label class="form-label">Tên màu sắc:</label>
                                <input class="form-control" readonly="true" value="${tenMS}"/>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                                <form:input class="form-control" path="giaBan" readonly="true"/>
                                <form:errors path="giaBan" cssStyle="color: red"></form:errors>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="form-group">
                                <label class="form-label">Tên kích cỡ:</label>
                                <input class="form-control" readonly="true" value="${tenKC}"/>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ngayTao">Ngày tạo:</form:label>
                                <form:input class="form-control" path="ngayTao" type="date" readonly="true"/>
                                <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ngaySua">Ngày sửa:</form:label>
                                <form:input class="form-control" path="ngaySua" type="date" readonly="true"/>
                                <form:errors path="ngaySua" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="nguoiTao">Người tạo:</form:label>
                                <form:input class="form-control" path="nguoiTao" type="date" readonly="true"/>
                                <form:errors path="nguoiTao" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="nguoiSua">Người sửa:</form:label>
                                <form:input class="form-control" path="nguoiSua" type="date" readonly="true"/>
                                <form:errors path="nguoiSua" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                                <form:input class="form-control" path="soLuongTon" readonly="true"/>
                                <form:errors path="soLuongTon" cssStyle="color: red"></form:errors>
                            </div>
                        </div>
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
<button style="display: none" type="button" id="batmodalupdatechitiet" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#updateCoAo">
    Open modal
</button>
<div class="modal" id="updateCoAo">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/chi-tiet-san-pham/update/${chiTiet.id}" method="post"
                           modelAttribute="chiTiet" enctype="multipart/form-data">
                    <div style="display: none">
                        <form:input class="form-control" path="ma"/>
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
                    <div class="col-12">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-3">
                                    <div align="center">
                                        <div style="display: none">
                                            <input style="" type="text" name="checkanh1" value="cu1" id="cucheck1">
                                            <br>
                                        </div>
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi1">
                                                <img id="preview-anh1-2" class="preview-image"
                                                     src="../../../uploads/${chiTiet.hinhAnh.duongDan1}" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH 1
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh1s" accept="image/jpeg, image/png"
                                                       id="anhmoi1">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div align="center">
                                        <div style="display: none">
                                            <input style="" type="text" name="checkanh2" value="cu2" id="cucheck2">
                                            <br>
                                        </div>
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi2">
                                                <img id="preview-anh2-2" class="preview-image"
                                                     src="../../../uploads/${chiTiet.hinhAnh.duongDan2}" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH 2
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh2s" accept="image/jpeg, image/png"
                                                       id="anhmoi2">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div align="center">
                                        <div style="display: none">
                                            <input style="" type="text" name="checkanh3" value="cu3" id="cucheck3">
                                            <br>
                                        </div>
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi3">
                                                <img id="preview-anh3-2" class="preview-image"
                                                     src="../../../uploads/${chiTiet.hinhAnh.duongDan3}" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH 3
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh3s" accept="image/jpeg, image/png"
                                                       id="anhmoi3">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div align="center">
                                        <div style="display: none">
                                            <input style="" type="text" name="checkanh4" value="cu4" id="cucheck4">
                                            <br>
                                        </div>
                                        <div align="center">
                                            <br>
                                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                                   for="anhmoi4">
                                                <img id="preview-anh4-2" class="preview-image"
                                                     src="../../../uploads/${chiTiet.hinhAnh.duongDan4}" alt=""
                                                     width="100%" height="100%"
                                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">
                                                <br><br>
                                                ẢNH 4
                                            </label>
                                            <br>
                                            <div style="display: none">
                                                <input type="file" name="anh4s" accept="image/jpeg, image/png"
                                                       id="anhmoi4">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                        <form:input class="form-control" path="soLuongTon"/>
                        <form:errors path="soLuongTon" cssStyle="color: red"></form:errors>
                        <label style="color: red">${thongBaoSoLuong}</label>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                        <form:input class="form-control" path="giaBan"/>
                        <form:errors path="giaBan" cssStyle="color: red"></form:errors>
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
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
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
        $('div[id^="showQR_"]').on('show.bs.modal', async function (e) {
            const id = e.currentTarget.id.split("_")[1];
            const url = "http://localhost:8080/san-pham/chi-tiet-san-pham/show-qr/" + id;
            console.log(id, url);
            try {
                const resp = await fetch(url);
                const data = await resp.json();
                console.log(data)
                let html = '';
                for (let i = 0; i < data.length; i++) {
                    const ctsp = data[i];
                    const tr = `
                <tr>
                    <td align="center"><img src="../../../maqr/` + ctsp.maQR + `" width="300" height="300"></td>
                </tr>
            `;
                    html += tr;
                }

                $("#listCTSP_" + id).html(html);
            } catch (err) {
                console.error(err)
            }
        });
    });
</script>
<script>
    const imageInput1 = document.getElementById('anhmoi1');

    const previewAnh12 = document.getElementById('preview-anh1-2');

    imageInput1.addEventListener('change', function () {
        const file = imageInput1.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh12.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh12.src = '';
        }
        document.getElementById('cucheck1').value = 'moi1';
    });
    const imageInput2 = document.getElementById('anhmoi2');

    const previewAnh22 = document.getElementById('preview-anh2-2');

    imageInput2.addEventListener('change', function () {
        const file = imageInput2.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh22.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh22.src = '';
        }
        document.getElementById('cucheck2').value = 'moi2';
    });
    const imageInput3 = document.getElementById('anhmoi3');

    const previewAnh32 = document.getElementById('preview-anh3-2');

    imageInput3.addEventListener('change', function () {
        const file = imageInput3.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh32.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh32.src = '';
        }
        document.getElementById('cucheck3').value = 'moi3';
    });
    const imageInput4 = document.getElementById('anhmoi4');

    const previewAnh42 = document.getElementById('preview-anh4-2');

    imageInput4.addEventListener('change', function () {
        const file = imageInput4.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewAnh42.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewAnh42.src = '';
        }
        document.getElementById('cucheck4').value = 'moi4';
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
    <c:if test="${batmodaldetailchitiet==0}">
    document.getElementById('batmodaldetailchitiet').click();
    </c:if>
</script>
<script>
    <c:if test="${batmodalupdatechitiet==0}">
    document.getElementById('batmodalupdatechitiet').click();
    </c:if>
</script>
</html>
