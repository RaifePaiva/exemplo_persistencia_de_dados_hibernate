package br.com.devmedia.application;

import br.com.devmedia.domain.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa("Persefono Sousa Cruz", 28);
        Pessoa pessoa2 = new Pessoa("Beatriz Nunes Aguiar", 15);
        Pessoa pessoa3 = new Pessoa("Rafael Guilherme das Neves", 21);

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2, pessoa3);

        EntityManager entityManager = Persistence.createEntityManagerFactory("introducao-jpa").createEntityManager();

        try {

           entityManager.getTransaction().begin();

           pessoas.stream().forEach(x -> {
               entityManager.persist(x);
           });

           entityManager.getTransaction().commit();

        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

        System.out.println("Recuperação de dados: ");
        List<Pessoa> pessoasList = entityManager.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
        System.out.println(pessoasList);
        entityManager.close();

    }


}
