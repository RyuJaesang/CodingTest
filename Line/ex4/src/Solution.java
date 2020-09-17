class Solution {
    public static void main(String[] args) {

        int[][] maze = {{0, 1, 0, 1}, {0, 1, 0, 0}, {0, 0, 0, 0}, {1, 0, 1, 0}};

        int solution = solution(maze);
        System.out.println("solution = " + solution);
    }

    static int[] location = {0, 0};
    static String dir = "s";
    static int answer = 0;
    
    public static int solution(int[][] maze) {
        if (maze[0][1] == 0) {
            dir = "e";
        }

        while (!((location[0] == maze.length - 1) && (location[1] == maze.length - 1))) {
            if (isExistLeft(maze) && isExistFront(maze)) {
                turnRight();
            } else if (!isExistLeft(maze) && isExistFront(maze)) {
                turnLeft();
                move();
            } else if (isExistLeft(maze) && !isExistFront(maze)) {
                move();
            } else if (!isExistLeft(maze) && !isExistFront(maze)) {
                turnLeft();
                move();
            }
        }
        return answer;
    }

    private static void move() {
        switch (dir) {
            case "e":
                location[1] += 1;
                break;
            case "w":
                location[1] -= 1;
                break;
            case "s":
                location[0] += 1;
                break;
            case "n":
                location[0] -= 1;
                break;
        }
        answer++;
    }

    private static void turnRight() {
        switch (dir) {
            case "e":
                dir = "s";
                break;
            case "w":
                dir = "n";
                break;
            case "s":
                dir = "w";
                break;
            case "n":
                dir = "e";
                break;
        }
    }

    private static void turnLeft() {
        switch (dir) {
            case "e":
                dir = "n";
                break;
            case "w":
                dir = "s";
                break;
            case "s":
                dir = "e";
                break;
            case "n":
                dir = "w";
                break;
        }
    }

    private static boolean isExistLeft(int[][] maze) {
        switch (dir) {
            case "e":
                if (location[0] - 1 < 0) {
                    return true;
                }
                return maze[location[0] - 1][location[1]] == 1;
            case "w":
                if (location[0] + 1 >= maze.length) {
                    return true;
                }
                return maze[location[0] + 1][location[1]] == 1;
            case "s":
                if (location[1] + 1 >= maze.length) {
                    return true;
                }
                return maze[location[0]][location[1] + 1] == 1;
            case "n":
                if (location[1] - 1 < 0) {
                    return true;
                }
                return maze[location[0]][location[1] - 1] == 1;
            default:
                return false;
        }
    }

    private static boolean isExistFront(int[][] maze) {
        switch (dir) {
            case "e":
                if (location[1] + 1 >= maze.length) {
                    return true;
                }
                return maze[location[0]][location[1] + 1] == 1;
            case "w":
                if (location[1] - 1 < 0) {
                    return true;
                }
                return maze[location[0]][location[1] - 1] == 1;
            case "s":
                if (location[0] + 1 >= maze.length) {
                    return true;
                }
                return maze[location[0] + 1][location[1]] == 1;
            case "n":
                if (location[0] - 1 < 0) {
                    return true;
                }
                return maze[location[0] - 1][location[1]] == 1;
            default:
                return false;
        }
    }
}