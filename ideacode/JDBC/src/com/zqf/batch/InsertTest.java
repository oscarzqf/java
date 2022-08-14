package com.zqf.batch;

import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author oscarzqf
 * @description
 *          使用PreparedStatement实现批量数据的操作
 *          update/delete本身就具有批量操作的效果
 *          此时的批量操作主要指的是批量插入,如何实现高效的批量插入操作
 * @create 2021-09-23-22:02
 */
public class InsertTest {
    @Test
    //批量插入方式一：使用preparedStatement
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();
            String sql="insert into testbatch(name) values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <= 20000; i++) {
                ps.setObject(1,"name_"+i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }
    }
    /*    方式二
     * 修改1： 使用 addBatch() / executeBatch() / clearBatch()
     * 修改2：mysql服务器默认是关闭批处理的，我们需要通过一个参数，让mysql开启批处理的支持。
     * 		 ?rewriteBatchedStatements=true 写在配置文件的url后面
     * 修改3：使用更新的mysql 驱动：mysql-connector-java-5.1.37-bin.jar
     *
     */
    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();

            conn = JDBCUtils.getConnection();

            String sql = "insert into testbatch(name)values(?)";
            ps = conn.prepareStatement(sql);

            for(int i = 1;i <= 20000;i++){
                ps.setString(1, "name_" + i);

                //1.“攒”sql
                ps.addBatch();
                if(i % 500 == 0){
                    //2.执行
                    ps.executeBatch();
                    //3.清空
                    ps.clearBatch();
                }
            }

            long end = System.currentTimeMillis();
            System.out.println("花费的时间为：" + (end - start));//20000条：625                                                                         //1000000条:14733
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
    //批量插入操作方式三：
    //设置连接不允许自动提交数据
    @Test
    public void testInsert3() throws Exception{
        long start = System.currentTimeMillis();

        Connection conn = JDBCUtils.getConnection();
        //设置不允许自动提交数据
        conn.setAutoCommit(false);
        String sql = "insert into testbatch(name)values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for(int i = 1;i <=10000;i++){
            ps.setString(1, "name_" + i);

            //1.“攒”sql
            ps.addBatch();
            if(i % 500 == 0){
                //2.执行
                ps.executeBatch();
                //3.清空
                ps.clearBatch();
            }
        }
        //提交数据
        conn.commit();
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));//20000条：625                                                                         //1000000条:14733

        JDBCUtils.closeResource(conn, ps);
    }
}
