
var quanlycongviec = quanlycongviec || {};
// quanlycongviec.showTitle = function () {
//     $.ajax(
//         {
//             url: urlPathHost+'/api/quanlycongviec/view',
//             method: 'GET',
//             dataType: 'json',
//             contentType: 'application/json',
//             success: function (data) {
//                 $('#tBody').html("");
//                 // index chỉ mục mảng , value giá trị của phần tử mảng
//                 $.each(data, function (index, value) {
//                     console.log(data);
//                     if (value.congViec.tinhTrang.id==1 || value.congViec.tinhTrang.id == 3 ){
//                     } else if(value.congViec.tinhTrang.id == 2){
//                         $('#tBody').append(
//                             '<tr>' +
//                             '<td><a href= "/admin/chitietcongviec/'+ value.id +'">' + value.congViec.tenCongViec + '</a></td>' +
//                             '<td>' + value.congViec.tinhTrang.tinhTrang + '</td>' +
//                             '<td>' + value.nhanVien.fullName + '</td>' ,
//                             '</tr>'
//                         );
//                     } else {
//                     $('#tBody').append(
//                         '<tr>' +
//                         '<td><a href= "/admin/chitietcongviec/'+ value.id +'">' + value.congViec.tenCongViec + '</a></td>' +
//                         '<td>' + value.congViec.tinhTrang.tinhTrang + '</td>' +
//                         '<td>' + value.nhanVien.fullName + '</td>' +
//                         '<td><button type="submit" class="btn btn-outline-success mr-2">Đồng ý</button>' +
//                         '<button type="submit" class="btn btn-outline-secondary">Không</button></td>' +
//                         '</tr>'
//                     );
//                     }
//                 });
//             },
//             error: function (e) {
//                 console.log(e.message);
//             }
//         })
// }
quanlycongviec.showTitle = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/quanlycongviec/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                let t = $('#dataTable').DataTable({
                    responsive: true
                });
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {
                    if(value.congViec.tinhTrang.id == 3){
                        t.row.add([
                            "<a href='/admin/chitietcongviec/"+value.id+"'>"+value.congViec.tenCongViec+"</a>",
                            value.congViec.tinhTrang.tinhTrang,
                            value.nhanVien.fullName,
                            value.nhanVien.phongBan.tenPB,
                            value.congViec.ngayKetThuc,
                            ""
                        ]).draw();
                    }else {
                    t.row.add([
                        "<a href='/admin/chitietcongviec/"+value.id+"'>"+value.congViec.tenCongViec+"</a>",
                        value.congViec.tinhTrang.tinhTrang,
                        value.nhanVien.fullName,
                        value.nhanVien.phongBan.tenPB,
                        value.congViec.ngayKetThuc,
                        "<i class='fas fa-check ' title='Chỉnh sửa' style='margin-right: 10px' onclick='quanlycongviec.dongy(" +value.id+","+value.congViec.tinhTrang.id + ")'></i>"
                    ]).draw();
                    }
                });
            },
            error: function (e) {
                console.log(e.message);
            }
        })
}
quanlycongviec.dongy = function (idChiTiet,idTinhTrang){
    console.log(idTinhTrang)
    if(idTinhTrang == 6){
        bootbox.confirm({
            title: "Kết thúc công việc",
            message: "Bạn có muốn kết thúc công việc không?",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Hủy'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Đồng ý',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: urlPathHost+"/api/quanlycongviec/ketthuc/" + idChiTiet,
                        method: "PUT",
                        dataType: "json",
                        success: function (data) {
                            $('#tBody').empty();
                            $('#dataTable').dataTable().fnClearTable();
                            $('#dataTable').dataTable().fnDestroy();
                            quanlycongviec.showTitle();
                        }
                    });
                }
            }
        });
    } else if(idTinhTrang == 4){
        bootbox.confirm({
            title: "Gia hạn cộng việc",
            message: "Bạn có muốn gia hạn công việc không?",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Hủy'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Đồng ý',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: urlPathHost+"/api/quanlycongviec/giahan/" + idChiTiet,
                        method: "PUT",
                        dataType: "json",
                        success: function (data) {
                            $('#tBody').empty();
                            $('#dataTable').dataTable().fnClearTable();
                            $('#dataTable').dataTable().fnDestroy();
                            quanlycongviec.showTitle();
                        }
                    });
                }
            }
        });
    }else if (idTinhTrang == 5){
        bootbox.confirm({
            title: "Chuyển giao cộng việc",
            message: "Bạn có muốn chuyển giao công việc không?",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-times"></i> Hủy'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> Đồng ý',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: urlPathHost+"/api/quanlycongviec/giahan/" + idChiTiet,
                        method: "PUT",
                        dataType: "json",
                        success: function (data) {
                            $('#tBody').empty();
                            $('#dataTable').dataTable().fnClearTable();
                            $('#dataTable').dataTable().fnDestroy();
                            quanlycongviec.showTitle();
                        }
                    });
                }
            }
        });
    }
}
// quanlycongviec.save = function () {
//     var quanlycongviecObject = {};
//     quanlycongviecObject.id = $('#id').val();
//     console.log(quanlycongviecObject.id);
//     if ($("id").val() == null) {
//         quanlycongviecObject.tieuDe = $('#tieuDe').val();
//         quanlycongviecObject.noiDung = $('#message-text').val();
//         console.log(quanlycongviec);
//         $.ajax({
//             url: urlPathHost+"/api/quanlycongviec/create",
//             method: "POST",
//             dataType: "json",
//             contentType: "application/json",
//             data: JSON.stringify(quanlycongviecObject),
//             success: function () {
//                 console.log("POST DONE");
//                 $('#exampleModal').modal('hide');
//                 $('#tBody').empty();
//                 $('#dataTable').dataTable().fnClearTable();
//                 $('#dataTable').dataTable().fnDestroy();
//                 quanlycongviec.showTitle();
//             },
//         });
//     } else {
//         $.ajax({
//             url: urlPathHost+"/api/quanlycongviec/edit/" + quanlycongviecObject.id,
//             method: "PUT",
//             dataType: "json",
//             contentType: "application/json",
//             data: JSON.stringify(quanlycongviecObject),
//             success: function () {
//                 $('#exampleModal').modal('hide');
//                 $("#tBody").DataTable().ajax.reload();
//
//             },
//         });
//     }
// }
// quanlycongviec.edit = function (id) {
//     console.log('get :' + id);
//     $.ajax({
//         url: urlPathHost+"/api/quanlycongviec/edit/" + id,
//         method: "GET",
//         dataType: "json",
//         success: function (data) {
//
//             $('#myform')[0].reset();
//             // //
//             $('#exampleModalLabel').html("Chỉnh sửa thông báo");
//             $('#modal-form-1').html("Sửa");
//             $('#id').val(data.id);
//             $('#tieuDe').val(data.tieuDe);
//             $('#message-text').val(data.noiDung);
//             $('#exampleModal').modal('show');
//             // $('#productLine').val(data.productLine.id);
//             // $('#id').val(data.id);
//
//         }
//     });
// };
// quanlycongviec.delete = function (id) {
//     bootbox.confirm({
//         title: "Xóa Thông Báo",
//         message: "Bạn có muốn xóa ko?",
//         buttons: {
//             cancel: {
//                 label: '<i class="fa fa-times"></i> No'
//             },
//             confirm: {
//                 label: '<i class="fa fa-check"></i> Yes',
//                 className: 'btn-danger'
//             }
//         },
//         callback: function (result) {
//             if (result) {
//                 $.ajax({
//                     url: urlPathHost+"/api/quanlycongviec/delete/" + id,
//                     method: "get",
//                     dataType: "json",
//                     success: function (data) {
//                         $('#tBody').empty();
//                         $('#dataTable').dataTable().fnClearTable();
//                         $('#dataTable').dataTable().fnDestroy();
//                         quanlycongviec.showTitle();
//                     }
//                 });
//             }
//         }
//     });
// }
// quanlycongviec.addNew = function () {
//     $('#exampleModalLabel').html("Tạo Thông báo");
//     quanlycongviec.resetForm();
//     $('#modal-form-1').html("Tạo");
//     $('#exampleModal').modal('show');
// };
//
// quanlycongviec.resetForm = function () {
//     $('#myform')[0].reset();
//     $('#tieuDe').val('');
//     $('#message-text').val('');
//     //
// }

$(document).ready(function () {
    quanlycongviec.showTitle();
});