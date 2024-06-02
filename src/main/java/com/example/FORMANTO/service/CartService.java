package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.*;
import com.example.FORMANTO.dto.productDetail.AddCartRequest;
import com.example.FORMANTO.dto.user.cart.CartToWishRequest;
import com.example.FORMANTO.dto.user.cart.MyPageCartDeleteRequest;
import com.example.FORMANTO.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final CustomerRepository customerRepository;
    private final WishRepository wishRepository;


    public List<Cart> findByCustomerId(Long customerId){ //repository 를 이용한 검색결과를 추출(서비스 레이어로 뺌) Optional -> entity(또는 List<Entity>) 검색 결과가 없을 경우 exeption을 발생, 아니면 List를 리턴, 모든 서비스에서 findBy... 은 동일
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
    }

    public List<Cart> findAllByCartId(Long cartId) {
        return cartRepository.findAllByCartId(cartId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
    }

    ProductGroup findByProductGroupId(Long productGroupId) {
        return productGroupRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found productGroup"));
    }

    public Customer findByCustomerIdCustomer(Long customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found customer"));
    }

    //0529추가
    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("not found product"));
    }


    @Transactional
    public void updateCount(Long customerId, Long productId, int changeCount){
        Cart findCart = cartRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
        /*특정 고객이 가진 상품의 수량 변경을 위해
         *카트 정보 가저옴
         */
//        findCart.setCount(changeCount); // 장바구니 특정 상품 수량 변경

        /*
         * Cart 객체 데이터 변경 안되는 상태
         * db에 어떻게 반영?
         */

        findCart.updateSelect(changeCount);
        cartRepository.save(findCart);

        System.err.println("findCart: "
                + findCart.getProductId() + ", "
                + findCart.getCount() + ", "
                + findCart.getCustomerId() + ", "
                + changeCount);
    }

    @Transactional
    public List<Cart> addCart(AddCartRequest request, Long customerId){
        int index = 0;
        List<Cart> carts = new ArrayList<>();

        for(Long productId : request.getProductIds()){
            Cart cart = cartRepository.findByCustomerIdAndProductId(customerId, productId).orElse(null);
            if(cart == null){
                cartRepository.save(Cart.builder()
                        .customerId(customerId)
                        .productId(productId)
                        .count(request.getCounts().get(index++))
                        .build()
                );
            }
            else{
                cart.addSelect(request.getCounts().get(index++));
            }

        }
        return carts;
    }

    //선택된 상품 삭제 메서드
    public void cartdelect(MyPageCartDeleteRequest request) {
        System.err.println("service 진입");

        List<Cart> carts = new ArrayList<>();

        //request에 담긴 카트아이디 갯수만큼 반복 후  레코드 삭제
        for (int i = 0; i < request.getCartIds().size(); i++) {
            //이게 왜 되는거심????
            carts.addAll(findAllByCartId(request.getCartIds().get(i)));
            //아니 carts.get(i)이게 왜 엔티티가 되는거임 미친것임?+++++++++++++
            cartRepository.delete(carts.get(i));
        }

    }

    //0529추가
    //선택된 상품 위시리스트로 넘기기
    public void cartToWish(CartToWishRequest request) {
        System.err.println("service진입");
        //customer_id, product_group_id를 구해야함
        //지금request에서 customer_id, product_id있음

        List<Wish>wishList = new ArrayList<>();
        for (int i = 0; i < request.getProductIds().size(); i++) {
            //product-id로 product_group_id가져오기
            Product byProductId = findByProductId(request.getProductIds().get(i));
            ProductGroup byProductGroupId = findByProductGroupId(byProductId.getProductGroupId());
            Customer byCustomerIdCustomer = findByCustomerIdCustomer(request.getCustomerIds().get(i));


            wishList.add( Wish.builder()
                    .productGroup(byProductGroupId)
                    .customer(byCustomerIdCustomer)

                    .build());

            //이게 왜 되는거지?
            wishRepository.save(wishList.get(i));
            System.err.println(wishList.get(i).getCustomer());
            System.err.println(wishList.get(i).getProductGroup());

        }
        System.err.println(wishList);

    }
}
