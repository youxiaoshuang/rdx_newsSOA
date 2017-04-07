/**
 * Created by youxiaoshuang on 2017/1/11.
 */

/**
 * 新鲜事 模块生成  无图
 */
//引用懒加载
document.write("<script src='/public/js/amazeui.lazyload.js'></script>");
document.write("<script src='/public/js/core/util.js'></script>");
function xinxianshi0(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block am-animation-scale-up">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a4.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">大兔</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_zzs">新鲜事</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">' + document.title + '</a></h3>');
    if (document.desc != null) {
        html += ('<div class="am-list-item-text pet_list_two_text">' + document.desc + '</div>');
    }
    html += ('</div>');
    html += ('</li>');
    return html;
}


/**
 * 新鲜事 模块生成  缩略图在标题右边
 */
function xinxianshi1(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block am-animation-scale-up">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a1.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">Super invincible 菁 </div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_xxs">新鲜事</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-u-sm-8 am-list-main pet_list_one_nr" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">' + document.title + '</a></h3>');
    if (document.desc != null) {
        html += ('<div class="am-list-item-text pet_list_one_text">' + document.desc + '</div>');
    }
    html += ('</div>');
    html += ('<div class="am-u-sm-4 am-list-thumb">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="' + document.images[0].url + '" class="pet_list_one_img" alt=""/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    return html;
}

/**
 * 新鲜事 模块生成  两张图
 */
function xinxianshi2(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block am-animation-scale-up">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a3.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">养了猫的飞飞</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_stj">新鲜事</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">' + document.title + '</a></h3>');
    html += ('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-2 am-avg-lg-2 am-gallery-default pet_list_one_list" >');
    for (var i = 0; i < document.images.length; i++) {
        html += ('<li>');
        html += ('<div class="am-gallery-item">');
        html += ('<a href="javascript:void(0);" class="">');
        html += ('<img class="lazy"  src="' + document.images[i].url + '"  alt="日大侠"/>');
        html += ('</a>');
        html += ('</div>');
        html += ('</li>');
    }
    html += ('</ul>');
    if (document.desc != null) {
        html += ('<div class="am-list-item-text pet_list_two_text">' + document.desc + '</div>');
    }
    html += ('</div>');
    html += ('</li>');
    return html;
}

/**
 * 新鲜事 模块生成  三张图Ø
 */
function xinxianshi3(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block  am-animation-scale-up">');
    html += ('<div class="pet_list_one_info ">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a3.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">养了猫的飞飞</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_stj">新鲜事</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">' + document.title + '</a></h3>');
    html += ('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-avg-md-3 am-avg-lg-3 am-gallery-default pet_list_one_list" >');
    for (var i = 0; i < document.images.length; i++) {
        html += ('<li>');
        html += ('<div class="am-gallery-item">');
        html += ('<a href="javascript:void(0);" class="">');
        html += ('<img class="lazy"  src="' + document.images[i].url + '"  alt="日大侠"/>');
        html += ('</a>');
        html += ('</div>');
        html += ('</li>');
    }
    html += ('</ul>');
    if (document.desc != null) {
        html += ('<div class="am-list-item-text pet_list_two_text">' + document.desc + '</div>');
    }
    html += ('</div>');
    html += ('</li>');
    return html;
}

/**
 * 新鲜事 模块生成  多张图
 */
function xinxianshi4(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block am-animation-scale-up">');
    html += (' <div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_tytj"><i class="iconfont pet_nav_kantuya pet_more_list_block_line_ico pet_list_tytj_ico">&#xe607;</i>' + document.title + '</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_kty">新鲜事</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-avg-md-3 am-avg-lg-3 am-gallery-default pet_list_one_list pet_list_one_tytj" >');

    for (var i = 0; i < document.images.length; i++) {
        html += ('<li>');
        html += ('<div class="am-gallery-item">');
        html += ('<a href="javascript:void(0);" class="">');
        html += ('<img class="lazy"  src="' + document.images[i].url + '"  alt="日大侠"/>');
        html += ('</a>');
        html += ('</div>');
        html += ('</li>');
    }
    html += ('</ul>');
    html += ('</li>');
    return html;

}

/**
 * 段子模块
 * @param document
 * @returns {string}
 */
function duanzi(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block am-animation-scale-up">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a6.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">La Da Dee</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_zzs">段子</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">' + document.title + '</a></h3>');
    // html += ('<div class="am-list-item-text pet_list_two_text">'+document.desc+'</div>');
    html += ('</div>');
    html += ('</li>');
    return html;
}


/**
 * 趣图模块
 */

function qt(document) {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block am-animation-scale-up">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/c.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">La Da Dee</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_video_tag">趣图</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class="pet_article_user_img">');
    html += ('<div class="pet_article_user_shadow" onclick="location.href=&quot;/news/detail/' + document.uuid + '&quot;"></div>');
    html += ('<div class="pet_article_user_title pet_list_one_text">' + document.title + '</div>');

    html += ('<img class="lazy"  src="' + document.images[0].url + '" alt="日大侠"></div>');
    // html += ('<div class="pet_article_user_info">');
    // html += ('<div class="pet_article_user_info_ico"><img class="lazy"  src="/public/images/c.png" alt=""></div>SeeYouAgain');
    // html += ('</div>');
    // html += ('<div class="am-list-item-text pet_article_user_nr">这是一家帮助客户在亚太地区找到适合的打折酒店的中介机构，在全球设立了9个办事处，老板克里斯蒂安·米施勒宣称要把它打造成世界上最棒的公司。</div>');
    html += ('</div>');
    html += ('</li>');
    return html;
}


/**
 * 视频模块
 */

function shipin() {
    var html = '';
    html += ('<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a6.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">La Da Dee</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_video_tag">视频</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-u-sm-8 am-list-main pet_list_one_nr">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">不是说好做彼此的天使吗？连最后一口汉堡也不给我</a></h3>');
    html += ('<div class="am-list-item-text pet_list_one_text">国外网友waxiestapple在论坛Reddit贴出爱犬照片，指出“我的狗狗好像瘦了点”“因为我刚刚把最后一口汉堡吃掉”，只见这只哈士奇一脸惨遭背叛的样子，对主人露出相当不可思议的表情。</div>');
    html += ('</div>');
    html += ('<div class="am-u-sm-4 am-list-thumb pet_video_info">');
    html += ('<div class="pet_video_info_tag"><i class="iconfont">&#xe62d;</i>03:50</div>');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/q4.jpg" class="pet_list_one_img" alt="我很囧，你保重....晒晒旅行中的那些囧！"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    return html;
}

/**
 * 三图并列
 */

function image_3() {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a3.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">养了猫的飞飞</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_stj">阅读</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">浣熊孤儿掉到树下，被一家人收养之后……</a></h3>');
    html += ('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-avg-md-3 am-avg-lg-3 am-gallery-default pet_list_one_list" >');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/qq1.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/qq2.jpg"  alt="不要太担心 只因为我相信"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/qq3.jpg"  alt="终会走过这条遥远的道路"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('</ul>');
    html += ('<div class="am-list-item-text pet_list_two_text">巴哈马拿骚的居民Rosie Kemp发现一个刚出生的浣熊，掉在了树下。因为找不到小浣熊的妈妈了，Rosie和她的女儿Laura Young决定收养这只小东西，并给她取名“小南瓜”。</div>');
    html += ('</div>');
    html += ('</li>');
    return html;
}


/**
 * 多图
 */

function images() {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block">');
    html += (' <div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_tytj"><i class="iconfont pet_nav_kantuya pet_more_list_block_line_ico pet_list_tytj_ico">&#xe607;</i>诺奖得主回忆通知获奖那一刻</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_kty">图集</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main">');
    html += ('<ul data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-avg-md-3 am-avg-lg-3 am-gallery-default pet_list_one_list pet_list_one_tytj" >');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w1.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w2.jpg"  alt="不要太担心 只因为我相信"/>');
    html += ('</a>');
    html += ('</div>');
    html += (' </li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w3.jpg"  alt="终会走过这条遥远的道路"/>');
    html += ('</a>');
    html += ('</div>');
    html += (' </li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w4.jpg"  alt="终会走过这条遥远的道路"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w5.jpg"  alt="终会走过这条遥远的道路"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('<li>');
    html += ('<div class="am-gallery-item">');
    html += ('<a href="javascript:void(0);" class="">');
    html += ('<img class="lazy"  src="/public/images/w6.jpg"  alt="终会走过这条遥远的道路"/>');
    html += ('</a>');
    html += ('</div>');
    html += ('</li>');
    html += ('</ul>');
    html += ('</li>');
    return html;

}


/**
 * 专题
 */

function zhuanti() {
    var html = '';
    html += ('<li class="am-g am-list-item-desced pet_list_one_block">');
    html += ('<div class="pet_list_one_info">');
    html += ('<div class="pet_list_one_info_l">');
    html += ('<div class="pet_list_one_info_ico"><img class="lazy"  src="/public/images/a5.png" alt=""></div>');
    html += ('<div class="pet_list_one_info_name">Hope</div>');
    html += ('</div>');
    html += ('<div class="pet_list_one_info_r">');
    html += ('<div class="pet_list_tag pet_list_tag_mzt">萌专题</div>');
    html += ('</div>');
    html += ('</div>');
    html += ('<div class=" am-list-main">');
    html += ('<h3 class="am-list-item-hd pet_list_one_bt"><a href="javascript:void(0);" class="">心情不好了，就来看看这只狗！</a></h3>');
    html += ('<div class="pet_list_zt_img"><img class="lazy"  src="/public/images/c1.png" alt=""></div>');
    html += ('<div class="am-list-item-text pet_list_two_text">猫咪不像人，猫咪的情绪不会写在脸上，反馈给我们的信息更多的应该是行为上肢体上的，当然从叫声中也会反应一些信息，那么要想“抓住它的心，就一定要抓住它的胃吗？”从它的行为和肢体语言当中我们可以读懂什么呢？</div>');

    html += ('</div>');
    html += ('</li>');
    html += ('<li class="am-g am-list-item-desced pet_list_one_block">');
    html += ('<div class="pet_article_user_block">');
    html += ('<div class="pet_article_user_img">');
    html += ('<div class="pet_article_user_shadow"></div>');
    html += ('<div class="pet_article_user_title">一周在任意地点工作三天？</div>');
    html += ('<img class="lazy"  src="/public/images/c2.png" alt=""></div>');
    html += ('<div class="pet_article_user_info">');
    html += ('<div class="pet_article_user_info_ico"><img class="lazy"  src="/public/images/c.png" alt=""></div>SeeYouAgain');
    html += ('</div>');
    html += ('<div class="am-list-item-text pet_article_user_nr">这是一家帮助客户在亚太地区找到适合的打折酒店的中介机构，在全球设立了9个办事处，老板克里斯蒂安·米施勒宣称要把它打造成世界上最棒的公司。</div>');
    html += ('</div>');
    html += ('</li>');
    return html;
}

/**
 * 首页列表
 */
function viewCreate() {
    lodaingStart($('#am-list'));
    var url = '/getHomeListData';
    var param = {};
    $.post(url, param, function (data) {
        var data = data.data;
        lodaingStop($('#am-list'));
        var dz = data.dz;
        var xxs = data.xxs;
        var qutu = data.qutu;
        xxsCalculate(xxs);
        qtCalculate(qutu);
        dzCalculate(dz);
        refreshBtn();
    });
}

/**
 * 猜你喜欢列表or首页刷新
 */
function likeViewCreate() {
    lodaingStart($('#am-list'));
    var url = '/getLikeData';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
           var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }

        }
        refreshBtn();
    });

}

