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
                        <a class="nav-link" href="/nhan-vien/hien-thi" role="tab"
                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông
                            tin
                            nhân viên</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                           aria-controls="description" aria-selected="true">Thêm thông tin nhân viên</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/nhan-vien/hien-thi-delete" role="tab"
                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Nhân
                            viên ngừng hoạt động</a>
                    </li>
                </ul>
            </div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <form:form action="/nhan-vien/add" method="post" modelAttribute="nhanVien">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-group">
                                    <form:label class="form-label" path="hoTen">Họ tên:</form:label>
                                    <form:input class="form-control" path="hoTen"/>
                                    <form:errors path="hoTen" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="diaChi">Địa chỉ:</form:label>
                                    <form:input class="form-control" path="diaChi"/>
                                    <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="sdt">SĐT:</form:label>
                                    <form:input class="form-control" path="sdt"/>
                                    <form:errors path="sdt" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="CCCD">CCCD:</form:label>
                                    <form:input class="form-control" path="CCCD"/>
                                    <form:errors path="CCCD" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                                    <form:input class="form-control" path="ngaySinh" type="date" required="true"/>
                                    <form:errors path="ngaySinh" cssStyle="color: red"></form:errors>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                    <div class="form-control">
                                        <form:radiobutton path="gioiTinh" value="true" checked="true"/>Nam
                                        <form:radiobutton path="gioiTinh" value="false"
                                                          cssStyle="margin-left: 1cm"/>
                                        Nữ
                                    </div>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="email">Email:</form:label>
                                    <form:input class="form-control" path="email"/>
                                    <form:errors path="email" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="taiKhoan">Tài khoản:</form:label>
                                    <form:input class="form-control" path="taiKhoan"/>
                                    <form:errors path="taiKhoan" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <form:label class="form-label" path="matKhau">Mật khẩu:</form:label>
                                    <form:input class="form-control" path="matKhau"/>
                                    <form:errors path="matKhau" cssStyle="color: red"></form:errors>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-10">
                                            <form:label class="form-label" path="chucVu">Chức vụ:</form:label>
                                            <form:select path="chucVu" class="form-control" id="selectChucVu"
                                                         cssStyle="font-weight: bold; width: 100%">
                                                <option selected disabled>Chức vụ</option>
                                                <form:options items="${listChucVu}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <form:errors path="chucVu"/>
                                        </div>
                                        <div class="col-2">
                                            <br>
                                            <br>
                                            <a type="button" data-bs-toggle="modal"
                                               data-bs-target="#addChatLieu">
                                                <img src="../images/plus.png">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Thêm thông tin
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<button style="display: none" type="button" id="batmodalthemnhanvien" class="btn btn-primary" data-bs-toggle="modal"
        data-bs-target="#addChatLieu">
    Open modal
</button>
<div class="modal" id="addChatLieu">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body" style="color: black">
                <form:form action="/nhan-vien/add-chuc-vu" method="post" modelAttribute="chucVu" >
                    <div class="form-group">
                        <form:label class="form-label" path="ten">Tên chức vụ:</form:label>
                        <form:input class="form-control" path="ten" id="tenCV"/>
                        <label id="tenCV1" style="color: red"></label>
                        <form:errors path="ten" cssStyle="color: red"></form:errors>
                    </div>
                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary mr-2" id="btCV" onclick="return checkCV()">
                            Thêm thông tin
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectChucVu').select2();
    });
</script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    function checkCV() {
        var tenCV = document.getElementById("tenCV").value;

        if (tenCV.trim() === '') {
            document.getElementById("tenCV1").innerHTML = "Không để trống thông tin";
            document.getElementById("btCV").type = "button";
            return false;
        } else {
            document.getElementById('tenCV1').innerHTML = '';
            document.getElementById('btCV').type = 'summit';
            return true;
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
    <c:if test="${batmodalthemnhanvien==0}">
    document.getElementById('batmodalthemnhanvien').click();
    </c:if>
</script>
</html>
