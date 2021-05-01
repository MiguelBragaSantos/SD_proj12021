import java.io.Serializable;
import java.util.GregorianCalendar;

/*
* FALTA
*   - formula stock
*       criar construtor?
*
* */
public class OpCompra extends ClassOperacao implements Serializable {

    public OpCompra(int codigo, int dia, int mes, int ano) {
        super(codigo, dia, mes, ano);
    }

    public String toString() {
        String s = super.toString();
        return s;
    }

}
