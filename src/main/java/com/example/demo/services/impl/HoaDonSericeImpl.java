package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.repositories.PhieuGiamGiaRepository;
import com.example.demo.services.HoaDonSerice;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonSericeImpl implements HoaDonSerice {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public Page<HoaDon> hoaDonAll(Pageable pageable) {
        return hoaDonRepository.hoaDonAll(pageable);
    }

    @Override
    public HoaDon findById(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public Page<HoaDon> hoaDonHuy(Pageable pageable) {
        return hoaDonRepository.hoaDonHuy(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonChoXacNhan(Pageable pageable) {
        return hoaDonRepository.hoaDonChoXacNhan(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonDaXacNhan(Pageable pageable) {
        return hoaDonRepository.hoaDonDaXacNhan(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonChoGiaoHang(Pageable pageable) {
        return hoaDonRepository.hoaDonChoGiaoHang(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonDangVanChuyen(Pageable pageable) {
        return hoaDonRepository.hoaDonDangVanChuyen(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonHoanThanh(Pageable pageable) {
        return hoaDonRepository.hoaDonHoanThanh(pageable);
    }

    @Override
    public List<HoaDon> hoaDonCho() {
        return hoaDonRepository.hoaDonCho();
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon update(UUID id, HoaDon hoaDon) {
        if (id != null) {
            HoaDon hoaDonUpdate = hoaDonRepository.findById(id).orElse(null);
            if (hoaDonUpdate != null) {
                BeanUtils.copyProperties(hoaDon, hoaDonUpdate);
                hoaDonRepository.save(hoaDonUpdate);
            }
        }
        return null;
    }

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAllHoaDon();
    }


//    @Override
//    public Page<HoaDon> hoaDonDaGiaoHang(Pageable pageable) {
//        return hoaDonRepository.hoaDonDaGiaoHang(pageable);
//    }

//    @Override
//    public Page<HoaDon> hoaDonDaThanhToan(Pageable pageable) {
//        return hoaDonRepository.hoaDonChoThanhToan(pageable);
//    }

//    @Override
//    public Page<HoaDon> hoaDonChoThanhToan(Pageable pageable) {
//        return hoaDonRepository.hoaDonDaThanhToan(pageable);
//    }

    @Override
    public ResponseEntity<byte[]> generatePdfDonTaiQuay(UUID hoaDonId) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            // Tạo nội dung HTML cho hóa đơn (thay đổi cho phù hợp với mẫu HTML của bạn)
            StringBuilder htmlContentBuilder = new StringBuilder();
            htmlContentBuilder.append("<html><head>");
            htmlContentBuilder.append("<meta charset=\"UTF-8\">");
            htmlContentBuilder.append("<title>Đơn hàng online</title>");
            htmlContentBuilder.append("<style>");
            htmlContentBuilder.append("body {\n" +
                    "    font-family: Arial, sans-serif;\n" +
                    "    line-height: 1.6;\n" +
                    "    background-color: #f9f9f9;\n" +
                    "    padding: 20px;\n" +
                    "    width: 1000px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    color: #338dbc;" +
                    "    text-align: center;\n" +
                    "    font-size: 24px;\n" +
                    "    margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;" +
                    "}\n" +
                    "\n" +
                    "h3 {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;\n" +
                    "    text-align: center;" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "    border-bottom: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "th {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "tr:hover {\n" +
                    "    background-color: #f5f5f5;\n" +
                    "}\n" +
                    "\n" +
                    "h1.order-details-title {\n" +
                    "    margin-top: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "p.footer-text {\n" +
                    "    margin-top: 30px;\n" +
                    "    text-align: center;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "    min-width: 1300px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    "\n" +
                    ".header {\n" +
                    "    text-align: center;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    ".footer {\n" +
                    "    text-align: center;\n" +
                    "    margin-top: 50px;\n" +
                    "    padding-top: 20px;\n" +
                    "    border-top: 1px solid #ddd;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".logo {\n" +
                    "    width: 100px;\n" +
                    "    height: auto;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table th, .product-table td {\n" +
                    "    text-align: left;\n" +
                    "}\n" +
                    "\n" +
                    ".total-amount {\n" +
                    "    font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    "/* Add more styles as needed */\n");
            htmlContentBuilder.append("</style>");
            htmlContentBuilder.append("<body>");

            //Các nội dung của html
            htmlContentBuilder.append("<h1>").append("GPhoneS Store").append("</h1>");

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            // Thêm thông tin đơn hàng

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(dateFormat.format(hoaDon.getNgayTao())).append("</p>");
            htmlContentBuilder.append("<p>Khách hàng đặt: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
            htmlContentBuilder.append("<p>Người nhận: ").append(hoaDon.getTenNguoiNhan()).append("</p>");
            htmlContentBuilder.append("<p>Số điện thoại người nhận: ").append(hoaDon.getSdtNguoiNhan()).append("</p>");
            htmlContentBuilder.append("<p>Loại hóa đơn: ").append(hoaDon.getLoaiHoaDon() == 0 ? "Hóa đơn tại quầy" : "Hóa đơn Online").append("</p>");
            htmlContentBuilder.append("<p>Hình thức thanh toán: ").append(hoaDon.getHinhThucThanhToan() == 0 ? "Tiền mặt" : "Chuyển khoản").append("</p>");
            htmlContentBuilder.append("<p>Trạng thái đơn: Đã thanh toán</p>");
            htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");


            String formattedTongTienDonHang = numberFormat.format(hoaDon.getTongTien());
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h3>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Thành tiền</th></tr>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.hoaDonChiTietAll(hoaDonId)) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getIdCTSP().getSanPham().getTen())
                        .append(" (").append(hoaDonChiTiet.getIdCTSP().getMauSac().getTen())
                        .append("/").append(hoaDonChiTiet.getIdCTSP().getCoAo().getTen())
                        .append("/").append(hoaDonChiTiet.getIdCTSP().getChatLieu().getTen())
                        .append("/").append(hoaDonChiTiet.getIdCTSP().getKichCo().getTen())
                        .append("/").append(hoaDonChiTiet.getIdCTSP().getThuongHieu().getTen())
                        .append(")")
                        .append("</td>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            htmlContentBuilder.append("<p>Phí Ship: ").append(hoaDon.getPhiShip()).append("</p>");
//            DoiTra dt = doiTraRepository.getDoiTraByHoaDon(hoaDonId);
//            if (dt != null) {
//                if (dt.getTinhTrang() == 2) {
//                    List<DoiTraChiTiet> list = doiTraChiTietRepository.doiTraChiTiet(hoaDonId);
//                    if (list.size() != 0) {
//                        htmlContentBuilder.append("<h3>").append("Danh sách sản phẩm đổi hàng").append("</h3>");
//                        htmlContentBuilder.append("<table>");
//                        htmlContentBuilder.append("<tr><th>Sản phẩm cần đổi</th><th>Sản phẩm đổi mới/hoàn trả</th><th>Lý do đổi trả</th></tr>");
//                        for (DoiTraChiTiet doiTraChiTiet : list) {
//                            NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//                            String fomatTienSanPham = fomatTien.format(doiTraChiTiet.getDonGia());
//                            String fomatTienSanPhamCu = fomatTien.format(doiTraChiTiet.getHoaDonChiTiet().getDonGia());
//                            htmlContentBuilder.append("<tr>");
//                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getSanPham().getTen())
//                                    .append(" (").append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getHoaDonChiTiet().getImei().getSoImei()).append(")").append("</td>");
//                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getImei().getChiTietSanPham().getSanPham().getTen())
//                                    .append(" (").append(doiTraChiTiet.getImei().getChiTietSanPham().getChip().getTen()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getMauSac().getTen()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getRam().getDungLuong()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getImei().getChiTietSanPham().getRom().getDungLuong()).append("/").append("<br>")
//                                    .append(doiTraChiTiet.getImei().getSoImei()).append(")").append("</td>");
//                            htmlContentBuilder.append("<td>").append(doiTraChiTiet.getLyDo()).append("</td>");
//                            htmlContentBuilder.append("</tr>");
//                        }
//                        htmlContentBuilder.append("</table>");
//                    }
//                }
//            }
            htmlContentBuilder.append("<h3>Xin chân thành cảm ơn sự ủng hộ của bạn!</h3>");
            htmlContentBuilder.append("</body></html>");
            // Gọi phương thức tạo file PDF từ nội dung HTML, sử dụng thư viện iText
            byte[] pdfBytes = createPdfFromHtml(htmlContentBuilder);
            // Thiết lập thông tin phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don_" + hoaDonId + ".pdf");
            // Trả về file PDF dưới dạng byte[]
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }

    private byte[] createPdfFromHtml(StringBuilder htmlContent) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent.toString(), outputStream, converterProperties);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
