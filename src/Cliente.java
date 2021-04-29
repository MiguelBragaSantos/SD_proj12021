

import myinputs.*;



public class Cliente {

    public static void main(String[] args) {

        String serverName = "";
        System.setSecurityManager(new SecurityManager());


        if (args.length != 1){
            try {
                serverName = java.net.InetAddress.getLocalHost().getHostName(); // isto serve para  ires buscar o nome do servidor
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            serverName = args[0];
        }
        if (serverName.equals("")){
            System.out.println("usage: java RMIClient < host running RMI server>");
            System.exit(0);
        }

        //Menu
        try{
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

                        break;
//--------------

                    case 2: //Adicionar uma certa quantidade de um produto já existente;



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

        /*catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re.getMessage());
        } catch (NotBoundException ex) {
            Logger.getLogger(AlunoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AlunoClient.class.getName()).log(Level.SEVERE, null, ex);
        }*/






    }

}
