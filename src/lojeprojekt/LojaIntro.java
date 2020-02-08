package lojeprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LojaIntro extends JFrame {

	private JPanel contentPane;
	private int nrLojtareve;
	//private Dice[] zarat=new Dice[6];
	//private int[][] ScoreTable=new int[13][nrLojtareve];
	private JPanel panel;
	
	private JPanel panelReg;
	private JTextField txtEmri;
	private JTextField txtMbiemri;
	private JTextField txtMosha;
	private JTextField txtUser;
	
	private JPanel panelLogin;
	private JTextField txtUsername;
	
	private JLabel lblIndexLojtari;
	private JLabel lblWarning;
	private int indeksLojtari=0;
	private Lojtar[] lojtaret;
	
	private lidhjaDB DB;


	public LojaIntro() {
		
		DB=new lidhjaDB();
		
		setForeground(Color.GREEN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 415);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setForeground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Paneli qe zgjedh nr e lojtareve
		panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(150, 53, 328, 273);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		// Paneli qe permban formen e regjistrimit
		panelReg = new JPanel();
		panelReg.setBackground(new Color(255,102,102));
		panelReg.setBounds(146, 43, 328, 273);
		contentPane.add(panelReg);
		panelReg.setLayout(null);
		panelReg.setVisible(false);
		
		// Paneli qe permban formen e logimit
		panelLogin = new JPanel();
		panelLogin.setBackground(Color.green);
		panelLogin.setBounds(146, 43, 328, 273);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setVisible(false);
		
		
		JLabel lblShkrim = new JLabel("Ju lutem percaktoni numrin e lojtareve :");
		lblShkrim.setBounds(30, 11, 265, 38);
		panel.add(lblShkrim);
		lblShkrim.setForeground(new Color(0, 0, 0));
		lblShkrim.setBackground(new Color(255, 255, 0));
		lblShkrim.setHorizontalAlignment(SwingConstants.CENTER);
		lblShkrim.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
		
		JButton btnNjeLojtar = new JButton("Nje lojtar");
		btnNjeLojtar.setBounds(82, 60, 151, 32);
		btnNjeLojtar.setBackground(new Color(0, 255, 0));
		panel.add(btnNjeLojtar);
		zgjedhLojtaretHandler handler=new zgjedhLojtaretHandler(1);
		btnNjeLojtar.addActionListener(handler);
		
		JButton btnTwoPlayer = new JButton("Dy lojtare");
		btnTwoPlayer.setBounds(82, 116, 151, 32);
		panel.add(btnTwoPlayer);
		btnTwoPlayer.setBackground(new Color(0, 255, 0));
		zgjedhLojtaretHandler handler2=new zgjedhLojtaretHandler(2);
		btnTwoPlayer.addActionListener(handler2);
		
		JButton btnThreePlayer = new JButton("Tre lojtare");
		btnThreePlayer.setBounds(82, 169, 151, 31);
		panel.add(btnThreePlayer);
		btnThreePlayer.setBackground(new Color(0, 255, 0));
		btnThreePlayer.addActionListener(new zgjedhLojtaretHandler(3));
		
		JButton btnFourPlayers = new JButton("Kater lojtare");
		btnFourPlayers.setBounds(82, 211, 151, 31);
		panel.add(btnFourPlayers);
		btnFourPlayers.setBackground(new Color(0, 255, 0));
		btnFourPlayers.addActionListener(new zgjedhLojtaretHandler(4));
		
		
		lblIndexLojtari = new JLabel("Lojtari "+(indeksLojtari+1));
		lblIndexLojtari.setBounds(112, 25, 76, 25);
		panelReg.add(lblIndexLojtari);
		
		
		txtEmri = new JTextField();
		txtEmri.setBounds(99, 61, 122, 20);
		txtEmri.setColumns(10);
		panelReg.add(txtEmri);
		JLabel lblEmri = new JLabel("Emri");
		lblEmri.setBounds(32, 64, 39, 14);
		panelReg.add(lblEmri);
		
		JLabel lblMbiemri = new JLabel("Mbiemri");
		lblMbiemri.setBounds(32, 108, 57, 14);
		panelReg.add(lblMbiemri);
		txtMbiemri = new JTextField();
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(99, 105, 122, 20);
		panelReg.add(txtMbiemri);
		
		JLabel lblUsername2 = new JLabel("Username");
		lblUsername2.setBounds(32, 145, 79, 14);
		panelReg.add(lblUsername2);
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(99, 145, 122, 20);
		panelReg.add(txtUser);
		
		JLabel lblMosha = new JLabel("Mosha");
		lblMosha.setBounds(32, 185, 39, 14);
		panelReg.add(lblMosha);
		txtMosha = new JTextField();
		txtMosha.setColumns(10);
		txtMosha.setBounds(99, 182, 122, 20);
		panelReg.add(txtMosha);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(80, 230, 84, 23);
		panelReg.add(btnSubmit);
		
		JButton btnKthehu = new JButton("Kthehu");
		btnKthehu.setBounds(170, 230, 84, 23);
		panelReg.add(btnKthehu);
		
		lblWarning = new JLabel("");
		lblWarning.setForeground(Color.WHITE);
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarning.setBackground(Color.red);
		lblWarning.setBounds(32, 234, 189, 31);
		panelReg.add(lblWarning);
		
		// Komponentet e panelit te logimit
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(32, 108, 97, 14);
		panelLogin.add(lblUsername);
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(99, 105, 142, 20);
		panelLogin.add(txtUsername);
		panelLogin.add(lblIndexLojtari);
		panelLogin.add(lblWarning);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(60, 171, 84, 23);
		panelLogin.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(149, 171, 84, 23);
		panelLogin.add(btnRegister);
		
		// Kur klikohet register kalojme ne panelin e regjistrimit
		btnRegister.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    panelLogin.setVisible(false);
			    panelReg.setVisible(true);
			}
		});
		
		// Nese klikojme login ne panel e logimit ...
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    
				// kontrollojme nese eshte plotesuar fusha username
				if(txtUsername.getText().equals("") ) {
				    lblWarning.setText("Shkruaj te gjitha te dhenat");
			    }
				else
				{
					String username=txtUsername.getText();
					if(DB.logohu(username)!=null){ // kthen Lojtar nese eshte regjistruar me pare ose null nese nuk eshte i regjistruar
						lojtaret[indeksLojtari]=DB.logohu(username);
						   nextPlayer();
					}
					else {
						lblWarning.setText("Nuk je i regjistruar !");
					    return;
					}
					
					if(indeksLojtari==nrLojtareve) {  
						// Nqs eshte arritur nr i lojtareve te zgjedhur, pra jane regjistruar ose loguar te gjithe lojtaret hapet loja, mbyllet
						// dritarja ku ndodhemi tani
						lojeboard l=new lojeboard(lojtaret);
						dispose();
						l.setVisible(true);												
					}
				}
		   
			}
		});
		
		
		
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    
				if(txtEmri.getText().equals("") || txtEmri.getText().equals("") || txtMosha.getText().equals("") || txtUser.getText().equals("")) {
				    lblWarning.setText("Shkruaj te gjitha te dhenat");
			    }
				else
				{
					try {
						String emri=txtEmri.getText();
						String mbiemri=txtMbiemri.getText();
						String username=txtUser.getText();
						int mosha=Integer.parseInt(txtMosha.getText());
					   Lojtar l=new Lojtar();
					    if(DB.kontrolloUsernameUnik(username)) {
						   lojtaret[indeksLojtari]=new Lojtar(emri,mbiemri,username,mosha,0);
						   DB.shto(emri, mbiemri, username, mosha, 0);
						   System.out.println("Eshte username unik");
						   nextPlayer();
					     }
					    else {
					    	lblWarning.setText("Provo nje username tjeter !");
					    	return;
					    }
					
					}
					catch(Exception e11){
						lblWarning.setText("Gabim te te dhenat");
						return;
					}
					panelReg.setVisible(false);
					panelLogin.setVisible(true);
					if(indeksLojtari==nrLojtareve) {
						// Nqs eshte arritur nr i lojtareve te zgjedhur, pra jane regjistruar ose loguar te gjithe lojtaret hapet loja, mbyllet
						// dritarja ku ndodhemi tani
						lojeboard l=new lojeboard(lojtaret);
						dispose();
						l.setVisible(true);												
					}
				}
		   
			}
		});
		
		btnKthehu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			  panelReg.setVisible(false);
		      panelLogin.setVisible(true);
			}
		});
		
	}
	
	
	private class zgjedhLojtaretHandler implements ActionListener{
        private int nr_l; 
		public zgjedhLojtaretHandler(int nr_l) {
			this.nr_l=nr_l;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			nrLojtareve=nr_l;
		    panel.setVisible(false);
		    panelLogin.setVisible(true);
		    lojtaret=new Lojtar[nrLojtareve];	// Krijohet nje vektor i objekteve te tipit Lojtar me madhesi sa nr i lojtareve te zgjedhur	
		}
	}
	
	private void nextPlayer() {
		indeksLojtari++;
		lblIndexLojtari.setText("Lojtari "+(indeksLojtari+1));
		// Hiqen vlerat e e vendosura nga plotesimet e meparme
		txtEmri.setText("");
		txtMbiemri.setText("");
		txtMosha.setText("");
		txtUser.setText("");
		lblWarning.setText("");
		txtUsername.setText("");
	}
}
