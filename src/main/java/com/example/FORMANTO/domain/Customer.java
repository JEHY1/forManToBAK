package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false)
    private Long customerId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_num", nullable = false, unique = true)
    private String phoneNum;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "role", nullable = false)
    private String role;


    //이하 UserDetails 구현
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role)); //인가 리턴
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //이하 계정의 유효성을 검사 함
    //현제 로직 없이 다 유효하다고 판단(테스트)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public Customer(String username, String password, String phoneNum, String email, String adminKey) { //주소 관련 필드는 null 허용, 최초 가입시 입력받지 않음

        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = (adminKey != null && adminKey.equals("hg463jd45djf")) ? "ADMIN" : "CUSTOMER";
        //admin 을 구별하기 위한 값과 비교후 role 부여 (하드코딩됨 나중에 변경 예정)

    }

    public void updateAddress(String postalCode, String roadNameAddress, String detailAddress){
        this.postalCode = postalCode;
        this.roadNameAddress = roadNameAddress;
        this.detailAddress = detailAddress;
    }
}
