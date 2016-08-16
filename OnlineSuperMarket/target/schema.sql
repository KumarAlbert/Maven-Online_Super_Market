
    alter table Cart 
        drop 
        foreign key FK_fyc9sifgf31mfnjljd417gu8f;

    alter table Cart 
        drop 
        foreign key FK_6jg31oniat6mkkocvwq9498ec;

    alter table Product 
        drop 
        foreign key FK_p9vq6pxsbgegp1jqrhnasldwf;

    alter table PurchaseOrder 
        drop 
        foreign key FK_5nos4pw4o7desa2ii18tmsjox;

    alter table Subcategory 
        drop 
        foreign key FK_gw0phpxiin8uxf57yfk3shpnp;

    alter table user_role 
        drop 
        foreign key FK_it77eq964jhfqtu54081ebtio;

    alter table user_role 
        drop 
        foreign key FK_apcc8lxk2xnug8377fatvbn04;

    drop table if exists Cart;

    drop table if exists Category;

    drop table if exists Product;

    drop table if exists PurchaseOrder;

    drop table if exists Subcategory;

    drop table if exists app_user;

    drop table if exists role;

    drop table if exists user_role;

    create table Cart (
        id integer not null,
        createdAt datetime,
        createdBy bigint,
        modifiedAt datetime,
        modifiedBy bigint,
        quantity integer,
        total double precision,
        productId integer,
        purchaseOrderId integer,
        primary key (id)
    ) ENGINE=InnoDB;

    create table Category (
        id integer not null auto_increment,
        createdAt datetime,
        createdBy integer,
        modifiedAt datetime,
        modifiedBy integer,
        name varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table Product (
        id integer not null auto_increment,
        createdAt datetime,
        createdBy integer,
        description varchar(255),
        imageUrl varchar(255),
        modifiedAt datetime,
        modifiedBy bigint,
        name varchar(255),
        price double precision,
        stock integer,
        subcategoryId integer not null,
        primary key (id)
    ) ENGINE=InnoDB;

    create table PurchaseOrder (
        id integer not null auto_increment,
        createdAt datetime,
        createdBy bigint,
        modifiedAt datetime,
        modifiedBy bigint,
        paymentType varchar(255),
        status bit,
        total double precision,
        userId bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table Subcategory (
        id integer not null auto_increment,
        createdAt datetime,
        createdBy integer,
        modifiedAt datetime,
        modifiedBy integer,
        name varchar(255),
        categoryId integer not null,
        primary key (id)
    ) ENGINE=InnoDB;

    create table app_user (
        id bigint not null auto_increment,
        account_expired bit not null,
        account_locked bit not null,
        address varchar(150),
        city varchar(50),
        country varchar(100),
        postal_code varchar(15),
        province varchar(100),
        credentials_expired bit not null,
        email varchar(255) not null,
        account_enabled bit,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        password varchar(255) not null,
        password_hint varchar(255),
        phone_number varchar(255),
        username varchar(50) not null,
        version integer,
        website varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table role (
        id bigint not null auto_increment,
        description varchar(64),
        name varchar(20),
        primary key (id)
    ) ENGINE=InnoDB;

    create table user_role (
        user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) ENGINE=InnoDB;

    alter table app_user 
        add constraint UK_1j9d9a06i600gd43uu3km82jw  unique (email);

    alter table app_user 
        add constraint UK_3k4cplvh82srueuttfkwnylq0  unique (username);

    alter table Cart 
        add constraint FK_fyc9sifgf31mfnjljd417gu8f 
        foreign key (productId) 
        references Product (id);

    alter table Cart 
        add constraint FK_6jg31oniat6mkkocvwq9498ec 
        foreign key (purchaseOrderId) 
        references PurchaseOrder (id);

    alter table Product 
        add constraint FK_p9vq6pxsbgegp1jqrhnasldwf 
        foreign key (subcategoryId) 
        references Subcategory (id);

    alter table PurchaseOrder 
        add constraint FK_5nos4pw4o7desa2ii18tmsjox 
        foreign key (userId) 
        references app_user (id);

    alter table Subcategory 
        add constraint FK_gw0phpxiin8uxf57yfk3shpnp 
        foreign key (categoryId) 
        references Category (id);

    alter table user_role 
        add constraint FK_it77eq964jhfqtu54081ebtio 
        foreign key (role_id) 
        references role (id);

    alter table user_role 
        add constraint FK_apcc8lxk2xnug8377fatvbn04 
        foreign key (user_id) 
        references app_user (id);
