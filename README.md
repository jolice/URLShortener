# URLShortener

[![Build Status](https://travis-ci.org/riguron/URLShortener.svg?branch=master)](https://travis-ci.org/riguron/URLShortener)
[![codecov](https://codecov.io/gh/riguron/URLShortener/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/URLShortener)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f934ddd367cc493d8ba463f87740eb06)](https://www.codacy.com/manual/riguron/URLShortener?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=riguron/URLShortener&amp;utm_campaign=Badge_Grade)
[![HitCount](http://hits.dwyl.io/riguron/URLShortener.svg)](http://hits.dwyl.io/riguron/URLShortener)

Plain implementation of the URL shortening service.

# Running

To start a web an application, clone the project and run it:

```
git clone git@github.com:riguron/URLShortener.git
mvn clean spring-boot:run
```

# Features

- Configurable HTTP redirect status code, either 301 or 302
- Keeping count of redirections for each URL
- Fetching URLs shortened by a certain account
- HTTP Basic authentication
- Access through the secured REST API

# Technologies used

- Spring Boot
- Spring Security
- Spring Data 
- Hibernate 5
- HSQL Database
- Lombok
- Maven

