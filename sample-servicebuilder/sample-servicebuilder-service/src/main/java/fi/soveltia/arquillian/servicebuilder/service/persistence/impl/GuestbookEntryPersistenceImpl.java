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

package fi.soveltia.arquillian.servicebuilder.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import fi.soveltia.arquillian.servicebuilder.exception.NoSuchGuestbookEntryException;
import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;
import fi.soveltia.arquillian.servicebuilder.model.impl.GuestbookEntryImpl;
import fi.soveltia.arquillian.servicebuilder.model.impl.GuestbookEntryModelImpl;
import fi.soveltia.arquillian.servicebuilder.service.persistence.GuestbookEntryPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the guestbook entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookEntryPersistence
 * @see fi.soveltia.arquillian.servicebuilder.service.persistence.GuestbookEntryUtil
 * @generated
 */
@ProviderType
public class GuestbookEntryPersistenceImpl extends BasePersistenceImpl<GuestbookEntry>
	implements GuestbookEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GuestbookEntryUtil} to access the guestbook entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GuestbookEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			GuestbookEntryModelImpl.UUID_COLUMN_BITMASK |
			GuestbookEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the guestbook entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid(String uuid, int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<GuestbookEntry> list = null;

		if (retrieveFromCache) {
			list = (List<GuestbookEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GuestbookEntry guestbookEntry : list) {
					if (!Objects.equals(uuid, guestbookEntry.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(GuestbookEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry findByUuid_First(String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByUuid_First(uuid,
				orderByComparator);

		if (guestbookEntry != null) {
			return guestbookEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookEntryException(msg.toString());
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUuid_First(String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		List<GuestbookEntry> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry findByUuid_Last(String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByUuid_Last(uuid, orderByComparator);

		if (guestbookEntry != null) {
			return guestbookEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookEntryException(msg.toString());
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUuid_Last(String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<GuestbookEntry> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set where uuid = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry[] findByUuid_PrevAndNext(long entryId, String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestbookEntry[] array = new GuestbookEntryImpl[3];

			array[0] = getByUuid_PrevAndNext(session, guestbookEntry, uuid,
					orderByComparator, true);

			array[1] = guestbookEntry;

			array[2] = getByUuid_PrevAndNext(session, guestbookEntry, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestbookEntry getByUuid_PrevAndNext(Session session,
		GuestbookEntry guestbookEntry, String uuid,
		OrderByComparator<GuestbookEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(GuestbookEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(guestbookEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<GuestbookEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbook entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (GuestbookEntry guestbookEntry : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(guestbookEntry);
		}
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching guestbook entries
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "guestbookEntry.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "guestbookEntry.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(guestbookEntry.uuid IS NULL OR guestbookEntry.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			GuestbookEntryModelImpl.UUID_COLUMN_BITMASK |
			GuestbookEntryModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGuestbookEntryException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry findByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByUUID_G(uuid, groupId);

		if (guestbookEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchGuestbookEntryException(msg.toString());
		}

		return guestbookEntry;
	}

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof GuestbookEntry) {
			GuestbookEntry guestbookEntry = (GuestbookEntry)result;

			if (!Objects.equals(uuid, guestbookEntry.getUuid()) ||
					(groupId != guestbookEntry.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<GuestbookEntry> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					GuestbookEntry guestbookEntry = list.get(0);

					result = guestbookEntry;

					cacheResult(guestbookEntry);

					if ((guestbookEntry.getUuid() == null) ||
							!guestbookEntry.getUuid().equals(uuid) ||
							(guestbookEntry.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, guestbookEntry);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (GuestbookEntry)result;
		}
	}

	/**
	 * Removes the guestbook entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the guestbook entry that was removed
	 */
	@Override
	public GuestbookEntry removeByUUID_G(String uuid, long groupId)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = findByUUID_G(uuid, groupId);

		return remove(guestbookEntry);
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching guestbook entries
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "guestbookEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "guestbookEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(guestbookEntry.uuid IS NULL OR guestbookEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "guestbookEntry.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED,
			GuestbookEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			GuestbookEntryModelImpl.UUID_COLUMN_BITMASK |
			GuestbookEntryModelImpl.COMPANYID_COLUMN_BITMASK |
			GuestbookEntryModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<GuestbookEntry> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<GuestbookEntry> list = null;

		if (retrieveFromCache) {
			list = (List<GuestbookEntry>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (GuestbookEntry guestbookEntry : list) {
					if (!Objects.equals(uuid, guestbookEntry.getUuid()) ||
							(companyId != guestbookEntry.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(GuestbookEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (guestbookEntry != null) {
			return guestbookEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookEntryException(msg.toString());
	}

	/**
	 * Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		List<GuestbookEntry> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry
	 * @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (guestbookEntry != null) {
			return guestbookEntry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchGuestbookEntryException(msg.toString());
	}

	/**
	 * Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	 */
	@Override
	public GuestbookEntry fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<GuestbookEntry> list = findByUuid_C(uuid, companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the guestbook entries before and after the current guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param entryId the primary key of the current guestbook entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry[] findByUuid_C_PrevAndNext(long entryId, String uuid,
		long companyId, OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = findByPrimaryKey(entryId);

		Session session = null;

		try {
			session = openSession();

			GuestbookEntry[] array = new GuestbookEntryImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, guestbookEntry, uuid,
					companyId, orderByComparator, true);

			array[1] = guestbookEntry;

			array[2] = getByUuid_C_PrevAndNext(session, guestbookEntry, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected GuestbookEntry getByUuid_C_PrevAndNext(Session session,
		GuestbookEntry guestbookEntry, String uuid, long companyId,
		OrderByComparator<GuestbookEntry> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(GuestbookEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(guestbookEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<GuestbookEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the guestbook entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (GuestbookEntry guestbookEntry : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(guestbookEntry);
		}
	}

	/**
	 * Returns the number of guestbook entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching guestbook entries
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_GUESTBOOKENTRY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "guestbookEntry.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "guestbookEntry.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(guestbookEntry.uuid IS NULL OR guestbookEntry.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "guestbookEntry.companyId = ?";

	public GuestbookEntryPersistenceImpl() {
		setModelClass(GuestbookEntry.class);
	}

	/**
	 * Caches the guestbook entry in the entity cache if it is enabled.
	 *
	 * @param guestbookEntry the guestbook entry
	 */
	@Override
	public void cacheResult(GuestbookEntry guestbookEntry) {
		entityCache.putResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryImpl.class, guestbookEntry.getPrimaryKey(),
			guestbookEntry);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { guestbookEntry.getUuid(), guestbookEntry.getGroupId() },
			guestbookEntry);

		guestbookEntry.resetOriginalValues();
	}

	/**
	 * Caches the guestbook entries in the entity cache if it is enabled.
	 *
	 * @param guestbookEntries the guestbook entries
	 */
	@Override
	public void cacheResult(List<GuestbookEntry> guestbookEntries) {
		for (GuestbookEntry guestbookEntry : guestbookEntries) {
			if (entityCache.getResult(
						GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
						GuestbookEntryImpl.class, guestbookEntry.getPrimaryKey()) == null) {
				cacheResult(guestbookEntry);
			}
			else {
				guestbookEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all guestbook entries.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GuestbookEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the guestbook entry.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GuestbookEntry guestbookEntry) {
		entityCache.removeResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryImpl.class, guestbookEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((GuestbookEntryModelImpl)guestbookEntry);
	}

	@Override
	public void clearCache(List<GuestbookEntry> guestbookEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GuestbookEntry guestbookEntry : guestbookEntries) {
			entityCache.removeResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
				GuestbookEntryImpl.class, guestbookEntry.getPrimaryKey());

			clearUniqueFindersCache((GuestbookEntryModelImpl)guestbookEntry);
		}
	}

	protected void cacheUniqueFindersCache(
		GuestbookEntryModelImpl guestbookEntryModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					guestbookEntryModelImpl.getUuid(),
					guestbookEntryModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				guestbookEntryModelImpl);
		}
		else {
			if ((guestbookEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookEntryModelImpl.getUuid(),
						guestbookEntryModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					guestbookEntryModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		GuestbookEntryModelImpl guestbookEntryModelImpl) {
		Object[] args = new Object[] {
				guestbookEntryModelImpl.getUuid(),
				guestbookEntryModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((guestbookEntryModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					guestbookEntryModelImpl.getOriginalUuid(),
					guestbookEntryModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new guestbook entry with the primary key. Does not add the guestbook entry to the database.
	 *
	 * @param entryId the primary key for the new guestbook entry
	 * @return the new guestbook entry
	 */
	@Override
	public GuestbookEntry create(long entryId) {
		GuestbookEntry guestbookEntry = new GuestbookEntryImpl();

		guestbookEntry.setNew(true);
		guestbookEntry.setPrimaryKey(entryId);

		String uuid = PortalUUIDUtil.generate();

		guestbookEntry.setUuid(uuid);

		guestbookEntry.setCompanyId(companyProvider.getCompanyId());

		return guestbookEntry;
	}

	/**
	 * Removes the guestbook entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry that was removed
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry remove(long entryId)
		throws NoSuchGuestbookEntryException {
		return remove((Serializable)entryId);
	}

	/**
	 * Removes the guestbook entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the guestbook entry
	 * @return the guestbook entry that was removed
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry remove(Serializable primaryKey)
		throws NoSuchGuestbookEntryException {
		Session session = null;

		try {
			session = openSession();

			GuestbookEntry guestbookEntry = (GuestbookEntry)session.get(GuestbookEntryImpl.class,
					primaryKey);

			if (guestbookEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGuestbookEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(guestbookEntry);
		}
		catch (NoSuchGuestbookEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected GuestbookEntry removeImpl(GuestbookEntry guestbookEntry) {
		guestbookEntry = toUnwrappedModel(guestbookEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(guestbookEntry)) {
				guestbookEntry = (GuestbookEntry)session.get(GuestbookEntryImpl.class,
						guestbookEntry.getPrimaryKeyObj());
			}

			if (guestbookEntry != null) {
				session.delete(guestbookEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (guestbookEntry != null) {
			clearCache(guestbookEntry);
		}

		return guestbookEntry;
	}

	@Override
	public GuestbookEntry updateImpl(GuestbookEntry guestbookEntry) {
		guestbookEntry = toUnwrappedModel(guestbookEntry);

		boolean isNew = guestbookEntry.isNew();

		GuestbookEntryModelImpl guestbookEntryModelImpl = (GuestbookEntryModelImpl)guestbookEntry;

		if (Validator.isNull(guestbookEntry.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			guestbookEntry.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (guestbookEntry.isNew()) {
				session.save(guestbookEntry);

				guestbookEntry.setNew(false);
			}
			else {
				guestbookEntry = (GuestbookEntry)session.merge(guestbookEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !GuestbookEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((guestbookEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookEntryModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { guestbookEntryModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((guestbookEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						guestbookEntryModelImpl.getOriginalUuid(),
						guestbookEntryModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						guestbookEntryModelImpl.getUuid(),
						guestbookEntryModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
			GuestbookEntryImpl.class, guestbookEntry.getPrimaryKey(),
			guestbookEntry, false);

		clearUniqueFindersCache(guestbookEntryModelImpl);
		cacheUniqueFindersCache(guestbookEntryModelImpl, isNew);

		guestbookEntry.resetOriginalValues();

		return guestbookEntry;
	}

	protected GuestbookEntry toUnwrappedModel(GuestbookEntry guestbookEntry) {
		if (guestbookEntry instanceof GuestbookEntryImpl) {
			return guestbookEntry;
		}

		GuestbookEntryImpl guestbookEntryImpl = new GuestbookEntryImpl();

		guestbookEntryImpl.setNew(guestbookEntry.isNew());
		guestbookEntryImpl.setPrimaryKey(guestbookEntry.getPrimaryKey());

		guestbookEntryImpl.setUuid(guestbookEntry.getUuid());
		guestbookEntryImpl.setEntryId(guestbookEntry.getEntryId());
		guestbookEntryImpl.setGroupId(guestbookEntry.getGroupId());
		guestbookEntryImpl.setCompanyId(guestbookEntry.getCompanyId());
		guestbookEntryImpl.setUserId(guestbookEntry.getUserId());
		guestbookEntryImpl.setCreateDate(guestbookEntry.getCreateDate());
		guestbookEntryImpl.setText(guestbookEntry.getText());
		guestbookEntryImpl.setAuthor(guestbookEntry.getAuthor());

		return guestbookEntryImpl;
	}

	/**
	 * Returns the guestbook entry with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the guestbook entry
	 * @return the guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGuestbookEntryException {
		GuestbookEntry guestbookEntry = fetchByPrimaryKey(primaryKey);

		if (guestbookEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGuestbookEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return guestbookEntry;
	}

	/**
	 * Returns the guestbook entry with the primary key or throws a {@link NoSuchGuestbookEntryException} if it could not be found.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry
	 * @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry findByPrimaryKey(long entryId)
		throws NoSuchGuestbookEntryException {
		return findByPrimaryKey((Serializable)entryId);
	}

	/**
	 * Returns the guestbook entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the guestbook entry
	 * @return the guestbook entry, or <code>null</code> if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
				GuestbookEntryImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GuestbookEntry guestbookEntry = (GuestbookEntry)serializable;

		if (guestbookEntry == null) {
			Session session = null;

			try {
				session = openSession();

				guestbookEntry = (GuestbookEntry)session.get(GuestbookEntryImpl.class,
						primaryKey);

				if (guestbookEntry != null) {
					cacheResult(guestbookEntry);
				}
				else {
					entityCache.putResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
						GuestbookEntryImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
					GuestbookEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return guestbookEntry;
	}

	/**
	 * Returns the guestbook entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entryId the primary key of the guestbook entry
	 * @return the guestbook entry, or <code>null</code> if a guestbook entry with the primary key could not be found
	 */
	@Override
	public GuestbookEntry fetchByPrimaryKey(long entryId) {
		return fetchByPrimaryKey((Serializable)entryId);
	}

	@Override
	public Map<Serializable, GuestbookEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GuestbookEntry> map = new HashMap<Serializable, GuestbookEntry>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GuestbookEntry guestbookEntry = fetchByPrimaryKey(primaryKey);

			if (guestbookEntry != null) {
				map.put(primaryKey, guestbookEntry);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
					GuestbookEntryImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GuestbookEntry)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GUESTBOOKENTRY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (GuestbookEntry guestbookEntry : (List<GuestbookEntry>)q.list()) {
				map.put(guestbookEntry.getPrimaryKeyObj(), guestbookEntry);

				cacheResult(guestbookEntry);

				uncachedPrimaryKeys.remove(guestbookEntry.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GuestbookEntryModelImpl.ENTITY_CACHE_ENABLED,
					GuestbookEntryImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the guestbook entries.
	 *
	 * @return the guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @return the range of guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findAll(int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the guestbook entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of guestbook entries
	 * @param end the upper bound of the range of guestbook entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of guestbook entries
	 */
	@Override
	public List<GuestbookEntry> findAll(int start, int end,
		OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<GuestbookEntry> list = null;

		if (retrieveFromCache) {
			list = (List<GuestbookEntry>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GUESTBOOKENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GUESTBOOKENTRY;

				if (pagination) {
					sql = sql.concat(GuestbookEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GuestbookEntry>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the guestbook entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GuestbookEntry guestbookEntry : findAll()) {
			remove(guestbookEntry);
		}
	}

	/**
	 * Returns the number of guestbook entries.
	 *
	 * @return the number of guestbook entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GUESTBOOKENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return GuestbookEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the guestbook entry persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GuestbookEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GUESTBOOKENTRY = "SELECT guestbookEntry FROM GuestbookEntry guestbookEntry";
	private static final String _SQL_SELECT_GUESTBOOKENTRY_WHERE_PKS_IN = "SELECT guestbookEntry FROM GuestbookEntry guestbookEntry WHERE entryId IN (";
	private static final String _SQL_SELECT_GUESTBOOKENTRY_WHERE = "SELECT guestbookEntry FROM GuestbookEntry guestbookEntry WHERE ";
	private static final String _SQL_COUNT_GUESTBOOKENTRY = "SELECT COUNT(guestbookEntry) FROM GuestbookEntry guestbookEntry";
	private static final String _SQL_COUNT_GUESTBOOKENTRY_WHERE = "SELECT COUNT(guestbookEntry) FROM GuestbookEntry guestbookEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "guestbookEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GuestbookEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No GuestbookEntry exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(GuestbookEntryPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "text"
			});
}