package com.stefanini.sistemainventariopreparacao;

import com.stefanini.sistemainventariopreparacao.dao.impl.AnalystDAO;
import com.stefanini.sistemainventariopreparacao.domain.Analyst;
import com.stefanini.sistemainventariopreparacao.exception.*;
import com.stefanini.sistemainventariopreparacao.service.impl.AnalystServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AnalystDAO analystDAO = new AnalystDAO();
            AnalystServiceImpl analystService = new AnalystServiceImpl(analystDAO);

            List<Analyst> analysts = analystService.findAll();

            analysts.forEach(a -> System.out.println(a.getName()));

        }catch(DuplicateEntityException | InvalidEntityException | DatabaseSQLException | DeleteFailedException |
               NotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
