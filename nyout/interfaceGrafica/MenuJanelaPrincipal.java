package nyout.interfaceGrafica;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import nyout.controle.AtorJogador;
import nyout.controle.Jogo;

public class MenuJanelaPrincipal extends JMenuBar {

    protected JanelaPrincipal janelaPrincipal;
    protected JMenu menuOpcoes;
    protected JMenu menuAjuda;
    protected JMenu menuComoJogar;
    AtorJogador atorJogador;

    public MenuJanelaPrincipal(JanelaPrincipal janela ) {
        janelaPrincipal = janela;
        

        menuOpcoes = new JMenu();
        menuOpcoes.setText("Opções");
        menuOpcoes.setMnemonic(KeyEvent.VK_O);
        menuOpcoes.setIcon(Imagens.opcoes);
        menuOpcoes.add(getMenuConectar());
        menuOpcoes.add(getMenuIniciar());
        menuOpcoes.add(getMenuDesconectar());
        menuOpcoes.addSeparator();
        menuOpcoes.add(getMenuAdversarios());
        add(menuOpcoes);
        
        menuAjuda = new JMenu();
        menuAjuda.setText("Ajuda");
        menuAjuda.setMnemonic(KeyEvent.VK_A);
        menuAjuda.setIcon(Imagens.ajuda);
        
        menuComoJogar = new JMenu();
        menuComoJogar.setText("Como jogar");
        menuComoJogar.setMnemonic(KeyEvent.VK_J);
        menuComoJogar.setIcon(Imagens.comoJogar25);
        
        menuComoJogar.add(getMenuCJBaixar());
        menuComoJogar.add(getMenuCJTInicial());
        menuComoJogar.add(getMenuCJConectar());
        menuComoJogar.add(getMenuCJIniciarPartida());
        menuComoJogar.add(getMenuCJPecasJaula());
        menuComoJogar.add(getMenuCJPecasMesmoJogador());
        menuComoJogar.add(getMenuCJGanhador());
        menuAjuda.add(menuComoJogar);
        
        menuAjuda.add(getMenuRegras());
        menuAjuda.add(getMapaTabuleiro());
        menuAjuda.addSeparator();
        menuAjuda.add(getMenuSobre());
        menuAjuda.addSeparator();
        menuAjuda.add(getMenuTesteVitoria());
        menuAjuda.add(getMenuTesteDerrota());
        add(menuAjuda);
        
        
        
    }

    
    private JMenuItem getMenuCJBaixar(){
    	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.vmnta7sw3t7k
        JMenuItem menuCJBaixar;
        menuCJBaixar = new JMenuItem();
        menuCJBaixar.setText("1 - Baixar o Jogo");
        menuCJBaixar.setMnemonic(KeyEvent.VK_B);
        
        menuCJBaixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.vmnta7sw3t7k");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJBaixar;
    }

    private JMenuItem getMenuCJTInicial(){
    	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.530839yhfc8j
        JMenuItem menuCJTi;
        menuCJTi = new JMenuItem();
        menuCJTi.setText("2 - Tela Inicial");
        menuCJTi.setMnemonic(KeyEvent.VK_T);
        
        menuCJTi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.530839yhfc8j");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJTi;
    }
    
    
    private JMenuItem getMenuCJConectar(){
    	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.k4ohikdwk67d
        JMenuItem menuCJconectar;
        menuCJconectar = new JMenuItem();
        menuCJconectar.setText("3 - Conectar o Jogo");
        menuCJconectar.setMnemonic(KeyEvent.VK_C);
        
        menuCJconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.k4ohikdwk67d");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJconectar;
    }
    
    private JMenuItem getMenuCJIniciarPartida(){
    	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.45t4phii87bw
        JMenuItem menuCJiniciarPartida;
        menuCJiniciarPartida = new JMenuItem();
        menuCJiniciarPartida.setText("4 - Iniciar Partida");
        menuCJiniciarPartida.setMnemonic(KeyEvent.VK_I);
        
        menuCJiniciarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.45t4phii87bw");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJiniciarPartida;
    }
    
