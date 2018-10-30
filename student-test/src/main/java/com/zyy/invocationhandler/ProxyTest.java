package com.zyy.invocationhandler;

import java.lang.reflect.InvocationHandler;

public class ProxyTest {

    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();

        MyInvocationHandler myInvocationHandler=new MyInvocationHandler(userService);

        UserService userService1= (UserService) myInvocationHandler.getProxy();
        String name=userService1.getUser();
        System.out.println(name);
    }
}
