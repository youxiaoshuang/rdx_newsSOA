/**
 * Created by youxiaoshuang on 2017/1/11.
 */
//引用懒加载
document.write("<script src='/public/js/amazeui.lazyload.js'></script>");
/**
 *图片懒加载
 */
function imageLazy() {
    $('.lazy').lazyload({effect : 'fadeIn'});
}

/**
 * 将div中加入loading
 */
function lodaingStart(div) {
    var html = '<div class="example-1"></div>';
    div.append(html);
}


/**
 * 停止loadding
 */
function lodaingStop(div) {
    div.html("");
}

