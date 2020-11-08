function readURL(input, elem) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(elem).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

$("#customFile").change(function () {
    readURL(this, "#avatar");
});

function changeInfo(){
    if($("#passCurrent").val() == $("#passConfirm").val() && $("#passCurrent").val() != ""){
        $.ajax({
            url: urlPathHost + "/user/nhanvien/changPass/",
            type: "POST",
            data: new FormData($('#myform')[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function () {
                alert("Đổi mật khẩu thành công");
            },
            error: function (data) {
                if (data.responseText == "Wrong old password"){
                    alert("Mật khẩu cũ không đúng");
                }
            }
        });
    }else {
        alert("Mật khẩu hiện tại và xác nhận không giống nhau");
    }
}