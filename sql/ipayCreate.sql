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

CREATE TABLE elec_trans (
  trans_id    NUMERIC(20) PRIMARY KEY,
  ref         VARCHAR(30)                     NULL,
  res_code    VARCHAR(255)                    NULL,
  pay_type_id NUMERIC(18) REFERENCES pay_type (pay_type_id),
  meter_id    NUMERIC(18) REFERENCES meter (meter_id),
  version     INTEGER                         NOT NULL,
  response    VARCHAR(20)                     NULL,
  token_id       NUMERIC(20) REFERENCES token (token_id)
);

CREATE TABLE token (
  token_id       NUMERIC PRIMARY KEY,
  trans_id       NUMERIC(20) REFERENCES elec_trans (trans_id),
  token_type     VARCHAR(30)         NULL,
  units          VARCHAR(20)         NULL,
  recipt_number  VARCHAR(30)         NULL,
  amount         VARCHAR(30)         NULL,
  tax            VARCHAR(30)         NULL,
  msg            VARCHAR(255)        NULL,
  version        INTEGER             NOT NULL,
  date_generated DATE                NULL
);

