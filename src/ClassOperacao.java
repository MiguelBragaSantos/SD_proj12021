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
    private int nOperacao;
    private static int ultimo;


    private int codigo; //código do produto
    //preços estão nas classes OpCompra e OpVenda

    public ClassOperacao(int codigo, int dia, int mes, int ano){
        ultimo++;
        nOperacao=ultimo;
        this.codigo=codigo;
        data=new GregorianCalendar(ano, mes, dia);
    }

    public ClassOperacao(int codigo){
        ultimo++;
        nOperacao=ultimo;
        this.codigo=codigo;
        GregorianCalendar data= new GregorianCalendar();
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

    public String toString(){
        String s = "DIA OPERACAO: "+ (data.getTime().getDate()) + "/" + (data.getTime().getMonth()+1) + "/" + (data.getTime().getYear()+1900) + " " ;
        s=s+ "\nN.OPERACAO "+ nOperacao + "\nOPERAÇÃO RELATIVA AO PRODUTO COM O CODIGO " + codigo;
        return s;
    }
}
