public class ValidSentenceChecker {

    public static boolean isValidSentence(String input) {
        // Rule 0: String is not empty.
        if (input.isEmpty()) {
            return false;
        }

        // Rule 1: String starts with a capital letter.
        if (!Character.isUpperCase(input.charAt(0))) {
            return false;
        }

        // Rule 2: String has an even number of balanced quotation marks.
        int quoteCount = input.replaceAll("[^\"]", "").length();
        if (quoteCount % 2 != 0) {
            return false;
        }

        // Rule 3: String ends with one of the specified sentence termination characters.
        char lastChar = input.charAt(input.length() - 1);
        if (lastChar != '.' && lastChar != '?' && lastChar != '!') {
            return false;
        }

        // Rule 4: String has no period characters other than the last character.
        if (input.substring(0, input.length() - 1).contains(".")) {
            return false;
        }

        // Rule 5: Numbers below 13 are spelled out.
        String[] words = input.split("\\s+");
        for (String word : words) {
            // Check if the word is a numeric value separated by commas.
            if (word.contains(",")) {
                String[] numValues = word.split(",");
                for (String num : numValues) {
                    if (isNumeric(num.trim()) && Integer.parseInt(num.trim()) < 13) {
                        return false;
                    }
                }
            } else {
                // Check if the word is a numeric value.
                if (isNumeric(word) && Integer.parseInt(word) < 13) {
                    return false;
                }
            }
        }

        // All rules passed, the sentence is valid.
        return true;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isValidSentence("The quick brown fox said “hello Mr lazy dog”.")); // true
        System.out.println(isValidSentence("The quick brown fox said \"hello Mr. lazy dog\".")); // false
        System.out.println(isValidSentence("One lazy dog is too few, 12 is too many.")); // false
        System.out.println(isValidSentence("One lazy dog is too few, thirteen is too many.")); // true
        System.out.println(isValidSentence("How many \"lazy dogs\" are there?")); // true

        // Additional test case for an empty string
        System.out.println(isValidSentence("")); // false
    }
}