    private JMenuItem getMenuCJPecasJaula(){
    	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.mgaa0en5rp2v
        JMenuItem menuCJpecasJaula;
        menuCJpecasJaula = new JMenuItem();
        menuCJpecasJaula.setText("5 - Peças adversárias para Jaula");
        menuCJpecasJaula.setMnemonic(KeyEvent.VK_J);
        
        menuCJpecasJaula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.mgaa0en5rp2v");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJpecasJaula;
    }
    
    
    
	private JMenuItem getMenuCJPecasMesmoJogador(){
	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.tuwiv1vfncor
        JMenuItem menuCJpcsMesmoJgd;
        menuCJpcsMesmoJgd = new JMenuItem();
        menuCJpcsMesmoJgd.setText("6 - Agrupar peças mesmo Jogador");
//        menuCJpcsMesmoJgd.setMnemonic(KeyEvent.VK_J);
        
        menuCJpcsMesmoJgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.tuwiv1vfncor");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJpcsMesmoJgd;
    }
	
	private JMenuItem getMenuCJGanhador(){
	//https://docs.google.com/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.mlhnun8eg9bc
        JMenuItem menuCJganhador;
        menuCJganhador = new JMenuItem();
        menuCJganhador.setText("7 - É Ganhador do NY-OUT");
//        menuCJpcsMesmoJgd.setMnemonic(KeyEvent.VK_J);
        
        menuCJganhador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1HgQvwThX791bMqyt5nSvZraNFdRH9hYfsb8Z9kSP8tU/pub#h.mlhnun8eg9bc");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuCJganhador;
    }
    

