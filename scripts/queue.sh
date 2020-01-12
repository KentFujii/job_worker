#!/bin/bash
cd `dirname $0`/..

JSON=$(cat twitter.json)
JSON_LENGTH=$(echo $JSON | jq ".statuses | length")
for INDEX in $( seq 0 $(($JSON_LENGTH - 1)) )
do
  ROW=$(echo $JSON | jq .statuses[$INDEX].text)
  echo $ROW | jq . > queue.json
  curl -X POST -d @queue.json -H "Content-Type: application/json" http://localhost:9000/message/enqueue
  echo -n -e "\n"
done
