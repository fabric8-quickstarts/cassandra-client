/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.apache.camel.cassandraql;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CqlPopulateBean {
	
	public void populate() {
		Cluster cluster = Cluster.builder().addContactPoint("cassandra").build();
		Session session = cluster.connect();
		session.execute("create keyspace if not exists test with replication = {'class':'SimpleStrategy', 'replication_factor':3};");
		session.execute("create table if not exists test.users ( id int primary key, name text );");
		session.execute("insert into test.users (id,name) values (1, 'oscerd') if not exists;");
		session.close();
		cluster.close();
	}

}
