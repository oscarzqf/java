package com.zqf.stringitems;

import org.junit.Test;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-14-9:57
 */
public class SearchLargeString {
    public String search(String str1,String str2){
        if(str1==""||str2==""){
            return "";
        }
        String longStr=str1.length()>=str2.length()?str1:str2;
        String shortStr=str1.length()<str2.length()?str1:str2;
        for (int i = 0; i <shortStr.length(); i++) {
                for(int j=0;j+shortStr.length()-i<=shortStr.length();++j){
                    String temp=shortStr.substring(j,j+shortStr.length()-i);
                    if(longStr.indexOf(temp)>=0){
                        return temp;
                    }
                }
            }
        return null;
    }
    @Test
    public void test(){
        System.out.println(search("abccccccccwerthello000yuiodef","ccccccccvhello000bnm"));
    }
}
