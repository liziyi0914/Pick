/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liziyi0914.pick;

import com.google.gson.Gson;
import com.liziyi0914.jal.App;
import com.liziyi0914.vg.VoiceGenerator;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALW
 */
public class Pick extends javax.swing.JFrame {

    Profile profile;

    HashMap<Integer, String> Students = new HashMap<>();
    HashMap<Integer, Boolean> Sign = new HashMap<>();
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> colormap = new HashMap<>();
    DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "学号", "姓名", "状态"
            }
    ) {
        Class[] types = new Class[]{
            java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
        };
        boolean[] canEdit = new boolean[]{
            false, false, true
        };

        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };
    boolean showTable = false;
    Config config = new Config(false, false);
    boolean start = false;
    int k = 0;
    long K = 0;
    SecureRandom r = new SecureRandom();
    ArrayList<Integer> list = new ArrayList<>();
    boolean first = true;
    String last;
    Student[] students;

    public static String profileName = "Default";
    public static boolean genVoice = false;

    File voiceRoot = null;
    File voiceTmp = null;

    /**
     * Creates new form Pick
     */
    public Pick() {
        initComponents();
        jLabel2.setText((String) model.getValueAt(0, 1));
        jCheckBox1.setSelected(config.isExpect());
        pack();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (start) {
                        K = r.nextLong();
                        if (config.isExpect()) {
                            k = (int) (Math.abs(K) % list.size());
                            jLabel2.setText((String) model.getValueAt(list.get(k), 1));
                        } else {
                            k = (int) (Math.abs(K) % model.getRowCount());
                            jLabel2.setText((String) model.getValueAt(k, 1));
                        }
                    }
                    try {
                        Thread.sleep(1000 / 35);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    {
        profileName = new ProfileChooser(this).getProfile();
        profile = new Profile(new File(".\\profile\\" + profileName + "\\" + profileName + ".profile"));
        InputStreamReader fr = null;
        try {
            File f = new File(".\\profile\\" + profileName + "\\students.table");
            voiceRoot = new File(".\\profile\\" + profileName + "\\");
            voiceTmp = new File(voiceRoot, "tmp\\");
            boolean hasF = f.exists();
            if (hasF) {
                Sign = (HashMap<Integer, Boolean>) new ObjectInputStream(new FileInputStream(f)).readObject();
                for (Map.Entry<Integer, Boolean> entry : Sign.entrySet()) {
                    Integer key = entry.getKey();
                    boolean value = entry.getValue();
                }
            }
            config = profile.getConfig();
            Gson gson = new Gson();
            students = profile.getStudents();
            int i = 0;
            for (Student student : students) {
                i++;
                map.put(student.getName(), i);
                Students.put(student.getID(), student.getName());
                colormap.put(student.getName(), Integer.parseInt(student.getColor(), 16));
                if (hasF) {
                    model.addRow(new Object[]{student.getID(), student.getName(), Sign.get(i)});
                } else {
                    Sign.put(i, false);
                    model.addRow(new Object[]{student.getID(), student.getName(), false});
                }
            }
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    Sign.put(e.getFirstRow(), (boolean) model.getValueAt(e.getFirstRow(), e.getColumn()));
                }
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("自动点名器 "+((App) Launcher.class.getAnnotation(App.class)).Version()+"  By liziyi0914 ");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setLayout(new java.awt.BorderLayout());

        jButton1.setFont(new java.awt.Font("宋体", 0, 48)); // NOI18N
        jButton1.setText("开始");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton2.setText("排除列表>>");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jPanel1.add(jButton2, java.awt.BorderLayout.CENTER);

        jCheckBox1.setText("自动排除");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });
        jPanel1.add(jCheckBox1, java.awt.BorderLayout.LINE_START);

        jPanel3.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jLabel2.setFont(new java.awt.Font("宋体", 0, 96)); // NOI18N
        jPanel4.add(jLabel2);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.setVisible(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton3.setText("全选");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton3MouseReleased(evt);
            }
        });
        jPanel5.add(jButton3);

        jButton5.setText("全不选");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton5MouseReleased(evt);
            }
        });
        jPanel5.add(jButton5);

        jButton4.setText("反选");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });
        jPanel5.add(jButton4);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        showTable = !showTable;
        jPanel2.setVisible(showTable);
        jScrollPane1.setVisible(showTable);
        if (showTable) {
            jButton2.setText("排除列表<<");
        } else {
            jButton2.setText("排除列表>>");
        }
        pack();
        setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2MouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        profile.setConfig(config);
        profile.save();
        try {
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(new File(".\\profile\\" + profileName + "\\students.table")));
            obj.writeObject(Sign);
            obj.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pick.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
        config.setExpect(((JCheckBox) evt.getSource()).isSelected());
    }//GEN-LAST:event_jCheckBox1MouseReleased

    void pressButton() {
        start = !start;
        if (!first) {
            if (config.isExpect()) {
                model.setValueAt(true, map.get(jLabel2.getText()) - 1, 2);
            }
        } else {
            first = false;
        }
        if (start) {
            list = new ArrayList<>();
            for (int i = 0; i < model.getRowCount(); i++) {
                if (!(boolean) model.getValueAt(i, 2)) {
                    list.add(i);
                }
            }
            if (list.isEmpty()) {
                start = false;
                Clear c = new Clear(this, true);
                c.setVisible(true);
                if (c.getReturnStatus() == Clear.RET_OK) {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        model.setValueAt(false, i, 2);
                    }
                }
            }
        }
        if (start) {
            jButton1.setText("停止");
            jLabel2.setForeground(Color.BLACK);
        } else {
            jButton1.setText("开始");
            jLabel2.setForeground(new Color(colormap.get(jLabel2.getText())));
            if (config.isSpeak()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        VoiceGenerator.read(profile.getVoiceData(), jLabel2.getText());
                    }
                }).start();
            }
        }
    }

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        pressButton();
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        pressButton();
    }//GEN-LAST:event_jButton1KeyReleased

    private void jButton3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseReleased
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true, i, 2);
        }
    }//GEN-LAST:event_jButton3MouseReleased

    private void jButton5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseReleased
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 2);
        }
    }//GEN-LAST:event_jButton5MouseReleased

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(!(boolean) model.getValueAt(i, 2), i, 2);
        }
    }//GEN-LAST:event_jButton4MouseReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pick.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pick().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
