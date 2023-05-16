import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class FetchAudioFiles extends JFrame {

    private static ArrayList<String> audioFiles;

    public static void setAudioFiles(ArrayList<String> audioFiles) {
        FetchAudioFiles.audioFiles = audioFiles;
    }

    public static ArrayList<String> getAudioFiles() {
        return audioFiles;
    }

    public int Total_Audio_Files() {
        return audioFiles.size();
    }
    public FetchAudioFiles() {
        //Do Nothing
    }
    
    public FetchAudioFiles(File selectedFile,String ClassName) {
        audioFiles = retrieveAudioFiles(selectedFile.getAbsolutePath());
        if(ClassName == "Music")
        {
            new SongPanel();
        }
        else if(ClassName == "Selection")
        {
            new Song_for_Selection_Panel();
        }
    }
    public FetchAudioFiles(String playlist_name) {
        audioFiles = Database.getFilePathsForPlaylist(playlist_name);
    }

    public static ArrayList<String> retrieveAudioFiles(String directory) {
        ArrayList<String> audioFiles = new ArrayList<String>();
        File folder = new File(directory);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".mp3") || fileName.endsWith(".wav") || fileName.endsWith(".m4a")) {
                    audioFiles.add(file.getAbsolutePath());
                }
            } else if (file.isDirectory()) {
                audioFiles.addAll(retrieveAudioFiles(file.getAbsolutePath()));
            }
        }

        return audioFiles;
    }

    public static String[] AudioData(String FileName) throws Exception {
        String[] AudioData = new String[5];

        File audioFile = new File(FileName);
        AudioFile af = AudioFileIO.read(audioFile);
        Tag tag = af.getTag();
        if (tag != null) {
            String songName = tag.getFirst(FieldKey.TITLE);
            String Genre = tag.getFirst(FieldKey.GENRE);
            String artistName = tag.getFirst(FieldKey.ARTIST);
            int durationSeconds = af.getAudioHeader().getTrackLength();
            long durationMinutes = TimeUnit.SECONDS.toMinutes(durationSeconds);
            int durationSecondsRemaining = durationSeconds % 60;
            String duration = String.format("%d:%02d", durationMinutes, durationSecondsRemaining);

            String ImageName = null;
            // Extract embedded artwork and save it as an image file
            if (tag.getFirstArtwork() != null) {
                byte[] imageData = tag.getFirstArtwork().getBinaryData();
                ImageName = Base64.getEncoder().encodeToString(imageData);
            }
            else
            {
                // Read the image file into a byte array
                byte[] imageData;
                Path path = Paths.get("src/image.jpg");
                imageData = Files.readAllBytes(path);
                // Convert the byte array to a Base64 encoded string
                ImageName = Base64.getEncoder().encodeToString(imageData);
            }
            if("".equals(songName))
            {
                // Split the file path by the file separator ("\") to get the file name
                String[] pathParts = FileName.split("\\\\");
                String fileNameWithExtension = pathParts[pathParts.length - 1];

                // Remove the extension (".mp3") from the file name
                songName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));

            }
            if("".equals(artistName))
            {
                artistName = "Unknown Artist";
            }
            if("".equals(Genre))
            {
                Genre = "Unknown Genre";
            }
            AudioData[0] = songName;
            AudioData[1] = artistName;
            AudioData[2] = Genre;
            AudioData[3] = duration;
            AudioData[4] = ImageName;
        }

        return AudioData;
    }
    
}
