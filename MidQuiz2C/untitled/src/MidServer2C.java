import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

public class MidServer2C extends UnicastRemoteObject implements MidInterface2C {

    public MidServer2C() throws RemoteException {}

    @Override
    public String dotw(int m, int d, int y) throws RemoteException {
        if (m > 12 || m < 1 || d < 1 || d > 31) {
            return "invalid";
        }

        int[] dayOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(y)) {
            dayOfMonths[1] = 29;
        }

        if (d > dayOfMonths[m - 1]) {
            return "invalid";
        }

        Calendar cal = new Calendar.Builder().setDate(y, m - 1, d).build();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        String week = switch (dayOfWeek) {
            case Calendar.SUNDAY -> "Sunday";
            case Calendar.MONDAY -> "Monday";
            case Calendar.TUESDAY -> "Tuesday";
            case Calendar.WEDNESDAY -> "Wednesday";
            case Calendar.THURSDAY -> "Thursday";
            case Calendar.FRIDAY -> "Friday";
            case Calendar.SATURDAY -> "Saturday";
            default -> "Invalid";
        };

        return week;
    }

    @Override
    public int age(Calendar calendar) throws RemoteException {
        Calendar currentDate = Calendar.getInstance();

        if (currentDate.after(calendar)) {
            return 0;
        }

        int currentYear = currentDate.get(Calendar.YEAR);
        int birthYear = calendar.get(Calendar.YEAR);

        return currentYear - birthYear;
    }

    @Override
    public boolean isLeapYear(int year) throws RemoteException {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            MidInterface2C stub = new MidServer2C();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("midquiz", stub);

            System.out.println("OK NA DOL");

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
