package nyout.interfaceGrafica.ajuda;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import nyout.interfaceGrafica.Imagens;

import javax.swing.JLabel;
import javax.swing.JMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MapaTabuleiro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel mapaBackA;
	private JLabel mapaBackN;
	Border raisedbevel, loweredbevel;
	
	public MapaTabuleiro() {
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MapaTabuleiro.class.getResource("/imagens/peoes_25.png")));
		
		setTitle("Mapa do Tabuleiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(0, 0, 900, 642);
		setBounds(0, 0, 420, 330);
		setLocation((tela.width-this.getSize().width)/2,   
                (tela.height-this.getSize().height)/2);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
	
		
		final JLabel cmNormalLb = new JLabel("<html><center>Caminho<br> Normal</center></html>");
		cmNormalLb.setFont(new Font("Arial", Font.BOLD, 14));
		cmNormalLb.setVerticalAlignment(SwingConstants.CENTER);
		cmNormalLb.setHorizontalAlignment(SwingConstants.CENTER);
		cmNormalLb.setBounds(95, 55, 80, 50);
				
		raisedbevel= BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		cmNormalLb.setBorder(raisedbevel);
		
		painel.add(cmNormalLb);
		
		cmNormalLb.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                trocarImg();
            }//mouseClicked
            
        	private void trocarImg() {
				if(mapaBackA.isVisible()){
					mapaBackA.setVisible(false);
					mapaBackN.setVisible(true);
					cmNormalLb.setText("<html><center>Exibir<br>Alas?</center></html>");
					
				}else{
					mapaBackA.setVisible(true);
					mapaBackN.setVisible(false);
					cmNormalLb.setText("<html><center>Exibir<br>Normal?</center></html>");
				}
				
				cmNormalLb.setBorder(loweredbevel);
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
        		cmNormalLb.setForeground(Color.RED);
        		if(mapaBackA.isVisible()){
					cmNormalLb.setText("<html><center>Exibir<br>Normal?</center></html>");
				}else{
					cmNormalLb.setText("<html><center>Exibir<br>Alas?</center></html>");
				}
                
        	}
        	
           	public void mouseExited(java.awt.event.MouseEvent e) {
           		cmNormalLb.setForeground(Color.BLACK);
           		cmNormalLb.setBorder(raisedbevel);
           		if(mapaBackA.isVisible()){
					cmNormalLb.setText("<html><center>Caminho<br>Alas</center></html>");
				}else{
					cmNormalLb.setText("<html><center>Caminho<br>Normal</center></html>");
				}
                
                
        	}
            
        });
	
		mapaBackA = new JLabel();
		/*mapaBackA.setIcon(Imagens.mapaTabuleiro);
		mapaBackA.setBounds(0, 0, 890, 632);*/
		mapaBackA.setIcon(Imagens.mapaTabuleiroAlas400);
		mapaBackA.setBounds(0, 0, 400, 284);
		mapaBackA.setVisible(false);
		painel.add(mapaBackA);
		
		mapaBackN = new JLabel();
		/*mapaBack.setIcon(Imagens.mapaTabuleiro);
		mapaBack.setBounds(0, 0, 890, 632);*/
		mapaBackN.setIcon(Imagens.mapaTabuleiroNormal400);
		mapaBackN.setBounds(0, 0, 400, 284);
		painel.add(mapaBackN);
		
	}

}