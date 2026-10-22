import java.io.FileNotFoundException;

public class DartThrowTest
{

    public static void main(String[] args) throws FileNotFoundException
    {
        DartThrow board = new DartThrow("coordinates.txt");
        board.printGame();
    }
}
