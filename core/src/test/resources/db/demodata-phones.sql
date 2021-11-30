create table colors (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(50),
  UNIQUE (code)
);

create table phones (
  id BIGINT AUTO_INCREMENT primary key,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(254) NOT NULL,
  price FLOAT,
  displaySizeInches FLOAT,
  weightGr SMALLINT,
  lengthMm FLOAT,
  widthMm FLOAT,
  heightMm FLOAT,
  announced DATETIME,
  deviceType VARCHAR(50),
  os VARCHAR(100),
  displayResolution VARCHAR(50),
  pixelDensity SMALLINT,
  displayTechnology VARCHAR(50),
  backCameraMegapixels FLOAT,
  frontCameraMegapixels FLOAT,
  ramGb FLOAT,
  internalStorageGb FLOAT,
  batteryCapacityMah SMALLINT,
  talkTimeHours FLOAT,
  standByTimeHours FLOAT,
  bluetooth VARCHAR(50),
  positioning VARCHAR(100),
  imageUrl VARCHAR(254),
  description VARCHAR(4096),
  CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color (
  phoneId BIGINT,
  colorId BIGINT,
  CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks (
  phoneId BIGINT NOT NULL,
  stock SMALLINT NOT NULL,
  reserved SMALLINT NOT NULL,
  UNIQUE (phoneId),
  CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO phones (id, brand, model, price) VALUES (101, 'Xiaomi', 'Mi 6', 101);
INSERT INTO phones (id, brand, model, price) VALUES (102, 'Samsung', 'S 20', 102);
INSERT INTO phones (id, brand, model, price) VALUES (103, 'Iphone', 'X', 103);
INSERT INTO phones (id, brand, model, price) VALUES (104, 'Huawei', 'P 20', 104);
INSERT INTO phones (id, brand, model, price) VALUES (105, 'Asus', 'ZenPhone 2', 105);
INSERT INTO phones (id, brand, model, price) VALUES (106, 'Colorful', 'Pixel', 0);

INSERT INTO stocks (phoneId, stock, reserved) VALUES (101, 101, 0);
INSERT INTO stocks (phoneId, stock, reserved) VALUES (102, 102, 0);
INSERT INTO stocks (phoneId, stock, reserved) VALUES (103, 103, 0);
INSERT INTO stocks (phoneId, stock, reserved) VALUES (104, 104, 0);
INSERT INTO stocks (phoneId, stock, reserved) VALUES (105, 105, 0);
INSERT INTO stocks (phoneId, stock, reserved) VALUES (106, 0, 0);

INSERT INTO colors VALUES (1, 'white');
INSERT INTO colors VALUES (2, 'black');
INSERT INTO colors VALUES (3, 'gray');
INSERT INTO colors VALUES (4, 'yellow');

INSERT INTO phone2color VALUES (106, 1);
INSERT INTO phone2color VALUES (106, 2);