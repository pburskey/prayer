package scratch;

public class CharacterHelper {
    public static final char[] lowerCaseAlphabet = new char[26];

    static {
        char a = Character.valueOf('a');
        for (int i = 0; i < lowerCaseAlphabet.length; i++) {
            int value = a;
            value += i;
            char current = (char) value;
            lowerCaseAlphabet[i] = current;
        }
    }


}

