import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int[] Floors = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static int[] r = new int[11];
    static int[] s =new int[40];
    static int n =s.length ;
    static int navigate = 0;


    public static void main(String[] args) {

        int q =ReachB(Floors, r, 10, 2, 2, 2);

        int x = 0;

        for (int i = 0; i < r.length; i++) {

            System.out.print(r[i]+" ");
        }

        System.out.println(" ");
        for (int i = 0; i < r.length; i++) {
            if(r[i]>0 & r[i] != 0 & r[i]!= Integer.MAX_VALUE){
                x = x+ r[i];
            }

        }
        System.out.println("min jumps : " + x );
        if (x == 0){
            System.out.println("ain't no way");
        }
        if(x!=0){
            s = Reverse(s);
            System.out.println("the route : ");
            for (int i = 0; i < s.length; i++) {
                if (s[i]!=0)
                    System.out.print(s[i]+ " ");


            }
        }

    }


    private static int[] Reverse(int[] s) {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = s[i];
            j = j - 1;
        }

        // printing the reversed array
        return b;
    }

    private static int ReachB(int[] Floors,int[] r, int B, int A, int u, int d) {
            if ( u == d){
                int y = ReachEqual(Floors,r,B,A,u,d);
                return y;
            }
            if(r[B]>0){
                return 0;
            }
            if(A==B){
                r[A] = Integer.MAX_VALUE;
                s[navigate]=B;
                navigate++;
                System.out.println("navigate is " );
                return 0;
            }
            if(A>B){
                r[A-u]=Integer.MAX_VALUE;
                return Integer.MAX_VALUE;
            }
            if(A<0){
                r[A+d] = Integer.MAX_VALUE;
                return Integer.MAX_VALUE;
            }
            if(r[A] > 0){
                System.out.println("You cant reach B");
                return Integer.MAX_VALUE;
            }

            int p =  ReachB(Floors,r,B,A+u,u,d);
            int q =  ReachB(Floors,r,B,A-d,u,d);

            if(p<q){
                r[A] = 1+ p;
                s[navigate]=A;
                navigate++;
                return p;
            }
            else{
                r[A]= 1 + q;
                s[navigate]=A;
                navigate++;
                return q;
            }
    }

    private static int ReachEqual(int[] floors, int[] r, int B, int A, int u, int d) {
        if(A==B){
            r[A] = Integer.MAX_VALUE;
            s[navigate]=B;
            navigate++;
            System.out.println("navigate is " );
            return 0;
        }
        if(A>B){
            r[A-u]=Integer.MAX_VALUE;
            return Integer.MAX_VALUE;
        }
        int p =  ReachB(Floors,r,B,A+u,u,d);
        r[A]= p+1;
        s[navigate] = A;
        navigate++;
        return p;
    }
}
