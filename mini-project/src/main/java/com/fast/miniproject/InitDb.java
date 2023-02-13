package com.fast.miniproject;

import com.fast.miniproject.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {

            Product product1 = new Product(1000,"우리은행","이미지 경로 어찌할깡", "부동산담보대출", 7.7,"02-123-1234");
            Product product2 = new Product(2000,"국민은행","이미지 경로 어찌할깡", "부동산담보대출", 7.7,"02-123-1234");
            Product product3 = new Product(3000,"하나은행","이미지 경로 어찌할깡", "부동산담보대출", 7.7,"02-123-1234");
            Product product4 = new Product(4000,"삼성은행","이미지 경로 어찌할깡", "부동산담보대출", 7.7,"02-123-1234");
            Product product5 = new Product(5000,"엘지은행","이미지 경로 어찌할깡", "부동산담보대출", 7.7,"02-123-1234");

            em.persist(product1);
            em.persist(product2);
            em.persist(product3);
            em.persist(product4);
            em.persist(product5);
        }
    }
}