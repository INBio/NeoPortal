/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.inbio.neoportal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderPersistenceTests {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	@Transactional
	public void testSaveOrderWithItems() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		order.getItems().add(new Item());
		session.save(order);
		session.flush();
		assertNotNull(order.getId());
	}

	@Test
	@Transactional
	public void testSaveAndGet() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		order.getItems().add(new Item());
		session.save(order);
		session.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		session.clear();
		Order other = (Order) session.get(Order.class, order.getId());
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
	}

	@Test
	@Transactional
	public void testSaveAndFind() throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Order order = new Order();
		Item item = new Item();
		item.setProduct("foo");
		order.getItems().add(item);
		session.save(order);
		session.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		session.clear();
		Order other = (Order) session.createQuery("select o from Order o join o.items i where i.product=:product")
				.setString("product", "foo").uniqueResult();
		assertEquals(1, other.getItems().size());
		assertEquals(other, other.getItems().iterator().next().getOrder());
	}

}
