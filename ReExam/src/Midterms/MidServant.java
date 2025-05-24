package Midterms;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MidServant extends UnicastRemoteObject implements MidExamA {

    public MidServant() throws RemoteException {}

    @Override
    public int sumResult(int begValue, int endValue) {
        if (begValue >= endValue) {
            return 0;
        }

        int result = 0;

        // Check if it's equals to 0
        if (begValue == 0) {
            begValue++;
        }

        for (int i = begValue; i < endValue; i++) {
            if (i >= 0) {
                if (i % 2 == 0) {
                    result += i;
                }
            }
        }

        return result;
    }

    // ito yung sagot ko and, mali ito umay guys
    @Override
    public int digitalRoot(int value) throws RemoteException {
        int n = value / 2;
        int j = n * n;
        if (j <= value) return n;
        return 0;
    }

    // ito yung tama sagot umay
    @Override
    public int digitalRoot2(int value) throws RemoteException {
        if (value == 0 || value == 1) return value;

        int left = 1, right = value, ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int square = mid * mid;

            if (square == value) return mid;
            if (square < value) {
                left = mid + 1;
                ans = mid; // Store the closest integer square root
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    // Kay Robe Abadecio
    @Override
    public int digitalRootNiRobe(int value) throws RemoteException {
        if (value <= 0) return 0;
        int x = 0;
        while((x * x) < value) x++;
        if (x * x > value) x -= 1;
        return x;
    }
}
