package nyout.interfaceGrafica;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Imagens {
	public static Icon ajuda = new ImageIcon(Imagens.class.getResource("/imagens/help_25.png"));
    public static Icon alaDestaque = new ImageIcon(Imagens.class.getResource("/imagens/ala_destaque.png"));
    public static Icon alaVazia = new ImageIcon(Imagens.class.getResource("/imagens/ala_vazia.png"));
    public static Icon casaDestaque = new ImageIcon(Imagens.class.getResource("/imagens/casa_destaque.png"));
    public static Icon casaInativa = new ImageIcon(Imagens.class.getResource("/imagens/casa_inativa.png"));
    public static Icon casaVazia = new ImageIcon(Imagens.class.getResource("/imagens/casa_vazia.png"));
    public static Icon casa1grfa = new ImageIcon(Imagens.class.getResource("/imagens/casa_1grfa.png"));
    public static Icon casa1hptm = new ImageIcon(Imagens.class.getResource("/imagens/casa_1hptm.png"));
    public static Icon casa1leao = new ImageIcon(Imagens.class.getResource("/imagens/casa_1leao.png"));
    public static Icon casa1zbra = new ImageIcon(Imagens.class.getResource("/imagens/casa_1zbra.png"));
    public static Icon conectar = new ImageIcon(Imagens.class.getResource("/imagens/conectar_25.png"));
    public static Icon comoJogar25 = new ImageIcon(Imagens.class.getResource("/imagens/como_jogar_25.png"));
    public static Icon derrota = new ImageIcon(Imagens.class.getResource("/imagens/perdeu.png"));
    public static Icon derrota25 = new ImageIcon(Imagens.class.getResource("/imagens/derrota_25.png"));
    public static Icon desconectar = new ImageIcon(Imagens.class.getResource("/imagens/desconectar_25.png"));
    public static Icon inicioVazia = new ImageIcon(Imagens.class.getResource("/imagens/inicio_vazia.png"));
    public static Icon iniciar = new ImageIcon(Imagens.class.getResource("/imagens/iniciar_25.png"));
    public static Icon jaulaFloresta = new ImageIcon(Imagens.class.getResource("/imagens/jaula_floresta.png"));
    public static Icon jaulaGirafa = new ImageIcon(Imagens.class.getResource("/imagens/jaula_grfa.png"));
    public static Icon jaulaHipo = new ImageIcon(Imagens.class.getResource("/imagens/jaula_hptm.png"));
    public static Icon jaulaLeao = new ImageIcon(Imagens.class.getResource("/imagens/jaula_leao.png"));
    public static Icon jaulaVazia = new ImageIcon(Imagens.class.getResource("/imagens/jaula_vazia.png"));
    public static Icon jaulaZebra = new ImageIcon(Imagens.class.getResource("/imagens/jaula_zebra.png"));
    public static Icon jogo = new ImageIcon(Imagens.class.getResource("/imagens/play_icon2.png"));
    public static Icon listar = new ImageIcon(Imagens.class.getResource("/imagens/listar_25.png"));
    public static Icon mapaTabuleiroAlas = new ImageIcon(Imagens.class.getResource("/imagens/mapa_tabuleiro_alas.png"));
    public static Icon mapaTabuleiroAlas400 = new ImageIcon(Imagens.class.getResource("/imagens/mapa_tabuleiro_alas400.png"));
    public static Icon mapaTabuleiroNormal = new ImageIcon(Imagens.class.getResource("/imagens/mapa_tabuleiro_normal.png"));
    public static Icon mapaTabuleiroNormal400 = new ImageIcon(Imagens.class.getResource("/imagens/mapa_tabuleiro_normal400.png"));
    public static Icon moedaCara = new ImageIcon(Imagens.class.getResource("/imagens/cara.png"));
    public static Icon moedaCoroa = new ImageIcon(Imagens.class.getResource("/imagens/coroa.png"));
    public static Icon moedaClick = new ImageIcon(Imagens.class.getResource("/imagens/moeda_click.png"));
    public static Icon moedaClickAnm = new ImageIcon(Imagens.class.getResource("/imagens/moeda_cl-aq_anm.gif"));
    public static Icon moedaInativa = new ImageIcon(Imagens.class.getResource("/imagens/moeda_inativa.png"));
    public static Icon on = new ImageIcon(Imagens.class.getResource("/imagens/on.png"));
    public static Icon opcoes = new ImageIcon(Imagens.class.getResource("/imagens/opcoes_25.png"));
    public static Icon off = new ImageIcon(Imagens.class.getResource("/imagens/off.png"));
    public static Icon peoes25 = new ImageIcon(Imagens.class.getResource("/imagens/peoes_25.png"));
    public static Icon play1 = new ImageIcon(Imagens.class.getResource("/imagens/play_icon.png"));
    public static Icon play2 = new ImageIcon(Imagens.class.getResource("/imagens/play_icon2.png"));
    public static Icon regras25 = new ImageIcon(Imagens.class.getResource("/imagens/regras_25.png"));
    public static Icon saidaVazia = new ImageIcon(Imagens.class.getResource("/imagens/saida_vazia.png"));
    public static Icon saidaDestaque = new ImageIcon(Imagens.class.getResource("/imagens/saida_destaque.png"));
    public static Icon sobre25 = new ImageIcon(Imagens.class.getResource("/imagens/sobre_25.png"));
    public static Icon sobreBack = new ImageIcon(Imagens.class.getResource("/imagens/sobreBack.png"));
    public static Icon telaInicial = new ImageIcon(Imagens.class.getResource("/imagens/tela_iniciar_jogo.png"));
    public static Icon telaInicialh900 = new ImageIcon(Imagens.class.getResource("/imagens/tela_iniciar_jogo_h900.png"));
    public static Icon ufsc = new ImageIcon(Imagens.class.getResource("/imagens/logoUfsc.png"));
    public static Icon vazio50 = new ImageIcon(Imagens.class.getResource("/imagens/vazio_50.png"));
    public static Icon vitoria = new ImageIcon(Imagens.class.getResource("/imagens/venceu.png"));
    public static Icon vitoria25 = new ImageIcon(Imagens.class.getResource("/imagens/vitoria_25.png"));
    public static Icon zoologico = new ImageIcon(Imagens.class.getResource("/imagens/zoologico.png"));

    
    public static Icon getAvatarCasa(int id) {

        if (id == 1) {
            return casa1leao;
        } else if (id == 2) {
            return casa1zbra;
        } else if (id == 3) {
            return casa1grfa;
        } else if (id == 4) {
            return casa1hptm;
        } else {
            return casaVazia;
        }

    }
    
    
    public static Icon getAvatarJaula(int id) {

        if (id == 1) {
            return jaulaLeao;
        } else if (id == 2) {
            return jaulaZebra;
        } else if (id == 3) {
            return jaulaGirafa;
        } else if (id == 4) {
            return jaulaHipo;
        } else {
            return jaulaVazia;
        }

    }
    
}
