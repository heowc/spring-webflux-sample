# 빙법

- `@Transactional` 제거
- `publishOn`과 `subscribeOn`에 스케줄러 해제

### 1. SELECT

```text
2018-05-04 00:21:13.191 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
2018-05-04 00:21:13.222 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-04 00:21:13.224  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.1                : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-04 00:21:13.225  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.1                : | request(1)
2018-05-04 00:21:13.226  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.1                : | onNext(User{id=null, fullName='null', age=null, fullAddress='null'})
2018-05-04 00:21:13.269  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.1                : | request(32)
2018-05-04 00:21:13.270  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.1                : | onComplete()
```

### 2. INSERT

```text
2018-05-04 00:23:09.437  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.2                : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-04 00:23:09.437  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.2                : | request(unbounded)
2018-05-04 00:23:09.438  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.2                : | onNext(User{id=1, fullName='HEO WON CHUL', age=27, fullAddress='Seoul'})
2018-05-04 00:23:09.438 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
Hibernate: 
    call next value for hibernate_sequence
2018-05-04 00:23:09.475 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    insert 
    into
        user
        (age, full_address, full_name, id) 
    values
        (?, ?, ?, ?)
2018-05-04 00:23:09.490  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.2                : | onComplete()
```

### 3. UPDATE

```text
2018-05-04 00:23:35.635 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
2018-05-04 00:23:35.640 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-04 00:23:35.641  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.3                : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-04 00:23:35.641  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.3                : | request(unbounded)
2018-05-04 00:23:35.641  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.3                : | onNext(User{id=1, fullName='HEO WON CHUL', age=27, fullAddress='Seoul'})
2018-05-04 00:23:35.641 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
2018-05-04 00:23:35.643 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    update
        user 
    set
        age=?,
        full_address=?,
        full_name=? 
    where
        id=?
2018-05-04 00:23:35.649  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.3                : | onComplete()
```

### 3. DELETE

```text
2018-05-04 00:23:58.964 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
2018-05-04 00:23:58.966 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-04 00:23:58.968  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.4                : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-04 00:23:58.968  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.4                : | request(unbounded)
2018-05-04 00:23:58.968  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.4                : | onNext(User{id=1, fullName='heo won chul', age=27, fullAddress='Seoul'})
2018-05-04 00:23:58.969 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
Hibernate: 
    select
        user0_.id as id1_0_0_,
        user0_.age as age2_0_0_,
        user0_.full_address as full_add3_0_0_,
        user0_.full_name as full_nam4_0_0_ 
    from
        user user0_ 
    where
        user0_.id=?
2018-05-04 00:23:58.976 DEBUG 25560 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    delete 
    from
        user 
    where
        id=?
2018-05-04 00:23:58.990  INFO 25560 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.4                : | onComplete()
```

# 문제점

- nio Thread만 사용(효율적인 것인가?)
- 아직 **UPDATE**와 **DELETE**에서는 영속성이 깨지고 있음