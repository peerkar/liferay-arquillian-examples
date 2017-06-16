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

package fi.soveltia.arquillian.servicebuilder.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import fi.soveltia.arquillian.servicebuilder.model.GuestbookEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GuestbookEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see GuestbookEntry
 * @generated
 */
@ProviderType
public class GuestbookEntryCacheModel implements CacheModel<GuestbookEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GuestbookEntryCacheModel)) {
			return false;
		}

		GuestbookEntryCacheModel guestbookEntryCacheModel = (GuestbookEntryCacheModel)obj;

		if (entryId == guestbookEntryCacheModel.entryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", text=");
		sb.append(text);
		sb.append(", author=");
		sb.append(author);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GuestbookEntry toEntityModel() {
		GuestbookEntryImpl guestbookEntryImpl = new GuestbookEntryImpl();

		if (uuid == null) {
			guestbookEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			guestbookEntryImpl.setUuid(uuid);
		}

		guestbookEntryImpl.setEntryId(entryId);
		guestbookEntryImpl.setGroupId(groupId);
		guestbookEntryImpl.setCompanyId(companyId);
		guestbookEntryImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			guestbookEntryImpl.setCreateDate(null);
		}
		else {
			guestbookEntryImpl.setCreateDate(new Date(createDate));
		}

		if (text == null) {
			guestbookEntryImpl.setText(StringPool.BLANK);
		}
		else {
			guestbookEntryImpl.setText(text);
		}

		if (author == null) {
			guestbookEntryImpl.setAuthor(StringPool.BLANK);
		}
		else {
			guestbookEntryImpl.setAuthor(author);
		}

		guestbookEntryImpl.resetOriginalValues();

		return guestbookEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		entryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		text = objectInput.readUTF();
		author = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(entryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (text == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(text);
		}

		if (author == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(author);
		}
	}

	public String uuid;
	public long entryId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public String text;
	public String author;
}