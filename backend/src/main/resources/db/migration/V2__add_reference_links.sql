alter table interview_items
    add column
        reference_links varchar(1000);

alter table tail_questions
    add column
        reference_links varchar(1000);