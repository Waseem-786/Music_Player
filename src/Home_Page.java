
import java.awt.Color;
import java.awt.Font;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author Hp
 */
public class Home_Page extends javax.swing.JFrame {

    /**
     * Creates new form Home_Page
     */
    public Home_Page() {
        initComponents();
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
        nav_panel = new javax.swing.JPanel();
        home_panel = new javax.swing.JPanel();
        home_label = new javax.swing.JLabel();
        playlist_panel = new javax.swing.JPanel();
        playlist_label = new javax.swing.JLabel();
        music_panel = new javax.swing.JPanel();
        music_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSlider2 = new javax.swing.JSlider();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        nav_panel.setBackground(new java.awt.Color(255, 255, 255));
        nav_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nav_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nav_panelMouseExited(evt);
            }
        });

        home_panel.setBackground(new java.awt.Color(255, 255, 255));
        home_panel.setPreferredSize(new java.awt.Dimension(150, 41));
        home_panel.addMouseListener(new java.awt.event.MouseAdapter() {
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
                .addContainerGap(59, Short.MAX_VALUE))
        );
        home_panelLayout.setVerticalGroup(
            home_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, home_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_label, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        playlist_panel.setBackground(new java.awt.Color(255, 255, 255));
        playlist_panel.addMouseListener(new java.awt.event.MouseAdapter() {
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playlist_panelLayout.setVerticalGroup(
            playlist_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playlist_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlist_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        music_panel.setBackground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(57, Short.MAX_VALUE))
        );
        music_panelLayout.setVerticalGroup(
            music_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, music_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(music_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout nav_panelLayout = new javax.swing.GroupLayout(nav_panel);
        nav_panel.setLayout(nav_panelLayout);
        nav_panelLayout.setHorizontalGroup(
            nav_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playlist_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(home_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nav_panelLayout.createSequentialGroup()
                .addComponent(music_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        nav_panelLayout.setVerticalGroup(
            nav_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nav_panelLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(home_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(music_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Recents");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jSlider1.setBackground(new java.awt.Color(204, 204, 204));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/prev.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/next.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shuffle.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/repeat.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 204));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/volume.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Aadat by Atif Aslam");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(131, Short.MAX_VALUE)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(85, 85, 85)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton6)
                                        .addComponent(jButton2))
                                    .addComponent(jButton1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7)
                            .addComponent(jButton8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(19, 19, 19))))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("MUSIC PLAYER");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nav_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel1)))
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nav_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void playlist_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseEntered
       playlist_panel.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_playlist_labelMouseEntered

    private void playlist_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_playlist_labelMouseReleased

    private void playlist_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_labelMouseExited

    }//GEN-LAST:event_playlist_labelMouseExited

    private void playlist_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_panelMouseEntered
        // TODO add your handling code here:
       playlist_panel.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_playlist_panelMouseEntered

    private void playlist_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist_panelMouseExited
        // TODO add your handling code here:
        playlist_panel.setBackground(Color.WHITE);
    }//GEN-LAST:event_playlist_panelMouseExited

    private void home_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseEntered
        // TODO add your handling code here:
        home_panel.setBackground(new Color(240, 240, 240));

    }//GEN-LAST:event_home_labelMouseEntered

    private void home_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_home_labelMouseExited

    private void home_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_home_labelMouseReleased

    private void home_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_panelMouseEntered
        home_panel.setBackground(new Color(240, 240, 240));
        // TODO add your handling code here:
    }//GEN-LAST:event_home_panelMouseEntered

    private void home_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_panelMouseExited
        home_panel.setBackground(Color.WHITE);
        // TODO add your handling code here:
    }//GEN-LAST:event_home_panelMouseExited

    private void nav_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_panelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_nav_panelMouseEntered

    private void nav_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nav_panelMouseExited
        // TODO add your handling code here:
       
    }//GEN-LAST:event_nav_panelMouseExited

    private void music_labelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseEntered
        // TODO add your handling code here:
        music_panel.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_music_labelMouseEntered

    private void music_labelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_music_labelMouseExited

    private void music_labelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_labelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_music_labelMouseReleased

    private void music_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_panelMouseEntered
       music_panel.setBackground(new Color(240, 240, 240));
        // TODO add your handling code here:
    }//GEN-LAST:event_music_panelMouseEntered

    private void music_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_music_panelMouseExited
        // TODO add your handling code here:
        music_panel.setBackground(Color.WHITE);
    }//GEN-LAST:event_music_panelMouseExited

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
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home_Page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home_Page().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel home_label;
    private javax.swing.JPanel home_panel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JLabel music_label;
    private javax.swing.JPanel music_panel;
    private javax.swing.JPanel nav_panel;
    private javax.swing.JLabel playlist_label;
    private javax.swing.JPanel playlist_panel;
    // End of variables declaration//GEN-END:variables
}
