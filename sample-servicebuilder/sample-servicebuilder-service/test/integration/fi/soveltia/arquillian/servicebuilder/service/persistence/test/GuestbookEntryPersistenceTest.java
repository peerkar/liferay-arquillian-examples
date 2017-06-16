/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package fi.soveltia.arquillian.servicebuilder.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import fi.soveltia.arquillian.servicebuilder.exception.NoSuchGuestbookEntryException;
import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;
import fi.soveltia.arquillian.servicebuilder.service.GuestbookEntryLocalServiceUtil;
import fi.soveltia.arquillian.servicebuilder.service.persistence.GuestbookEntryPersistence;
import fi.soveltia.arquillian.servicebuilder.service.persistence.GuestbookEntryUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class GuestbookEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = GuestbookEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<GuestbookEntry> iterator = _guestbookEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GuestbookEntry guestbookEntry = _persistence.create(pk);

		Assert.assertNotNull(guestbookEntry);

		Assert.assertEquals(guestbookEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		_persistence.remove(newGuestbookEntry);

		GuestbookEntry existingGuestbookEntry = _persistence.fetchByPrimaryKey(newGuestbookEntry.getPrimaryKey());

		Assert.assertNull(existingGuestbookEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addGuestbookEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GuestbookEntry newGuestbookEntry = _persistence.create(pk);

		newGuestbookEntry.setUuid(RandomTestUtil.randomString());

		newGuestbookEntry.setGroupId(RandomTestUtil.nextLong());

		newGuestbookEntry.setCompanyId(RandomTestUtil.nextLong());

		newGuestbookEntry.setUserId(RandomTestUtil.nextLong());

		newGuestbookEntry.setCreateDate(RandomTestUtil.nextDate());

		newGuestbookEntry.setText(RandomTestUtil.randomString());

		newGuestbookEntry.setAuthor(RandomTestUtil.randomString());

		_guestbookEntries.add(_persistence.update(newGuestbookEntry));

		GuestbookEntry existingGuestbookEntry = _persistence.findByPrimaryKey(newGuestbookEntry.getPrimaryKey());

		Assert.assertEquals(existingGuestbookEntry.getUuid(),
			newGuestbookEntry.getUuid());
		Assert.assertEquals(existingGuestbookEntry.getEntryId(),
			newGuestbookEntry.getEntryId());
		Assert.assertEquals(existingGuestbookEntry.getGroupId(),
			newGuestbookEntry.getGroupId());
		Assert.assertEquals(existingGuestbookEntry.getCompanyId(),
			newGuestbookEntry.getCompanyId());
		Assert.assertEquals(existingGuestbookEntry.getUserId(),
			newGuestbookEntry.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingGuestbookEntry.getCreateDate()),
			Time.getShortTimestamp(newGuestbookEntry.getCreateDate()));
		Assert.assertEquals(existingGuestbookEntry.getText(),
			newGuestbookEntry.getText());
		Assert.assertEquals(existingGuestbookEntry.getAuthor(),
			newGuestbookEntry.getAuthor());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUUID_G(StringPool.NULL, 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		GuestbookEntry existingGuestbookEntry = _persistence.findByPrimaryKey(newGuestbookEntry.getPrimaryKey());

		Assert.assertEquals(existingGuestbookEntry, newGuestbookEntry);
	}

	@Test(expected = NoSuchGuestbookEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<GuestbookEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("svc_test_GuestbookEntry",
			"uuid", true, "entryId", true, "groupId", true, "companyId", true,
			"userId", true, "createDate", true, "text", true, "author", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		GuestbookEntry existingGuestbookEntry = _persistence.fetchByPrimaryKey(newGuestbookEntry.getPrimaryKey());

		Assert.assertEquals(existingGuestbookEntry, newGuestbookEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GuestbookEntry missingGuestbookEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingGuestbookEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		GuestbookEntry newGuestbookEntry1 = addGuestbookEntry();
		GuestbookEntry newGuestbookEntry2 = addGuestbookEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbookEntry1.getPrimaryKey());
		primaryKeys.add(newGuestbookEntry2.getPrimaryKey());

		Map<Serializable, GuestbookEntry> guestbookEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, guestbookEntries.size());
		Assert.assertEquals(newGuestbookEntry1,
			guestbookEntries.get(newGuestbookEntry1.getPrimaryKey()));
		Assert.assertEquals(newGuestbookEntry2,
			guestbookEntries.get(newGuestbookEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, GuestbookEntry> guestbookEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(guestbookEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbookEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, GuestbookEntry> guestbookEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, guestbookEntries.size());
		Assert.assertEquals(newGuestbookEntry,
			guestbookEntries.get(newGuestbookEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, GuestbookEntry> guestbookEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(guestbookEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newGuestbookEntry.getPrimaryKey());

		Map<Serializable, GuestbookEntry> guestbookEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, guestbookEntries.size());
		Assert.assertEquals(newGuestbookEntry,
			guestbookEntries.get(newGuestbookEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = GuestbookEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<GuestbookEntry>() {
				@Override
				public void performAction(GuestbookEntry guestbookEntry) {
					Assert.assertNotNull(guestbookEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GuestbookEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				newGuestbookEntry.getEntryId()));

		List<GuestbookEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		GuestbookEntry existingGuestbookEntry = result.get(0);

		Assert.assertEquals(existingGuestbookEntry, newGuestbookEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GuestbookEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				RandomTestUtil.nextLong()));

		List<GuestbookEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GuestbookEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		Object newEntryId = newGuestbookEntry.getEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { newEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntryId = result.get(0);

		Assert.assertEquals(existingEntryId, newEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(GuestbookEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		GuestbookEntry newGuestbookEntry = addGuestbookEntry();

		_persistence.clearCache();

		GuestbookEntry existingGuestbookEntry = _persistence.findByPrimaryKey(newGuestbookEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingGuestbookEntry.getUuid(),
				ReflectionTestUtil.invoke(existingGuestbookEntry,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingGuestbookEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingGuestbookEntry,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected GuestbookEntry addGuestbookEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		GuestbookEntry guestbookEntry = _persistence.create(pk);

		guestbookEntry.setUuid(RandomTestUtil.randomString());

		guestbookEntry.setGroupId(RandomTestUtil.nextLong());

		guestbookEntry.setCompanyId(RandomTestUtil.nextLong());

		guestbookEntry.setUserId(RandomTestUtil.nextLong());

		guestbookEntry.setCreateDate(RandomTestUtil.nextDate());

		guestbookEntry.setText(RandomTestUtil.randomString());

		guestbookEntry.setAuthor(RandomTestUtil.randomString());

		_guestbookEntries.add(_persistence.update(guestbookEntry));

		return guestbookEntry;
	}

	private List<GuestbookEntry> _guestbookEntries = new ArrayList<GuestbookEntry>();
	private GuestbookEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}