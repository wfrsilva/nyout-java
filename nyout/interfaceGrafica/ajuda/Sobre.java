package nyout.interfaceGrafica.ajuda;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import nyout.interfaceGrafica.Imagens;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class Sobre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	
	public Sobre() {
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/imagens/sobre_25.png")));
		
		setTitle("Sobre Ny-Out");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 275);
		setLocation((tela.width-this.getSize().width)/2,   
                (tela.height-this.getSize().height)/2);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
		
		JLabel ufscLb = new JLabel("");
		ufscLb.setIcon(Imagens.ufsc);
		ufscLb.setBounds(375, 20, 90, 200);
		painel.add(ufscLb);
		
		JLabel ine5608Lb = new JLabel("INE5608 - Análise e Projeto de Sistemas");
		ine5608Lb.setFont(new Font("Calibri", Font.BOLD, 15));
		ine5608Lb.setVerticalAlignment(SwingConstants.CENTER);
		ine5608Lb.setHorizontalAlignment(SwingConstants.CENTER);
		ine5608Lb.setBounds(50, 15, 400, 20);
		painel.add(ine5608Lb);
		
		JLabel nyOutLb = new JLabel("Ny-Out");
		nyOutLb.setFont(new Font("Arial", Font.BOLD, 30));
		nyOutLb.setVerticalAlignment(SwingConstants.CENTER);
		nyOutLb.setHorizontalAlignment(SwingConstants.CENTER);
		nyOutLb.setBounds(100, 50, 300, 35);
		painel.add(nyOutLb);
		
		JLabel nyBackLb = new JLabel("Back to the Forest");
		nyBackLb.setFont(new Font("Calibri", Font.ITALIC, 20));
		nyBackLb.setVerticalAlignment(SwingConstants.CENTER);
		nyBackLb.setHorizontalAlignment(SwingConstants.CENTER);
		nyBackLb.setBounds(100, 85, 300, 25);
		painel.add(nyBackLb);
		
		JLabel autor1Lb = new JLabel("Autores: Rafael Barbaresco");
		autor1Lb.setFont(new Font("Calibri", Font.ITALIC, 14));
		autor1Lb.setBounds(140, 125, 400, 14);
		painel.add(autor1Lb);
		
		JLabel autor2Lb = new JLabel("Rafael Fernandes da Silva");
		autor2Lb.setFont(new Font("Calibri", Font.ITALIC, 14));
		autor2Lb.setBounds(140, 150, 400, 14);
		painel.add(autor2Lb);
		
		JLabel autor3Lb = new JLabel("Wendel Fabiano Ribeiro da Silva");
		autor3Lb.setFont(new Font("Calibri", Font.ITALIC, 14));
		autor3Lb.setBounds(140, 175, 400, 14);
		painel.add(autor3Lb);
	
		JLabel turmaLb = new JLabel("Turma 2017/1");
		turmaLb.setFont(new Font("Calibri", Font.ITALIC, 14));
		turmaLb.setBounds(175, 215, 150, 15);
		painel.add(turmaLb);
		
		JLabel leao = new JLabel();
		leao.setIcon(Imagens.casa1leao);
		leao.setBounds(5, 5, 100, 100);
		painel.add(leao);
						
		JLabel zbra = new JLabel();
		zbra.setIcon(Imagens.casa1zbra);
		zbra.setBounds(35, 50, 100, 100);
		painel.add(zbra);
		
		JLabel hptm = new JLabel();
		hptm.setIcon(Imagens.casa1hptm);
		hptm.setBounds(0, 80, 100, 100);
		painel.add(hptm);
		
		JLabel grfa = new JLabel();
		grfa.setIcon(Imagens.casa1grfa);
		grfa.setBounds(25, 110, 100, 100);
		painel.add(grfa);
		
		JLabel sobreBack = new JLabel();
		sobreBack.setIcon(Imagens.sobreBack);
		sobreBack.setBounds(0, 0, 500, 250);
		painel.add(sobreBack);
		
	}

}