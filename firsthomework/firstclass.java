import java.util.Scanner;

public class firstclass {
    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);

        System.out.println("请输入一串数字：");
        String[] nums1 = sc.nextLine().split(",");
        int[] num1=new int[nums1.length];
        for(int i=0;i<num1.length;i++){
            num1[i]=Integer.parseInt(nums1[i]);
        }
        sort(num1);
        System.out.println("排序后的结果：");
        for (int k : num1) {
            System.out.print(k);
            System.out.print(" ");
        }

        System.out.println("\n请输入另一串数字：");
        String[] nums2 = sc.nextLine().split(",");
        int[] num2=new int[nums2.length];
        for(int i=0;i<num2.length;i++){
            num2[i]=Integer.parseInt(nums2[i]);
        }
        int[] num=new int[num1.length+num2.length];
        System.arraycopy(num1,0,num,0,num1.length);
        System.arraycopy(num2,0,num,num1.length,num2.length);
        sort(num);
        System.out.println("合并后的结果：");
        for (int i : num) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
    public static void sort(int[] a){
        int k,temp;
        for(int i=0;i<a.length;i++){
            k=i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[k]){
                    k=j;
                }
            }
            if(k!=i){
                temp=a[k];
                a[k]=a[i];
                a[i]=temp;
            }
        }
    }
}
