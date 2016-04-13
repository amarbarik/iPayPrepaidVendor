CREATE TABLE meter (
  meter_id     NUMERIC(18) PRIMARY KEY,
  meter_number VARCHAR(40)                 NOT NULL,
  address      VARCHAR(255)                NOT NULL,
  version      INTEGER                     NOT NULL,
  enabled      BIT DEFAULT '1'             NOT NULL
);

CREATE TABLE pay_type (
  pay_type_id NUMERIC(18) PRIMARY KEY,
  name        VARCHAR(30)                     NOT NULL,
  description VARCHAR(255)                    NULL,
  version     INTEGER                         NOT NULL,
  enabled     BIT DEFAULT '1'                 NOT NULL

);

