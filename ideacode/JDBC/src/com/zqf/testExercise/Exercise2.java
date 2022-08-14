package com.zqf.testExercise;

import com.zqf.bean.Student;
import com.zqf.util.JDBCUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author oscarzqf
 * @description
 * @create 2021-09-23-19:53
 */
public class Exercise2 {
    public  void ecerciseTest1() {
        Scanner scan =new Scanner(System.in);
        System.out.println("四/六级：");
        int s = scan.nextInt();
        scan.nextLine();
        System.out.println("身份证号：");
        String s1 = scan.nextLine();
        System.out.println("准考证号：");
        String s2 = scan.nextLine();
        System.out.println("学生姓名：");
        String s3 = scan.nextLine();
        System.out.println("所在城市：");
        String s4 = scan.nextLine();
        System.out.println("学生成绩：");
        int s5 = scan.nextInt();
        String sql="insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        int insertCount = JDBCUtils.update(sql,s,s1,s2,s3,s4,s5);
        if(insertCount>0){
            System.out.println("插入成功！");
        }else{
            System.out.println("插入失败！");
        }

    }
    @Test
    public void exerciseTest2() {
        while(true){
            Scanner scan = new Scanner(System.in);
            System.out.println("输入身份证号/准考证号：");
            String s = scan.nextLine();
            String sql = "select FlowID as flowID,Type as type," +
                    "IDCard,ExamCard examCard,StudentName name,Location location,Grade as " +
                    "grade from examstudent where IDCard=? or ExamCard=?";
            String sql2 = "delete from examstudent where IDCard=? or ExamCard=?";
            ArrayList<Student> list = JDBCUtils.getForList(Student.class, sql, s, s);
            if (list.size() == 0 || list == null) {
                System.out.println("查无此人，请重新输入");
            } else {
                int returnCount = JDBCUtils.update(sql2, s, s);
                if (returnCount > 0) {
                    System.out.println("删除成功");
                    break;
                } else {
                    System.out.println("删除失败");
                    break;
                }
            }

        }
    }
}
