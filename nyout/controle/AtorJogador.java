package nyout.controle;



import java.util.List;

import nyout.interfaceGrafica.AvatarDialog;
import nyout.interfaceGrafica.JanelaPrincipal;
import nyout.modelo.JogadaNy;
import nyout.modelo.Jogador;
import nyout.modelo.Tabuleiro;

public class AtorJogador {
	
	protected JanelaPrincipal janelaPrincipal;
	protected Jogo partida;
	protected AtorNetGames atorNetGames;
	protected String idJogador;

	protected boolean modoOffline;
    protected boolean conectado;
    protected boolean faltaJgRemoto;
    protected boolean emAndamento;
	
	public AtorJogador (JanelaPrincipal jan){
		super();
		partida = new Jogo(this);
		atorNetGames = new AtorNetGames(partida, this);
		//partida.setAtorNetGames(atorNetGames);
		janelaPrincipal = jan;
	}

	public JanelaPrincipal getJanelaPrincipal() {
		return janelaPrincipal;
	}


	public void getMapaTabuleiro() {
		// janelaPrincipal.getPartida().dialogoMapaTabuleiro();
		janelaPrincipal.getMapaTabuleiro();
		//partida.dialogoMapaTabuleiro();
	}
	
	public Jogo getPartida() {
        return partida;
    }

	public void getMenuSobre() {
		//janelaPrincipal.getPartida().dialogoSobre();
		janelaPrincipal.dialogoSobre();
	}
	

	
	public int conectar() {
		boolean conectado = partida.verificarSeConectado();
		if (!conectado){
			String servidor = this.obterDadosConexao();
			janelaPrincipal.setIdServidor(servidor);
			boolean exito = atorNetGames.conectar(servidor, idJogador);
			if (exito){
				partida.setEhConectado(true);
				getJogadorLocal().setAvatar(escolherAvatar());
				partida.getTabuleiro().resetar();
				return 0;
			}else{
				return 2;
			}			
		}else{
			return 1;
		}
		
		
	}
	
    private String obterDadosConexao() {
		idJogador = janelaPrincipal.obterIdJogadorJop();
		String servidor = janelaPrincipal.obterIdServidorJop();
		return servidor;
	}
    
