# setting

`embedded MongoDB`

```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <scope>runtime</scope>
</dependency>
```

`reactive MongoDB`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>
```


# 결과

- [43b075acd5 - DefaultStudyQueryService.java](https://github.com/heowc/spring-webflux-sample/blob/43b075acd5d2a2ab8158c27799ac9ea906b238bd/src/main/java/com/heowc/webflux/service/DefaultStudyQueryService.java)
- `ReactiveMongoRepository`를 이용하여 나름(?) 손쉽게 활용