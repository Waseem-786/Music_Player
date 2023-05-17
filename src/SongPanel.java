
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongPanel extends JPanel {

    private static SongPanel selectedPanel = null;
    private JLabel songNameLabel = new JLabel();
    private JLabel artistLabel = new JLabel();;
    private JLabel durationLabel = new JLabel();;
    private JLabel genreLabel = new JLabel();;
    private static JPanel parent_panel;

    private static String current_songName;
    private static String current_artistName;
    private static String current_genreName;
    private static String current_duration;

    private static Music music = null;
    private static Playlist_Songs playlist = null;
    
    
    public static void setSelectedPanel(SongPanel selectedPanel) {
        SongPanel.selectedPanel = selectedPanel;
    }

    public static SongPanel getSelectedPanel() {
        return selectedPanel;
    }
    
    public static String getCurrent_songName() {
        return current_songName;
    }

    public static String getCurrent_artistName() {
        return current_artistName;
    }

    public static String getCurrent_genreName() {
        return current_genreName;
    }

    public static String getCurrent_duration() {
        return current_duration;
    }


    public SongPanel(JPanel panel) {
        parent_panel = panel;
        
    }
    public static void Music_Object(Music m) {
        music = m;
        playlist = null;
    }
    public static void PlayList_Object(Playlist_Songs p) {
        playlist = p;
        music = null;
    }

    public SongPanel() {
        // Create a new JPanel container to hold the SongPanels
        JPanel songPanelContainer = new JPanel();
        songPanelContainer.setLayout(new BoxLayout(songPanelContainer, BoxLayout.Y_AXIS));

        for (String Files : FetchAudioFiles.getAudioFiles()) {
            try {
                String[] AudioData = FetchAudioFiles.AudioData(Files);

                // Add Songs Information in Database
                byte[] bytes = null;
                if (AudioData[4] != null) {
                    bytes = Base64.getDecoder().decode(AudioData[4]);
                }
                if (Database.Fetch_Music_Path() == null) {
                    Database.Insert_Song(AudioData[0], AudioData[1], AudioData[2], AudioData[3], bytes, Files);
                }

                // Call Second Constructor
                SongPanel songPanel = new SongPanel(AudioData[0], AudioData[1], AudioData[2], AudioData[3]);
                songPanelContainer.add(songPanel);
            } catch (Exception ex) {
                Logger.getLogger(FetchAudioFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        JScrollPane scrollPane = new JScrollPane(songPanelContainer);
        parent_panel.add(scrollPane);
    }

    public SongPanel(String songName, String artist, String genre, String duration) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(400, 50));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(0, 50, 0, 50);

        songNameLabel = new JLabel(songName);
        songNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        songNameLabel.setPreferredSize(new Dimension(350, songNameLabel.getPreferredSize().height));
        c.gridx = 0;
        c.gridy = 0;
        add(songNameLabel, c);

        artistLabel = new JLabel(artist);
        artistLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        artistLabel.setPreferredSize(new Dimension(20, artistLabel.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 0;
        add(artistLabel, c);

        genreLabel = new JLabel(genre);
        genreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        genreLabel.setPreferredSize(new Dimension(20, genreLabel.getPreferredSize().height));
        c.gridx = 2;
        c.gridy = 0;
        add(genreLabel, c);

        durationLabel = new JLabel(duration);
        durationLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        durationLabel.setPreferredSize(new Dimension(10, durationLabel.getPreferredSize().height));
        durationLabel.setHorizontalAlignment(JLabel.CENTER);
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(durationLabel, c);

        // Add hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (selectedPanel != SongPanel.this) {
                    setBackground(new Color(250, 250, 250));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedPanel != SongPanel.this) {
                    setBackground(new Color(240, 240, 240));
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedPanel != null) {
                    selectedPanel.deselect();
                }
                select();

            }

        });

    }

    public void select() {
        current_songName = songNameLabel.getText();
        current_artistName = artistLabel.getText();
        current_genreName = genreLabel.getText();
        current_duration = durationLabel.getText();
        
        if (selectedPanel != null) {
            selectedPanel.deselect();
        }
        

        setBackground(new Color(220, 220, 255));
        selectedPanel = this;
        
        if(playlist != null)
        {
            playlist.switch_song();
        }
        else if(music != null)
        {
            music.switch_song();
        }
        
    }
    
    private void deselect() {
        setBackground(new Color(240, 240, 240));
        selectedPanel = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (selectedPanel == this) {
            g.setColor(new Color(120, 170, 255));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void selectNextPanel() {
        if (selectedPanel != null && parent_panel instanceof JPanel) {
            JPanel panel = (JPanel) parent_panel;
            Component[] components = panel.getComponents();
            boolean foundSelectedPanel = false;

            for (Component panel_component : components) {
                if (panel_component instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) panel_component;
                    Component viewportComponent = scrollPane.getViewport().getView();

                    if (viewportComponent instanceof JPanel) {
                        JPanel nestedPanel = (JPanel) viewportComponent;
                        Component[] nestedComponents = nestedPanel.getComponents();

                        for (Component nested_component : nestedComponents) {
                            if (nested_component instanceof SongPanel) {
                                SongPanel songPanel = (SongPanel) nested_component;
                                if (songPanel == selectedPanel) {
                                    foundSelectedPanel = true;
                                } else if (foundSelectedPanel && songPanel.isVisible()) {
                                    songPanel.select();
                                    SwingUtilities.invokeLater(() -> {
                                        Rectangle bounds = songPanel.getBounds();
                                        scrollPane.getViewport().scrollRectToVisible(bounds);
                                    });
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            // If no next panel is found, select the first panel
            for (Component panel_component : components) {
                if (panel_component instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) panel_component;
                    Component viewportComponent = scrollPane.getViewport().getView();

                    if (viewportComponent instanceof JPanel) {
                        JPanel nestedPanel = (JPanel) viewportComponent;
                        Component[] nestedComponents = nestedPanel.getComponents();

                        for (Component nested_component : nestedComponents) {
                            if (nested_component instanceof SongPanel) {
                                SongPanel songPanel = (SongPanel) nested_component;
                                if (songPanel.isVisible()) {
                                    songPanel.select();
                                    SwingUtilities.invokeLater(() -> {
                                        Rectangle bounds = songPanel.getBounds();
                                        scrollPane.getViewport().scrollRectToVisible(bounds);
                                    });
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void selectPreviousPanel() {
        if (selectedPanel != null && parent_panel instanceof JPanel) {
            JPanel panel = (JPanel) parent_panel;
            Component[] components = panel.getComponents();
            boolean foundSelectedPanel = false;

            for (int i = components.length - 1; i >= 0; i--) {
                Component panel_component = components[i];
                if (panel_component instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) panel_component;
                    Component viewportComponent = scrollPane.getViewport().getView();

                    if (viewportComponent instanceof JPanel) {
                        JPanel nestedPanel = (JPanel) viewportComponent;
                        Component[] nestedComponents = nestedPanel.getComponents();

                        for (int j = nestedComponents.length - 1; j >= 0; j--) {
                            Component nested_component = nestedComponents[j];
                            if (nested_component instanceof SongPanel) {
                                SongPanel songPanel = (SongPanel) nested_component;
                                if (songPanel == selectedPanel) {
                                    foundSelectedPanel = true;
                                } else if (foundSelectedPanel && songPanel.isVisible()) {
                                    songPanel.select();
                                    SwingUtilities.invokeLater(() -> {
                                        Rectangle bounds = songPanel.getBounds();
                                        scrollPane.getViewport().scrollRectToVisible(bounds);
                                    });
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            // If no previous panel is found, select the last panel
            for (int i = components.length - 1; i >= 0; i--) {
                Component panel_component = components[i];
                if (panel_component instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) panel_component;
                    Component viewportComponent = scrollPane.getViewport().getView();

                    if (viewportComponent instanceof JPanel) {
                        JPanel nestedPanel = (JPanel) viewportComponent;
                        Component[] nestedComponents = nestedPanel.getComponents();

                        for (int j = nestedComponents.length - 1; j >= 0; j--) {
                            Component nested_component = nestedComponents[j];
                            if (nested_component instanceof SongPanel) {
                                SongPanel songPanel = (SongPanel) nested_component;
                                if (songPanel.isVisible()) {
                                    songPanel.select();
                                    SwingUtilities.invokeLater(() -> {
                                        Rectangle bounds = songPanel.getBounds();
                                        scrollPane.getViewport().scrollRectToVisible(bounds);
                                    });
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
