/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pooject_2_adelinofootballmanager;
/**
 *
 * @author hontman
 */
import com.github.javafaker.Faker;

import java.util.Random;

public class Pessoa {
    private final String nome; //variável para os nomes
    private final int idade; // variável para as idades
    public Faker faker = new Faker(); //Gerador de nomes aleatorios automaticamente

    public static  Random random = new Random(); // Gerador de números aleatoriamente
    public Pessoa() {
        this.nome = faker.name().fullName();//VerificarNome(faker.name().fullName());
        this.idade = random.nextInt(25,40);//gera numeros de 25 a 40, aleatoriamente para variável idade
    }
    //Getters e Setters
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    //Construtor
    public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
    }

    //Método toString
    @Override
    public String toString(){
        return "Nome: " + nome + " | Idade: " + idade;
    }
}
