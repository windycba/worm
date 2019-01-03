package com.wei.worm.dao;

import com.wei.worm.entity.DoubleColorBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoubleColorBallDao extends JpaRepository<DoubleColorBall,Long>,
        JpaSpecificationExecutor<DoubleColorBallDao> {

}
