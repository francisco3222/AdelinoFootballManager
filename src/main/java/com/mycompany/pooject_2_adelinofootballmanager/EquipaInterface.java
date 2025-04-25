/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pooject_2_adelinofootballmanager;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author leand
 */
public interface EquipaInterface {
    //Métodos
    String imprime();
    void mudarLigaMini();
    String printable();
    void addToEquipa(Jogadores player);
    int pain();
    //Getter's e Setter's
    String getLigaNome();
    String getNome();
    Jogadores getPlayer(int Player);
    int getInt();
    int getSize();
    ArrayList<Jogadores> getAllEquipaPlayers();
    Treinador getTrainer();
    void setVitorias();
    void setDerrotas();
    void setEmpate();
    void setDesempenho(int dif);
    String getCidade();
    void setGolosMarcados(int golos);
    void setGolosSofridos(int golos);
    int equipasLiga();
    ArrayList<EquipaInterface> getEquipas();
}
