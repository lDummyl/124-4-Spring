CREATE TABLE driver
(
    id         INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255)       NULL,
    last_name  VARCHAR(255)       NULL,
    age        INT                NULL,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);

CREATE TABLE car
(
    id            INT AUTO_INCREMENT NOT NULL,
    model_name    VARCHAR(255)       NULL,
    car_name      VARCHAR(255)       NULL,
    `description` VARCHAR(255)       NULL,
    driver_id     INT                NULL,
    CONSTRAINT pk_car PRIMARY KEY (id)
);

ALTER TABLE car
    ADD CONSTRAINT FK_CAR_ON_DRIVER FOREIGN KEY (driver_id) REFERENCES driver (id);

CREATE TABLE user
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)       NULL,
    `description` VARCHAR(255)       NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);