import java.util.Random;

public class RandomShuffle {

    /**
     * Write a method to shuffle a deck of cards. It must be a perfect shuffle-in
     * other words, each of the 52! permutations of the deck has to be equally
     * likely.
     * 
     * Assume that you are given a random number generator which is perfect.
     */

    void shuffleArraylteratively(int[] cards) {
        Random r = new Random();
        for (int i = 0; i < cards.length; i++) {
            int k = r.nextInt(cards.length);
            int temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
    }
}