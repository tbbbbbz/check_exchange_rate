# ExchangeRate
I remember when I first came to US, I have to calculate every USD price to Chinese Yuan price in order to have a sense of how really the value means.  This app is built to provide the calucation service.

ExchangeRate is a RESTFUL service that provides user to check how much a price is in Chinese Yuan providing a price in USD. 

## User case
### Server side
Start the server:

```console
java -jar check_exchange_rate-*.jar
```

### Client side
#### Using Terminal As Example
```console
$ curl localhost:8080/{USD}
$ {"value":{CNY}}
```


