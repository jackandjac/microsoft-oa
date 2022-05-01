public class Pow {
    public double myPow(double x, int n){
        long nn = n;
        if(n<0){
            x = 1/x;
            nn = -nn;
        }

        return fastPow(x,nn);
    }

    private double fastPow(double x, long y){
        if(y == 0 ) return 1.0;
        double fast = fastPow(x, y/2);
        if(y%2 == 0){
            return fast * fast;
        }else{
            return fast * fast * x;
        }

    }
}
