package nyout.interfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class DerrotaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public DerrotaFrame(String nmJogador) {
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Você perdeu " + nmJogador + "!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 375);
		setLocation((tela.width-this.getSize().width)/2,   
                (tela.height-this.getSize().height)/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblParabens = new JLabel("Que pena " + nmJogador + "!");
		lblParabens.setFont(new Font("Cambria", Font.BOLD, 25));
		lblParabens.setBounds(15, 15, 300, 30);
		lblParabens.setBackground(getBackground());
		contentPane.add(lblParabens);
		
		JLabel lblchegou = new JLabel("<html>Você não saiu do Zoológico.");
		lblchegou.setFont(new Font("Calibri", Font.BOLD, 20));
		lblchegou.setBounds(15, 50, 300, 50);
		contentPane.add(lblchegou);
		
		JLabel lblVitoria = new JLabel("");
		lblVitoria.setIcon(Imagens.derrota);
		lblVitoria.setBounds(0, 0, 500, 375);
		contentPane.add(lblVitoria);

	}

}