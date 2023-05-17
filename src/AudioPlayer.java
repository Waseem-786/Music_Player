
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JSlider;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.Player;

public class AudioPlayer {

    private static Player player;
    private static long pausePosition;
    private static FileInputStream fileInputStream;
    private static ArrayList<String> playlist;
    private static int currentIndex;
    private static boolean isPaused;

    private static JSlider positionSlider;
    private static Thread updateThread;
    private static int totalBytes = 0;
    private static int currentPosition = 0;

    public static boolean get_IsPaused() {
        return isPaused;
    }

    public AudioPlayer() {
        playlist = new ArrayList<>();
        currentIndex = 0;
        isPaused = false;
    }

    public static void play(String file) {
        stop();

        try {
            fileInputStream = new FileInputStream(file);
            playlist.add(file);
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
            fileInputStream = new FileInputStream(file);
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
            playlist.clear();
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
            try {
                // Store the current position and close the player
                pausePosition = fileInputStream.available();
                player.close();
                isPaused = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resume() {
        if (player != null) {
            if (isPaused) {
                playFromPosition(pausePosition);
                isPaused = false;
            }
        }
    }

    private static void playFromPosition(long position) {
        try {
            // Re-create the FileInputStream object and skip to the paused position
            fileInputStream = new FileInputStream(playlist.get(currentIndex));
            AudioDevice audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
            player = new Player(fileInputStream, audioDevice);

            // Skip to the desired position
            long bytesToSkip = (position * 128) / 1000;
            fileInputStream.skip(bytesToSkip);

            // Start playback in a separate thread
            Thread playbackThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            playbackThread.start();
        } catch (IOException | JavaLayerException e) {
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

    public static void update_slider(JSlider slider) {
        positionSlider = slider;

        // Get the total bytes of the current audio file
        totalBytes = player.getPosition();
        System.out.println("Total bytes: " + totalBytes);

        
        // Start a thread to update the position slider
        updateThread = new Thread(new Runnable() {
            public void run() {
                try {
                    while (currentPosition < totalBytes) {
                        currentPosition = player.getPosition();
                        System.out.println("Current position: " + currentPosition);
                        int positionPercent = (int) (((float) currentPosition / (float) totalBytes) * 100f);
                        System.out.println("Position percent: " + positionPercent);
                        positionSlider.setValue(positionPercent);
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
    }
}
