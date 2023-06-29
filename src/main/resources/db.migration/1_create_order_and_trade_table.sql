CREATE TABLE "order"
(
    id         TEXT NOT NULL,
    created_at TEXT NOT NULL,
    type       TEXT NOT NULL,
    price      INT  NOT NULL,
    quantity   INT  NOT NULL,
    CONSTRAINT "order_id_pk" PRIMARY KEY (id)
);

CREATE TABLE "trade"
(
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    aggressingOrderId TEXT NOT NULL,
    restingOrderId    TEXT NOT NULL,
    price             INT  NOT NULL,
    quantity          INT  NOT NULL
);