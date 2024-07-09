ALTER TABLE MEMBERS
    ADD COLUMN created_at DATETIME(6);

ALTER TABLE MEMBERS
    ADD COLUMN updated_at DATETIME(6);

UPDATE MEMBERS
SET created_at = CURRENT_TIMESTAMP(6),
    updated_at = CURRENT_TIMESTAMP(6);

ALTER TABLE MEMBERS
    MODIFY COLUMN created_at DATETIME(6) NOT NULL;

ALTER TABLE MEMBERS
    MODIFY COLUMN updated_at DATETIME(6) NOT NULL;


alter table members
    add column is_deleted boolean not null default false;
