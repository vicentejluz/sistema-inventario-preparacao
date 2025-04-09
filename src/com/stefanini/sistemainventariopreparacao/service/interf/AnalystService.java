package com.stefanini.sistemainventariopreparacao.service.interf;

import com.stefanini.sistemainventariopreparacao.domain.Analyst;

import java.util.List;

public interface AnalystService {
    void createOrUpdate(Analyst analyst);

    void delete(long id);

    void update(Analyst analyst);

    Analyst findById(long id);

    List<Analyst> findAll();
}
