/*
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.inbio.neoportal.filter;

import java.io.IOException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Weight;
import org.apache.lucene.util.OpenBitSet;

/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class DeleteDuplicatesFilter  extends Filter{

    private Query query;
    private String field;

    @Override
    public DocIdSet getDocIdSet(final IndexReader reader) throws IOException {

        DocIdSet docIdSet;

        final Weight weight = query.weight(new IndexSearcher(reader));

        docIdSet = new DocIdSet() {
            public DocIdSetIterator iterator() throws IOException {
                return weight.scorer(reader, true, false);
            }
            public boolean isCacheable() { return false; }
        };

// #############################################################################


    OpenBitSet bits=new OpenBitSet(reader.maxDoc()); //assume all are INvalid

        Term startTerm=new Term(field);


        TermEnum te = reader.terms(startTerm);

        if(te!=null) {
            Term currTerm=te.term();
            while((currTerm!=null)
                    &&(currTerm.field()==startTerm.field())){ //term fieldnames are interned {
                //set non duplicates
                TermDocs td = reader.termDocs(currTerm);

                if(td.next()) {
                        bits.set(td.doc());
                }

                if(!te.next()) {
                    break;
                }
                currTerm=te.term();
            }
        }
        return bits;

// #############################################################################

//        return docIdSet;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
