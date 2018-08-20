package nyout.modelo;

import java.io.Serializable;

public class Jogador implements Serializable{

    protected String nome;
    protected int avatar;
    protected Jaula jaula;
    protected int restantes; // quantos animais faltam escapar
    protected int pecasTotal;
    protected int posicao =-1; 

    public Jogador(String nome, int numeroPecas) {
        this.nome = nome;
        pecasTotal = numeroPecas;
        reinicia();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nm) {
        nome = nm;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avtrInt) {
        this.avatar = avtrInt;
    }

    public Jaula getJaula() {
        return jaula;
    }

    public void setJaula(Jaula j) {
        jaula = j;
    }

    public boolean ehVencedor() {
        return restantes == 0;
    }

    public void escapou(int qnt) {
        restantes -= qnt;
    }

    public int getRestantes() {
        return restantes;
    }

    public void setRestantes(int qnt) {
        restantes = qnt;
    }

    public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public final void reinicia() {
        jaula = new Jaula();
        jaula.setQuantidade(pecasTotal);
        restantes = pecasTotal;
    }
}