package com.zyy.collection;

import com.yunji.oms.common.entity.DataResponse;
import com.yunji.scs.price.priceapplication.service.PriceApplicationPromotionServiceImpl;
import com.yunji.scs.price.priceapplication.vo.PriceApplicationVo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
* @Author  zsf
* @Description //价格系统  保价规则校验
* @Date 2018/10/29 14:34
* @Param
* @return
*/
public class CallableForCheckTest extends BaseTest{

    @Test
    public void testDeleteRedis() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
   /*     CallableForCheckTest.CallableForCheck task1 = new CallableForCheckTest.CallableForCheck(1);
        CallableForCheckTest.CallableForCheck task2 = new CallableForCheckTest.CallableForCheck( 2);
        CallableForCheckTest.CallableForCheck task3 = new CallableForCheckTest.CallableForCheck( 3);*/
        long beginTime = System.currentTimeMillis();
        List<FutureTask<String>> futureTaskList = new ArrayList<>();
        for (int i=1;i<4;i++) {
            CallableForCheck callable  = new CallableForCheck(i);
            FutureTask<String> futureTask = new FutureTask<>(callable);
            futureTaskList.add(futureTask);
            // 线程池开始获取数据
            executor.submit(futureTask);
        }

        List<String> resultDataList = new ArrayList<>();
        for (FutureTask<String> futureTask : futureTaskList) {
            try {
                // 获取数据，注意有超时时间，如果超出，即获取不到数据
                String result =  futureTask.get();
                resultDataList.add(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                // 最后一定要调用cancel方法，里面的参数 mayInterruptIfRunning 是是否在运行的时候也关闭，如果设置为true，那么在
                // 运行的时候也能关闭，之后的代码不会再执行。
                // 如果正在运行，暂停成功，会返回true，如果运行完了，那么不管 mayInterruptIfRunning 是什么值，都会返回false。
                futureTask.cancel(true);
                executor.shutdown();
            }

        }
        System.out.println("总用时 ------- " + (System.currentTimeMillis() - beginTime) + " ms");

       /* System.out.println("future1:"+future1.get());
        System.out.println("future2:"+future2.get());
        System.out.println("future3:"+future3.get());*/

    }

    private  void checkProtectionRules(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void checkBenchmarkPrice(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void priceCheck(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CallableForCheck implements Callable<String> {

        // private final long waitTime;
        private final int taskId;
       // private final PriceApplicationVo applicationVo;

        //构造器,applicationVo and taskId
        public CallableForCheck(int taskId) {
            //this.waitTime = waitTime;
            this.taskId = taskId;
        }

        //实现Callable接口的call方法,会在线程池submit(this)的时候调用本方法
        public String call() throws Exception {
            if(1 == this.taskId){//1.保价规则校验
                long checkProtectionRulesTime = System.currentTimeMillis();//方法开始时间
                checkProtectionRules();
                System.out.println("保价规则校验: "  + " 用时 --------"
                        + (System.currentTimeMillis() - checkProtectionRulesTime) + " ms");

                return "1";
            }
            else if(2 == this.taskId){//2.基准价校验
                long checkBenchmarkPriceTime = System.currentTimeMillis();//方法开始时间
                checkBenchmarkPrice();
                System.out.println("基准价校验: "  + " 用时 --------"
                        + (System.currentTimeMillis() - checkBenchmarkPriceTime) + " ms");
                return "2";
            }

            else if(3 == this.taskId){//3.价格校验
                long priceCheckTime = System.currentTimeMillis();//方法开始时间
                priceCheck();
                System.out.println("价格校验: "  + " 用时 --------"
                        + (System.currentTimeMillis() - priceCheckTime) + " ms");
                return "3";
            }
            /*System.out.println("Hello, this is task: "+this.taskId);
            Thread.sleep(this.waitTime);*/
            //return DataResponse.builderSuccess();
            return  "0";
        }

    }
}
