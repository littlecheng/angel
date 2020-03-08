package com.weitao.reflectd;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Person {

    @Column(name = "姓名",value = "小红")
    private String name;

    @Column(name = "性别", value = "男")
    private String gender;

    @Column(name = "年龄", value = "28")
    private int age;

    @Column(name = "住址", value = "江苏省南京市玄武区")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        List<String> columNames = new ArrayList<String>();
        Class clazz = Class.forName("com.weitao.reflectd.Person");
        //获取Person类所有属性
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            //获取该属性的Colum注解
            Column colum = field.getAnnotation(Column.class);
            //或者可以先判断有无该注解
            field.isAnnotationPresent(Column.class);
            //将该属性通过注解配置好的中文含义取出来放到集合中
            columNames.add(colum.name());
            columNames.add(colum.value());
        }

        //打印集合
        columNames.forEach((columName) -> System.out.println(columName));

        Class class1 = Person.class;
        System.out.println(class1.getName());

        Class clas2 = Class.forName("com.weitao.reflectd.Person");
        System.out.println(clas2.getSimpleName());

      Field[] field =    clas2.getDeclaredFields();
        System.out.println(field.length);
        for (Field field1:field) {
            System.out.println(field1.getType() + "\t"+ field1.getName());
        }

        Method[] methods = clas2.getDeclaredMethods();

        for (Method m:methods) {
            System.out.println("\t"+ m.getReturnType() + "\t"+m.getName());
        }


        try {
           Person object =  (Person)clas2.newInstance();
            Method m = clas2.getDeclaredMethod("setName",String.class);
            m.setAccessible(true);
            m.invoke(object,"武汉");
            System.out.println(object.getName());

            System.out.println(".................................................................");

            Method m2 = clas2.getDeclaredMethod("getName");
            Object object2 = m2.invoke(object);
            System.out.println(object2);

        } catch (NoSuchMethodException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



    }
}
