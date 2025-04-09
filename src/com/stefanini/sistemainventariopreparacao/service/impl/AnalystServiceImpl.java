package com.stefanini.sistemainventariopreparacao.service.impl;

import com.stefanini.sistemainventariopreparacao.dao.impl.AnalystDAO;
import com.stefanini.sistemainventariopreparacao.domain.Analyst;
import com.stefanini.sistemainventariopreparacao.exception.*;
import com.stefanini.sistemainventariopreparacao.service.interf.AnalystService;

import java.util.List;

public class AnalystServiceImpl implements AnalystService {
    public final AnalystDAO analystDAO;

    public AnalystServiceImpl(AnalystDAO analystDAO) {
        this.analystDAO = analystDAO;
    }

    @Override
    public void createOrUpdate(Analyst analyst) throws DatabaseSQLException {
        if(analyst == null || analyst.getName().trim().isEmpty()) throw  new InvalidEntityException("Por favor, informe um analista válido.");

        analyst.setName(normalizeName(analyst.getName().trim()));

        checkDuplicateOrReactivate(analyst);
    }


    public void delete(long id) throws DatabaseSQLException, DeleteFailedException {
        if(id <= 0) throw  new InvalidEntityException("Não foi possível excluir: ID do analista inválido.");
        analystDAO.delete(id);
    }

    public void update(Analyst analyst) throws DatabaseSQLException, UpdateFailedException {
        if(analyst == null) throw  new InvalidEntityException("Não foi possível atualizar: Analista inválido.");
        analystDAO.update(analyst);
    }

    @Override
    public Analyst findById(long id) throws DatabaseSQLException, NotFoundException{
        if(id <= 0) throw new InvalidEntityException("ID inválido!");

        return analystDAO.findByID(id);
    }

    @Override
    public List<Analyst> findAll() throws DatabaseSQLException {

        return analystDAO.findAll();
    }

    private String normalizeName(String name){
        if(name.contains(",")) {
            String[] split = name.split(",");
            if (split.length != 2)
                throw new InvalidEntityException("Formato de nome inválido. Use 'Sobrenome, Nome' ou 'Nome + Sobrenome'.");

            String lastname = split[0].trim();
            String firstname = split[1].trim();
            name  = firstname + " " + lastname;
        }

        return name.toUpperCase();
    }

    private void checkDuplicateOrReactivate(Analyst analyst) {
            List<Analyst> analysts = findAll();

            for (Analyst existing : analysts) {
                if (existing.getName().equals(analyst.getName())) {
                    if (existing.getActive())
                        throw new DuplicateEntityException("Já existe um analista com esse nome!!");

                    update(existing);
                    return;
                }
            }
            analystDAO.create(analyst);
    }
}
