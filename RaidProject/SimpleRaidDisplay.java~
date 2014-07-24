import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import raidFileStreams.raid0FileStreams.Raid0FileOutputStream;
import raidFileStreams.raid1FileStreams.Raid1FileOutputStream;

/**
 * Display for RAID 0 and 1 type files
 * @author Ramana, Dnyanesh
 */
class SimpleRaidDisplay extends javax.swing.JFrame implements ActionListener 
{
	
	private JButton bSave;
	
    /**
     * Creates new form SimpleRaidDisplay
     */
    public SimpleRaidDisplay() {
        initComponents();
        this.setLayout(null);
        this.setBounds(100, 100, 640, 560);
        this.setResizable(false);
        this.bSave=new JButton("Save & Refresh");
        this.add(this.bSave);
        //this.bSave.setBounds(this.jTextPane3.getX(), this.jTextPane3.getY()+this.jTextPane3.getHeight()+10, 50, 20);
        this.bSave.setBounds(430, 450, 140, 40);
        this.bSave.addActionListener(this);
        this.bSave.setVisible(true);
        this.jTextArea1.setEditable(false);
        this.jTextArea2.setEditable(false);
        this.jTextPane3.requestFocus();
    }
    
    int raidType;
    String name=null;
    RaidSelection r;
    
    void setVisibleForRaid0(String name, RaidSelection r)
    {
    	this.raidType=0;
    	this.name=name;
    	this.r=r;
    	this.setTitle("RAID0 File - "+this.name);
    	this.setVisible(true);
    }
    void setVisibleForRaid1(String name, RaidSelection r)
    {
    	this.raidType=1;
    	this.name=name;
    	this.r=r;
    	this.setTitle("RAID1 File - "+this.name);
    	this.setVisible(true);
    }
    
    @Override
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		
		if(o.equals(this.bSave))
		{
			if(this.raidType==0)
			{
				try
				{
					Raid0FileOutputStream rw=new Raid0FileOutputStream(name);
					rw.writeByteArray(this.jTextPane3.getText());
					rw.close();
					this.dispose();
					this.r.refresh();
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}
			}
			else
			{
				try
				{
					Raid1FileOutputStream rw=new Raid1FileOutputStream(name);
					rw.writeByteArray(this.jTextPane3.getText());
					rw.close();
					this.dispose();
					this.r.refresh();
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(this, ee.getMessage());
				}
			}
		}
	}
    
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
        jLabelFile1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelFile2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabelMainFile = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getUI().getPreferredSize(this).width 
                    <= getParent().getSize().width;
            }
        };
        
        
        //set tool tips
        
        jTextArea1.setToolTipText("Contents of the file on disk 1");
        jTextArea2.setToolTipText("Contents of the file on disk 2");
        jTextPane3.setToolTipText("Enter contents of the Main file here");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Raid Display");

        jLabelFile1.setText("File1");
        jScrollPane1.setViewportView(jTextArea1);
        jLabelFile2.setText("File2");
        jScrollPane2.setViewportView(jTextArea2);
        jLabelMainFile.setText("Main File");
        jScrollPane3.setViewportView(jTextPane3);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMainFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFile1)
                    .addComponent(jLabelFile2)
                    .addComponent(jLabelMainFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }                        

    /**
     * @param args the command line arguments
     */
    public static void proceed()
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SimpleRaidDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SimpleRaidDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SimpleRaidDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimpleRaidDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleRaidDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabelFile1;
    private javax.swing.JLabel jLabelFile2;
    private javax.swing.JLabel jLabelMainFile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea2;
    public javax.swing.JTextPane jTextPane3;
    // End of variables declaration                   
}
