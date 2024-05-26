    import java.util.Random;
    /**
     * This class generates a random string of a given length.
     * This class gets called in the add method of the AVLTree class
     * to generate a random string for the stock symbol.
     */
    public class RandomStringGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private final Random random;

        public RandomStringGenerator() {
            this.random = new Random();
        }

        public String generateRandomString(int length) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(CHARACTERS.length());
                stringBuilder.append(CHARACTERS.charAt(index));
            }
            return stringBuilder.toString();
        }

    }

