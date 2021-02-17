package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepository extends JpaRepository<Image, Integer> {


}
