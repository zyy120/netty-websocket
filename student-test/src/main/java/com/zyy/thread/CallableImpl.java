package com.zyy.thread;

import java.util.concurrent.Callable;

public class CallableImpl implements Callable {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        String th_name=Thread.currentThread().getName();
        System.out.println(th_name + "遭遇大规模敌军突袭...");

        System.out.println(th_name + "迅速变换阵型...");

        System.out.println(th_name + "极速攻杀敌军...");
        return "敌军损失惨重，我军大获全胜";
    }
}
