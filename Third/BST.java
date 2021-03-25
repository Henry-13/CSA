public class BST<T> {
    private T[] data;
    private int size=0;
    public BST(){
        this(10);
    }
    public BST(int capacity){
        data=(T[])new Object[capacity];
    }
    //向数组中添加元素
    public void add(T value){
        if(size==data.length)
        {
            T[] newArray=(T[])new Object[2*size+1];
            //将老数组拷贝到新数组内
            System.arraycopy(data, 0, newArray, 0, data.length);
            //再将新数组赋值给老数组
            data=newArray;
        }
        data[size++]=value;
    }
    //向数组中指定位置添加元素
    public void adddata(int a,T s){
        if(size==data.length)
        {
            T[] newArray=(T[])new Object[2*size+1];
            //将老数组拷贝到新数组内
            System.arraycopy(data, 0, newArray, 0, data.length);
            //再将新数组赋值给老数组
            data=newArray;
        }
        for(int i=size;i>=a;i--)
        {
            data[i]=data[i-1];
        }
        data[a]=s;
        size++;
    }
    //删除数组元素
    public void deldata(int a){
        for(int i=a;i<size;i++)
        {
            data[i]=data[i+1];
        }
    }
    //查询某一数值第一次出现位置
    public int finddata(T s){
        int flag=0;
        int i;
        for(i=0;i<size&&flag==0;i++)
        {
            if(data[i]==s)
            {
                flag=1;
            }
        }
        return i;
    }
    //获取数组长度
    public int getSize(){
        return size;
    }
}
