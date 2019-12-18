import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadText {
     public static void main(String[] args) {
         List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();
         for (int i=1;i<=3 ;i++){
             new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                 System.out.println(list);
             },String.valueOf(i)).start();
         }
         Set<String> set =new CopyOnWriteArraySet<>();
         for (int i=1;i<=3 ;i++){
             new Thread(()->{
                 set.add(UUID.randomUUID().toString().substring(0,6));
               System.out.println(set);
             },String.valueOf(i)).start();
         }
         Map<String,String> map = new ConcurrentHashMap<>();
         for (int i=1;i<=3 ;i++){
             final int temp=i;
             new Thread(()->{
                 map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,6));
                 System.out.println(map);
             },String.valueOf(i)).start();
         }
     }
}
