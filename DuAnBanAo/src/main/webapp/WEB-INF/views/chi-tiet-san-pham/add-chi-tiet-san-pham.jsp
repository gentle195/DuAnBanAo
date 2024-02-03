<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/font-awesome.min.css">

    <title>Document</title>

</head>
<body>
<h3><b>Sản phẩm</b> / Thêm sản phẩm</h3>
<div class="card">

    <div class="card-body">
        <form:form modelAttribute="SP" method="post" action="/san-pham/add" cssClass="form">

        <h4 style="text-align: center">Thêm thông tin sản phẩm</h4>
        <div class="form-group" style="margin-left: 200px;margin-right: 200px; ">
            <label for="val-username" class="form-label"><b>Mã sản phẩm</b> <span class="text-danger">*</span></label>
            <form:input type="text" path="ma" class="form-control" id="val-username" name="val-username" style="width: 800px"
                        placeholder="Nhập mã sản phẩm ..." />
        </div>
        <div class="form-group" style="margin-left: 200px;margin-right: 200px; ">
            <label for="val-username" class="form-label"><b>Tên sản phẩm</b> <span class="text-danger">*</span></label>
            <form:input type="text" path="ten" class="form-control" id="val-username1" name="val-username" style="width: 800px"
                        placeholder="Nhập tên sản phẩm ..."/>
        </div>
        <div class="form-group" style="margin-left: 200px;margin-right: 200px; ">
            <label for="val-username" class="form-label"><b>Mô tả</b> <span class="text-danger">*</span></label>
            <form:input type="text" path="moTa" class="form-control" id="val-username2" name="val-username" style="width: 800px"
                        placeholder="Nhập mô tả ..."/>
        </div>
        <div class="form-group" style="margin-left: 200px;margin-right: 200px; ">
            <label for="val-username" class="form-label"><b>Trạng thái</b> <span class="text-danger">*</span></label>
            <div class="basic-form">

                    <div class="form-group">

                            <form:radiobutton  path="trangThai" value="1" checked="true" label="Hoạt động"/>
                        <br>

                            <form:radiobutton  path="trangThai"  value="0" label="Ngừng hoạt động"/>

                    </div>

            </div>
        </div>
        <button type="submit" class="btn mb-1 btn-primary" style="margin-left: 600px">Add</button>
            <button class="button" type="submit" onclick="return confirm('Xác nhận')">Submit</button>
        </form:form>

    <%--        <div class="row">--%>
        <%--            <div class="card-body" style="margin-left: 190px">--%>
        <%--                <label class="form-label"><b>Chất liệu</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="card-body" style="margin-right: 100px">--%>
        <%--                <label class="form-label"><b>Thương hiệu</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="row">--%>
        <%--            <div class="card-body" style="margin-left: 190px">--%>
        <%--                <label class="form-label"><b>Kích cỡ</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="card-body" style="margin-right: 100px">--%>
        <%--                <label class="form-label"><b>Cổ áo</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="row">--%>
        <%--            <div class="card-body" style="margin-left: 190px">--%>
        <%--                <label class="form-label"><b>Màu sắc</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="card-body" style="margin-right: 100px">--%>
        <%--                <label class="form-label"><b>Chất liệu</b></label><span--%>
        <%--                    class="text-danger">*</span></label>--%>
        <%--                <div class="basic-dropdown">--%>
        <%--                    <div class="dropdown">--%>
        <%--                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>--%>
        <%--                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>


    </div>
</div>


</body>
</html>