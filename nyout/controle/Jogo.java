package nyout.controle;

import java.util.logging.Level;
import java.util.logging.Logger;

import nyout.estrategia.Regras;
import nyout.interfaceGrafica.JanelaPrincipal;
import nyout.modelo.Casa;
import nyout.modelo.JogadaNy;
import nyout.modelo.Jogador;
import nyout.modelo.Tabuleiro;

public class Jogo{

	protected Tabuleiro tabuleiro;
    protected Jogador jogadorLocal;
    protected Jogador jogadorRemoto;
    protected Jogador jogadorAtual;
    private JanelaPrincipal janelaPrincipal;

    //protected Proxy conexao;
    protected AtorNetGames atorNetGames;
    protected AtorJogador atorJogador;

    protected boolean modoOffline;
    protected boolean conectado;
    protected boolean faltaJgRemoto;
    protected boolean emAndamento;
    
    public Jogo(AtorJogador pAtorJogador) {
    //public Jogo(JanelaPrincipal janela) {
        //conexao = Proxy.getInstance();
        //conexao.addOuvinte(this);
    	atorJogador = pAtorJogador;
//    	atorNetGames = new AtorNetGames(this, atorJogador);		2017-06-28
        conectado = false;
        faltaJgRemoto = true;
        

        janelaPrincipal = pAtorJogador.getJanelaPrincipal();
        jogadorLocal = new Jogador("Local", 4);
        jogadorRemoto = new Jogador("Net", 4);
        tabuleiro = new Tabuleiro();
        tabuleiro.resetar();
        
        jogadorLocal.setAvatar(0);
        jogadorRemoto.setAvatar(0);

        assert(janelaPrincipal != null);
        // para testes
//        modoOffline = true;
//        jogadorAtual = jogadorLocal;
//        emAndamento = true;

        
    }

    // getters
    

    public Jogador getJogadorLocal() {
        return jogadorLocal;
    }

    public Jogador getJogadorRemoto() {
        return jogadorRemoto;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }
    
    public void setJogadorAtual(int  pscJA) {
    	if(pscJA == jogadorLocal.getPosicao()){
    		this.jogadorAtual = jogadorLocal;
    	}else if(pscJA == jogadorRemoto.getPosicao()){
    		this.jogadorAtual = jogadorRemoto;
    	}
	}

	public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    // altera o comportamento do jogo para nÃ£o usar a rede
    public void setModoOffline(boolean m) {
        // adicionar verificacao de partida em andamento
        modoOffline = m;
    }
    
    public boolean ehModoOffline() {
        return modoOffline;
    }
    
    public boolean verificarSeConectado() {
        return conectado;
    }
    
    
    public void setEhConectado(boolean c){
    	conectado = c;
    }

    
    public boolean ehEmAndamento() {
        return emAndamento;
    }
    
    public void setEmAndamento(boolean eman){
    	this.emAndamento = eman;
    }
    
    // aÃ§Ãµes da interface grÃ¡fica

