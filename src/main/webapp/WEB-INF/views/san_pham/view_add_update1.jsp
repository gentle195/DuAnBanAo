<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .formAdd {
        width: 700px;
        height: 500px;
        background-color: #89CFF0;
        border-radius: 20px;
        color: #FFFFFF;
        font-family: "Nunito";
        margin: 0 auto;
    }

    .row.mb-3 {
        margin-top: 30px;
    }

    .button {
        line-height: 40px;
        background-color: #FFFFFF;
        color: black;
        font-weight: bold;
        width: 120px;
        height: 40px;
        border-radius: 10px;
        border: 0px;
        margin-top: 30px;
        margin: auto;
        display: block;
        text-align: center;
    }

    label {
        font-size: 17px;
    }

    .error {
        color: red;
        font-size: 15px;
        padding-left: 190px;
        padding-top: 15px;
    }
</style>
<div class="formAdd">
    <h1 style="text-align: center; font-weight: normal;padding-top: 20px;">Sửa sản phẩm</h1>
    <form:form modelAttribute="SP" method="post" action="${action1}" cssClass="form">

        <div class="row mb-3">
            <div class="col-sm-6">
                <form:input type="hidden" class="form-control" id="inputEmail3" path="id"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Mã sản phẩm:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="ma"/>
                <form:errors path="ma" cssStyle="color: crimson"></form:errors>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Tên sản phẩm:</label>
            </div>
            <div class="col-lg-8">
                <form:input type="text" class="form-control" path="ten"/>
                <form:errors path="ten" cssStyle="color: crimson"></form:errors>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label>Trạng thái:</label>
            </div>
            <div class="col-lg-8" style="margin-left: -20px">
                <form:radiobuttons items="${dsTrangThai}" path="trangThai" class="radioButton" cssStyle="margin-left: 20px;margin-right: 8px"/>
                <form:errors path="trangThai" cssStyle="color: crimson"></form:errors>
            </div>
        </div>
        <div class="row">
            <button class="button" type="submit" onclick="return confirm('Xác nhận')">Submit</button>
            <a class="button" href="/san-pham/hien-thi">Return</a>
        </div>


    </form:form>
</div>