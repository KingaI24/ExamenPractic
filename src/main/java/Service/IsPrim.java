package Service;

public class IsPrim {

    public boolean isPrime(int n) {
        for (int i = 2; i*i < n; i++) {
            if (n % i == 0)
                return true;
        }
            return false;
        }
}
