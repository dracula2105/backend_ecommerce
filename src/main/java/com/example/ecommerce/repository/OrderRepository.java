package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select e from Order e where e.user.id=?1 order by e.id desc     ")
    List<Order> findOrderOfUser(Long id);
}
