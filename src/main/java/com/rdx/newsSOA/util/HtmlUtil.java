package com.rdx.newsSOA.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by youxiaoshuang on 2017/1/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
public class HtmlUtil {

    /**
     * 得到网页中图片的地址
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                ( regEx_img, Pattern.CASE_INSENSITIVE );
        m_image = p_image.matcher( htmlStr );
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile( "src\\s*=\\s*\"?(.*?)(\"|>|\\s+)" ).matcher( img );
            while (m.find()) {
                pics.add( m.group( 1 ) );
            }
        }
        return pics;
    }


    /**
     * 获取字符串中的span
     *
     * @param htmlStr
     * @return
     */
    public static String getSpen(String htmlStr) {
        String regEx_span = "<span>(.*)</span>";
        Pattern compile = Pattern.compile( regEx_span );
        Matcher m_spa = compile.matcher( htmlStr );
        String span = "";
        while (m_spa.find()) {
            span = m_spa.group( 1 );
        }
        return span;
    }

    public static List<String> getHref(String htmlStr) {
        String domin = "http://md.itlun.cn/";
        List<String> hrefs = new ArrayList<String>(  );
        String ptn = "(href=[\"\']*)([^\"\']*[\"\'])";
        Pattern p = Pattern.compile( ptn );
        Matcher m = p.matcher( htmlStr );
        while (m.find()) {
            String a = m.group( 0 ).replaceAll( "href=","" ).replaceAll( "\"","" );
            if(a.indexOf( "html" )>-1){
                a = domin+a;
                hrefs.add( a );
            }

        }
        return hrefs;
    }

