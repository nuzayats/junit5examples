#!/bin/sh
until echo select 1 | mysql -h localhost -uroot -pmy-secret-pw --protocol tcp --port=3306 > /dev/null 2>&1; do echo 'Waiting for MySQL...'; sleep 2s; done
echo 'MySQL should be running now'
