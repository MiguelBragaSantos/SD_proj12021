import java.io.Serializable;
import java.rmi.RemoteException;

//funções do cliente
public interface InterfaceCliente extends java.rmi.Remote{

    //quando o stock baixa para o minimo (depois de uma venda)
    void NotifyClient(String s) throws RemoteException;
}
