package io.luigicuri.lanches.service;

import io.luigicuri.lanches.model.Lanche;
import io.luigicuri.lanches.repository.LancheRepository;
import io.luigicuri.lanches.service.lanche.LancheService;
import io.luigicuri.lanches.service.promocoes.PromocoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FazerPedidoService {
    @Autowired
    LancheRepository lancheRepository;
    @Autowired
    LancheService lancheService;
    @Autowired
    PromocoesService promocoesService;
    public Lanche fazerPedido(Lanche lanche) {
        //ESCOLHER LANCHE
        lancheService.escolherLanche(lanche);
        lancheService.inserirAdicional(lanche);

        //PROMOCOES
        promocoesService.calcularPromocaoLight(lanche);
        promocoesService.calcularPromocaoMuitaCarne(lanche);
        promocoesService.calcularPromocaoMuitoQueijo(lanche);

        return lancheRepository.save(lanche);
    }
}
