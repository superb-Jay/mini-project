package com.fast.miniproject.product.service.impl;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.PageResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.product.dto.OrderHistory;
import com.fast.miniproject.product.dto.ProductDTO;
import com.fast.miniproject.product.dto.ProductDetailDTO;
import com.fast.miniproject.product.dto.PurchasedProductDto;
import com.fast.miniproject.product.entity.Orders;
import com.fast.miniproject.product.entity.Product;
import com.fast.miniproject.product.entity.PurchasedProduct;
import com.fast.miniproject.product.repository.BasketRepository;
import com.fast.miniproject.product.repository.OrderRepository;
import com.fast.miniproject.product.repository.ProductRepository;
import com.fast.miniproject.product.repository.PurchaseProductRepository;
import com.fast.miniproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;


    @Override
    public ResponseDTO selectProductDetail(Long product_id) {
        try {
            Product product = productRepository.getReferenceById(product_id);
            ProductDetailDTO productDetailDTO = new ProductDetailDTO(product);
            return new ResponseDTO<>(productDetailDTO);

        } catch (Exception e) {
            return new ErrorResponseDTO(500, "해당 상품의 상세정보를 찾지 못했습니다.").toResponse();
        }
    }

    @Override
    public ResponseDTO<?> selectProduct(Pageable pageable) {
        try {
            Page<ProductDTO> productDTO = productRepository.findAll(pageable)
                    .map(ProductDTO::new);
            return new ResponseDTO<>(new PageResponseDTO(productDTO));
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "상품 목록을 불러오지 못 했습니다").toResponse();
        }
    }

    @Override
    public ResponseDTO<?> recommendProduct(String email, Pageable pageable) {
        try {

            User user = userRepository.findByEmail(email).get();

            int limitAmount = (int) (user.getSalary() * 2);

            Page<Product> product = productRepository.findByPriceLessThanEqual(limitAmount, pageable);

            Page<ProductDTO> productPage = product
                    .map(ProductDTO::new);

            return new ResponseDTO<>(new PageResponseDTO(productPage));
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "추천 상품을 불러 오지 못 했습니다").toResponse();
        }
    }

    @Override
    @Transactional
    public ResponseDTO<?> buyProduct(ArrayList<Integer> products_id_list, LoginReqDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(IllegalArgumentException::new);
        List<Product> productList = productRepository.findAllByProductId(products_id_list);
        if (productList.size() != products_id_list.size())
            return new ErrorResponseDTO(500, "구매에 실패하였습니다.").toResponse();
        if (!isAvailableToPurchase(user, productList))
            return new ErrorResponseDTO(500, "대출 가능 금액을 초과하였습니다.").toResponse();
        try {
            Orders orders = new Orders(user);
            toSave(productList, orders);
            orderRepository.save(orders);
            basketRepository.deleteByUserAndProductList(user.getMemberId(), products_id_list);
            return new ResponseDTO<>("상품 구매에 성공하였습니다.");
        } catch (Exception e) {
            return new ErrorResponseDTO(500, "구매에 실패하였습니다.").toResponse();
        }
    }

    @Override
    public ResponseDTO<?> orderCheck(LoginReqDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(IllegalArgumentException::new);
        try {
            List<Orders> orderList = orderRepository.findAllByUserOrderByPurchaseDate(user);
            ArrayList<PurchasedProduct> list = purchaseProductRepository.findAllByOrdersList(orderList);
            return new ResponseDTO<>(parsingByOrders(list));
        } catch (Exception e) {

        }
        return new ErrorResponseDTO(500, "구매하신 상품이 없습니다.").toResponse();
    }

    @Override
    public ResponseDTO<?> deleteOrder(LoginReqDTO dto, Long orderId) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(IllegalArgumentException::new);
        try {
            Orders orders = orderRepository.findByOrderIdAndUser(orderId, user).orElseThrow(IllegalArgumentException::new);
            orderRepository.delete(orders);
            return new ResponseDTO<>(200, "구매를 취소하였습니다.", null);
        } catch (Exception e) {

        }
        return new ErrorResponseDTO(500, "구매 취소를 실패하였습니다.").toResponse();

    }

    private boolean isAvailableToPurchase(User user, List<Product> productList) {
        List<Orders> ordersList = orderRepository.findAllByUserOrderByPurchaseDate(user);
        Integer sum = purchaseProductRepository.searchSumByOrdersList(ordersList);
        int max = (int) (user.getSalary() * 2);
        if (sum != null) {
            System.out.println(sum);
            max -= sum;
        }
        for (Product product : productList) {
            max -= product.getPrice();
        }
        if (max > 0) {
            return true;
        } else {
            return false;
        }
    }


    private void toSave(List<Product> productList, Orders orders) {
        for (Product product : productList) {
            PurchasedProduct purchasedProduct = new PurchasedProduct(product);
            orders.getPurchasedProducts().add(purchasedProduct);
            purchasedProduct.setOrders(orders);
        }
    }

    private ArrayList<OrderHistory> parsingByOrders(ArrayList<PurchasedProduct> purchasedProducts) {
        ArrayList<OrderHistory> orderHistoryArrayList = new ArrayList<>();
        ArrayList<PurchasedProductDto> toShowList = new ArrayList<>();
        Orders order = null;
        for (int i = 0; i < purchasedProducts.size(); i++) {
            if (order == purchasedProducts.get(i).getOrders() || order == null) {
                order = purchasedProducts.get(i).getOrders();
                toShowList.add(new PurchasedProductDto(purchasedProducts.get(i)));
            } else {
                orderHistoryArrayList.add(new OrderHistory(toShowList, order));
                order = purchasedProducts.get(i).getOrders();
                toShowList = new ArrayList<>();
                toShowList.add(new PurchasedProductDto(purchasedProducts.get(i)));
            }
        }
        orderHistoryArrayList.add(new OrderHistory(toShowList, order));
        return orderHistoryArrayList;
    }


}
