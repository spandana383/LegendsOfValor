public class Player {
    static private boolean quit;

    Player(){
        quit = false;
    }
    static boolean checkQuit(){
        return quit;
    }
    static void Quit(){
        quit = true;
    }
}
