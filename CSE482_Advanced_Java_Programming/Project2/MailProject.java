package secondadvancedproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class MailProject extends javax.swing.JFrame {

   private MailSettings mailSettings;
   private DefaultListModel dlm;
   private ArrayList<ReceivedMails> mailList;
   private SendProps sendProperties;
   private ReceiveProps receiveProperties;
   private Properties propsR;
   private Properties propsS;
   private static boolean settingType;
   private boolean mailType;
   private File f;
   private JPopupMenu menu;
   private JMenuItem display;
   private JMenuItem reply;
   private JMenuItem close;
   private ViewMail vm;

   public MailProject() {
      initComponents();
      setLocationRelativeTo(this);

      propsR = new Properties();
      propsS = new Properties();
      
      dlm = new DefaultListModel();
      receivedList.setModel(dlm);
      mailList = new ArrayList<>();
      
      menu = new JPopupMenu();
      
      display = new JMenuItem("Display Message");
      reply = new JMenuItem("Reply");
      close = new JMenuItem("Close");
      menu.add(display);
      menu.add(reply);
      menu.add(close);
      
      f = null;

      //menu items
      receiveSettings.addActionListener(new MyListener());
      sendSettings.addActionListener(new MyListener());
      exitProgram.addActionListener(new MyListener());

      //radio buttons
      receiveAllButton.addActionListener(new MyListener());
      receiveUnreadButton.addActionListener(new MyListener());

      //buttons
      receiveButton.addActionListener(new MyListener());
      sendAttachButton.addActionListener(new MyListener());
      sendButton.addActionListener(new MyListener());

   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      buttonGroup1 = new javax.swing.ButtonGroup();
      tab = new javax.swing.JTabbedPane();
      receiveMailTab = new javax.swing.JPanel();
      jPanel1 = new javax.swing.JPanel();
      receiveButton = new javax.swing.JButton();
      receiveAllButton = new javax.swing.JRadioButton();
      receiveUnreadButton = new javax.swing.JRadioButton();
      jLabel11 = new javax.swing.JLabel();
      jLabel12 = new javax.swing.JLabel();
      jLabel13 = new javax.swing.JLabel();
      jLabel14 = new javax.swing.JLabel();
      receiveAllMessageCount = new javax.swing.JLabel();
      receiveUnreadMessageCount = new javax.swing.JLabel();
      jLabel10 = new javax.swing.JLabel();
      jLabel17 = new javax.swing.JLabel();
      jLabel18 = new javax.swing.JLabel();
      jPanel2 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      receivedList = new javax.swing.JList();
      sendMailTab = new javax.swing.JPanel();
      jPanel3 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      sendToArea = new javax.swing.JTextArea();
      sendAttachmentArea = new javax.swing.JTextArea();
      sendSubjectArea = new javax.swing.JTextArea();
      sendButton = new javax.swing.JButton();
      sendAttachButton = new javax.swing.JButton();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jLabel6 = new javax.swing.JLabel();
      jLabel7 = new javax.swing.JLabel();
      jLabel8 = new javax.swing.JLabel();
      jLabel9 = new javax.swing.JLabel();
      jPanel4 = new javax.swing.JPanel();
      jScrollPane3 = new javax.swing.JScrollPane();
      sendMailArea = new javax.swing.JTextArea();
      jMenuBar1 = new javax.swing.JMenuBar();
      jMenu1 = new javax.swing.JMenu();
      receiveSettings = new javax.swing.JMenuItem();
      sendSettings = new javax.swing.JMenuItem();
      exitProgram = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setMaximumSize(new java.awt.Dimension(600, 400));
      setMinimumSize(new java.awt.Dimension(600, 400));
      setPreferredSize(new java.awt.Dimension(600, 400));

      receiveMailTab.setLayout(new java.awt.BorderLayout());

      jPanel1.setLayout(new java.awt.GridBagLayout());

      receiveButton.setText("Get / Refresh");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.gridheight = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel1.add(receiveButton, gridBagConstraints);

      buttonGroup1.add(receiveAllButton);
      receiveAllButton.setSelected(true);
      receiveAllButton.setText("All");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(receiveAllButton, gridBagConstraints);

      buttonGroup1.add(receiveUnreadButton);
      receiveUnreadButton.setText("Unread");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(receiveUnreadButton, gridBagConstraints);

      jLabel11.setText(" ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(jLabel11, gridBagConstraints);

      jLabel12.setText("Message Count:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(jLabel12, gridBagConstraints);

      jLabel13.setText("Message Count:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(jLabel13, gridBagConstraints);

      jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel14.setText("Newest messages are on top");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel14, gridBagConstraints);

      receiveAllMessageCount.setText("0");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(receiveAllMessageCount, gridBagConstraints);

      receiveUnreadMessageCount.setText("0");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel1.add(receiveUnreadMessageCount, gridBagConstraints);

      jLabel10.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 4;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel10, gridBagConstraints);

      jLabel17.setText("                                                       ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 4;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel17, gridBagConstraints);

      jLabel18.setText("  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.gridwidth = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel18, gridBagConstraints);

      receiveMailTab.add(jPanel1, java.awt.BorderLayout.PAGE_START);

      jPanel2.setLayout(new java.awt.BorderLayout());

      receivedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      receivedList.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            receivedListMouseClicked(evt);
         }
      });
      jScrollPane1.setViewportView(receivedList);

      jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      receiveMailTab.add(jPanel2, java.awt.BorderLayout.CENTER);

      tab.addTab("Receive Mail", receiveMailTab);

      sendMailTab.setLayout(new java.awt.BorderLayout());

      jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      jPanel3.setLayout(new java.awt.GridBagLayout());

      jLabel1.setText("To:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(jLabel1, gridBagConstraints);

      jLabel2.setText("Subject:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(jLabel2, gridBagConstraints);

      jLabel3.setText("Attachment:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(jLabel3, gridBagConstraints);

      sendToArea.setColumns(20);
      sendToArea.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
      sendToArea.setLineWrap(true);
      sendToArea.setRows(1);
      sendToArea.setTabSize(0);
      sendToArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 2.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(sendToArea, gridBagConstraints);

      sendAttachmentArea.setColumns(20);
      sendAttachmentArea.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
      sendAttachmentArea.setLineWrap(true);
      sendAttachmentArea.setRows(1);
      sendAttachmentArea.setTabSize(0);
      sendAttachmentArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 2.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(sendAttachmentArea, gridBagConstraints);

      sendSubjectArea.setColumns(20);
      sendSubjectArea.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
      sendSubjectArea.setLineWrap(true);
      sendSubjectArea.setRows(1);
      sendSubjectArea.setTabSize(0);
      sendSubjectArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 2.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(sendSubjectArea, gridBagConstraints);

      sendButton.setText("SEND");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.gridheight = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(sendButton, gridBagConstraints);

      sendAttachButton.setText("Attach");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
      jPanel3.add(sendAttachButton, gridBagConstraints);

      jLabel4.setText("           ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel4, gridBagConstraints);

      jLabel5.setText("            ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel5, gridBagConstraints);

      jLabel6.setText("     ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel6, gridBagConstraints);

      jLabel7.setText("     ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel7, gridBagConstraints);

      jLabel8.setText("   ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel8, gridBagConstraints);

      jLabel9.setText(" ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 6;
      gridBagConstraints.gridwidth = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel9, gridBagConstraints);

      sendMailTab.add(jPanel3, java.awt.BorderLayout.NORTH);

      jPanel4.setLayout(new java.awt.BorderLayout());

      sendMailArea.setColumns(20);
      sendMailArea.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
      sendMailArea.setLineWrap(true);
      sendMailArea.setRows(5);
      sendMailArea.setTabSize(0);
      jScrollPane3.setViewportView(sendMailArea);

      jPanel4.add(jScrollPane3, java.awt.BorderLayout.CENTER);

      sendMailTab.add(jPanel4, java.awt.BorderLayout.CENTER);

      tab.addTab("Send Mail", sendMailTab);

      getContentPane().add(tab, java.awt.BorderLayout.CENTER);

      jMenu1.setText("File");

      receiveSettings.setText("Receive Mail Settings");
      jMenu1.add(receiveSettings);

      sendSettings.setText("Send Mail Settings");
      jMenu1.add(sendSettings);

      exitProgram.setText("Exit");
      jMenu1.add(exitProgram);

      jMenuBar1.add(jMenu1);

      setJMenuBar(jMenuBar1);

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void receivedListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receivedListMouseClicked
      receivedList.addMouseListener(new MouseAdapter() {
         public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
               menu.setVisible(false);
//               System.out.println("THATS RIGHT");
               display.addActionListener(new MyListener());
               reply.addActionListener(new MyListener());
               close.addActionListener(new MyListener());
               menu.show(null, e.getXOnScreen(), e.getYOnScreen());
            }
         }
      });
   }//GEN-LAST:event_receivedListMouseClicked

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
         java.util.logging.Logger.getLogger(MailProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(MailProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(MailProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(MailProject.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new MailProject().setVisible(true);
         }
      });
   }

   public static boolean getSettingType() {
      return settingType;
   }

   private void receiveMail() {
      mailList.removeAll(mailList);
      String contentString = "";
      propsR.setProperty("mail.store.protocol", receiveProperties.getProtocol());
      Session session = Session.getInstance(propsR, null);
      try {
         Store store = session.getStore("imaps");
         store.connect(receiveProperties.getHost(), receiveProperties.getUser(), receiveProperties.getPassword());

         Folder inbox = store.getFolder("INBOX");
         inbox.open(Folder.READ_ONLY);

         int msgCount = inbox.getMessageCount();
         int msgUnreadCount = inbox.getUnreadMessageCount();

         receiveAllMessageCount.setText(msgCount + "");
         receiveUnreadMessageCount.setText(msgUnreadCount + "");

         Message[] message = null;
         if (mailType) {
            message = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
         } else {
            message = inbox.getMessages();
         }
         for (int i = 0; i < message.length; i++) {
            Message msg = message[i];

            Object content = msg.getContent();
            if (content instanceof String) {
               contentString = (String) content;
            } else if (content instanceof Multipart) {
               Multipart mp = (Multipart) content;
               int partCount = mp.getCount();
               for (int j = 0; j < partCount; j++) {
                  MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(j);
                  if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
                     bp.saveFile(bp.getFileName());
                  }
               }
               BodyPart bp = mp.getBodyPart(0);
               contentString = bp.getContent().toString();
            } else {
               System.out.println("is there any type???");
            }

            String s = Arrays.toString(msg.getFrom());
            String ss = s.substring(1, s.length() - 1);
            ReceivedMails rm = new ReceivedMails(ss, msg.getSubject(), contentString, msg.getSentDate());
            mailList.add(rm);
         }
         setDLM();
      } catch (Exception ex) {
         System.out.println("Exception: " + ex.getMessage());
      }
      System.out.println("All Received");
   }

   private void setDLM() {
      dlm.removeAllElements();
      Collections.reverse(mailList);
      for (int i = 0; i < mailList.size(); i++) {
         dlm.addElement(mailList.get(i).getFrom() + " " + mailList.get(i).getSubject());
      }
   }

   private void sendMailWithoutAttachment() {
      propsS.put("mail.smtp.host", sendProperties.getHost());
      propsS.put("mail.smtp.port", sendProperties.getPort());
      propsS.put("mail.smtp.socketFactory.port", sendProperties.getPort());
      propsS.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      propsS.put("mail.from", sendProperties.getUser());
      propsS.put("mail.smtp.auth", sendProperties.isAuth());
      Authenticator a = new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sendProperties.getUser(), sendProperties.getPassword());
         }
      };

      Session session = Session.getDefaultInstance(propsS, a);
      try {
         MimeMessage msg = new MimeMessage(session);
         msg.setFrom();
         msg.setRecipients(Message.RecipientType.TO, sendToArea.getText());
         msg.setSubject(sendSubjectArea.getText());
         msg.setSentDate(new Date());
         msg.setText(sendMailArea.getText());
         Transport.send(msg);
         emailSend();
      } catch (MessagingException mex) {
         System.out.println("send failed, exception: " + mex);
      }
   }

   private void sendMailWithAttachment() {
      propsS.put("mail.smtp.host", sendProperties.getHost());
      propsS.put("mail.smtp.port", sendProperties.getPort());
      propsS.put("mail.smtp.socketFactory.port", sendProperties.getPort());
      propsS.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      propsS.put("mail.from", sendProperties.getUser());
      propsS.put("mail.smtp.auth", sendProperties.isAuth());
      Authenticator a = new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sendProperties.getUser(), sendProperties.getPassword());
         }
      };

      Session session = Session.getDefaultInstance(propsS, a);
      try {
         MimeMessage msg = new MimeMessage(session);
         msg.setFrom();
         msg.setRecipients(Message.RecipientType.TO, sendToArea.getText());
         msg.setSubject(sendSubjectArea.getText());
         msg.setSentDate(new Date());
         Multipart mp = new MimeMultipart();
         BodyPart bp = new MimeBodyPart();
         bp.setText(sendMailArea.getText());
         mp.addBodyPart(bp);

         BodyPart partForAtt = new MimeBodyPart();
         String filename = f.getPath();
         DataSource source = new FileDataSource(filename);
         partForAtt.setDataHandler(new DataHandler(source));
         partForAtt.setFileName(filename);
         mp.addBodyPart(partForAtt);
         msg.setContent(mp);
         Transport.send(msg);
         emailSend();
      } catch (MessagingException ex) {
         System.out.println("send failed, exception: " + ex.getMessage());
      }
   }

   private void emailSend() {
      JOptionPane.showMessageDialog(null, "I have send your mail.");
      sendToArea.setText("");
      sendSubjectArea.setText("");
      sendAttachmentArea.setText("");
      sendMailArea.setText("");
   }

   class MyListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JRadioButton) {
            JRadioButton button = (JRadioButton) e.getSource();
            if (button.getText().equals("All")) {
               mailType = false;
            } else if (button.getText().equals("Unread")) {
               mailType = true;
            } else {
               System.out.println("no other jradio button??");
            }
         } else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Get / Refresh")) {
               if (propertiesOK("r")) {
                  receiveMail();
               } else {
                  JOptionPane.showMessageDialog(null, "I could not find imaps.properties file");
               }
            } else if (button.getText().equals("SEND")) {
               if (propertiesOK("s")) {
                  if (areasOK("send")) {
                     if (f != null) {
                        sendMailWithAttachment();
                     } else {
                        sendMailWithoutAttachment();
                     }
                     f = null;
                  }
               } else {
                  JOptionPane.showMessageDialog(null, "I could not find smtp.properties file");
               }
            } else if (button.getText().equals("Attach")) {
               JFileChooser file = new JFileChooser();
               if (file.showOpenDialog(file) == JFileChooser.APPROVE_OPTION) {
                  f = file.getSelectedFile();
                  sendAttachmentArea.setText(f.getPath());
               }
            } else {
               System.out.println("no other jbutton???");
            }
         } else if (e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) e.getSource();
            if (item.getText().equals("Receive Mail Settings")) {
               settingType = false;
               mailSettings = new MailSettings(null, false);
               mailSettings.setVisible(true);
            } else if (item.getText().equals("Send Mail Settings")) {
               settingType = true;
               mailSettings = new MailSettings(null, false);
               mailSettings.setVisible(true);
            } else if (item.getText().equals("Exit")) {
               System.exit(0);
            } else if (item.getText().toLowerCase().contains("display")) {
               int index = receivedList.getSelectedIndex();
               vm = new ViewMail(null, false, mailList.get(index).info());
               vm.setVisible(true);
               menu.setSelected(null);
            } else if (item.getText().toLowerCase().contains("reply")) {
               int index = receivedList.getSelectedIndex();
               ReceivedMails rm = mailList.get(index);
               sendToArea.setText(rm.getFrom());
               sendSubjectArea.setText("RE: " + rm.getSubject());
               tab.setSelectedIndex(1);
            } else if (item.getText().toLowerCase().contains("close")) {
               menu.setVisible(false);
            } else {
               System.out.println("no other menu item.");
            }
         }
      }

      private boolean areasOK(String s) {
         if (!sendToArea.getText().isEmpty() && !sendSubjectArea.getText().isEmpty()) {
            if (!sendToArea.getText().contains("@")) {
               JOptionPane.showMessageDialog(null, "There should be ( @ )  in To Area ");
               return false;
            }
         } else {
            JOptionPane.showMessageDialog(null, "To Area/Subject Area  is empty.");
            return false;
         }
         return true;
      }

      private boolean propertiesOK(String b) {
         File file;
         Scanner s;
         if (b.equals("r")) {
            file = new File("imaps.properties");
            try {
               if (receiveProperties == null) {
                  s = new Scanner(file);
                  String x = "";
                  while (s.hasNextLine()) {
                     x += s.nextLine() + "-";
                  }
                  receiveProperties = new ReceiveProps(x.split("-"));
               }
            } catch (FileNotFoundException ex) {
               System.out.println("FileNotFoundException: " + ex.getMessage());
               return false;
            }
         } else if (b.equals("s")) {
            file = new File("smtp.properties");
            try {
               if (sendProperties == null) {
                  s = new Scanner(file);
                  String x = "";
                  while (s.hasNextLine()) {
                     x += s.nextLine() + "-";
                  }
                  sendProperties = new SendProps(x.split("-"));
               }
            } catch (FileNotFoundException ex) {
               System.out.println("FileNotFoundException: " + ex.getMessage());
               return false;
            }
         }
         return true;
      }
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.ButtonGroup buttonGroup1;
   private javax.swing.JMenuItem exitProgram;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel12;
   private javax.swing.JLabel jLabel13;
   private javax.swing.JLabel jLabel14;
   private javax.swing.JLabel jLabel17;
   private javax.swing.JLabel jLabel18;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JMenu jMenu1;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JRadioButton receiveAllButton;
   private javax.swing.JLabel receiveAllMessageCount;
   private javax.swing.JButton receiveButton;
   private javax.swing.JPanel receiveMailTab;
   private javax.swing.JMenuItem receiveSettings;
   private javax.swing.JRadioButton receiveUnreadButton;
   private javax.swing.JLabel receiveUnreadMessageCount;
   private javax.swing.JList receivedList;
   private javax.swing.JButton sendAttachButton;
   private javax.swing.JTextArea sendAttachmentArea;
   private javax.swing.JButton sendButton;
   private javax.swing.JTextArea sendMailArea;
   private javax.swing.JPanel sendMailTab;
   private javax.swing.JMenuItem sendSettings;
   private javax.swing.JTextArea sendSubjectArea;
   private javax.swing.JTextArea sendToArea;
   private javax.swing.JTabbedPane tab;
   // End of variables declaration//GEN-END:variables
}
