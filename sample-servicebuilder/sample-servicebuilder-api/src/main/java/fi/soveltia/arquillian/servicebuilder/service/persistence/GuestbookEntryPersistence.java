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

package fi.soveltia.arquillian.servicebuilder.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fi.soveltia.arquillian.servicebuilder.exception.NoSuchGuestbookEntryException;
import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;

/**
 * The persistence interface for the guestbook entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see fi.soveltia.arquillian.servicebuilder.service.persistence.impl.GuestbookEntryPersistenceImpl
 * @see GuestbookEntryUtil
 * @generated
 */
@ProviderType
public interface GuestbookEntryPersistence extends BasePersistence<GuestbookEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GuestbookEntryUtil} to access the guestbook entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the guestbook entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching guestbook entries
	*/
	public java.util.List<GuestbookEntry> findByUuid(java.lang.String uuid);

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
	public java.util.List<GuestbookEntry> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<GuestbookEntry> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

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
	public java.util.List<GuestbookEntry> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first guestbook entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook entry
	* @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	*/
	public GuestbookEntry findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the first guestbook entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

	/**
	* Returns the last guestbook entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook entry
	* @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	*/
	public GuestbookEntry findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the last guestbook entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

	/**
	* Returns the guestbook entries before and after the current guestbook entry in the ordered set where uuid = &#63;.
	*
	* @param entryId the primary key of the current guestbook entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next guestbook entry
	* @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	*/
	public GuestbookEntry[] findByUuid_PrevAndNext(long entryId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Removes all the guestbook entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of guestbook entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching guestbook entries
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the guestbook entry where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGuestbookEntryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook entry
	* @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	*/
	public GuestbookEntry findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the guestbook entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the guestbook entry where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the guestbook entry that was removed
	*/
	public GuestbookEntry removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the number of guestbook entries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching guestbook entries
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the guestbook entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching guestbook entries
	*/
	public java.util.List<GuestbookEntry> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<GuestbookEntry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<GuestbookEntry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

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
	public java.util.List<GuestbookEntry> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook entry
	* @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	*/
	public GuestbookEntry findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the first guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

	/**
	* Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook entry
	* @throws NoSuchGuestbookEntryException if a matching guestbook entry could not be found
	*/
	public GuestbookEntry findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the last guestbook entry in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public GuestbookEntry fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

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
	public GuestbookEntry[] findByUuid_C_PrevAndNext(long entryId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator)
		throws NoSuchGuestbookEntryException;

	/**
	* Removes all the guestbook entries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of guestbook entries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching guestbook entries
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the guestbook entry in the entity cache if it is enabled.
	*
	* @param guestbookEntry the guestbook entry
	*/
	public void cacheResult(GuestbookEntry guestbookEntry);

	/**
	* Caches the guestbook entries in the entity cache if it is enabled.
	*
	* @param guestbookEntries the guestbook entries
	*/
	public void cacheResult(java.util.List<GuestbookEntry> guestbookEntries);

	/**
	* Creates a new guestbook entry with the primary key. Does not add the guestbook entry to the database.
	*
	* @param entryId the primary key for the new guestbook entry
	* @return the new guestbook entry
	*/
	public GuestbookEntry create(long entryId);

	/**
	* Removes the guestbook entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the guestbook entry
	* @return the guestbook entry that was removed
	* @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	*/
	public GuestbookEntry remove(long entryId)
		throws NoSuchGuestbookEntryException;

	public GuestbookEntry updateImpl(GuestbookEntry guestbookEntry);

	/**
	* Returns the guestbook entry with the primary key or throws a {@link NoSuchGuestbookEntryException} if it could not be found.
	*
	* @param entryId the primary key of the guestbook entry
	* @return the guestbook entry
	* @throws NoSuchGuestbookEntryException if a guestbook entry with the primary key could not be found
	*/
	public GuestbookEntry findByPrimaryKey(long entryId)
		throws NoSuchGuestbookEntryException;

	/**
	* Returns the guestbook entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the guestbook entry
	* @return the guestbook entry, or <code>null</code> if a guestbook entry with the primary key could not be found
	*/
	public GuestbookEntry fetchByPrimaryKey(long entryId);

	@Override
	public java.util.Map<java.io.Serializable, GuestbookEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the guestbook entries.
	*
	* @return the guestbook entries
	*/
	public java.util.List<GuestbookEntry> findAll();

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
	public java.util.List<GuestbookEntry> findAll(int start, int end);

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
	public java.util.List<GuestbookEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator);

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
	public java.util.List<GuestbookEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GuestbookEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the guestbook entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of guestbook entries.
	*
	* @return the number of guestbook entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}