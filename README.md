# URLShortener

[![Build Status](https://travis-ci.org/riguron/URLShortener.svg?branch=master)](https://travis-ci.org/riguron/URLShortener)
[![codecov](https://codecov.io/gh/riguron/URLShortener/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/URLShortener)
[![HitCount](http://hits.dwyl.io/riguron/URLShortener.svg)](http://hits.dwyl.io/riguron/URLShortener)

Plain implementation of the URL shortening service.

## Features

- Configurable HTTP redirect status code, either 301 or 302
- Keeping count of redirections for each URL
- Fetching URLs shortened by a certain account
- HTTP Basic authentication
- Access through the secured REST API

## Running

To start a web application, clone the project and run it:

```bash
git clone git@github.com:riguron/URLShortener.git
cd URLShortener
mvn clean spring-boot:run
```

## Usage

In order to shorten URLs, you need to open an account:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"AccountId":"JohnDoe"}' \
  http://localhost:8080/api/account
```

The response looks like:

```json
{  
   "success":true,
   "description":"Your account is opened",
   "password":"UY5uD96d"
}
```

Now you are able to shorten URLs:

```bash
curl --header "Content-Type: application/json" \
  --user JohnDoe:UY5uD96d \
  --request POST \
  --data '{"url":"https://www.google.com/"}' \
  http://localhost:8080/api/register
```

Shortening will be returned in the following format:

```json
{  
   "url":"AGiKGd"
}
```

Now, [http://localhost:8080/AGiKGd](http://localhost:8080/AGiKGd) will redirect you to [https://www.google.com/](https://www.google.com/)

You may also view redirection stats for your account:

```bash
curl --header "Content-Type: application/json" \
  --user JohnDoe:UY5uD96d \
  --request GET \
  http://localhost:8080/api/stats
```

Account statistics will be returned in the following format:

```bash
{  
   "https://www.apple.com/":5,
   "https://www.google.com/":7
}
```

## Technologies used

- Spring Boot
- Spring Security
- Spring Data 
- Hibernate 5
- HSQL Database
- Lombok
- Maven

