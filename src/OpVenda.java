import java.io.Serializable;

//classe fechada!!
public class OpVenda extends ClassOperacao implements Serializable {
    private double preco;

    //Vender produto numa respetiva
    public OpVenda(int codigo, int dia, int mes, int ano, double preco){
        super(codigo,dia,mes,ano);
        this.preco=preco;
    }

    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco=preco;
    }
    public String toString() {
        String s= super.toString() + "\nPRECO "+ preco +"\n";
        return s;
    }

}