    public static void main(String[] args) {
        HtmlUtil.getHref( "<!DOCTYPE html><!--STATUS OK--><html>\n" +
                "<head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "<meta http-equiv=\"content-type\" content=\"text/html;charset=gb2312\">\n" +
                "<TITLE>金馆长斗图QQ表情图片官网_妈蛋表情网 md.itlun.cn</TITLE>\n" +
                "<link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"/search.xml\" title=\"表情搜索\" />\n" +
                "<meta name=\"keywords\" content=\"\"/>\n" +
                "<meta name=\"description\" content=\"金馆长暴走漫画表情网站，锤桌子,群主，女管理等各种趣图表情,欢迎使用搜索功能快速找到需要的表情,表情制作栏目可以在线生成有趣表情,关注微信公众号每天接收最新表情免费P图\"/>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "<!-- //平台、设备和操作系统\n" +
                "var system ={\n" +
                "win : false,\n" +
                "mac : false,\n" +
                "xll : false\n" +
                "};\n" +
                "//检测平台\n" +
                "var p = navigator.platform;\n" +
                "system.win = p.indexOf(\"Win\") == 0;\n" +
                "system.mac = p.indexOf(\"Mac\") == 0;\n" +
                "system.x11 = (p == \"X11\") || (p.indexOf(\"Linux\") == 0);\n" +
                "//跳转语句，如果是手机访问就\n" +
                "if(!(system.win||system.mac||system.xll)){\n" +
                "window.location.href=\"http://wap.itlun.cn\";\n" +
                "}\n" +
                "-->\n" +
                "</script> \n" +
                "<script type=\"text/javascript\"  src=\"/css/wd.js\"  charset=\"gb2312\"></script> \n" +
                "<link rel=\"alternate\" media=\"only screen and(max-width: 640px)\"     href=\"http://wap.itlun.cn \" >\n" +
                "<LINK href=\"/css/Command.css\" type=\"text/css\" rel=\"stylesheet\">\n" +
                "<LINK href=\"/css/style.CSS\" type=\"text/css\" rel=\"stylesheet\">\n" +
                "<LINK href=\"/css/mdbq.css\" type=text/css rel=stylesheet>\n" +
                "</head><body link=\"#0000cc\">\n" +
                "<div id=\"wrapper\" class=\"wrapper_l\">\n" +
                "<div id=\"head\">\n" +
                "<div class=\"head_wrapper\">\n" +
                "<div class=\"s_form\">\n" +
                "<div class=\"s_form_wrapper\">\n" +
                "<a href=\"/sotu.html\" id=\"result_logo\"><img src=\"/css/logo.gif\" alt=\"到首页\" title=\"到首页\"></a>\n" +
                "<form name=f id=form1 action=/plus/search.php class=\"fm\" >\n" +
                "\n" +
                "\n" +
                "<input name=\"kwtype\" type=\"hidden\" value=\"1\" checked>\n" +
                "<input name=\"searchtype\" id=\"searchtype\" type=\"hidden\" value=\"titlekeyword\" selected>\n" +
                "\t\n" +
                "\n" +
                "\n" +
                "<span class=\"bg s_ipt_wr\">\n" +
                "<input type=text name=q id=kw1  class=\"s_ipt\" value=\"\" maxlength=\"100\" autocomplete=\"off\"  placeholder=\"请输入两到三个关键词，如'仓鼠''厉害了''搞事'\">\n" +
                "\n" +
                "</span>\n" +
                "\n" +
                "<span class=\"bg s_btn_wr\">\n" +
                "\n" +
                "<input type=submit value=\"搜索一下\" id=su1  class=\"bg s_btn\">\n" +
                "\n" +
                "</span>\n" +
                "\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "      <div align=\"right\">\n" +
                "<!-- 分享代码-->  \n" +
                "      </div>\n" +
                "</div>\n" +
                "</div></div><div class=\"s_tab\" id=\"s_tab\">\n" +
                "<a href=\"http://md.itlun.cn/\">首页</a>\n" +
                "\n" +
                "        \t<a href='/a/new/' >最新</a>   \t\n" +
                "      \t<a href='/a/remen/'  onmouseover=\"rm()\" >热门</a>   \t\n" +
                "      \t<a href='#'  onmouseover=\"cd()\" >栏目</a>\n" +
                "<a href=\"#\" onmouseover=\"ds()\" >倒数</a>\n" +
                "<a href=\"/yx\">游戏</a>\n" +
                "  </div>\n" +
                "\t \n" +
                "\n" +
                "<div class=\"nums\" id=\"nums\">建议搜索的词不超过3个字\n" +
                "<a target=\"_blank\" rel=\"nofollow\" href=\"http://jq.qq.com/?_wv=1027&k=2DkWEy7\">【官方2群】</a>\n" +
                "<a target=\"_blank\" rel=\"nofollow\" href=\"http://jq.qq.com/?_wv=1027&k=27ckQSa\">【3群】</a>\n" +
                "<a target=\"_blank\"  href=\"http://md.itlun.cn/a/xinwen/bangzhu/\">【P图教程】</a>\n" +
                "<a target=\"_blank\"  href=\"http://md.itlun.cn/a/xinwen/zhishi/\">【知识】</a>\n" +
                "<a target=\"_blank\"  href=\"http://mdzz.itlun.cn/\">生成制作表情请点击链接</a>\n" +
                "</div>\n" +
                "\t\t  \n" +
                "<div id=\"content_left\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "<DIV class=\"firstscreen\">\n" +
                "  <DIV class=\"todayfocus\">\n" +
                "   <DIV id=\"imgbox\">\n" +
                "    <DIV id=\"recshowBox\">\n" +
                "\n" +
                "<A id=\"rp_1\" href=\"/a/jgz/23271.html\" title=\"不知不觉又装了一年的B，大家干\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/170101/1-1F101120117.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_2\" href=\"/a/jgz/22276.html\" title=\"一定要冷静：打他死！打死你个龟\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/161102/1-161102144057-lp.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_3\" href=\"/a/zt/21622.html\" title=\"突然兴奋、复活、滑稽、蒙蔽、抽\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/161017/1-16101G41214.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_4\" href=\"/a/xmr/21107.html\" title=\"鼓掌：奶思！（nice 漂亮）\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/160928/1-16092Q33R3-lp.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_5\" href=\"/a/jgz/19805.html\" title=\"先定一个能达到的小目标，比方说\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/160830/1-160S0162537-lp.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_6\" href=\"/a/jgz/19689.html\" title=\"扔硬币：正面就去上网、反面就去\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/160827/1-160RG43231.jpg\" border=\"0\"></A>\n" +
                "<A id=\"rp_7\" href=\"/a/xinwen/yingyong/15698.html\" title=\"QQ：对方撤回消息并摸了一把你的\" target=\"_blank\"><IMG src=\"http://img.itlun.cn/uploads/allimg/160430/1-160430093Z2Y2.jpg\" border=\"0\"></A>\n" +
                "  \n" +
                "\n" +
                "<small><a  href=\"/a/jgz/10421.html\"  target=\"_blank\"  id=\"focus_title\">喝敌敌畏：不胜酒力。。。你他妈养鱼呢？干了！</a></small>\n" +
                "\n" +
                "\n" +
                " </DIV>\n" +
                "   </DIV>\n" +
                "     <DIV id=\"recshowUrl\">\n" +
                "<A id=\"ru_1\" href=\"/a/jgz/23271.html\" title=\"不知不觉又装了一年的B，大家干\" target=\"_blank\">1</A>\n" +
                "<A id=\"ru_2\" href=\"/a/jgz/22276.html\" title=\"一定要冷静：打他死！打死你个龟\" target=\"_blank\">2</A>\n" +
                "<A id=\"ru_3\" href=\"/a/zt/21622.html\" title=\"突然兴奋、复活、滑稽、蒙蔽、抽\" target=\"_blank\">3</A>\n" +
                "<A id=\"ru_4\" href=\"/a/xmr/21107.html\" title=\"鼓掌：奶思！（nice 漂亮）\" target=\"_blank\">4</A>\n" +
                "<A id=\"ru_5\" href=\"/a/jgz/19805.html\" title=\"先定一个能达到的小目标，比方说\" target=\"_blank\">5</A>\n" +
                "<A id=\"ru_6\" href=\"/a/jgz/19689.html\" title=\"扔硬币：正面就去上网、反面就去\" target=\"_blank\">6</A>\n" +
                "<A id=\"ru_7\" href=\"/a/xinwen/yingyong/15698.html\" title=\"QQ：对方撤回消息并摸了一把你的\" target=\"_blank\">7</A>\n" +
                "\n" +
                "\n" +
                "\t\t </DIV></DIV>\n" +
                "\n" +
                "<script  src=\"/templets/meinv/index_files/focus.js\"></script><script>var focus_count=7,MovieRecom={bigpic:\"recshowBox\",text:'focus_title',step:380,smallpic:\"ru\",selectstyle:\"cur\",pictxt:\"\",totalcount:focus_count,autotimeintval:5000,objname:\"MovieRecom\"};focus.init(MovieRecom);</script>\n" +
                "\n" +
                "\t <DIV class=\"weekfocus\">\n" +
                "\t  <DIV class=\"searchbox\">\n" +
                "              <form action=\"/plus/search.php\" name=\"formsearch\" target=\"_blank\">\n" +
                "\n" +
                "<input name=\"pagesize\" type=\"hidden\" id=\"pagesize\" value=\"20\" size=\"4\" class=\"ipt-txt\">\n" +
                "<input name=\"kwtype\" type=\"hidden\" value=\"1\" checked>\n" +
                "<input name=\"searchtype\" id=\"searchtype\" type=\"hidden\" value=\"titlekeyword\" selected>\n" +
                "\n" +
                "              <INPUT value=\"\" type=\"text\" id=\"search-keyword\" name=\"q\">\n" +
                "              <BUTTON type=\"submit\">搜索</BUTTON>  </form>\n" +
                "\t  </DIV>\n" +
                "    <DIV class=\"hotpiclist\">\n" +
                "\t  <H3 class=\"title lineB_Gary\">热点主题</H3>\n" +
                "\t    <UL class=\"text\">\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot1.gif\">&#160;<A href=\"/a/jgz/7435.html\" title=\"爱她就去摸她，喜欢就强奸啊，表\" target=\"_blank\">爱她就去摸她，喜欢就强奸啊，表</A></LI>\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot2.gif\">&#160;<A href=\"/a/nhtp/5185.html\" title=\"女在跟别人偷情接老公电话：我真\" target=\"_blank\">女在跟别人偷情接老公电话：我真</A></LI>\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot3.gif\">&#160;<A href=\"/a/jgz/207.html\" title=\"漂亮的大姐姐我可以摸一下你的胸\" target=\"_blank\">漂亮的大姐姐我可以摸一下你的胸</A></LI>\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot4.gif\">&#160;<A href=\"/a/zt/4560.html\" title=\"土豪捡垃圾破烂组图 拾荒少爷\" target=\"_blank\">土豪捡垃圾破烂组图 拾荒少爷</A></LI>\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot5.gif\">&#160;<A href=\"/a/jgz/5643.html\" title=\"北风吹，秋风凉 谁家娇妻守空房 \" target=\"_blank\">北风吹，秋风凉 谁家娇妻守空房 </A></LI>\n" +
                "\n" +
                "       </UL>\n" +
                "\t    <UL class=\"text\">\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot6.gif\"> <A href=\"/a/jgz/48.html\" title=\"持剑系列QQ表情\" target=\"_blank\">持剑系列QQ表情</A></LI>\n" +
                "\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot7.gif\"> <A href=\"/a/jgz/1323.html\" title=\"出来斗图\" target=\"_blank\">出来斗图</A></LI>\n" +
                "\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot8.gif\"> <A href=\"/a/zt/6191.html\" title=\"金馆长版英雄联盟人物套图\" target=\"_blank\">金馆长版英雄联盟人物套图</A></LI>\n" +
                "\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot9.gif\"> <A href=\"/a/jgz/17288.html\" title=\"我控几不住我记几啊！（我控制不\" target=\"_blank\">我控几不住我记几啊！（我控制不</A></LI>\n" +
                "\n" +
                "<LI><IMG align=\"absmiddle\" src=\"/css/clist_dot10.gif\"> <A href=\"/a/jgz/79.html\" title=\"我去你妈个大苹果、包菜、黄瓜、\" target=\"_blank\">我去你妈个大苹果、包菜、黄瓜、</A></LI>\n" +
                "\n" +
                "       </UL>\n" +
                "\n" +
                "\t\n" +
                "<script async src=\"http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\n" +
                "<!-- 右侧文章 -->\n" +
                "<ins class=\"adsbygoogle\"\n" +
                "     style=\"display:inline-block;width:200px;height:200px\"\n" +
                "     data-ad-client=\"ca-pub-8152760122334813\"\n" +
                "     data-ad-slot=\"1655930483\"></ins>\n" +
                "<script>\n" +
                "(adsbygoogle = window.adsbygoogle || []).push({});\n" +
                "</script>\n" +
                "   </DIV></DIV></DIV>\n" +
                "\n" +
                "<DIV class=\"box_cClass\">\n" +
                " <DIV class=\"reclist c_box\">\n" +
                "<script async src=\"http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\n" +
                "<!-- 大横幅 -->\n" +
                "<ins class=\"adsbygoogle\"\n" +
                "     style=\"display:inline-block;width:970px;height:90px\"\n" +
                "     data-ad-client=\"ca-pub-8152760122334813\"\n" +
                "     data-ad-slot=\"5634182489\"></ins>\n" +
                "<script>\n" +
                "(adsbygoogle = window.adsbygoogle || []).push({});\n" +
                "</script>\n" +
                "\n" +
                "      <H2 class=\"title fColor\"><STRONG>推荐图片</STRONG></H2>\n" +
                "\t  <DIV class=\"c_inner\">\n" +
                "\t     <UL class=\"pic\">\n" +
                "<LI><A href=\"/a/jgz/23271.html\" title=\"不知不觉又装了一\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170101/1-1F101120117.jpg\" style=\"background-size:100% 100%;\"><SPAN>不知不觉又装了一</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23206.html\" title=\"窃・格瓦拉打工是\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/161231/1-1612310UF1-lp.png\" style=\"background-size:100% 100%;\"><SPAN>窃・格瓦拉打工是</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/zt/22925.html\" title=\"可达鸭抱头一套图\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/161213/1-161213200537-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>可达鸭抱头一套图</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xbq/22350.html\" title=\"熊本熊一直乱跳\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/161104/1-161104145920.gif\" style=\"background-size:100% 100%;\"><SPAN>熊本熊一直乱跳</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/22276.html\" title=\"一定要冷静：打他\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/161102/1-161102144057-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>一定要冷静：打他</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/zt/21707.html\" title=\"PS版拿枪套图\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/161019/1-161019143405-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>PS版拿枪套图</SPAN></A></LI>\n" +
                "\n" +
                "        </UL>\n" +
                "\t<DIV class=\"z\"></DIV></DIV></DIV>\n" +
                "\t\n" +
                "    <DIV class=\"c_box c_box1\">\n" +
                "\t  <H2 class=\"title fColor\"><STRONG><A href=\"http://md.itlun.cn/a/new/\" target=\"_blank\">最新图片</A></STRONG><SMALL><A href=\"http://md.itlun.cn/a/new/\" target=\"_blank\">MORE>></A></SMALL></H2>  \n" +
                "  <DIV class=\"c_inner\">\n" +
                "\t  <UL class=\"text fLeft\">\n" +
                "\t     <STRONG class=\"bColor fWhite\">新闻</STRONG>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2313.html\" title=\"表情百科之彼尔德\" target=\"_blank\">表情百科之彼尔德</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/bangzhu/2868.html\" title=\"微信添加自定义金馆长表情图文教\" target=\"_blank\">微信添加自定义金馆长表情图文教</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/2414.html\" title=\"挖掘机技术哪家强段子分享\" target=\"_blank\">挖掘机技术哪家强段子分享</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2316.html\" title=\"表情百科之大苦Dukoo\" target=\"_blank\">表情百科之大苦Dukoo</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2183.html\" title=\"表情出处之菲律宾大叔\" target=\"_blank\">表情出处之菲律宾大叔</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2182.html\" title=\"表情出处之斯巴达300勇士\" target=\"_blank\">表情出处之斯巴达300勇士</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2314.html\" title=\"表情百科之黄溜溜Hololo\" target=\"_blank\">表情百科之黄溜溜Hololo</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/yingyong/15698.html\" title=\"QQ：对方撤回消息并摸了一把你的\" target=\"_blank\">QQ：对方撤回消息并摸了一把你的</A></LI>\n" +
                "<LI><A href=\"/a/xinwen/zhishi/2126.html\" title=\"表情出处之九门大提督\" target=\"_blank\">表情出处之九门大提督</A></LI>\n" +
                "\n" +
                "<br>\n" +
                " <STRONG class=\"bColor fWhite\">下载</STRONG>\n" +
                "<LI><A href=\"/a/xiazai/2312.html\" title=\"魔魔狗表情下载\" target=\"_blank\">魔魔狗表情下载</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2307.html\" title=\"比丢biu表情下载\" target=\"_blank\">比丢biu表情下载</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2305.html\" title=\"肉团十二生肖之金馆长表情一套\" target=\"_blank\">肉团十二生肖之金馆长表情一套</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/4009.html\" title=\"阿狮马金馆长表情包下载\" target=\"_blank\">阿狮马金馆长表情包下载</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2867.html\" title=\"金馆长版潘斯特无字动态表情96张\" target=\"_blank\">金馆长版潘斯特无字动态表情96张</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2310.html\" title=\"紧张的小白金馆长表情下载\" target=\"_blank\">紧张的小白金馆长表情下载</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2306.html\" title=\"肉团十二生肖之乐康版\" target=\"_blank\">肉团十二生肖之乐康版</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2122.html\" title=\"金馆长P图素材下载\" target=\"_blank\">金馆长P图素材下载</A></LI>\n" +
                "<LI><A href=\"/a/xiazai/2123.html\" title=\"金馆长精选表情一百多张-Q聊常用\" target=\"_blank\">金馆长精选表情一百多张-Q聊常用</A></LI>\n" +
                "\n" +
                "    </UL>\n" +
                "\t<DIV class=\"picbox fRight\">\n" +
                "\t   <UL class=\"pic\">\n" +
                "<LI><A href=\"/a/xbq/23556.html\" title=\"小葡萄竖大拇指\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU552.jpg\" style=\"background-size:100% 100%;\"><SPAN>小葡萄竖大拇指</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23541.html\" title=\"脑子里都是屎：很遗憾，这是你的\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT539.jpg\" style=\"background-size:100% 100%;\"><SPAN>脑子里都是屎：很遗憾，这是你的</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23542.html\" title=\"么么哒，可爱的孩子\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT607.jpg\" style=\"background-size:100% 100%;\"><SPAN>么么哒，可爱的孩子</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23543.html\" title=\"小黄狗吸饮料喝可乐:呐，给你\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT627.jpg\" style=\"background-size:100% 100%;\"><SPAN>小黄狗吸饮料喝可乐:呐，给你</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23554.html\" title=\"滑稽：将稽就稽，将计就计\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU412.jpg\" style=\"background-size:100% 100%;\"><SPAN>滑稽：将稽就稽，将计就计</SPAN></A></LI>\n" +
                "\n" +
                "          </UL>\n" +
                "\t\t <UL class=\"pic\">\n" +
                "<LI><A href=\"/a/jgz/23563.html\" title=\"踩在别人的体重称上面：快看，你\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU956.jpg\" style=\"background-size:100% 100%;\"><SPAN>踩在别人的体重称上面：快看，你</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23566.html\" title=\"突然兴奋：突然握草\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ329.jpg\" style=\"background-size:100% 100%;\"><SPAN>突然兴奋：突然握草</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23567.html\" title=\"厉害了我的表哥表姐三姑婆\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ400.jpg\" style=\"background-size:100% 100%;\"><SPAN>厉害了我的表哥表姐三姑婆</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/nhtp/23577.html\" title=\"一生下来JJ就长：七分天注定，三\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11Q91333-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>一生下来JJ就长：七分天注定，三</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23576.html\" title=\"戴的绿帖子光芒万丈：爱是一道光\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11Q91303-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>戴的绿帖子光芒万丈：爱是一道光</SPAN></A></LI>\n" +
                " \n" +
                "               </UL>\n" +
                "\t\t\t</DIV>\n" +
                "\t\t<DIV class=\"z\"></DIV></DIV></DIV>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t<DIV class=\"c_box c_box3\">\n" +
                "\t  <H2 class=\"title fColor\"><STRONG><A href=\"/a/jgz/\" target=\"_blank\">未分类</A></STRONG><SMALL><A href=\"/a/jgz/\" target=\"_blank\">MORE>></A></SMALL></H2>\n" +
                "\t  <DIV class=\"c_inner\">\n" +
                "\t    <DIV class=\"cbl fLeft\">\n" +
                "\t\t   <UL class=\"pic\">\n" +
                "    <div id='tag096e6ded3f35b7602a743d69ac7aeb4a'>\n" +
                "<LI><A href=\"/a/jgz/23542.html\" title=\"么么哒，可爱的孩子\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT607.jpg\"><SPAN>么么哒，可爱的孩子</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23543.html\" title=\"小黄狗吸饮料喝可乐\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT627.jpg\"><SPAN>小黄狗吸饮料喝可乐</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23554.html\" title=\"滑稽：将稽就稽，将\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU412.jpg\"><SPAN>滑稽：将稽就稽，将</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23563.html\" title=\"踩在别人的体重称上\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU956.jpg\"><SPAN>踩在别人的体重称上</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23566.html\" title=\"突然兴奋：突然握草\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ329.jpg\"><SPAN>突然兴奋：突然握草</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/23567.html\" title=\"厉害了我的表哥表姐\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ400.jpg\"><SPAN>厉害了我的表哥表姐</SPAN></A></LI>\n" +
                "    </div>\n" +
                "\n" +
                "                 </UL>\n" +
                "           </DIV>\n" +
                "\n" +
                "\t\t  <DIV class=\"z\"></DIV></DIV></DIV>\n" +
                "\n" +
                "\n" +
                "\t  <DIV class=\"c_box\">\n" +
                "\t   <H2 class=\"title fColor\"><STRONG><A href=\"http://md.itlun.cn/a/xmr/\" target=\"_blank\">熊猫人</A></STRONG><SMALL><A href=\"http://md.itlun.cn/a/xmr/\" target=\"_blank\">MORE>></A></SMALL></H2>\n" +
                "  <DIV class=\"c_inner\">\n" +
                "\t    <DIV class=\"cbl fLeft\">\n" +
                "\t\t   <UL class=\"pic\">\n" +
                "    <div id='tag49ab57c019074a80fe9a878787f03ce5'>\n" +
                "<LI><A href=\"/a/xmr/23545.html\" title=\"你果然和外面的娇艳\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QTG3-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>你果然和外面的娇艳</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xmr/23540.html\" title=\"一入图群深似海，遍\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT511-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>一入图群深似海，遍</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xmr/23534.html\" title=\"抱拳：且慢！\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170115/1-1F115223911.jpg\" style=\"background-size:100% 100%;\"><SPAN>抱拳：且慢！</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xmr/23533.html\" title=\"拿着五把剑：小心劳\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170115/1-1F115223S4-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>拿着五把剑：小心劳</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xmr/23486.html\" title=\"背后说悄悄话：你今\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112194423-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>背后说悄悄话：你今</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/xmr/23485.html\" title=\"给我个面子吧，好歹\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112194359-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>给我个面子吧，好歹</SPAN></A></LI>\n" +
                "    </div>\n" +
                "\n" +
                "      </UL>\n" +
                "<DIV class=\"z\"></DIV></DIV></DIV></DIV>\n" +
                "\n" +
                "\t  <DIV class=\"c_box\">\n" +
                "\t   <H2 class=\"title fColor\"><STRONG><A href=\"http://md.itlun.cn/a/dhrw/\" target=\"_blank\">动漫人物</A></STRONG><SMALL><A href=\"http://md.itlun.cn/a/dhrw/\" target=\"_blank\">MORE>></A></SMALL></H2>\n" +
                "\t   <DIV class=\"c_inner\">\n" +
                "\t    <UL class=\"pic\">\n" +
                "    <div id='tag423692e1fe0729b83dc55872469747f5'>\n" +
                "<LI><A href=\"/a/dhrw/23541.html\" title=\"脑子里都是屎：很遗\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QT539.jpg\" style=\"background-size:100% 100%;\"><SPAN>脑子里都是屎：很遗</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23574.html\" title=\"人家是鹦鹉OK？\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11Q91231-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>人家是鹦鹉OK？</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23573.html\" title=\"熊本熊：我可是是玩\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZR5-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>熊本熊：我可是是玩</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23569.html\" title=\"朕感觉很崩溃，奔溃\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ515-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>朕感觉很崩溃，奔溃</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23568.html\" title=\"雷电法王杨永信：你\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ432-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>雷电法王杨永信：你</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/23565.html\" title=\"特朗普：开心得像个\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QZ248-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>特朗普：开心得像个</SPAN></A></LI>\n" +
                "    </div>\n" +
                "\n" +
                "      </UL><DIV class=\"z\"></DIV>\n" +
                "</DIV></DIV>\n" +
                "\n" +
                "<DIV style=\"border: #C9C9C9 1px solid;border-top: none; width: 968px; padding: 1px;\">\n" +
                "\n" +
                "        </DIV>\n" +
                "\n" +
                "<div class=\"c_box\">\n" +
                "\t  <h2 class=\"title fColor\"><strong><A href=\"/a/gif\" target=\"_blank\">动态</A></strong><small></small></h2>\n" +
                "\t  <div class=\"c_inner\">\n" +
                "\t     <UL class=\"pic\">\n" +
                "<LI><a href=\"/a/gif/23503.html\" title=\"滑稽劳资抱着女管理非礼\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170115/1-1F115221911-lp.gif\" style=\"background-size:100% 100%;\"><span>滑稽劳资抱着女管理非礼</span></a></LI>\n" +
                "<LI><a href=\"/a/gif/23477.html\" title=\"端着咖啡被打的动物\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112193938.gif\" style=\"background-size:100% 100%;\"><span>端着咖啡被打的动物</span></a></LI>\n" +
                "<LI><a href=\"/a/gif/23482.html\" title=\"小黄狗吸饮料喝可乐：两杯喝饱了\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112194226.jpg\" style=\"background-size:100% 100%;\"><span>小黄狗吸饮料喝可乐：两杯喝饱了</span></a></LI>\n" +
                "<LI><a href=\"/a/gif/23488.html\" title=\"很多钱：进入盈利模式\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112194510.gif\" style=\"background-size:100% 100%;\"><span>很多钱：进入盈利模式</span></a></LI>\n" +
                "<LI><a href=\"/a/gif/23454.html\" title=\"怪怪的小鸡：我要是不呢？\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F1121920100-L.jpg\" style=\"background-size:100% 100%;\"><span>怪怪的小鸡：我要是不呢？</span></a></LI>\n" +
                "<LI><a href=\"/a/gif/23440.html\" title=\"妹子学僵尸跳，突然内容掉了\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170109/1-1F109194F7-lp.gif\" style=\"background-size:100% 100%;\"><span>妹子学僵尸跳，突然内容掉了</span></a></LI>\n" +
                "\n" +
                "           </UL><div class=z></div> </div> </div>\n" +
                "\n" +
                "<div class=\"c_box\">\n" +
                "\t  <h2 class=\"title fColor\"><strong><A href=\"/a/nhtp/\" target=\"_blank\">内涵图</A></strong><small></small></h2>\n" +
                "\t  <div class=\"c_inner\">\n" +
                "\t     <UL class=\"pic\">\n" +
                "<LI><a href=\"/a/nhtp/23550.html\" title=\"一男的拿迷药把妹子迷晕抱走后，\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170118/1-1F11QU043-lp.jpg\" style=\"background-size:100% 100%;\"><span>一男的拿迷药把妹子迷晕抱走后，</span></a></LI>\n" +
                "<LI><a href=\"/a/nhtp/23519.html\" title=\"温州一裸男排队买火车票被警察带\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170115/1-1F115223134-lp.jpg\" style=\"background-size:100% 100%;\"><span>温州一裸男排队买火车票被警察带</span></a></LI>\n" +
                "<LI><a href=\"/a/nhtp/23481.html\" title=\"真正的粉丝都，粉你麻痹，吾系李\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112194147-lp.jpg\" style=\"background-size:100% 100%;\"><span>真正的粉丝都，粉你麻痹，吾系李</span></a></LI>\n" +
                "<LI><a href=\"/a/nhtp/23467.html\" title=\"猜出来你就是神，一碗面，一个飞\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170112/1-1F112192924-lp.jpg\" style=\"background-size:100% 100%;\"><span>猜出来你就是神，一碗面，一个飞</span></a></LI>\n" +
                "<LI><a href=\"/a/nhtp/23409.html\" title=\"民警提示：年底案件高发，ATM取\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170109/1-1F109192317-lp.jpg\" style=\"background-size:100% 100%;\"><span>民警提示：年底案件高发，ATM取</span></a></LI>\n" +
                "<LI><a href=\"/a/nhtp/23400.html\" title=\"明天冬至，也就是入九。俗话说一\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/170107/1-1F10G91J2-lp.jpg\" style=\"background-size:100% 100%;\"><span>明天冬至，也就是入九。俗话说一</span></a></LI>\n" +
                "\n" +
                "           </UL><div class=z></div> </div> </div>\n" +
                "\n" +
                "\n" +
                "\t  <DIV class=\"c_box\">\n" +
                "\t   <H2 class=\"title fColor\"><STRONG><A href=\"/\" target=\"_blank\">随机推荐</A></STRONG><SMALL><A href=\"/\" target=\"_blank\">MORE>></A></SMALL></H2>\n" +
                "\t   <DIV class=\"c_inner\">\n" +
                "\t    <UL class=\"pic\">\n" +
                "    <div id='tagdfccd984e722d7a3578eaec8ceb9a18a'>\n" +
                "<LI><A href=\"/a/gif/6418.html\" title=\"群主查岗：一只狗站\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/150420/1-150420160P7-lp.gif\" style=\"background-size:100% 100%;\"><SPAN>群主查岗：一只狗站</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/18538.html\" title=\"兔子微笑：嘴角勾起\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/160802/1-160P2154559.jpg\" style=\"background-size:100% 100%;\"><SPAN>兔子微笑：嘴角勾起</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/dhrw/10990.html\" title=\"被打：我再也不装逼\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/151015/1-151015153042-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>被打：我再也不装逼</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/13879.html\" title=\"每日任务尿管理-前\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/160219/1-160219143220-lp.png\" style=\"background-size:100% 100%;\"><SPAN>每日任务尿管理-前</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/14453.html\" title=\"被人按在地上动不了\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/160311/1-160311154627-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>被人按在地上动不了</SPAN></A></LI>\n" +
                "<LI><A href=\"/a/jgz/16235.html\" title=\"楼上楼下：装逼可以\" target=\"_blank\"><IMG border=\"0\" src=\"http://img.itlun.cn/uploads/allimg/160519/1-160519142321-lp.jpg\" style=\"background-size:100% 100%;\"><SPAN>楼上楼下：装逼可以</SPAN></A></LI>\n" +
                "    </div>\n" +
                "\n" +
                "      </UL>\n" +
                "\t\t<DIV class=\"z\"></DIV></DIV></DIV>\n" +
                "\t<DIV class=\"l_box\"><SPAN><STRONG><a href=\"/link.html\" target=\"_blank\">友情链接</a></STRONG><SMALL> （友链邮箱：itlun@qq.com)</SMALL></SPAN>\n" +
                "\t  <UL>\n" +
                "<li><a href='http://chengyu.eduu.com/' target='_blank'>成语大全</a> </li><li><a href='http://school.aoshu.com' target='_blank'>中学库</a> </li><li><a href='http://biaoqing.oicq88.com/' target='_blank'>QQ表情</a> </li><li><a href='http://mdzz.itlun.cn/' target='_blank'>表情制作</a> </li><li><a href='http://www.luqq.net' target='_blank'>女生头像</a> </li><li><a href='http://www.youxiname.net' target='_blank'>游戏名字</a> </li><li><a href='http://cxt.iq2.cn' target='_blank'>诚信通托管</a> </li><li><a href='http://www.nv08.com' target='_blank'>游戏王者网</a> </li>\n" +
                "      </UL>\n" +
                "\t  <DIV class=\"z\"></DIV></DIV></DIV>\n" +
                "\n" +
                "\n" +
                "<br>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " <div id=\"foot\"><span class=\"foot_c\">&#169;2015&#160;妈蛋表情网md.itlun.cn&#160;<span><a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">湘ICP备16021352号</a>欢迎访问妈蛋表情网</span></span>\n" +
                "\n" +
                "</div></div></div>\n" +
                "\n" +
                "<div style=\"display:none\">\n" +
                "\n" +
                "<script>\n" +
                "  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
                "  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
                "  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
                "  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n" +
                "\n" +
                "  ga('create', 'UA-71276368-1', 'auto');\n" +
                "  ga('send', 'pageview');\n" +
                "\n" +
                "</script><script>\n" +
                "(function(){\n" +
                "   var src = (document.location.protocol == \"http:\") ? \"http://js.passport.qihucdn.com/11.0.1.js?634dcf46daf93cdc45d45c334f2dc489\":\"https://jspassport.ssl.qhimg.com/11.0.1.js?634dcf46daf93cdc45d45c334f2dc489\";\n" +
                "   document.write('<script src=\"' + src + '\" id=\"sozz\"><\\/script>');\n" +
                "})();\n" +
                "</script>\n" +
                "<script src=\"http://s19.cnzz.com/stat.php?id=5439572&web_id=5439572\" language=\"JavaScript\"></script></div>\n" +
                "\n" +
                "\n" +
                "</body></html>" );

    }
}
