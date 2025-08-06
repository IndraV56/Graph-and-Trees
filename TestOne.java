import java.util.*;
public class TestOne{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        int k=scanner.nextInt();
        int j=scanner.nextInt();
        int m=scanner.nextInt();
        int p=scanner.nextInt();
        if(k==0 || j==0){
            System.out.println("INVALID INPUT");
            return;
        }
        int s1=m/j;
        int s2=p/k;
        int s=s1+s2;
        System.out.println("No of Monkeys Remaining: "+(n-s));
    }
}