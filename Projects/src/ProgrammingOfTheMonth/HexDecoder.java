package ProgrammingOfTheMonth;

public class HexDecoder {
    public static void main(String[] args) {
        Main lobby = new Main();
        String input = lobby.readFile().toString();

        // Convert hex string to byte array
        byte[] decodedBytes = hexStringToByteArray(input);

        // Convert byte array to String
        String decodedString = new String(decodedBytes);

        System.out.println("Decoded String: " + decodedString);
    }

    // Helper method to convert hex string to byte array
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}

