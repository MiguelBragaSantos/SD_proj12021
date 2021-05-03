import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

//servidor vai ser Impl
public class Servidor extends java.rmi.server.UnicastRemoteObject implements InterfaceServidor{

    private ArrayList<ClassProduto> Produtos = new ArrayList<>();
    private ArrayList<ClassOperacao> Vendas = new ArrayList<>();          //deviam ser do tipo da subclasse ou da super? agora ficou super: ClassOperacao
    private ArrayList<ClassOperacao> Compras = new ArrayList<>();

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
    @Override
    public void RegistarCompra(ClassOperacao oc) throws RemoteException {
        ArrayList<ClassOperacao> arraylist_clone = (ArrayList<ClassOperacao>) Compras.clone();
        arraylist_clone.add(oc);
        Compras= (ArrayList<ClassOperacao>) arraylist_clone.clone();
        try {
            EscreverFileCompras(Compras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void RegistarVenda(ClassOperacao op) throws RemoteException {
        ArrayList<ClassOperacao> arraylist_clone = (ArrayList<ClassOperacao>) Vendas.clone();
        arraylist_clone.add(op);
        Vendas= (ArrayList<ClassOperacao>) arraylist_clone.clone();
        try {
            EscreverFileVendas(Vendas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //add stock + registar compra
    @Override
    public synchronized void ComprarProduto(String nomeProd, int dia, int mes, int ano, int add_stock) throws RemoteException {
        //!!!!! falta consultar se produto existe no registo - arraylist de Produtos
        ArrayList<ClassProduto> arrayListClone = (ArrayList<ClassProduto>) Produtos.clone();

        for (int i = 0; i < arrayListClone.size(); i++) {
            ClassProduto a = arrayListClone.get(i);
            if (a.getNome().equals(nomeProd)) {                 //buscar objeto com o nome nomeProd
                //registar Compra
                OpCompra o = new OpCompra(a,dia, mes,ano,add_stock); //objeto compra criado e inicializado
                RegistarCompra(o);

                //adicionar stock no arraylist
                int tot = a.getStock() + add_stock;
                a.setStock(tot);
                arrayListClone.set(i,a);    //alterar objeto atualizado (com novo stock) no arraylist
                Produtos= (ArrayList<ClassProduto>) arrayListClone.clone();
                try {
                    EscreverFileProd(Produtos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            else{
                System.out.println("Produto não encontrado");
                break;
            }
        }
    }

    @Override
    public synchronized void VenderProduto(String nomeProd, int dia, int mes, int ano, int sub_stock) throws RemoteException {
        //consultar se produto existe no registo - arraylist de Produtos
        ArrayList<ClassProduto> arrayListClone = (ArrayList<ClassProduto>) Produtos.clone();

        for (int i = 0; i < arrayListClone.size(); i++) {
            ClassProduto a = arrayListClone.get(i);
            if (a.getNome().equals(nomeProd)) {
                //registar venda
                OpVenda o = new OpVenda(a,dia, mes,ano,sub_stock);
                RegistarVenda(o);

                //subtrair stock no arraylist
                int tot = a.getStock() - sub_stock;
                a.setStock(tot);
                arrayListClone.set(i,a);    //alterar objeto atualizado (com novo stock) no arraylist
                Produtos= (ArrayList<ClassProduto>) arrayListClone.clone();
                try {
                    EscreverFileProd(Produtos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void EliminarProduto(String nome, int id) throws RemoteException {
        ArrayList<ClassProduto> arrayListClone = (ArrayList<ClassProduto>) Produtos.clone();

        for (int i = 0; i < arrayListClone.size(); i++){
            ClassProduto a = arrayListClone.get(i);
            if( a.getNome().equals(nome) || a.getId()==id){
                arrayListClone.remove(id);
                Produtos= (ArrayList<ClassProduto>) arrayListClone.clone();
                try {
                    EscreverFileProd(Produtos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
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
    private void EscreverFileProd(ArrayList<ClassProduto> c) throws IOException{ //synchronized static
        try{
            FileOutputStream fos = new FileOutputStream("Produtos_registados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //separar vendas de compras -> 2 files
    private void EscreverFileCompras(ArrayList<ClassOperacao> c) throws IOException{ //synchronized static
        try{
            FileOutputStream fos = new FileOutputStream("Compras.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void EscreverFileVendas(ArrayList<ClassOperacao> c) throws IOException{ //synchronized static
        try{
            FileOutputStream fos = new FileOutputStream("Vendas.txt");
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
