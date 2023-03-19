package io.luigicuri.lanches.service.lanche;

import io.luigicuri.lanches.enums.Ingrediente;
import io.luigicuri.lanches.enums.TipoLanche;
import io.luigicuri.lanches.model.Lanche;
import org.springframework.stereotype.Service;

@Service
public class LancheService {
    public void escolherLanche(Lanche lanche){
        if (lanche.getNomeLanche().equals(TipoLanche.X_BACON)) {
            lanche.setValorTotal(Ingrediente.BACON.getValor()
                    + Ingrediente.HAMBURGUER.getValor()
                    + Ingrediente.QUEIJO.getValor());
        } else if (lanche.getNomeLanche().equals(TipoLanche.X_BURGER)) {
            lanche.setValorTotal(Ingrediente.HAMBURGUER.getValor()
                    + Ingrediente.QUEIJO.getValor());
        } else if (lanche.getNomeLanche().equals(TipoLanche.X_EGG)) {
            lanche.setValorTotal(Ingrediente.OVO.getValor()
                    + Ingrediente.HAMBURGUER.getValor()
                    + Ingrediente.QUEIJO.getValor());
        } else if (lanche.getNomeLanche().equals(TipoLanche.X_EGG_BACON)) {
            lanche.setValorTotal(Ingrediente.OVO.getValor()
                    + Ingrediente.BACON.getValor()
                    + Ingrediente.HAMBURGUER.getValor()
                    + Ingrediente.QUEIJO.getValor());
        }
    }
    public void inserirAdicional(Lanche lanche){
        if(!lanche.getAdicional().isEmpty()){
            lanche.getAdicional()
                    .stream()
                    .forEach(adicional -> lanche.setValorTotal(lanche.getValorTotal() + adicional.getValor()));
        }
    }
}

