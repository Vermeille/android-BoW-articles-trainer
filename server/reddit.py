import requests
import json
import time

API_KEY='THE_KEY'
SUBREDDITS='programming+machinelearning'

#Work around a bug in BoW
requests.put("http://localhost:8888/dataset?label=cool&input=_init_&epoch=1",
        headers={'Content-Type': 'application/json'})
requests.put("http://localhost:8888/dataset?label=shit&input=_init_&epoch=1",
        headers={'Content-Type': 'application/json'})

last_update = 0
while True:
    r = requests.get("https://www.reddit.com/r/" + SUBREDDITS + "/new.json?limit=5", headers=
            { 'User-Agent' : 'cool/shit classifier' })
    r = json.loads(r.text)

    for last_post in reversed(r["data"]["children"]):
        last_post = last_post["data"]
        this_update = last_post["created_utc"]
        last_title = last_post["title"].replace('|', '').lower()

        if this_update > last_update:
            last_update = this_update
            resp = requests.get("http://localhost:8888/prediction",
                    params={'input': last_title},
                    headers={'Accept': 'application/json'})
            resp = json.loads(resp.text)

            cool_or_shit = resp["label"]
            if resp["confidence"] > 0.9 and cool_or_shit == 'shit':
                continue
            requests.post("https://gcm-http.googleapis.com/gcm/send",
                    headers={'Authorization': 'key=' + API_KEY,
                            'Content-Type': 'application/json'},
                    data=json.dumps({'to':'/topics/global', 'data':
                                            {'author': last_post["author"],
                                            'txt': last_title,
                                            'url': last_post["url"]}}))


    time.sleep(30)
