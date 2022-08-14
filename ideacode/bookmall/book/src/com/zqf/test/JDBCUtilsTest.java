package com.zqf.test;

import com.zqf.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author oscarzqf
 * @description
 * @create 2021-10-23-17:03
 */
public class JDBCUtilsTest {
    @Test
    public void testjdbcutils() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
        JDBCUtils.close(conn);
    }

}
