import java.io.Serializable;

public class OpVenda extends ClassOperacao implements Serializable {

    private double tot_preco; //+ preço total da operação :: multiplicar preço do produto com qunatidade a comprar/vender
    private int outStock; //quantia da saída de stock


    public OpVenda(ClassProduto produto, int dia, int mes, int ano, int outStock){
        super(produto,dia,mes,ano);
        this.outStock=outStock;
        tot_preco= outStock * produto.getPreco_venda();
    }

    public double getTot_preco(){
        return tot_preco;
    }
    /*public void setTot_preco(double tot_preco){
        this.tot_preco=tot_preco;
    }*/
    public int getOutStock(){
        return outStock;
    }
    public void setOutStock(int outStock){
        this.outStock=outStock;
    }

    public String toString() {
        String s= super.toString() + "\nPRECO "+ tot_preco +"\n";
        return s;
    }

}
