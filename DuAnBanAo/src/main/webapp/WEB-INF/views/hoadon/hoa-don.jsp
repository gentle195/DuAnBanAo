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
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
</head>
<body>
<div class="col-md-12">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Nav Pills Tabs</h4>
            <ul class="nav nav-pills mb-3">
                <li class="nav-item"><a href="#navpills-1" class="nav-link active" data-toggle="tab"
                                        aria-expanded="false">Tất cả</a>
                </li>
                <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab" aria-expanded="false">Hoá
                    đơn chờ</a>
                </li>
                <li class="nav-item"><a href="#navpills-3" class="nav-link" data-toggle="tab" aria-expanded="true">Tab
                    Three</a>
                </li>
            </ul>
            <div class="tab-content br-n pn">
                <div id="navpills-1" class="tab-pane active">
                    <div class="row align-items-center">
                        <div class="col-sm-6 col-md-8 col-xl-10">
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                                aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                                helvetica.</p>
                            <p>Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui
                                irure terry richardson ex squid.</p>
                        </div>
                    </div>
                </div>
                <div id="navpills-2" class="tab-pane">
                    <div class="row align-items-center">
                        <div class=" col-xl-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Status</th>
                                                <th>Date</th>
                                                <th>Price</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th>1</th>
                                                <td>Kolor Tea Shirt For Man</td>
                                                <td><span class="badge badge-primary px-2">Sale</span>
                                                </td>
                                                <td>January 22</td>
                                                <td class="color-primary">$21.56</td>
                                            </tr>
                                            <tr>
                                                <th>2</th>
                                                <td>Kolor Tea Shirt For Women</td>
                                                <td><span class="badge badge-danger px-2">Tax</span>
                                                </td>
                                                <td>January 30</td>
                                                <td class="color-success">$55.32</td>
                                            </tr>
                                            <tr>
                                                <th>3</th>
                                                <td>Blue Backpack For Baby</td>
                                                <td><span class="badge badge-success px-2">Extended</span>
                                                </td>
                                                <td>January 25</td>
                                                <td class="color-danger">$14.85</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="navpills-3" class="tab-pane">
                    <div class="row align-items-center">
                        <div class="col-sm-6 col-md-8 col-xl-10">
                            <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown
                                aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan
                                helvetica.</p>
                            <p>Reprehenderit butcher retro keffiyeh dreamcatcher synth. Cosby sweater eu banh mi, qui
                                irure terry richardson ex squid.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>