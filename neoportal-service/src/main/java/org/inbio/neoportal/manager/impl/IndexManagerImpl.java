/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager.impl;

import org.inbio.neoportal.dao.IndexDAO;
import org.inbio.neoportal.manager.IndexManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author asanabria
 */
@Service
public class IndexManagerImpl implements IndexManager{

    @Autowired
    private IndexDAO indexDAO;

    public IndexManagerImpl(){
        super();
    }

    public void createIndex() throws InterruptedException{
        this.indexDAO.createIndex();
    }

    public IndexDAO getIndexDAO() {
        return indexDAO;
    }

    public void setIndexDAO(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }
}