    public void clickCasa(int linha, int coluna) {
    	//TODO quebrar metodo (video professor qualidade de metodo)
        if (jogadorAtual == null || !emAndamento || faltaJgRemoto || jogadorAtual != jogadorLocal) {
            return;
        }

        Casa selecionada = tabuleiro.getCasaSelecionada(); //avatar do jogador a ser movido
        Casa clickada = tabuleiro.getCasa(linha, coluna); // destino do avatar
        int pontos = getPontos();
        
        
        //Selecionar Casa com Avatar do jogador
        if (selecionada == null) {
        	if (clickada.getJogador() == jogadorAtual) { 
        		tabuleiro.setCasaSelecionada(linha, coluna);
            	setCasaFrenteAMover(linha, coluna);            
            	avaliarVirarEsquerda(linha, coluna);
        	}
            
        }
        
        
//TODO voltarjaula pode ser aqui tbm, segunda opção
        //Deselecionar Casa jogador
        else if (clickada == selecionada) {
            tabuleiro.unsetCasaSelecionada();
        }

        else {

        	if (!Regras.validaMovimento(selecionada, clickada,tabuleiro.getCasaFrente(), tabuleiro.getCasaEsquerda(), pontos)) {
        		setCasaFrenteAMover(selecionada.getLinha(), selecionada.getColuna());
        		// movimento invalido
        		atorJogador.getJanelaPrincipal().informaTabuleiroMovimentoIlegal();//TODO remover, apenas teste
        		
        		atorJogador.getJanelaPrincipal().atualizar();				
                return;
            }
        	
        	tabuleiro.moveAvatar(linha, coluna);
        	
            if (!modoOffline) {
                JogadaNy jogada = new JogadaNy();
                jogada.setJogadorLocal(this.jogadorAtual);
                jogada.setTabuleiro(this.tabuleiro);
                

                	System.out.println("!modoOffline conexao \r" + jogada.getTabuleiro().imprimirTabuleiro());

                    enviarJogada(montarJogada());

            }
            
            if (jogadorAtual.ehVencedor()) { // fim de jogo
                emAndamento = false;
            }
            else {
                if (modoOffline || jogadorAtual == jogadorLocal) {
                	//TODO nao era chamado no switch-case ??? Verificar necessidade de estar aqui
                    tabuleiro.sortearMoedas();
                }
            }
        } 
        
        atorJogador.getJanelaPrincipal().atualizar();
    }
    
    public void avaliarVirarEsquerda(int x, int y){
    	if ( (x == 0 && y == 3) || (x == 3 && y == 3) || (x == 3 && y == 6)){
    		setCasaEsquerdaMover(x, y);
    	}
    }

    public void liberaAnimal(Jogador jogador) {
        if (jogador != jogadorAtual || !emAndamento)
            return;
        
        if (jogador.getJaula().getQuantidade() > 0) {
            Casa casa = this.getTabuleiro().getCasa(6, 4);
            if (casa.getJogador() == null) {
                casa.setQtePecas(1);
                casa.setJogador(jogador);

                jogador.getJaula().setQuantidade(jogador.getJaula().getQuantidade() - 1);
                
                tabuleiro.setCasaSelecionada(6, 4);
                setCasaFrenteAMover(6, 4); //TODO, verificar se deve ficar aki realmente

                atorJogador.getJanelaPrincipal().atualizar();
            }
        }
    }
    
    public void clickMoeda() {
        System.out.println("Voce clicou nas moedas");

       if(emAndamento && jogadorAtual == jogadorLocal && tabuleiro.getPontos() == -1){
        	tabuleiro.sortearMoedas();
            atorJogador.getJanelaPrincipal().atualizar();
        }
        /*else if (faltaJgRemoto && emAndamento) { 11-06-2014//
//          primeiraJogada = false; 11-06-2014 nao sei se erro esta acontecendo aki
          tabuleiro.sortearMoedas();
          atorJogador.getJanelaPrincipal().atualizar();
      }*/
    }


    // callbacks do OuvidorProxy
     
//    public void tratarVitoria(){
//    	int intVitoria = dialogoVitoria();
//    	
//    	if (intVitoria == 1){
//    		atorJogador.inciarPartida();
//    	} else if (intVitoria == 2 ){
//            try {
//				conexao.desconectar();
//			} catch (NaoConectadoException e) {
//				e.printStackTrace();
//			}
//            setEhConectado(false);
//            setEmAndamento(false);
//
//            atorJogador.getJanelaPrincipal().atualizar();
//            atorJogador.getJanelaPrincipal().infomarResultadoConexao();
//    	}
//    }
    

    

    

    
    public int inciarPartida(Integer posicao){
//    18-05-2014 void para int, preciso do retorno para saber o q aconteceu
    	System.out.println("jogo.inciarPartida.posicao : " + posicao );
    	if(!emAndamento && conectado){
			atorNetGames.iniciarPartida();
			this.setModoOffline(false);
			this.setEmAndamento(true);
			atorJogador.getJanelaPrincipal().atualizar();
			
			return 6; //"O jogo iniciou " + nmJgdrLocal + ", e voce comeca jogando!"
		}
		
		if(!conectado){
			atorJogador.getJanelaPrincipal().atualizar();
			//TODO mensagem nao condiz com !contectado, pode ser q o nro 7 esteja certo... verificar
			return 7; //"Tentativa de inicio sem conexao previamente estabelecida."
		}
    	
    	System.out.println("nome jogador remoto : " + this.jogadorRemoto.getNome());
    	return 17; //"A partida corrente nÃ£o pode ser interrompida!"
    }
    
    
    
