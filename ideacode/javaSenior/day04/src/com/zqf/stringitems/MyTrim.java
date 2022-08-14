package com.zqf.stringitems;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-13-20:16
 */
public class MyTrim {
    public static String mytrim(String str){
        char[] c1=str.toCharArray();
        int i=0,j=c1.length-1;
        if(c1[0]!=' '&&c1[c1.length-1]!=' '){
            return str;
        }
        while(c1[i]==' '){
            ++i;
        }
            while (c1[j] == ' ') {
                --j;
            }
        return str.substring(i,j+1);
    }
}
class MyTrimTest{
    public static void main(String[] args) {
        String s1="  abaaaaa";
        System.out.println("*****"+s1+"*******");
        System.out.println("******"+MyTrim.mytrim(s1)+"********");

    }
}