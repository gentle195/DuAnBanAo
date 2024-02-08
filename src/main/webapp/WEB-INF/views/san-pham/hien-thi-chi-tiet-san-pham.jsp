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
                        <a class="nav-link" href="/san-pham/hien-thi" role="tab"
                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông
                            tin
                            sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Danh sách chi tiết sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/san-pham/them-chi-tiet-san-pham" role="tab"
                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm chi
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
                                            <%--                                            <th>Ảnh Sản Phẩm</th>--%>
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
                                                    <%--                                                <td>${ctsp.sten}</td>--%>
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
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/san-pham/chi-tiet-san-pham/update/${chiTiet.id}" method="post"
                           modelAttribute="chiTiet">
                    <div style="display: none">
                        <form:input class="form-control" path="ma"/>
                        <form:input class="form-control" path="sanPham"/>
                        <form:input class="form-control" path="thuongHieu"/>
                        <form:input class="form-control" path="mauSac"/>
                        <form:input class="form-control" path="kichCo"/>
                        <form:input class="form-control" path="coAo"/>
                        <form:input class="form-control" path="chatLieu"/>
                        <form:input class="form-control" path="ngayTao"/>
                        <form:input class="form-control" path="nguoiTao"/>
                    </div>
                    <div class="form-group">
                        <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                        <form:input class="form-control" path="soLuongTon"/>
                        <form:errors path="soLuongTon" cssStyle="color: red"></form:errors>
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
