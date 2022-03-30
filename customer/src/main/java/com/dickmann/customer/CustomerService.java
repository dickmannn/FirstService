package com.dickmann.customer;

import com.dickmann.clients.fraud.FraudCheckResponse;
import com.dickmann.clients.fraud.FraudClient;
import com.dickmann.clients.notification.NotificationClient;
import com.dickmann.clients.notification.NotificationRequest;
import com.dickmann.customer.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());


        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //todo: make it asynchron
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...", customer.getFirstName())
                )
        );
    }
}
