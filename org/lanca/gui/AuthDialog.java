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

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.caboclo.clients.AmazonClient;
import org.caboclo.clients.ApiClient;
import org.caboclo.clients.CloudService;
import org.caboclo.clients.DropboxClient;
import org.caboclo.clients.GoogleDriveClient;
import org.caboclo.clients.OpenStackClient;
import org.caboclo.clients.OneDriveClient;
import org.caboclo.util.Credentials;

public class AuthDialog extends javax.swing.JFrame {

    private CardLayout cards;
    private ApiClient dropboxClient;
    private ApiClient googleDriveClient;
    private ApiClient oneDriveClient;
    private ApiClient openStackClient;
    private ApiClient amazonClient;
    private AuthPanel dropboxPanel;
    private AuthPanel googleDrivePanel;
    private AuthPanel oneDrivePanel;
    private AuthPanel openStackPanel;
    private AuthPanel amazonPanel;
    private final Credentials credentials;

    /**
     * Creates new form AuthDialog
     */
//    public AuthDialog(java.awt.Frame parent, boolean modal) {
//        super(parent, modal);
//
//        initComponents();
//        initPanels();
//
//        updateComboBoxPanel();
//    }
    public AuthDialog() {
        initComponents();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        initPanels();

        credentials = new Credentials();

        initializeServicesComboBox();

        updateComboBoxPanel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        servicesComboBox = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cloudServiceComboBox = new javax.swing.JComboBox();
        authPanel = new javax.swing.JPanel();
        createAccountButton = new javax.swing.JButton();
        loginSavedAccountButton = new javax.swing.JButton();
        deleteAccountButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());
        setLocationByPlatform(true);

        jLabel1.setText("Choose saved account...");

        servicesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<no account saved>" }));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("... or enter a new account");

        jLabel4.setText("Cloud service:");

        cloudServiceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dropbox", "Google Drive", "OneDrive", "Amazon S3", "OpenStack" }));
        cloudServiceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cloudServiceComboBoxActionPerformed(evt);
            }
        });

        authPanel.setLayout(new java.awt.CardLayout());

        createAccountButton.setText("Create account and login");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        loginSavedAccountButton.setText("Login");
        loginSavedAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginSavedAccountButtonActionPerformed(evt);
            }
        });

        deleteAccountButton.setText("Delete account");
        deleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(servicesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginSavedAccountButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteAccountButton)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(createAccountButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cloudServiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 118, Short.MAX_VALUE))
                    .addComponent(authPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cloudServiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(servicesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(authPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createAccountButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(loginSavedAccountButton)
                                    .addComponent(deleteAccountButton)))))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cloudServiceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cloudServiceComboBoxActionPerformed
        updateComboBoxPanel();
    }//GEN-LAST:event_cloudServiceComboBoxActionPerformed

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed
        final JFrame _this = this;

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boolean b = getVisiblePanel().authenticate();

                if (b) {
                    MainForm form = new MainForm(getVisiblePanel().getClient());
                    form.setVisible(true);
                    _this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(_this,
                            "Could not authenticate with the cloud server",
                            "Authentication error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }//GEN-LAST:event_createAccountButtonActionPerformed

    private void loginSavedAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginSavedAccountButtonActionPerformed

        String selectedService = getSelectedService();
        ApiClient client = credentials.retrieveAccount(selectedService);

        if (client == null) {
            // TODO invalid saved credentials...
            System.out.println("Invalid credentials.");
            return;
        }

        MainForm form = new MainForm(client);
        form.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_loginSavedAccountButtonActionPerformed

    private void deleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccountButtonActionPerformed
        Object item = servicesComboBox.getSelectedItem();

        if (item != null) {
            String service = item.toString();
            if (!service.isEmpty()) {
                credentials.removeCredentials(service);
                servicesComboBox.removeItem(service);
            }
        }
        if (servicesComboBox.getItemCount()<1){
            servicesComboBox.addItem("<no account saved>");
            deleteAccountButton.setEnabled(false);
            loginSavedAccountButton.setEnabled(false);
        }
    }//GEN-LAST:event_deleteAccountButtonActionPerformed

    @Override
    public Image getIconImage() {
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/org/lanca/gui/icons/Caboclo.png"));
    }

    private void updateComboBoxPanel() {
        String selectedItem = (String) cloudServiceComboBox.getSelectedItem();

        cards.show(authPanel, selectedItem);

        this.pack();
    }

    private void initPanels() {
        openStackClient = new OpenStackClient();
        dropboxClient = new DropboxClient();
        googleDriveClient = new GoogleDriveClient();
        amazonClient = new AmazonClient();
        oneDriveClient = new OneDriveClient();

        dropboxPanel = new OAuthPanel(dropboxClient);
        googleDrivePanel = new OAuthPanel(googleDriveClient);
        oneDrivePanel = new OAuthPanel(oneDriveClient);
        openStackPanel = new OpenStackPanel(openStackClient);
        amazonPanel = new AmazonPanel(amazonClient);

        authPanel.add(dropboxPanel, CloudService.DROPBOX.toString());
        authPanel.add(googleDrivePanel, CloudService.GOOGLE_DRIVE.toString());
        authPanel.add(oneDrivePanel, CloudService.ONE_DRIVE.toString());
        authPanel.add(openStackPanel, CloudService.OPEN_STACK.toString());
        authPanel.add(amazonPanel, CloudService.AMAZON_S3.toString());

        cards = (CardLayout) authPanel.getLayout();
    }

    private AuthPanel getVisiblePanel() {
        AuthPanel card = null;

        for (Component comp : authPanel.getComponents()) {
            if (comp.isVisible() == true) {
                card = (AuthPanel) comp;
            }
        }

        return card;
    }

    private void initializeServicesComboBox() {
        List<String> savedAccounts = credentials.retrieveSavedAccounts();

        if (savedAccounts.size() < 1) {
            servicesComboBox.setEnabled(false);
            deleteAccountButton.setEnabled(false);
            loginSavedAccountButton.setEnabled(false);
            return;
        }else{
            servicesComboBox.setEnabled(true);
            deleteAccountButton.setEnabled(true);
            loginSavedAccountButton.setEnabled(true);
        }

        System.out.println(savedAccounts);

        DefaultComboBoxModel<String> dcbm = (DefaultComboBoxModel<String>) servicesComboBox.getModel();
        dcbm.removeAllElements();

        for (String account : savedAccounts) {
            dcbm.addElement(account);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //AuthDialog dialog = new AuthDialog(new javax.swing.JFrame(), true);
                AuthDialog dialog = new AuthDialog();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel authPanel;
    private javax.swing.JComboBox cloudServiceComboBox;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JButton deleteAccountButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton loginSavedAccountButton;
    private javax.swing.JComboBox servicesComboBox;
    // End of variables declaration//GEN-END:variables
// </editor-fold> 

    private String getSelectedService() {
        return servicesComboBox.getSelectedItem().toString();
    }
}
