/**
 * Created by linbin on 6/29/2017.
 */
public class Solution {
    Solution() {}

    // solution 1: recursion
    public long A2powerB1(int a, int b) {
        if (b == 0)
            return 1;

        return A2powerB1(a, b -1) * a;
    }

    // solution 2: loop
    public long A2powerB2(int a, int b) {
        long res = 1;
        for (int i = b; i > 0; --i) {
            res *= a;
        }

        return res;
    }

    // solution3: lg(b) time complexity
    public long A2powerB3(int a, int b) {

        if (b == 0) {
            return 1;
        }
        long half = A2powerB3(a, b/2);
        if (b % 2 == 0){
            return half * half;
        } else {
            return half * half * a;
        }
    }

    public static void main(String []argv){
        Solution solu = new Solution();

        System.out.println("Recursion: " );
        System.out.println(solu.A2powerB1(5, 5));
        System.out.println(solu.A2powerB1(10000, 0));
        System.out.println(solu.A2powerB1(-3, 9));

        System.out.println("For loop: " );
        System.out.println(solu.A2powerB2(5, 5));
        System.out.println(solu.A2powerB2(10000, 0));
        System.out.println(solu.A2powerB2(-3, 9));

        System.out.println("Devide and conquer: " );
        System.out.println(solu.A2powerB3(5, 5));
        System.out.println(solu.A2powerB3(10000, 0));
        System.out.println(solu.A2powerB3(-3, 9));
    }

}
