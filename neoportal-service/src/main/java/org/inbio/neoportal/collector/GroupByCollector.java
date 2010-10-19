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

package org.inbio.neoportal.collector;

import java.io.IOException;
import java.util.HashSet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.HitCollector;

/**
 *
 * @author asanabria <asanabria@inbio.ac.cr>
 */
public class GroupByCollector extends HitCollector {

    protected IndexReader indexReader;
    protected String groupField;
    protected boolean distinct;
    //protected TLongHashSet set;
    protected HashSet set;
    protected int distinctSize;

    int count = 0;
    int sum = 0;

    public GroupByCollector(HashSet set) {
        this.set = set;
    }


    public String getGroupField()
{
        return groupField;
    }

    public void setGroupField(String groupField)
{
        this.groupField = groupField;
    }

    public IndexReader getIndexReader()
{
        return indexReader;
    }

    public void setIndexReader(IndexReader indexReader)
{
        this.indexReader = indexReader;
    }

    public boolean isDistinct()
{
        return distinct;
    }

    public void setDistinct(boolean distinct)
{
        this.distinct = distinct;
    }

    public void collect(int doc, float score)
{
        if(distinct)
{
            try
{
                Document document = this.indexReader.document(doc);
                if(document != null)
{
                    String s = document.get(groupField);
                    if(s != null)
{
                        set.add(s.hashCode());
                        //set.add(Crc64.generate(s));
                    }
                }
            }
catch (IOException e)
{
    e.printStackTrace();
}
        }
        count++;
        sum += doc;  // use it to avoid any possibility of being optimized away
    }

    public int getCount() { return count; }
    public int getSum() { return sum; }

    public int getDistinctCount()
{
        distinctSize = set.size();
        return distinctSize;
    }
}