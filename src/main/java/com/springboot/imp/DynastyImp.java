package com.springboot.imp;

import com.springboot.dao.DynastyDao;
import com.springboot.entity.TDynasty;
import com.springboot.service.DynastyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DynastyImp implements DynastyService {
    @Autowired
    private DynastyDao dynastyDao;


    @Override
    public List<TDynasty> getAllDynasty() {
        return dynastyDao.findAll();
    }
}