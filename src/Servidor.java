import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

//servidor vai ser Impl
public class Servidor extends java.rmi.server.UnicastRemoteObject implements InterfaceServidor{

    private ArrayList<ClassProduto> Produtos = new ArrayList<>();
    private ArrayList<OpVenda> Vendas = new ArrayList<>();
    private ArrayList<OpCompra> Compras = new ArrayList<>();


    public Servidor() throws RemoteException {
        super();
    } // ,Runnable


    public static void main(String[] argv) {
        //tratar do RMI
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
    public synchronized void ComprarProduto(String nomeProd, int add_stock) throws RemoteException {
        //consultar se produto existe no registo - arraylist de Produtos
        ArrayList<ClassProduto> arrayListClone = (ArrayList<ClassProduto>) Produtos.clone();

        //OpCompra



        //alterar stock dp produto no arraylist
        for(int i=0;i<arrayListClone.size();i++){
            ClassProduto a = arrayListClone.get(i);
            if(a.getNome().equals(nomeProd)){
                //adicionar stock
                int tot = a.getStock() + add_stock;
                a.setStock(tot);

            }

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
    private synchronized static ArrayList<ClassProduto> inicializarProd() throws ClassNotFoundException {
        ArrayList<ClassProduto> aux=new ArrayList<ClassProduto>();
        try
        {
            FileInputStream fis = new FileInputStream("out.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            aux = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return aux;
    }

}
