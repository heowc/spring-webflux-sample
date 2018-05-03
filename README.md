# 방법

- CUD에서 Defer 활용
- `publishOn`과 `subscribeOn`에 스케줄러 적용

### 1. SELECT

```
2018-05-03 22:48:56.635 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
2018-05-03 22:48:56.636 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:48:56.638 DEBUG 26826 --- [    find-sub-16] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:48:56.639 DEBUG 26826 --- [    find-sub-16] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:48:56.640  INFO 26826 --- [    find-sub-16] reactor.Mono.OnAssembly.12               : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-03 22:48:56.640  INFO 26826 --- [    find-sub-16] reactor.Mono.OnAssembly.12               : | request(1)
2018-05-03 22:48:56.640  INFO 26826 --- [    find-sub-16] reactor.Mono.OnAssembly.12               : | onNext(User{id=null, fullName='null', age=null, fullAddress='null'})
2018-05-03 22:48:56.641  INFO 26826 --- [    find-sub-16] reactor.Mono.OnAssembly.12               : | onComplete()

```

### 2. INSERT

```text
2018-05-03 22:53:03.820 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
2018-05-03 22:53:03.826  INFO 26826 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.14               : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-03 22:53:03.826  INFO 26826 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.14               : | request(unbounded)
2018-05-03 22:53:03.826  INFO 26826 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.14               : | onNext(User{id=1, fullName='HEO WON CHUL', age=27, fullAddress='Seoul'})
2018-05-03 22:53:03.826  INFO 26826 --- [ctor-http-nio-2] reactor.Mono.OnAssembly.14               : | onComplete()
2018-05-03 22:53:03.826 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:53:03.827 DEBUG 26826 --- [     add-pub-18] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:53:03.841 DEBUG 26826 --- [     add-pub-18] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    insert 
    into
        user
        (age, full_address, full_name, id) 
    values
        (?, ?, ?, ?)
```

### 3. UPDATE

```text
2018-05-03 22:54:14.032 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
2018-05-03 22:54:14.034 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:54:14.036 DEBUG 26826 --- [    find-sub-19] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:54:14.039 DEBUG 26826 --- [    find-sub-19] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:54:14.040  INFO 26826 --- [    find-sub-19] reactor.Mono.OnAssembly.15               : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-03 22:54:14.040  INFO 26826 --- [    find-sub-19] reactor.Mono.OnAssembly.15               : | request(unbounded)
2018-05-03 22:54:14.040  INFO 26826 --- [    find-sub-19] reactor.Mono.OnAssembly.15               : | onNext(User{id=1, fullName='HEO WON CHUL', age=27, fullAddress='Seoul'})
2018-05-03 22:54:14.040  INFO 26826 --- [    find-sub-19] reactor.Mono.OnAssembly.15               : | onComplete()
2018-05-03 22:54:14.043 DEBUG 26826 --- [  modify-pub-20] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:54:14.046 DEBUG 26826 --- [  modify-pub-20] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    update
        user 
    set
        age=?,
        full_address=?,
        full_name=? 
    where
        id=?
```

### 4. DELETE

```text
2018-05-03 22:55:25.208 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : begin
2018-05-03 22:55:25.211 DEBUG 26826 --- [ctor-http-nio-2] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:55:25.211 DEBUG 26826 --- [    find-sub-21] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:55:25.213 DEBUG 26826 --- [    find-sub-21] o.h.e.t.internal.TransactionImpl         : committing
2018-05-03 22:55:25.213  INFO 26826 --- [    find-sub-21] reactor.Mono.OnAssembly.16               : | onSubscribe([Fuseable] FluxOnAssembly.OnAssemblySubscriber)
2018-05-03 22:55:25.214  INFO 26826 --- [    find-sub-21] reactor.Mono.OnAssembly.16               : | request(unbounded)
2018-05-03 22:55:25.214  INFO 26826 --- [    find-sub-21] reactor.Mono.OnAssembly.16               : | onNext(User{id=1, fullName='heo won chul', age=27, fullAddress='Seoul'})
2018-05-03 22:55:25.214  INFO 26826 --- [    find-sub-21] reactor.Mono.OnAssembly.16               : | onComplete()
2018-05-03 22:55:25.215 DEBUG 26826 --- [  remove-pub-22] o.h.e.t.internal.TransactionImpl         : begin
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
2018-05-03 22:55:25.218 DEBUG 26826 --- [  remove-pub-22] o.h.e.t.internal.TransactionImpl         : committing
Hibernate: 
    delete 
    from
        user 
    where
        id=?
```

> nio Thread가 아닌 별도 Thread에서 동작되어 초기에 트랜잭션이 끊김
> Publish와 Subscribe와 서로 다른 Thread로 동작하기 때문에 영속성이 깨짐(트랜잭션이 끊김)