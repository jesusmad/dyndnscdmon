# dyndnscdmon
Simple Java program to send a GET request to the cdmon Dynamic DNS update API

# Prerequisites
Java

# Installing
Compile Java .class file and configure a cron job

Cron job example, run the program every minute
```
crontab -e 
MAILTO=""
* * * * * cd /path/to/class && java DynIP
```
