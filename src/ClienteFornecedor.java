import java.rmi.RemoteException;

/*
* Fornecedor - Produtos
* Vendedor - só Mobilia
*
* */


public class ClienteFornecedor extends java.rmi.server.UnicastRemoteObject implements ProdutoInterface { //subcalss de Cliente


    protected ClienteFornecedor() throws RemoteException {
    }

    public static void main(String[] args) {


    }



}
