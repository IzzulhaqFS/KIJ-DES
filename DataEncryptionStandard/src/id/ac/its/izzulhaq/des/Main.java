package id.ac.its.izzulhaq.des;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DES cipher = new DES();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Plain Text");
        String text = scanner.next();
        String plainText = stringToHex(text);
        System.out.println("Plain Text: " + plainText);

        System.out.println("Enter Key 64-bit");
        String keyText = scanner.next();
        String key = stringToHex(keyText);
        System.out.println("Key: " + key);

        if (plainText.length() != 16) {
            int n = ((plainText.length() / 16) + 1) * 16;
            StringBuilder sb = new StringBuilder();
            sb.append(plainText);

            while (sb.length() < n) {
                sb.append("0");
            }

            plainText = sb.toString();
            String temp;
            String cipherText = "";
            System.out.println("Encryption\n");
            for (int i = 0; i < n; i += 16) {
                temp = plainText.substring(i, i + 16);
                System.out.println("\nPlain Text " + ((i / 16) + 1) + " -> " + temp.toUpperCase());
                temp = cipher.encrypt(temp, key);
                System.out.println("\nCipher Text " + ((i / 16) + 1) + " -> " + temp.toUpperCase());
                cipherText += temp;
            }
            System.out.println("\nCipher Text " + cipherText.toUpperCase());

            System.out.println("Decryption\n");
            String decryptedText = "";
            for (int i = 0; i < n; i += 16) {
                temp = cipherText.substring(i, i + 16);
                System.out.println("\nCipher Text " + ((i / 16) + 1) + " -> " + temp.toUpperCase());
                temp = cipher.decrypt(temp, key);
                System.out.println("\nPlain Text " + ((i / 16) + 1) + " -> " + temp.toUpperCase());
                decryptedText += temp;
            }
            System.out.println("\nPlain Text " + decryptedText.toUpperCase());
        }

        else {
            System.out.println("Encryption\n");
            plainText = cipher.encrypt(plainText, key);
            System.out.println("\nChiper Text: " + plainText.toUpperCase() + "\n");

            System.out.println("Decryption\n");
            plainText = cipher.decrypt(plainText, key);
            System.out.println("\nPlain Text: " + plainText.toUpperCase() + "\n");
        }
    }

    private static String stringToHex(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();

        for (char aChar : chars) {
            String hexString = Integer.toHexString(aChar);
            sb.append(hexString);
        }
        String result = sb.toString();

        return result;
    }
}