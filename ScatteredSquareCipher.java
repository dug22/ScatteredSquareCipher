public class ScatteredSquareCipher {

    private final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String encrypt(String plaintext, String key, Map<Character, int[]> keyCharacterGridPositions){
        return encryptOrDecrypt(plaintext, key, keyCharacterGridPositions, true);
    }

    public String decrypt(String ciphertext, String key, Map<Character, int[]> keyCharacterGridPositions){
        return encryptOrDecrypt(ciphertext, key, keyCharacterGridPositions, false);
    }

    public String encryptOrDecrypt(String text, String key, Map<Character, int[]> keyCharacterGridPositions, boolean encrypt) {
        text = text.toUpperCase();
        key = key.toUpperCase();
        if (text.replaceAll("[^A-Z0-9]", "").length() != key.replaceAll("[^A-Z0-9]", "").length()) {
            System.out.println("Both the plain text length and key length must be the same");
            return null;
        }

        char[][] extendedPolybiusSquare = createExtendedPolybiusSquare(key, keyCharacterGridPositions);
        StringBuilder resultingText = new StringBuilder();
        int keyIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char textCharacter = text.charAt(i);
            char keyCharacter = key.charAt(keyIndex);
            int[] textCharacterCoordinates = findTextCharacterCoordinates(textCharacter, extendedPolybiusSquare);
            if (textCharacterCoordinates == null) {
                resultingText.append(textCharacter);
                continue;
            }

            int GRID_SIZE = 6;
            int currentIndex = textCharacterCoordinates[0] * GRID_SIZE + textCharacterCoordinates[1];
            int shiftAmount = getKeyValueIndex(keyCharacter);
            int newIndex;
            int TOTAL_CELLS = GRID_SIZE * GRID_SIZE;
            if (encrypt) {
                newIndex = (currentIndex + shiftAmount) % TOTAL_CELLS;
            } else {
                newIndex = (currentIndex - shiftAmount + TOTAL_CELLS) % TOTAL_CELLS;
            }
            int resultingRow = newIndex / GRID_SIZE;
            int resultingCol = newIndex % GRID_SIZE;
            resultingText.append(extendedPolybiusSquare[resultingRow][resultingCol]);
            keyIndex++;
        }

        printExtendedPolybiusSquare(extendedPolybiusSquare);

        return resultingText.toString();
    }

    public void printExtendedPolybiusSquare(char[][] extendedPolybiusSquare){
        System.out.println("-- 6x6 Polybius Square --");
        for (int i = 0; i < 6; i++) {
            System.out.print("|");
            for (int j = 0; j < 6; j++) {
                System.out.printf(" %c |", extendedPolybiusSquare[i][j]);

            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    private void insertKeyCharactersIntoSquare(char[][] polybiusSquare, char character, int row, int col) {
        int min = 0;
        int max = 5;
        if (row >= min && row <= max && col >= min && col <= max) {
            polybiusSquare[row][col] = character;
        }
    }

    private char[][] createExtendedPolybiusSquare(String key, Map<Character, int[]> keyCharacterPositions) {
        int size = 6;
        char[][] extendedPolybiusSquare = new char[size][size];
        char[] keyCharacters = key.toCharArray();
        for (char keyCharacter : keyCharacters) {
            insertKeyCharactersIntoSquare(extendedPolybiusSquare, keyCharacter, keyCharacterPositions.get(keyCharacter)[0], keyCharacterPositions.get(keyCharacter)[1]);
        }

        fillPolybiusSquare(extendedPolybiusSquare);

        return extendedPolybiusSquare;
    }

    private void fillPolybiusSquare(char[][] extendedPolybiusSquare) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (extendedPolybiusSquare[i][j] == '\u0000') {
                    while (count < ALLOWED_CHARACTERS.length()) {
                        char characterToPlace = ALLOWED_CHARACTERS.charAt(count);
                        if (!isCharacterUsed(extendedPolybiusSquare, characterToPlace)) {
                            extendedPolybiusSquare[i][j] = characterToPlace;
                            count++;
                            break;
                        }
                        count++;
                    }
                }
            }
        }
    }

    private boolean isCharacterUsed(char[][] extendedPolybiusSquare, char targetCharacter) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (extendedPolybiusSquare[i][j] == targetCharacter) {
                    return true;
                }
            }
        }

        return false;
    }

    private int[] findTextCharacterCoordinates(char textCharacter, char[][] extendedPolybiusSquare) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (extendedPolybiusSquare[i][j] == textCharacter) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private int getKeyValueIndex(char keyCharacter) {
        return ALLOWED_CHARACTERS.indexOf(keyCharacter);
    }
}
