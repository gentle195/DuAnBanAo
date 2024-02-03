<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<h1 style="text-align: center">Quản lý sản phẩm</h1>
</br>
<body>

<div class="card">

    <div class="card-body">

        <div class="row">

            <div class="col-6">
                <form:form action="/san-pham/search" modelAttribute="search" method="post">

                <div class="form-group" style="float: left">
                    <input type="text" class="form-control input-rounded" style="width: 300px; float: left; margin-left: 20px" placeholder="Nhập sản phẩm cần tìm kiếm...">
                    <button type="submit" class="btn btn-outline-primary" style="margin-left: 20px">Search</button>
                </div>
            </div>
            </form:form>
            <div class="col-6">
                <a href="/san-pham/view-add" style="float:right;" class="btn mb-1 btn-outline-primary">Thêm mới</a>
            </div>
        </div>


        <div class="bootstrap-carousel">
            <div class="carousel slide" data-ride="carousel">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã sản phẩm</th>
                            <th>Tên sản phẩm</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.content}" varStatus="index" var="sp">
                            <tr>
                                <th>${index.index+1}</th>
                                <td>${sp.ma}</td>
                                <td>${sp.ten}</td>


                                <c:if test="${sp.trangThai==1}">
                                    <td><span class="badge badge-success px-2">Đang bán</span>
                                    </td>
                                </c:if>
                                <c:if test="${sp.trangThai==0}">
                                    <td><span class="badge badge-danger px-2">Ngừng bán</span>
                                    </td>
                                </c:if>
                                <td>
                                    <a href="/san-pham/view-update/${sp.id}" class="btn btn-primary"><i
                                            class="bi bi-pencil-square"></i>
                                        <b>Chi tiết</b>
                                    </a>
                                    <a href="/chi-tiet-san-pham/list-san-pham/${sp.id}" class="btn btn-secondary"><i
                                            class="bi bi-eye-fill"></i></a>
                                </td>

                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div>
        <nav aria-label="Page navigation example">
            <ul class="pagination" style="justify-content: center">
                <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number-1}">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?p=${page.number+1}">Next</a></li>
            </ul>
        </nav>
    </div>

</body>
