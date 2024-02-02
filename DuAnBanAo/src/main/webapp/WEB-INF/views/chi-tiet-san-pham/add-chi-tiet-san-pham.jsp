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
        <h4 style="text-align: center">Thêm thông tin sản phẩm</h4>
        <div class="form-group" style="margin-left: 200px;margin-right: 200px; ">
            <label for="val-username" class="form-label"><b>Tên sản phẩm</b> <span class="text-danger">*</span></label>
            <input type="text" class="form-control" id="val-username" name="val-username" style="width: 800px"
                   placeholder="Nhập tên sản phẩm ...">
        </div>
        <div class="row">
            <div class="card-body" style="margin-left: 190px">
                <label class="form-label"><b>Chất liệu</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body" style="margin-right: 100px">
                <label class="form-label"><b>Thương hiệu</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="card-body" style="margin-left: 190px">
                <label class="form-label"><b>Kích cỡ</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body" style="margin-right: 100px">
                <label class="form-label"><b>Cổ áo</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="card-body" style="margin-left: 190px">
                <label class="form-label"><b>Màu sắc</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Chọn chất liệu</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body" style="margin-right: 100px">
                <label class="form-label"><b>Chất liệu</b></label><span
                    class="text-danger">*</span></label>
                <div class="basic-dropdown">
                    <div class="dropdown">
                        <button type="button" class="btn btn-outline-dark dropdown-toggle" style="width: 250px; height: 40px" data-toggle="dropdown">Dropdown button</button>
                        <div class="dropdown-menu"><a class="dropdown-item" href="#">Link 1</a> <a class="dropdown-item" href="#">Link 2</a> <a class="dropdown-item" href="#">Link 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>




    </div>
</div>


</body>
</html>