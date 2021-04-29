import java.io.Serializable;

public class Produto implements Serializable {

        private int id;
        private int stock; //quantidade em stock
        private String nome;

        //private static int ultimo =

        public Produto(int id, int quantia_adicionar, String nome){
                this.id=id;
                this.nome=nome;
                stock = ++quantia_adicionar; // ou criar parametro ultimo para guardar a quantia a adicionar e fazer stock =
        }

        //construtor só com nome? por causa do IMPL

        //getters e setters


}
