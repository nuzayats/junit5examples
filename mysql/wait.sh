#!/bin/sh
until echo select 1 | mysql -h localhost -uroot -pmy-secret-pw --protocol tcp --port=3306; do sleep 2s; done
