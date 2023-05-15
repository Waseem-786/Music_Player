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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

public class Playlist_Panel {

    private static JPanel parent_panel;

    public Playlist_Panel(JPanel panel) {
        parent_panel = panel;
    }
    
    public Playlist_Panel() {
        // Set the layout manager
        JPanel playlistsPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        playlistsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        for (String Files : FetchAudioFiles.getAudioFiles()) {
            try {
                String[] AudioData = FetchAudioFiles.AudioData(Files);

                String playlist_name = "";
                int total_songs = 0;
                
                // Add Songs Information in Database
                byte[] bytes = null;
                if (AudioData[4] != null) {
                    bytes = AudioData[4].getBytes();
                }
//                Database(AudioData[0], AudioData[1], AudioData[2], AudioData[3], bytes, Files);

                // Call Second Constructor

                JPanel playlistPanel = createPlaylistPanel(playlist_name,total_songs);
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
    

    private static JPanel createPlaylistPanel(String playlist_name, int total_songs) {
        // Create the playlist panel
        JPanel playlistPanel = new JPanel(new BorderLayout());
        playlistPanel.setBackground(new Color(230, 230, 230));
        playlistPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
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
        });

        // Add the image to the top of the playlist panel
        ImageIcon imageIcon = new ImageIcon("D:\\6th Semester\\Software Construction\\MusicPlayer\\src\\Images\\playlist.jpg");
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
        JLabel songCountLabel = new JLabel("Total Songs: "+total_songs);
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
        playButton.setBackground(new Color(220, 220, 220));
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setFocusPainted(false);
        playButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e) {
                playButton.setBackground(new Color(200, 200, 200));
                playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playButton.setBackground(new Color(220, 220, 220));
                playButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle play action
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

        // Create the menu items
        JMenuItem renameItem = new JMenuItem("Rename");
        renameItem.addActionListener(e -> {
            // Handle rename action
        });
        JMenuItem addSongItem = new JMenuItem("Add Song");
        addSongItem.addActionListener(e -> {
            // Handle add song action
        });
        JMenuItem deleteItem = new JMenuItem("Delete Playlist");
        deleteItem.addActionListener(e -> {
            // Handle delete playlist action
        });

        // Set the font and color for the menu items
        renameItem.setFont(new Font("Arial", Font.PLAIN, 14));
        renameItem.setForeground(Color.DARK_GRAY);
        addSongItem.setFont(new Font("Arial", Font.PLAIN, 14));
        addSongItem.setForeground(Color.DARK_GRAY);
        deleteItem.setFont(new Font("Arial", Font.PLAIN, 14));
        deleteItem.setForeground(Color.DARK_GRAY);

        // Add the menu items to the popup menu
        popupMenu.add(renameItem);
        popupMenu.add(addSongItem);
        popupMenu.add(deleteItem);

        // Add a button to the bottom of the playlist panel
        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setPreferredSize(new Dimension(60, 20));
        editButton.setBackground(new Color(220, 220, 220));
        editButton.setBorder(BorderFactory.createEmptyBorder());
        editButton.setFocusPainted(false);
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(editButton, 0, editButton.getHeight());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                editButton.setBackground(new Color(200, 200, 200));
                editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                editButton.setBackground(new Color(220, 220, 220));
                editButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        buttonPanel.add(editButton);

        return playlistPanel;
    }
    
//    public static void main(String[] args) {
//        // Create the frame
//        JFrame frame = new JFrame("My Playlists");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Set the layout manager
//        JPanel playlistsPanel = new JPanel(new GridLayout(0, 5, 10, 10));
//        playlistsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        // Add each playlist panel to the playlists panel
//        for (int i = 0; i < 5; i++) {
//            JPanel playlistPanel = createPlaylistPanel();
//            playlistsPanel.add(playlistPanel);
//        }
//
//        // Create a panel to hold all the playlist panels
//        JPanel allPlaylistsPanel = new JPanel(new BorderLayout());
//        allPlaylistsPanel.add(playlistsPanel, BorderLayout.NORTH);
//
//        // Wrap the allPlaylistsPanel in a JScrollPane
//        JScrollPane scrollPane = new JScrollPane(allPlaylistsPanel);
//        // Add the JScrollPane to the frame
//        frame.add(scrollPane);
//        // Set the frame size and make it visible
//        frame.pack();
//        frame.setVisible(true);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//    }
}
