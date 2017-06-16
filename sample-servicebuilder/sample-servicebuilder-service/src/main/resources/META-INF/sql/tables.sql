create table svc_test_GuestbookEntry (
	uuid_ VARCHAR(75) null,
	entryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	text_ VARCHAR(75) null,
	author VARCHAR(75) null
);