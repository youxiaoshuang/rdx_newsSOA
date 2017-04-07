var ctx;//项目路径
$(function () {
    ctx = $('#ctx').val();
    //上传图片
    "use strict";
    var docCode = $('#docCode').val();
    $('#content').artEditor({
        imgTar: '#imageUpload',
        limitSize: 5,   // 兆
        showServer: true,
        uploadUrl: ctx+'/fileCenter/uploadFile',
        data: {
            fileType: '.jpg',
            docCode: docCode
        },
        uploadField: 'image',
        placeholader: '<p>请输入文章正文内容</p>',
        validHtml: ["br"],
        uploadSuccess: function (res) {
            // return img url
            console.log(res);
            return res.url;
        },
        uploadError: function (res) {
            // something error
            console.log(res);
        }
    });

});

function sub() {
    var title = $('#title').val();
    var content = getContent();
    var docCode = $('#docCode').val();
    var desc = $('#desc').val();
    var url = ctx+"/news/subNews"
    var param = {
        content: content,
        title : title,
        uuid : docCode,
        desc : desc
    }
    $.post(url, param, function (data) {
      console.log(data);
        window.location.href = ctx+'/';
    });
}

function getContent() {
    var content = $('#content').getValue();
    console.info(content);
    return content;
}

