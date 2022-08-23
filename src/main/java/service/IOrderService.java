package service;

import dto.Customer;

import java.util.List;
import java.util.Map;

public interface IOrderService {

    int getCustomerOrderListTotal(String customerId);
    List<Map<String, Object>> getCustomerOrderList(String customerId, int rowPerPage, int beginPage);

    int insertOrder(String goodsNo, Customer customer, String goodsCount);
}
