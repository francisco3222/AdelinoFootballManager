/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooject_2_adelinofootballmanager;

import java.io.*;
import java.io.IOException;
import java.util.*;

import static com.mycompany.pooject_2_adelinofootballmanager.EquipaPT.getLigaPT;
import static com.mycompany.pooject_2_adelinofootballmanager.EquipaES.getLigaES;
import static com.mycompany.pooject_2_adelinofootballmanager.EquipaAL.getLigaAL;

/**
 *
 * @author hontman
 */
public abstract class Equipas {
    //Array das equipas
    static ArrayList<EquipaInterface> equipaList = new ArrayList<EquipaInterface>(9);

    static Scanner scan = new Scanner(System.in);

    //Metodos abstratos
    public abstract int getInt();
    //Metodos da classe

    //Metodo que retorna o número de equipas
    public static int numberEquipas(){
        return equipaList.size();
    }
    //Getter's e Setter's
    public static int getMinEquipaSize(){
        int min = 0;
        for (int i = 0; i < numberEquipas(); i++)
            if (min < getFullEquipa(i).getSize())
                min = getFullEquipa(i).getSize();
        return min;
    }

    public static EquipaInterface getFullEquipa(int Equipa) {
        return equipaList.get(Equipa);
    }

    public static EquipaInterface getFullEquipa(String Equipa) {
        for (EquipaInterface equipa : equipaList) {
            if (equipa.getNome().equals(Equipa)) {
                return equipa;
            }
        }
        return null;
    }
    //Método que ajuda o utilizador a trocar uma equipa de liga
    public static void mudarLiga(){
        while(true){
            System.out.println("Que equipa gostaria de mudar de liga?\n");
            for (int i = 0; i < numberEquipas(); i++) {
                System.out.println((i + 1) + ". " + equipaList.get(i).getNome());
            }
            int choice = scan.nextInt();
            scan.nextLine();
            if(choice > 0 && choice < numberEquipas()) {
                getFullEquipa(choice-1).mudarLigaMini();
                break;
            }
            else
                System.out.println("Escolha uma opção valida");
        }
    }
    //Método que imprime as estatisticas de uma determinada equipa
    public static void imprime(String equipa) {
        EquipaInterface a = getFullEquipa(equipa);
        if (a != null) {
            System.out.println(a.imprime());
        }
        else {
            System.out.println("A equipa não existe");
        }
    }

    //Método para inserir uma equipa em uma das 3 ligas disponiveis

