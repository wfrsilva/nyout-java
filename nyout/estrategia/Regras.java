package nyout.estrategia;

import nyout.modelo.Casa;

public class Regras {

	public static boolean validaMovimento(Casa origem, Casa destino, Casa frente, Casa esquerda, int pontos){
		
		if (destino == frente || destino == esquerda){
			return true;
		}else{
			return validaMovimento( origem,  destino,  pontos); // TODO remover "return validaMovimento" , apenas teste alas
//			return false;
		}
		
	}
	
	public static boolean validaMovimento(Casa origem, Casa destino, int pontos){
		int ol = origem.getLinha();
		int oc = origem.getColuna();
		int dl = destino.getLinha();
		int dc = destino.getColuna();
		boolean validacao = false;
		
		int oriXY = converteXY(ol, oc) ;
				
		int desXY = converteXY(dl, dc); 
		
		switch(oriXY){
			case 12:
				validacao = validaOrigem12(desXY,pontos);
				break;
				
			case 47:
				validacao = validaOrigem47(desXY,pontos);
				break;			
				
			case 57:
				validacao = validaOrigem57(desXY,pontos);
				break;

			case 67:
				validacao = validaOrigem67(desXY,pontos);
				break;
				
			case 75:
				validacao = validaOrigem75(desXY,pontos);
				break;
				
			case 76:
				validacao = validaOrigem76(desXY,pontos);
				break;
				
		}
	
		return validacao;
	}
	


	
	
	public static boolean validaOrigem12(int desXY, int pt){
		//x=1,y=2 -> array[0][1]
		if( (desXY == 21 && pt == 1) ||
			(desXY == 31 && pt == 2) ||
			(desXY == 41 && pt == 3) ||
			(desXY == 51 && pt == 4) ||
			(desXY == 61 && pt == 5)	){
			return true;
		}
		return false;
	}

	
	public static boolean validaOrigem47(int desXY, int pt){ // Ala Leste
		//x=4, y=7 -> array[3][6]
		if(	(desXY == 37 && pt == 1) ||
			(desXY == 27 && pt == 2) ||
			(desXY == 16 && pt == 3) ||
			(desXY == 15 && pt == 4) ||
			(desXY == 14 && pt == 5) ||
			
			(desXY == 46 && pt == 1) ||
			(desXY == 45 && pt == 2) ||
			(desXY == 44 && pt == 3) ||
			(desXY == 43 && pt == 4) ||
			(desXY == 42 && pt == 5)	){
			return true;
		}
		return false;
	}
	
		
	public static boolean validaOrigem57(int desXY, int pt){
		
		if(	(desXY == 47 && pt == 1) ||
			(desXY == 37 && pt == 2) ||
			(desXY == 27 && pt == 3) ||
			(desXY == 16 && pt == 4) ||
			(desXY == 15 && pt == 5) ){
			return true;
		}
		return false;
	}
	
		
	public static boolean validaOrigem67(int desXY, int pt){
		
		if(	(desXY == 57 && pt == 1) ||
			(desXY == 47 && pt == 2) ||
			(desXY == 37 && pt == 3) ||
			(desXY == 27 && pt == 4) ||
			(desXY == 16 && pt == 5)	){
			return true;
		}
		return false;
	}
	
	
	
	public static boolean validaOrigem75(int desXY, int pt){
		if( 
			(desXY ==47) ||				//TODO, remover, apenas para teste das alas
			(desXY ==14) ||				//TODO, remover, apenas para teste das alas
			(desXY ==44) ||				//TODO, remover, apenas para teste das alas
			(desXY == 76 && pt == 1) ||
			(desXY == 67 && pt == 2) ||
			(desXY == 57 && pt == 3) ||
			(desXY == 47 && pt == 4) ||
			(desXY == 37 && pt == 5)	){
			return true;
		}
		return false;
	}
	
	public static boolean validaOrigem76(int desXY, int pt){
		
		if(	(desXY == 67 && pt == 1) ||
			(desXY == 57 && pt == 2) ||
			(desXY == 47 && pt == 3) ||
			(desXY == 37 && pt == 4) ||
			(desXY == 27 && pt == 5) 	){
			return true;
		}
		return false;
	}
	
