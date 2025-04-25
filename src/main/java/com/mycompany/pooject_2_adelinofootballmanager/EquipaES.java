/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pooject_2_adelinofootballmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author leand
 */
public class EquipaES extends Equipas implements EquipaInterface{
    //variaveis String nome e cidade
    private final String nome, cidade;
    //declaração de uma variável do tipo "Treinador" com o nome "trainer"
    private Treinador trainer;
    //Variaveis inteiras das estatisticas de uma equipa
    private int vitorias, derrotas, empates, pontos, golosSofridos, golosMarcados, desempenho = 50;
    //Variavel para a Liga Espanhola
    private static final String Liga = "Espanhola";
    // Importação da classe Random para gerar numeros aleatorios
    private final Random rand = new Random();
    // Importação da classe Scanner para permitir a interação com o utilizador
    private static final Scanner scan = new Scanner(System.in);
    //Arrays de jogadores para cada equipa com capacidade para 11 jogadores
    private final ArrayList<Jogadores> equipaPlayers = new ArrayList<Jogadores>(11);
    private static ArrayList<EquipaInterface> equipasES = new ArrayList<>(3);


    //construtores
    public EquipaES(String nome, Treinador treinador, String cidade) {
        this.nome = nome;
        this.trainer = treinador;
        this.cidade = cidade;
        equipaList.add(this);
        equipasES.add(this);
    }

    public EquipaES(String nome, Treinador treinador, int vitorias, int derrotas, int empates, int pontos, int golosSofridos, int golosMarcados, int desempenho, String cidade, ArrayList<Jogadores> players) {
        this.nome = nome;
        this.trainer = treinador;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.pontos = pontos;
        this.golosSofridos = golosSofridos;
        this.golosMarcados = golosMarcados;
        this.desempenho = desempenho;
        this.cidade = cidade;
        equipaList.add(this);
        equipasES.add(this);
        this.equipaPlayers.addAll(players);
    }

    //Getters e Setters
    public String getNome(){
        return nome;
    }
    public String getCidade(){
        return cidade;
    }
    public void setVitorias(){vitorias++; pontos += 3;}
    public void setDerrotas(){derrotas++;}
    public void setEmpate(){empates++; pontos++;}
    public Treinador getTrainer(){return trainer;}
    public int getSize(){return equipaPlayers.size();}
    public void setDesempenho(int dif){desempenho += dif;}
    public void setGolosMarcados(int golos){golosMarcados+=golos;}
    public void setGolosSofridos(int golos){golosSofridos+=golos;}
    public int equipasLiga(){return equipasES.size();}
    public ArrayList<EquipaInterface> getEquipas(){return equipasES;}

    public ArrayList<Jogadores> getAllEquipaPlayers(){return equipaPlayers;}
    public String getAllPlayersName(){
        String names = "";
        for (Jogadores Player : equipaPlayers) {
            names += Player.getNome() + "\n";
        }
        return names;
    }
    //metodos da Equipas

    //Método para adicionar jogadores à equipa
    public void addToEquipa(Jogadores jogador) {
        this.equipaPlayers.add(jogador);
    }
    //Método para impor lesoes nos jogadores
    public int pain(){
        int nLesoes = 0;
        int chance = rand.nextInt(100);
        for (Jogadores equipaPlayer : equipaPlayers)
            if (chance < 10) {
                equipaPlayer.painMaker();
                nLesoes++;
            }
        return nLesoes;
    }
   //Getter's e Setter's
    public int getInt(){
        for(int i = 0; i < equipaList.size(); i++){
            if (this == equipaList.get(i))
                return i;
        }
        return 0;
    }

    public ArrayList<Jogadores> getEquipaPlayers(){
        return equipaPlayers;
    }