    public JogadaNy montarJogada(){
    	System.out.println("Jogo.montarJogada()");
    	JogadaNy jgda = new JogadaNy();
    	jgda.setJogadorLocal(this.jogadorLocal);
    	jgda.setJogadorRemoto(this.jogadorRemoto);
    	jgda.setTabuleiro(this.tabuleiro);
    	jgda.setMatrizTabuleiro(this.tabuleiro.criarMatrizDoTabuleiro());
    	//22-06-2014 problemas do restantes do jogador Remoto, vem sempre 4, nao consguindo definir se jogador remoto eh vencedor
    	jgda.setRestantesRemoto(this.jogadorLocal.getRestantes());
    	   	
    	jgda.setPassaVez(!tabuleiro.jogaNovamente() && !faltaJgRemoto && tabuleiro.getMoedas() > -1);//11-06-14 para trocar as moedas
    	verificaTrocaJogadorDaVez();
    	jgda.setPscDaVez(jogadorAtual.getPosicao());
    	
    	System.out.println(this.tabuleiro.imprimirTabuleiro());
    	//03-06-2014 envia o inteiro do avatar para comparacao
    	jgda.setAvtRemoto(this.getJogadorRemoto().getAvatar());
    	System.out.println("Jogo : montarJogada() > return jgda");
    	return jgda;
    }
    
