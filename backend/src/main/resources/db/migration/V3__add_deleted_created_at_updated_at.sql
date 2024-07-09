ALTER TABLE members
    ADD COLUMN created_at DATETIME(6);

ALTER TABLE members
    ADD COLUMN updated_at DATETIME(6);

UPDATE members
SET created_at = CURRENT_TIMESTAMP(6),
    updated_at = CURRENT_TIMESTAMP(6);

ALTER TABLE members
    MODIFY COLUMN created_at DATETIME(6) NOT NULL;

ALTER TABLE members
    MODIFY COLUMN updated_at DATETIME(6) NOT NULL;

alter table members
    add column is_deleted boolean not null default false;
