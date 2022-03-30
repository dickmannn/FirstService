package com.dickmann.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
