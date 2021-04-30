

public class Servidor {

    public static void main(String[] argv) {

        System.setSecurityManager(new SecurityManager());

        try { //Iniciar a execução do registry no porto desejado
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (Exception e) {
            System.out.println("Exception starting RMI registry:");
            e.printStackTrace();
        }
        try {
            //
            ImplFornecedor implementaInterface = new ImplFornecedor("teste1");

            System.out.println("Servidor está OK");
        } catch (Exception e) {
            System.out.println("Erro no servidor -> " + e);
        }
    }





}
