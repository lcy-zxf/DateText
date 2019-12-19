import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountDownLatch {
     public static void main(String[] args)  {
//         java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(5);
//         for (int i=1;i<=5 ;i++){
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName()+"走人了");
//                countDownLatch.countDown();
//            },String.valueOf(i)).start();
//        }
//             countDownLatch.await();
//         System.out.println(Thread.currentThread().getName()+"\t 班长锁门");
//
//         CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println(Thread.currentThread().getName()+"\t 召唤金龙");});
//         for (int i=1;i<=7 ;i++){
//             final int tmpl=i;
//             new Thread(()->{
//                 System.out.println(Thread.currentThread().getName()+"收集到"+tmpl+"颗龙珠");
//                 try {
//                     cyclicBarrier.await();
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 } catch (BrokenBarrierException e) {
//                     e.printStackTrace();
//                 }
//             },String.valueOf(i)).start();
//         }
         Semaphore semaphore=new Semaphore(3);
         for (int i=1;i<= 10;i++){
             new Thread(()->{
                 boolean tag=false;
                try{
                    semaphore.acquire();
                    tag=true;
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位");
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if (tag){
                        semaphore.release();
                    }
                }
             },String.valueOf(i)).start();
         }
     }
}
