var phongban = phongban || {}
phongban.showTitle = function (){
    $.ajax(
        {
            url: urlPathHost+'/api/phongban/view' ,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data){
                console.log(data);
                let t = $('#dataTable').DataTable({
                    responsive: true
                });
                // index chỉ mục mảng , value giá trị của phần tử mảng
                $.each(data, function( index, value ) {
                    t.row.add( [
                        value.tenPB,
                        "<i class='far fa-edit ' title='Chỉnh sửa phòng ban' style='margin-right: 10px' onclick='phongban.edit("+value.mpb+")'></i>",
                    ]).draw();
                });


            },
            error: function (e) {
                console.log(e.message);
            }
        })
}
phongban.save = function () {
    var phongbanObject = {};
    phongbanObject.mpb = $('#id').val();
    phongbanObject.tenPB = $('#tenPB').val();
    if ($("id").val() === null) {
        $.ajax({
            url: urlPathHost+'/api/phongban/create',
            method: 'POST',
            dataType: 'JSON',
            contentType: 'application/json',
            data: JSON.stringify(phongbanObject),
            success: function (data) {
                console.log("POST DONE");
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                phongban.showTitle();
            }
        })
    } else {
        $.ajax({
            url: urlPathHost+"/api/phongban/edit" ,
            method: "PUT",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(phongbanObject),
            success: function () {
                $('#exampleModal').modal('hide');
                $('#tBody').empty();
                $('#dataTable').dataTable().fnClearTable();
                $('#dataTable').dataTable().fnDestroy();
                phongban.showTitle();

            },
        });
    }
}
phongban.edit = function(mpb){
    console.log('get :'+ mpb);
    $.ajax({
        url : urlPathHost+"/api/phongban/edit/" + mpb,
        method : "GET",
        dataType : "json",
        success : function(data){
            console.log(data);
            $('#myform')[0].reset();
            // //
            $('#exampleModalLabel').html("Chỉnh sửa phòng ban");
            $('#modal-form-1').html("Sửa");
            $('#id').val(data.mpb);
            $('#tenPB').val(data.tenPB);
            $('#exampleModal').modal('show');
            formDepartmentValid.resetForm();
            $(".error").removeClass("error");
            // $('#productLine').val(data.productLine.id);
            // $('#id').val(data.id);

        }
    });
};
    phongban.resetForm = function () {
        $('#myform')[0].reset();
        $('#tenPB').val('');
        //
    }
    phongban.addNew = function () {
        $('#exampleModalLabel').html("Tạo Phòng ban");
        phongban.resetForm();
        $('#modal-form-1').html("Tạo");
        $('#exampleModal').modal('show');
        formDepartmentValid.resetForm();
    };
    $(document).ready(function () {
        phongban.showTitle();
    });

var formDepartment = $("#myform");
var formDepartmentValid = formDepartment.validate({
    rules: {
        tenPB: "required"
    },
    messages: {
        tenPB: "Vui lòng nhập phòng ban"
    },
    submitHandler: function () {
        phongban.save();
    }
});
