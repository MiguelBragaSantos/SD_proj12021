/*
* Add VM options
* -Djava.security.policy=D:\MIP\EI\1SD\1Praticas\G14_Mobiliario\SD_proj12021\src\permissoes.policy
* */


import java.io.*;
import java.rmi.*;      //RemoteException, Naming
import java.rmi.registry.Registry;
import java.util.*;

/*
* fatal:
* - public Servidor(ArrayList<ClassProduto> p) --> DEVIA TER AS 3 ARRAYLISTS/FILES
* - no main: Servidor lserver=new Servidor(inicializarProd());  -> este inicializar devia inicializar os 3 e retornar 3

 * */

public class Servidor extends java.rmi.server.UnicastRemoteObject implements InterfaceServidor, Runnable{

    Hashtable<String,InterfaceCliente> list = new Hashtable<String,InterfaceCliente> ();
    private ArrayList<ClassProduto> Produtos = new ArrayList<>();
    private ArrayList<ClassOperacao> Vendas = new ArrayList<>();
    private ArrayList<ClassOperacao> Compras = new ArrayList<>();

    public Servidor(ArrayList<ClassProduto> p) throws RemoteException {
        super();
        this.Produtos=p;
        //this.Vendas=v;
        //this.Compras=c;
    }


    //1º a executar
    private synchronized static ArrayList<ClassProduto> inicializarProd() throws ClassNotFoundException {
        ArrayList<ClassProduto> auxP=new ArrayList<ClassProduto>();
        ArrayList<ClassOperacao> auxV=new ArrayList<ClassOperacao>();
        ArrayList<ClassOperacao> auxC = new ArrayList<ClassOperacao>();

        try {

            FileOutputStream fos = new FileOutputStream("Produtos_registados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(fos);
            fos.flush();
            oos.flush();
            oos.close();
            fos.close();

            FileInputStream fisP = new FileInputStream("Produtos_registados.txt");
            ObjectInputStream oisP = new ObjectInputStream(fisP);
            auxP = (ArrayList) oisP.readObject();

            oisP.close();
            fisP.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return auxP;
    }


    //FUNÇÕES DO INTERFACE SERVIDOR
    @Override
    public synchronized void RegistarProduto(ClassProduto c) throws RemoteException {
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


    @Override
    public synchronized void ComprarProduto(String nomeProd, int dia, int mes, int ano, int add_stock) throws RemoteException {
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
        /*
        *
        *
        *
        *
        *
        *
        *
        * */
        return null;
    }

    @Override
    public synchronized ArrayList<ClassProduto> ConsultarProdutoCategoria(int s) throws RemoteException {
        ArrayList<ClassProduto> aux = new ArrayList<ClassProduto>();

        for(int i=0;i<Produtos.size();i++){
            ClassProduto a = Produtos.get(i);
            if(a.getCategoria()==s)
                aux.add(a);
        }
        return aux;
    }

    @Override
    public ArrayList<ClassOperacao> ConsultarVendas(String s) throws RemoteException {
        /*
         *
         *
         *
         *
         *
         *
         *
         * */

        return null;
    }

    @Override
    public synchronized ArrayList<ClassOperacao> ListarVendas() throws RemoteException{
        return Vendas;
    }
    public synchronized ArrayList<ClassOperacao> ListarCompras() throws RemoteException{
        return Compras;
    }
    public synchronized ArrayList<ClassProduto> ListarProdutos() throws RemoteException{
        return Produtos;
    }




    //FUNÇOES AUXILIARES
    private void EscreverFileProd(ArrayList<ClassProduto> c) throws IOException { //synchronized static
        try{
            FileOutputStream fos = new FileOutputStream("Produtos_registados.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);

            oos.flush();

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

            oos.flush();

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

            oos.flush();

            oos.close();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] argv) {
        //Iniciar a execução do registry no porto 1099
        try {
            //Registry r =
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("mipRMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("mipException starting RMI registry:");
            e.printStackTrace();
        }
        try{
            //Servidor é onde está a Impl
            Servidor lserver=new Servidor(inicializarProd()); //1º a executar

            Naming.rebind("Prod",lserver);

            System.out.println("mipServidor está OK");


            Thread thread= new Thread(lserver);
            thread.start();
        } catch (Exception e) {
            System.err.println("mipServer exception: " + e.getMessage());
        }
    }


    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

            Enumeration<String> enumeration = list.keys();
            ArrayList<ClassProduto> cloneProd =  (ArrayList<ClassProduto>) Produtos.clone();
            ArrayList<ClassOperacao> cloneVendas = (ArrayList<ClassOperacao>) Vendas.clone();
            ArrayList<ClassOperacao> cloneCompras = (ArrayList<ClassOperacao>) Compras.clone();

            while(enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();

                for(int i=0;i<cloneProd.size();i++){
                    ClassProduto a=cloneProd.get(i);

                    if(a.getStock() < a.getMin_stock()){          //quando um produto fica abaixo do stock minimo -> notificar cliente
                        InterfaceCliente b = ( InterfaceCliente) list.get(key);
                        try{
                            System.out.println(b);
                            b.NotifyClient("O produto:" + key + ", está com pouco stock. Reponha sff, na opção 2 do MENU");
                            list.remove(key);

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }//run





}
