-- br linked to brf
ALTER TABLE br
  ADD brf_id BINARY(16);

CREATE TABLE brf
(
  id              BINARY(16) NOT NULL,
  name            varchar(200) NOT NULL,
  status          varchar(200),
  year_registered int,
  member_count    int,
  PRIMARY KEY (id),
  UNIQUE (name)
);



