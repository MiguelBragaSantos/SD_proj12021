import java.io.Serializable;
/*
*        O QUE FALTA:
* - definir função para o preço de venda
*       tipo IVA, comissão, etc
*
* - associar categoria a um range de 1000 números
*       id mudar o primeiro valor consoante a categoria
*               se for sofás: 1xxx
*               se for camas: 2xxx
*
* - getters, setters
* */


public class ClassProduto implements Serializable {

        private int id; //categoria x id=1000; categoria y id=2000
        private static int ultimo;
        private String categoria;
        private String nome;

        private int stock; //quantidade em stock
        //private int min_stock; //stock minimo -> fica 42 para todos, para já
        //private static int add_stock; //quantidade a adicionar - no need fot this

        private float preco_compra; //preço da compra ao fornecedor pelo vendedor
        private float preco_venda; //preço

        public ClassProduto(){
                ultimo++;
                id=ultimo;
                categoria="";
                nome="";

                stock=0;
                //min_stock=42; // 42 é o standart para todos os produtos

                preco_compra=0;
                preco_venda=0;
        }

        public ClassProduto(String nome, String categoria, int add_stock){
                ultimo++;
                id=ultimo;
                this.nome=nome;
                this.categoria=categoria;

                stock = ++add_stock;

                //preços -> ver nas operações?
        }

        //getters e setters
        public int getUltimo() {
                return ultimo;
        }
        public void setUltimo(int ultimo) {
                this.ultimo = ultimo;
        }

        //........


        //toString
        public String toString() {
                String s = "ID: " + id + "\nNOME: " + nome + "\nCATEGORIA: " + categoria + "\nSTOCK: " + stock;
                return s;
        }
}
