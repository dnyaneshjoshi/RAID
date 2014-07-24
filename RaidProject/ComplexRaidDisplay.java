import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import raidFileStreams.raid01FileStreams.Raid01FileOutputStream;
import raidFileStreams.raid10FileStreams.Raid10FileOutputStream;

/**
 * Display for RAID 1+0 and 0+1 type files
 * @author Ramana, Dnyanesh
 */
class ComplexRaidDisplay extends javax.swing.JFrame implements ActionListener
{
	private JButton bSave;
    /**
     * Creates new form ComplexRaidDisplay
     */
    public ComplexRaidDisplay() {
        initComponents();
        this.bSave=new JButton("Save & Refresh");
        this.add(this.bSave);
        this.bSave.setBounds(615, 135, 140, 40);
        this.bSave.addActionListener(this);
        this.bSave.setVisible(true);
        this.jTextAreaFile1.setEditable(false);
        this.jTextAreaFile2.setEditable(false);
        this.jTextAreaFile3.setEditable(false);
        this.jTextAreaFile4.setEditable(false);
    }
    
    int raidType;
    String name=null;
    RaidSelection r;
    
    void setVisibleForRaid2(String name, RaidSelection r)
    {
    	this.raidType=2;
    	this.name=name;
    	this.r=r;
    	this.setTitle("RAID1+0 File - "+this.name);
    	this.setVisible(true);
    }
    void setVisibleForRaid3(String name, RaidSelection r)
    {
    	this.raidType=3;
    	this.name=name;
    	this.r=r;
    	this.setTitle("RAID0+1 File - "+this.name);
    	this.setVisible(true);
    }
    
    @Override
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		
		if(o.equals(this.bSave))
		{
			if(this.raidType==2)
			{
				try
				{
					Raid10FileOutputStream rw=new Raid10FileOutputStream(name);
					rw.writeByteArray(this.jTextPaneRaidMain.getText());
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
					Raid01FileOutputStream rw=new Raid01FileOutputStream(name);
					rw.writeByteArray(this.jTextPaneRaidMain.getText());
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

    	this.setLocation(100,100);
    	this.setSize(800, 600);
        this.setResizable(false);
        this.setLayout(null);
        
        jLabelRaidMain = new javax.swing.JLabel();
        jTextPaneRaidMain = new javax.swing.JTextPane(){
            public boolean getScrollableTracksViewportWidth()
            {
                return getUI().getPreferredSize(this).width 
                    <= getParent().getSize().width;
            }
        };
        jLabelGroup1 = new javax.swing.JLabel();
        jLabelGroup2 = new javax.swing.JLabel();
        jLabelFile1 = new javax.swing.JLabel();
        jLabelFile2 = new javax.swing.JLabel();
        jLabelFile3 = new javax.swing.JLabel();
        jLabelFile4 = new javax.swing.JLabel();        
        jTextAreaFile1 = new javax.swing.JTextArea();
        jTextAreaFile2 = new javax.swing.JTextArea();
        jTextAreaFile3 = new javax.swing.JTextArea();
        jTextAreaFile4 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane(jTextPaneRaidMain);
        jScrollPane3 = new javax.swing.JScrollPane(jTextAreaFile1);
        jScrollPane4 = new javax.swing.JScrollPane(jTextAreaFile2);
        jScrollPane5 = new javax.swing.JScrollPane(jTextAreaFile3);
        jScrollPane6 = new javax.swing.JScrollPane(jTextAreaFile4);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jTextAreaFile1.setToolTipText("Contents of Group1-File1");
        jTextAreaFile2.setToolTipText("Contents of Group1-File2");
        jTextAreaFile3.setToolTipText("Contents of Group2-File1");
        jTextAreaFile4.setToolTipText("Contents of Group2-File2");
        jTextPaneRaidMain.setToolTipText("Enter the Contents of the Main file here");
        
        jLabelRaidMain.setText("Main File");
        jLabelGroup1.setText("Group1");
        jLabelGroup2.setText("Group2");
        jLabelFile1.setText("File1");
        jLabelFile2.setText("File2");
        jLabelFile3.setText("File1");
        jLabelFile4.setText("File2");

        jLabelRaidMain.setBounds(10, 10, 100, 15);
        jLabelGroup1.setBounds(10, 200, 100, 15);
        jLabelGroup2.setBounds(400, 200, 100, 15);
        jLabelFile1.setBounds(10, 250, 100, 15);
        jLabelFile2.setBounds(200, 250, 100, 15);
        jLabelFile3.setBounds(400, 250, 100, 15);
        jLabelFile4.setBounds(600, 250, 100, 15);
        
        jScrollPane1.setBounds(10, 30, 770, 100);
        jScrollPane3.setBounds(10, 280, 180, 250);
        jScrollPane4.setBounds(200, 280, 180, 250);
        jScrollPane5.setBounds(400, 280, 180, 250);
        jScrollPane6.setBounds(600, 280, 180, 250);
        
        this.add(jLabelRaidMain);
        this.add(jLabelGroup1);
        this.add(jLabelGroup2);
        this.add(jLabelFile1);
        this.add(jLabelFile2);
        this.add(jLabelFile3);
        this.add(jLabelFile4);
        
        this.add(jScrollPane1);
        this.add(jScrollPane3);
        this.add(jScrollPane4);
        this.add(jScrollPane5);
        this.add(jScrollPane6);
    }// </editor-fold>                        

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComplexRaidDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ComplexRaidDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabelFile1;
    private javax.swing.JLabel jLabelFile2;
    private javax.swing.JLabel jLabelFile3;
    private javax.swing.JLabel jLabelFile4;
    private javax.swing.JLabel jLabelGroup1;
    private javax.swing.JLabel jLabelGroup2;
    private javax.swing.JLabel jLabelRaidMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JTextArea jTextAreaFile1;
    public javax.swing.JTextArea jTextAreaFile2;
    public javax.swing.JTextArea jTextAreaFile3;
    public javax.swing.JTextArea jTextAreaFile4;
    public javax.swing.JTextPane jTextPaneRaidMain;
    // End of variables declaration                   
}
