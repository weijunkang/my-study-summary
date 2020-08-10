package com.weijunkang.miaosha;

import java.util.concurrent.*;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/7 16:20
 */
public class Test {

    final int x;
    int y = 2;


    Test() {
        x = 2;
        if (this.x == 0) {
            System.out.println(666);
        }
        if (this.y == 3) {
            System.out.println(333);
        }
//        System.out.println(Thread.currentThread().getId());
        y = 3;

    }

    void aa() {
//        x =3;
    }

/*    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5000, 10000, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10000000; i++) {
            tpe.execute(Test::new);
        }
        tpe.shutdown();
        System.out.println(11);
    }*/


    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<500000;i++){
            Test.State state = new Test.State();
            ThreadA threadA=new ThreadA(state);
            ThreadB threadB=new ThreadB(state);
            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();
        }
    }

    static class ThreadA extends Thread{

        private final Test.State state;

        ThreadA(Test.State state) {
            this.state = state;
        }

        public void run(){
            state.a=1;
            state.b=1;
            state.c=1;
            state.d=1;
        }
    }

    static class ThreadB extends Thread{

        private final Test.State state;

        ThreadB(Test.State state) {
            this.state = state;
        }

        public void run(){
            if(state.b==1 && state.a==0){
                System.out.println("b=1");
            }

            if(state.c==1 && (state.b==0 || state.a == 0)){
                System.out.println("c=1");
            }

            if(state.d==1 && (state.a==0 || state.b==0 || state.c==0)){
                System.out.println("d==1");
            }
        }
    }

    static class State {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
    }


}