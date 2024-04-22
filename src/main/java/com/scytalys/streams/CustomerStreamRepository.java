package com.scytalys.streams;

import com.github.javafaker.Faker;
import com.scytalys.model.Customer;
import com.scytalys.model.CustomerCategory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CustomerStreamRepository {

    private final ArrayList<Customer> customers = new ArrayList<>();

    public CustomerCategory randomCategory() {
        Faker faker = new Faker();
        int random = faker.number().numberBetween(0, CustomerCategory.values().length);
        return CustomerCategory.values()[random];
    }

    public void fillListWithData() {
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setFirstName(faker.name().firstName());
            customer.setLastName(faker.name().lastName());
//            customer.setRegistrationDate(faker.date().between(new Date(), new Date(2000,1,1)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            customer.setRegistrationDate(faker.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            customer.setCategory(randomCategory());
            customer.setBalance(faker.number().randomDouble(2, 0, 1000));
            customer.setAddress(faker.address().streetAddress());
            customer.setEmail(faker.internet().emailAddress());
            customers.add(customer);

        }

    }

    public void printList() {
        customers.forEach(System.out::println);

    }

    // A. sort
    public void sort() {
        //customers.sort(Comparator.comparing(Customer::getLastName));
        //customers.sort(Comparator.comparing(Customer::getRegistrationDate));
        customers.sort(Comparator.comparingDouble(Customer::getBalance));
    }

    // B. filter
    public List<Customer> filterCustomerByBalance(double balance) {
        return customers.stream().
                filter(customer -> customer.getBalance() > 0).
                filter(customer -> customer.getBalance() >= balance).collect(Collectors.toList());

    }

    // C. map - reduce
    public String getAddresses() {
        return customers.stream().map(Customer::getAddress).collect(Collectors.joining("\n"));
    }


    public double maxBalance() {
        return customers.stream().map(Customer::getBalance).max(Double::compareTo).get();

    }

    public double minBalance() {
        return customers.stream().map(Customer::getBalance).min(Double::compareTo).get();
    }

    public int countCustomersAfterRegDate(LocalDate date) {
        return customers.stream().filter(customer -> customer.getRegistrationDate().isAfter(date))
                .toList().size();
        //or
//        return customers.stream().filter(customer -> customer.getRegistrationDate().isAfter(date))
//            .count();
    }

    // E. matching
    public List<Customer> getCustomersStartsWithName(String s) {
        return customers.stream().filter(customer -> customer.getFirstName().startsWith(s)).collect(Collectors.toList());
    }


    // F. grouping
    public Map<CustomerCategory, List<Customer>> groupCustomersWithCategory() {
        return customers.stream()
                .collect(Collectors.groupingBy(Customer::getCategory));
    }


}
