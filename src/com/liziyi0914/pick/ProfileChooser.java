/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liziyi0914.pick;

import java.io.File;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

/**
 *
 * @author liziy
 */
public class ProfileChooser extends javax.swing.JDialog {

    
    String profile = "";
    ArrayList<String> li = new ArrayList<>();
    ListModel<String> model = new AbstractListModel<String>() {
        @Override
        public int getSize() {
            return li.size();
        }

        @Override
        public String getElementAt(int index) {
            return li.get(index);
        }
    };

    /**
     * Creates new form NewJDialog
     */
    public ProfileChooser(java.awt.Frame parent) {
        super(parent, true);
        File profileFile = new File("profile");
        File[] files = profileFile.listFiles();
        for (File tmp1 : files) {
            if (tmp1.isDirectory()) {
                File[] tmp2 = tmp1.listFiles((f, name) -> {
                    return name.endsWith(".profile");
                });
                for (File file : tmp2) {
                    String name = file.getName();
                    li.add(name.substring(0, name.indexOf(".profile")));
                }
            }
        }
        initComponents();
    }
    
    public String getProfile(){
        setVisible(true);
        return profile;
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("选择配置文件");

        jLabel1.setFont(new java.awt.Font("宋体", 0, 36)); // NOI18N
        jLabel1.setText("配置文件");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jList2.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jList2.setModel(model);
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jButton2.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jButton2.setText("打开");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(416, 322));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        profile= jList2.getSelectedValue();
        if (profile!=null) {
            setVisible(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
