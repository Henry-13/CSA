### 使用泛型自定义实现Arraylist
#### 添加了一个无参构造方法和一个有参构造方法来为数组分配空间
必须定义一个有参构造方法，如无参数传入令无参构造方法来调用有参构造方法初始化数组长度
```
public BST(){
        this(10);
    }
    public BST(int capacity){
        data=(T[])new Object[capacity];
    }
```
#### 实现数组动态加长，添加元素add
需先判断数组长度是否够用，不够用则需先同通过定义一个新的数组来扩充数组
```
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
```
#### 实现向数组中指定位置添加元素
需先判断数组长度是否够用，不够用则需先同通过定义一个新的数组来扩充数组
```
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
```
#### 实现删除数组元素
```
 public void deldata(int a){
        for(int i=a;i<size;i++)
        {
            data[i]=data[i+1];
        }
    }
```
#### 实现数据元素查询
```
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
```
#### 获取数组长度
```
public int getSize(){
        return size;
    }
```
