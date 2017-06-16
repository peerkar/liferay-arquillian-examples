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

package fi.soveltia.arquillian.servicebuilder.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link GuestbookEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookEntry
 * @generated
 */
@ProviderType
public class GuestbookEntryWrapper implements GuestbookEntry,
	ModelWrapper<GuestbookEntry> {
	public GuestbookEntryWrapper(GuestbookEntry guestbookEntry) {
		_guestbookEntry = guestbookEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return GuestbookEntry.class;
	}

	@Override
	public String getModelClassName() {
		return GuestbookEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("entryId", getEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("text", getText());
		attributes.put("author", getAuthor());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String text = (String)attributes.get("text");

		if (text != null) {
			setText(text);
		}

		String author = (String)attributes.get("author");

		if (author != null) {
			setAuthor(author);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _guestbookEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _guestbookEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _guestbookEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _guestbookEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry> toCacheModel() {
		return _guestbookEntry.toCacheModel();
	}

	@Override
	public fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry toEscapedModel() {
		return new GuestbookEntryWrapper(_guestbookEntry.toEscapedModel());
	}

	@Override
	public fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry toUnescapedModel() {
		return new GuestbookEntryWrapper(_guestbookEntry.toUnescapedModel());
	}

	@Override
	public int compareTo(
		fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry guestbookEntry) {
		return _guestbookEntry.compareTo(guestbookEntry);
	}

	@Override
	public int hashCode() {
		return _guestbookEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _guestbookEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new GuestbookEntryWrapper((GuestbookEntry)_guestbookEntry.clone());
	}

	/**
	* Returns the author of this guestbook entry.
	*
	* @return the author of this guestbook entry
	*/
	@Override
	public java.lang.String getAuthor() {
		return _guestbookEntry.getAuthor();
	}

	/**
	* Returns the text of this guestbook entry.
	*
	* @return the text of this guestbook entry
	*/
	@Override
	public java.lang.String getText() {
		return _guestbookEntry.getText();
	}

	/**
	* Returns the user uuid of this guestbook entry.
	*
	* @return the user uuid of this guestbook entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _guestbookEntry.getUserUuid();
	}

	/**
	* Returns the uuid of this guestbook entry.
	*
	* @return the uuid of this guestbook entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _guestbookEntry.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _guestbookEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _guestbookEntry.toXmlString();
	}

	/**
	* Returns the create date of this guestbook entry.
	*
	* @return the create date of this guestbook entry
	*/
	@Override
	public Date getCreateDate() {
		return _guestbookEntry.getCreateDate();
	}

	/**
	* Returns the company ID of this guestbook entry.
	*
	* @return the company ID of this guestbook entry
	*/
	@Override
	public long getCompanyId() {
		return _guestbookEntry.getCompanyId();
	}

	/**
	* Returns the entry ID of this guestbook entry.
	*
	* @return the entry ID of this guestbook entry
	*/
	@Override
	public long getEntryId() {
		return _guestbookEntry.getEntryId();
	}

	/**
	* Returns the group ID of this guestbook entry.
	*
	* @return the group ID of this guestbook entry
	*/
	@Override
	public long getGroupId() {
		return _guestbookEntry.getGroupId();
	}

	/**
	* Returns the primary key of this guestbook entry.
	*
	* @return the primary key of this guestbook entry
	*/
	@Override
	public long getPrimaryKey() {
		return _guestbookEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this guestbook entry.
	*
	* @return the user ID of this guestbook entry
	*/
	@Override
	public long getUserId() {
		return _guestbookEntry.getUserId();
	}

	@Override
	public void persist() {
		_guestbookEntry.persist();
	}

	/**
	* Sets the author of this guestbook entry.
	*
	* @param author the author of this guestbook entry
	*/
	@Override
	public void setAuthor(java.lang.String author) {
		_guestbookEntry.setAuthor(author);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_guestbookEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this guestbook entry.
	*
	* @param companyId the company ID of this guestbook entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_guestbookEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this guestbook entry.
	*
	* @param createDate the create date of this guestbook entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_guestbookEntry.setCreateDate(createDate);
	}

	/**
	* Sets the entry ID of this guestbook entry.
	*
	* @param entryId the entry ID of this guestbook entry
	*/
	@Override
	public void setEntryId(long entryId) {
		_guestbookEntry.setEntryId(entryId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_guestbookEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_guestbookEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_guestbookEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this guestbook entry.
	*
	* @param groupId the group ID of this guestbook entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_guestbookEntry.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_guestbookEntry.setNew(n);
	}

	/**
	* Sets the primary key of this guestbook entry.
	*
	* @param primaryKey the primary key of this guestbook entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_guestbookEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_guestbookEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the text of this guestbook entry.
	*
	* @param text the text of this guestbook entry
	*/
	@Override
	public void setText(java.lang.String text) {
		_guestbookEntry.setText(text);
	}

	/**
	* Sets the user ID of this guestbook entry.
	*
	* @param userId the user ID of this guestbook entry
	*/
	@Override
	public void setUserId(long userId) {
		_guestbookEntry.setUserId(userId);
	}

	/**
	* Sets the user uuid of this guestbook entry.
	*
	* @param userUuid the user uuid of this guestbook entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_guestbookEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this guestbook entry.
	*
	* @param uuid the uuid of this guestbook entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_guestbookEntry.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GuestbookEntryWrapper)) {
			return false;
		}

		GuestbookEntryWrapper guestbookEntryWrapper = (GuestbookEntryWrapper)obj;

		if (Objects.equals(_guestbookEntry,
					guestbookEntryWrapper._guestbookEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public GuestbookEntry getWrappedModel() {
		return _guestbookEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _guestbookEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _guestbookEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_guestbookEntry.resetOriginalValues();
	}

	private final GuestbookEntry _guestbookEntry;
}