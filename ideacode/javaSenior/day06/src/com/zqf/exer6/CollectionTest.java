package com.zqf.exer6;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @author oscarzqf
 * @description 1111
 * @create 2021-08-15-18:36
 */
public class CollectionTest {
    public static void main(String[] args) {
       /* //Collection接口中方法的使用
        Collection coll=new ArrayList();
        //1.add(Object e):将元素e添加到集合coll中
        coll.add("aa");
        coll.add(22);

        //2.int  size():获取添加的元素个数
        System.out.println(coll.size());

        //3.addAll(Collection coll):将一个集合中的元素添加到当前集合
        Collection coll1=new ArrayList();
        coll1.add(11);
        coll1.add("abc");
        coll.addAll(coll1);

        //4.clear():清空集合元素
        coll.clear();

        //5.boolean isEmpty():判断当前集合是否为空(size=0)
        coll.isEmpty();

        //6.boolean  contains(Object obj):判断当前集合是否包含obj,本质调用obj对象的equals()方法
        //比较内容还是地址需按equals()判断,向Collection接口实现类的对象中添加数据obj时，
        //要求obj所在的类要重写equals()
        coll.contains(22);

        //7.boolean   containsAll(Collection coll):判断形参集合中所有元素是否都在当前集合中
        coll.containsAll(coll1);

        //8.boolean remove(Object obj):true删除成功，false失败，从当前集合删除obj元素
        coll.remove(22);

        //9.boolean removeAll(Collection coll1):差集:从当前集合移除参数集合相同的所有元素
        coll.removeAll(coll1);

        //10.retainAll(Collection coll1):交集:获取当前集合与参数集合的交集，保存给当前集合
        coll.retainAll(coll1);

        //11.boolean  equals(Object obj):判断当前集合和形参集合元素是否相同
        coll.equals(coll1);

        //12.hashCode():返回当前对象的哈希值
        System.out.println(coll.hashCode());

        //13.集合--->数组：toArray()
        Object[] arr=coll.toArray();
        //拓展:数组--->集合:调用Arrays类的静态方法asList()
        //如果是基本类型数组会识别为一个数组元素，需要传入包装类数组
        List list= Arrays.asList(new String[]{"11","abc"});
            */
        //14.iterator():返回Iterator接口的实例，用于遍历集合元素。
        /*Collection coll2=new ArrayList();
        coll2.add(66);
        coll2.add(33);
        coll2.add(22);
        LinkedList list=new LinkedList();
        Vector v1=new Vector();
        //for(集合元素的类型 局部变量 ：集合对象)
        //内部仍然调用了迭代器
        for (Object obj:coll2){
            System.out.println(obj);
        }*/
        ArrayList list =new ArrayList();
        list.add(12);
        list.add(13);
        list.add(55);
        list.add(1);
        //1.void add(int index,Object ele):在index位置插入ele
        list.add(3,50);

        //2.boolean addAll(int index,Collection eles):
        //从index开始将集合eles中所有元素添加到当前集合
        List list1=Arrays.asList(1,2,3);
        list.addAll(1,list1);
        System.out.println(list);

        //3.Object get(index):获取索引为index位置的元素
        System.out.println(list.get(0));

        //4.int indexOf(Object obj):返回obj在集合中首次出现的位置，没有返回-1

        //5.int lastIndexOf(Object obj)

        //6.Object remove(int index):
        //移除指定index位置的元素，并返回此元素
        list.remove(2);

        //7.Object set(int index,Object ele):设置指定位置的元素为ele
        list.set(0,222);
        System.out.println(list);

        //8.List subList(int fromIndex,int toIndex)
        //返回[fromIndex,toIndex)子集合
        List subList=list.subList(1,4);
        System.out.println(subList);
        TreeSet trs=new TreeSet();
        trs.add(130);
        trs.add(222);
        trs.add(1);
        System.out.println(trs);

    }
}
