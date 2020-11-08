var taocongviec = taocongviec || {}
var nhanVienLamViec = []
taocongviec.listphongban = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/phongban/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                // phongBanList = data;
                $('#phongBanId').html("<option disabled selected>--/--</option>");
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    $('#phongBanId').append(
                        "<option value='" + value.mpb + "' >" + value.tenPB + "</option>"
                    );
                });
            },
            error: function (e) {
                console.log(e);
                console.log(e.message);
            }
        }).done(function (data) {
        // If successful
        console.log(data);
    })
}

taocongviec.phongban = function (element) {
    let idPhongBan = $(element).val();
    console.log(idPhongBan);
    $.ajax(
        {
            url: urlPathHost + '/api/nhanvien/viewuser/' + idPhongBan,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                $('#nhanVienId').html("");
                // nhanVienList = data;
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    if (nhanVienLamViec.indexOf(value.mnv) == -1) {
                        $('#nhanVienId').append(
                            "<option value='" + value.mnv + "'>" + value.fullName + "</option>"
                        );
                    }

                });
            },
            error: function (e) {
                console.log(e.message);
            }
        });
}

var inNhavien = [];
taocongviec.showNhanVien = function () {
    taocongviec.phongban($('#phongBanId'));
    let t = $('#dataTable').DataTable();
    t.clear().draw();
    $.each(inNhavien, function (index, value) {
        t.row.add([
            value[1],
            value[2],
            `<input type='radio' name='iMain' value='${value[0]}' checked>`,
            `<span onclick='taocongviec.xoa(${index})'><i class='far fa-trash-alt' ></i></span>`
        ]).draw();

    });
}

taocongviec.themtatca = function () {
    let idPhongBan = $('#phongBanId').val();
    let idNhanVien = $('#nhanVienId').val();
    let nhanvien = $('#nhanVienId').find(`option[value='${idNhanVien}']`).text();
    console.log(nhanvien);
    if (nhanvien == "") {
        alert("Thêm không thành công")
    } else {
        $.ajax(
            {
                url: urlPathHost + '/api/nhanvien/view/' + idPhongBan,
                method: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    // index chỉ mục mảng , value giá trị của phần tử mảng
                    $.each(data, function (index, value) {
                        if (nhanVienLamViec.indexOf(value.mnv) == -1) {
                            nhanVienLamViec.push(value.mnv);

                            inNhavien.push([value.mnv, value.phongBan.tenPB, value.fullName])
                        }
                    });
                    // console.log(inNhavien);
                    console.log(nhanVienLamViec);
                    taocongviec.showNhanVien();
                },
                error: function (e) {
                    console.log(e.message);
                }
            })
    }
}


taocongviec.them = function () {
    let idPhongBan = $('#phongBanId').val();
    let phongban = $('#phongBanId').find(`option[value='${idPhongBan}']`).text();
    let idNhanVien = $('#nhanVienId').val();
    let nhanvien = $('#nhanVienId').find(`option[value='${idNhanVien}']`).text();

    if (nhanvien == "") {
        alert("Thêm không thành công")
    } else {
        nhanVienLamViec.push(+idNhanVien);
        inNhavien.push([+idNhanVien, phongban, nhanvien]);

        taocongviec.showNhanVien();
    }
}

taocongviec.xoa = function (index) {
    nhanVienLamViec.splice(index, 1);
    inNhavien.splice(index, 1);
    taocongviec.showNhanVien();

}
//kiểm tra
// document.querySelectorAll("input[name='iMain']")[0].checked

taocongviec.save = function () {
    var congViecObject = {
        tenCongViec: '',
        noiDung: '',
        ngayBatDau: '',
        ngayKetThuc: '',
        tatCaNhanVien: [],
        nvChinh: ''
    };

    let tmp = document.querySelectorAll("input[name='iMain']");
    $.each(tmp, function (index, value) {
        console.log(tmp[index].checked);
        if (tmp[index].checked === true) {
            congViecObject.nvChinh = tmp[index].value;
        }
    });
    congViecObject.tenCongViec = ($('#title_work').val());
    congViecObject.noiDung = ($('#message_text').val());
    congViecObject.ngayBatDau = ($('#start_day').val());
    congViecObject.ngayKetThuc = ($('#end_day').val());
    congViecObject.tatCaNhanVien = nhanVienLamViec;
    console.log(congViecObject);
    bootbox.confirm({
        title: "Tạo công việc",
        message: "Bạn có muốn tạo công việc?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Hủy'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Có',
                className: 'btn-success'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: urlPathHost + "/api/taocongviec/create",
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(congViecObject),
                    success: function () {
                        console.log("POST DONE");
                        location.reload();
                    }
                })
            }
        }
    });
}

$(document).ready(function () {
    taocongviec.listphongban();
});


var formCreate = $("#myform");
// jQuery.validator.addMethod(
//     "xyz",
//     function () {
//         if ($('#nhanVienId').val() == null)
//             return false;
//         else
//             return true;
//     },
//     "Vui lòng chọn nhân viên"
// );
jQuery.validator.addMethod(
    "phongban",
    function () {
        if ($('#phongBanId').val() == null)
            return false;
        else
            return true;
    },
    "Vui lòng chọn phòng ban"
);



formCreate.validate({
    rules: {
        title_work: "required",
        message_text: "required",
        phongBanId: {
            phongban: true
        }
    },
    messages: {
        title_work: "Vui lòng nhập tên công việc",
        message_text: "Vui lòng nhập nội dung",
    },
    submitHandler: function () {
        if (validDate()) {
            taocongviec.save();
        }
    }
});

function validDate() {
    var startDay = new Date($('#start_day').val()).getTime();
    var endDay = new Date($('#end_day').val()).getTime();
    var q = new Date();
    var m = q.getMonth() + 1;
    var d = q.getDate();
    var y = q.getFullYear();
    if (new Date($('#start_day').val()) == "Invalid Date" || new Date($('#end_day').val()) == "Invalid Date") {
        console.log(1)
        alert("Vui lòng chọn ngày tháng")
        return false;
    } else if (startDay < new Date(y + "-" + m + "-" + d).getTime()) {
        console.log(2)
        alert("Ngày bắt đầu không được nhỏ hơn ngày hiện tại")
        return false;
    } else if (startDay > endDay) {
        console.log(3)
        alert("Ngày bắt đầu không được lớn hơn ngày kết thúc")
        return false;
    }
    return true;
}