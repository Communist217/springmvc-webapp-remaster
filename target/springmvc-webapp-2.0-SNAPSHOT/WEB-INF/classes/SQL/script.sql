create table if not exists members
(
    MemberID  int auto_increment
        primary key,
    Username  varchar(45)              not null,
    Password  varchar(45)              not null,
    Fullname  varchar(45) charset utf8 not null,
    Address   varchar(45) charset utf8 not null,
    Phone     varchar(20)              not null,
    Gender    varchar(8)               not null,
    BirthDate date                     null,
    Email     varchar(45)              not null,
    constraint members_Username_uindex
        unique (Username)
);

create table if not exists orders
(
    orderID          int auto_increment
        primary key,
    orderDate        timestamp    not null,
    ShippedDate      date         null,
    RequiredDate     date         not null,
    MemberID         int          not null,
    noteFromCustomer varchar(100) null,
    Comments         varchar(100) null,
    Status           varchar(10)  not null,
    Payment          bigint       not null,
    PaymentMethod    varchar(20)  not null
);

create index orders_members_MemberID_fk
    on orders (MemberID);

create table if not exists producttype
(
    TypeID   int         not null
        primary key,
    Typename varchar(45) not null
);

create table if not exists products
(
    ProductID   int auto_increment
        primary key,
    ProductName varchar(45)  null,
    TypeID      int          not null,
    Description text         null,
    Price       int          null,
    ImageSource varchar(200) null,
    Warranty    varchar(11)  not null,
    Stock       int          null,
    Popularity  int          not null,
    constraint products_producttype_TypeID_fk
        foreign key (TypeID) references producttype (TypeID)
            on update cascade on delete cascade
);

create table if not exists orderdetails
(
    OrderID          int           not null,
    ProductID        int           not null,
    Price            bigint        not null,
    Product_Quantity int default 1 not null,
    Quantity         int           not null,
    primary key (OrderID, ProductID),
    constraint orderdetails_orders_orderID_fk
        foreign key (OrderID) references orders (orderID)
            on update cascade on delete cascade,
    constraint orderdetails_products_ProductID_fk
        foreign key (ProductID) references products (ProductID)
            on update cascade on delete cascade
);

create index fk2_idx
    on orderdetails (ProductID);

create table if not exists preorder
(
    PreorderID bigint auto_increment
        primary key,
    MemberID   int not null,
    ProductID  int not null,
    Quantity   int not null,
    constraint preorder_members_MemberID_fk
        foreign key (MemberID) references members (MemberID)
            on update cascade on delete cascade,
    constraint preorder_products_ProductID_fk
        foreign key (ProductID) references products (ProductID)
            on update cascade on delete cascade
);

create table if not exists review
(
    PostID        int auto_increment
        primary key,
    MemberID      int    not null,
    ProductID     int    not null,
    ReviewComment text   not null,
    RatingValue   double not null,
    constraint Review_unique
        unique (MemberID, ProductID),
    constraint review_MemberID_ProductID_uindex
        unique (MemberID, ProductID),
    constraint review_members_MemberID_fk
        foreign key (MemberID) references members (MemberID)
            on update cascade on delete cascade,
    constraint review_products_ProductID_fk
        foreign key (ProductID) references products (ProductID)
            on update cascade on delete cascade
);

create table if not exists like_review
(
    LikeID   bigint auto_increment
        primary key,
    MemberID int not null,
    PostID   int not null,
    constraint like_review_MemberID_PostID_uindex
        unique (MemberID, PostID),
    constraint like_review_members_MemberID_fk
        foreign key (MemberID) references members (MemberID)
            on update cascade on delete cascade,
    constraint like_review_review_PostID_fk
        foreign key (PostID) references review (PostID)
            on update cascade on delete cascade
);

create table if not exists reply
(
    ReplyID      int auto_increment
        primary key,
    MemberID     int  not null,
    PostID       int  not null,
    ReplyComment text not null,
    constraint Reply_review_PostID_fk
        foreign key (PostID) references review (PostID)
            on update cascade on delete cascade,
    constraint reply_members_MemberID_fk
        foreign key (MemberID) references members (MemberID)
            on update cascade on delete cascade
);

