package com.bunny.aop.worker;

import com.bunny.aop.annotation.ReadOnly;
import com.bunny.aop.annotation.ReadWriteLockManaged;
import com.bunny.aop.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@ReadWriteLockManaged
public class Pot {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void feedAnimal() throws Exception {
        if (Math.random() > 0.3d) {
            // resemble the network delay in connecting to e.g. register center.
            Thread.sleep(2000);
            throw new Exception();
        }
        System.out.println("feed animal");
    }

    public void petAnimal() throws Exception {
        if (Math.random() > 0.3d) {
            // resemble the network delay in connecting to e.g. register center.
            Thread.sleep(2000);
            throw new Exception();
        }
        System.out.println("pet animal");
    }

    @Transactional
    @ReadOnly
    public void queryAnimal() {
//        可以正常执行
        jdbcTemplate.execute("insert into products (id, name, description, price) values(1006,'ProductE', 'Product E description', 5)");
//        抛出异常Duplicate entry '1005' for key 'PRIMARY'
        jdbcTemplate.execute("insert into products (id, name, description, price) values(1005,'ProductF', 'Product F description', 6)");
    }
}
