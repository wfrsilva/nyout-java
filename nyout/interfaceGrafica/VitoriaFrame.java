package nyout.interfaceGrafica;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class VitoriaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	protected int reiniciarJogo = -1;
	
	public VitoriaFrame(String nmJogador) {
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	
		
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Você venceu " + nmJogador + "!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 375);
		setLocation((tela.width-this.getSize().width)/2,   
                (tela.height-this.getSize().height)/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblParabens = new JLabel("Parabéns " + nmJogador + "!");
		lblParabens.setFont(new Font("Cambria", Font.BOLD, 30));
		lblParabens.setBounds(50, 25, 300, 35);
		lblParabens.setBackground(getBackground());
		contentPane.add(lblParabens);
		
		JLabel lblchegou = new JLabel("Você chegou até a floresta.");
		lblchegou.setFont(new Font("Calibri", Font.BOLD, 20));
		lblchegou.setBounds(50, 75, 300, 25);
		contentPane.add(lblchegou);
		
		JButton botaoIniciar = new JButton();
		botaoIniciar.setText("Iniciar Nova Partida");
		botaoIniciar.setBounds(50, 300, 150, 25);
		botaoIniciar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				acaoBotaoIniciar();
			}
		});
//		contentPane.add(botaoIniciar);

		JButton botaoDesconectar = new JButton();
		botaoDesconectar.setText("Desconectar");
		botaoDesconectar.setBounds(275, 300, 150, 25);
		botaoDesconectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				acaoBotaoDesconectar();
			}
		});
//		contentPane.add(botaoDesconectar);
		
		JLabel lblVitoria = new JLabel("");
		lblVitoria.setIcon(Imagens.vitoria);
		lblVitoria.setBounds(0, 0, 500, 375);
		contentPane.add(lblVitoria);

	}
	
	public int informaReinicioSelecionado(){
		return reiniciarJogo;
	}//informaAvatar
	
	public void acaoBotaoIniciar() {
		reiniciarJogo = 1;
        setVisible(false);
        dispose();
	}//acaoBotaoIniciar
	
	public void acaoBotaoDesconectar() {
		reiniciarJogo = 2;
        setVisible(false);
        dispose();
	}//acaoBotaoIniciar
	
	public static int informaInicioDialogo(String nmJgdr){		
		VitoriaFrame gui = new VitoriaFrame(nmJgdr);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		return (gui.informaReinicioSelecionado());
		
	}//informaAvatarDialogo

}