package io.luigicuri.lanches.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Ingrediente {
    ALFACE(0.40),
    BACON(2.00),
    HAMBURGUER(3.00),
    OVO(0.80),
    QUEIJO(1.50);

    private final double valor;
}
