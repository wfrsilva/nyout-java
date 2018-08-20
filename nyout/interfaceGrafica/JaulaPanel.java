package nyout.interfaceGrafica;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import nyout.modelo.Jaula;
import nyout.modelo.Jogador;

public class JaulaPanel extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected Jogador jogador;
    protected JanelaPrincipal janelaPrincipal;
    protected JLabel labelNome = null;
    protected JLabel[] labelPecas = null;

    public JaulaPanel(final Jogador jogador, JanelaPrincipal janela) {
        super();

        this.janelaPrincipal = janela;
        this.jogador = jogador;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFont(new Font("Dialog", Font.BOLD, 13));

        labelNome = new JLabel();
        labelNome.setText(jogador.getNome());
        labelNome.setForeground(new Color(255, 255, 0));
        labelNome.setOpaque(true);
        labelNome.setBackground(Color.DARK_GRAY);
        labelNome.setVerticalAlignment(SwingConstants.CENTER);
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        labelNome.setVerticalTextPosition(SwingConstants.CENTER);
        labelNome.setHorizontalTextPosition(SwingConstants.CENTER);
        labelNome.setIcon(Imagens.casaInativa);
        
        labelNome.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                            if (!janelaPrincipal.getAtorjogador().ehEmAndamento()) {
                            	//TODO teste do avatar dialogo - verificar possibilidade de desatir esse codigo
                                jogador.setAvatar( AvatarDialog.informaAvatarDialogo("Qual avatar do " + jogador.getNome()) );
                            }
                        }
                    } 
            );
        
        add(labelNome);

        labelPecas = new JLabel[Jaula.Limite];

        for (int i = 0; i < labelPecas.length; i++) {
            labelPecas[i] = new JLabel();
            labelPecas[i].setOpaque(true);
            labelPecas[i].setBackground(Color.DARK_GRAY);
            labelPecas[i].setIcon(Imagens.jaulaVazia);
            add(labelPecas[i]);

            labelPecas[i].addMouseListener(
                    new java.awt.event.MouseAdapter() {
                    @Override
                    //TODO Voltar peca para jaula, deve ser implementada aqui, primeira opçao
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                    		janelaPrincipal.getAtorjogador().clickCasa(true, 0, 0);
                            //janelaPrincipal.getAtorjogador().liberaAnimal(jogador);
                        }
                    } 
            ); 
        }
        
    }

    public void atualizar() {

        for (int i = 0; i < jogador.getJaula().getQuantidade(); i++) {
        	//TODO verificar avatar Classe aqui
        	
            int nroAvt = jogador.getAvatar();
            labelPecas[i].setIcon(Imagens.getAvatarJaula(nroAvt));

        }

        for (int i = jogador.getJaula().getQuantidade(); i < labelPecas.length; i++) {
            labelPecas[i].setIcon(Imagens.jaulaVazia);
        }
        
        //*
         if(jogador.getRestantes() <4){
        	atualizarIconeFloresta();
        }
         

        
        if (jogador.ehVencedor()) {
            labelNome.setText("<html>VENCEDOR! <br><br>"+jogador.getNome());
        } else {
            labelNome.setText(jogador.getNome());
        }
    }//atualizar

    public void atualizarIconeFloresta(){
    	for (int i = 3; i > jogador.getRestantes()-1; i--){
    		labelPecas[i].setIcon(Imagens.jaulaFloresta);
    	}
    } //atualizarIconeFloresta



}