
import myinputs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Cliente implements InterfaceCliente, Serializable {

    private static InterfaceServidor look;

    public Cliente() throws RemoteException {
    }

    //quando o stock baixa para o minimo (depois de uma venda)
    /*@Override
    public void NotifyClient(String s) throws RemoteException {
        System.out.println(s);
    }*/

    public static void main(String[] args) throws RemoteException, UnknownHostException {
        new Cliente().run();
    }

    private void run() throws UnknownHostException {
        //Configurações RMI
        System.out.println((InterfaceCliente)this);
        String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/CR"; // isto serve para  ires buscar o nome do servidor
        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));

        //Menu
        try{
            look=(InterfaceServidor) Naming.lookup(url);

            int option;
            int x = 0;      //gerar loop

            while( x==0 ){
                do {
                    System.out.println("----Mobiliário----");
                    System.out.println("F1- Registar um produto -->");
                    System.out.println("F2- Adicionar uma certa quantidade -->");
                    System.out.println("V3- Dar saída/venda de um produto -->");
                    System.out.println("F4- Eliminar um produto-->");
                    System.out.println("V5- Consultar produtos existentes-->");
                    System.out.println("F6- Consultar as vendas (listar todas/ consultar por ordem de valor/produto mais vendido ...-->");
                    System.out.println("V7- Consultar as compras feitas a um fornecedor (listar todas/ consultar por ordem de valor -->");
                    System.out.println("8 -Sair-->8");

                    option = Read.mipInt();

                    if(option==8){
                        System.out.println("Obrigado Cliente");
                        break;
                    }

                } while ( option < 1 && option > 8 );

                switch(option){

                    case 1: //Registar um produto;

                        System.out.println("Nome da mobília:");
                        String nome1=ob.readLine();

                        System.out.println("Categoria:\n " +
                                "1-Móveis "+
                                "2-Camas"+
                                "3-Sofás "+
                                "4-Exterior "+
                                "5-Escritório "+
                                "6-Decoração "+
                                "7-Texteis "+
                                "8-Electrodomésticos"+
                                "9-Cozinha "+
                                "10-Casa de banho "+
                                "11- Smart Home"+
                                "12- Animais"+
                                "\n->");
                        int catg =Read.mipInt();

                        System.out.println("Stock minimo? Caso defina o valor standart press 0");
                        int stock =Read.mipInt();

                        System.out.println("Insira o nome do Fornecedor");
                        //listar todos como sugestão
                        String forn =Read.mipString();

                        System.out.println("Insira o preço de compra:");
                        float preco =Read.mipFloat();

                        ClassProduto c= new ClassProduto(nome1, catg, stock, forn, preco);
                        look.RegistarProduto(c);

                        break;
//--------------

                    case 2: //Adicionar uma certa quantidade de um produto já existente;
                        System.out.println("Nome da mobília a comprar:");
                        String nome2=ob.readLine();
                        System.out.println("Quantidade a comprar:");
                        int stock2 =Read.mipInt();

                        look.ComprarProduto(nome2, stock2);

                        break;
//--------------

                    case 3: //Dar saída de um produto (se um produto é vendido ou atinge o seu prazo de validade é retirada a quantidade correspondente do stock);


                        break;
//--------------

                    case 4: //Eliminar um produto (caso deixe de existir no negócio);
                        break;
//--------------

                    case 5: //Consultar produtos existentes. Prever vários tipos de consultas;
                        break;
//--------------

                    case 6: //– Consultar as vendas (listar todas/ consultar por ordem de valor/produto mais vendido
                        break;
//--------------

                    case 7: //Consultar as compras feitas a um fornecedor (listar todas/ consultar por ordem de valor/
                        break;
//--------------
                }
            }
        } catch(Exception r){
            System.out.println("Exception in client "+r.getMessage());
        }



    }


        /*catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(AlunoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AlunoClient.class.getName()).log(Level.SEVERE, null, ex);
        }*/







}
