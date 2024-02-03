<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<br>
<br>
<style>
    #myInput {
        height: 40px;
        border: 2px solid #37517E;
        border-radius: 15px;
        margin-bottom: 15px;
        float: right;
    }
</style>
<div class="container">
    <div class="row">
        <h3 class="text-center">Danh sách chi tiết sản phẩm </h3>

        <br>
        <br>
    </div>
    <div class="row">
        <div class="col-6 col-md-6 col-sm-6">
            <h3>
                <a class="btn btn-primary" href="/san-pham/hien-thi">Danh sách sản phẩm</a>
                <a href="/chi-tiet-san-pham/hien-thi" class=" btn btn-secondary">
                    <i class="bi bi-arrow-clockwise"></i></i>
                </a>
            </h3>
        </div>
        <div class="col-6 col-md-6 col-sm-6">
            <input class="form-control" placeholder="Tìm kiếm...." id="myInput"/>
            <%--            <form:form action="/chi-tiet-san-pham/search" modelAttribute="searchForm">--%>
            <%--                <div class="row">--%>
            <%--                    <div class="col-2 col-md-2 col-sm-3">--%>
            <%--                        <button class="btn" type="submit" style="background: #0d6efd; color: whitesmoke">Search</button>--%>
            <%--                    </div>--%>
            <%--                    <div class="col-6 col-md-6 col-sm-4">--%>
            <%--                        <form:input path="keyword" class="form-control" placeholder="Nhập mã hoặc loại tên sản phẩm"/>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </form:form>--%>
        </div>
        </br>
        <br>
        <br>

        <div class="row" style="padding-bottom: 30px">
            <div class="col-6">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <form:form modelAttribute="lg" action="/chi-tiet-san-pham/search-by-loaigiay">
                        <label class="form-label">Thương hiệu: </label>
                        <form:select type="text" id="searchName0" path="idLG" onchange="submit()">
                            <form:option value="">Tất cả</form:option>
                            <form:options items="${listLoaiGiay}" itemLabel="ten" itemValue="id"/>
                        </form:select>
                    </form:form>
                </div>

                <div class="col-lg-2 col-md-2 col-sm-2">
                    <form:form modelAttribute="searchChatLieu" action="/chi-tiet-san-pham/search-by-chatlieu">
                        <label class="form-label">Chất liệu: </label>
                        <form:select type="text" id="searchName4" path="idChatLieu" onchange="submit()">
                            <form:option value="">Tất cả</form:option>
                            <form:options items="${listChatLieu}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                    </form:form>
                </div>

                <div class="col-lg-2 col-md-2 col-sm-2">
                    <form:form action="/chi-tiet-san-pham/search-by-degiay" modelAttribute="searchDG">
                        <label class="form-label">Cổ áo: </label>
                        <form:select type="text" id="searchName3" path="idDe" onchange="submit()">
                            <form:option value="">Tất cả</form:option>
                            <form:options items="${listDeGiay}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                    </form:form>
                </div>
            </div>
            <div class="col-6">
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <form:form action="/chi-tiet-san-pham/search-by-kichco" modelAttribute="searchKC">
                        <label class="form-label">Kích cỡ: </label>
                        <form:select type="text" id="searchName1" path="idKC" onchange="submit()">
                            <form:option value="">Tất cả</form:option>
                            <form:options items="${listKichCo}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                    </form:form>
                </div>
                <div class="col-lg-2 col-md-2 col-sm-2">
                    <form:form action="/chi-tiet-san-pham/search-by-mausac" modelAttribute="searchFormByMau">

                        <label class="form-label">Màu sắc:</label>
                        <form:select type="text" id="searchName2" path="idMau" onchange="submit()">
                            <form:option value="">Tất cả</form:option>
                            <form:options items="${listMau}" itemLabel="ten" itemValue="id"/>
                        </form:select>

                    </form:form>
                </div>

                <div class="col-lg-2 col-md-2 col-sm-2">

                    <%--                <form:form method="post" action="/import" enctype="multipart/form-data">--%>
                    <%--                  <input type="file" name="file" accept=".xlsx, .xls"/>--%>
                    <%--                    <a href="/import" type="submit" class="btn btn-success"> <i class="bi bi-file-earmark-arrow-up-fill"></i></a>--%>
                    <%--                </form:form>--%>
                    <form:form action="/chi-tiet-san-pham/sort" modelAttribute="sortForm">
                        <label class="form-label">Sắp xếp:</label>
                        <form:select path="key" onchange="submit()" class="form-control">
                            <option value="0">---</option>
                            <form:option value="giaBan">Đơn giá</form:option>

                        </form:select>
                    </form:form>
                </div>
            </div>
        </div>
        <%-- Hiển thị thông báo lỗi nếu có --%>
        <% if (request.getAttribute("error") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <% if (request.getAttribute("success") != null) { %>
        <div style="color: green;">
            <%= request.getAttribute("success") %>
        </div>
        <% } %>


<%--        <form action="/chi-tiet-san-pham/upload" method="post" enctype="multipart/form-data">--%>
<%--            <label for="file">Chọn tệp Excel:</label>--%>
<%--            <input type="file" name="file" id="file" accept=".xls, .xlsx" required>--%>
<%--            <button type="submit">Tải lên</button>--%>
<%--        </form>--%>
        <c:if test="${not empty page.content}">

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên Sản phẩm</th>
                        <th>Thương hiệu</th>
                        <th>Màu sắc</th>
                        <th>Kích cỡ</th>
                        <th>Chất liệu</th>
                        <th>Cổ Áo</th>
                        <th>Giá bán</th>
                        <th>Số lượng</th>
                        <th>Trạng thái</th>
                        <th>Hình ảnh</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.content}" var="sp" varStatus="i">
                        <tr>
                            <th>${index.index+1}</th>
                            <td>${sp.sanPham.ten}</td>
                            <td>${sp.thuongHieu.ten}</td>
                            <td>${sp.mauSac.ten}</td>
                            <td>${sp.kichCo.ten}</td>
                            <td>${sp.chatLieu.ten}</td>
                            <td>${sp.coAo.ten}</td>
                            <td><fmt:formatNumber value="${sp.giaBan}" maxFractionDigits="20" type="number"/></td>
                            <td>${sp.soLuong}</td>
                            <c:if test="${sp.trangThai==1}">
                                <td><span class="badge badge-success px-2">Đang bán</span>
                                </td>
                            </c:if>
                            <c:if test="${sp.trangThai==0}">
                                <td><span class="badge badge-danger px-2">Ngừng bán</span>
                                </td>
                            </c:if>

                            <td>
                                <c:choose>
                                    <c:when test="${empty sp.hinhAnhs.tenanh}">
                                        <i class="bi bi-image"></i><span class="text-muted">no image</span>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="../../uploads/${sp.hinhAnh.tenAnh}" width="100px" height="100px"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>



                            <td>
                                <a href="/chi-tiet-san-pham/view-update-ctsp/${sp.id}" class="btn btn-warning"><i
                                        class="bi bi-pencil-square"></i></a>
                                <a href="/chi-tiet-san-pham/hinh-anh-sp/view-add/${sp.id}?idSP=${sp.sanPham.id}&idMS=${sp.mauSac.id}" class="btn btn-warning"><i
                                        class="bi bi-file-earmark-image"></i></a>
                            </td>

                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty page.content}">
            <td colspan="8" class="text-center">Không có sản phẩm.</td>
        </c:if>
        <div class="text-center">
            <nav aria-label="Page navigation text-center">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="/chi-tiet-san-pham/hien-thi?p=0">Previous</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chi-tiet-san-pham/hien-thi?p=${page.number-1}"><<</a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chi-tiet-san-pham/hien-thi?p=${page.number+1}">>></a></li>
                    <li class="page-item"><a class="page-link"
                                             href="/chi-tiet-san-pham/hien-thi?p=${page.totalPages-1}">Next</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<script>
    function submitForm() {
        document.getElementById('searchMauSac').submit();
    }
</script>
<script>
    $("#myInput").keyup(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $("#searchName").click(function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
</script>
<script src="../../../../../webapp/js/chi-tiet-san-pham.js"></script>
