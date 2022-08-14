package com.zqf.stringitems;

import org.junit.Test;

/**
 * @author oscarzqf
 * @description
 * @create 2021-08-13-21:40
 */
public class StriingCount {

    public static int count(String findStr,String str){
        int i=0,count=0;
        while(true){
            if(str.indexOf(findStr,i)>=0){
                count++;
                i=str.indexOf(findStr,i)+findStr.length();
                str.indexOf(findStr,i);
            }else{
                break;
            }
        }

        return count;
    }
    @Test
    public void test(){
        System.out.println("666");
    }

}
class StringCountTest{
    public static void main(String[] args) {
        String str="abkkcadkabkebfkabkskab";
        System.out.println(StriingCount.count("ab",str));
    }
}