    public static void inserirEquipa() {
        System.out.println("Insira o nome da equipa: ");
        String nome = scan.nextLine();
        System.out.println("Gostaria de inserir o Treinador manualmente?(s/n)");
        char choiceT = scan.next().charAt(0);
        scan.nextLine();
        Treinador trainer;
        if(choiceT == 's')
            trainer = Treinador.inserirTreinador();
        else
            trainer = Treinador.autoTraining(Equipas.equipaList.size());
        System.out.println("Que cidade pertence a equipa?");
        String cidade = scan.nextLine();
        System.out.println("Insira a liga a que pertence: ");
        System.out.println("1. Portuguesa");
        System.out.println("2. Espanhola");
        System.out.println("3. Alemã");
        int liga = scan.nextInt();
        scan.nextLine();

        switch(liga){
            case 1:
                new EquipaPT(nome, trainer, cidade);
                break;
            case 2:
                new EquipaES(nome, trainer, cidade);
                break;
            case 3:
                new EquipaAL(nome, trainer, cidade);
                break;
            default:
                System.out.println("Selecione uma liga valida por favor.");
                break;
        }
        addPlayers();
    }
    //Método que pergunta ao utilizada se que inserir os jogadores manualmente ou não
    private static void addPlayers() {
        System.out.println("Gostaria de inserir jogadores manualmente?");
        System.out.println("1. Sim, inserir os 11 jogadores manualmente");
        System.out.println("2. Inserir alguns manualmente e autocompletar");
        System.out.println("3. Inserir automaticamente");
        int choice = scan.nextInt();
        switch(choice) {
            case 1:
                for (int i = 0; i <= 11; i++) {
                    Jogadores.inserirJogador(equipaList.size());
                }
                break;
            case 2:
                System.out.println("Quantos gostaria de inserir ?");
                int playerMaking = scan.nextInt();
                scan.nextLine();
                for (int i = 0; i < playerMaking; i++) {
                    Jogadores.inserirJogador(equipaList.size());
                }
                for (int i = 0; i < 11 - playerMaking; i++) {
                    Jogadores.autoPlayer(equipaList.size()-1);
                }
                break;
            case 3:
                for (int i = 0; i < 11; i++) {
                    Jogadores.autoPlayer(equipaList.size()-1);
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
    //Método que escreve num ficheiro .txt todas as equipas e Ligas do
    public static void writer() throws IOException {
        String classe;
        for(EquipaInterface equipa : equipaList){
            if(equipa.getLigaNome().equals("Portuguesa")) {
                ArrayList<EquipaInterface> pts = new ArrayList<EquipaInterface>(equipa.getEquipas());
                for (EquipaInterface pt : pts) {
                    FileWriter PTFW = new FileWriter("equipasPT.txt");
                    BufferedWriter PTBW = new BufferedWriter(PTFW);
                    PrintWriter PT = new PrintWriter(PTBW);
                    PT.println(pt.equipasLiga());
                    PT.println(pt.printable());
                    PT.close();
                }
            } else if (equipa.getLigaNome().equals("Espanhola")){
                ArrayList<EquipaInterface> ess = new ArrayList<EquipaInterface>(equipa.getEquipas());
                for (EquipaInterface es : ess) {
                    FileWriter ESFW = new FileWriter("equipasES.txt");
                    BufferedWriter ESBW = new BufferedWriter(ESFW);
                    PrintWriter ES = new PrintWriter(ESBW);
                    ES.println(es.equipasLiga());
                    ES.println(es.printable());
                    ES.close();
                }
            } else if (equipa.getLigaNome().equals("Alema")){
                ArrayList<EquipaInterface> als = new ArrayList<EquipaInterface>(equipa.getEquipas());
                for (EquipaInterface al : als) {
                    FileWriter ALFW = new FileWriter("equipasAL.txt");
                    BufferedWriter ALBW = new BufferedWriter(ALFW);
                    PrintWriter AL = new PrintWriter(ALBW);
                    AL.println(al.equipasLiga());
                    AL.println(al.printable());
                    AL.close();
                }
            }
        }

//        for (EquipaInterface equipa : equipaList) {
//            classe = equipa.getLigaNome();
//            switch (classe) {
//                case "Portuguesa":
//                    FileWriter PTFW = new FileWriter("equipasPT.txt");
//                    BufferedWriter PTBW = new BufferedWriter(PTFW);
//                    PrintWriter PT = new PrintWriter(PTBW);
//                    PT.println(equipa.equipasLiga());
//                    PT.println(equipa.printable());
//                    PT.close();
//                    break;
//                case "Espanhola":
//                    FileWriter ESFW = new FileWriter("equipasES.txt");
//                    BufferedWriter ESBW = new BufferedWriter(ESFW);
//                    PrintWriter ES = new PrintWriter(ESBW);
//                    ES.println(equipa.equipasLiga());
//                    ES.println(equipa.printable());
//                    ES.close();
//                    break;
//                case "Alema":
//                    FileWriter ALFW = new FileWriter("equipasAL.txt");
//                    BufferedWriter ALBW = new BufferedWriter(ALFW);
//                    PrintWriter AL = new PrintWriter(ALBW);
//                    AL.println(equipa.equipasLiga());
//                    AL.println(equipa.printable());
//                    AL.close();
//                    break;
//                default:
//                    System.out.println("Equipa de Liga não reconhecida");
//            }
//
//        }
    }
}
