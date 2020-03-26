package spacedatahub.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import spacedatahub.model.Order;
import spacedatahub.repository.OrderRepository;

@Transactional
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {

        List<Order> orders = orderRepository.findAll();
        return orders;
    }
    
    public List<Order> findAllByAppUserId(Long id) {

        List<Order> orders = orderRepository.findAllByAppUserId(id).get();
        return orders;
    }

    public Order find(Long id) {

        Order order = orderRepository.findById(id).get();
        return order;
    }

    public void save(Order order) {

        orderRepository.save(order);
    }

    public void delete(Long id) {

        orderRepository.deleteById(id);
    }
}
