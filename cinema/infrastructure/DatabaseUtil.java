package io.fnmz.cinema.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class DatabaseUtil {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("cinemaPU");

    private DatabaseUtil() {}

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void close() {
        if (EMF.isOpen()) EMF.close();
    }
}
