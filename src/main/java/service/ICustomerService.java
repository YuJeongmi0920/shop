package service;

import dto.Customer;

import java.util.List;

public interface ICustomerService {
    int getCustomerListTotal();

    List<Customer> getCustomerList(int rowPerPage, int beginRow);

}
