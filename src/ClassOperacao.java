import java.io.Serializable;
import java.util.GregorianCalendar;

/*Uma operação pode ser de entrada(compra)
* ou se saída(venda)
 */

//classe fechada! ? - criar parametro de influencia de stock
    /* Fornecedor/Vendedor-efetuam operação->
            Venda ou compra-> -stock / +stock
 */
public class ClassOperacao implements Serializable {

    private GregorianCalendar data;
    private int nOperacao;          //nr do recido
    private static int ultimo;

    //private ClassProduto produto;
    private int codigo; //código do produto

    //preços estão nas classes OpCompra e OpVenda
    //private int opStock; //stock a ser adicionado ou removido


    public ClassOperacao(ClassProduto produto, int dia, int mes, int ano){
        ultimo++;
        nOperacao=ultimo;
        codigo=produto.getId();
        data=new GregorianCalendar(ano, mes, dia);
    }


    public GregorianCalendar getData() {
        return data;
    }
    public void setData(GregorianCalendar data) {
        this.data = data;
    }
    public int getNOperacao(){
        return nOperacao;
    }
    public void setNOperacao(int nOperacao){
        this.nOperacao=nOperacao;
    }
    public int getUltimo(){
        return ultimo;
    }
    public void setUltimo(int ultimo){
        this.ultimo=ultimo;
    }
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    public int getCodigo(){
        return codigo;
    }


    public String toString(int st){
        String s = "\n\nDATA OPERACAO: "+ (data.getTime().getDate()) + "/" + (data.getTime().getMonth()) + "/" + (data.getTime().getYear()+1900) + " " ;
        s=s+ "\nN.OPERACAO "+ nOperacao + "\nOPERAÇÃO RELATIVA AO PRODUTO COM O CODIGO " + codigo + "\nQUANTIDADE COMPRADA/VENDIDA = " + st;
        return s;
    }
}