    //11-06-2014
    public int verificaTrocaJogadorDaVez(){
    	int psc = -1;
    	if(!tabuleiro.jogaNovamente() && !faltaJgRemoto && tabuleiro.getMoedas() > -1){
    		//troca
    		if(jogadorAtual.getPosicao() == 1){
    			psc = 2;
    		}else if(jogadorAtual.getPosicao() == 2){
    			psc = 1;
    		}
    		setJogadorAtual(psc);
    	}
    	
    	return psc;
    }
    
    
    public void enviarJogada(JogadaNy jgd){
    	System.out.println("Jogo.enviarJogada()");
    	try {
    		System.out.println(jgd.getJogadorLocal().getNome() +  " -> enviando ...");
    		System.out.println(jgd.getTabuleiro().imprimirTabuleiro());
    		//TODO Inserir AtoNetGames
    		System.out.println("Jogo : enviarJogada(..) > atorNetGames.enviarJogada(jgd);");
			atorNetGames.enviarJogada(jgd);
			System.out.println(jgd.getJogadorLocal().getNome() +  " -> jogo.enviarJogada : Enviada!\n");
          
			atorJogador.getJanelaPrincipal().atualizar();
			
			if (jogadorLocal.ehVencedor()) { // fim de jogo
				System.out.println("Jogo : enviarJogada(..) > atorJogador.getJanelaPrincipal().dialogoVitoria()");
				atorJogador.getJanelaPrincipal().dialogoVitoria();				
            }
			
		} catch (Exception e) {
//			this.inciarPartida(0); //05-06-2014
			e.printStackTrace();
			System.out.println("catch (Exception e) : this.enviarJogada(0);");
		}
    }
    

    
    public void iniciarNovaPartida(Integer posicao) {
    	//    	TODO true apenas para teste, deve avaliar se conectado
    	System.out.println("Jogo : iniciarNovaPartida(posicao : "+ posicao +" )");
        conectado = true;
        
        jogadorLocal.setPosicao(posicao);
        
//        TODO POSICAO Q DIZ KEM INICIA
        
        System.out.println("Lcl : " + jogadorLocal.getNome() + "\t Cnx ( "+ posicao +" ) : " + atorNetGames.obterNomeAdversario(posicao) );
        if (posicao == 1 && jogadorLocal.getNome().equals(atorNetGames.obterNomeAdversario(posicao))) {
            jogadorAtual = jogadorLocal;
        } else {
            jogadorAtual = jogadorRemoto;
        }
        
        //20170628System.out.println("L : " + jogadorLocal.getNome() + "\t obterNomeAdversario(posicao): " + atorNetGames.obterNomeAdversario(1) + "\tA : " + jogadorAtual.getNome());
        System.out.println("Local :" + jogadorLocal.getPosicao() + " : " + jogadorLocal.getNome() + "\robterNomeAdversario(posicao): (" + jogadorRemoto.getPosicao() +") : " + jogadorRemoto.getNome() + "\rAtual : " + jogadorAtual.getNome());
        
//        tratarIniciarPartida
        System.out.println("Jogo : iniciarNovaPartida(..) > tabuleiro.esvaziarTabuleiro()");
        tabuleiro.esvaziarTabuleiro();
        System.out.println("Jogo : iniciarNovaPartida(..) > atorJogador.getJanelaPrincipal().atualizar()");
       // atorJogador.getJanelaPrincipal().atualizar();
//      recebe o inicio de jogo, aqui tentando atualizar jogador remoto
        System.out.println("Jogo : iniciarNovaPartida(..) > enviarJogada(montarJogada())");
        enviarJogada(montarJogada());
        
        if(posicao == 1){
        	System.out.println("iniciarNovaPartida - notificarResultado(6)");
        	atorJogador.getJanelaPrincipal().notificarResultado(6);//"O jogo iniciou " + nmJgdrLocal + ", e você começa jogando!");
        }
        else if(posicao == 2){
        	System.out.println("iniciarNovaPartida - notificarResultado(9)");
        	atorJogador.getJanelaPrincipal().notificarResultado(19); //"A partida comeÃ§ou! Aguarde a primeira jogada de " + partida.getJogadorRemoto().getNome()
        }

      
      Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "Nova partida: {0}", posicao);
    }

    //setinha
    public void finalizarPartidaComErro(String message) {
        conectado = false;
        Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "Finalizada: {0}", message);
    }

    
    public void receberMensagem(String msg) {
        Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "Mensagem: {0}", msg);
    }

    
    public void receberJogada(JogadaNy jog) {
    	System.out.println("Jogo.receberJogada()");
        JogadaNy jogada = (JogadaNy) jog;
               
        Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "receberJogada" );
        
        if (!emAndamento && !jogada.getJogadorLocal().ehVencedor()) {
            // reinicio de partida
            emAndamento = true;
            jogadorLocal.reinicia();
        }
        
        if(jogadorLocal.getJaula().getQuantidade() != jogada.getJogadorRemoto().getJaula().getQuantidade()){
        	jogadorLocal.setJaula(jogada.getJogadorRemoto().getJaula());
        }   	 
        
        // atualiza informacoes do jogador remoto 
        jogadorRemoto.setNome(jogada.getJogadorLocal().getNome());
        jogadorRemoto.setAvatar(jogada.getJogadorLocal().getAvatar());
        	if (jogadorRemoto.getAvatar() == jogadorLocal.getAvatar() && jogadorLocal.getPosicao() == 2) {
        		jogadorLocal.setAvatar((jogadorLocal.getAvatar()+1)%4);
        	}
        jogadorRemoto.setJaula(jogada.getJogadorLocal().getJaula());
        jogadorRemoto.setPosicao(jogada.getJogadorLocal().getPosicao());
        jogadorRemoto.setRestantes(jogada.getJogadorLocal().getRestantes());
        
        
        System.out.println("Jogo.receberJogada() > tabuleiro.criarTabuleiroDaMatriz(..)");
        tabuleiro.criarTabuleiroDaMatriz(jogada.getMatrizTabuleiro(), jogadorLocal, jogadorRemoto);
        
        System.out.println("receberJogada.jogada.getTabuleiro()\r" + jogada.getTabuleiro().imprimirTabuleiro());
        System.out.println("tabuleiro.imprimirTabuleiro()\r" + tabuleiro.imprimirTabuleiro());
