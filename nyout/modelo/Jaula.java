package nyout.modelo;

import java.io.Serializable;

public class Jaula implements Serializable {

    protected int quantidade;
    
    public static int Limite = 4;
    
    public Jaula() {
        quantidade = Limite;
    }

    public void setQuantidade(int q) {
        quantidade = q;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
}