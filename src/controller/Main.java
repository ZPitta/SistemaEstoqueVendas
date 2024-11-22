package controller;

import model.Produtos;

public class Main {
    public static void main(String[] args) {
        //Cria produtos como exemplo
        Produtos p1 = new Produtos("Arroz Tio Zé", "123", 11.99, 10);
        Produtos p2 = new Produtos("Feijão Tio Zé", "124", 8.99, 5);

        //Imprime os produtos
        System.out.println(p1);
        System.out.println(p2);
    }
}