	public int desconectar() {
		boolean conectado = partida.verificarSeConectado();
		if (conectado){
			boolean exito = atorNetGames.desconectar();
			if (exito){
				partida.setEhConectado(false);
				return 3;
			}else{
				return 5;
			}			
		}else{
			return 4;
		}			
	}

	
//	public int iniciarPartida() {
//		boolean conectado = false;
//		boolean interromper = false;
//		boolean emAndamento = partida.ehEmAndamento();
//		if (emAndamento){
//			interromper = this.avaliarInterrupcao();
//		}else{
//			conectado = partida.verificarSeConectado();
//		}
//		if (interromper || ((!emAndamento) && conectado)){
//			atorNetGames.iniciarPartida();
//			partida.setModoOffline(false);
//			partida.setEmAndamento(true);
//			janelaPrincipal.atualizar();
//			
//			return 6;
//		}
//		if (!conectado) {
//			janelaPrincipal.atualizar();
//			return 7;
//		}
//		return 13;
//	}
	
	
    public int iniciarPartida(){
//      18-05-2014 void para int, preciso do retorno para saber o q aconteceu
    	emAndamento = partida.ehEmAndamento();
    	conectado = partida.verificarSeConectado();
//      	System.out.println("AtorJogador.inciarPartida.posicao : " + posicao );
      	if(!emAndamento && conectado){
      		System.out.println("AtorJogador: inciarPartida() > atorNetGames.iniciarPartidaAntesProxy()");
  			atorNetGames.iniciarPartida();
  			partida.setModoOffline(false);
  			this.setEmAndamento(true);
  			System.out.println("AtorJogador: inciarPartida() > janelaPrincipal.atualizar()");
  			janelaPrincipal.atualizar();
  			
  			System.out.println("AtorJogador: inciarPartida(posicao) return 6;");
  			return 6; //"O jogo iniciou " + nmJgdrLocal + ", e voce comeca jogando!"
  		}
  		
  		if(!conectado){
  			System.out.println("AtorJogador: inciarPartida() : if(!conectado) > janelaPrincipal.atualizar()");
  			janelaPrincipal.atualizar();
  			//TODO mensagem nao condiz com !contectado, pode ser q o nro 7 esteja certo... verificar
  			return 7; //"Tentativa de inicio sem conexao previamente estabelecida."
  		}
      	
      	System.out.println("nome jogador remoto : " + getJogadorRemoto().getNome());
      	return 17; //"A partida corrente nÃ£o pode ser interrompida!"
    }
    public int iniciarPartida(Integer posicao){
    	System.out.println("AtorJogador: resultado = inciarPartida(posicao) > atorNetGames.iniciarPartidaAntesProxy(posicao : "+ posicao +" )");
    	int resultado = 	atorNetGames.iniciarPartidaAntesProxy(posicao);
    	System.out.println("AtorJogador: inciarPartida(posicao) return resultado;");
  		return resultado;
      }
    
    
    
	
    public boolean verificarSeConectado() {
        return partida.verificarSeConectado();
    }
	
    
    public int escolherAvatar(){
    	return (AvatarDialog.informaAvatarDialogo("Qual avatar do " + getJogadorLocal().getNome()));		
    }
	    
    
    public String listarTodosAdversarios(){
		//TODO inserir AtorNetGames
    	String listaTdsAdversarios = "";
    	
    	List <String> adversarios;
		adversarios = atorNetGames.obterNomeAdversarios();
		System.out.println("adversarios.size() : " + adversarios.size());
		
		if(adversarios.size() > 1){
			for(String adversario : adversarios){
				listaTdsAdversarios = listaTdsAdversarios + "\n" + adversario.toString();
			}
		}else if (adversarios.size() == 1){
			listaTdsAdversarios = adversarios.get(0).toString();
		}
    	
		return listaTdsAdversarios;
    }
    
        

    
    
	public boolean avaliarInterrupcao() {
		return true;
	}
	
	

	
	
	public void enviarJogada() {
		partida.enviarJogada(partida.montarJogada());
	}
	
	

	public void receberJogada(JogadaNy jogada) {
		partida.receberJogada(jogada);
		janelaPrincipal.atualizar();
	}
	
	
	private void setJogadorAtual(int pscDaVez) {
		partida.setJogadorAtual(pscDaVez);
		
	}

	public void tratarIniciarPartida(Integer posicao) {
		System.out.println("*** tratarIniciarPartida ***");
	}

	public boolean ehEmAndamento() {
		return partida.ehEmAndamento();
	}

	//Adicionado tag "deprecated"
	@Deprecated
	public void liberaAnimal(Jogador jogador) {
		partida.liberaAnimal(jogador);
	}

	public void setEhConectado(boolean b) {
		partida.setEhConectado(b);
	}

	public void setEmAndamento(boolean b) {
		partida.setEmAndamento(b);
		
	}

	public Tabuleiro getTabuleiro(){
		return partida.getTabuleiro();
	}
		
	
	public Jogador getJogadorLocal(){
		return partida.getJogadorLocal();
	}
	
	
	public Jogador getJogadorRemoto(){
		return partida.getJogadorRemoto();
	}
	
	public Jogador getJogadorAtual(){
		return partida.getJogadorAtual();
	}

	public void clickMoeda() {
		partida.clickMoeda();
	}

	//Adicionado tag "deprecated"
	@Deprecated
	public void clickCasa(int linha, int coluna) {
			partida.clickCasa(linha, coluna);
	}
	
	//Novo m�todo
	public void clickCasa(boolean jaula, int linha, int coluna) {
		if (jaula) {
			partida.liberaAnimal(getJogadorLocal());
		} else {
			partida.clickCasa(linha, coluna);
		}
	}

	public void atualizar() {
		janelaPrincipal.atualizar();
		
	}




}
