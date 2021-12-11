package com.udj.course.domain.enums;

public enum PaymentState {

    PENDING(1, "pendente"),
    PAYED(2, "pago"),
    CANCELED(3, "cancelado");

    private final Integer id;
    private final String value;

    PaymentState(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }


    public static PaymentState getById(int id) {
        for (PaymentState state : PaymentState.values()) {
            if (state.id.equals(id)) {
                return state;
            }
        }
        throw new IllegalArgumentException("id not found");
    }
}
