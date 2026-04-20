package org.example.demo.repo;


import org.example.demo.repo.Repository;
import org.example.demo.repo.RepostioryImpl;

public class RepositoryProvider {

    private static final Repository REPOSITORY = new RepostioryImpl(
            "ponedeljak.txt",
            "utorak.txt",
            "sreda.txt",
            "cetvrtak.txt",
            "petak.txt"
    );

    private RepositoryProvider() {
    }

    public static Repository getRepository() {
        return REPOSITORY;
    }
}