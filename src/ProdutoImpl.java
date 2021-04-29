

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ProdutoImpl extends java.rmi.server.UnicastRemoteObject implements ProdutoInterface {

    public static ArrayList<Produto> listaProdutos = new ArrayList<>(); //lista com todos os produtos do fornecedor
    public static ArrayList<Produto> listaMobilia = new ArrayList<>(); //lista com todos os produtos Mobilia do vendedor


    protected ProdutoImpl(String nome) throws RemoteException {
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

    @Override
    public void registarF(Produto p) throws RemoteException {

    }



}
