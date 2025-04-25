/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooject_2_adelinofootballmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author hontman
 */
public class Treinador extends Pessoa{
    //Array que irá guardar os treinadores criados pela classse

    public static ArrayList<Treinador> treinadores = new ArrayList<Treinador>(9);
    // Importação da classe Scanner para permitir a interação com o utilizador

    public static Scanner scan = new Scanner(System.in);
    //Variáveis String para a especialização e táticas favoritas dos Treinadores
    private String especializacao, taticasFavo;
    //Variável inteira para a equipa que cada treinador irá treinar
    private final int equipa;
    //Construtores

    public Treinador(String nome, int idade, String especializacao, String taticasFavo, int equipa) {
        super(nome, idade);
        this.especializacao = especializacao;
        this.taticasFavo = taticasFavo;
        this.equipa = equipa;
        treinadores.add(this);
    }
    public Treinador(String especializacao, String taticasFavo, int equipa) {
        super();
        this.especializacao = especializacao;
        this.taticasFavo = taticasFavo;
        this.equipa = equipa;
        treinadores.add(this);
    }
    //Método impressor de todosa os treinadores existentes no array
    public static void imprime(String Treinador) {
        for(Treinador treinador : treinadores) {
            if(treinador.getNome().equals(Treinador)) {
                System.out.println(treinador);
                break;
            }
        }
    }
    //Método toString
    @Override
    public String toString() {
        return super.toString() + " | Especialização: " + especializacao + " | Táticas Favoritas: " + taticasFavo + " | Equipa: " + Equipas.getFullEquipa(equipa).getNome();
    }
    //Método que irá buscar o treinador e todos os seus stats, caso o utilizador escolha um existente
    public static Treinador getTrainer(String nome){
        Treinador trainer = null;
        for(int i = 0; i < treinadores.size(); i++){
            if(nome.equals(treinadores.get(i).getNome())){
                trainer = treinadores.get(i);
            }
        }
        return trainer;
    }
    //Método que permite ao utilizador escolher a equipa do novo treinador

    private static int escolheEquipa() {
            System.out.println("Selecione a equipa do novo Treinador:");
            for(int i = 0; i < Equipas.numberEquipas(); i++) {
                System.out.println(i+1 + ". " + Equipas.getFullEquipa(i).getNome());
            }

            int EquipaID = scan.nextInt();

            return EquipaID-1;
    }
    //Método que interage com o utilizador para a inserção de um novo treinador no jogo
    public static Treinador inserirTreinador() {
        System.out.println("Insira o nome do treinador: ");
        String name = scan.nextLine();
        System.out.println("Insira a idade do treinador: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.println("Insira a especialização do treinador: ");
        String especializacao = scan.nextLine();
        System.out.println("Insira a tatica favorita do treinador: ");
        String taticasFavo = scan.nextLine();
        System.out.println("Insira a equipa do treinador: ");
        //int equipa = escolheEquipa();
        int equipa = Equipas.equipaList.size();
        try {
            return new Treinador(name, age, especializacao, taticasFavo, equipa);
        }
        catch (Exception e) {
            System.out.println("Por favor, insira valores validos!");
            inserirTreinador();
        }
        return null;
    }
    //Getters e Setters
    public static Treinador autoTraining(int Team) {
        return new Treinador(especializacao(), taticas(), Team);
    }
    public String getEspecializacao() {
        return especializacao;
    }
    public void setEspecializacao(String especializacao){
        this.especializacao = especializacao;
    }

    public String getTaticasFavo() {
        return taticasFavo;
    }
    public void setTaticasFavo(String taticasFavo){
        this.taticasFavo = taticasFavo;
    }
    //Método String que destribui a especialização de cada treinador, de forma aleatoria
    public static String especializacao(){
        String[] especializacoes = {"Licença A: Federação Portuguesa de Futebol (FPF)", "Mestrado em Treino Desportivo: Especialização em Futebol", "Cursos de Futebol","Licença B: Federação Portuguesa de Futebol (FPF)"};
        String text = "";
        for(Treinador t : treinadores){
            t.setEspecializacao(especializacoes[random.nextInt(especializacoes.length)]);
            text = t.getEspecializacao();
        }
        return text;

    }
    //Método que irá escrever num ficheiro de texto os treinadores criados no nosso programa
    public static void writer() throws IOException {
        FileWriter fw = new FileWriter("treinadores.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < treinadores.size(); i++){
            out.println(treinadores.get(i).getNome() + " ; " + treinadores.get(i).getIdade() + " ; " + treinadores.get(i).especializacao + " ; " + treinadores.get(i).taticasFavo + " ; " + treinadores.get(i).equipa);
        }
        out.close();
    }
    //Método que irá ler o ficheiro de texto dos treinadores criados, previamente, no nosso programa
    public static void reader() throws IOException{
        FileReader fr = new FileReader("treinadores.txt");
        BufferedReader br = new BufferedReader(fr);
        for (int i = 0; i < 3; i++) {
            String[] jogador = br.readLine().split(" ; ");
            String nome = jogador[0];
            int idade = Integer.parseInt(jogador[1]);
            String especializacao = jogador[2];
            String taticasFavo = jogador[3];
            int equipa = Integer.parseInt(jogador[4]);
            new Treinador(nome, idade, especializacao, taticasFavo, equipa);
        }
    }
//Método String que irá distribuir pelos diversos treinadores, uma tática para cada um deles
    public static String taticas(){
        String[] taticas = {"4-3-3","4-4-2","4-5-1","3-5-2","4-3-2-1","4-2-3-1","4-2-4","5-3-2"};
        String text = "";
        for(Treinador t : treinadores){
            t.setTaticasFavo(taticas[random.nextInt(taticas.length)]);
            text = t.getTaticasFavo();
        }
        return text;
    }

}
