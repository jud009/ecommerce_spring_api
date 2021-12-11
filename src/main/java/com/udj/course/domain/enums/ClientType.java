package com.udj.course.domain.enums;

public enum ClientType {

    PERSONAL(1,"Pessoa Física"),
    ENTERPRISE(2,"Pessoa Jurídica");

    private final Integer id;
    private final String name;

    private ClientType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ClientType getById(Integer id) {
        if (id == null)
            return null;

        for (ClientType client : ClientType.values()) {
            if (client.getId().equals(id))
                return client;
        }

        throw new IllegalArgumentException("ID NOT FOUND.");
    }
}
