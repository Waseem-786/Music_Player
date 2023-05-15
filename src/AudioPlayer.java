import java.io.*;
import javazoom.jl.player.*;

public class AudioPlayer {
    private static Player player;

    public AudioPlayer() {
        
    }

    public static void play(String file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            player = new Player(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void stop() {
        if (player != null) {
            player.close();
        }
    }

    public static boolean isPlaying() {
        if (player != null) {
            return !player.isComplete();
        }
        return false;
    }
}

//import java.io.*;
//import javazoom.jl.player.*;
//
//public class AudioPlayer {
//    AudioPlayer(String file)
//    {
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            Player player = new Player(fileInputStream);
//            player.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
