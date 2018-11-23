public class SnakeGame {
    /**
     * Design a Snake game that is played on a device with screen size = width x
     * height. Play the game online if you are not familiar with the game.
     * 
     * The snake is initially positioned at the top left corner (0,0) with length =
     * 1 unit.
     * 
     * You are given a list of food's positions in row-column order. When a snake
     * eats the food, its length and the game's score both increase by 1.
     * 
     * Each food appears one by one on the screen. For example, the second food will
     * not appear until the first food was eaten by the snake.
     * 
     * When a food does appear on the screen, it is guaranteed that it will not
     * appear on a block occupied by the snake.
     */

    /**
      * 
      Given width = 3, height = 2, and food = [[1,2],[0,1]].

        Snake snake = new Snake(width, height, food);

        Initially the snake appears at position (0,0) and the food at (1,2).

        |S| | |
        | | |F|

        snake.move("R"); -> Returns 0

        | |S| |
        | | |F|

        snake.move("D"); -> Returns 0

        | | | |
        | |S|F|

        snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

        | |F| |
        | |S|S|

        snake.move("U"); -> Returns 1

        | |F|S|
        | | |S|

        snake.move("L"); -> Returns 2 (Snake eats the second food)

        | |S|S|
        | | |S|

        snake.move("U"); -> Returns -1 (Game over because snake collides with border)   
      */


    int[][] food;
    int width;
    int height;
    int foodIndex;

    int score;
    LinkedList<Integer> snake; // when snake took a food, remove the tail and add head
    Set<Integer> snakeCopy; // exact copy of the snake only, but to check if its gona eat itself

    /**
     * Initialize your data structure here.
     * 
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
     *               first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;

        this.score = 0;
        this.snake = new LinkedList<>();
        this.snake.addFirst(new Integer(0)); // we are saving the snakes location form 2D to 1D
        this.snakeCopy = new HashSet<>();
        this.snakeCopy.add(new Integer(0));
    }

    /**
     * Moves the snake.
     * 
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over
     *         when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        if (score == -1) {
            return -1; // the game is already over
        }

        int currentRow = this.snake.peekFirst() / this.width;
        int currentColumn = this.snake.peekFirst() % this.width;

        switch (direction) {
        case "U":
            currentRow--;
            break;
        case "D":
            currentRow++;
            break;
        case "L":
            currentColumn--;
            break;
        default:
            currentColumn++;
        }

        int newHead = currentRow * this.width + currentColumn;

        // Check if it has crossed the border or bitten itself, but
        // before that remove the tail since it would have move one location
        // we are just removing from the copy for validation purpose
        this.snakeCopy.remove(this.snake.peekLast());

        // Now lets check
        if (currentRow < 0 || currentRow == height || currentColumn < 0 || currentColumn == width
                || this.snakeCopy.contains(newHead)) {
            return score = -1;
        }

        // now lets make the move in the game
        this.snakeCopy.add(newHead);
        this.snake.addFirst(newHead);

        // Now if we have found a food in this place, we need to grow, so the old tail
        // still stays
        // since we removed it from the copy during the validation add it back
        if (foodIndex < food.length && currentRow == food[foodIndex][0] && currentColumn == food[foodIndex][1]) {
            this.snakeCopy.add(this.snake.peekLast());
            foodIndex++;
            score += 1;
            return score;
        }

        this.snake.pollLast();
        return score;

    }
}