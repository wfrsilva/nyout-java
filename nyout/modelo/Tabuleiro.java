package nyout.modelo;
/*Windows > Preferences > General > Workspaces, set "Text file encoding" to "Other : UTF-8".*/
import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Tabuleiro implements Serializable{

	public static final long serialVersionUID = 1L;

	protected Casa casas[][];
	
	protected transient Casa selecionada;
    protected transient Casa frente;
    protected transient Casa esquerda;
    
    protected int pontos;
    protected int moedas;
    public static int Linhas = 7;
    public static int Colunas = 7;
    
    public Tabuleiro() {
    	
    }
    
    public void resetar() {

        casas = new Casa[Linhas][Colunas];
        selecionada = null;
        frente = null;
        pontos = -1;
        moedas = -1;

        for (int linha = 0; linha < Linhas; linha++) {
            for (int coluna = 0; coluna < Colunas; coluna++) {
                casas[linha][coluna] = new Casa(linha, coluna);
            }
        }
    }
    
    public void redefinirCasas(Jogador local, Jogador remoto){
    	for (int linha = 0; linha < Linhas; linha++) {
            for (int coluna = 0; coluna < Colunas; coluna++) {
                casas[linha][coluna].redefineJogador(local.getPosicao(), local);
                casas[linha][coluna].redefineJogador(remoto.getPosicao(), remoto);
            }
        }
    }

    public void esvaziarTabuleiro() {
//    	System.out.println("\rINICIO esvaziarTabuleiro()\r" + imprimirTabuleiro());
    	
        for (int linha = 0; linha < Linhas; linha++) {
            for (int coluna = 0; coluna < Colunas; coluna++) {
                casas[linha][coluna] = new Casa(linha, coluna);
            }
        }
        
//        System.out.println("\rFIM esvaziarTabuleiro()\r" + imprimirTabuleiro());
    }
    
    public int[][] criarMatrizDoTabuleiro(){
    	System.out.println("Tabuleiro : criarMatrizDoTabuleiro");

    	Casa casas[][] =  this.getCasas();
    	int matrizTab[][] = new int [casas.length][casas[0].length];
    	    	 
		for (int linha = 0; linha < casas.length; linha++) {
            for (int coluna = 0; coluna < casas[0].length; coluna++) {
            	if(casas[linha][coluna].ocupada()){
            		int mPsc = this.casas[linha][coluna].getJogador().getPosicao() * 100; //Matriz Posicao 
                	int mAvt = this.casas[linha][coluna].getJogador().getAvatar() * 10; // Matriz Avatar 
            		int mQtd = casas[linha][coluna].getQuantidadePecas();
            		
            		matrizTab[linha][coluna] = (mPsc + mAvt + mQtd);
            		System.out.println("[" + linha + "][" + coluna + "]: " + mPsc + " + " + mAvt + " + " + mQtd + " = " + matrizTab[linha][coluna]);
            	}else{
            		matrizTab[linha][coluna] = 0;
            	}
            }
        }
		
		return  matrizTab;
    }
    
    public void imprimirMatrizTabuleiro(int[][] mtz){
    	for (int linha = 0; linha < mtz.length; linha++) {
            for (int coluna = 0; coluna < mtz[0].length; coluna++) {
            	if(mtz[linha][coluna] != 0){
            		System.out.println("[" + linha + "][" + coluna + "]: " + mtz[linha][coluna]);
            	}
            }
        }
    }
    
    public void criarTabuleiroDaMatriz(int[][] mtz, Jogador jogadorLocal, Jogador jogadorRemoto){
    	for (int linha = 0; linha < mtz.length; linha++) {
            for (int coluna = 0; coluna < mtz[0].length; coluna++) {
            	if(mtz[linha][coluna] != 0){
            		
            		int psc = converterPosicao(mtz[linha][coluna]);
            		int avt = converterAvatar(mtz[linha][coluna]);
            		int qdt = converteQuantidade(mtz[linha][coluna]);
            		int pLc = jogadorLocal.getPosicao();
            		int pRm = jogadorRemoto.getPosicao();
            		
            		if(psc == pLc){
            			this.casas[linha][coluna].setJogador(jogadorLocal);
            		//TODO problemas para comparar com tipo, esta retornando -1
            		}else if (avt == jogadorRemoto.getAvatar()){
            			this.casas[linha][coluna].setJogador(jogadorRemoto);
            		}
            		            		
            		this.casas[linha][coluna].setQtePecas(qdt);
            		
            		System.out.println("\rcriarTabuleiroDaMatriz : [" + linha + "][" + coluna + "]: " + mtz[linha][coluna]);
            		System.out.println("psc: " + psc + "\t avt: " + avt + "\t qdt: " + qdt);
            	}else{
            		casas[linha][coluna] = new Casa(linha, coluna);
            	}
            }
        }
    }
    

	public int converterPosicao(int nroMtr){
		int psc = nroMtr /100;
		System.out.println(nroMtr + " / 100 = " + psc );
		return psc;
	}
	
	public int converterAvatar(int nroMtr){
		int avt;
		int temp = nroMtr;
		
		do{
			temp-=100;
		}while(temp > 100);
		
		avt = temp/10;
		System.out.println(temp + " / 10 = " + avt );
		return avt;
	}
	
	public int converteQuantidade(int nroMtr){
		int qtd;
		int temp = nroMtr;
		
		do{
			temp-=10;
		}while(temp > 10);
		
		qtd = temp;
		
		return qtd;
	}

	

    public String imprimirTabuleiro(){
		String printTab = "";
		String tts;
        int avt,qtd ;
        
		for (int linha = 0; linha < casas.length; linha++) {
            for (int coluna = 0; coluna < casas[0].length; coluna++) {
                if (casas[linha][coluna].ocupada()) {
                    avt = casas[linha][coluna].getJogador().getAvatar() * 10;
                    qtd = casas[linha][coluna].getQuantidadePecas();
                    tts = "" + (avt + qtd);
                    
                }else{
                	tts = "--";
                }
                
                if(coluna < casas[0].length -1){
                	printTab = printTab + tts + " | ";
                }else if (coluna == casas[0].length -1 ){
                	printTab = printTab + tts + "\r";
                }
                
            }
//            System.out.println(printTab);
        }
        System.out.println("Tabuleiro > imprimirTabuleiro() > return printTab");
        printTab = " 28/06/2017 - Aqui deveria imprimir matriz, temporariamente desligada (para nao poluir muito o LOG)";
    	return printTab;
    	
    }
    
    public void sortearMoedas() {
    	/* 2017-05-11 atualziado metodo para respeitar a estatistica 
        //moedas = new Random(System.currentTimeMillis()).nextInt(5);
    	//System.out.println("moedas (caras): " + moedas + "\tpontos: " + getPontos());
    	  */
    	 
        
        int nroCaras = 0;
        
        for(int i=0; i<4; i++){
        	boolean face = sortearCaraCoroa();
        	        	
        	if(face){
        		nroCaras++;
        	}
        	
        	System.out.println("face = rdm.nextBoolean(): " + face + "\t\tCaras : " + nroCaras);
        }
        
        setMoedas(nroCaras);
        converteCarasPontos(nroCaras);
        
        //
    }
    
    public boolean sortearCaraCoroa(){
    	Random rdm = new Random();
    	return rdm.nextBoolean();
    }
    
    
    private void converteCarasPontos(int nroCaras) {
		
		switch(nroCaras){
		case 0:
			//nyout
			pontos = 4;
			break;
			
		case 1:
			//kel
			pontos = 3;
			break;
			
		case 2:
			//ka
			pontos = 2;
			break;
			
		case 3:
			//to
			pontos = 1;
			break;
			
		case 4:
			//mo
			pontos = 5;
			break;
			
		}//switch
		
	}//converteCarasPontos

	public boolean jogaNovamente() {
//        return moedas == 0 || moedas == 4;// TODO 11-06-2014 NAO SERA IMPLANTADO JOGAR NOVAMENTE
    	return pontos == 99;
    }

    public int getMoedas() {
        return moedas;
    }
    
    public void setMoedas(int mds){
    	this.moedas = mds;
    }
    
    public void setPontos(int p) {
    	this.pontos = p;
    }

    public int getPontos() {
/*	a.	4 caras: 5 pontos + outro lance - mo		 
	b.	4 coroas: 4 pontos + outro lance  - nyout
	c.	3 coroas: 3 pontos - kel 
	d.	2 coroas: 2 pontos - ka	*/
        return pontos;        
    }

    public Casa getCasa(int l, int c) {
        return casas[l][c];
    }
    
    public void setCasa(Casa cs, int l, int c){
    	this.casas[l][c] = cs;
    }

    public Casa getCasaSelecionada() {
        return selecionada;
    }

    public void setCasaSelecionada(int l, int c) {
        selecionada = casas[l][c];
    }
    
    public Casa getCasaFrente() {
        return frente;
    }

    public void setCasaFrente(int l, int c) {
        frente = casas[l][c];
    }
    
    public Casa getCasaEsquerda(){
    	return esquerda;
    }
    
    public void setCasaEsquerda(int l, int c){
    	esquerda = casas[l][c];
    }

    public void moveAvatar(int linha, int coluna) {
        Casa destino = casas[linha][coluna];

       

        
        
        if (linha == 6 && coluna == 3) { // saida
            selecionada.getJogador().escapou(selecionada.getQuantidadePecas());
        } else if (destino.getJogador() == selecionada.getJogador()) {
            destino.setQtePecas(destino.getQuantidadePecas() + selecionada.getQuantidadePecas());
        } else {
            if (destino.getJogador() != null) {
                destino.getJogador().getJaula().setQuantidade(
                        destino.getJogador().getJaula().getQuantidade()
                        + destino.getQuantidadePecas());
            }
            destino.setQtePecas(selecionada.getQuantidadePecas());
            destino.setJogador(selecionada.getJogador());
        }

        selecionada.limpar();
        selecionada = null;
        frente = null;
        esquerda = null;
        

        

    }

    public void unsetCasaSelecionada() {
        if (selecionada.getLinha() == 6 && selecionada.getColuna() == 4) {
        	System.out.println("não é possível deselecionar a casa inicial");
            return; // não é possível de-selecionar a casa inicial alterar para utf-8
            /*Windows > Preferences > General > Workspaces, set "Text file encoding" to "Other : UTF-8".*/
        }
        
        unsetCasaFrente();
        unsetCasaEsquerda();
        
        selecionada = null;
        frente = null;
        esquerda = null;
    }

    public void unsetCasaEsquerda(){
    	if(selecionada == null){
    		esquerda = null;
    	}
    }
    
    public void unsetCasaFrente(){
    	if(selecionada == null){
    		frente = null;
    	}
    }

	public Casa[][] getCasas() {
		return casas;
	}

	public void setCasas(Casa[][] casas) {
		this.casas = casas;
	}

}