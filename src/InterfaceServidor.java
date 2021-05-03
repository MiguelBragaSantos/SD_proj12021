import java.lang.reflect.Array;
import java.rmi.Remote;
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

    //entrada de stock (add/compra) FORNECEDOR
    public void ComprarProduto(ArrayList produtos, int add_stock) throws java.rmi.RemoteException;

    //saída de stock (venda) VENDEDOR
    public void VenderProduto() throws java.rmi.RemoteException;

    //eliminar produto FORNECEDOR
    public void EliminarProduto() throws java.rmi.RemoteException;

//------
    //consultar produto existente VENDEDOR
        //por categoria, preço, stock ...
    public ArrayList<ClassProduto> ConsultarProduto(String s) throws java.rmi.RemoteException;

    //? consultar saidas (vendas) VENDEDOR
    public ArrayList<ClassOperacao> ConsultarVendas(String s) throws java.rmi.RemoteException;

    //consultar compras feitas ao Fornecedor FORNECEDOR

}
