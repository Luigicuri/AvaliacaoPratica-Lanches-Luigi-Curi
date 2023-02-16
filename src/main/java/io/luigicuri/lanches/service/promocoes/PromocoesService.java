package io.luigicuri.lanches.service.promocoes;

import io.luigicuri.lanches.enums.Ingrediente;
import io.luigicuri.lanches.model.Lanche;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class PromocoesService {
    public void calcularPromocaoLight(Lanche lanche){
        if(lanche.getAdicional().contains(Ingrediente.ALFACE) && !lanche.getAdicional().contains(Ingrediente.BACON)){
            lanche.setValorTotal(lanche.getValorTotal() - (lanche.getValorTotal() * 0.10));
        }
    }
    public void calcularPromocaoMuitaCarne(Lanche lanche) {
        double valorTotal = 0;
        int hamburguerCount = Collections.frequency(lanche.getAdicional(), Ingrediente.HAMBURGUER);
        int countForDiscount = hamburguerCount / 3;
        double discountAmount = countForDiscount * Ingrediente.HAMBURGUER.getValor();

        for (Ingrediente ingrediente : lanche.getAdicional()) {
            valorTotal += ingrediente.getValor();
            if (ingrediente == Ingrediente.HAMBURGUER) {
                valorTotal -= discountAmount;
            } else {
                valorTotal += ingrediente.getValor();
            }
        }
        lanche.setValorTotal(lanche.getValorTotal() - discountAmount);
    }
    public void calcularPromocaoMuitoQueijo(Lanche lanche) {
        double valorTotal = 0;
        int queijoCount = Collections.frequency(lanche.getAdicional(), Ingrediente.QUEIJO);
        int countForDiscount = queijoCount / 3;
        double discountAmount = countForDiscount * Ingrediente.QUEIJO.getValor();

        for (Ingrediente ingrediente : lanche.getAdicional()) {
            valorTotal += ingrediente.getValor();
            if (ingrediente == Ingrediente.QUEIJO) {
                valorTotal -= discountAmount;
            } else {
                valorTotal += ingrediente.getValor();
            }
        }
        lanche.setValorTotal(lanche.getValorTotal() - discountAmount);
    }
}
