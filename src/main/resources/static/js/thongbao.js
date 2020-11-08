
var thongbao = thongbao || {};
thongbao.showTitle = function () {
    $.ajax(
        {
            url: urlPathHost+'/api/thongbao/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                let t = $('#dataTable').DataTable({
                    responsive: true
                });
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    t.row.add([
                        value.tieuDe,
                        value.noiDung,
                        "<i class='far fa-edit ' title='Chỉnh sửa thông báo' style='margin-right: 10px' onclick='thongbao.edit(" + value.id + ")'></i>" +
                        "<i class='far fa-trash-alt ' onclick='thongbao.delete(" + value.id + ")'></i>"
                        ,
                    ]).draw();
                });


            },
            error: function (e) {
                console.log(e.message);
            }
        })
}

thongbao.save = function () {
    var thongbaoObject = {};
    thongbaoObject.id = $('#id').val();
    console.log(thongbaoObject.id);
    if ($("id").val() == null) {
        thongbaoObject.tieuDe = $('#tieuDe').val();
        thongbaoObject.noiDung = $('#message_text').val();
        console.log(thongbao);
        $.ajax({
            url: urlPathHost+"/api/thongbao/create",
            method: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(thongbaoObject),
            success: function () {
                console.log("POST DONE");
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                thongbao.showTitle();
            },
        });
    } else {
        $.ajax({
            url: urlPathHost+"/api/thongbao/edit/" + thongbaoObject.id,
            method: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(thongbaoObject),
            success: function () {
                $('#exampleModal').modal('hide');
                $("#tBody").DataTable().ajax.reload();

            },
        });
    }
}
thongbao.edit = function (id) {
    console.log('get :' + id);
    $.ajax({
        url: urlPathHost+"/api/thongbao/edit/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {

            $('#myform')[0].reset();
            // //
            $('#exampleModalLabel').html("Chỉnh sửa thông báo");
            $('#modal-form-1').html("Sửa");
            $('#id').val(data.id);
            $('#tieuDe').val(data.tieuDe);
            $('#message_text').val(data.noiDung);
            $('#exampleModal').modal('show');
            formNotificationValid.resetForm();
            $(".error").removeClass("error");
            // $('#productLine').val(data.productLine.id);
            // $('#id').val(data.id);

        }
    });
};
thongbao.delete = function (id) {
    bootbox.confirm({
        title: "Xóa Thông Báo",
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
                    url: urlPathHost+"/api/thongbao/delete/" + id,
                    method: "get",
                    dataType: "json",
                    success: function (data) {
                        $('#tBody').empty();
                        $('#dataTable').dataTable().fnClearTable();
                        $('#dataTable').dataTable().fnDestroy();
                        thongbao.showTitle();
                    }
                });
            }
        }
    });
}
thongbao.addNew = function () {
    $('#exampleModalLabel').html("Tạo Thông báo");
    thongbao.resetForm();
    $('#modal-form-1').html("Tạo");
    $('#exampleModal').modal('show');
    formNotificationValid.resetForm();
};

thongbao.resetForm = function () {
    $('#myform')[0].reset();
    $('#tieuDe').val('');
    $('#message_text').val('');
    //
}

$(document).ready(function () {
    thongbao.showTitle();
});

var formNotification = $("#myform");
var formNotificationValid = formNotification.validate({
    rules: {
        tieuDe: "required",
        message_text: "required"
    },
    messages: {
        tieuDe: "Vui lòng nhập tiêu đề",
        message_text: "Vui lòng nhập nội dung",
    },
    submitHandler: function () {
        thongbao.save();
    }
});
