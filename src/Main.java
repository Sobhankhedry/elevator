import java.util.Arrays;

public class Main {
    static int[] Floors = {0, 1, 2, 3, 4};
    static int[] r = new int[5];


    public static void main(String[] args) {
        System.out.println("min jumps : ");
        System.out.println(ReachB(Floors, r, 4, 3, 2, 1));


        System.out.println("");
        System.out.println("");
        for (int i = 0; i < r.length; i++) {
                System.out.print(r[i] + " ");
            System.out.println("");
        }
    }

    private static int ReachB(int[] Floors,int[] r, int B, int A, int u, int d) {
        if(A==B){
            r[A] = 1;
            System.out.println("helloooo");
            return 0;
        }
        if(A>B){
            return 10000;
        }
        if(A<0){
            return 10000;
        }
        if(r[A] > 0){
            return Integer.MAX_VALUE;
        }
        int p = 1+ ReachB(Floors,r,B,A+u,u,d);
        int q = 1+ ReachB(Floors,r,B,A-d,u,d);

        if(p<q){
            r[A] = p;
            return p;
        }
        else {
            r[A]=q;
            return q;
        }
    }
}
