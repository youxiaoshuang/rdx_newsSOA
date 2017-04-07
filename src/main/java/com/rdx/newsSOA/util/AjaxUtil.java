package com.rdx.newsSOA.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by zhuangli on 2016/4/11.
 */
public class AjaxUtil
{
    private static Logger logger = Logger.getLogger(AjaxUtil.class);

    /**
     * 设置浏览器输出头
     */
    public static void setCacheHeader(HttpServletResponse resp, long cacheTime)
    {
        if (cacheTime <= 0)
        {
            setNoCacheHeader(resp);
            return;
        }
        long now = new Date().getTime();
        resp.setDateHeader("Expires", now + cacheTime);
        resp.setDateHeader("Last-Modified", now);
        resp.setHeader("Cache-Control", "max-age=" + (cacheTime / 1000));
    }

    public static void setNoCacheHeader(HttpServletResponse resp)
    {
        resp.setHeader("Pragma", "No-Cache");
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setDateHeader("Expires", 0);
    }

    public static void writeResponse(HttpServletRequest req, HttpServletResponse resp, String content)
    {
        writeResponse(req, resp, content, 0, null);
    }

    public static void writeResponse(HttpServletRequest req, HttpServletResponse resp, String content, String jsoncallback)
    {
        writeResponse(req, resp, content, 0, jsoncallback);
    }

    /**
     * 写ajax响应
     *
     * @param content
     */
    public static void writeResponse(HttpServletRequest req, HttpServletResponse resp, String content, long cacheTime, String jsoncallback)
    {
        String charset = req.getParameter("charset");
        PrintWriter writer = null;
        try
        {
            resp.setHeader("P3P", "CP=CAO PSA OUR");
            if (StringUtils.isNotBlank(charset))
            {
                resp.setCharacterEncoding(charset);
            } else
            {
                resp.setCharacterEncoding("UTF-8");
            }
            resp.setContentType("text/plain");
            setCacheHeader(resp, cacheTime);
            writer = resp.getWriter();
            writer.write(content);
        } catch (IOException e)
        {
            logger.error("writeResponse error:", e);
        } finally
        {
            IOUtils.closeQuietly(writer);
        }
    }
}
