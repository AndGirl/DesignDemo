package com.ybj.singletondesigndemo;

import java.util.HashMap;

/**
 * Created by 杨阳洋 on 2018/1/15.
 */

public class SingletonManager {

    public static Object getObjMap(String key) {
        return objMap.get(key);
    }

    public void setObjMap(String key,Object obj) {
        if(!objMap.containsKey(key)) {
            objMap.put(key,obj);
        }
    }

    private static HashMap<String, Object> objMap = new HashMap<String, Object>();

    private SingletonManager() {
    }


}
