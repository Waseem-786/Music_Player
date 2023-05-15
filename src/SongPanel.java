import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongPanel extends JPanel {
    private static SongPanel selectedPanel = null;
    private JLabel songNameLabel;
    private JLabel artistLabel;
    private JLabel durationLabel;
    private JLabel genreLabel;
    private static JPanel parent_panel;

    public SongPanel(JPanel panel) {
        parent_panel = panel;
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
                    bytes = AudioData[4].getBytes();
                }
                Database.Insert_Song(AudioData[0], AudioData[1], AudioData[2], AudioData[3], bytes, Files);

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
                String songName = songNameLabel.getText();
                String filePath = Database.Fetch_Path_From_Song(songName);

                if (selectedPanel != null) {
                    if(AudioPlayer.isPlaying()) {
                        AudioPlayer.stop();
                    }
                    selectedPanel.deselect();
                }
                select();
                AudioPlayer.play(filePath);
            }

        });
        
    }

    
    private void select() {
        setBackground(new Color(220, 220, 255));
        selectedPanel = this;
        if (getParent() instanceof JViewport) {
            JViewport viewport = (JViewport) getParent();
            Rectangle bounds = getBounds();
            bounds.setLocation(bounds.x - viewport.getViewPosition().x, bounds.y - viewport.getViewPosition().y);
            if (!viewport.getViewRect().contains(bounds)) {
                if (bounds.y < viewport.getViewRect().y) {
                    viewport.setViewPosition(new Point(0, bounds.y));
                } else {
                    viewport.setViewPosition(new Point(0, bounds.y + bounds.height - viewport.getViewRect().height));
                }
            }
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

}