    public Jogadores getPlayer(int player){
        return equipaPlayers.get(player);
    }
    //Método para mudar a equipa,à escolha do utilizador para uma das outras ligas
    public void mudarLigaMini() {
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            System.out.println("A equipa " + nome + " pertence a liga " + Liga + ", para qual pretende mudar?");
            System.out.println("1. Alemã");
            System.out.println("2. Portuguesa");
            try{
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 1:
                        new EquipaAL(nome, trainer, vitorias, derrotas, empates, pontos, golosSofridos, golosMarcados, desempenho, cidade, equipaPlayers);
                        equipaList.remove(this);
                        equipasES.remove(this);
                        break;
                    case 2:
                        new EquipaPT(nome, trainer, vitorias, derrotas, empates, pontos, golosSofridos, golosMarcados, desempenho, cidade, equipaPlayers);
                        equipaList.remove(this);
                        equipasES.remove(this);
                        break;
                    default:
                        System.out.println("Por favor, escolha uma opção valida");
                        scan.nextLine();
                        repeat = true;
                        break;
                }
            }catch (InputMismatchException error){
                System.out.println("Ocorreu um erro, por favor insira um número!");
                scan.nextLine();
            }
        }
    }
    //Método que imprime a tabela de pontos e estatisticas da liga Espanhola
    public String imprime(){
        String text = nome + " (Liga " + Liga + "):\n" + vitorias + " vitorias, "  + derrotas + " derrotas, " + empates + " empates, " + golosMarcados + " golos marcados, "
                + golosSofridos + " golos sofridos, " + desempenho + " desempenho medio, " + " treinador: " + trainer.getNome() + " ,cidade " + cidade + " , \nJogadores:\n";
        for(Jogadores players : equipaPlayers)
            text += players.getNome();
        return text;
    }
    //Método que irá ler o ficheiro de texto das equipas espanholas criadas, previamente, no nosso programa
    public static void reader() throws IOException {
        FileReader fr = new FileReader("equipasES.txt");
        BufferedReader br = new BufferedReader(fr);
        int size = Integer.parseInt(br.readLine());
        for (int i = 0; i < size; i++) {
            String[] equipa = br.readLine().split(" ; ");
            String nome = equipa[0];
            Treinador trainer = Treinador.getTrainer(equipa[1]);
            int vitorias = Integer.parseInt(equipa[2]);
            int derrotas = Integer.parseInt(equipa[3]);
            int empates = Integer.parseInt(equipa[4]);
            int pontos = Integer.parseInt(equipa[5]);
            int golosSofridos = Integer.parseInt(equipa[6]);
            int golosMarcados = Integer.parseInt(equipa[7]);
            int desempenho = Integer.parseInt(equipa[8]);
            String cidade = equipa[9];
            int nPlayers = Integer.parseInt(equipa[10]);
            ArrayList<Jogadores> players = new ArrayList<Jogadores>(nPlayers);
            for (int k = 0; k < nPlayers; k++) {
                players.add(Jogadores.getPlayer(br.readLine()));
            }
            new EquipaES(nome, trainer, vitorias, derrotas, empates, pontos, golosSofridos, golosMarcados, desempenho, cidade, players);
        }
    }
    public String getLigaNome(){return Liga;}

    public static ArrayList<EquipaInterface> getLigaES(){
        return equipasES;
    }

    //Método de impressão de uma equipa, num ficheiro .txt
    public String printable(){
        return nome + " ; " + trainer.getNome() + " ; " + vitorias + " ; " + derrotas + " ; " + empates + " ; " + pontos + " ; " + golosSofridos + " ; " + golosMarcados
                + " ; " + desempenho + " ; " + cidade + " ; " + equipaPlayers.size() + "\n" + getAllPlayersName();
    }
    //Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if (o.getClass() != getClass()) return false;

        EquipaInterface o1 = (EquipaInterface) o;
        return  o1.getNome().equals(getClass().getName());
    }

    @Override
    public String toString() {
        return nome + " - " + vitorias + " Vitorias, " + empates + " Empates, " + derrotas + " Derrotas," + golosMarcados+" Golos Marcados," + golosSofridos + " Golos Sofridos," + desempenho + " Desempenho Médio," + pontos + " Pontos." + "\n";
    }
}
