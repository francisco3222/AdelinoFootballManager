/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pooject_2_adelinofootballmanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hontman
 */
public class Pooject_2_AdelinoFootballManager {
    private static void starter() {
        new EquipaPT("Benfica", Treinador.autoTraining(0), "Lisboa");
        new EquipaPT("Porto", Treinador.autoTraining(1), "Porto");
        new EquipaPT("Sporting", Treinador.autoTraining(2), "Lisboa");
        new EquipaAL("Borussia Dortmund", Treinador.autoTraining(3), "Dortmund");
        new EquipaAL("AdelinoFC", Treinador.autoTraining(4), "Sömmerda");
        new EquipaAL("Bayern Munich", Treinador.autoTraining(5), "Munich");
        new EquipaES("Real Madrid", Treinador.autoTraining(6),"Madrid");
        new EquipaES("Barcelona", Treinador.autoTraining(7),"Barcelona");
        new EquipaES("Atlético de Madrid", Treinador.autoTraining(8),"Madrid");
        for(int i = 0; i <= 81; i++)
            Jogadores.autoPlayer();
        for (int i = 0; i <= 5; i++)
            new Arbitros();
    }

private static void fileChecker() throws IOException {
        File play = new File("jogadores.txt");
        boolean BPlay = play.exists() && !play.isDirectory();
        File team = new File("equipasPT.txt");
        boolean BTeam = team.exists() && !team.isDirectory();
        File trainer = new File("treinadores.txt");
        boolean BTrainer = trainer.exists() && !trainer.isDirectory();
        File arbitos = new File("arbitros.txt");
        boolean BArbitos = arbitos.exists() && !arbitos.isDirectory();
        if(!(BPlay && BTeam && BTrainer)){
            starter();
        } else {
            Jogadores.reader();
            Treinador.reader();
            EquipaPT.reader();
            EquipaES.reader();
            EquipaAL.reader();
            Arbitros.reader();
        }
    }

    public static void main(String[] args) throws IOException {
        //fileChecker();
        starter();
        Menu menu = new Menu();
        menu.theMenu();
    }
}

