import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class shareResource{
    private Lock lock =new ReentrantLock();
    private int flag =1;
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void print5(){
        lock.lock();
        try{
            while (flag!=1){
               c1.await();
            }
            for (int i = 0; i <5 ; i++) {
                final int num=i;
                System.out.println(Thread.currentThread().getName()+"\t"+num);
            }
            flag=2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    } public void print10(){
        lock.lock();
        try{
            while (flag!=2){
                c2.await();
            }
            for (int i = 0; i <10 ; i++) {
                final int num=i;
                System.out.println(Thread.currentThread().getName()+"\t"+num);
            }
            flag=3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    } public void print15(){
        lock.lock();

        try{
            while (flag!=3){
                c3.await();
            }
            for (int i = 0; i <15 ; i++) {
                final int num=i;
                System.out.println(Thread.currentThread().getName()+"\t"+num);
            }
            flag=1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

public class TextThread {
     public static void main(String[] args) {
         shareResource s= new shareResource();
         new Thread(()->{ for (int i = 0; i <5 ; i++) s.print5(); },"A").start();
         new Thread(()->{ for (int i = 0; i <5 ; i++)  s.print10(); },"B").start();
         new Thread(()->{ for (int i = 0; i <5 ; i++) s.print15(); },"C").start();

     }
}
