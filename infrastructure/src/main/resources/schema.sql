CREATE TABLE BRANDS (
    BRAND_ID UUID PRIMARY KEY,
    BRAND_NAME VARCHAR(255) NOT NULL
);

CREATE TABLE PRODUCTS (
    PRODUCT_ID UUID PRIMARY KEY
);

CREATE TABLE PRICES (
    PRICE_ID UUID PRIMARY KEY,
    BRAND_ID UUID NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_CODE INTEGER NOT NULL,
    PRODUCT_ID UUID NOT NULL,
    PRIORITY INTEGER NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(3) NOT NULL,
    FOREIGN KEY (BRAND_ID) REFERENCES BRANDS(BRAND_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(PRODUCT_ID)
);

CREATE INDEX APPLICABLE_PRICES_SEARCH ON PRICES(BRAND_ID, PRODUCT_ID, START_DATE, END_DATE, PRIORITY DESC);