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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link fi.soveltia.arquillian.servicebuilder.service.http.GuestbookEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see fi.soveltia.arquillian.servicebuilder.service.http.GuestbookEntryServiceSoap
 * @generated
 */
@ProviderType
public class GuestbookEntrySoap implements Serializable {
	public static GuestbookEntrySoap toSoapModel(GuestbookEntry model) {
		GuestbookEntrySoap soapModel = new GuestbookEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setEntryId(model.getEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setText(model.getText());
		soapModel.setAuthor(model.getAuthor());

		return soapModel;
	}

	public static GuestbookEntrySoap[] toSoapModels(GuestbookEntry[] models) {
		GuestbookEntrySoap[] soapModels = new GuestbookEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GuestbookEntrySoap[][] toSoapModels(GuestbookEntry[][] models) {
		GuestbookEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GuestbookEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new GuestbookEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GuestbookEntrySoap[] toSoapModels(List<GuestbookEntry> models) {
		List<GuestbookEntrySoap> soapModels = new ArrayList<GuestbookEntrySoap>(models.size());

		for (GuestbookEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GuestbookEntrySoap[soapModels.size()]);
	}

	public GuestbookEntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getText() {
		return _text;
	}

	public void setText(String text) {
		_text = text;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	private String _uuid;
	private long _entryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private String _text;
	private String _author;
}