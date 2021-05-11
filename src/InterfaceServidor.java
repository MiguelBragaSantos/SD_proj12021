import java.rmi.RemoteException;
import java.util.ArrayList;

// funçoes do servidor
//      -> isto serve para o callback
public interface InterfaceServidor extends java.rmi.Remote{

    //registar produto
    /*
     *  fornecedor
     *  stock comprado -> lançar erro quando o stock baixar para stock minimo
     *  preço compra e venda
     * */
    public void RegistarProduto(ClassProduto c) throws java.rmi.RemoteException;
    void RegistarCompra(ClassOperacao oc) throws RemoteException;
    void RegistarVenda(ClassOperacao op) throws RemoteException;

    //entrada de stock (add/compra) FORNECEDOR
    public void ComprarProduto(String nomeProd, int dia, int mes, int ano, int add_stock) throws java.rmi.RemoteException;

    //saída de stock (venda) VENDEDOR
    void VenderProduto(String nomeProd, int dia, int mes, int ano, int sub_stock) throws RemoteException;

    //eliminar produto FORNECEDOR
    void EliminarProduto(String nome) throws RemoteException;

    //------
    //consultar produto existente VENDEDOR
    //por categoria, preço, stock ...
    public ClassProduto ConsultarProduto(String s) throws java.rmi.RemoteException;

    ArrayList<ClassProduto> ConsultarProdutoCategoria(int s) throws RemoteException;

    //? consultar saidas (vendas) VENDEDOR
    public ArrayList<ClassOperacao> ConsultarVendasProduto(String s) throws java.rmi.RemoteException;
    public ArrayList<ClassOperacao> ConsultarComprasProduto(String s) throws java.rmi.RemoteException;
    public ArrayList<ClassOperacao> ConsultarVendasCategoria(int s) throws RemoteException;
    public ArrayList<ClassOperacao> ConsultarComprasCategoria(int s) throws RemoteException;
    public ArrayList<ClassOperacao> ConsultarComprasFornecedor(String s) throws RemoteException;

    public ArrayList<ClassOperacao> ListarVendas() throws RemoteException;
    public ArrayList<ClassOperacao> ListarCompras() throws RemoteException;
    public ArrayList<ClassProduto> ListarProdutos() throws RemoteException;


        public ArrayList<ClassProduto> ConsultarProdutoPrecoVendaCresc() throws RemoteException;
    public ArrayList<ClassProduto> ConsultarProdutoPrecoVendaDesc() throws RemoteException;
    public ArrayList<ClassProduto> ConsultarProdutoPrecoCompraDesc() throws RemoteException;
    public ArrayList<ClassProduto> ConsultarProdutoPrecoCompraCresc() throws RemoteException;

    public ArrayList<ClassProduto> ConsultarProdutoStockCresc() throws RemoteException;
    public ArrayList<ClassProduto> ConsultarProdutoStockDesc() throws RemoteException;
}
