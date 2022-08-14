package com.zqf.domxml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author oscarzqf
 * @description
 * @create 2021-10-05-16:34
 */
public class Dom4jTest {
    @Test
    public void test1() {
        //创建一个SAXReader输入流，去读取xml配置文件，生成Document对象
        SAXReader saxReader = new SAXReader();
        try {
            Document document= saxReader.read("xml/book.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 读取books.xml文件生成book类
     */
    public void test2() throws Exception{
        //1.读取book.xml文件,单元测试时相对路径为当前moudle开始算
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("xml/book.xml");
        //2.通过Documnet元素获取根元素
        Element rootElement = document.getRootElement();
        //3.通过根元素获取book标签对象
        //element()和elements()都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        //4.遍历，处理每一个book标签转换为Book类
        for(Element book:books){
            //asXML()可以把标签对象转换为标签字符
            //根据标签名获取book中的标签
            Element nameElement = book.element("name");
            //getText()：可以获取标签中的文本内容
            String nameText=nameElement.getText();
            //elementText():可以直接获取指定标签名的内容
            String priceText = book.elementText("price");
            String authorText=book.elementText("author");
            //获取属性值
            String snValue = book.attributeValue("sn");
            //装入对象
            System.out.println(new Book(snValue,nameText,
                    Double.parseDouble(priceText),authorText));
        }
    }
}
