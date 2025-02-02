package com.evangelizacao_back.assistance.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum CycleEnum {

    CICLO_1("1"),
    CICLO_2("2"),
    CICLO_3("3"),
    CICLO_4("4"),
    CICLO_5("5"),
    MOCIDADE("Mocidade"),
    INDEFINIDO("Indefinido");

    private final String label;

    public static CycleEnum fromAge(int age) {
        if (age >= 13) {
            return MOCIDADE;
        } else if (age >= 11) {
            return CICLO_5;
        } else if (age >= 9) {
            return CICLO_4;
        } else if (age >= 7) {
            return CICLO_3;
        } else if (age >= 5) {
            return CICLO_2;
        } else if (age >= 2) {
            return CICLO_1;
        } else {
            return INDEFINIDO;
        }
    }
}