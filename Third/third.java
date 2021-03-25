public class third {
    public static void main(String[] args) {
        BST<String> a=new BST<>(3);
        a.add("hello");
        a.add("world");
        System.out.println("a内拥有："+a.getSize()+"个元素");
    }
}
