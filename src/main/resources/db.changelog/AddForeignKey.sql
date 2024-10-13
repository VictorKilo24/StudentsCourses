--liquibase formatted sql
--changeset vladimir_kushhov:AddForeignKey


ALTER TABLE persons_courses
    ADD CONSTRAINT fk_users
        FOREIGN KEY (persons_id)
            REFERENCES persons(id)
            ON DELETE CASCADE;

ALTER TABLE persons_courses
    ADD CONSTRAINT fk_courses
        FOREIGN KEY (courses_id)
            REFERENCES courses(id)
            ON DELETE CASCADE;