/**
 * 新鲜事
 */
function xxsViewCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/refreshNews';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }
        }
        refreshBtn();
    });
}

/**
 * 新鲜事列表刷新
 */
function xxsViewRefCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/getRdNewsData';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }
        }
        refreshBtn();
    });
}


/**
 * 趣图列表
 */
function dzViewCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/refreshDuanZi';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }

        }
        refreshBtn();
    });
}

/**
 * 趣图列表刷新
 */
function dzViewRefCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/refreshDuanZi';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }
        }
        refreshBtn();
    });
}

/**
 * 段子列表
 */
function qtViewCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/refreshQuTu';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }

        }
        refreshBtn();
    });
}

/**
 * 趣图列表刷新
 */
function qtViewRefCreate() {
    lodaingStart($('#am-list'));
    var url = '/news/getRdQtData';
    var param = {};
    $.post(url, param, function (data) {
        var data  = data.data;
        lodaingStop($('#am-list'));
        for(var i=0;i<data.length;i++){
            var obj = data[i];
            if(obj.type == 'xxs'){
                var div = xxsCreate(obj);
                appendView(div);
            }else if(obj.type == 'qt' ){
                var div = qtCreate(obj);
                appendView(div);
            }else {
                var div = dzCreate(obj);
                appendView(div);
            }

        }
        refreshBtn();
    });

}






