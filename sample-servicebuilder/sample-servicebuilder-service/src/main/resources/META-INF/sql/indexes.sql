create index IX_7677D1F9 on svc_test_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D98E82BB on svc_test_GuestbookEntry (uuid_[$COLUMN_LENGTH:75$], groupId);