package com.zqf.blob;

import com.zqf.bean.Customer;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-23-21:07
 */
public class BlobTest {
    //添加
    @Test
    public void testBlobTest()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="insert into customers(name,email,birth,photo) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"张张张");
            ps.setString(2,"228@qq.com");
            ps.setString(3,"1998-04-03");
            FileInputStream fs = new FileInputStream("head.jpeg");
            ps.setBlob(4,fs);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    //读取方式
    @Test
    public void readBlob()  {
        InputStream binaryStream=null;
        FileOutputStream fs1=null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql="select id,name,email,birth,photo from customers where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,22);
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Customer cust = new Customer(id, name, email, birth);
                //将Blob类型的字段下载下来,以文件的形式保存在本地
                Blob photo = resultSet.getBlob("photo");
                binaryStream = photo.getBinaryStream();
                fs1 = new FileOutputStream("head1.jpeg");
                byte[] buffer=new byte[1024];
                int len;
                while((len=binaryStream.read(buffer))!=-1){
                    fs1.write(buffer,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,resultSet);

            try {
                if(binaryStream!=null)
                binaryStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fs1!=null)
                fs1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
