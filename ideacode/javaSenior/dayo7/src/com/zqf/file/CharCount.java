package com.zqf.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author oscarzqf
 * @description       统计文件中各种字符的数量
 * @create 2021-08-19-17:12
 */
public class CharCount {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        BufferedReader bis1=null;
        try {
            bis1=new BufferedReader(new FileReader(new File("hello.txt")));
            //复制的细节：读取、写入
            //方式一
            int len=0;
            while((len= bis1.read())!=-1){
                char ch=(char)len;
                    if(map.containsKey(ch)==false){
                        map.put(ch,1);
                    }else{
                        int valueNew=map.get(ch)+1;
                        map.put(ch,valueNew);
                    }
                }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭
            try {
                if(bis1!=null)
                    bis1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Set<Map.Entry<Character, Integer>> entry = map.entrySet();
        Iterator<Map.Entry<Character, Integer>> iterator = entry.iterator();
        while(iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            System.out.println(next.getKey()+"...."+next.getValue());
        }

    }
}
