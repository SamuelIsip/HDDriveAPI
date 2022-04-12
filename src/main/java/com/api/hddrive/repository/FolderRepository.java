package com.api.hddrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.hddrive.entity.Folder;


@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

}
