import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Recent_Panel {

    private static JPanel parent_panel;
    private static String clicked_song = null;

    private static String current_songName;
    private static String current_duration;
    
    private static Home_Page home = null;
    
    public static String getCurrent_songName() {
        return current_songName;
    }

    public static String getCurrent_duration() {
        return current_duration;
    }
    
    public static String getClicked_playlist() {
        return clicked_song;
    }

    public Recent_Panel(JPanel panel) {
        parent_panel = panel;
    }
    public static void Home_Object(Home_Page h) {
        home = h;
    }

    public Recent_Panel() {
        // Set the layout manager
        JPanel recent_song_Panel = new JPanel(new GridLayout(0, 5, 10, 10));
        recent_song_Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (String songName : Database.fetchRecentSongs()) {
            try {
                // Add Songs Information in Database
                byte[] bytes = null;
                if (Database.getSongImage(songName) != null) {
                    bytes = Database.getSongImage(songName);
                }

                // Call Second Constructor
                JPanel song_panel = createRecentPanel(songName, bytes);
                recent_song_Panel.add(song_panel);
            } catch (Exception ex) {
                Logger.getLogger(FetchAudioFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Create a panel to hold all the playlist panels
        JPanel allPlaylistsPanel = new JPanel(new BorderLayout());
        allPlaylistsPanel.add(recent_song_Panel, BorderLayout.NORTH);

        // Wrap the allPlaylistsPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(allPlaylistsPanel);
        // Add the JScrollPane to the frame
        parent_panel.add(scrollPane);

    }

    private static JPanel createRecentPanel(String songName, byte[] bytes) {
        // Create the playlist panel
        JPanel recent_songs_panel = new JPanel(new BorderLayout());
        recent_songs_panel.setBackground(new Color(230, 230, 230));
        recent_songs_panel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        recent_songs_panel.setPreferredSize(new Dimension(200, 250));

        // Add a mouse listener to the panel to show a border on hover
        recent_songs_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                recent_songs_panel.setBackground(new Color(220, 220, 220));
                recent_songs_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                recent_songs_panel.setBackground(new Color(230, 230, 230));
                recent_songs_panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        // Add the image to the top of the playlist panel
        ImageIcon imageIcon = new ImageIcon(bytes);
        int width = 230;
        int height = 150;
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon, JLabel.CENTER);
        recent_songs_panel.add(imageLabel, BorderLayout.NORTH);

        // Add the playlist name and song count to the center of the playlist panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel(songName);
        nameLabel.setBackground(new Color(230, 230, 230));
        nameLabel.setOpaque(true);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(nameLabel);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        centerPanel.setBackground(new Color(230, 230, 230));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recent_songs_panel.add(centerPanel, BorderLayout.CENTER);

        // Add a panel with the Play and Edit buttons to the bottom of the playlist panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(0, 30));
        buttonPanel.setBackground(new Color(220, 220, 220));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        recent_songs_panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add a button to the bottom of the playlist panel
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setPreferredSize(new Dimension(60, 20));
        playButton.setBackground(new Color(255, 255, 255));
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setFocusPainted(false);
        playButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setBackground(Color.LIGHT_GRAY);
                playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setBackground(Color.white);
                playButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle play action
                current_songName = songName;
                current_duration = Database.Fetch_Duration_From_Song(current_songName);
                
                home.switch_song();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        buttonPanel.add(playButton);

        return recent_songs_panel;
    }

}
