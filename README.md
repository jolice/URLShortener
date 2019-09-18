# URLShortener

[![Build Status](https://travis-ci.org/riguron/URLShortener.svg?branch=master)](https://travis-ci.org/riguron/URLShortener)
[![codecov](https://codecov.io/gh/riguron/URLShortener/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/URLShortener)
Plain implementation of the URL shortening service.

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
