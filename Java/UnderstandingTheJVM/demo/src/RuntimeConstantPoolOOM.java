import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author makoto
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        //10MB的PermSize在integer范围内足够产生OOM了

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }


    }
}
