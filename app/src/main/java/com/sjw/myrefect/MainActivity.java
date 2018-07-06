package com.sjw.myrefect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //第一种，任何一个类都有一个隐含的静态成员变量class
        Class c1 = Person.class;

//第二种，已经知道该类的对象，通过getClass()获得
        Person person = new Person();
        Class c2 = person.getClass();

//第三种，Class类的forName()方法
        Class c3 = null;
        try {
            c3 = Class.forName("com.sjw.myrefect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//这里，c1,c2,c3都是Class类的实例，我们称c1, c2 ,c3为Person类的类类型
//不难看出，c1 == c2结果是true, c2 == c3结果也是true

        e(c1.equals(c2) + "");
        e(c2.equals(c3) + "");


        try {

            //实例化的三种方式
            Person personA = new Person(); //直接new一个实例

            Person personB = Person.class.newInstance(); //通过newInstance()方法获得Person的实例

//            //在学习JAVAEE时候，newInstance()方法我们最常见于获取数据库驱动
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Person personC = (Person) Class.forName("com.sjw.myrefect.Person").newInstance();


//需要注意的是，在使用newInstance()方法的前提是该类必须要有无参构造方法
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void e(String msg) {

        Log.e("MainActivity", msg);
    }

    //执行public方法
    public void invokeM(View view) {

        //要获取print(int, int)
        //要获取类的方法就要获取类的信息，获取类的信息就要获取类的类类型
        A a1 = new A();
        Class c = a1.getClass();
        //2,获取方法 名称和参数列表
        //getMethod获取的是public的方法
        try {
            Method m = c.getDeclaredMethod("print", int.class, int.class);

            //方法的反射操作
            //a1.print(10, 20);方法的反射操作，用m来进行方法调用和前者效果一致
            Object obj = m.invoke(a1, 10, 20);//如果方法有返回值返回值，没有就null
            Toast.makeText(this, "结果是" + obj, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //执行私有的方法
    public void invokeM2(View view) {
        //要获取print(int, int)
        //要获取类的方法就要获取类的信息，获取类的信息就要获取类的类类型
        A a1 = new A();
        Class c = a1.getClass();
        //2,获取方法 名称和参数列表
        //getMethod获取的是public的方法
        try {
            Method m = c.getDeclaredMethod("print", String.class, String.class);

            m.setAccessible(true);
            //方法的反射操作
            //a1.print(10, 20);方法的反射操作，用m来进行方法调用和前者效果一致
            Object obj = m.invoke(a1, "pRI", "vaTe");//如果方法有返回值返回值，没有就null
            Toast.makeText(this, "结果是" + obj, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //返回所有有的方法  方法名称  方法传入参数  方法返回参数
    public void invokeM3(View view) {
        A a = new A();
        ClassUtil.getClassMethodMessage(a);

    }

    //返回所有的成员变量
    public void invokeM4(View view) {
        A a = new A();
        ClassUtil.getFieldMessage(a);

    }


    //返回所有的构造方法
    public void invokeM5(View view) {

        A a = new A();
        ClassUtil.printConMessage(a);
    }

    public void invokeM6(View view) {


        // 获取字节码文件对象
        try {
            Class c = Class.forName("android.view.MotionEvent");
//            // 获取私有带参构造方法对象
//            // NoSuchMethodException:没有这个方法异常
//            // 原因是我们一开始使用的方法只能获取公共的,下面这种方式就可以了。
//            // Constructor con = c.getConstructor(String.class);
            Constructor con = c.getDeclaredConstructor();
//
//            // 通过私有带参构造方法对象创建对象
//            // IllegalAccessException:非法的访问异常
//            // 暴力访问
            con.setAccessible(true);// 值为true则指示反射的对象在使用时应该取消Java语言访问检查。

            Object obj = con.newInstance();
            System.out.println(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
