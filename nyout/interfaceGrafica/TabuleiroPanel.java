package nyout.interfaceGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import nyout.estrategia.Regras;
import nyout.modelo.Casa;
import nyout.modelo.Jogador;
import nyout.modelo.Tabuleiro;

public class TabuleiroPanel extends JPanel {

    JanelaPrincipal janelaPrincipal;
    JLabel casas[][];
    JLabel labelStatusRede;
    Jogador atual;
    Tabuleiro tabuleiro;
    
    public TabuleiroPanel(Tabuleiro tab, JanelaPrincipal janela) {
        janelaPrincipal = janela;
        tabuleiro =tab;
        setBorder(BorderFactory.createLineBorder(Color.green));

        int linhas = 7;
        int colunas = 7;

        setLayout(new GridLayout(linhas, colunas));

        casas = new JLabel[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                casas[i][j] = criarCasa(i, j);
                add(casas[i][j]);
            }
        }

        labelStatusRede = casas[1][1];
        labelStatusRede.setBackground(Color.BLUE);
        labelStatusRede.setFont(new Font("Tahoma", Font.ITALIC, 16));
        labelStatusRede.setVerticalAlignment(SwingConstants.CENTER);
        labelStatusRede.setHorizontalAlignment(SwingConstants.CENTER);
        labelStatusRede.setVerticalTextPosition(JLabel.BOTTOM);
        labelStatusRede.setHorizontalTextPosition(JLabel.CENTER);
    }

   
    private JLabel criarCasa(int x, int y) {
        JLabel casaLb = new JLabel();
        casaLb.setIcon(definirIconeCasa(x, y));
        casaLb.setBackground(new Color(175, 175, 175));
        casaLb.setVerticalAlignment(SwingConstants.CENTER);
        casaLb.setHorizontalAlignment(SwingConstants.CENTER);
        casaLb.setVerticalTextPosition(JLabel.CENTER);
        casaLb.setHorizontalTextPosition(JLabel.CENTER);

        final int l = x;
        final int c = y;

        casaLb.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        clickMapa(l, c);
                    }
                }
        );

        return casaLb;

    }

    public void atualizar() {
        // casas
        
        for (int l = 0; l < 7; l++) {
            for (int c = 0; c < 7; c++) {
                Casa casa = tabuleiro.getCasa(l, c);
                if (casa.ocupada()) {
                    casas[l][c].setIcon(Imagens.getAvatarCasa(casa.getJogador().getAvatar()));
                    casas[l][c].setText("<html><font color=yellow size=8> "+String.valueOf(casa.getQuantidadePecas()));
                    casas[l][c].setBorder(null);
                } else {
                    casas[l][c].setIcon(definirIconeCasa(l,c));
                    casas[l][c].setText("<html><center>" + l + " , " + c + "<br>" + (l+1) + " " +(c+1));// TODO remover texto, apenas teste
                }

                if (casa == tabuleiro.getCasaSelecionada()) {
                    casas[l][c].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    casas[l][c].setOpaque(true);
                    
                    
                } 
                else if(casa == tabuleiro.getCasaFrente()) {
                 	casas[l][c].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                	if (!casa.ocupada()) {
                		casas[l][c].setIcon(Imagens.casaDestaque);
                		if( (l == 3 && c == 0) || (l == 3 && c == 3) || (l == 3 && c == 6) || (l == 0 && c == 3) ){
                			casas[l][c].setIcon(Imagens.alaDestaque);
                		}else if (l ==6 && c ==3){
                			casas[l][c].setIcon(Imagens.saidaDestaque);
                		}
                	}
                } 
                else if(casa == tabuleiro.getCasaEsquerda()) {
                	casas[l][c].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                	if (!casa.ocupada()) {
                		casas[l][c].setIcon(Imagens.casaDestaque);
                		if( (l == 3 && c == 0) || (l == 3 && c == 3) || (l == 3 && c == 6) || (l == 0 && c == 3) ){
                			casas[l][c].setIcon(Imagens.alaDestaque);
                		}else if (l ==6 && c ==3){
                			casas[l][c].setIcon(Imagens.saidaDestaque);
                		}
                	}
                }            
                
                
                
                else {
                    casas[l][c].setBorder(null);
                    casas[l][c].setOpaque(false);
                }

            } // for coluna
        }
        
        // jogador atual
        Jogador atual = janelaPrincipal.getAtorjogador().getJogadorAtual();
        if (atual != null) {
            casas[1][5].setIcon(Imagens.getAvatarCasa(atual.getAvatar()));
            casas[2][5].setText("<html><center><font color=blue size=4>Da Vez : <br>" + atual.getNome());
        } else {
            casas[1][5].setIcon(Imagens.casaInativa);
            casas[2][5].setText("");
        }
        
        // moedas
        atualizarMoedas();        
        
        // status rede
//        if (janelaPrincipal.getPartida().ehModoOffline()) {
//            atualizaModoOffline();
//        } else {
            if (janelaPrincipal.getAtorjogador().verificarSeConectado()) {
            	atualizaConectado(janelaPrincipal.getIdServidor());
            } else {
                atualizaDesconectado();
            }
//        }
    }

    public void atualizarMoedas(){
    	int moedas = tabuleiro.getMoedas();
    	Jogador jgAtual = janelaPrincipal.getAtorjogador().getJogadorAtual();
    	Jogador jgLocal = janelaPrincipal.getAtorjogador().getJogadorLocal();
        
        if (moedas < 0) {
        	if(jgAtual != jgLocal){
	        	casas[4][1].setIcon(Imagens.moedaInativa);
	        	casas[4][2].setIcon(Imagens.moedaInativa);
	        	casas[5][1].setIcon(Imagens.moedaInativa);
	        	casas[5][2].setIcon(Imagens.moedaInativa);
        	}else{
        		casas[4][1].setIcon(Imagens.moedaClickAnm);
	        	casas[4][2].setIcon(Imagens.moedaClickAnm);
	        	casas[5][1].setIcon(Imagens.moedaClickAnm);
	        	casas[5][2].setIcon(Imagens.moedaClickAnm);
        	}
        
        	
        }else{
        	if(moedas > -1){
        		casas[4][1].setIcon(Imagens.moedaCoroa);
	            casas[4][2].setIcon(Imagens.moedaCoroa);
	            casas[5][1].setIcon(Imagens.moedaCoroa);
	            casas[5][2].setIcon(Imagens.moedaCoroa);
        	}
              
	        if (moedas > 0) {
	            casas[4][1].setIcon(Imagens.moedaCara);
	        }
	        if (moedas > 1) {
	            casas[4][2].setIcon(Imagens.moedaCara);
	        }
	        if (moedas > 2) {
	            casas[5][1].setIcon(Imagens.moedaCara);
	        }
	        if (moedas > 3) {
	            casas[5][2].setIcon(Imagens.moedaCara);
	        }
        }
    }
    
    public Icon definirIconeCasa(int x, int y) {
        Icon posicaoIC;
        /*+1 para seguir a logica da matriz
         * https://drive.google.com/file/d/0BwEqvrpf-cBMazF6NEJ1UDJTZ0E/edit?usp=sharing*/
        int nroXY = ((x + 1) * 10) + (y + 1);
//		System.out.println(x + "," +  y + " - " + nroXY);
        switch (nroXY) {
            /*cases que retornam Inativa
             * default : 0 ,  70 , 22, 23 , 25, 26, 32, 33, ,35, 36, 55, 56, 65, 66 
             * por enquanto 51, 52, 61, 62 - futuro moedas
             */
            case 12:
            case 13:
            case 15:
            case 16:
            case 21:
            case 24:
            case 27:
            case 31:
            case 34:
            case 37:
            case 42:
            case 43:
            case 45:
            case 46:
            case 51:
            case 54:
            case 57:
            case 61:
            case 64:
            case 67:
            case 72:
            case 73:
            case 76:
                posicaoIC = Imagens.casaVazia;
                break;

            case 14:
            case 41:
            case 44:
            case 47:
                posicaoIC = Imagens.alaVazia;
                break;



            case 74:
                posicaoIC = Imagens.saidaVazia;
                break;

            case 52:
            case 53:
                posicaoIC = Imagens.moedaInativa;
                break;

            case 62:
            case 63:
                posicaoIC = Imagens.moedaInativa;
                break;

            case 75:
                posicaoIC = Imagens.inicioVazia;
                break;

            default:
                posicaoIC = Imagens.casaInativa;
                break;
        } // switch

        return posicaoIC;
    }//definirIconeCasa



    /**
     * @param linha
     * @param coluna
     */
    //Avalia se e�realmente um click de casa, ou outro como moeda
    public void clickMapa(int linha, int coluna) {
        /*+1 para seguir a logica da matriz
         * https://drive.google.com/file/d/0BwEqvrpf-cBMazF6NEJ1UDJTZ0E/edit?usp=sharing*/
        int nroXY = Regras.converteXY(linha, coluna);

        System.out.println("clickMapa: " + linha + "," + coluna + " - " + nroXY);
        switch (nroXY) {
            case 52:
            case 53:
            case 62:
            case 63:
                janelaPrincipal.getAtorjogador().clickMoeda();
                break;

            case 11:
            case 17:
            case 23:
            case 25:
            case 26:
            case 32:
            case 33:
            case 35:
            case 36:
            case 55:
            case 56:
            case 65:
            case 66:
            case 71:
            case 77:
                //casas inativas, por momento sem msg de click
                break;

            default:
                janelaPrincipal.getAtorjogador().clickCasa(false, linha, coluna);
                //janelaPrincipal.getAtorjogador().clickCasa(linha, coluna);
                break;
        }

    }//clickMapa

    public void inicioAtualizarMoedas() {

        casas[4][1].setIcon(Imagens.moedaClick);
        casas[4][1].setText("");

        casas[4][2].setIcon(Imagens.moedaClick);
        casas[4][2].setText("");

        casas[5][1].setIcon(Imagens.moedaClick);
        casas[5][1].setText("");

        casas[5][2].setIcon(Imagens.moedaClick);
        casas[5][2].setText("");

        casas[2][1].setText("<html><center>Clique nas moedas para definir quem inicia.");


    }//inicioAtualizarMoedas
    
    
    public void informaMovimentoIlegal(){
    	casas[2][1].setText("<html><font color=red size=4> <center>Movimento Ilegal!" ); //// TODO : JanelaPrincipal.notificarResultado() : aki provisorio!!!
    }
    
    public void informaPontuacaoMoedas(){
    	int moedas = tabuleiro.getMoedas();
    	int pontos = tabuleiro.getPontos();
    	String informa = moedas>-1?( moedas + ((moedas == 1 || moedas == 0) ?(" cara = "):(" caras = ")) + pontos + ((pontos == 1) ?(" ponto !") : (" pontos !"))):informaJogoIniciado();
    	casas[2][1].setText("<html><center><font color=blue size=4> <center>" + informa);
    	System.out.println(informa);
    }
    
    public String informaJogoIniciado(){
    	String informa = casas[2][1].getText() ;
    	boolean andamento = janelaPrincipal.getAtorjogador().ehEmAndamento();
    	Jogador atual = janelaPrincipal.getAtorjogador().getJogadorAtual();
    	Jogador local = janelaPrincipal.getAtorjogador().getJogadorLocal();
    	
    	if(andamento && atual != null){
    		if(atual == local ){
    			informa = "É sua vez, " + local.getNome() + " !";
    		}else{
    			informa = "Aguarde! Vez do " +  atual.getNome();
    		}
    	}
    	
    	return informa;
    }

    
	public void atualizaConectado(String idServidor){
		this.labelStatusRede.setForeground(new Color(0,125,0));
		this.labelStatusRede.setIcon(Imagens.on);
		this.labelStatusRede.setText("<html><center><b>Conectado<br><font size=-2>" + idServidor);	
	}
	
	public void atualizaDesconectado(){
		this.labelStatusRede.setForeground(Color.RED);
		this.labelStatusRede.setIcon(Imagens.off);
		this.labelStatusRede.setVerticalTextPosition(JLabel.BOTTOM);
		this.labelStatusRede.setHorizontalTextPosition(JLabel.CENTER);
		this.labelStatusRede.setText("Desconectado\r" + janelaPrincipal.getIdServidor());		
	}
	
	public void atualizaEmAndamento(){
		this.labelStatusRede.setForeground(Color.BLUE);
		this.labelStatusRede.setIcon(Imagens.jogo);
		this.labelStatusRede.setVerticalTextPosition(JLabel.BOTTOM);
		this.labelStatusRede.setHorizontalTextPosition(JLabel.CENTER);
		this.labelStatusRede.setText("Em Andamento");		
	}
	
	public void atualizaModoOffline(){
		this.labelStatusRede.setForeground(Color.BLACK);
		this.labelStatusRede.setIcon(Imagens.jogo);
		this.labelStatusRede.setVerticalTextPosition(JLabel.BOTTOM);
		this.labelStatusRede.setHorizontalTextPosition(JLabel.CENTER);
		this.labelStatusRede.setText("Modo Offline");	
	}


}
