package io.github.dug22.scatteredsquarecipher;

import java.util.Map;

public class ScatteredSquareCipherMain {

    public static void main(String[] args) {
        ScatteredSquareCipher scatteredSquareCipher = new ScatteredSquareCipher();
        String message = "HELLO WORLD";
        String key = "PQOWIEURM0";
        System.out.println(key.length());
        Map<Character, int[]> keyCharacterGridPositions = Map.ofEntries(
                Map.entry('P', new int[]{1, 2}),
                Map.entry('Q', new int[]{2, 1}),
                Map.entry('O', new int[]{3, 3}),
                Map.entry('W', new int[]{1, 3}),
                Map.entry('I', new int[]{4, 3}),
                Map.entry('E', new int[]{3, 2}),
                Map.entry('U', new int[]{4, 2}),
                Map.entry('R', new int[]{4, 1}),
                Map.entry('M', new int[]{2, 5}),
                Map.entry('0', new int[]{1, 5})
        );
        String encryptedMessage = scatteredSquareCipher.encrypt(message, key, keyCharacterGridPositions);
        String decryptedMessage = scatteredSquareCipher.decrypt(encryptedMessage, key, keyCharacterGridPositions);
        System.out.println("Original Text: " + message);
        System.out.println("Key: " + key);
        System.out.println("Encrypted Text: " + encryptedMessage);
        System.out.println("Decrypted Text: " + decryptedMessage);
    }
}
