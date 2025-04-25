/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.pooject_2_adelinofootballmanager;

import java.util.Random;

/**
 *
 * @author hontman
 */
public enum Enum {
    //variáveis para os tipos de lesão dos jogadores
    braco, perna, tronco, cabeca;
    //variável que irá ganhar todos os métodos da classe Random
    private static final Random rand = new Random();
    //Método String que irá retornar o tipo de lesão que o jogadoes sofreram
    public static String getRandomWound() {
        Enum[] wound = values();
        int randWound = rand.nextInt(wound.length);
        return wound[randWound].toString();
    }
}
