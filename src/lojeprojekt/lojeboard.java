package lojeprojekt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class lojeboard extends JFrame {

	
	private JPanel contentPane;
	
	private Dice zarat[]=new Dice[5];
	private int checked[]= new int [] {-1,-1,-1,-1,-1};
	private JTable table_1;
	private int nrlojtareve;
	private Lojtar[] lojtaret;
	private int hedhjetZarit=0;
	private JButton btnRoll;
	
	//private int turni=0;
	private int radha=0;
	private JLabel[] dicelbl=new JLabel[5];
	private ScoreCell[][] ScoreTable; // Tabela e pikeve per lojen
	private String[] emrat; // Mban emrat per cdo lojtar
	private JLabel lblturn; // Tregon radhen e lojtarit emer mbiemer
	private int niveli=0;  // Variabli qe percakton nivelin e lojes, nga 0 deri ne 12. 13 nivele gjithsej = 13 zgjedhje kategorish
	private JLabel[] Kateg_lbl=new JLabel[13];
	

	public lojeboard(Lojtar[] l) {
		nrlojtareve=l.length;
		lojtaret=new Lojtar[nrlojtareve];
		lojtaret=l;
		prepareComp();
		zarat[0]=new Dice();
		zarat[1]=new Dice();
		zarat[2]=new Dice();
		zarat[3]=new Dice();
		zarat[4]=new Dice();
	}
	
	public void prepareComp(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 5, 961, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(24, 11,824, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		

		dicelbl[0] = new JLabel("");
		dicelbl[0].setIcon(new ImageIcon("src\\images\\Alea_6.png"));
		dicelbl[0].setBounds(72, 11, 84, 78);
		panel.add(dicelbl[0]);
		dicelbl[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checked[0]=1;
				dicelbl[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			}
		});
		
		dicelbl[1] = new JLabel("");
		dicelbl[1].setIcon(new ImageIcon("src\\images\\Alea_2.png"));
		dicelbl[1].setBounds(231, 11, 84, 84);
		panel.add(dicelbl[1]);
		dicelbl[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checked[1]=1;
				dicelbl[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			}
		});
		
		dicelbl[2] = new JLabel("");
		dicelbl[2].setIcon(new ImageIcon("src\\images\\Alea_3.png"));
		dicelbl[2].setBounds(376, 11, 84, 84);
		panel.add(dicelbl[2]);
		dicelbl[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checked[2]=1;
				dicelbl[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			}
		});
		
		dicelbl[3] = new JLabel("");
		dicelbl[3].setIcon(new ImageIcon("src\\images\\Alea_4.png"));
		dicelbl[3].setBounds(521, 11, 84, 84);
		panel.add(dicelbl[3]);
		dicelbl[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checked[3]=1;
				dicelbl[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			}
		});
		
		dicelbl[4] = new JLabel("");
		dicelbl[4].setIcon(new ImageIcon("src\\images\\Alea_1.png"));
		dicelbl[4].setBounds(663, 11, 84, 84);
		panel.add(dicelbl[4]);
		dicelbl[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checked[4]=1;
				dicelbl[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
			}
		});
		
		
		 lblturn = new JLabel("Radha e lojtarit "+lojtaret[radha]);
		 lblturn.setBounds(554, 202, 175, 14);
		 contentPane.add(lblturn);
		
		
		btnRoll = new JButton("Rrotullo");
		btnRoll.setBounds(348, 108, 89, 23);
		btnRoll.setVisible(true);
		panel.add(btnRoll);
		btnRoll.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				clearBorders(dicelbl); // heqim kornizat e atyre zarave qe kemi selktuat				
				
				hedhjetZarit++; // kemi kryer nje hedhje
				
				if(radha==nrlojtareve) {
					radha=0;
				System.out.println("U be radha zero kur shtypet butoni");
				}
				
							    
				boolean selected=false; // selected do te behet true nqs te pakten njeri nga zarat eshte selektuar
				
				for(int i=0;i<5;i++) {
					if(checked[i]==1) {  // checked vektori qe tregon cilet nga zarat eshte selektuat. Nqs checked[1] eshte 1 atehere zari i dyte eshte zgjedhur
						zarat[i].roll();
						dicelbl[i].setIcon(new ImageIcon("src\\images\\Alea_"+zarat[i].getFace()+".png"));
						selected=true;
					}
				}
				
				if(selected) {// Nqs te pakten njeri nga zarat eshte selektuar ...
					 
					// De-selektohen ne vektorin checked
					 for(int i=0;i<5;i++) {
						checked[i]=-1;
					 }
					 
					 updateTable(radha,zarat);
					 ((AbstractTableModel) table_1.getModel()).fireTableDataChanged();
					    
					    if(hedhjetZarit>=3) {
		                    JOptionPane.showMessageDialog(null, "Tashme i keni realizuar hedhjet tuaja ! \nSelekto nje nga kategorite e meposhtme :");
		                    System.out.println("Tashme i keni realizuar hedhjet tuaj");
		                    return;
						}
					    
					    int n=JOptionPane.showConfirmDialog(null,"Deshiron ti hedhesh serish zarat ?","Pyetje",JOptionPane.YES_NO_OPTION);
					    if(JOptionPane.YES_OPTION == n) {     
					    	// Program will keep going
					    }
					    else if (JOptionPane.NO_OPTION == n) { // Plotesojme automatikisht nr total te hedhjeve
					        hedhjetZarit=3;
	                        JOptionPane.showMessageDialog(null, "Selekto nje nga kategorite e meposhtme :");
					    }
					return;
				}
					
				// Nqs shtypim butonin rrotullo pa selektuar asnje nga zarat , atehere do te rrotullohen te gjithe
				  for(int i=0;i<5;i++) {
					zarat[i].roll();
					dicelbl[i].setIcon(new ImageIcon("src\\images\\Alea_"+zarat[i].getFace()+".png"));					
				  }
				  updateTable(radha,zarat);
				  ((AbstractTableModel) table_1.getModel()).fireTableDataChanged();
				  
				  if(hedhjetZarit>=3) {
	                    JOptionPane.showMessageDialog(null, "Tashme i keni realizuar hedhjet tuaja ! \nSelekto nje nga kategorite e meposhtme :");
	                    System.out.println("Tashme i keni realizuar hedhjet tuaj");
	                    return;
					}
				  
				  int n=JOptionPane.showConfirmDialog(null,"Deshiron ti hedhesh serish zarat ?","Pyetje",JOptionPane.YES_NO_OPTION);	 
				     if(JOptionPane.YES_OPTION == n) {
				        // Program will keep going
				    }
				    else if (JOptionPane.NO_OPTION == n) {
				    	hedhjetZarit=3;
                        JOptionPane.showMessageDialog(null, "Selekto nje nga kategorite e meposhtme :");                    
				    }
			
			  }
		});

	
	
		
		ScoreTable=new ScoreCell[17][nrlojtareve];
		for(int i=0;i<17;i++) {
			for(int j=0;j<nrlojtareve;j++) {
				ScoreTable[i][j]=new ScoreCell();
			}
		}
		emrat=new String[nrlojtareve];
		for(int i=0;i<nrlojtareve;i++) {
			emrat[i]="Lojtar"+(i+1);
		}
	
		
		table_1=new JTable() ;
	
		table_1.setModel(new DefaultTableModel(ScoreTable,emrat) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
          });
		
		table_1.setRowSelectionAllowed(false);
		table_1.setBounds(259, 180, 267, 449);
		table_1.setRowHeight(26);
		
		table_1.setBorder(BorderFactory.createLineBorder(Color.blue));
		contentPane.add(table_1);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int z=0;z<nrlojtareve;z++)
		table_1.getColumnModel().getColumn(z).setCellRenderer( centerRenderer );
		
		
		
		Kateg_lbl[0] = new JLabel("Njesha");
		Kateg_lbl[0].setBounds(145, 188, 46, 14);
		contentPane.add(Kateg_lbl[0]);
		Kateg_lbl[0].addMouseListener(new zgjedhKategoriHandler(0));		
		
		Kateg_lbl[1] = new JLabel("Dysha");
		Kateg_lbl[1].setBounds(145, 210, 46, 14);
		contentPane.add(Kateg_lbl[1]);
		Kateg_lbl[1].addMouseListener(new zgjedhKategoriHandler(1));
	
		Kateg_lbl[2] = new JLabel("Tresha\r\n");
		Kateg_lbl[2].setBounds(145, 235, 46, 14);
		contentPane.add(Kateg_lbl[2]);
		Kateg_lbl[2].addMouseListener(new zgjedhKategoriHandler(2));
		
		Kateg_lbl[3]= new JLabel("Katra");
		Kateg_lbl[3].setBounds(145, 260, 46, 14);
		contentPane.add(Kateg_lbl[3]);
		Kateg_lbl[3].addMouseListener(new zgjedhKategoriHandler(3));
		
		Kateg_lbl[4] = new JLabel("Pesa");
		Kateg_lbl[4].setBounds(145, 290, 46, 14);
		contentPane.add(Kateg_lbl[4]);
		Kateg_lbl[4].addMouseListener(new zgjedhKategoriHandler(4));
		
		Kateg_lbl[5] = new JLabel("Gjashta");
		Kateg_lbl[5].setBounds(145, 315, 46, 14);
		contentPane.add(Kateg_lbl[5]);
		Kateg_lbl[5].addMouseListener(new zgjedhKategoriHandler(5));

		
		JLabel lblPiketESiperme = new JLabel("Piket e siperme");
		lblPiketESiperme.setBounds(145, 340, 99, 14);
		contentPane.add(lblPiketESiperme);
		lblPiketESiperme.setFont(new Font("Calibri",Font.ITALIC,14) );
		lblPiketESiperme.setForeground(Color.blue);
		
		JLabel lblBonus = new JLabel("Bonus");
		lblBonus.setBounds(145, 366, 79, 14);
		lblBonus.setFont(new Font("Calibri",Font.ITALIC,14) );
		lblBonus.setForeground(Color.blue);
		contentPane.add(lblBonus);
		
		Kateg_lbl[6] = new JLabel("Tre me nje vlere");
		Kateg_lbl[6].setBounds(145, 397, 95, 14);
		contentPane.add(Kateg_lbl[6]);
		Kateg_lbl[6].addMouseListener(new zgjedhKategoriHandler(6));
		
		Kateg_lbl[7] = new JLabel("Kater me nje vlere");
		Kateg_lbl[7] .setBounds(145, 424, 105, 14);
		contentPane.add(Kateg_lbl[7] );
		Kateg_lbl[7].addMouseListener(new zgjedhKategoriHandler(7));
		
		Kateg_lbl[8]  = new JLabel("Tre dhe dy");
		Kateg_lbl[8].setBounds(145, 449, 95, 14);
		contentPane.add(Kateg_lbl[8]);
		Kateg_lbl[8].addMouseListener(new zgjedhKategoriHandler(8));
		
		Kateg_lbl[9] = new JLabel("Kater njepasnje");
		Kateg_lbl[9].setBounds(145, 477, 95, 14);
		contentPane.add(Kateg_lbl[9]);
		Kateg_lbl[9].addMouseListener(new zgjedhKategoriHandler(9));
		
		Kateg_lbl[10] = new JLabel("Pese njepasnje");
		Kateg_lbl[10].setBounds(145, 502, 95, 14);
		contentPane.add(Kateg_lbl[10]);
		Kateg_lbl[10].addMouseListener(new zgjedhKategoriHandler(10));
		
		Kateg_lbl[11] = new JLabel("Te gjitha njesoj");
		Kateg_lbl[11].setBounds(145, 524, 183, 27);
		contentPane.add(Kateg_lbl[11]);
		Kateg_lbl[11].addMouseListener(new zgjedhKategoriHandler(11));
		
		Kateg_lbl[12] = new JLabel("Cdo rast");
		Kateg_lbl[12].setBounds(145, 552, 95, 14);
		contentPane.add(Kateg_lbl[12]);
		Kateg_lbl[12].addMouseListener(new zgjedhKategoriHandler(12));
		
		JLabel lblPiketEPoshtme = new JLabel("Piket e poshtme");
		lblPiketEPoshtme.setBounds(145, 577, 95, 14);
		contentPane.add(lblPiketEPoshtme);
		lblPiketEPoshtme.setFont(new Font("Calibri",Font.ITALIC,14));
		lblPiketEPoshtme.setForeground(Color.blue);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(145, 602, 95, 14);
		contentPane.add(lblTotal);
		lblTotal.setFont(new Font("Calibri",Font.ITALIC,14));
		lblTotal.setForeground(Color.blue);
		
						
	}
	
	private class zgjedhKategoriHandler extends MouseAdapter{
        private int index_kategori;
        private int index_lojtar;
		public zgjedhKategoriHandler(int nr) {
			this.index_kategori=nr;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(radha==nrlojtareve)
				radha=0;
			
			if(this.index_kategori>=6) {
				if(ScoreTable[index_kategori+2][radha].is_Selected()) {
					JOptionPane.showMessageDialog(null, "Kete kategori e ke zgjedhur tashme ! \nProvo nje kategori tjeter!");
					return ;
				}
			}
			else {
		     	if(ScoreTable[index_kategori][radha].is_Selected()) {
				   JOptionPane.showMessageDialog(null, "Kete kategori e ke zgjedhur tashme ! \nProvo nje kategori tjeter !");
				  return ;
			    }
			}
			
			if(hedhjetZarit>0) {
				if(index_kategori<6) {
			        ScoreTable[index_kategori][radha].set_Selected();
			        selectKategori(Kateg_lbl[index_kategori]);
			        ResetoTabelen(radha);
			        nextTurn();
			    }
				else if(index_kategori>=6) {
			
					ScoreTable[index_kategori+2][radha].set_Selected();
			        selectKategori(Kateg_lbl[index_kategori]);
			        ResetoTabelen(radha);
			        nextTurn();
				}
			}
			else 
				JOptionPane.showMessageDialog(null, "Hidh fillimisht zarat !");
		}
	
	}
	
	// Ploteson kolonen e lojtarit te percaktuar nga indeksi radhat. Per konfigurimin perfundimtar te zarave, plotesohet te gjitha kategorite
	//(rreshtat  e tabeles ) me vleren qe do te mbanin nese do te zgjidheshim. Plotesohen vetem ato rreshta(kategori) qe nuk jane zgjedhur me pare
	public void updateTable(int radha,Dice[] zarat) {
			
		Kategori k=new Kategori();
		
		for(int i=0;i<=5;i++) {
			if(ScoreTable[i][radha].is_Selected()) {
				selectKategori(Kateg_lbl[i]);
			}
			else
				ScoreTable[i][radha].setVlera(k.llogaritPiket(i+1, zarat[0].getFace(), zarat[1].getFace(), zarat[2].getFace(), zarat[3].getFace(), zarat[4].getFace()));
		}
		
		for(int i=8;i<=14;i++) {
			if(ScoreTable[i][radha].is_Selected()) {
				selectKategori(Kateg_lbl[i-2]);
			}
			else {
					ScoreTable[i][radha].setVlera(k.llogaritPiket(i-1, zarat[0].getFace(), zarat[1].getFace(), zarat[2].getFace(), zarat[3].getFace(), zarat[4].getFace()));
			}
		}
			
	}
		// Inkrementohet radha, nqs radha barazaohet me nr e lojtareve atehere resetohet ne zero
	public void nextTurn() {
		radha++;
		if(radha<nrlojtareve)
		lblturn.setText("Radha e lojtarit "+lojtaret[radha]);
		else {
			lblturn.setText("Radha e lojtarit "+lojtaret[0]);
			niveli++;
			isFinished();
		}
		
		for(int i=0;i<Kateg_lbl.length;i++) {	
			Kateg_lbl[i].setBorder(BorderFactory.createLineBorder(Color.white));		
		}	
	}
    // Updatohen PiketSiperme (rresht ne tabele)
	public void updatePiketSiper(int radha) {
		int piket_siperme=0;
		for(int i=0;i<6;i++)
		{
			piket_siperme+=ScoreTable[i][radha].getVlera();
		}		
		ScoreTable[6][radha].setVlera(piket_siperme);
		updateBonus(radha);
	}
	// Updatohen PiketPoshtme (rresht ne tabele)
	public void updatePiketPoshte(int radha) {
		int piket_p=0;
		for(int i=8;i<15;i++)
		{
			piket_p+=ScoreTable[i][radha].getVlera();
		}		
		ScoreTable[15][radha].setVlera(piket_p);
	}
	// Updatohen Total (rresht ne tabele)
	public void updateTotal(int radha) {	
		ScoreTable[16][radha].setVlera(ScoreTable[15][radha].getVlera()+ScoreTable[6][radha].getVlera()+ScoreTable[7][radha].getVlera());
	}
	// Updatohen Bonus (rresht ne tabele)
	public void updateBonus(int radha) {	
		if(ScoreTable[6][radha].getVlera()>63)
			ScoreTable[7][radha].setVlera(35);
	}
	// pasi lojtari sipas variablit radha ben zgjedhjen e tij per kategorine, kategorite e tjera, pra rreshtat e tjere te tabeles, qe plotesohen
	// gjate UpdateTable() do te behen serish zero
	public void ResetoTabelen(int radha) {
		hedhjetZarit=0;
		for(int i=0;i<6;i++)
		{
			if(!ScoreTable[i][radha].is_Selected()) 
				ScoreTable[i][radha].setVlera(0);
		}
		for(int i=8;i<15;i++)
		{
			if(!ScoreTable[i][radha].is_Selected()) 
				ScoreTable[i][radha].setVlera(0);
		}
		updatePiketSiper(radha);
		updatePiketPoshte(radha);
		updateTotal(radha);
		((AbstractTableModel) table_1.getModel()).fireTableDataChanged();
	}
	// Fshin kornizen e imazheve te zarave
	private void clearBorders(JLabel[] dicelbl) {
		for(int i=0;i<5;i++) {
			dicelbl[i].setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
	}
	// Zgjidhet kategoria dhe behet korniza e kuqe
	private void selectKategori(JLabel l) {
		l.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
     // Kontrollon nese loja ka mbaruar. Nese arrihet niveli 13 atehere shpallet rezultati
	// Ky funksion therritet ne cdo nextTurn()
	public void isFinished() {
		if(niveli==13) {
			int max=ScoreTable[16][0].getVlera();
			for(int i=0;i<nrlojtareve;i++) {
				lojtaret[i].updatePiket(ScoreTable[16][i].getVlera());
			}
			
			int k=0;
			for(int i=1;i<nrlojtareve;i++) {
				if(ScoreTable[16][i].getVlera()>max) {
					max=ScoreTable[16][i].getVlera();
				      k=i;
				    }
			}
			
			if(nrlojtareve>1)
			JOptionPane.showMessageDialog(null, "Loja mbaroi !\n Fituesi eshte "+lojtaret[k].getEmri()+""+lojtaret[k].getMbiemri()
					+" me nje total pikesh : "+ScoreTable[16][k].getVlera());
			else
				JOptionPane.showMessageDialog(null, "Loja mbaroi !");
			
			 int n=JOptionPane.showConfirmDialog(null,"Deshiron te luash perseri ?","Pyetje",JOptionPane.YES_NO_OPTION);
			    if(JOptionPane.YES_OPTION == n) {
			    	dispose();
			    	LojaIntro l=new LojaIntro();
			    	l.setVisible(true);
			    }
			    else if (JOptionPane.NO_OPTION == n) { 
			    	dispose();
			    }
			btnRoll.setVisible(false);
			
		}	
	}

}