    private JMenuItem getMenuRegras(){
    //https://docs.google.com/document/d/1FrzKw1nuFr3cnlZEKF2EdZ4wsGhB1V9fajDce0BUa7Y/pub
        JMenuItem menuregras;
        menuregras = new JMenuItem();
        menuregras.setText("Regras do Ny-Out");
        menuregras.setMnemonic(KeyEvent.VK_R);
        menuregras.setIcon(Imagens.regras25);
        menuregras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	URL url;
				try {
					url = new URL("https", "docs.google.com", "/document/d/1FrzKw1nuFr3cnlZEKF2EdZ4wsGhB1V9fajDce0BUa7Y/pub");
					abrirPaginaWebUrl(url);
				} catch (MalformedURLException e1) {
					 e1.printStackTrace();
				}
            	
            }
        });

        return menuregras;
    }
    
    private JMenuItem getMapaTabuleiro(){
        JMenuItem menuMapaTabuleiro;
        menuMapaTabuleiro = new JMenuItem();
        menuMapaTabuleiro.setText("Mapa do Tabuleiro");
        menuMapaTabuleiro.setMnemonic(KeyEvent.VK_M);
        menuMapaTabuleiro.setIcon(Imagens.peoes25);
        menuMapaTabuleiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {                
            	//janelaPrincipal.getPartida().dialogoMapaTabuleiro();
            	atorJogador.getMapaTabuleiro();
            }
        });

        return menuMapaTabuleiro;
    }
    
    public static void abrirPaginaWeb(java.net.URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void abrirPaginaWebUrl(URL url) {
        try {
        	abrirPaginaWeb(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    
    private JMenuItem getMenuSobre(){
        JMenuItem menuSobre;
        menuSobre = new JMenuItem();
        menuSobre.setText("Sobre Ny-Out");
        menuSobre.setMnemonic(KeyEvent.VK_S);
        menuSobre.setIcon(Imagens.sobre25);
        menuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {                
            	janelaPrincipal.dialogoSobre();
            }
        });

        return menuSobre;
    }
    
    private JMenuItem getMenuTesteVitoria(){
        JMenuItem menuTesteVitoria;
        menuTesteVitoria = new JMenuItem();
        menuTesteVitoria.setText("Teste Vitória");
        menuTesteVitoria.setMnemonic(KeyEvent.VK_V);
        menuTesteVitoria.setIcon(Imagens.vitoria25);
        menuTesteVitoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {                
            	janelaPrincipal.dialogoVitoria();
            }
        });

        return menuTesteVitoria;
    }
    
    private JMenuItem getMenuTesteDerrota(){
        JMenuItem menuTesteDerrota;
        menuTesteDerrota = new JMenuItem();
        menuTesteDerrota.setText("Teste Derrota");
        menuTesteDerrota.setMnemonic(KeyEvent.VK_D);
        menuTesteDerrota.setIcon(Imagens.derrota25);
        menuTesteDerrota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {                
            	janelaPrincipal.dialogoDerrota();
            }
        });

        return menuTesteDerrota;
    }

    private JMenuItem getMenuConectar() {
        JMenuItem menuConectar;
        menuConectar = new JMenuItem();
        menuConectar.setText("Conectar");
        menuConectar.setMnemonic(KeyEvent.VK_C);
        menuConectar.setIcon(Imagens.conectar);
        menuConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {                
                try {
                	if(!janelaPrincipal.getAtorjogador().verificarSeConectado()){
                		janelaPrincipal.conectar();
                		/* 2017-04-16 inseridos no metodo janelaPrincipal.conectar()*/
	                    
                	}else{
                		janelaPrincipal.notificarResultado(1);
                	}
                    
                } catch (JahConectadoException ex) {
                	janelaPrincipal.notificarResultado(1);
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                    janelaPrincipal.getAtorjogador().setEhConectado(false);
                } catch (NaoPossivelConectarException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                    janelaPrincipal.getAtorjogador().setEhConectado(false);
                } catch (ArquivoMultiplayerException ex) {
                    Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
                    janelaPrincipal.getAtorjogador().setEhConectado(false);
                }
                
                ;
                
            }
        });

        return menuConectar;
    }

    private JMenuItem getMenuAdversarios(){
    	JMenuItem menuAdversarios;
    	menuAdversarios = new JMenuItem();
    	menuAdversarios.setText("Listar Adversários");
    	menuAdversarios.setMnemonic(KeyEvent.VK_A);
    	menuAdversarios.setIcon(Imagens.listar);
    	menuAdversarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
               janelaPrincipal.listarTodosAdversarios();
            }
        });

        return menuAdversarios;    	
    }
    
    private JMenuItem getMenuIniciar() {
        JMenuItem menuIniciar;
        menuIniciar = new JMenuItem();
        menuIniciar.setText("Iniciar partida");
        menuIniciar.setMnemonic(KeyEvent.VK_I);
        menuIniciar.setIcon(Imagens.iniciar);
        menuIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            		System.out.println("MenuJP : getMenuIniciar > janelaPrincipal.inciarPartida(new Integer(2)");
                    janelaPrincipal.inciarPartida(new Integer(2));
                   
            }
        });

        return menuIniciar;
    }

    private JMenuItem getMenuDesconectar() {
        JMenuItem menuDesconectar;
        menuDesconectar = new JMenuItem();
        menuDesconectar.setText("Desconectar");
        menuDesconectar.setMnemonic(KeyEvent.VK_D);
        menuDesconectar.setIcon(Imagens.desconectar);
        menuDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                janelaPrincipal.getAtorjogador().desconectar();
				janelaPrincipal.getAtorjogador().setEhConectado(false);
				janelaPrincipal.getAtorjogador().setEmAndamento(false);
				janelaPrincipal.iniciarJogo(); //funcao resetar tudo

				janelaPrincipal.atualizar();
				janelaPrincipal.infomarResultadoConexao();
                
            }
        });

        return menuDesconectar;
    }
}
