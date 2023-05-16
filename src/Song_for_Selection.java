import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Song_for_Selection {

    public Song_for_Selection(File file_path, String playlist_Name) {
        JFrame frame = new JFrame("Grid Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        Set Panel
        JPanel panel = new JPanel(new GridLayout());
        panel.setLayout(new GridLayout(1, 0)); // set the GridLayout
        new Song_for_Selection_Panel(panel);
        
        
        // Create a top panel for labels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(53, 59, 72));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel selectLabel = new JLabel("SELECT SONGS TO ADD IN PLAYLIST");
        selectLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        selectLabel.setForeground(Color.WHITE);
        topPanel.add(selectLabel, BorderLayout.WEST);

        JLabel doneLabel = new JLabel("Done");
        doneLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        doneLabel.setForeground(Color.WHITE);
        doneLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add hover effects to the "Done" button
        doneLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                doneLabel.setForeground(new Color(200, 200, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                doneLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Perform the desired action when the "Done" button is clicked
                for (Song_for_Selection_Panel panel : Song_for_Selection_Panel.getSelectedPanels()) {
                    Database.Add_Song_in_Playlist(panel.getSongName(), playlist_Name);
                }
//                Make selectedPanels Empty
                Song_for_Selection_Panel.setSelectedPanels(new ArrayList<>());
//                Dispose Screen
                frame.dispose();
                Playlist playlist = new Playlist();
                playlist.setVisible(true);
            }
        });

        topPanel.add(doneLabel, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        new FetchAudioFiles(file_path, "Selection");
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