//        tabuleiro = jogada.getTabuleiro(); // substituir por copia matriz
        

        
        tabuleiro.imprimirMatrizTabuleiro(jogada.getMatrizTabuleiro());
        tabuleiro.redefinirCasas(jogadorLocal, jogadorRemoto);
        System.out.println("receberJogada.tabuleiro.redefinirCasas\r" + tabuleiro.imprimirTabuleiro());
        
        
        setJogadorAtual(jogada.getPscDaVez());
        if(jogada.ehPassaVez()){
        	tabuleiro.setMoedas(-1);
        	tabuleiro.setPontos(-1);
        }
        
        atorJogador.getJanelaPrincipal().atualizar();
        
        System.out.println("jogo.receberJogada");
    	System.out.println("obterNomeAdversario 1: " +  atorNetGames.obterNomeAdversario(1));
    	System.out.println("obterNomeAdversario 2: " + atorNetGames.obterNomeAdversario(2));
    	System.out.println("jogadorRemoto.getNome(): " + jogadorRemoto.getNome());
    	System.out.println("jogadorAtual.getNome(): " + jogadorAtual.getNome());
       
    	//03-06-2014 Compara os ints (avatar) local e recebido, se diferente, envia jogada
    	int avtTmp = jogada.getAvtRemoto();
        System.out.println("avtTmp : " + avtTmp + "\tL.getAvatar : " + jogadorLocal.getAvatar());
        if(avtTmp != jogadorLocal.getAvatar()){
        	System.out.println("Jogo.receberJogada() > atorNetGames.enviarJogada(montarJogada())");
        	atorNetGames.enviarJogada(montarJogada());
        }else{
        	faltaJgRemoto = false; 
        }
        
        if (jogadorRemoto.ehVencedor()) { // fim de jogo
        	atorJogador.getJanelaPrincipal().dialogoDerrota();
            emAndamento = false;
        }

    }


    public void tratarConexaoPerdida() {
        Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "Desconectado" );
        
        conectado = false;
        emAndamento = false;
    }

    public void tratarPartidaNaoIniciada(String message) {
        Logger.getLogger(Jogo.class.getName()).log(Level.INFO, "Partida nao iniciada" );
        
        emAndamento = false;
        
        atorJogador.getJanelaPrincipal().atualizar();
    }
    
    
    public int getPontos(){
    	return tabuleiro.getPontos();
    }
    
    
    public void setCasaEsquerdaMover(int x, int y){
    	int pontos = getPontos();
    	int destXY = 0;
    	int l = x;
    	int c = y;
    	int al = x; // ala-linha
    	int ac = y; // ala-coluna
    	
    	for(int i = 0; i < pontos; i++){
    		destXY = Regras.informaProximaCasaEsquerda(l, c, al, ac);
    		
    		int dX = Regras.getLinhaXY(destXY);
    		int dY = Regras.getColunaXY(destXY);
    		
    		l = dX;
    		c = dY;
   
    	}
    	
    	if(pontos > 0){
    		tabuleiro.setCasaEsquerda(l, c);
    	}
    }
    
    
    public void setCasaFrenteAMover(int x, int y){
    	int pontos = getPontos();
    	int destXY = 0;
    	int l = x;
    	int c = y;
    	int al = x; // ala-linha
    	int ac = y; // ala-coluna
    	    	
    	for(int i = 0; i < pontos; i++){
    		destXY = Regras.informaProximaCasaFrente(l, c, al, ac);
    		
    		int dX = Regras.getLinhaXY(destXY);
    		int dY = Regras.getColunaXY(destXY);
    		
    		l = dX;
    		c = dY;
    	}
    	
    	if(pontos > 0){
//    		TODO - avaliar se roda realmente
    		tabuleiro.setCasaFrente(l, c);
    				
    	}

    }
    
    public boolean localEhVencedor(){
    	return jogadorLocal.ehVencedor();
    	
    }
    
    public void setJogadorRemoto(Jogador jogadorRemoto) {
		this.jogadorRemoto = jogadorRemoto;
	}

	public AtorNetGames getAtorNetGames() {
		return atorNetGames;
	}

	public void setAtorNetGames(AtorNetGames atorNetGames) {
		this.atorNetGames = atorNetGames;
	}

	
    
    
    
}