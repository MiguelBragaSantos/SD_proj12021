/*
 * Add VM options
 * -Djava.security.policy=D:\MIP\EI\1SD\1Praticas\G14_Mobiliario\SD_proj12021\src\permissoes.policy
 * */

import myinputs.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends java.rmi.server.UnicastRemoteObject implements InterfaceCliente, Serializable {

    private static InterfaceServidor look; //vamos aceder às funções do servidor pela key: look

    public Cliente() throws RemoteException {
        super();
    }

    //Não funciona! método remoto:quando o stock baixa para o minimo (depois de uma venda)
    @Override
    public void NotifyClient(String s) throws RemoteException {
        System.out.println("mipMensagem do Servidor: " + s);
    }

    public static void main(String[] args) throws UnknownHostException, NotBoundException, MalformedURLException, RemoteException, IOException {
        new Cliente().runCliente();
    }

    private int runCliente() throws IOException{

        System.out.println((InterfaceCliente)this);


        String url = "rmi://" +
                //InetAddress.getLocalHost().getHostAddress()

                InetAddress.getByName("192.168.1.65").getHostAddress() //ip Miguel
                //InetAddress.getByName("192.168.1.77").getHostAddress() //ip Inês
                //InetAddress.getByName("192.168.1.78").getHostAddress() //ip Carlos

                + ":1099/Prod"; // isto serve para ir buscar o nome do servidor + label usada para o Registry (inicializarProd - servidor)

        try{
            look = (InterfaceServidor) Naming.lookup(url);

            int option;
            int x = 0;

            while( x==0 ){
                do {
                    System.out.println("\n\n-------G14-------");
                    System.out.println("----Mobiliário----");
                    System.out.println("1- Registar um produto -->");
                    System.out.println("2- Efetuar compra -->"); //Adicionar uma certa quantidade
                    System.out.println("3- Vender um produto -->"); //Dar saída/venda
                    System.out.println("4- Eliminar um produto-->");
                    System.out.println("5- Consultar produtos existentes-->");
                    System.out.println("6- Consultar as vendas -->");
                    System.out.println("7- Consultar as compras -->");
                    System.out.println("8 -Sair-->");

                    option = Read.mipInt();

                    if(option==8){
                        System.out.println("Obrigado Cliente");
                        break;
                    }

                } while ( option < 1 && option > 8 );

                switch(option){

                    case 1: //Registar um novo produto;
                        System.out.println("Nome da mobília:");
                        String nome1=Read.mipString();

                        System.out.println("Categoria:\n " +
                                "1-Móveis "+
                                "2-Camas "+
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
                        int catg1 =Read.mipInt();

                        System.out.println("Stock minimo? Caso defina o valor standart press 0");
                        int stock1 =Read.mipInt();
                        System.out.println("Insira o nome do Fornecedor");
                        String forn1 =Read.mipString();
                        System.out.println("Insira o preço de compra:");
                        float preco1 =Read.mipFloat();

                        ClassProduto c= new ClassProduto(nome1, catg1, stock1, forn1, preco1);
                        look.RegistarProduto(c);

                        break;
//--------------
                    case 2: //Adicionar uma certa quantidade de um produto já existente;
                        System.out.println("Nome da mobília a comprar:");
                        String nome2=Read.mipString();
                        System.out.println("Quantidade a comprar:");
                        int stock2 =Read.mipInt();
                        System.out.println("Insira a data de compra");
                        System.out.println("Dia");
                        int dia2 = Read.mipInt();
                        System.out.println("Mes");
                        int mes2 = Read.mipInt();
                        System.out.println("Ano");
                        int ano2 = Read.mipInt();
                        GregorianCalendar DC = new GregorianCalendar(ano2, (mes2 - 1), dia2); //ou passar só DC -> mudar nas classes
                        look.ComprarProduto(nome2, dia2, mes2, ano2,stock2);

                        break;
//--------------
                    case 3: //Dar saída de um produto (se um produto é vendido ou atinge o seu prazo de validade é retirada a quantidade correspondente do stock);
                        System.out.println("Nome da mobília a vender:");
                        String nome3=Read.mipString();
                        System.out.println("Quantidade a vender:");
                        int stock3 =Read.mipInt();
                        System.out.println("Insira a data de venda");
                        System.out.println("Dia");
                        int dia3 = Read.mipInt();
                        System.out.println("Mes");
                        int mes3 = Read.mipInt();
                        System.out.println("Ano");
                        int ano3 = Read.mipInt();
                        //GregorianCalendar DV = new GregorianCalendar(ano3, (mes3 - 1), dia3); //ou passar só DC -> mudar nas classes
                        look.VenderProduto(nome3, dia3, mes3, ano3,stock3);

                        break;
//--------------
                    case 4: //Eliminar um produto (caso deixe de existir no negócio);
                        System.out.println("Nome da mobília a eliminar:");
                        String nome4=Read.mipString();

                        look.EliminarProduto(nome4);
                        break;
//--------------
                    case 5: //Consultar produtos existentes. Prever vários tipos de consultas;
                        System.out.println("Consultar por stock descendente-->1");
                        System.out.println("Consultar por stock ascendente-->2");
                        System.out.println("Consultar por categoria-->3");
                        System.out.println("Consultar por preço compra descendente-->4");
                        System.out.println("Consultar por preço compra ascendente-->5");
                        System.out.println("Consultar por preço venda descendente-->6");
                        System.out.println("Consultar por preço venda ascendente-->7");
                        System.out.println("Consultar por nome-->8");
                        System.out.println("Listar todos -->9");

                        int opcao5=Read.mipInt();
                        switch (opcao5){

                            case 1:
                                ArrayList<ClassProduto> p6 = look.ConsultarProdutoStockDesc();
                                for(int i=0; i<p6.size();i++){
                                    System.out.println(p6.get(i).toString());
                                }
                                p6.clear();
                                break;

                            case 2:
                                ArrayList<ClassProduto> p7 = look.ConsultarProdutoStockCresc();
                                for(int i=0; i<p7.size();i++){
                                    System.out.println(p7.get(i).toString());
                                }
                                p7.clear();
                                break;

                            case 3:
                                System.out.println("Insira a categoria a procurar:\n");
                                System.out.println("1-Móveis "+
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
                                int catg5 =Read.mipInt();
                                ArrayList<ClassProduto> p1=look.ConsultarProdutoCategoria(catg5);

                                if(p1.size()==0){
                                    System.out.println("Nada encontrado");
                                    break;
                                }
                                for(int i=0; i<p1.size();i++){
                                    System.out.println(p1.get(i).toString());
                                }
                                p1.clear();
                                break;

                            case 4:
                                ArrayList<ClassProduto> p2 = look.ConsultarProdutoPrecoCompraDesc();
                                for(int i=0; i<p2.size();i++){
                                    System.out.println(p2.get(i).toString());
                                }
                                p2.clear();
                                break;

                            case 5:
                                ArrayList<ClassProduto> p3 = look.ConsultarProdutoPrecoCompraCresc();
                                for(int i=0; i<p3.size();i++){
                                    System.out.println(p3.get(i).toString());
                                }
                                p3.clear();
                                break;

                            case 6:
                                ArrayList<ClassProduto> p4 = look.ConsultarProdutoPrecoVendaDesc();
                                for(int i=0; i<p4.size();i++){
                                    System.out.println(p4.get(i).toString());
                                }
                                p4.clear();
                                break;

                            case 7:
                                ArrayList<ClassProduto> p5 = look.ConsultarProdutoPrecoVendaCresc();
                                for(int i=0; i<p5.size();i++){
                                    System.out.println(p5.get(i).toString());
                                }
                                p5.clear();
                                break;

                            case 8:
                                System.out.println("Nome prod");
                                String s8 = Read.mipString();
                                ClassProduto p8 = look.ConsultarProduto(s8);
                                System.out.println(p8.toString());
                                break;

                            case 9:
                                ArrayList<ClassProduto> p9 = look.ListarProdutos();
                                for(int i=0; i<p9.size();i++){
                                    System.out.println(p9.get(i).toString());
                                }
                                break;


                            default:
                                System.out.println("Numero invalido");
                                break;
                        }
                        break;


//--------------
                    case 6: //– Consultar as vendas
                        System.out.println("Listar todas as vendas-->1");
                        System.out.println("Consultar por nome de produto-->2");
                        System.out.println("Consultar por categoria-->3");


                        int opcao6=Read.mipInt();
                        switch (opcao6){

                            case 1:
                                ArrayList<ClassOperacao> v=look.ListarVendas();
                                for(int i=0; i<v.size();i++){
                                    System.out.println(v.get(i).toString());
                                }
                                break;

                            case 2:
                                System.out.println("Nome prod");
                                String s9 = Read.mipString();

                                ArrayList<ClassOperacao> v1 =look.ConsultarVendasProduto(s9);
                                for(int i=0; i<v1.size();i++){
                                    System.out.println(v1.get(i).toString());
                                }
                                break;

                            case 3:
                                System.out.println("Insira a categoria a procurar:\n");
                                System.out.println("1-Móveis "+
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
                                int catg7 =Read.mipInt();
                                ArrayList<ClassOperacao> v5 =look.ConsultarVendasCategoria(catg7);
                                for(int i=0; i<v5.size();i++){
                                    System.out.println(v5.get(i).toString());
                                }
                                break;

                            default:
                                System.out.println("Numero invalido");
                                break;
                        }

                        break;
//--------------
                    case 7: //Consultar as compras feitas
                        System.out.println("Listar todas as compras-->1");
                        System.out.println("Consultar por nome de produto-->2");
                        System.out.println("Consultar por fornecedor-->3");
                        System.out.println("Consultar por categoria-->4");


                        int opcao7=Read.mipInt();
                        switch (opcao7) {

                            case 1: //listar todas
                                ArrayList<ClassOperacao> cc = look.ListarCompras();
                                for (int i = 0; i < cc.size(); i++) {
                                    System.out.println(cc.get(i).toString());
                                }
                                break;

                            case 2:
                                System.out.println("Nome do produto: ");
                                String s10 = Read.mipString();

                                ArrayList<ClassOperacao> v2 =look.ConsultarComprasProduto(s10);

                                for(int i=0; i<v2.size();i++){
                                    System.out.println(v2.get(i).toString());
                                }
                                break;

                            case 3:
                                System.out.println("Nome do fornecedor: ");
                                String s11 = Read.mipString();

                                ArrayList<ClassOperacao> v3 =look.ConsultarComprasFornecedor(s11);
                                for(int i=0; i<v3.size();i++){
                                    System.out.println(v3.get(i).toString());
                                }
                                break;

                            case 4:
                                System.out.println("Insira a categoria a procurar:\n");
                                System.out.println("1-Móveis "+
                                        "2-Camas "+
                                        "3-Sofás "+
                                        "4-Exterior "+
                                        "5-Escritório "+
                                        "6-Decoração "+
                                        "7-Texteis "+
                                        "8-Electrodomésticos "+
                                        "9-Cozinha "+
                                        "10-Casa de banho "+
                                        "11-Smart Home "+
                                        "12-Animais"+
                                        "\n->");
                                int catg6 =Read.mipInt();

                                ArrayList<ClassOperacao> v4 =look.ConsultarComprasCategoria(catg6);

                                for(int i=0; i<v4.size();i++){
                                    System.out.println(v4.get(i).toString());
                                }
                                break;

                            default:
                                System.out.println("Numero invalido");
                                break;
                        }

                        break;

                    case 8:
                        System.out.println("---");
                        return 0;
                    default:
                        System.out.println("Numero invalido");
                        break;
                }
            } } catch(Exception r){
            System.out.println("Exception in client "+r.getMessage()); }
        return 1;
    }//run
}
