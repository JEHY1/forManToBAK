package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.*;
import com.example.FORMANTO.dto.MypageReviewResponse;
import com.example.FORMANTO.dto.ReviewViewResponse;
import com.example.FORMANTO.dto.user.review.ReviewViewRequest;
import com.example.FORMANTO.repository.PaymentRepository;
import com.example.FORMANTO.repository.ReviewImgRepository;
import com.example.FORMANTO.repository.ReviewRepository;
import com.example.FORMANTO.repository.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImgRepository reviewImgRepository;
    //추가
    private final SaleProductRepository saleProductRepository;
    private final PaymentRepository paymentRepository;
    private final ProductService productService;

    public List<Review> findByProductGroupId(Long productGroupId){
        return reviewRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found review"));
    }

    //추가
    public List<SaleProduct> findByPaymentId(Long paymentId){
        return saleProductRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("not found saleProduect"));
    }

    //주문상세, 주문취소 등에도 쓸 수 있을지도
//    public List<Payment> findByCustomerIdAndPaymentId(Long customerId, Long paymentId) {
//        return paymentRepository.findByCustomerIdAndPaymentId(customerId,paymentId)
//                .orElseThrow(()-> new IllegalArgumentException("not found payment"));
//    }
//    //

    //리뷰
    public List<Payment> findByCustomerId(Long customerId){
        return paymentRepository.findByCustomerId(customerId)
                .orElseThrow(()-> new IllegalArgumentException("not found payment"));
    }

    //productId로 productGroupid 들고오기
    public List<ReviewViewResponse> productReviews(Long productGroupId){
        List<Review> reviews = findByProductGroupId(productGroupId);
        List<ReviewViewResponse> reviewResponses = new ArrayList<>();
        reviews.forEach(review -> {
            List<String> reviewImgs = new ArrayList<>();
            List<ReviewImg> imgs = reviewImgRepository.findByReviewId(review.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("not found img"));  //orElseThrow사용하면 Optional벗기기 가능 null잡기
            imgs.forEach(img -> {
                reviewImgs.add(img.getReviewImgSrc());
            });

            reviewResponses.add(ReviewViewResponse.builder()
                    .username(review.getUsername())
                    .content(review.getContent())
                    .imgs(reviewImgs)
                    .build());
        });

        return reviewResponses;
    }

    //1.자바로 dto에 company와 name만들어서 하던가  아니면 2.oneToMany로 join을 해서 proudtgroupid를 긁어오던가
    //mypage의 리뷰란에서 db->view로 데이터 들고오기
    public List<MypageReviewResponse> mypageReview(Long customerId){

        //payment bd의 customerId로 paymentId 추출?
        List<Payment> payments = findByCustomerId(customerId);
        System.err.println("payments = "+Arrays.toString(payments.toArray()));


        //이부분 다시이해하기 paymentId를 추출하는 부분인듯..?       paymets에서 paymentid인덱스만 추출하는 부분ㄴ?
        List<Long> paymentIds = payments.stream().mapToLong(payment -> payment.getPaymentId()).boxed().toList();
        System.err.println("paymentIds : " + paymentIds);

        //payment로 sale_product tb를 가져올것임

//        List<Long> productGroupId = payments.stream().mapToLong(productGroupid-> productGroupid.get)

        List<MypageReviewResponse> mypageReview = new ArrayList<>();
        List<SaleProduct> saleProducts = new ArrayList<>();
//        List<ProductGroup> productGroups = new ArrayList<>();

        //리스트에서 payment의 인덱스 추출
        List<Long> paymentIdsIndex= new ArrayList<>();
        paymentIdsIndex.addAll(paymentIds);
        System.err.println("paymentIdsIndex = "+paymentIdsIndex.size()
        );

        //추출한 인덱스로 saleProduct db접근
        for (int i = 0; i < 1; i++) {//문제 있는 코드///////////////////////////
            saleProducts = findByPaymentId(paymentIdsIndex.get(i));
            System.err.println("saleProductsize = "+saleProducts.size());
        }
/*
        //payment의 인덱스로 productid접근
        List<Long> productId = new ArrayList<>();
        List<String>products = new ArrayList<>();
        for (int i = 0; i < paymentIds.size(); i++) {
            productId.add(saleProducts.get(i).getProductId());
            System.err.println("productId = "+productId);
            System.err.println("productId size = "+productId.size());

            //productid로 product_td접근
            Product productByProductId = productService.findProductByProductId(productId.get(i));
            products.add(productByProductId.getName());

            //product_td의 productGroupId로


        }
        System.err.println("products = "+products);

*/
        //MypageREviewResponse dto안에 데이터 넣기
        saleProducts.forEach(saleProduct -> {
            mypageReview.add(MypageReviewResponse.builder()
//                    .name(products.get(1))
                    .name("")
                    .company("회사")
                    .imgsrc(null)
                    .deadline(saleProduct.getReviewDeadline())
                    .reviewId(saleProduct.getReviewId())//null이면 작성 아니면 작성막기 해야함
                    .build());
        });



        //sale_product bd의 paymentId들고오기
        //결제된 상품에 대한 리뷰를 써야함

        //회사명,
        return mypageReview;


    }


    //리뷰db저장하기
    public Review save(ReviewViewRequest request){

        Review review = Review.builder()
                .username("user1")
                .productGroupId(1L)
                .content(request.getContent())
                .build();

        Review saved = reviewRepository.save(review);

        return saved;
    }

    public Review findById(Long id){//db->아이디 읽어서 고객이 리뷰쓸 제품 찾기
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

}