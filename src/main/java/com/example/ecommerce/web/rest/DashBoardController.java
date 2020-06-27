package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.model.ChartProduct;
import com.example.ecommerce.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashBoardController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/countorderoneweek")
    public List<Chart> countOrderByWeek(){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = java.time.LocalDate.now();
        List<Chart> chart = new ArrayList<>();
        for(int i = 1; i<= 7 ;i++){
            Chart c = new Chart();
            c.setId(i);
            c.setDate(localDate);
            Date dateStart = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd =  Date.from(localDate.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            c.setCount(orderService.countOrderByDate(dateStart,dateEnd).size());
            chart.add(c);
            localDate = localDate.minusDays(1);
        }
        Collections.sort(chart,Comparator.comparing(Chart::getDate));
        return chart;
    }
    @GetMapping("/summoneyoneweek")
    public List<Chart> sumMoneyOneWeek(){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = java.time.LocalDate.now();
        List<Chart> chart = new ArrayList<>();
        for(int i = 1; i<= 7 ;i++){
            Chart c = new Chart();
            c.setId(i);
            c.setDate(localDate);
            Date dateStart = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            Date dateEnd =  Date.from(localDate.minusDays(-1).atStartOfDay(defaultZoneId).toInstant());
            int sum = 0;
            List<Order> lst = orderService.countOrderByDate(dateStart,dateEnd);
            for(Order s : lst){
                sum += s.getTotalprice();
            }

            c.setCount(sum/1000);
            chart.add(c);
            localDate = localDate.minusDays(1);
        }
        Collections.sort(chart,Comparator.comparing(Chart::getDate));
        return chart;
    }
    @GetMapping("/countproducttop")
    public List<ChartProduct> getCountProductTop(){
        return orderService.countProductOrderTop();
    }

    @Data
    private class Chart{
        private int id;
        private LocalDate date;
        private  int count;
    }
}