/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.inbio.neoportal.manager.impl;

import org.inbio.neoportal.dao.IndexDAO;
import org.inbio.neoportal.manager.IndexManager;


/**
 *
 * @author asanabria
 */
public class IndexManagerImpl implements IndexManager{

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
