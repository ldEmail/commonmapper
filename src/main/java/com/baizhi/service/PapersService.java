package com.baizhi.service;

import com.baizhi.entity.Papers;

import java.util.List;

public interface PapersService {
    int addPapers(Papers papers);
    List<Papers> queryAll();
}
