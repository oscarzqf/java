package com.zqf.stringitems;

/**
 * @author oscarzqf
 * @description  将一个字符串部分反转
 * @create 2021-08-13-20:05
 */
public class ReversePart {
    public static String reverse(String str,int start,int end){
        if(str==null){
            return null;
        }
        if(str.length()==0||str.length()==1){
            return str;
        }
        char[] c1=str.toCharArray();
        for(int i=start,j=end;i<j;++i,--j){
            char temp;
            temp=c1[i];
            c1[i]=c1[j];
            c1[j]=temp;
        }
        String str1=new String(c1);
        return str1;
    }
}
class ReversePartTest{
    public static void main(String[] args) {
        String str1="abcdefg";
        System.out.println(str1);
        str1=ReversePart.reverse(str1,2,5);
        System.out.println(str1);
    }
}