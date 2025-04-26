ALTER TABLE team_members
    ADD COLUMN id BIGSERIAL;

ALTER TABLE team_members
    DROP CONSTRAINT team_members_pkey;

ALTER TABLE team_members
    ADD CONSTRAINT pk_team_members_id PRIMARY KEY (id);

ALTER TABLE team_members
    ADD CONSTRAINT uq_team_members_user_team UNIQUE (user_id, team_id);
