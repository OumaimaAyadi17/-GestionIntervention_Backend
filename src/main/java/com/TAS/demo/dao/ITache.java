package com.TAS.demo.dao;

import com.TAS.demo.models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITache extends JpaRepository<Tache,Long>{
}
