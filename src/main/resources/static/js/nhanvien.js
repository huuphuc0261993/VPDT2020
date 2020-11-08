var nhanvien = nhanvien || {}

nhanvien.showTitle = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/nhanvien/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                let t = $('#dataTable').DataTable({
                    responsive: true
                });
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    t.row.add([
                        value.username,
                        value.fullName,
                        value.phongBan.tenPB,
                        value.email,
                        value.phone,
                        "<i class='far fa-edit ' title='Chỉnh sửa' style='margin-right: 10px' onclick='nhanvien.edit(" + value.mnv + ")'></i>" +
                        "<i class='far fa-trash-alt ' title='Xóa' style='margin-right: 10px' onclick='nhanvien.delete(" + value.mnv + ")'></i>"
                    ]).draw();
                });


            },
            error: function (e) {
                console.log(e.message);
            }
        })
}
nhanvien.listphongban = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/phongban/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {

                // phongBanList = data;
                $('#department').html("<option disabled selected>--/--</option>");
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    $('#department').append(
                        "<option value='" + value.mpb + "' >" + value.tenPB + "</option>"
                    );
                });
            },
            error: function (e) {
                console.log(e.message);
            }
        }).done(function (data) {
        // If successful
    })
}

nhanvien.save = function () {
    var nhanvienObject = {};
    nhanvienObject.mnv = $('#id').val();
    nhanvienObject.username = $('#username').val();
    nhanvienObject.email = $('#email').val();
    nhanvienObject.password = $('#password').val();
    nhanvienObject.fullName = $('#fullName').val();
    nhanvienObject.department = $('#department').val();
    nhanvienObject.phone = $('#phone').val();

    if (nhanvienObject.mnv === "") {
        $.ajax({
            url: urlPathHost + '/api/nhanvien/create/' + nhanvienObject.department,
            method: 'POST',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(nhanvienObject),
            success: function (data) {
                console.log("POST DONE");
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                nhanvien.showTitle();
            }
        })
    } else {

        $.ajax({
            url: urlPathHost + "/api/nhanvien/edit/" + nhanvienObject.department,
            method: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(nhanvienObject),
            success: function () {
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                nhanvien.showTitle();

            },
        });
    }
}

nhanvien.edit = function (mnv) {
    console.log("get: " + mnv)
    $.ajax({
        url: urlPathHost + "/api/nhanvien/edit/" + mnv,
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log("day la " + data);
            $('#myform')[0].reset();
            // //
            $('#exampleModalLabel').html("Chỉnh sửa thông tin");
            $('#modal-form-1').html("Sửa");

            $('#id').val(data.mnv);
            $('#username').val(data.username);
            $('#email').val(data.email);
            $('#password').val(data.password);
            $('#fullName').val(data.fullName);

            $('#department').val(data.phongBan.mpb);
            // console.log(data.phongBan.tenPB);
            $('#phone').val(data.phone);
            $('#exampleModal').modal('show');
            formUserValid.resetForm();
            $(".error").removeClass("error");
            // $('#productLine').val(data.productLine.id);
            // $('#id').val(data.id);

        },
        done: function (data){
            console.log(data);
        }
    });
};

nhanvien.delete = function (id) {
    bootbox.confirm({
        title: "Xóa nhân viên",
        message: "Bạn có muốn xóa ko?",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> No'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Yes',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: urlPathHost+"/api/nhanvien/delete/" + id,
                    method: "get",
                    dataType: "json",
                    success: function (data) {
                        $('#tBody').empty();
                        $('#dataTable').dataTable().fnClearTable();
                        $('#dataTable').dataTable().fnDestroy();
                        nhanvien.showTitle();
                    }
                });
            }
        }
    });
}
nhanvien.resetForm = function () {
    $('#myform')[0].reset();
    $('#tenPB').val('');
    //
}
nhanvien.addNew = function () {
    $('#exampleModalLabel').html("Thêm nhân viên");
    nhanvien.resetForm();
    $('#modal-form-1').html("Tạo");
    $('#exampleModal').modal('show');
    formUserValid.resetForm();
};
$(document).ready(function () {
    nhanvien.showTitle();
    nhanvien.listphongban();
});

var formUser = $("#myform");
jQuery.validator.addMethod('phone_valid', function (value) {
    var regex = /((09|03|07|08|05)+([0-9]{8,9})\b)/g;
    return value.trim().match(regex);
});
var formUserValid = formUser.validate({
    rules: {
        username: "required",
        password: "required",
        fullName: "required",
        department: "required",
        email: {
            required: true,
            email: true
        },
        phone: {
            required: true,
            phone_valid: true
        }
    },
    messages: {
        username: "Vui lòng nhập username",
        password: "Vui lòng nhập password",
        fullName: "Vui lòng nhập họ và tên",
        department: "Vui lòng nhập phòng ban",
        email: {
            required: "Vui lòng nhập email",
            email: "Vui lòng nhập dúng định dạng email",
        },
        phone: {
            required: "Vui lòng nhập số điện thoại",
            phone_valid: "Vui lòng nhập đúng định dạng số điện thoại"
        }
    },
    submitHandler: function () {
        nhanvien.save();
    }
});
