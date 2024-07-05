create table if not exists interview_items
(
    remain_tail_question_count int                               not null,
    score                      int                               null,
    time_to_answer             int                               null,
    created_at                 datetime(6)                       null,
    id                         bigint auto_increment
        primary key,
    interview_id               bigint                            not null,
    member_id                  bigint                            not null,
    question_id                bigint                            not null,
    updated_at                 datetime(6)                       null,
    content                    varchar(2000)                     null,
    feedback_content           varchar(2000)                     null,
    question_content           varchar(255)                      not null,
    tail_question              varchar(255)                      null,
    answer_state               enum ('COMPLETE', 'INIT', 'PASS') null
);

create table if not exists interviews
(
    interview_index     int                       not null,
    size                int                       not null,
    tail_question_depth int                       null,
    time_to_answer      int                       null,
    time_to_think       int                       null,
    created_at          datetime(6)               null,
    id                  bigint auto_increment
        primary key,
    member_id           bigint                    not null,
    updated_at          datetime(6)               null,
    title               varchar(255)              not null,
    interview_state     enum ('DONE', 'PROGRESS') null
);

create table if not exists members
(
    id          bigint auto_increment
        primary key,
    avatar_url  varchar(255) null,
    nickname    varchar(255) not null,
    provider_id varchar(255) null
);

create table if not exists question_sets
(
    default_tail_question_depth int          null,
    default_time_to_answer      int          null,
    default_time_to_think       int          null,
    admin_id                    bigint       not null,
    created_at                  datetime(6)  null,
    id                          bigint auto_increment
        primary key,
    updated_at                  datetime(6)  null,
    description                 varchar(255) null,
    thumbnail_url               varchar(255) null,
    title                       varchar(255) not null
);

create table if not exists questions
(
    sequence        int          not null,
    created_at      datetime(6)  null,
    id              bigint auto_increment primary key,
    question_set_id bigint       not null,
    updated_at      datetime(6)  null,
    content         varchar(255) not null,
    reference_links varchar(255) null,
    constraint FKkpkuc627fwg4g9prwdfb0mj2l
        foreign key (question_set_id) references question_sets (id)
);

create table if not exists refresh_tokens
(
    id    bigint       not null
        primary key,
    token varchar(700) null
);

create table if not exists tail_questions
(
    score                 int                               null,
    time_to_answer        int                               null,
    id                    bigint auto_increment
        primary key,
    interview_id          bigint                            not null,
    interview_question_id bigint                            not null,
    member_id             bigint                            not null,
    content               varchar(2000)                     null,
    feedback_content      varchar(2000)                     null,
    question              varchar(255)                      not null,
    tail_question         varchar(255)                      null,
    answer_state          enum ('COMPLETE', 'INIT', 'PASS') null
);

