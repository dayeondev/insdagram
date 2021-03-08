package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByDirectory(String directory);
    Optional<Image> findById(Long id);

}
