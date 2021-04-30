import java.io.Serializable;

public class ClassProduto implements Serializable {

        private int id; //categoria x id=1000; categoria y id=2000
        private String categoria;
        private int stock; //quantidade em stock
        private int min_stock; //stock minimo
        private String nome;
        private float preco_compra;
        private float preco_venda;

        /*
        *
        *
        *
        * */


        //private static int ultimo =

        public ClassProduto(int id, int quantia_adicionar, String nome){
                this.id=id;
                this.nome=nome;
                stock = ++quantia_adicionar; // ou criar parametro ultimo para guardar a quantia a adicionar e fazer stock =
        }

        //construtor só com nome? por causa do IMPL



        //getters e setters


        //toString


}
