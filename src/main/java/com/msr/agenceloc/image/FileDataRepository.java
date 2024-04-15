package com.msr.agenceloc.image;

import com.msr.agenceloc.automobile.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);
   List<FileData> findAllByAutomobile(Automobile automobile);
}
