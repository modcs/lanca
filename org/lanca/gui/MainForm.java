/*
**The MIT License (MIT)
**Copyright (c) <2014> <CIn-UFPE>
** 
**Permission is hereby granted, free of charge, to any person obtaining a copy
**of this software and associated documentation files (the "Software"), to deal
**in the Software without restriction, including without limitation the rights
**to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
**copies of the Software, and to permit persons to whom the Software is
**furnished to do so, subject to the following conditions:
** 
**The above copyright notice and this permission notice shall be included in
**all copies or substantial portions of the Software.
** 
**THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
**IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
**FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
**AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
**LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
**OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
**THE SOFTWARE.
*/


package org.lanca.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import org.caboclo.activities.Activity;
import org.caboclo.activities.ActivityListener;
import org.caboclo.clients.ApiClient;
import org.caboclo.service.BackupEngine;
import org.caboclo.service.ControlBackup;
import org.lanca.gui.components.ActivitiesTableModel;
import org.lanca.gui.components.ConsolePanel;
import org.lanca.gui.components.RemoteFolderListener;
import org.lanca.gui.components.FileBrowserPanel;
import org.lanca.gui.components.LocalFolderListener;
import org.lanca.gui.components.RemoteFilesPanel;


public class MainForm extends javax.swing.JFrame {

    private final BackupEngine engine;
    private FileBrowserPanel localFileExplorerPanel;
    private RemoteFilesPanel remoteFileExplorerPanel;
    private JSplitPane splitPane;
    private final ConsolePanel consolePanel = new ConsolePanel();
    private JSplitPane splitPane2;
    private final ActivitiesTableModel model;

    private AtomicInteger c = new AtomicInteger(0);

    public MainForm(ApiClient client) {

        engine = new BackupEngine(new ControlBackup(client));

        initComponents();

        initializeFineExplorerPanel();

        DefaultTableModel dtb = (DefaultTableModel) activitiesTable.getModel();
        model = new ActivitiesTableModel(dtb);

        initializeTable();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        fileExplorerPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        doBackupButton = new javax.swing.JButton();
        restoreFolderButton = new javax.swing.JButton();
        activitiesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        activitiesTable = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        resumeButton = new javax.swing.JButton();
        suspendButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        logoutItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Caboclo - Client API for Backup on Cloud");
        setIconImage(getIconImage());
        setLocationByPlatform(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        fileExplorerPanel.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        doBackupButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/lanca/gui/icons/cloud-upload-icon.png"))); // NOI18N
        doBackupButton.setToolTipText("Backup folder");
        doBackupButton.setFocusable(false);
        doBackupButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        doBackupButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        doBackupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doBackupButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(doBackupButton);

        restoreFolderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/lanca/gui/icons/cloud-download-icon.png"))); // NOI18N
        restoreFolderButton.setToolTipText("Restore folder");
        restoreFolderButton.setFocusable(false);
        restoreFolderButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        restoreFolderButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        restoreFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreFolderButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(restoreFolderButton);

        fileExplorerPanel.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("File explorer", fileExplorerPanel);

        activitiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Status", "Description", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        activitiesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activitiesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(activitiesTable);

        jToolBar2.setRollover(true);

        resumeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/lanca/gui/icons/Button-Play-icon.png"))); // NOI18N
        resumeButton.setFocusable(false);
        resumeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resumeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(resumeButton);

        suspendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/lanca/gui/icons/Button-Pause-icon.png"))); // NOI18N
        suspendButton.setFocusable(false);
        suspendButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        suspendButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        suspendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspendButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(suspendButton);

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/lanca/gui/icons/Button-Delete-icon.png"))); // NOI18N
        cancelButton.setFocusable(false);
        cancelButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(cancelButton);

        javax.swing.GroupLayout activitiesPanelLayout = new javax.swing.GroupLayout(activitiesPanel);
        activitiesPanel.setLayout(activitiesPanelLayout);
        activitiesPanelLayout.setHorizontalGroup(
            activitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(activitiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(activitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        activitiesPanelLayout.setVerticalGroup(
            activitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, activitiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Activities", activitiesPanel);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jProgressBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("File");

        logoutItem.setText("Logout");
        logoutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutItemActionPerformed(evt);
            }
        });
        jMenu1.add(logoutItem);

        exitItem.setText("Exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Events treatment">        
    private void logoutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutItemActionPerformed
        this.setVisible(false);

        AuthDialog.main(new String[]{});
    }//GEN-LAST:event_logoutItemActionPerformed

    private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        setSplitPaneLocation();
    }//GEN-LAST:event_formComponentResized

    private void activitiesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activitiesTableMouseClicked
        enableButtons();
    }//GEN-LAST:event_activitiesTableMouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            enableButtons();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        Activity act = getSelectedActivity();

        if (!consolePanel.hasActivity(act)) {
            consolePanel.addActivity(act);
        }

        if (!act.hasListener(listener)) {
            act.addListener(listener);
        }

        act.resumeActivity();
        c.incrementAndGet();
    }//GEN-LAST:event_resumeButtonActionPerformed

    private void suspendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspendButtonActionPerformed
        Activity act = getSelectedActivity();
        act.suspendActivity();
    }//GEN-LAST:event_suspendButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        Activity act = getSelectedActivity();
        act.cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void doBackupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doBackupButtonActionPerformed
        ArrayList<String> localFolder = localFileExplorerPanel.getSelectedFolder();

        if (localFolder != null) {
            backupFolder(localFolder);
        }
    }//GEN-LAST:event_doBackupButtonActionPerformed

    private void restoreFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreFolderButtonActionPerformed
        ArrayList<String> folders = localFileExplorerPanel.getSelectedFolder();
        String localFolder = "";
        if(folders!=null)
        {
            localFolder = folders.get(0);
        }
        String remoteFolder = remoteFileExplorerPanel.getSelectedFolder();

        if (localFolder != null && remoteFolder != null && !remoteFolder.equals("..")) {
            restoreBackup(remoteFolder, localFolder);
        }
    }//GEN-LAST:event_restoreFolderButtonActionPerformed
