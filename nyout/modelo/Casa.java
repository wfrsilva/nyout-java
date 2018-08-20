package nyout.modelo;

import java.io.Serializable;

public class Casa implements Serializable{

	private static final long serialVersionUID = 1L;
	protected int linha;
    protected int coluna;
    protected int quantidadePecas;   
    protected int tipoDaCasa;
    protected int posicaoJogador = 0;
    
    public enum Sentido {
        NENHUM, NORTE, SUL, LESTE, OESTE
    }
    
    protected Sentido sentidoMovimento;
    
    protected transient Jogador jogador;

    public Casa(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        jogador = null;
        sentidoMovimento = Sentido.NENHUM;
    }

    public boolean ocupada() {
        return jogador != null;
    }

    public void setQtePecas(int nro) {
        this.quantidadePecas = nro;
    }



    public int getTipoDaCasa() {
        return this.tipoDaCasa;
    }

    /**
     *
     * @param tipoDaCasa
     */
    public void setTipoDaCasa(int tipoDaCasa) {
        this.tipoDaCasa = tipoDaCasa;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public Jogador getJogador() {
    	return jogador;
    }
    
    public void setJogador(Jogador j) {
        jogador = j;
        posicaoJogador = j.getPosicao();
    }

    public void limpar() {
        jogador = null;
        quantidadePecas = 0;
        posicaoJogador = 0;
        sentidoMovimento = Sentido.NENHUM;
    }
    
    public void setSentidoMovimento(Sentido m) {
        sentidoMovimento = m;
    }
    
    public Sentido getSentidoMovimento() {
        return sentidoMovimento;
    }
    
    public void redefineJogador(int posicao, Jogador jgd){
    	if(posicaoJogador == posicao){
    		jogador = jgd;
    	}
    }

}