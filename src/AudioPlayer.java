import java.io.FileInputStream;
import java.util.ArrayList;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer {

    private static Player player;
    private static long pausePosition;
    private static ArrayList<String> playlist;
    private static int currentIndex;
    private static boolean isPaused;

    public AudioPlayer() {
        playlist = new ArrayList<>();
        currentIndex = 0;
        isPaused = false;
    }

    public static void play(String file) {
        stop();

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
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public static void play_in_sequence(String file) {
        stop();

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
                    if (!isPaused) {
                        playNextSong();
                    }
                } catch (JavaLayerException e) {
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

    public static void pause() {
        if (player != null) {
            pausePosition = player.getPosition();
            stop();
            isPaused = true;
        }
    }

    public static void resume() {
        if (player != null) {
            isPaused = false;
            playFromPosition(pausePosition);
        }
    }

    private static void playFromPosition(final long position) {
        try {
            String filePath = Database.Fetch_Path_From_Song(SongPanel.getCurrent_songName());
            FileInputStream fileInputStream = new FileInputStream(filePath);
            fileInputStream.skip(position);
            player = new Player(fileInputStream);
            player.play();
            if (!isPaused) {
                playNextSong();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playPlaylist(ArrayList<String> songs) {
        if (songs != null && !songs.isEmpty()) {
            playlist = songs;
            currentIndex = -1;
            playNextSong();
        }
    }

    public static void playSongs(ArrayList<String> songs) {
        if (songs != null && !songs.isEmpty()) {
            playlist = songs;
            currentIndex = 0;
            playNextSong();
        }
    }

    private static void playNextSong() {
        currentIndex++;
        if (currentIndex < playlist.size()) {
            String nextSong = playlist.get(currentIndex);
            play_in_sequence(nextSong);
        }
    }
}
