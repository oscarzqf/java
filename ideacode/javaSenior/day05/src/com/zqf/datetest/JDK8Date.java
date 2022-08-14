package com.zqf.datetest;

/**
 * @author oscarzqf
 * @description  111
 * @create 2021-08-14-12:21
 */
public class JDK8Date {
    public static void main(String[] args) {

    }
}
//使用enum关键字定义枚举类，父类为java.lang.Enum
enum Season{
    //1.提供当前枚举类的对象,多个对象之间用“,”隔开，末尾用“;”结束
    SPRING("春天","春暖花开"),
    SUMMER("夏天","烈日炎炎");
    //2.声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //2.私有化类的构造器，并给对象赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName=seasonName;
        this.seasonDesc=seasonDesc;
    }

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }
    //其他诉求2：选择重写toString()方法,不重写默认为输出类的名字
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}