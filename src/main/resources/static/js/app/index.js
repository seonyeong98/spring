const index = {
    init: function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
index.init();


//function doSave() {
    //alert(1)
//}
/*
$('#btn-save').on('click', function () {
    save();
});

function save() {
    alert(1)
    var data = {
        title: $('#title').val(),
        author: $('#author').val(),
        content: $('#content').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/v1/posts',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function() {
        alert('글이 등록되었습니다.');
        window.location.href = '/';
    })
}
*/
