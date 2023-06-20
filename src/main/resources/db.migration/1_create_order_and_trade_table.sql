CREATE TABLE "order"
(
    id         text      not null,
    created_at timestamp not null,
    type       text      not null,
    price      int       not null,
    quantity   int       not null,
    constraint "order_id_pk" primary key (id)
);

CREATE TABLE "trade"
(
    id                text not null,
    aggressingOrderId text not null,
    restingOrderId    text not null,
    price             int  not null,
    quantity          int  not null,
    constraint "trade_id_pk" primary key (id)
);