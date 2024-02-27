let scanner = new Instascan.Scanner({video: document.getElementById('video')});
Instascan.Camera.getCameras().then(function (cameras) {
    if (cameras.length > 0) {
        scanner.start(cameras[0]);
    } else {
        alert('Cameras found');
    }
}).catch(function (e) {
    console.error(e);
});

scanner.addListener("scan", function (qrcode) {
        // alert("You Qr is : " + qrcode.toLocaleString("vi-VN"));

        // var fields = qrcode.split('|');

        // // Extract the fields
        // var nationalId = fields[0];
        // var name = fields[2];
        // var birthday = fields[3];
        // var gender = fields[4];
        // var address = fields[5];
        //
        // // Display the fields
        // alert("Số CCCD: " + nationalId);
        // alert("Họ và tên: " + name.toLocaleString("vi-VN"));
        // alert("Ngày sinh: " + birthday.toLocaleString("vi-VN");
        // alert("Giới tính: " + gender.toLocaleString("vi-VN"));
        // alert("Địa chỉ: " + address.toLocaleString("vi-VN"));

    // Chuyển người dùng đến trang controller khi quét thành công
    window.location.href =  `/nhan-vien/quet-qr/${qrcode}`;

    // var issueDate = fields[6];
    // var ngay = birthday.substring(0, 2);
    // var thang = birthday.substring(2, 4);
    // var nam = birthday.substring(4, birthday.length());
    // var ngayThangNam = nam + "-" + thang + "-" + ngay;
    // alert("Issue Date: " + issueDate.toLocaleString("vi-VN"));

});



