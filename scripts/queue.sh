#!/bin/bash
cd `dirname $0`/..

JSON=$(cat twitter.json)
JSON_LENGTH=$(echo $JSON | jq ".statuses | length")
for INDEX in $( seq 0 $(($JSON_LENGTH - 1)) )
do
  TEXT=$(echo $JSON | jq .statuses[$INDEX].text | sed -e 's/^"//' -e 's/"$//')
  JSON_STRING=$(jq -n --arg text "$TEXT" '{text: $text}')
  echo $JSON_STRING | jq . > queue.json
  curl -X POST -d @queue.json -H "Content-Type: application/json" http://localhost:9000/queues/enqueue
  echo -n -e "\n"
done
