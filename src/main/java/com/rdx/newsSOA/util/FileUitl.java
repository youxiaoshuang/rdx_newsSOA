package com.rdx.newsSOA.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

public class FileUitl {

    private static Logger logger =  LoggerFactory.getLogger( FileUitl.class );

    /**
     * 将文件转成base64 字符串
     *
     * @param
     * @return *
     * @throws Exception
     */

    public static String encodeBase64File(String path) throws Exception {
        File file = new File( path );
        ;
        FileInputStream inputFile = new FileInputStream( file );
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read( buffer );
        inputFile.close();
        return new BASE64Encoder().encode( buffer );

    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer( base64Code );
        FileOutputStream out = new FileOutputStream( targetPath );
        out.write( buffer );
        out.close();

    }

    /**
     * 将base64字符保存文本文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(String base64Code, String targetPath)
            throws Exception {
        if (base64Code != null && targetPath != null) {
            //对字节数组字符串进行Base64解码并生成图片
                BASE64Decoder decoder = new BASE64Decoder();
            try {
                //Base64解码
                byte[] b = decoder.decodeBuffer( base64Code );
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {//调整异常数据
                        b[i] += 256;
                    }
                }
                //生成文件
                OutputStream out = new FileOutputStream( targetPath );
                out.write( b, 0, b.length );
                out.flush();
                out.close();
            } catch (Exception e) {
            }
        }else{
            logger.info( "保存文件失败" );
        }
    }


    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void main(String[] args) {
        try {
            String base64Code = encodeBase64File( "D:/0101-2011-qqqq.tif" );
            System.out.println( base64Code );
            decoderBase64File( base64Code, "D:/2.tif" );
            toFile( base64Code, "D:\\three.txt" );
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}