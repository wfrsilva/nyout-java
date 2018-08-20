package nyout.modelo;

public class JogadaNy implements br.ufsc.inf.leobr.cliente.Jogada {

    private static final long serialVersionUID = 1L;
    protected Jogador jogadorLocal;
    protected Jogador jogadorRemoto;
    protected Tabuleiro tabuleiro;
    protected int [][] matrizTabuleiro;
    
    //03-06-2014 Envia o inteiro do Avatar do Jogador Remoto para comparacao, sendo diferente, envia jogada novamente
    protected int avtRemoto;
    //09-06-2014 problemas com daVez, criando booleano para verificar
    protected boolean passaVez; 
    //11-06-2014 problemas de davez local ser diferente do davez remoto
    protected int pscDaVez;
    //22-06-2014 problemas do restantes do jogador Remoto, vem sempre 4, nao consguindo definir se jogador remoto eh vencedor
    protected int restantesRemoto;
    
    

    public JogadaNy() {
    }

    public Jogador getJogadorLocal() {
        return jogadorLocal;
    }

    public void setJogadorLocal(Jogador j) {
        jogadorLocal = j;
    }
    
    public Jogador getJogadorRemoto() {
        return jogadorRemoto;
    }

    public void setJogadorRemoto(Jogador j) {
        jogadorRemoto = j;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro t) {
        tabuleiro = t;
    }

	public int getAvtRemoto() {
		return avtRemoto;
	}

	public void setAvtRemoto(int avtRmt) {
		this.avtRemoto = avtRmt;
	}

	public int[][] getMatrizTabuleiro() {
		return matrizTabuleiro;
	}

	public void setMatrizTabuleiro(int[][] mtzTblr) {
		this.matrizTabuleiro = mtzTblr;
	}

	public boolean ehPassaVez() {
		return passaVez;
	}

	public void setPassaVez(boolean pssVz) {
		this.passaVez = pssVz;
	}

	public int getPscDaVez() {
		return pscDaVez;
	}

	public void setPscDaVez(int pscDV) {
		this.pscDaVez = pscDV;
	}

	public boolean isPassaVez() {
		return passaVez;
	}

	public int getRestantesRemoto() {
		return restantesRemoto;
	}

	public void setRestantesRemoto(int restantesRemoto) {
		this.restantesRemoto = restantesRemoto;
	}
}