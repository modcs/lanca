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


package org.lanca.gui.components;

import com.google.api.services.drive.model.FileList;
import java.awt.Color;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;
import javax.swing.filechooser.FileSystemView;
import org.caboclo.clients.ApiClient;
import org.caboclo.service.BackupEngine;
import org.caboclo.service.RemoteFile;


public class RemoteFilesPanel extends javax.swing.JPanel {

    private final BackupEngine engine;
    private final ApiClient client;
    private String selectedPath;
    private DefaultListModel<RemoteFile> listModel;
    private RemoteFolderListener listener = new RemoteFolderListener() {
        @Override
        public void folderDragged(ArrayList<String> folder) {
        }
    };

    /**
     * Creates new form NewJPanel
     *
     * @param eng
     */
    public RemoteFilesPanel(BackupEngine eng) {
        this.engine = eng;
        this.client = eng.getClient();

        initComponents();

        initializeList();

        filesList.setDragEnabled(true);
        filesList.setTransferHandler(new TransferHandler() {

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                if (!selectedPath.equals("/")) {
                    return false;
                }

                return support.isDataFlavorSupported(LocalFileSelection.localFileFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {

                if (!canImport(support)) {
                    return false;
                }

                // fetch the drop location
                JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();

                int index = dl.getIndex();

                // fetch the data and bail if this fails
                ArrayList<String> data;
                try {
                    data = (ArrayList<String>) support.getTransferable().getTransferData(LocalFileSelection.localFileFlavor);
                } catch (UnsupportedFlavorException e) {
                    return false;
                } catch (java.io.IOException e) {
                    return false;
                }

                listener.folderDragged(data);

                return true;
            }

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }

            @Override
            protected Transferable createTransferable(JComponent c) {              
                return new RemoteFileSelection(((RemoteFile) filesList.getSelectedValue()).getPath());
            }

            @Override
            protected void exportDone(JComponent source, Transferable data, int action) {

            }

        });

    }

    public void setListener(RemoteFolderListener listener) {
        this.listener = listener;
    }

    private void initializeList() {
        listModel = new DefaultListModel<>();
        filesList.setModel(listModel);
        

        filesList.setCellRenderer(new FileListCellRenderer());

        displayBackups();
        JPopupMenu popM = new JPopupMenu();
        JMenuItem removeBackup = new JMenuItem("Remove Backup");
        ActionListener removeHandler = new RemoveHandler();
        
        removeBackup.addActionListener(removeHandler);
        popM.add(removeBackup);
        filesList.setComponentPopupMenu(popM);

        filesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    RemoteFile selectedValue = (RemoteFile) filesList.getSelectedValue();

                    if (selectedValue.toString().equals("..")) {
                        moveToUpFolder();
                    } else {
                        if (selectedValue.isDirectory()) {
                            moveToFolder(selectedValue.toString());
                        }
                    }
                }
            }
        });

        filesList.setDragEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        filesList = new javax.swing.JList();

        jScrollPane1.setViewportView(filesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList filesList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void moveToUpFolder() {
        int pos = selectedPath.lastIndexOf("/");

        selectedPath = selectedPath.substring(0, pos);

        if (selectedPath.endsWith("backups")) {
            selectedPath = "/";
            displayBackups();
        } else {
            displayFolder();
        }

    }

    private void moveToFolder(String folder) {

        if (selectedPath.equals("/")) {
            selectedPath += "backups/" + folder;
        } else {
            selectedPath += "/" + folder;
        }

        displayFolder();
    }

    private void displayFolder() {
        try {

            List<RemoteFile> list = client.getChildren(selectedPath);

            listModel.clear();

            listModel.addElement(new RemoteFile("..", true, 0));

            for (RemoteFile file : list) {
                listModel.addElement(file);
            }

        } catch (IOException ex) {
            Logger.getLogger(RemoteFilesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    List<RemoteFile> list = client.getChildren(selectedPath);

                    listModel.clear();

                    listModel.addElement(new RemoteFile("..", true, 0));

                    for (RemoteFile file : list) {
                        listModel.addElement(file);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(RemoteFilesPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void displayBackups() {
        selectedPath = "/";
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    listModel.clear();

                    List<String> backups = engine.listBackups();
                    for (String str : backups) {
                        listModel.addElement(new RemoteFile(str, true, 0));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public String getSelectedFolder() {
        return ((RemoteFile) filesList.getSelectedValue()).getPath();
    }

    private class RemoveHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String folder = getSelectedFolder();
            System.out.println("Removing "+folder);            
            engine.deleteBackup(folder);
            displayBackups();
        }
        
        

    }

    private class FileListCellRenderer extends DefaultListCellRenderer {

        private static final long serialVersionUID = -7799441088157759804L;
        private FileSystemView fileSystemView;
        private JLabel label;
        private Color textSelectionColor = Color.BLACK;
        private Color backgroundSelectionColor = Color.decode("#9DA19E");
        private Color textNonSelectionColor = Color.BLACK;
        private Color backgroundNonSelectionColor = Color.WHITE;

        FileListCellRenderer() {
            label = new JLabel();
            label.setOpaque(true);
            fileSystemView = FileSystemView.getFileSystemView();
        }

        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean selected,
                boolean expanded) {

            RemoteFile entry = (RemoteFile) value;

            if (selectedPath.equals("/")) {
                setLabelIcon("backup24.png");
            } else {
                if (entry.isDirectory()) {
                    setLabelIcon("folder24.png");
                } else {
                    setLabelIcon("file24.png");
                }
            }

            label.setText(entry.toString());
            label.setToolTipText(entry.getPath());

            if (selected) {
                label.setBackground(backgroundSelectionColor);
                label.setForeground(textSelectionColor);
            } else {
                label.setBackground(backgroundNonSelectionColor);
                label.setForeground(textNonSelectionColor);
            }

            return label;
        }

        private void setLabelIcon(String string) {
            java.net.URL imageURL = FileList.class.getResource("/org/lanca/gui/icons/"
                    + string);
            ImageIcon icon = new ImageIcon(imageURL);
            label.setIcon(icon);
        }
    }

}
