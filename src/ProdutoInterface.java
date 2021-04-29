import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProdutoInterface extends java.rmi.Remote, Serializable {

//métodos a ser usados no Impl
    //public void registarF(Produto p) throws java.rmi.RemoteException; //registar novo produto -> Fornecedor
    //adicionar stock -> Fornecedor
    //saída de stock -> Fornecedor
    //eliminar produto -> Fornecedor
    //consultar saidas -> Fornecedor

    //comprar produto -> Vendedor
    //consultar produto existente -> Vendedor
    //consultar compras feitas ao Fornecedor -> Vendedor

}
