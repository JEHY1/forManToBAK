package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.domain.Wish;
import com.example.FORMANTO.dto.WishResponse;
import com.example.FORMANTO.dto.user.wish.MyPageWishDeleteRequest;
import com.example.FORMANTO.dto.user.wish.MyPageWishOptionResponse;
import com.example.FORMANTO.repository.ProductGroupRepository;
import com.example.FORMANTO.repository.ProductRepository;
import com.example.FORMANTO.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishRepository wishRepository;
    private final ProductGroupRepository productGroupRepository;
    private final CustomerService customerService;
    private final ProductRepository productRepository;
    private final ProductService productService;

    //optional이 아닌 list형식으로 바꾸기
    public List<Wish> findByCustomerId(Long customerId) {
        return wishRepository.findAllByCustomerCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found wish"));
        //List형식이 아닌 형식은 어떻게 만들 수 있을까?
    }

    public ProductGroup findByProductGroupId(Long productGroupId) {
        //findById는 Jpa도 그냥 제공하는 기능인가봄
        return productGroupRepository.findById(productGroupId)
                .orElseThrow(()-> new IllegalArgumentException("not found ProductGroup"));
    }

    public List<ProductGroup> findAllByProductIdId(Long productGroupId) {
        return productGroupRepository.findAllByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found ProductGroup"));
    }

    public List<Wish> findAllByCustomerId(Long customer) {
        return wishRepository.findAllByCustomerCustomerId(customer)
                .orElseThrow(()->new IllegalArgumentException("not found wish"));
    }

    //0528 추가
    public List<Wish> findAllByWishId(Long wishId) {
        return wishRepository.findAllByWishId(wishId)
                .orElseThrow(() -> new IllegalArgumentException("not found wish"));
    }

    //0530추가
    public List<Product> findByProductGroupIds(Long productGroupId) {
        return productRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found product"));
    }




    //db-> view로 데이터 넘기기
    public List<WishResponse> wishview(Long customerId) {
        //1. customerId를 받아와서 wish의 리파지토리 테이블 레코드를 받아야함
//      List<Wish> wishes = findByCustomerId(customerId);
        List<Wish> wishes = findAllByCustomerId(customerId);
        //2. 받은 레코드의 productGroupID를 추출해서 productGroupTb를 접근
        //  3. productGroupTb안에 잇는 company, name, price    +++img도 있어야 함

        List<WishResponse> wishResponses = new ArrayList<>();



        wishes.forEach(wish -> {
            ProductGroup productGroups = productService.findProductGroupByProductGroupId(wish.getProductGroup().getProductGroupId());

            List<MyPageWishOptionResponse> wishInfos = new ArrayList<>();
            List<Product> products = productService.findProductByProductGroupId(wish.getProductGroup().getProductGroupId());


            System.out.println("wishId : " + wish.getWishId());


            products.forEach(product -> {

                System.err.println("111");
                wishInfos.add(MyPageWishOptionResponse.builder()
                        .productName(product.getName())
                        .productPrice(product.getPrice())
                        .productId(product.getProductId())
                        .count(1)
                        .build()
                );

            });



            wishResponses.add(WishResponse.builder()
                    .wishId(wish.getWishId())
                    .company(productGroups.getCompany())
                    .price(productGroups.getGroupPrice())
                    .name(productGroups.getName())
                    .productGroupId(productGroups.getProductGroupId())
                    .productInfos(wishInfos)
                    .build()
            );
            //이 wishId가 왜 되는거지???

            System.err.println("price : " + productGroups.getGroupPrice());
        });




//내가 실패한 코드
//        wishes.forEach(wish -> {
//            ProductGroup productGroups = findByProductGroupId(wish.getProductGroup().getProductGroupId());
////            List<ProductGroup>productGroups1 = findAllByProductIds(wish.getProductGroup().getProductGroupId());
//            System.err.println("productgroup = "+productGroups);
//            System.err.println("productgroup.getProductGroupId = "+productGroups.getProductGroupId());
//
//
//            List<Integer> byProductGroupIds = new ArrayList<>();
//            byProductGroupIds.add(findByProductGroupIds(productGroups.getProductGroupId()).get(0).getPrice());
//
//            System.err.println("byProductGroupIds = "+byProductGroupIds);
//
//            wishResponses.add(WishResponse.builder()
//                    .src("")
//                    .company(productGroups.getCompany())
//                    .price(productGroups.getPrice())
//                    .name(productGroups.getName())
//                    .productGroupId(productGroups.getProductGroupId())
//                    //이 wishId가 왜 되는거지???
//                    .wishId(wish.getWishId())
//                    .productPrice(byProductGroupIds)
////                    .productName("")
//                    .build());
//        });

        return wishResponses;

    }

    /*
    //0529 추가  wish->cart시 옵션 선택을 위하 메서드
 public List<MyPageWishOptionResponse> wishOption(Long customerId) {
        List<Wish> wishes = findAllByCustomerId(customerId);


        List<Product>products = new ArrayList<>();
      for (Wish wish : wishes) {
            Long productGroupId = wish.getProductGroup().getProductGroupId();
            System.err.println("productGroupId = "+productGroupId);
            //List형식 반환은 무조건 addAll 인가??  --> List형태의 리파지토리라서 ..id로 모든걸 받아오니까!
            products.addAll(findByProductGroupIds(productGroupId));
            System.err.println("products = "+products);
            System.err.println("products size= "+products.size());

        }
        List<MyPageWishOptionResponse>wishOptionResponses = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            wishOptionResponses.add(
                    MyPageWishOptionResponse.builder()
                            .productName(products.get(i).getName())
                            .productPrice(products.get(i).getPrice())
//                            .productGroupId()
                            .build()
            );
        }
        return wishOptionResponses;

    }

*/

    //0528추가
    //wish.html -> 체크박스에서 체크된 데이터만 db에서 삭제하기
    public void selectItemDelet(MyPageWishDeleteRequest request) {
        //request안에는 지우기 위해 있어야 할 데이터가 들어있음
        List<Wish> wishes = new ArrayList<>();
        //request에 들어있는 wishId만큼 순환하여 wishId로 테이블을 삭제 하겠다.
        for (int i = 0; i < request.getWishIds().size(); i++) {
            //왜 addAll을 해야하는걸까 ++++++++++++++++왜 실행되는 코드지???
            wishes.addAll(findAllByWishId(request.getWishIds().get(i)));
            System.err.println(findAllByWishId(request.getWishIds().get(i)));

            wishRepository.delete(wishes.get(i));
        }

    }



    public Wish findByCustomerIdAndProductGroupId(Long customerId, Long productGroupId){
        return wishRepository.findByCustomerCustomerIdAndProductGroupProductGroupId(customerId, productGroupId)
                .orElse(null);
    }

    public boolean isWish(Principal principal, Long productGroupId){
        Wish wish = findByCustomerIdAndProductGroupId(customerService.findByUsername(principal.getName()).getCustomerId(), productGroupId);

        return wish == null ? false : true;
    }

    public Wish change(Principal principal, Long productGroupId){
        if(isWish(principal, productGroupId)){
            wishRepository.delete(findByCustomerIdAndProductGroupId(customerService.findByUsername(principal.getName()).getCustomerId(), productGroupId));
            return null;
        }
        else{
            return wishRepository.save(Wish.builder()
                    .productGroup(productGroupRepository.findById(productGroupId).orElseThrow(() -> new IllegalArgumentException("not found productGroup")))
                    .customer(customerService.findByUsername(principal.getName()))
                    .build()
            );
        }
    }

}