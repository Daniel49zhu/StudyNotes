package com.zjc;

public class QHashMap<K, V> {
    //默认的数组的大小
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //默认的扩容因子，当数据中元素的个数越多时，hash冲突也容易发生
    //所以，需要在数组还没有用完的情况下就开始扩容
    //这个 0.75 就是元素的个数达到了数组大小的75%的时候就开始扩容
    //比如数组的大小是100，当里面的元素增加到75的时候，就开始扩容
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    //存放元素的数组
    private QEntry[] table;

    //数组中元素的个数
    private int size;

    public QHashMap() {
        table = new QEntry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * 1 参数key,value很容易理解
     * 2 返回V，我们知道，HashMap有一个特点，
     * 如果调用了多次 map.put("name","tom"); map.put("name","lilei");
     * 后面的值会把前面的覆盖，如果出现这种情况，返回旧值，在这里返回"tom"
     */
    public V put(K key, V value) {
        //1 为了简单，key不支持null
        if (key == null) {
            throw new RuntimeException("key is null");
        }
        //不直接用key.hashCode()，我们对key.hashCode()再作一次运算作为hash值
        //这个hash()的方法我是直接从HashMap源码拷贝过来的。可以不用关心hash()算法本身
        //只需要知道hash()输入一个数，返回一个数就行了。
        int hash = hash(key.hashCode());
        //用key的hash值和数组的大小，作一次映射，得到应该存放的位置
        int index = indexFor(hash, table.length);
        //看看数组中，有没有已存在的元素的key和参数中的key是相等的
        //相等则把老的值替换成新的，然后返回旧值
        QEntry<K, V> e = table[index];
        while (e != null) {
            //先比较hash是否相等，再比较对象是否相等，或者比较equals方法
            //如果相等了，说明有一样的key,这时要更新旧值为新的value,同时返回旧的值
            if (e.hash == hash && (key == e.key || key.equals(e.key))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            e = e.next;
        }
        //如果数组中没有元素的key与传的key相等的话
        //把当前位置的元素保存下来
        QEntry<K, V> next = table[index];
        //next有可能为null，也有可能不为null，不管是否为null
        //next都要作为新元素的下一个节点(next传给了QEntry的构造函数)
        //然后新的元素保存在了index这个位置
        table[index] = new QEntry<>(key, value, hash, next);
        //如果需要扩容，元素的个数大于 table.length * 0.75 (别问为什么是0.75，经验)
        if (size++ >= (table.length * DEFAULT_LOAD_FACTOR)) {
            resize();
        }
        return null;
    }


    public void resize() {
        //新建一个数组，大小为原来数组大小的2倍
        int newCapacity = table.length * 2;
        QEntry[] newTable = new QEntry[newCapacity];

        QEntry[] src = table;
//遍历旧数组，重新映射到新的数组中
        for (int j = 0; j < src.length; j++) {
            //获取旧数组元素
            QEntry<K, V> e = src[j];
            //释放旧数组
            src[j] = null;
            //因为e是一个链表，有可能有多个节点，循环遍历进行映射
            while (e != null) {
                //把e的下一个节点保存下来
                QEntry<K, V> next = e.next;
                //e这个当前节点进行在新的数组中映射
                int i = indexFor(e.hash, newCapacity);
                //newTable[i] 位置上有可能是null，也有可能不为null
                //不管是否为null，都作为e这个节点的下一个节点
                e.next = newTable[i];
                //把e保存在新数组的 i 的位置
                newTable[i] = e;
                //继续e的下一个节点的同样的处理
                e = next;
            }
        }
        //所有的节点都映射到了新数组上，别忘了把新数组的赋值给table
        table = newTable;
    }


    public V get(K key) {
        //同样为了简单，key不支持null
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        //对key进行求hash值
        int hash = hash(key.hashCode());
        //用hash值进行映射，得到应该去数组的哪个位置上取数据
        int index = indexFor(hash, table.length);

        QEntry<K, V> e = table[index];
        while (e != null) {
            //比较 hash值是否相等
            if (hash == e.hash && (key == e.key || key.equals(e.key))) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }


    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int length) {
        //或者  return h & (length-1) 性能更好
        //这里我们用最容易理解的方式，对length取余数，范围就是[0,length - 1]
        //正好是table数组的所有的索引的范围
        h = h > 0 ? h : -h; //防止负数
        return h % length;
    }


    static class QEntry<K, V> {
        K key;
        V value;
        int hash;

        QEntry<K, V> next;

        QEntry(K key, V value, int hash, QEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }
}
