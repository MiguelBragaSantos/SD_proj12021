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
*
* */


public class ClassProduto implements Serializable {


        private int id;
        private static int ultimo;
        private int categoria;
        private String nome;//categoria+id

        private int stock; //quantidade em stock
        private int min_stock; //stock minimo -> fica 42 para todos, para já

        private float preco_compra; //preço da compra ao fornecedor pelo vendedor
        private float preco_venda; //preço

        private String fornecedor;

        public ClassProduto(){
                ultimo++;
                id=ultimo;
                categoria=0;
                nome="";

                stock=0;
                min_stock=42; // 42 é o standart para todos os produtos

                preco_compra=0;
                preco_venda=0;
                fornecedor="";
        }

        //registar produto
        public ClassProduto(String nome, int categoria, int min_stock, String fornecedor, float preco_compra){
                ultimo++;
                id=ultimo;

                this.nome=nome;

                /*"1-Móveis "+
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
                        "12- Animais"
                                */

                this.categoria=categoria;

                stock = 0;
                this.min_stock=min_stock;

                this.fornecedor=fornecedor;

                this.preco_compra=preco_compra;
                preco_venda = (float) (preco_compra + preco_compra*0.23 + 0.01);
        }


        //getters e setters
        public int getUltimo() {
                return ultimo;
        }
        public void setUltimo(int ultimo) {
                this.ultimo = ultimo;
        }
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getCategoria() {
                return categoria;
        }

        public void setCategoria(int categoria) {
                this.categoria = categoria;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public int getStock() {
                return stock;
        }

        public void setStock(int stock) {
                this.stock = stock;
        }

        public int getMin_stock() {
                return min_stock;
        }

        public void setMin_stock(int min_stock) {
                this.min_stock = min_stock;
        }

        public float getPreco_compra() {
                return preco_compra;
        }

        public void setPreco_compra(float preco_compra) {
                this.preco_compra = preco_compra;
        }

        public float getPreco_venda() {
                return preco_venda;
        }

        /*? assim ou simples? public void setPreco_venda(float preco_compra) {
                preco_venda= (float) (preco_compra + preco_compra*0.23 + 0.01);
        }*/

        public String getFornecedor() {
                return fornecedor;
        }

        public void setFornecedor(String fornecedor) {
                this.fornecedor = fornecedor;
        }


        //toString
        public String toString() {
                String s = "\nID: " + id + "\nNOME: " + nome + "\nCATEGORIA: " + categoria + "\nSTOCK: " + stock + "\nFORNECEDOR: " + fornecedor;
                return s;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ClassProduto)) return false;
                ClassProduto that = (ClassProduto) o;
                return nome.equals(that.nome);
        }

}
