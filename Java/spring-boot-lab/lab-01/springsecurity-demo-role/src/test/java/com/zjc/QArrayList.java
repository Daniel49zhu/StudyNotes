package com.zjc;

public class QArrayList<T> {

    //默认的数组的大小
    private final int DEFAULT_LIST_SIZE = 8;

    //存放数据的地方
    private Object[] mData;

    //下一个可以存放数据的当前数组的索引
    private int mSize;

    public QArrayList() {
        mData = new Object[DEFAULT_LIST_SIZE];
        mSize = 0;
    }

    public QArrayList(int capacity) {
        if (capacity <= 0 || capacity > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("invalid capacity");
        }

        mData = new Object[capacity];
    }

    public int size() {
        return mSize;
    }

    public int capacity() {
        return mData.length;
    }

    public void add(T e) {
        //规定不允许添加一个空元素
        if (e == null) {
            return;
        }

        //如果当前数组已经满了，扩容为原来数组的2倍
        if (mSize >= mData.length) {
            // 扩容
            resize();
        }

        //将添加的元素添加到数组中
        mData[mSize] = e;
        //同时 mSize++ 指向下一个可以存放数据的位置
        mSize++;
    }

    public T get(int position) {
        if (position < 0 || position >= mData.length) {
            throw new IllegalArgumentException("position is invalid");
        }

        return (T) mData[position];
    }

    public T remove(int position) {
        if (position < 0 || position >= mData.length) {
            throw new IllegalArgumentException("position is invalid");
        }

        T e = (T) mData[position];
        for (int i = position + 1; i < mData.length; i++) {
            mData[i - 1] = mData[i];
        }
        mSize--;
        return e;
    }

    public void resize() {
        Object[] old = mData;
        mData = new Object[mData.length * 2];
        for (int i = 0; i < old.length; i++) {
            mData[i] = old[i];
        }
        old = null;
    }
}
