var duocgiao = duocgiao || {}
duocgiao.listcongviec = function () {
    $.ajax(
        {
            url: urlPathHost+'/api/duocgiao/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                // console.log(data);
                // let t = $('#dataTable').DataTable({
                //     responsive: true
                // });
                $('#tBody').html("");
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function (index, value) {

                    // t.row.add([
                    //         "<a onclick='thongbao.delete(" + value.id + ")'></a>",
                    //     value.congViec.ngayBatDau,
                    //     value.congViec.ngayKetThuc,
                    //     // "<i class='far fa-edit ' title='Chỉnh sửa thông báo' style='margin-right: 10px' onclick='thongbao.edit(" + value.id + ")'></i>" +
                    //     // "<i class='far fa-trash-alt ' onclick='thongbao.delete(" + value.id + ")'></i>"
                    //     // ,
                    // ]).draw();
                    $('#tBody').append(
                        '<tr>' +
                            '<td><a href= "/user/chitietcongviec/'+ value.id +'">' + value.congViec.tenCongViec + '</a></td>' +
                        '<td>' + value.congViec.ngayBatDau + '</td>' +
                        '<td>' + value.congViec.ngayKetThuc + '</td>' +
                        '<td>' + value.congViec.tinhTrang.tinhTrang + '</td>' +
                        '</tr>'
                    );

                });


            },
            error: function (e) {
                console.log(e.message);
            }
        })
}
$(document).ready(function () {
    duocgiao.listcongviec();
});