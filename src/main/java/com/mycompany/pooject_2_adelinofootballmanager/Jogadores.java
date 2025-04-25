/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooject_2_adelinofootballmanager;

/**
 *
 * @author hontman
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;


public class Jogadores extends Pessoa{
    //Array List que irá armazenar todos os jopgadores criados pela classe
    public static final ArrayList<Jogadores> jogadores = new ArrayList<>(89);
    //Variável de posição do jogador
    private final String posicao;
    //Array List que armazena os tipos de lesão
    private ArrayList<String> lesoes = new ArrayList<>();
    //variáveis inteiras para as estatisticas dos jogadores
    private final int statAt, statDef, angerLevel, titulos, nEquipa;
    // Importação da classe Scanner para permitir a interação com o utilizador
    public static Scanner scan = new Scanner(System.in);

//Construtores
    public Jogadores(String position, int ataque, int defesa, int anger, int titulos, int nEquipa) {
        super();
        this.posicao = position;
        this.nEquipa = nEquipa;
        this.statAt = ataque;
        this.statDef = defesa;
        this.angerLevel = anger;
        this.titulos = titulos;
        jogadores.add(this);
        Equipas.getFullEquipa(nEquipa).addToEquipa(this);
    }

    public Jogadores(String nome, int idade, String posicao, int ataque, int defesa, int angerLevel, int titulos, int nEquipa) {
        super(nome, idade);
        this.posicao = posicao;
        this.nEquipa = nEquipa;
        this.statAt = StatsSetter(ataque, "ataque");
        this.statDef = StatsSetter(defesa, "defesa");
        this.angerLevel = angerLevel;
        this.titulos = titulos;
        jogadores.add(this);
        Equipas.getFullEquipa(nEquipa-1).addToEquipa(this);
    }

    public Jogadores(String nome, int idade, String posicao, int ataque, int defesa, int angerLevel, int titulos, int nEquipa, int i) {
        super(nome, idade);
        this.posicao = posicao;
        this.nEquipa = nEquipa;
        this.statAt = StatsSetter(ataque, "ataque");
        this.statDef = StatsSetter(defesa, "defesa");
        this.angerLevel = angerLevel;
        this.titulos = titulos;
        this.lesoes = new ArrayList<String>();
        this.lesoes.addAll(lesoes);
        jogadores.add(this);
    }
    //Método String que retorna a reposta que o utilizador deu, de acordo com as opções fornecidas
    private static String givenChoiceP() {
        System.out.println("Selecione a posição do jogador:");
        System.out.println("1.Avançado");
        System.out.println("2.Medio");
        System.out.println("3.Defesa");
        System.out.println("4.Guarda-Redes");

        int position = scan.nextInt();

        return switch (position) {
            case 1 -> "Avançado    ";
            case 2 -> "Medio       ";
            case 3 -> "Defesa      ";
            case 4 -> "Guarda-Redes";
            default -> {
                System.out.println("Opção inválida!");
                yield givenChoiceP();
            }
        };
    }
    //Método inteiro que retorna o inteiro correspondente à equipa que o utilizador escolheu
    private static int givenChoiceT() {
            System.out.println("Selecione a equipa do jogador:");
            for(int i = 0; i < Equipas.numberEquipas(); i++) {
                System.out.println(i+1 + ". " + Equipas.getFullEquipa(i).getNome());
            }

        return scan.nextInt() - 1;
    }
    //Método que permite interação com o utilizador na criação de um novo jogador
    public static void inserirJogador() {
        int age, anger, titulos, ataque, defesa;
        System.out.println("Digite o nome do jogador:");
        String name = scan.nextLine();
        System.out.println("Digite a idade do jogador (número):");
        try {
            age = scan.nextInt();
        }
        catch(Exception e) {
            System.out.println("Por favor insira um número:");
            age = scan.nextInt();
        }
        scan.nextLine();
        String position = givenChoiceP();
        int whatTeam = givenChoiceT();
        System.out.println("Digite o nivel de agrissividade do jogador (número):");
        try {
            anger = scan.nextInt();
        }
        catch(Exception e) {
            System.out.println("Por favor insira um número:");
            anger = scan.nextInt();
        }
        scan.nextLine();
        System.out.println("Digite o número de titulos do jogador (número):");
        try {
            titulos = scan.nextInt();
        }
        catch(Exception e) {
            titulos = scan.nextInt();
        }
        scan.nextLine();
        System.out.println("Digite o ataque do jogador (número):");
        try {
            ataque = scan.nextInt();
        }
        catch(Exception e) {
            ataque = scan.nextInt();
            scan.nextLine();
        }
        System.out.println("Digite a defesa do jogador (número):");
        try {
            defesa = scan.nextInt();
        }
        catch(Exception e) {
            defesa = scan.nextInt();
            scan.nextLine();
        }
        try {
            new Jogadores(name, age, position, ataque, defesa, anger, titulos, whatTeam);
        }
        catch (Exception e) {
            System.out.println("Por favor, insira valores validos!");
            inserirJogador();
        }
    }
    //Overload do método acima, que permite a inserção de um jogador diretamente na equipa
    public static void inserirJogador(int team) {
        int age, anger, titulos, ataque, defesa;
        System.out.println("Digite o nome do jogador:");
        String name = scan.nextLine();
        System.out.println("Digite a idade do jogador (número):");
        try {
            age = scan.nextInt();
        }
        catch(Exception e) {
            System.out.println("Por favor insira um número:");
            age = scan.nextInt();
        }
        scan.nextLine();
        String position = givenChoiceP();
        System.out.println("Digite o nivel de agrissividade do jogador (número):");
        try {
            anger = scan.nextInt();
        }
        catch(Exception e) {
            System.out.println("Por favor insira um número:");
            anger = scan.nextInt();
        }
        scan.nextLine();
        System.out.println("Digite o número de titulos do jogador (número):");
        try {
            titulos = scan.nextInt();
        }
        catch(Exception e) {
            titulos = scan.nextInt();
        }
        scan.nextLine();
        System.out.println("Digite o ataque do jogador (número):");
        try {
            ataque = scan.nextInt();
        }
        catch(Exception e) {
            ataque = scan.nextInt();
            scan.nextLine();
        }
        System.out.println("Digite a defesa do jogador (número):");
        try {
            defesa = scan.nextInt();
        }
        catch(Exception e) {
            defesa = scan.nextInt();
            scan.nextLine();
        }
        try {
            new Jogadores(name, age, position, ataque, defesa, anger, titulos, team);
        }
        catch (Exception e) {
            System.out.println("Por favor, insira valores validos!");
            inserirJogador();
        }
    }
    //Método que criar aleatoriamente os jogadores com todas as suas estatisticas,posições,etc...
    public static void autoPlayer() {
        int posi = random.nextInt(1, 4);
        String position = switch (posi) {
            case 2 -> "Medio";
            case 3 -> "Defesa";
            case 4 -> "Guarda-Redes";
            default -> "Avançado";
        };
        int tem = random.nextInt(Equipas.numberEquipas());
        while (Equipas.getMinEquipaSize() < 9){
            tem = random.nextInt(Equipas.numberEquipas());
            if (Equipas.getFullEquipa(tem).getSize() < 9){
                break;
            }
        }
        int anger = random.nextInt(100);
        int titulos = random.nextInt(50);
        int ataque;
        int defesa;
        switch (position) {
            case "Avançado" -> {
                ataque = random.nextInt(50, 100);
                defesa = random.nextInt(20, 50);
            }
            case "Defesa" -> {
                defesa = random.nextInt(50, 100);
                ataque = random.nextInt(20, 50);
            }
            case "Medio" -> {
                ataque = random.nextInt(50, 100);
                defesa = random.nextInt(50, 100);
            }
            default -> {
                ataque = random.nextInt(20);
                defesa = random.nextInt(70, 100);
            }
        }
        new Jogadores (position, ataque, defesa, anger, titulos, tem);
    }
    //Overload do método anterior que permite a criação de jogadores para cada equipa
    public static void autoPlayer(int team) {
        int posi = random.nextInt(1, 4);
        String position = switch (posi) {
            case 2 -> "Medio";
            case 3 -> "Defesa";
            case 4 -> "Guarda-Redes";
            default -> "Avançado";
        };
        int anger = random.nextInt(100);
        int titulos = random.nextInt(50);
        int ataque;
        int defesa;
        switch (position) {
            case "Avançado" -> {
                ataque = random.nextInt(50, 100);
                defesa = random.nextInt(20, 50);
            }
            case "Defesa" -> {
                defesa = random.nextInt(50, 100);
                ataque = random.nextInt(20, 50);
            }
            case "Medio" -> {
                ataque = random.nextInt(50, 100);
                defesa = random.nextInt(50, 100);
            }
            default -> {
                ataque = random.nextInt(20);
                defesa = random.nextInt(70, 100);
            }
        }
        new Jogadores (position, ataque, defesa, anger, titulos, team);
    }
    //Método que imprime todos os jogadores guardados no array
    public static void allData() {
        for (Jogadores jogador : jogadores) {
            System.out.println(jogador);
        }
    }
    //Método que permite a que o utilizador procure um jogador em especifico
    public static void Data(String player) {
        player = player.toLowerCase();
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getNome().toLowerCase().equals(player)) {
                System.out.println(jogadores.get(i));
                break;
            }
            else if(i == jogadores.size() - 1) {
                System.out.println("Jogador não encontrado!");
            }
        }
    }
    //Método que irá escrever num ficheiro de texto os jogadores criados no nosso programa
    public static void writer() throws IOException{
        FileWriter fw = new FileWriter("jogadores.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(jogadores.size());
        for (Jogadores a : jogadores) {
            out.println(a.getNome() + " ; " + a.getIdade() + " ; " + a.posicao + " ; " + a.statAt + " ; " + a.statDef + " ; " + a.angerLevel + " ; " + a.titulos + " ; " + a.nEquipa);// + " ; " + a.lesoes.size());
//            for (int j = 0; j < a.lesoes.size(); j++) {
//                out.println(a.lesoes.get(j));
//            }
        }
        out.close();
    }
    //Método que irá ler o ficheiro de texto dos jogadores criados, previamente, no nosso programa
    public static void reader() throws IOException{
        FileReader fr = new FileReader("jogadores.txt");
        BufferedReader br = new BufferedReader(fr);
        int size = Integer.parseInt(br.readLine());
        for (int i = 0; i < size; i++) {
            String[] jogador = br.readLine().split(" ; ");
            String nome = jogador[0];
            int Idade = Integer.parseInt(jogador[1]);
            String posicao = jogador[2];
            int statAt = Integer.parseInt(jogador[3]);
            int statDef = Integer.parseInt(jogador[4]);
            int angerLevel = Integer.parseInt(jogador[5]);
            int titulos = Integer.parseInt(jogador[6]);
            int nEquipas = Integer.parseInt(jogador[7]);
//            int nlesoes = Integer.parseInt(jogador[8]);
//            ArrayList<String> lesoes = new ArrayList<String>(nlesoes);
//            for(int j = 0; j < nlesoes; j++){
//                lesoes.add(br.readLine());
//            }
            new Jogadores(nome, Idade, posicao, statAt, statDef, angerLevel, titulos, nEquipas, 1);
        }
    }
    //Método que irá provocar as lesões nos jogadores
    public void painMaker() {
        String wound = Enum.getRandomWound();
        this.lesoes.add(wound);
    }
    //Método que verifica se os dados inseridos pelo utilizador estão de acordo com o que é previsto
    private int StatsSetter(int stats, String stat) {
        if (stats <= 100)
            return stats;
        else if (stats > 100) {
            System.out.println("O valor de " + stat + " não pode ser maior que 100!");
            return 100;
        }
        else {
            System.out.println("O valor de " + stat + " não pode ser menor que 0!");
            return 0;
        }
    }
    //Getters
    public static Jogadores getPlayer(String name){
        for(int i = 0; i < jogadores.size(); i++){
            if (name.equals(jogadores.get(i).getNome())){
                return jogadores.get(i);
            }
        }
        return null;
    }

    public int getAnger(){
        return angerLevel;
    }

    public int getEquipa(){
        return nEquipa;
    }

    public String getPos(){
        return posicao;
    }
    // Método toString
    @Override
    public String toString() {
        return super.toString() +"| Posição: " + posicao + " | Equipa: " + Equipas.getFullEquipa(nEquipa).getNome() + " | Ataque: " + statAt + " | Defesa: " + statDef + " | Nivel de Agressividade: " + angerLevel + " | Titulos: " + titulos;
    }
}

