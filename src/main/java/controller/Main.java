package controller;

import model.Cliente;
import model.Produto;
import model.Usuario;
import model.Permissao;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Cria produtos como exemplo
        Produto p1 = new Produto("Arroz Tio Zé", "123", 11.99, 10);
        Produto p2 = new Produto("Feijão Tio Zé", "124", 8.99, 5);

        //Imprime os produtos
        System.out.println(p1);
        System.out.println(p2);

        //Cria clientes como exemplo
        Cliente c1 = new Cliente("João", "123.456.789-09", "teste@email.com",
                "(11) 99999-9999", LocalDate.of(1990, 1, 1));
        Cliente c2 = new Cliente("Maria", "12345678000195", "test@eemail.com",
                "", LocalDate.of(1990, 1, 1));

        //Imprime os clientes
        System.out.println(c1);
        System.out.println(c2);

        //Cria usuários como exemplo
        Usuario u1 = new Usuario("admin", "123456789-09", "teste2@teste.com",
                "admin123", Permissao.ADMIN, LocalDate.of(1990, 1, 1));
        Usuario u2 = new Usuario("user", "12345678/000195", "teste3@email.com",
                "user123", Permissao.VENDEDOR, LocalDate.of(1990, 1, 1));

        //Imprime os usuários
        System.out.println(u1);
        System.out.println(u2);
    }
}
