package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
