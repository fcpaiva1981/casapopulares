package br.com.fcpaiva.admin.domain.utils;

import java.util.UUID;

public final class IdUtils {

    private IdUtils() {}

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}

