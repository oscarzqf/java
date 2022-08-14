package jabc.next.dao;

import com.zqf.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author oscarzqf
 * @description
 * 此接口用于规范针对于customers表的常用操作
 *
 * @create 2021-09-27-22:42
 */
public interface CustomerDAO{
    /**
     * 将cust对象添加到数据库中
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * 针对于指定的id，删除表中的一条记录
     * @param conn
     * @param id
     */
    void deleteById(Connection conn,int id);

    /**
     * 针对于内存中的cust对象，修改数据库中干的数据
     * @param conn
     * @param cust
     */
    void update(Connection conn,Customer cust);

    /**
     *针对指定的ID查询得到对应的customer对象
     * @param conn
     * @param id
     */
    Customer getCustomerById(Connection conn,int id);

    /**
     * 查询表中的所有记录构成的集合
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * 返回数据表中的数据的条目数
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    /**
     * 返回数据表中的最大的生日
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);

}
