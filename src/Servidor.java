import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Servidor extends java.rmi.server.UnicastRemoteObject implements InterfaceServidor{

    private ArrayList<ClassProduto> Produtos = new ArrayList<>();
    private ArrayList<OpVenda> Vendas = new ArrayList<>();
    private ArrayList<OpCompra> Compras = new ArrayList<>();


    public Servidor() throws RemoteException {
        super();
    } // ,Runnable


    public static void main(String[] argv) {
        //tratar dos files
    }


    //FUNÇÕES DO INTERFACE SERVIDOR

    @Override
    public void RegistarProduto(ClassProduto c) throws RemoteException {
        ArrayList<ClassProduto> arraylist_clone = (ArrayList<ClassProduto>) Produtos.clone();
        arraylist_clone.add(c);
        Produtos= (ArrayList<ClassProduto>) arraylist_clone.clone();
        try {
            EscreverFileProd(Produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //add stock + registar compra
    @Override
    public void ComprarProduto(ArrayList produtos, int add_stock) throws RemoteException {

    }

    @Override
    public void VenderProduto() throws RemoteException {

    }

    @Override
    public void EliminarProduto() throws RemoteException {

    }

    @Override
    public ArrayList<ClassProduto> ConsultarProduto(String s) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<ClassOperacao> ConsultarVendas(String s) throws RemoteException {
        return null;
    }


    //FUNÇOES AUXILIARES

    private synchronized static void EscreverFileProd(ArrayList<ClassProduto> c) throws IOException{
        try{
            FileOutputStream fos = new FileOutputStream("outProduto.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
