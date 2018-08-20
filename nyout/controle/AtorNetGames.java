package nyout.controle;

import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import nyout.modelo.JogadaNy;

import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetGames implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected AtorJogador atorJogador;
	protected Jogo partida;
	protected Proxy proxy;
	
	protected boolean modoOffline;
    protected boolean conectado;
    protected boolean faltaJgRemoto;
    protected boolean emAndamento;
	
	public AtorNetGames(Jogo partida, AtorJogador atorJgdr){
		super();
		this.atorJogador = atorJgdr;
		this.partida = partida;
		
		this.partida.setAtorNetGames(this);
		
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);	
	}
	
	public boolean conectar(String servidor, String nome) {
		try {
			proxy.conectar(servidor, nome);
			return true;
		} catch (JahConectadoException e) {
			e.printStackTrace();
			return false;
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			return false;
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean desconectar() {
		try {
			proxy.desconectar();
			return true;
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
    public int iniciarPartidaAntesProxy(Integer posicao){
    	System.out.println("AtorNetGames.iniciarPartidaAntesProxy()");
    	emAndamento = partida.ehEmAndamento();
    	conectado = partida.verificarSeConectado();
      	System.out.println("AtorNG : iniciarPartidaAntesProxy(posicao : " + posicao + " )" );
      	if(!emAndamento && conectado){
      		System.out.println("AtorNG : iniciarPartidaAntesProxy(..) > AtorNetGames.iniciarPartida(..)" );
  			iniciarPartida(posicao);
  			partida.setModoOffline(false);
  			atorJogador.setEmAndamento(true);
  			System.out.println("AtorNG : iniciarPartidaAntesProxy > atorJogador.atualizar()"); 
  			atorJogador.atualizar();
  			
  			System.out.println("AtorNG : iniciarPartidaAntesProxy : return 6;");
  			return 6; //"O jogo iniciou " + nmJgdrLocal + ", e voce comeca jogando!"
  		}
  		
  		if(!conectado){
  			atorJogador.atualizar();
  			//TODO mensagem nao condiz com !contectado, pode ser q o nro 7 esteja certo... verificar
  			return 7; //"Tentativa de inicio sem conexao previamente estabelecida."
  		}
      	
      	System.out.println("AtorNG : iniciarPartidaAntesProxy : nome jogador remoto : " + atorJogador.getJogadorRemoto().getNome());
      	return 17; //"A partida corrente nÃ£o pode ser interrompida!"
    }

	public void iniciarPartida() {
		try {
			System.out.println("AtorNG : iniciarPartida > proxy.iniciarPartida()");
			proxy.iniciarPartida(new Integer(2));
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}
	public void iniciarPartida(Integer posicao) {
		try {
			System.out.println("AtorNG : iniciarPartida > proxy.iniciarPartida(posicao : " + posicao + " )");
			proxy.iniciarPartida(posicao);
		} catch (NaoConectadoException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void iniciarNovaPartida(Integer posicao) {
		System.out.println("AtorNG : iniciarPartida > partida.iniciarNovaPartida(posicao : " + posicao + " )");
		partida.iniciarNovaPartida(posicao);
	}
	
	public void enviarJogada(Jogada jogada) {
		System.out.println("AtorNetGames.enviarJogada()");
		try {
			System.out.println("AtorNG : enviarJogada(..)  >  proxy.enviaJogada)");
			proxy.enviaJogada((Jogada) jogada);
		} catch (NaoJogandoException e) {
			e.printStackTrace();
		}
	}

//	public String informarNomeAdversario(String idUsuario) {
//		String aux1 = proxy.obterNomeAdversario(new Integer(1));
//		String aux2 = proxy.obterNomeAdversario(new Integer(2));;
//		if (aux1.equals(idUsuario)){
//			return aux2;
//		} else {
//			return aux1;
//		}		
//	}
	
	
	@Override
	public void receberJogada(Jogada jogada) {
		JogadaNy jogadaNy = (JogadaNy) jogada;
		System.out.println("AtorNG : receberJogada(..) > partida.receberJogada (..)");
		partida.receberJogada (jogadaNy);
	}

	
	public void finalizarPartidaComErro(String aMessage) {
		throw new UnsupportedOperationException();
	}

	public void receberMensagem(String aMessage) {
		throw new UnsupportedOperationException();
	}

	public void tratarConexaoPerdida() {
		throw new UnsupportedOperationException();
	}

	public void tratarPartidaNaoIniciada(String aMessage) {
		throw new UnsupportedOperationException();
	}

	public List<String> obterNomeAdversarios() {
		System.out.println("AtorNG : obterNomeAdversarios > return proxy.obterNomeAdversarios()");
		return proxy.obterNomeAdversarios();
	}

	public String obterNomeAdversario(Integer posicao) {
		System.out.println("AtorNG : obterNomeAdversario(..)  > return proxy.obterNomeAdversario(posicao : "+ posicao +" )");
		return proxy.obterNomeAdversario(posicao);
	}

}
