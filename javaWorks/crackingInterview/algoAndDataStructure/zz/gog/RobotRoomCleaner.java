public class RobotRoomCleaner {

    /**
     * Given a robot cleaner in a room modeled as a grid.
     * 
     * Each cell in the grid can be empty or blocked.
     * 
     * The robot cleaner with 4 given APIs can move forward, turn left or turn
     * right. Each turn it made is 90 degrees.
     * 
     * When it tries to move into a blocked cell, its bumper sensor detects the
     * obstacle and it stays on the current cell.
     * 
     * Design an algorithm to clean the entire room using only the 4 given APIs
     * shown below.
     */

    public void cleanRoom(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        Set<String> set = new HashSet<>();
        int cur_dir = 0; // 0: up, 90: right, 180: down, 270: left
        backtrack(robot, set, 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> set, int i, int j, int cur_dir) {
        String tmp = i + "->" + j;
        if (set.contains(tmp)) {
            return;
        }

        robot.clean();
        set.add(tmp);

        for (int n = 0; n < 4; n++) {
            // the robot can to four directions, we use right turn
            if (robot.move()) {
                // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
                switch (cur_dir) {
                case 0:
                    // go up, reduce i
                    x = i - 1;
                    break;
                case 90:
                    // go right
                    y = j + 1;
                    break;
                case 180:
                    // go down
                    x = i + 1;
                    break;
                case 270:
                    // go left
                    y = j - 1;
                    break;
                default:
                    break;
                }

                backtrack(robot, set, x, y, cur_dir);
                // go back to the starting position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnRight();
                robot.turnRight();

            }
            // turn to next direction
            robot.turnRight();
            cur_dir += 90;
            cur_dir %= 360;
        }

    }
}