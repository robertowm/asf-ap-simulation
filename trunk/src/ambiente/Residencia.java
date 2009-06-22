package ambiente;

import framework.FIPA.ElementID;
import java.io.Serializable;
import objeto.Comodo;

public class Residencia extends Ambiente implements Serializable {

    public Residencia(ElementID aid) {
        super(aid);

        listaComodos.add(new Comodo("Quarto", this/*, "Quarto", condicoes*/));
        listaComodos.add(new Comodo("Cozinha", this/*, "cozinha", condicoes*/));
//        listaComodos.add(new Comodo("Sala"/*, "Sala", condicoes*/));
        listaComodos.add(new Comodo("Banheiro", this/*, "Banheiro", condicoes*/));
//        listaComodos.add(new Comodo("Área"/*, "Área", condicoes*/));
    }
}

