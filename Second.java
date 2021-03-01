import java.util.Scanner;

public class Second {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入主角名称（1-50）：");
        String name1 = scanner.nextLine();
        if(name1.length()>50 || name1.length()<1){
            System.out.print("输入数据有误，请重新输入：");
            name1 = scanner.nextLine();
        }
        System.out.println("请输入主角攻击力（1-999）：");
        int attack1 = scanner.nextInt();
        if(attack1>999 || attack1<1){
            System.out.print("输入数据有误，请重新输入：");
            attack1 = scanner.nextInt();
        }
        System.out.println("请输入主角生命值（1-999）：");
        int life1 = scanner.nextInt();
        if(life1>999 || life1<1){
            System.out.print("输入数据有误，请重新输入：");
            life1 = scanner.nextInt();
        }
        System.out.println("请输入主角防御力（1-999）：");
        int defense1 = scanner.nextInt();
        if(defense1>999 || defense1<1){
            System.out.print("输入数据有误，请重新输入：");
            defense1 = scanner.nextInt();
        }
        Human ren = new Human(name1,attack1,life1,defense1);
        scanner.nextLine();
        System.out.println("请输入怪兽名称：");
        String name2 = scanner.nextLine();
        System.out.println("请输入怪兽攻击力：");
        int attack2 = scanner.nextInt();
        System.out.println("请输入怪兽生命值：");
        int life2 = scanner.nextInt();
        Monster mon = new Monster(name2,attack2,life2);
        System.out.print("请输入攻击回合数：");
        int a = scanner.nextInt();
        for(int i=1;i<=a&&life1>0&&life2>0;i++){
           if(i%2!=0){
               System.out.println("回合"+i+":"+name1+"对"+name2+"发起攻击");
               System.out.println(name1+"对"+name2+"造成了"+attack1+"点伤害");
               life2=life2-attack1;
           }
           if(i%2==0){
               System.out.print("回合"+i+":"+name2+"对"+name1+"发起攻击");
               if(attack2-defense1>=1){
                   System.out.println(name2+"对"+name1+"造成了"+(attack2-defense1)+"点伤害");
                   life1=life1-(attack2-defense1);
               }else{
                   System.out.println(name2+"对"+name1+"造成了1点伤害");
                   life1=life1-1;
               }
           }
        }
        if(life1!=0 && life2!=0){
            System.out.println("游戏结束，"+name1+"剩余"+life1+"点生命值；"+name2+"剩余"+life2+"点生命值.");
        }else if(life1==0 && life2!=0){
            System.out.println("游戏结束，"+name1+"死亡，"+name2+"胜利!");
        }else if(life2==0 && life1!=0){
            System.out.println("游戏结束，"+name2+"死亡，"+name1+"胜利!");
        }else{
            System.out.println("游戏结束，两者都死亡，平局。");
        }
    }
}
