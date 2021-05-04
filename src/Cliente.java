
import myinputs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente implements InterfaceCliente, Serializable {

    private static InterfaceServidor look; //vamos aceder às funções do servidor pela key: look

    public Cliente() throws RemoteException {
    }

    //quando o stock baixa para o minimo (depois de uma venda)
    @Override
    public void NotifyClient(String s) throws RemoteException {
        System.out.println(s);
    }

    public static void main(String[] args) throws RemoteException, UnknownHostException {
        new Cliente().runCliente();
    }

    private void runCliente() throws UnknownHostException, RemoteException {
        System.out.println((InterfaceCliente)this);

        //Configurações RMI
        String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/Prod"; // isto serve para ir buscar o nome do servidor + label usada para o Registry (inicializarProd - servidor)

        //Menu
        try{
            look=(InterfaceServidor) Naming.lookup(url);

            int option;
            int x = 0;      //gerar loop

            while( x==0 ){
                do {
                    System.out.println("----Mobiliário----");
                    System.out.println("1- Registar um produto -->");
                    System.out.println("2- Efetuar compra | Adicionar uma certa quantidade -->");
                    System.out.println("3- Vender | Dar saída/venda de um produto -->");
                    System.out.println("4- Eliminar um produto-->");
                    System.out.println("5- Consultar produtos existentes-->");
                    System.out.println("6- Consultar as vendas (listar todas/ consultar por ordem de valor/produto mais vendido ...-->");
                    System.out.println("7- Consultar as compras feitas a um fornecedor (listar todas/ consultar por ordem de valor -->");
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
                        System.out.println("ID da mobília a eliminar:");
                        int id4 =Read.mipInt();

                        look.EliminarProduto(nome4, id4);
                        break;
//--------------
                    case 5: //Consultar produtos existentes. Prever vários tipos de consultas;
                        System.out.println("Consultar por stock ascendente-->1");
                        System.out.println("Consultar por stock descendente-->2");
                        System.out.println("Consultar por categoria-->3");
                        System.out.println("Consultar por preço compra ascendente-->4");
                        System.out.println("Consultar por preço compra ascendente-->5");
                        int opcao5=Read.mipInt();
                        switch (opcao5){

                            /*
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            * */




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
                                break;

                            default:
                                System.out.println("Numero invalido");
                                break;
                        }
                        break;
//--------------
                    case 6: //– Consultar as vendas (listar todas/ consultar por ordem de valor/produto mais vendido

                        System.out.println("Listar todas as vendas-->1");
                        System.out.println("Consultar por preço venda ascendente-->2");
                        System.out.println("Consultar por preço venda descendente-->3");
                        System.out.println("Consultar por produto mais vendido-->4");

                        int opcao6=Read.mipInt();
                        switch (opcao6){

                            case 1:
                                ArrayList<ClassOperacao> v=look.ListarVendas();
                                for(int i=0; i<v.size();i++){
                                    System.out.println(v.get(i).toString());
                                }
                                break;

                            case 2:
                            /*
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            *
                            * */


                            default:
                                System.out.println("Numero invalido");
                                break;
                        }

                        break;
//--------------
                    case 7: //Consultar as compras feitas a um fornecedor (listar todas/ consultar por ordem de valor/


                        System.out.println("Listar todas as compras-->1");
                        System.out.println("Consultar por preço venda ascendente-->2");
                        System.out.println("Consultar por preço venda descendente-->3");
                        System.out.println("Consultar por fornecedor-->4");

                        int opcao7=Read.mipInt();
                        switch (opcao7) {

                            case 1:
                                ArrayList<ClassOperacao> cc = look.ListarCompras();
                                for (int i = 0; i < cc.size(); i++) {
                                    System.out.println(cc.get(i).toString());
                                }
                                break;



                                /*
                                *
                                *
                                *
                                *
                                *
                                *
                                *
                                *
                                *
                                *
                                *
                                * */


//--------------
                        }
                        break;
                    default:
                        System.out.println("Numero invalido");
                        break;
                }
            } } catch(Exception r){
            System.out.println("Exception in client "+r.getMessage()); }
    }//run
}
