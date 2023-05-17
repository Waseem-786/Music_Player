import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Hp
 */
public class Music extends javax.swing.JFrame {

    private static boolean isPlayButton = false;
    private AudioPlayer audioPlayer;
    private static SongPanel selectedPanel;
    
    /**
     * Creates new form Music
     */
    public Music() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.Volume_Slider.setValue(Volume.getVolumeValue());
        
//        Set if Song is already playing
        if(AudioPlayer.isPlaying())
        {
            this.Song_Name.setText(SongPanel.getCurrent_songName());
            this.Timer_End.setText(SongPanel.getCurrent_duration());
            play_pause_icon.setIcon(new ImageIcon(getClass().getResource("Images/stop.png")));
        
            byte[] bytes = null;
            if (Database.getSongImage(this.Song_Name.getText()) != null) {
                bytes = Database.getSongImage(this.Song_Name.getText());
            }
            
            // Add the image to the top of the playlist panel
            ImageIcon imageIcon = new ImageIcon(bytes);
            int width = 80;
            int height = 80;
            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            this.Image_Label.setIcon(imageIcon);
            
            this.isPlayButton = true;
        }
        SongPanel.Music_Object(this);
        Path_Chooser(this.jPanel2);
        
        audioPlayer = new AudioPlayer();
        
        
    }

    public static void Path_Chooser(JPanel panel) {
//        Already Path is Choosed
        if (Database.Fetch_Music_Path() != null) {
            String path = Database.Fetch_Music_Path();
            File file_path = new File(path);
            // Set this Panel in SongPanel class
            
            new SongPanel(panel);
            // Do something with the path
            new FetchAudioFiles(file_path,"Music");
            // Check if there are audio files exist in given path or not
            if (!Check_Music_Files(panel)) {
                Choose_Path_Button(panel);
            }

        } // First Time Path Choose
        else {
            Choose_Path_Button(panel);
        }
    }

    public static void Choose_Path_Button(JPanel panel) {
        // Create the "Choose Path" button
        JButton choosePathButton = new JButton("Choose Path");
        choosePathButton.setForeground(Color.WHITE);
        choosePathButton.setBackground(Color.BLUE);
        choosePathButton.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label = new JLabel("Nothing to Show Here");
        label.setForeground(Color.RED);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        choosePathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Set this Panel in SongPanel class
                    new SongPanel(panel);
                    // Do something with the selected file
                    File selectedFile = fileChooser.getSelectedFile();
                    new FetchAudioFiles(selectedFile,"Music");

                    // Add path in Database
                    Database.Add_MUSIC_Path(selectedFile.getAbsolutePath());
                    // Check if there are audio files exist in given path or not
                    if (Check_Music_Files(panel)) {
                        // Delete Choose button and Label
                        remove_Button_and_label(panel);

                        // Repaint the panel to reflect the changes
                        panel.revalidate();
                        panel.repaint();
                        // Set Layout
                        panel.setLayout(new GridLayout(1, 0)); // set the GridLayout
                    } else {
                        remove_Scroll_Pane(panel);
                    }
                }
            }
        });

        // Set the panel layout to FlowLayout and center the button
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));
        panel.add(choosePathButton);
        panel.add(label);

    }

    public static boolean Check_Music_Files(JPanel panel) {
        FetchAudioFiles fetchAudioFiles = new FetchAudioFiles();
        if (fetchAudioFiles.Total_Audio_Files() == 0) {
            return false;
        }
        return true;

    }

    public static void remove_Button_and_label(JPanel panel) {
        // Find the "Choose Path" button in the panel
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals("Choose Path")) {
                    // Remove the button from the panel
                    panel.remove(button);
                }
            } else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().equals("Nothing to Show Here")) {
                    // Remove the button from the panel
                    panel.remove(label);
                }
            }

        }

        // Repaint the panel to reflect the changes
        panel.revalidate();
        panel.repaint();
    }

    public static void remove_Scroll_Pane(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                JScrollPane pane = (JScrollPane) component;
                panel.remove(pane);
            }
        }
    }

    
    public void switch_song()
    {
        // play
        if (AudioPlayer.isPlaying()) {
            this.isPlayButton = false;
            AudioPlayer.stop();
        }
        
        this.Song_Name.setText(SongPanel.getCurrent_songName());
        this.Timer_End.setText(SongPanel.getCurrent_duration());
        play_pause_icon.setIcon(new ImageIcon(getClass().getResource("Images/stop.png")));
        this.isPlayButton = true;
        
        byte[] bytes = null;
        if (Database.getSongImage(this.Song_Name.getText()) != null) {
            bytes = Database.getSongImage(this.Song_Name.getText());
        }
        
        // Add the image to the top of the playlist panel
        ImageIcon imageIcon = new ImageIcon(bytes);
        int width = 80;
        int height = 80;
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        this.Image_Label.setIcon(imageIcon);
                
                
        String path = Database.Fetch_Path_From_Song(SongPanel.getCurrent_songName());
        AudioPlayer.play(path);
        
        this.repaint();
        this.revalidate();
    }
    
    public void change_play_pause_button()
    {
        if(this.isPlayButton)
        {
            play_pause_icon.setIcon(new ImageIcon(getClass().getResource("Images/play.png")));
            this.isPlayButton = false;
            AudioPlayer.pause();
        }
        else
        {
            play_pause_icon.setIcon(new ImageIcon(getClass().getResource("Images/stop.png")));
            this.isPlayButton = true;
            
            
            
            if(AudioPlayer.get_IsPaused())
            {
                audioPlayer.resume();
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Music_Player_Label = new javax.swing.JLabel();
        nav_panel = new javax.swing.JPanel();
        playlist_panel = new javax.swing.JPanel();
        playlist_label = new javax.swing.JLabel();
        music_panel = new javax.swing.JPanel();
        music_label = new javax.swing.JLabel();
        home_panel = new javax.swing.JPanel();
        home_label = new javax.swing.JLabel();
        Music_Player_Panel = new javax.swing.JPanel();
        slider = new javax.swing.JSlider();
        Volume_Slider = new javax.swing.JSlider();
        Song_Name = new javax.swing.JLabel();
        Timer_Start = new javax.swing.JLabel();
        Timer_End = new javax.swing.JLabel();
        play_pause_icon = new javax.swing.JLabel();
        Next_Button_Label = new javax.swing.JLabel();
        Repeat_Button_Label = new javax.swing.JLabel();
        Previous_Button_Label = new javax.swing.JLabel();
        Shuffle_Button_Label = new javax.swing.JLabel();
        Volume_Button_Label = new javax.swing.JLabel();
        Image_Label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Music_Player_Label.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Music_Player_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Music_Player_Label.setText("MUSIC PLAYER");
        Music_Player_Label.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        nav_panel.setBackground(new java.awt.Color(204, 204, 204));
        nav_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nav_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nav_panelMouseExited(evt);
            }
        });

        playlist_panel.setBackground(new java.awt.Color(204, 204, 204));
        playlist_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlist_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playlist_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playlist_panelMouseExited(evt);
            }
        });

        playlist_label.setBackground(new java.awt.Color(255, 0, 0));
        playlist_label.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        playlist_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playlist_label.setText("Playlist");
        playlist_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playlist_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playlist_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playlist_labelMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlist_labelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout playlist_panelLayout = new javax.swing.GroupLayout(playlist_panel);
        playlist_panel.setLayout(playlist_panelLayout);
        playlist_panelLayout.setHorizontalGroup(
            playlist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playlist_panelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(playlist_label)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        playlist_panelLayout.setVerticalGroup(
            playlist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playlist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlist_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        music_panel.setBackground(new java.awt.Color(204, 204, 204));
        music_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                music_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                music_panelMouseExited(evt);
            }
        });

        music_label.setBackground(new java.awt.Color(255, 0, 0));
        music_label.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        music_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        music_label.setText("Music");
        music_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                music_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                music_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                music_labelMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                music_labelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout music_panelLayout = new javax.swing.GroupLayout(music_panel);
        music_panel.setLayout(music_panelLayout);
        music_panelLayout.setHorizontalGroup(
            music_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(music_panelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(music_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        music_panelLayout.setVerticalGroup(
            music_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(music_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        home_panel.setBackground(new java.awt.Color(204, 204, 204));
        home_panel.setPreferredSize(new java.awt.Dimension(150, 41));
        home_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                home_panelMouseExited(evt);
            }
        });

        home_label.setBackground(new java.awt.Color(255, 0, 0));
        home_label.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        home_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        home_label.setText("Home");
        home_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_labelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_labelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                home_labelMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                home_labelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout home_panelLayout = new javax.swing.GroupLayout(home_panel);
        home_panel.setLayout(home_panelLayout);
        home_panelLayout.setHorizontalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(home_panelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(home_label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout nav_panelLayout = new javax.swing.GroupLayout(nav_panel);
        nav_panel.setLayout(nav_panelLayout);
        nav_panelLayout.setHorizontalGroup(
            nav_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addComponent(playlist_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(music_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        nav_panelLayout.setVerticalGroup(
            nav_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nav_panelLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(music_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );

        Music_Player_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Music_Player_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        slider.setBackground(new java.awt.Color(204, 204, 204));
        slider.setValue(0);
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderStateChanged(evt);
            }
        });

        Volume_Slider.setMajorTickSpacing(20);
        Volume_Slider.setPaintLabels(true);
        Volume_Slider.setPaintTicks(true);
        Volume_Slider.setValue(100);
        Volume_Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                Volume_SliderStateChanged(evt);
            }
        });

        Song_Name.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        Timer_Start.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Timer_Start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Timer_Start.setText("0:0:0");

        Timer_End.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Timer_End.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Timer_End.setText("0:0:0");

        play_pause_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/play.png"))); // NOI18N
        play_pause_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                play_pause_iconMouseClicked(evt);
            }
        });

        Next_Button_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/next.png"))); // NOI18N
        Next_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next_Button_LabelMouseClicked(evt);
            }
        });

        Repeat_Button_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/repeat.png"))); // NOI18N

        Previous_Button_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/prev.png"))); // NOI18N
        Previous_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Previous_Button_LabelMouseClicked(evt);
            }
        });

        Shuffle_Button_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/shuffle.png"))); // NOI18N

        Volume_Button_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Volume_Button_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/volume.png"))); // NOI18N
        Volume_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Volume_Button_LabelMouseClicked(evt);
            }
        });

        Image_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout Music_Player_PanelLayout = new javax.swing.GroupLayout(Music_Player_Panel);
        Music_Player_Panel.setLayout(Music_Player_PanelLayout);
        Music_Player_PanelLayout.setHorizontalGroup(
            Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Music_Player_PanelLayout.createSequentialGroup()
                .addComponent(Timer_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Timer_End, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(Music_Player_PanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Repeat_Button_Label)
                .addGap(50, 50, 50)
                .addComponent(Previous_Button_Label)
                .addGap(50, 50, 50)
                .addComponent(play_pause_icon)
                .addGap(50, 50, 50)
                .addComponent(Next_Button_Label)
                .addGap(50, 50, 50)
                .addComponent(Shuffle_Button_Label)
                .addGap(525, 525, 525))
            .addGroup(Music_Player_PanelLayout.createSequentialGroup()
                .addComponent(Image_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Song_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Volume_Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Volume_Button_Label)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Music_Player_PanelLayout.setVerticalGroup(
            Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Music_Player_PanelLayout.createSequentialGroup()
                .addGroup(Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Timer_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Timer_End, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Music_Player_PanelLayout.createSequentialGroup()
                        .addGroup(Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Shuffle_Button_Label)
                            .addComponent(Next_Button_Label)
                            .addComponent(play_pause_icon)
                            .addComponent(Previous_Button_Label)
                            .addComponent(Repeat_Button_Label))
                        .addGroup(Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Music_Player_PanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(Song_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Music_Player_PanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(Music_Player_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Volume_Button_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Volume_Slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21))))
                    .addComponent(Image_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Music_Player_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(nav_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Music_Player_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Music_Player_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(nav_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Music_Player_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void home_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseEntered
        // TODO add your handling code here:
        home_panel.setBackground(Color.white);
    }//GEN-LAST:event_home_labelMouseEntered

    private void home_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_home_labelMouseExited

    private void home_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_home_labelMouseReleased

    private void home_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_panelMouseEntered
        home_panel.setBackground(Color.white);
        // TODO add your handling code here:
    }//GEN-LAST:event_home_panelMouseEntered

    private void home_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_panelMouseExited
        home_panel.setBackground(new Color(204, 204, 204));
        // TODO add your handling code here:
    }//GEN-LAST:event_home_panelMouseExited

    private void playlist_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseEntered
        playlist_panel.setBackground(Color.white);
    }//GEN-LAST:event_playlist_labelMouseEntered

    private void playlist_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseExited

    }//GEN-LAST:event_playlist_labelMouseExited

    private void playlist_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_playlist_labelMouseReleased

    private void playlist_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_panelMouseEntered
        // TODO add your handling code here:
        playlist_panel.setBackground(Color.white);
    }//GEN-LAST:event_playlist_panelMouseEntered

    private void playlist_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_panelMouseExited
        // TODO add your handling code here:
        playlist_panel.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_playlist_panelMouseExited

    private void music_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseEntered
        // TODO add your handling code here:
        music_panel.setBackground(Color.white);
    }//GEN-LAST:event_music_labelMouseEntered

    private void music_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_music_labelMouseExited

    private void music_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_music_labelMouseReleased

    private void music_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_panelMouseEntered
        music_panel.setBackground(Color.white);
        // TODO add your handling code here:
    }//GEN-LAST:event_music_panelMouseEntered

    private void music_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_panelMouseExited
        // TODO add your handling code here:
        music_panel.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_music_panelMouseExited

    private void nav_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_panelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nav_panelMouseEntered

    private void nav_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_panelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_nav_panelMouseExited

    private void play_pause_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_play_pause_iconMouseClicked
        // TODO add your handling code here:
        this.change_play_pause_button();
    }//GEN-LAST:event_play_pause_iconMouseClicked

    private void Volume_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Volume_Button_LabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Volume_Button_LabelMouseClicked

    private void home_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseClicked
        // TODO add your handling code here:
        dispose();
        Home_Page home = new Home_Page();
        home.setVisible(true);
    }//GEN-LAST:event_home_labelMouseClicked

    private void playlist_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseClicked
        // TODO add your handling code here:
        dispose();
        Playlist play = new Playlist();
        play.setVisible(true);
    }//GEN-LAST:event_playlist_labelMouseClicked

    private void music_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_music_labelMouseClicked

    private void home_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_panelMouseClicked
        // TODO add your handling code here:
        dispose();
        Home_Page home = new Home_Page();
        home.setVisible(true);

    }//GEN-LAST:event_home_panelMouseClicked

    private void playlist_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_panelMouseClicked
        // TODO add your handling code here:
        dispose();
        Playlist play = new Playlist();
        play.setVisible(true);
    }//GEN-LAST:event_playlist_panelMouseClicked

    
    private void sliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderStateChanged
        // TODO add your handling code here:
