package com.rdx.newsSOA.util;

import java.io.*;

public class SerializeTool {
    public static String object2String(Object obj) {
        String objBody = null;
        ByteArrayOutputStream baops = null;
        ObjectOutputStream oos = null;


        try {
            baops = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baops);
            oos.writeObject(obj);
            byte[] bytes = baops.toByteArray();
            objBody = new String(bytes);
        } catch (IOException e) {

        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baops != null)
                    baops.close();
            } catch (IOException e) {
            }
        }
        return objBody;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T getObjectFromString
            (String objBody, Class<T> clazz)

    {
        byte[] bytes = objBody.getBytes();
        ObjectInputStream ois = null;
        T obj = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = (T) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } finally {

            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
            }
        }

        return obj;
    }

}  