import java.rmi.RemoteException;

public interface InterfaceCliente extends java.rmi.Remote{

    //quando o stock baixa para o minimo (depois de uma venda)
    void NotifyClient(String s) throws RemoteException;
}
