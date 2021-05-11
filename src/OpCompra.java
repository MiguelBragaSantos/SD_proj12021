import java.io.Serializable;

public class OpCompra extends ClassOperacao implements Serializable {

    private double tot_preco; //- preço total da operação :: multiplicar preço do produto com qunatidade a comprar/vender
    private int inStock; //quantia da entrada de stock

    public OpCompra(ClassProduto produto, int dia, int mes, int ano, int inStock){
        super(produto,dia,mes,ano);
        this.inStock=inStock;
        tot_preco= inStock * produto.getPreco_compra();
    }

    public double getTot_preco(){
        return tot_preco;
    }

    public int getInStock(){
        return inStock;
    }
    public void setInStock(int inStock){
        this.inStock=inStock;
    }

    public String toString() {
        int a = getInStock();
        String s = super.toString(a);
        return s;
    }

}
