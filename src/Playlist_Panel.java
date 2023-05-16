
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
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class Playlist_Panel {

    private static JPanel parent_panel;
    private static String clicked_playlist = null;

    public static String getClicked_playlist() {
        return clicked_playlist;
    }

    public Playlist_Panel(JPanel panel) {
        parent_panel = panel;
    }

    public Playlist_Panel() {
        // Set the layout manager
        JPanel playlistsPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        playlistsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (String playlistName : Database.getAllPlaylists()) {
            try {

                String playlist_name = playlistName;
                int total_songs = Database.countSongsInPlaylist(playlistName);
                // Add Songs Information in Database
                byte[] bytes = null;
                if (Database.getFirstSongImage(playlistName) != null) {
                    bytes = Database.getFirstSongImage(playlistName);
                }

                // Call Second Constructor
                JPanel playlistPanel = createPlaylistPanel(playlist_name, total_songs, bytes);
                playlistsPanel.add(playlistPanel);
            } catch (Exception ex) {
                Logger.getLogger(FetchAudioFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Create a panel to hold all the playlist panels
        JPanel allPlaylistsPanel = new JPanel(new BorderLayout());
        allPlaylistsPanel.add(playlistsPanel, BorderLayout.NORTH);

        // Wrap the allPlaylistsPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(allPlaylistsPanel);
        // Add the JScrollPane to the frame
        parent_panel.add(scrollPane);

    }

    private static JPanel createPlaylistPanel(String playlist_name, int total_songs, byte[] bytes) {
        // Create the playlist panel
        JPanel playlistPanel = new JPanel(new BorderLayout());
        playlistPanel.setBackground(new Color(230, 230, 230));
        playlistPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        playlistPanel.setPreferredSize(new Dimension(200, 250));

        // Add a mouse listener to the panel to show a border on hover
        playlistPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playlistPanel.setBackground(new Color(220, 220, 220));
                playlistPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playlistPanel.setBackground(new Color(230, 230, 230));
                playlistPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle play action
                clicked_playlist = playlist_name;
                new Playlist_Songs();
            }
        });

        // Add the image to the top of the playlist panel
        ImageIcon imageIcon = new ImageIcon(bytes);
        int width = 230;
        int height = 150;
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon, JLabel.CENTER);
        playlistPanel.add(imageLabel, BorderLayout.NORTH);

        // Add the playlist name and song count to the center of the playlist panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        JLabel nameLabel = new JLabel(playlist_name);
        nameLabel.setBackground(new Color(230, 230, 230));
        nameLabel.setOpaque(true);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel songCountLabel = new JLabel("Total Songs: " + total_songs);
        songCountLabel.setBackground(new Color(230, 230, 230));
        songCountLabel.setOpaque(true);
        songCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        songCountLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(nameLabel);
        centerPanel.add(songCountLabel);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        centerPanel.setBackground(new Color(230, 230, 230));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistPanel.add(centerPanel, BorderLayout.CENTER);

        // Add a panel with the Play and Edit buttons to the bottom of the playlist panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(0, 30));
        buttonPanel.setBackground(new Color(220, 220, 220));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistPanel.add(buttonPanel, BorderLayout.SOUTH);

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
//                Show Musics Page
                clicked_playlist = playlist_name;
                new Playlist_Songs();
//                Play Musics
                ArrayList<String> filePathsForPlaylist = Database.getFilePathsForPlaylist(playlist_name);
                AudioPlayer.playPlaylist(filePathsForPlaylist);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        buttonPanel.add(playButton);

        // Create a popup menu for the Edit button
        JPopupMenu popupMenu = new JPopupMenu();

        // Set the font and color for the menu items
        popupMenu.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.setForeground(Color.DARK_GRAY);
        popupMenu.setBackground(Color.DARK_GRAY);

        // Create the menu items
        JMenuItem renameItem = new JMenuItem("Rename");
        renameItem.addActionListener(e -> {
            // Handle rename action
            String old_playistName = playlist_name;
            String new_playlistName = JOptionPane.showInputDialog("Enter new playlist name:");
            if (new_playlistName != null && !new_playlistName.isEmpty()) {
                // Do something with the playlist name
                Database.updatePlaylistName(old_playistName, new_playlistName);
                
//                To reflect changes in mean time
                parent_panel.removeAll();
                new Playlist_Panel(parent_panel);
                new Playlist_Panel();
                parent_panel.revalidate();
                parent_panel.repaint();
            }
        });
        JMenuItem addSongItem = new JMenuItem("Add Song");
        addSongItem.addActionListener(e -> {
            // Handle add song action
            String path = Database.Fetch_Music_Path();
            File file_path = new File(path);
            new Song_for_Selection(file_path, playlist_name);
            
            // To reflect changes in mean time
            parent_panel.removeAll();
            new Playlist_Panel(parent_panel);
            new Playlist_Panel();
            parent_panel.revalidate();
            parent_panel.repaint();

        });
        JMenuItem deleteItem = new JMenuItem("Delete Playlist");
        deleteItem.addActionListener(e -> {
            // Handle delete playlist action
            Database.Delete_Playlist(playlist_name);
            // To reflect changes in mean time
            parent_panel.removeAll();
            new Playlist_Panel(parent_panel);
            new Playlist_Panel();
            parent_panel.revalidate();
            parent_panel.repaint();

        });

        // Set the font and color for the menu items
        renameItem.setFont(new Font("Arial", Font.PLAIN, 14));
        renameItem.setForeground(Color.black);
        addSongItem.setFont(new Font("Arial", Font.PLAIN, 14));
        addSongItem.setForeground(Color.black);
        deleteItem.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteItem.setForeground(Color.black);

        // Add the menu items to the popup menu
        popupMenu.add(renameItem);
        popupMenu.add(addSongItem);
        popupMenu.add(deleteItem);

        // Add a button to the bottom of the playlist panel
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setPreferredSize(new Dimension(60, 20));
        editButton.setBackground(Color.white);
        editButton.setBorder(BorderFactory.createEmptyBorder());
        editButton.setFocusPainted(false);
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(editButton, 0, editButton.getHeight());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editButton.setBackground(Color.LIGHT_GRAY);
                editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editButton.setBackground(Color.white);
                editButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        buttonPanel.add(editButton);

        return playlistPanel;
    }

}
