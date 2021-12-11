package com.udj.course;

import com.udj.course.domain.*;
import com.udj.course.domain.enums.ClientType;
import com.udj.course.domain.enums.PaymentState;
import com.udj.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CourseApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category("Informática");
        Category c2 = new Category("Escritório");

        Product p1 = new Product("Computador", 2000.00);
        Product p2 = new Product("Impressora", 800.00);
        Product p3 = new Product("Mouse", 80.00);

        c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        c2.getProducts().add(p2);

        p1.getCategories().add(c1);
        p2.getCategories().addAll(Arrays.asList(c1, c2));
        p3.getCategories().add(c1);

        categoryRepository.saveAll(Arrays.asList(c1, c2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        State sp = new State("São Paulo");
        State mg = new State("Minas Gerais");

        City spCity = new City("São paulo", sp);
        City camp = new City("Campinas", sp);
        City ub = new City("Uberlândia", mg);

        sp.getCities().addAll(Arrays.asList(spCity, camp));
        mg.getCities().add(ub);

        stateRepository.saveAll(Arrays.asList(sp, mg));
        cityRepository.saveAll(Arrays.asList(spCity, camp, ub));

        Client client = new Client(
                "Maria Silva",
                "maria@gmail.com",
                "841256689",
                ClientType.PERSONAL);

        client.getPhoneNumbers().addAll(Arrays.asList("754555", "84552212"));

        Address adr1 = new Address(ub, client, "rua floures",
                "300", "aptor 230", "Jardim", "3215455");

        Address adr2 = new Address(spCity, client, "Avenida Matos",
                "105", "sala 800", "centro", "4588122");


        client.getAddresses().addAll(Arrays.asList(adr1, adr2));

        clientRepository.save(client);
        addressRepository.saveAll(Arrays.asList(adr1, adr2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ProductOrder order = new ProductOrder(sdf.parse("30/09/2020 10:32"), client, adr1);
        ProductOrder order2 = new ProductOrder(sdf.parse("10/10/2020 19:35"), client, adr2);

        Payment pay1 = new CardPayment(PaymentState.PAYED, order, 6);
        order.setPayment(pay1);

        Payment pay2 = new PaymentTicket(PaymentState.PENDING, order2,
                null, sdf.parse("20/10/2020 00:00"));
        order2.setPayment(pay2);

        client.getOrders().addAll(Arrays.asList(order, order2));

        orderRepository.saveAll(Arrays.asList(order, order2));
        paymentRepository.saveAll(Arrays.asList(pay1, pay2));

        OrderItem oi1 = new OrderItem(order, p1, 0.00, 1, p1.getPrice());
        OrderItem oi2 = new OrderItem(order, p2, 0.00, 2, 80.00);
        OrderItem oi3 = new OrderItem(order2, p2, 0.00, 1, p2.getPrice());

        order.getItems().addAll(Arrays.asList(oi1, oi2));
        order2.getItems().add(oi3);

        p1.getItems().add(oi1);
        p2.getItems().add(oi3);

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
    }
}
