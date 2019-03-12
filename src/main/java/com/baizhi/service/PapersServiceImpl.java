package com.baizhi.service;

import com.baizhi.dao.PapersDao;
import com.baizhi.entity.Papers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PapersServiceImpl implements PapersService{

    @Autowired
    private PapersDao papersDao;

    @Override
    public int addPapers(Papers papers) {
        String id = UUID.randomUUID().toString();
        papers.setId(id);
        int insert = papersDao.insert(papers);
        return insert;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Papers> queryAll() {
        List<Papers> papers = papersDao.selectAll();
        return papers;
    }
}
