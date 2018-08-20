package nyout.interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import nyout.controle.AtorJogador;
import nyout.interfaceGrafica.ajuda.MapaTabuleiro;
import nyout.interfaceGrafica.ajuda.Sobre;

public class JanelaPrincipal extends JFrame {

    //protected Jogo partida;
    protected JaulaPanel jaulaJogadorLocal;
    protected JaulaPanel jaulaJogadorRemoto;
    protected TabuleiroPanel tabuleiroPnl;
    protected IniciarPanel telaInicial;
    protected AtorJogador atorjogador;


	protected static final long serialVersionUID = 1L;

    protected int escalaJn = 2;

    public String idServidor = "netgames.labsoft.ufsc.br";

    public JanelaPrincipal() {
        super();

        telaInicial = new IniciarPanel(this);
        atorjogador = new AtorJogador(this);

        this.setFont(new Font("Dialog", Font.BOLD, 13));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPrincipal.class.getResource("/imagens/play_icon2.png")));
        this.setTitle("NY-OUT : Back to the Forest - 170706 - Versão Final");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 450 * escalaJn, 350 * escalaJn);

        this.setLayout(new BorderLayout());
        this.add(telaInicial, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    } 

    protected void iniciarJogo() {
        // esconde a tela inicial
    	if(telaInicial != null){
    		telaInicial.setVisible(false);
    		telaInicial = null;
    	}
    	
        jaulaJogadorLocal = new JaulaPanel(atorjogador.getJogadorLocal(), this);
        jaulaJogadorRemoto = new JaulaPanel(atorjogador.getJogadorRemoto(), this);
        tabuleiroPnl = new TabuleiroPanel(atorjogador.getTabuleiro(),this);

        setJMenuBar(new MenuJanelaPrincipal(this ));
        
        add(jaulaJogadorLocal, BorderLayout.WEST);
        add(jaulaJogadorRemoto, BorderLayout.EAST);
        add(tabuleiroPnl, BorderLayout.CENTER);
                
        setVisible(true);

    }
    
    public Component adicionarFundo(){
    	JPanel contentPane = new JPanel();
    	setContentPane(contentPane);
		contentPane.setLayout(null);
		
    	JLabel fundoZoo = new JLabel("");
    	fundoZoo.setIcon(Imagens.zoologico);
    	fundoZoo.setBounds(0, 0, 450 * escalaJn, 350 * escalaJn);
    	contentPane.add(fundoZoo);
    	
    	return contentPane;
    	
    }

    
    public void inciarPartida(){
    	System.out.println("JanPri : inciarPartida() > atorjogador.iniciarPartida()");
    	int resultado = atorjogador.iniciarPartida();
    	notificarResultado(resultado);
    }
    public void inciarPartida(Integer posicao){
    	System.out.println("JanPri : inciarPartida(Integer posicao) > atorjogador.iniciarPartida(posicao)");
    	int resultado = atorjogador.iniciarPartida(posicao);
    	notificarResultado(resultado);
    }
    
    
    
    public void notificarResultado(int codigo) {

        String nmJgdrLocal = atorjogador.getJogadorLocal().getNome();
        
        //TODO nao enviar msg ate remoto atualizar
//        if(this.partida.getJogadorRemoto().getAvatar() < 1){
//        	System.out.println("\nnotificarResultado - codigo (antes) : " + codigo + "\t(agora) = -1");
//        	codigo = -1;
//        }

        switch (codigo) {
            case 0:
                JOptionPane.showMessageDialog(this, "Conexão efetuada com sucesso!\n" + this.idServidor + "\nBom jogo " + nmJgdrLocal + ".", nmJgdrLocal + " conectado ao NetGames", JOptionPane.INFORMATION_MESSAGE, Imagens.on);
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "<html>Você já está conectado ao servidor: " + this.idServidor + "!\n\nCaso queria conectar com outro servidor, desconecte-se desse primeiro.", "Tentativa de conexão repetida", JOptionPane.WARNING_MESSAGE);
                System.out.println("Você já está conectado ao servidor: " + this.getIdServidor() + "!");
                break;
            case 2:
                JOptionPane.showMessageDialog(this, "Tentativa de conexão com NetGames falhou.", "Conexão falhou", JOptionPane.ERROR_MESSAGE, Imagens.off);
                break;
            case 3:
                JOptionPane.showMessageDialog(this, "Desconexão efetuada com Sucesso.", "Desconectado do NetGames", JOptionPane.INFORMATION_MESSAGE, Imagens.off);
                break;
            case 4:
                JOptionPane.showMessageDialog(this, "Você já está Desconectado.", "Tentativa de desconexão Repetida", JOptionPane.WARNING_MESSAGE, Imagens.off);
                break;
            case 5:
                JOptionPane.showMessageDialog(this, "Tentativa de desconexao falhou.");
                break;
            case 6:
                JOptionPane.showMessageDialog(this, "O jogo iniciou " + nmJgdrLocal + ", e você começa jogando!");
                break;
            case 7:
                JOptionPane.showMessageDialog(this, "Tentativa de inicio sem conexao previamente estabelecida.", "Pressione Conectar", JOptionPane.WARNING_MESSAGE);
                break;
            case 8:
                JOptionPane.showMessageDialog(this, "Desculpe " + nmJgdrLocal + "!\nNão é a sua vez.", "Vez do Jogador Remoto : " + atorjogador.getJogadorRemoto().getNome(), JOptionPane.ERROR_MESSAGE);
                break;
            case 9: {
                System.out.println("Cheguei aqui!");
//                boolean jogadorLocalEhVencedor = partida.jogadorLocalEhVencedor();

//                if (jogadorLocalEhVencedor) {
//                    JOptionPane.showMessageDialog(this, "Parabéns, você ganhou!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Você perdeu! :/");
//                }
//                JOptionPane.showMessageDialog(this, "Para jogar novamente, clique em \"Iniciar nova partida\" no menu ou "
//                        + "aguarde uma nova solicitação.");
                break;
            }
            case 10: {
                JOptionPane.showMessageDialog(this, nmJgdrLocal + "\n\nÉ a sua vez!");
                break;
            }
            case 11: {
                JOptionPane.showMessageDialog(this, "Você não pode clicar numa posição vazia");
                break;
            }
            case 12:
                JOptionPane.showMessageDialog(this, "Este animal não é seu!");
                break;
            case 13: {
                JOptionPane.showMessageDialog(this, "Você já sorteou sua pontuação para ver quem inicia.\n\nAguarde o lance do jogador remoto : " + atorjogador.getJogadorRemoto().getNome() + ".", "Aguarde lance do Jogador Remoto.", JOptionPane.WARNING_MESSAGE);
                break;
            }
            case 14: {
                JOptionPane.showMessageDialog(this, "Não há blocos suficientes para a movimentação");
                break;
            }
            case 15: {
                JOptionPane.showMessageDialog(this, "Não pode mover, pois há peças suas no intervalo!");
                break;
            }
            case 16: {
                JOptionPane.showMessageDialog(this, "Peça selecionada!");
                break;
            }
            case 17:
                JOptionPane.showMessageDialog(this, "A partida corrente não pode ser interrompida!");
                break;
            case 18:
                JOptionPane.showMessageDialog(this, 
                        "O jogo ainda não começou! Conecte-se e aguarde uma solicitação"
                        + " ou clique em \"Iniciar nova partida\"!");
                break;
            case 19:
                JOptionPane.showMessageDialog(this, "A partida começou! Aguarde a primeira jogada do jogador remoto : " 
                        + atorjogador.getJogadorRemoto().getNome());
                break;
        };
    }//notificarResultado

    
    public String obterIdJogadorJop() {
        String idJogador = this.atorjogador.getJogadorLocal().getNome();
        idJogador = JOptionPane.showInputDialog(this, ("Nome do jogador"), idJogador);

        //TODO Tentar buscar nome de outr forma, aki apenas para teste
        this.atorjogador.getJogadorLocal().setNome(idJogador);
        this.jaulaJogadorLocal.atualizar();
        return idJogador;
    }

    public String obterIdServidorJop() {
        return JOptionPane.showInputDialog(this, 
                "Insira o endereço do servidor", idServidor);
    }
    
    public String getIdServidor() {
		return idServidor;
	}
    
    public void setIdServidor(String idSrv){
    	this.idServidor = idSrv;	
    }
    
  
    public void atualizar() {
    	System.out.println("JanelaPrincipal.atualizar()");
        jaulaJogadorLocal.atualizar();
        jaulaJogadorRemoto.atualizar();
        tabuleiroPnl.atualizar();
        tabuleiroPnl.informaPontuacaoMoedas(); //TODO, nao se tem certeza que deveria ficar aqui
    }
    
    public void informaTabuleiroMovimentoIlegal(){
    	tabuleiroPnl.informaMovimentoIlegal(); // TODO : notificarResultado() : Aki provisorio
    }
    

	public void infomarResultadoConexao() {
		if(atorjogador.verificarSeConectado()){
			notificarResultado(0); //"Conexão efetuada com sucesso!\n"
			atualizaTabuleiroConectado();
		}else{
			notificarResultado(2);
			atualizaTabuleiroDesconectado();
		}
		
	}
	
	
	public void atualizaTabuleiroConectado(){
			tabuleiroPnl.atualizaConectado(this.idServidor);		
	}
	
	
	public void infomarResultadoDesconexao() {
		if(!atorjogador.verificarSeConectado()){
			notificarResultado(3);
			atualizaTabuleiroDesconectado();
		}else{
			notificarResultado(5);
		}
		atualizar();
		atualizaTabuleiroDesconectado();
		
	}
	
	
	public void atualizaTabuleiroDesconectado(){
		tabuleiroPnl.atualizaDesconectado();		
	}
	
	public void  atualizaJogoEmAndamento(){
		tabuleiroPnl.atualizaEmAndamento();
	}

	
	public void conectar() throws JahConectadoException, NaoPossivelConectarException, ArquivoMultiplayerException{
		atorjogador.conectar();
		
		atualizar();
		infomarResultadoConexao();
	}
	


	public void listarTodosAdversarios(){
		//TODO Inserir Atorjogador
		String listaAdversarios = "";
		boolean conectado = atorjogador.verificarSeConectado();
		if(conectado){
			listaAdversarios = atorjogador.listarTodosAdversarios();
			System.out.println("\nLista adversarios Conectados:\n-----------------------------\n" + listaAdversarios + "\n-----------------------------");
			JOptionPane.showMessageDialog(this, "\nLista adversarios Conectados:\n-----------------------------\n" + listaAdversarios + "\n-----------------------------") ;	
				
		}else{
			JOptionPane.showMessageDialog(this, "Não é possível exibir a lista.\nVocê deve se conectar primeiro!", "Não é possivel exibir lista : Desconectado", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void getMapaTabuleiro() {
		MapaTabuleiro mpt = new MapaTabuleiro();  
	  	mpt.setVisible(true);  
	  	mpt.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
    public AtorJogador getAtorjogador() {
		return atorjogador;
	}
    
    public int dialogoVitoria(){
    	return (VitoriaFrame.informaInicioDialogo(atorjogador.getJogadorLocal().getNome()));	
    }
    
    public void dialogoDerrota(){
	  	DerrotaFrame frVtr = new DerrotaFrame(atorjogador.getJogadorLocal().getNome());  
        frVtr.setVisible(true);  
        frVtr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void dialogoSobre(){
	  	Sobre sbr = new Sobre();  
        sbr.setVisible(true);  
        sbr.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}