// </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel activitiesPanel;
    private javax.swing.JTable activitiesTable;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton doBackupButton;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JPanel fileExplorerPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JMenuItem logoutItem;
    private javax.swing.JButton restoreFolderButton;
    private javax.swing.JButton resumeButton;
    private javax.swing.JButton suspendButton;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>

    public static void main(String[] args) {
        MainForm frm = new MainForm(null);
        frm.setVisible(true);
    }

    private void initializeFineExplorerPanel() {
        localFileExplorerPanel = new FileBrowserPanel();
        localFileExplorerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Local files"));
        localFileExplorerPanel.setListener(localFolderListener);

        remoteFileExplorerPanel = new RemoteFilesPanel(engine);
        remoteFileExplorerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Remote backups"));
        remoteFileExplorerPanel.setListener(remoteFolderListener);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                localFileExplorerPanel, remoteFileExplorerPanel);

        splitPane.setOneTouchExpandable(true);

        splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, consolePanel);
        splitPane2.setOneTouchExpandable(true);

        setSplitPaneLocation();

        fileExplorerPanel.add(splitPane2, BorderLayout.CENTER);

    }

    private void setSplitPaneLocation() {
        if (splitPane == null) {
            return;
        }
        int location = (int) this.getSize().getWidth() / 2;
        splitPane.setDividerLocation(location);

        if (splitPane2 == null) {
            return;
        }

        location = (int) (this.getSize().getHeight() * 0.6);
        splitPane2.setDividerLocation(location);
    }

    private RemoteFolderListener remoteFolderListener = new RemoteFolderListener() {

        @Override
        public void folderDragged(ArrayList<String> folder) {
            backupFolder(folder);
        }

    };

    @Override
    public Image getIconImage() {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/org/lanca/gui/icons/Caboclo.png"));
    }

    private LocalFolderListener localFolderListener = new LocalFolderListener() {

        @Override
        public void folderDragged(String source, String destination) {
            restoreBackup(source, destination);
        }
    };

    private ActivityListener listener = new ActivityListener() {

        @Override
        public void activityStarted(Activity act) {
            System.out.println("Activity started: act-" + act.getDescription());

            enableButtons();
            jProgressBar1.setIndeterminate(true);
        }

        @Override
        public void activityCanceled(Activity act) {
            System.out.println("Activity canceled: act-" + act.getDescription());

            enableButtons();

            int nThreads = c.decrementAndGet();

            if (nThreads == 0) {
                jProgressBar1.setIndeterminate(false);
            }
        }

        @Override
        public void activityResumed(Activity act) {
            System.out.println("Activity resumed: act-" + act.getDescription());

            enableButtons();
        }

        @Override
        public void activityFinished(Activity act) {
            System.out.println("Activity finished: act" + act.getDescription());

            enableButtons();

            remoteFileExplorerPanel.displayBackups();

            int nThreads = c.decrementAndGet();

            if (nThreads == 0) {
                jProgressBar1.setIndeterminate(false);
            }
        }

        @Override
        public void activitySuspended(Activity act) {
            System.out.println("Activity suspended: " + act.getDescription());

            enableButtons();

            int nThreads = c.decrementAndGet();

            if (nThreads == 0) {
                jProgressBar1.setIndeterminate(false);
            }
        }

        @Override
        public void activityFailed(Activity act) {
            System.out.println("Activity resumed: " + act.getDescription());

            enableButtons();

            int nThreads = c.decrementAndGet();

            if (nThreads == 0) {
                jProgressBar1.setIndeterminate(false);
            }
        }
    };

    private void enableButtons() {
        Activity act = getSelectedActivity();

        if (act == null) {
            resumeButton.setEnabled(false);
            cancelButton.setEnabled(false);
            suspendButton.setEnabled(false);
            return;
        }

        switch (act.getStatus()) {
            case FAILED:
            case SUSPENDED:
                resumeButton.setEnabled(true);
                cancelButton.setEnabled(true);
                suspendButton.setEnabled(false);
                return;
            case RUNNING:
                resumeButton.setEnabled(false);
                cancelButton.setEnabled(true);
                suspendButton.setEnabled(true);
                return;
            case CANCELED:
            case FINISHED:
                resumeButton.setEnabled(false);
                cancelButton.setEnabled(false);
                suspendButton.setEnabled(false);
                return;
        }
    }

    private Activity getSelectedActivity() {
        try {
            int row = activitiesTable.getSelectedRow();
            String id = ((DefaultTableModel) activitiesTable.getModel()).getValueAt(row, 0).toString();
            return engine.getActivity(id);
        } catch (Exception e) {
            return null;
        }
    }

    private void backupFolder(ArrayList<String> folder) {
        //String id = engine.doBackupAsync(folder, listener);
        String id = engine.doBackup(folder, listener);
        Activity act = engine.getActivity(id);
        model.addActivity(act);

        consolePanel.addActivity(act);

        act.printOutput("Activity started: " + act.getDescription());

        c.incrementAndGet();
    }

    private void restoreBackup(String source, String destination) {
        //String id = engine.doRestoreAsync(source, destination, listener);
        String id = engine.doRestore(source, destination, listener);

        Activity act = engine.getActivity(id);
        model.addActivity(act);

        consolePanel.addActivity(act);

        c.incrementAndGet();
    }

    private void initializeTable() {
        for (Activity act : engine.getActivities()) {
            model.addActivity(act);
        }
    }
}
