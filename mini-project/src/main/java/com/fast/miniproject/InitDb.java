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

//            Product product1 = new Product(1000,"우리은행","https://ai.esmplus.com/heehyohoo/project/wori.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product2 = new Product(2000,"국민은행","https://ai.esmplus.com/heehyohoo/project/kb.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product3 = new Product(3000,"하나은행","https://ai.esmplus.com/heehyohoo/project/hana.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product4 = new Product(4000,"신한은행","https://ai.esmplus.com/heehyohoo/project/shinhan.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product5 = new Product(5000,"카카오뱅크","https://ai.esmplus.com/heehyohoo/project/kakao.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product6 = new Product(5000,"기업은행","https://ai.esmplus.com/heehyohoo/project/ibk.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product7 = new Product(5000,"k뱅크","https://ai.esmplus.com/heehyohoo/project/kbank.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product8 = new Product(5000,"농협은행","https://ai.esmplus.com/heehyohoo/project/nh.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product9 = new Product(5000,"SC제일은행","https://ai.esmplus.com/heehyohoo/project/SC.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product10 = new Product(5000,"토스","https://ai.esmplus.com/heehyohoo/project/toss.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product11 = new Product(1500,"우리은행","https://ai.esmplus.com/heehyohoo/project/wori.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product12 = new Product(2500,"국민은행","https://ai.esmplus.com/heehyohoo/project/kb.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product13 = new Product(3500,"하나은행","https://ai.esmplus.com/heehyohoo/project/hana.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product14 = new Product(4500,"신한은행","https://ai.esmplus.com/heehyohoo/project/shinhan.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product15 = new Product(5500,"카카오뱅크","https://ai.esmplus.com/heehyohoo/project/kakao.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product16 = new Product(5500,"기업은행","https://ai.esmplus.com/heehyohoo/project/ibk.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product17 = new Product(5500,"k뱅크","https://ai.esmplus.com/heehyohoo/project/kbank.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product18 = new Product(5500,"농협은행","https://ai.esmplus.com/heehyohoo/project/nh.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product19 = new Product(5500,"SC제일은행","https://ai.esmplus.com/heehyohoo/project/SC.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product20 = new Product(5500,"토스","https://ai.esmplus.com/heehyohoo/project/toss.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product21 = new Product(3100,"우리은행","https://ai.esmplus.com/heehyohoo/project/wori.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product22 = new Product(3200,"국민은행","https://ai.esmplus.com/heehyohoo/project/kb.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product23 = new Product(3300,"하나은행","https://ai.esmplus.com/heehyohoo/project/hana.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product24 = new Product(3400,"신한은행","https://ai.esmplus.com/heehyohoo/project/shinhan.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product25 = new Product(3500,"카카오뱅크","https://ai.esmplus.com/heehyohoo/project/kakao.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product26 = new Product(3600,"기업은행","https://ai.esmplus.com/heehyohoo/project/ibk.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product27 = new Product(3700,"k뱅크","https://ai.esmplus.com/heehyohoo/project/kbank.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product28 = new Product(3800,"농협은행","https://ai.esmplus.com/heehyohoo/project/nh.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product29 = new Product(3900,"SC제일은행","https://ai.esmplus.com/heehyohoo/project/SC.png", "부동산담보대출", 7.7,"02-123-1234");
//            Product product30 = new Product(4200,"토스","https://ai.esmplus.com/heehyohoo/project/toss.png", "부동산담보대출", 7.7,"02-123-1234");
//
//            em.persist(product1);
//            em.persist(product2);
//            em.persist(product3);
//            em.persist(product4);
//            em.persist(product5);
//            em.persist(product6);
//            em.persist(product7);
//            em.persist(product8);
//            em.persist(product9);
//            em.persist(product10);
//            em.persist(product11);
//            em.persist(product12);
//            em.persist(product13);
//            em.persist(product14);
//            em.persist(product15);
//            em.persist(product16);
//            em.persist(product17);
//            em.persist(product18);
//            em.persist(product19);
//            em.persist(product20);
//            em.persist(product21);
//            em.persist(product22);
//            em.persist(product23);
//            em.persist(product24);
//            em.persist(product25);
//            em.persist(product26);
//            em.persist(product27);
//            em.persist(product28);
//            em.persist(product29);
//            em.persist(product30);
        }
    }
}