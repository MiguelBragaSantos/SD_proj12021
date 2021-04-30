import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProdutoInterface extends java.rmi.Remote, Serializable {
// funçoes que queres usar no servidor, isto serve para o callback caso não faças podes tirar
//métodos a ser usados no Impl
    //public void registarF(Produto p) throws java.rmi.RemoteException; //registar novo produto -> Fornecedor
    //adicionar stock -> Fornecedor
    //saída de stock -> Fornecedor
    //eliminar produto -> Fornecedor
    //consultar saidas -> Fornecedor



}
