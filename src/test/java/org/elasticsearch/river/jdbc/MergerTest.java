/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.river.jdbc;

import java.io.IOException;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.testng.annotations.Test;

public class MergerTest {

    @Test
    public void test() throws Exception {
        
        String columns[] = new String[] {"_id","person.salary","person.name", "person.position.name", "person.position.since"};
        String row1[] = new String[]{"1","$1000","Joe Doe", "Worker", "2012-06-12"};
        String row2[] = new String[]{"2","$2000","Bill Smith", "Boss", "2012-06-13"};
        
        MergeListener listener =  new MergeListener() {

            @Override
            public void merged(String index, String type, String id, XContentBuilder builder) throws IOException {
               System.err.println("index="+index + " type="+type + " id="+id+ " builder="+builder.string());
            }
            
        };
        Merger merger = new Merger(listener);
        merger.row(columns, row1);
        merger.row(columns, row2);
        merger.close();
    }
}
