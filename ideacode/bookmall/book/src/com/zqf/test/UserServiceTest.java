package src.com.zqf.test;

import com.zqf.pojo.User;
import com.zqf.service.UserService;
import com.zqf.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author oscarzqf
 * @description
 * @create 2021-11-29-12:48
 */
public class UserServiceTest {

    @Test
    public void registerUser() {
        UserService userService=new UserServiceImpl();
        userService.registerUser(new User(null,"bbbb","666","2283@qqcom"));
    }

    @Test
    public void login() {
        UserService userService=new UserServiceImpl();
        System.out.println(userService.login(new User(null,"admin","admin",null)));
    }

    @Test
    public void existUsername() {
        UserService userService=new UserServiceImpl();
        if(userService.existUsername("admin")==false){
            System.out.println("用户名可以使用");
        }else{
            System.out.println("用户名不可用");
        }
    }
}