	public static int informaProximaCasaEsquerda(int x, int y, int ax, int ay){
		int anteriorXY = converteXY(x,y);
		int slcndXY = converteXY(ax, ay);
							
		return informaProximaCasaEsquerdaAla(anteriorXY, slcndXY);
	}
	
	public static int informaProximaCasaEsquerdaAla(int anteriorXY, int slcndXY){
		
		int esqXY = 0;
		
		switch(anteriorXY){
		case 14:
			esqXY = 24;
			break;
		case 44:
			//slcnd = selecionada
			if(slcndXY == 45 || slcndXY == 46 || slcndXY == 47){
				esqXY = 43;
			}else if(slcndXY == 14 || slcndXY == 24 || slcndXY == 34){
				esqXY = 54;
			}
			
			if(slcndXY == 44){
				esqXY = 54;
			}
			break;
		case 47:
			esqXY = 46;
			break;
		default:

			esqXY = informaProximaCasaFrente(anteriorXY, anteriorXY);
		}
		return esqXY;
	}
	
	
	public static int informaProximaCasaFrente(int ox, int oy, int ax, int ay){
		int anteriorXY = converteXY(ox,oy);
		int slcndXY = converteXY(ax, ay);
		
		return informaProximaCasaFrente(anteriorXY, slcndXY);
	}
	
	public static int informaProximaCasaFrente(int anteriorXY, int slcndXY){
		int frtXY = 0;
		
		switch(anteriorXY){
		case 12:
			frtXY = 21;
			break;
		case 13:
			frtXY = 12;
			break;
		case 14:
			frtXY = 13;
			break;
		case 15:
			frtXY = 14;
			break;			
		case 16:
			frtXY = 15;
			break;
		case 21:
			frtXY = 31;
			break;
		case 24:
			frtXY = 34;
			break;
		case 27:
			frtXY = 16;
			break;
		case 31:
			frtXY = 41;
			break;
		case 34:
			frtXY = 44;
			break;
		case 37:
			frtXY = 27;
			break;
		case 41:
			frtXY = 51;
			break;
		case 42:
			frtXY = 41;
			break;
		case 43:
			frtXY = 42;
			break;
		case 44:
//			if(anteriorXY == 14 || anteriorXY == 24 || anteriorXY == 34 ){
//				frtXY = 54;
//			}
//			else if(anteriorXY == 47 || anteriorXY == 46 || anteriorXY == 45 || anteriorXY == 44){
//				frtXY = 43;
//			}
			if(slcndXY == 44 || slcndXY == 45 || slcndXY == 46 || slcndXY == 47){ 
				frtXY = 43;
			}else if(slcndXY == 14 || slcndXY == 24 || slcndXY == 34){
				frtXY = 54;
			}
			break;
		case 45:
			frtXY = 44;
			break;
		case 46:
			frtXY = 45;
			break;
		case 47:
			frtXY = 37;
			break;
		case 51:
			frtXY = 61;
			break;
		case 54:
			frtXY = 64;
			break;
		case 57:
			frtXY = 47;
			break;
		case 61:
			frtXY = 72;
			break;
		case 64:
			frtXY = 74;
			break;
		case 67:
			frtXY = 57;
			break;
		case 72:
			frtXY = 73;
			break;
		case 73:
			frtXY = 74;
			break;
		case 74:
			frtXY = 74; //saida
			break;
		case 75:
			frtXY = 76;
			break;
		case 76:
			frtXY = 67;
			break;
		}
		return frtXY;

	}
	
	public static int getLinhaXY(int nroXY){
		// processo inverso - ((x + 1) * 10) + (y + 1); //6, 7  [5][6]
		System.out.println("\rx: (" + nroXY + "-11)/10 = " + (nroXY - 11) /10);
		return (nroXY - 11) /10;
	}
	
	
	public static int getColunaXY(int nroXY){
		System.out.println("y: (" + nroXY + "-11)%10 = " + (nroXY - 11)%10);
		return (nroXY - 11)%10;
	}
	

	public static int converteXY(int x, int y){
		return ((x + 1) * 10) + (y + 1);
	}
	
}
