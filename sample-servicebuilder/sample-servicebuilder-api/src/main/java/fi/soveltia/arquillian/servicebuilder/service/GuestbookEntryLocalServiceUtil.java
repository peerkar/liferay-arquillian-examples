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

package fi.soveltia.arquillian.servicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for GuestbookEntry. This utility wraps
 * {@link fi.soveltia.arquillian.servicebuilder.service.impl.GuestbookEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookEntryLocalService
 * @see fi.soveltia.arquillian.servicebuilder.service.base.GuestbookEntryLocalServiceBaseImpl
 * @see fi.soveltia.arquillian.servicebuilder.service.impl.GuestbookEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class GuestbookEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link fi.soveltia.arquillian.servicebuilder.service.impl.GuestbookEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the guestbook entry to the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookEntry the guestbook entry
	* @return the guestbook entry that was added
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry addGuestbookEntry(
		fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry guestbookEntry) {
		return getService().addGuestbookEntry(guestbookEntry);
	}

	/**
	* Creates a new guestbook entry with the primary key. Does not add the guestbook entry to the database.
	*
	* @param entryId the primary key for the new guestbook entry
	* @return the new guestbook entry
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry createGuestbookEntry(
		long entryId) {
		return getService().createGuestbookEntry(entryId);
	}

	/**
	* Deletes the guestbook entry from the database. Also notifies the appropriate model listeners.
	*
	* @param guestbookEntry the guestbook entry
	* @return the guestbook entry that was removed
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry deleteGuestbookEntry(
		fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry guestbookEntry) {
		return getService().deleteGuestbookEntry(guestbookEntry);
	}

	/**
	* Deletes the guestbook entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the guestbook entry
	* @return the guestbook entry that was removed
	* @throws PortalException if a guestbook entry with the primary key could not be found
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry deleteGuestbookEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteGuestbookEntry(entryId);
	}

	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry fetchGuestbookEntry(
		long entryId) {
		return getService().fetchGuestbookEntry(entryId);
	}

	/**
	* Returns the guestbook entry matching the UUID and group.
	*
	* @param uuid the guestbook entry's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook entry, or <code>null</code> if a matching guestbook entry could not be found
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry fetchGuestbookEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchGuestbookEntryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the guestbook entry with the primary key.
	*
	* @param entryId the primary key of the guestbook entry
	* @return the guestbook entry
	* @throws PortalException if a guestbook entry with the primary key could not be found
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry getGuestbookEntry(
		long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGuestbookEntry(entryId);
	}

	/**
	* Returns the guestbook entry matching the UUID and group.
	*
	* @param uuid the guestbook entry's UUID
	* @param groupId the primary key of the group
	* @return the matching guestbook entry
	* @throws PortalException if a matching guestbook entry could not be found
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry getGuestbookEntryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getGuestbookEntryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the guestbook entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param guestbookEntry the guestbook entry
	* @return the guestbook entry that was updated
	*/
	public static fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry updateGuestbookEntry(
		fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry guestbookEntry) {
		return getService().updateGuestbookEntry(guestbookEntry);
	}

	/**
	* Returns the number of guestbook entries.
	*
	* @return the number of guestbook entries
	*/
	public static int getGuestbookEntriesCount() {
		return getService().getGuestbookEntriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.soveltia.arquillian.servicebuilder.model.impl.GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.soveltia.arquillian.servicebuilder.model.impl.GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the guestbook entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link fi.soveltia.arquillian.servicebuilder.model.impl.GuestbookEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of guestbook entries
	* @param end the upper bound of the range of guestbook entries (not inclusive)
	* @return the range of guestbook entries
	*/
	public static java.util.List<fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry> getGuestbookEntries(
		int start, int end) {
		return getService().getGuestbookEntries(start, end);
	}

	/**
	* Returns all the guestbook entries matching the UUID and company.
	*
	* @param uuid the UUID of the guestbook entries
	* @param companyId the primary key of the company
	* @return the matching guestbook entries, or an empty list if no matches were found
	*/
	public static java.util.List<fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry> getGuestbookEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getGuestbookEntriesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of guestbook entries matching the UUID and company.
	*
	* @param uuid the UUID of the guestbook entries
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of guestbook entries
	* @param end the upper bound of the range of guestbook entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching guestbook entries, or an empty list if no matches were found
	*/
	public static java.util.List<fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry> getGuestbookEntriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry> orderByComparator) {
		return getService()
				   .getGuestbookEntriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static GuestbookEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GuestbookEntryLocalService, GuestbookEntryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(GuestbookEntryLocalService.class);
}