//        if (!AudioPlayer.isPlaying()) {
//            // Do nothing if the audio player is not currently playing
//            return;
//        }
//
//        // Get the current position of the slider
//        JSlider slider = (JSlider) evt.getSource();
//        int sliderValue = slider.getValue();
//
//        // Get the total duration of the audio file
//        LocalTime time = LocalTime.parse("00:" + SongPanel.getduration_Label_for_JSlider());
//        long timeMillis = time.toNanoOfDay() / 1000000; // Time in milliseconds
//        long totalDuration = timeMillis;
//
//        // Calculate the new position to set the audio playback to
//        long newPosition = (long) ((double) sliderValue / slider.getMaximum() * totalDuration);
//
//        // Set the audio player's playback position to the new position
//        AudioPlayer.playFromPosition(newPosition);
    }//GEN-LAST:event_sliderStateChanged

    private void Next_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next_Button_LabelMouseClicked
        // TODO add your handling code here:
        SongPanel.selectNextPanel();
    }//GEN-LAST:event_Next_Button_LabelMouseClicked

    private void Previous_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Previous_Button_LabelMouseClicked
        // TODO add your handling code here:
        SongPanel.selectPreviousPanel();
    }//GEN-LAST:event_Previous_Button_LabelMouseClicked

    private void Volume_SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_Volume_SliderStateChanged
        // TODO add your handling code here:
        // Set the volume based on the slider value
        
        float volume = (float) Volume_Slider.getValue() / 100f;
        Volume.setVolume(volume);
    }//GEN-LAST:event_Volume_SliderStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Music().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image_Label;
    private javax.swing.JLabel Music_Player_Label;
    private javax.swing.JPanel Music_Player_Panel;
    private javax.swing.JLabel Next_Button_Label;
    private javax.swing.JLabel Previous_Button_Label;
    private javax.swing.JLabel Repeat_Button_Label;
    private javax.swing.JLabel Shuffle_Button_Label;
    private javax.swing.JLabel Song_Name;
    private javax.swing.JLabel Timer_End;
    private javax.swing.JLabel Timer_Start;
    private javax.swing.JLabel Volume_Button_Label;
    private javax.swing.JSlider Volume_Slider;
    private javax.swing.JLabel home_label;
    private javax.swing.JPanel home_panel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel music_label;
    private javax.swing.JPanel music_panel;
    private javax.swing.JPanel nav_panel;
    private javax.swing.JLabel play_pause_icon;
    private javax.swing.JLabel playlist_label;
    private javax.swing.JPanel playlist_panel;
    private javax.swing.JSlider slider;
    // End of variables declaration//GEN-END:variables
}
