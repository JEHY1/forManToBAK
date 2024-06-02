package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.*;
import com.example.FORMANTO.dto.order.PaymentRequest;
import com.example.FORMANTO.dto.order.PaymentResponse;
import com.example.FORMANTO.dto.user.orderCancel.OrderCancelViewResponse;
import com.example.FORMANTO.dto.user.orderInquiry.MyPageOrderInquiryResponse;
import com.example.FORMANTO.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SaleProductRepository saleProductRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CartRepository cartRepository;

    private final CustomerRepository customerRepository;

    public PaymentResponse searchQuantity (PaymentRequest request, Principal principal){
        System.err.println("call searchQuantity");
        List<Long> scarcityProductIds = new ArrayList<>();
        List<Integer> scarcityProductCounts = new ArrayList<>();
        List<String> scarcityProductNames = new ArrayList<>();

        int index = 0;
        for(Long productId : request.getProductIds()){
            Product product = productService.findProductByProductId(productId);
            if(product.getQuantity() < request.getProductCounts().get(index++)){
                scarcityProductCounts.add(product.getQuantity());
                scarcityProductIds.add(productId);
                scarcityProductNames.add(product.getName());
            }
        }

        if(!scarcityProductIds.isEmpty()){

            return PaymentResponse.builder()
                    .scarcityProductIds(scarcityProductIds)
                    .scarcityProductCounts(scarcityProductCounts)
                    .scarcityProductNames(scarcityProductNames)
                    .build();
        }
        else{
            System.err.println("before call payment");
            payment(request, principal);
            System.err.println("call payment");
            return new PaymentResponse(null, null, null);
        }
    }

    public Payment payment (PaymentRequest request, Principal principal){

        if(principal == null){ //비회원 결제시

            System.err.println("before save");
            Long paymentId = paymentRepository.save(Payment.builder() //결제 테이블 추가후 id값 가져오기
                .paymentPrice(request.getPaymentPrice())
                .paymentType(request.getPaymentType())
                .address(request.getRoadNameAddress() + " " + request.getDetailAddress())
                .status("배송준비")
                .deliveryFee(request.getDeliveryFee())
                .receiver(request.getReceiver())
                .receiverPhone(request.getReceiverPhone())
                .build()).getPaymentId();

            System.err.println("after save");

            if(!request.getProductIds().isEmpty()){
                int index = 0;
                int saleCount = 0; //productGroup의 판매량 수정용
                List<Integer> productCounts = request.getProductCounts();

                for(Long productId : request.getProductIds()){
                    SaleProduct saleProduct = saleProductRepository.save(SaleProduct.builder()
                        .paymentId(paymentId)
                        .productId(productId)
                        .reviewDeadline(LocalDateTime.now().plusDays(30))
                        .totalQuantity(productCounts.get(index))
                        .build());
                    updateQuantity(productId, productCounts.get(index));
                    updateSaleCount(productId, productCounts.get(index++));

                    for(int i = 0; i < saleProduct.getTotalQuantity(); i++){
                        paymentDetailRepository.save(PaymentDetail.builder()
                            .saleProductId(saleProduct.getSaleProductId())
                            .status("배송준비")
                            .productPrice(productService.findProductByProductId(productId).getPrice())
                            .build());
                    }
                }
            }

            return paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("not created"));
        }
        else{
            System.err.println("before payment save");
            Long paymentId = paymentRepository.save(Payment.builder() //결제 테이블 추가후 id값 가져오기
                    .customerId(customerService.findByUsername(principal.getName()).getCustomerId())
                    .paymentPrice(request.getPaymentPrice())
                    .paymentType(request.getPaymentType())
                    .address(request.getRoadNameAddress() + " " + request.getDetailAddress())
                    .status("배송준비")
                    .deliveryFee(request.getDeliveryFee())
                    .receiver(request.getReceiver())
                    .receiverPhone(request.getReceiverPhone())
                    .build()).getPaymentId();

            System.err.println("after payment save");
            if(!request.getProductIds().isEmpty()){
                int index = 0;
                List<Integer> productCounts = request.getProductCounts();

                for(Long productId : request.getProductIds()){
                    System.err.println("before saleProduct save");
                    SaleProduct saleProduct = saleProductRepository.save(SaleProduct.builder()
                            .paymentId(paymentId)
                            .productId(productId)
                            .reviewDeadline(LocalDateTime.now().plusDays(30))
                            .totalQuantity(productCounts.get(index))
                            .build());
                    System.err.println("after saleProduct save");

                    System.err.println("before product update");
                    updateQuantity(productId, productCounts.get(index));
                    System.err.println("after product update");
                    System.err.println("before productGroup update");
                    updateSaleCount(productId, productCounts.get(index++));
                    System.err.println("after productGroup update");

                    for(int i = 0; i < saleProduct.getTotalQuantity(); i++){
                        System.err.println("before paymentDetail save");

                        paymentDetailRepository.save(PaymentDetail.builder()
                                .saleProductId(saleProduct.getSaleProductId())
                                .status("배송준비")
                                .productPrice(productService.findProductByProductId(productId).getPrice())
                                .build());

                        System.err.println("after paymentDetail save");
                    }
                }
            }
            System.err.println("before cart delete");
            request.getCartIds().forEach(cartId -> cartRepository.deleteById(cartId));
            System.err.println("after cart delete");

            System.err.println("before address update");
            updateCustomerAddress(principal, request);
            System.err.println("after address update");

            return paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("not created"));
        }
    }

    @Transactional
    private void updateQuantity(long productId, int count){

        productService.findProductByProductId(productId).updateQuantity(count);
    }

    @Transactional
    private void updateSaleCount(long productId, int totalCount){

        productService.findProductGroupByProductGroupId(productService.findProductByProductId(productId).getProductGroupId()).updateSaleCount(totalCount);
    }

    @Transactional
    private void updateCustomerAddress(Principal principal, PaymentRequest request){
        Customer customer = customerService.findByUsername(principal.getName());
        if(customer.getPostalCode() == null){
            customer.updateAddress(request.getPostalCode(), request.getRoadNameAddress(), request.getDetailAddress());
            customerRepository.save(customer);
        }
    }

    List<Payment> findAllByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found payment"));
    }

    List<SaleProduct> findAllByPaymentId(Long paymentId) {
        return saleProductRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("not found saleProduct"));
    }

    // ManytoOne JoinColum 을 사용하는 방법으로도 해보기
    public List<OrderCancelViewResponse>orderCancelView(Long customerId) {
        //1 customer_id로 .paymet_Tb검색
        List<Payment> payments = findAllByCustomerId(customerId);
        //2.payment_tb의 payment_id추출
        //3.payment_id로 sale_proudct_tb검색
        List<SaleProduct>saleProducts = new ArrayList<>();
        List<OrderCancelViewResponse>orderCancelResponses = new ArrayList<>();

        //4.sale_product_tb의 product_id추출
        //5.product_id로 product_tb검색 후 가격,이름, 사진 가져오기
        for (int i = 0; i < payments.size(); i++) {
            saleProducts.addAll(findAllByPaymentId(payments.get(i).getPaymentId()));
            //리스트가 아니라 단일로 받을 수 있는 이유가 뭘까
            Product byProductId = productService.findProductByProductId(saleProducts.get(i).getProductId());

            orderCancelResponses.add(
                    OrderCancelViewResponse.builder()
                            .cancel("")
                            .orderNum(saleProducts.get(i).getSaleProductId())
                            .price(payments.get(i).getPaymentPrice())
                            .count(saleProducts.get(i).getTotalQuantity())
                            .name(byProductId.getName())
                            .build());
        }

        return orderCancelResponses;
    }

    public List<MyPageOrderInquiryResponse> orderInquiryView(Long customer) {
        // 1. payment_tb에서 customer_id로 테이블을 찾음
        List<Payment> payments = findAllByCustomerId(customer);
        List<MyPageOrderInquiryResponse> responses = new ArrayList<>();
        List<SaleProduct> saleProducts = new ArrayList<>();
        List<Long> productIds = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // 2. 각 payment에 대해 처리
        for (Payment payment : payments) {
            saleProducts.addAll(findAllByPaymentId(payment.getPaymentId()));
        }

        // 3. 각 saleProduct에 대해 product 조회
        for (SaleProduct saleProduct : saleProducts) {
            productIds.add(saleProduct.getProductId());
        }

        // 4. productIds로 productTb 조회
        //아니 이거 왜 단일로 검색하는거지
        for (Long productId : productIds) {
            products.add(productService.findProductByProductId(productId));
        }

        // 5. responses 리스트 채우기
        for (int i = 0; i < payments.size(); i++) {
            responses.add(MyPageOrderInquiryResponse.builder()
                    .price(products.get(i).getPrice())
                    .count(saleProducts.get(i).getTotalQuantity())
                    .name(products.get(i).getName())
                    .status(payments.get(i).getStatus())
                    .dateTime(payments.get(i).getDay())
                    .company("")
                    .build());
        }

        System.err.println("Payments: " + payments);
        System.err.println("SaleProducts: " + saleProducts);
        System.err.println("Products: " + products);
        System.err.println("Responses: " + responses);

        return responses;
    }

}