function xxsCreate(document) {
    var html = "";
    var imageSize = 0;
    if (document.images != null) {
        imageSize = document.images.length;
    }
    if (imageSize == 0) {
        html = xinxianshi0(document);
    } else if (imageSize == 1) {
        html = xinxianshi1(document);
    } else if (imageSize == 2) {
        html = xinxianshi2(document);
    } else if (imageSize == 3) {
        html = xinxianshi3(document);
    } else if (imageSize > 3) {
        html = xinxianshi4(document);
    }
    return html;
}

function dzCreate(document) {
    var html = "";
    html = duanzi(document);
    return html;
}



function qtCreate(document) {
    var html = "";
    html = qt(document);
    return html;
}



function xxsCalculate(documents) {
    for (var i = 0; i < documents.length; i++) {
        var html = "";
        var document = documents[i];
        var imageSize = 0;
        if (document.images != null) {
            imageSize = document.images.length;
        }
        if (imageSize == 0) {
            html += xinxianshi0(document);
        } else if (imageSize == 1) {
            html += xinxianshi1(document);
        } else if (imageSize == 2) {
            html += xinxianshi2(document);
        } else if (imageSize == 3) {
            html += xinxianshi3(document);
        } else if (imageSize > 3) {
            html += xinxianshi4(document);
        }
        appendView(html);
    }
}

function dzCalculate(documents) {
    for (var i = 0; i < documents.length; i++) {
        var html = "";
        var document = documents[i];
        html += duanzi(document);
        appendView(html);
    }
}

function qtCalculate(documents) {
    for (var i = 0; i < documents.length; i++) {
        var html = "";
        var document = documents[i];
        html += qt(document);
        appendView(html);
    }
}

function refreshBtn() {
    var $wrapper = $('#am-list');
    $wrapper.append('<input type="button" class="am-btn am-btn-secondary btn-loading-example am-center" value="换一批 看看" data-am-loading="{loadingText: &quot;努力加载中...&quot;}" />')
    $('.btn-loading-example').click(function () {
        var $btn = $(this)
        $btn.button('loading');
        refreshData();
    });
}

function appendView(html) {
    var $wrapper = $('#am-list');
    $wrapper.append(html);
    //添加刷新按钮
    imageLazy();
    // $('#am-list').append(html);
}


// var xinxianshi = xinxianshi();
// var shipin = shipin();
// var image_3 = image_3();
// var images = images();
// var duanzi = duanzi();
// var zhuanti = zhuanti();
// $('#am-list').append(xinxianshi).append(shipin).append(image_3).append(images).append(duanzi).append(zhuanti);



