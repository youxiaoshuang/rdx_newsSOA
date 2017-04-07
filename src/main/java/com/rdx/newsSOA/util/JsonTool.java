package com.rdx.newsSOA.util;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class JsonTool {
    private static final Logger log = LoggerFactory.getLogger(JsonTool.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonTool() {
    }

    public static HashMap<String, String> toHashMap(String json)
    {
        Object object = JSON.parse( json );
        HashMap<String, String> data = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            String value = (String) jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        String json = "[{\"albumName\":\"单车二势力\",\"hdType\":0,\"albumPicUrl\":\"http://image4.tudou.com/data/imgs/i/004/978/374/p.jpg\",\"update\":\"更新第集\",\"albumDesc\":\"albumDesc\",\"actors\":[],\"directors\":[],\"categories\":[],\"area\":{\"id\":1,\"name\":\"其他\"},\"albumShortDesc\":null,\"date\":\"2010-07-20\",\"itemTile\":\"单车二势�?010-07-20\",\"itemUrl\":\"http://4978374\",\"itemPicUrl\":\"http://image4.tudou.com/data/imgs/i/004/978/374/p.jpg\",\"itemShortDesc\":\"恶魔就是在你身边\",\"itemDesc\":\"d\",\"year\":2009},{\"albumName\":\"热线追踪\",\"hdType\":0,\"albumPicUrl\":\"http://image7.tudou.com/data/imgs/i/002/110/067/m15.jpg\",\"update\":\"更新第集\",\"albumDesc\":\"albumDesc\",\"actors\":[],\"directors\":[],\"categories\":[],\"area\":{\"id\":1,\"name\":\"其他\"},\"albumShortDesc\":\"test2\",\"date\":\"2006-08-13\",\"itemTile\":\"热线追踪2006-08-13\",\"itemUrl\":\"http://2110067\",\"itemPicUrl\":\"http://image7.tudou.com/data/imgs/i/002/110/067/m15.jpg\",\"itemShortDesc\":\"\",\"itemDesc\":\"s\",\"year\":2009},{\"albumName\":\"热线追踪\",\"hdType\":0,\"albumPicUrl\":\"http://image7.tudou.com/data/imgs/i/002/110/137/m5.jpg\",\"update\":\"更新第集\",\"albumDesc\":\"albumDesc\",\"actors\":[],\"directors\":[],\"categories\":[],\"area\":{\"id\":1,\"name\":\"其他\"},\"albumShortDesc\":\"test2\",\"date\":\"2006-08-13\",\"itemTile\":\"热线追踪2006-08-13\",\"itemUrl\":\"http://2110137\",\"itemPicUrl\":\"http://image7.tudou.com/data/imgs/i/002/110/137/m5.jpg\",\"itemShortDesc\":\"\",\"itemDesc\":\"2\",\"year\":2009}]";
        new ObjectMapper();
    }

    public static String writeListValueAsString(Collection<? extends Jsonable> jsonableList) {
        LinkedList list = new LinkedList();
        Iterator i$ = jsonableList.iterator();

        while(i$.hasNext()) {
            Jsonable jsonable = (Jsonable)i$.next();
            list.add(jsonable.getMap4Json());
        }

        return writeValueAsString((Object)list);
    }

    public static String writeValueAsString(Jsonable jsonable) {
        return writeValueAsString((Object)jsonable.getMap4Json());
    }

    public static String writeValueAsString(Object obj) {
        if(obj instanceof Jsonable) {
            return writeValueAsString((Jsonable)obj);
        } else {
            try {
                return mapper.writeValueAsString(obj);
            } catch (JsonGenerationException var2) {
                var2.printStackTrace();
                throw new IllegalArgumentException(var2.getMessage());
            } catch (JsonMappingException var3) {
                var3.printStackTrace();
                throw new IllegalArgumentException(var3.getMessage());
            } catch (IOException var4) {
                var4.printStackTrace();
                throw new IllegalArgumentException(var4.getMessage());
            }
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException(var3.getMessage());
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
            throw new IllegalArgumentException(var4.getMessage());
        } catch (IOException var5) {
            var5.printStackTrace();
            throw new IllegalArgumentException(var5.getMessage());
        }
    }

    public static ArrayList<Map<String, Object>> readValue2List(String json) {
        ArrayList list = null;

        try {
            list = (ArrayList)mapper.readValue(json, TypeFactory.collectionType(ArrayList.class, Map.class));
            return list;
        } catch (JsonParseException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException(var3.getMessage());
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
            throw new IllegalArgumentException(var4.getMessage());
        } catch (IOException var5) {
            var5.printStackTrace();
            throw new IllegalArgumentException(var5.getMessage());
        }
    }

    public static <T> ArrayList<T> readValue2List(String json, Class<T> clazz) {
        try {
            return (ArrayList)mapper.readValue(json, TypeFactory.collectionType(ArrayList.class, clazz));
        } catch (JsonParseException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException(var3.getMessage());
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
            throw new IllegalArgumentException(var4.getMessage());
        } catch (IOException var5) {
            var5.printStackTrace();
            throw new IllegalArgumentException(var5.getMessage());
        }
    }

    public static <T> ArrayList<T> readValue2List(String json, TypeReference<List<T>> typeReference) {
        try {
            return (ArrayList)mapper.readValue(json, typeReference);
        } catch (JsonParseException var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException(var3.getMessage());
        } catch (JsonMappingException var4) {
            var4.printStackTrace();
            throw new IllegalArgumentException(var4.getMessage());
        } catch (IOException var5) {
            var5.printStackTrace();
            throw new IllegalArgumentException(var5.getMessage());
        }
    }

    public static <K, V> Map<K, V> readValue2Map(String json, Class<K> keyClazz, Class<V> valueClazz) {
        try {
            return (Map)mapper.readValue(json, TypeFactory.mapType(Map.class, keyClazz, valueClazz));
        } catch (JsonParseException var4) {
            var4.printStackTrace();
            throw new IllegalArgumentException(var4.getMessage());
        } catch (JsonMappingException var5) {
            var5.printStackTrace();
            throw new IllegalArgumentException(var5.getMessage());
        } catch (IOException var6) {
            var6.printStackTrace();
            throw new IllegalArgumentException(var6.getMessage());
        }
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    static {
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }



}