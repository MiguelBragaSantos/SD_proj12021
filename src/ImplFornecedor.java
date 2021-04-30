

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ImplFornecedor extends java.rmi.server.UnicastRemoteObject implements InterfaceFornecedor {

    public static ArrayList<ClassProduto> listaClassProdutos = new ArrayList<>(); //lista com todos os produtos do fornecedor
    public static ArrayList<ClassProduto> listaMobilia = new ArrayList<>(); //lista com todos os produtos Mobilia do vendedor


    protected ImplFornecedor(String nome) throws RemoteException {
        super();
        try {
            Naming.rebind(nome, this);
        } catch (Exception e) {
            if (e instanceof RemoteException) {
                throw (RemoteException) e;
            } else {
                throw new RemoteException(e.getMessage());
            }
        }

    }

    /*@Override
    public void registarF(Produto p) throws RemoteException {

    }*/

}
