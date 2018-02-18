//Written by joshi271
import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        int score = 0;
        boolean debugMode = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board rows dimension (between 3 and 12 inclusive): \n");
        int r = sc.nextInt();
        System.out.println("Enter board columns dimension (between 3 and 12 inclusive): \n");
        int c = sc.nextInt();

        Board board = new Board(r, c, debugMode);
        int unsunkBoats = board.unsunkBoats();
        System.out.println("Boats placed on board: " + unsunkBoats + " \n");

        while(unsunkBoats > 0){
            System.out.println("Enter guess for row: \n");
            r = sc.nextInt();
            System.out.println("Enter guess for column: \n");
            c = sc.nextInt();

            int result = board.guess(r, c);
            System.out.println(board + "\n");
            score = updateScore(score, result);

            unsunkBoats = board.unsunkBoats();

            System.out.println("Unsunk boats left: " + unsunkBoats + ", score: " + score);
        }

        System.out.println("Final score: " + score);
    }

    public static int updateScore(int score, int result){
        if (result == 0){
            System.out.println("Penalty: Out of Bounds");
            return score + 2;
        }
        else if (result == 1){
            System.out.println("Miss");
            return score + 1;
        }
        else if (result == 2){
            System.out.println("Hit");
            return score + 1;
        }
        else{
            System.out.println("Penalty: Redundant Guess");
            return score + 2;
        }
    }
}
