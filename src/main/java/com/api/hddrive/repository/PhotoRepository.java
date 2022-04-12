package com.api.hddrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.hddrive.entity.Photo;



@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
