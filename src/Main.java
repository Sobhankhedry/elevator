public class Main {
    static int[] Floors = {0,1,2,3,4,5,6,7,8,9,10};
    public static void main(String[] args) {
        int x =ReachB(Floors,9,2,2,4);
        System.out.println(x);
    }

    private static int ReachB(int[] floors, int B, int A, int u, int d) {
        if((A>B) || (A==B)){
            return 0;
        }
        if(u+A > B){
            return 1+ ReachB(floors,B, A-d,u,d);
        }
        else {
            return 1 + ReachB(floors,B,A+u , u ,d);
        }
    }
}