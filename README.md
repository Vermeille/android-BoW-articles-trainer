Watizdis
========

My Reddit articles classifier will send push notifications to your phone for
its training. This is the phone part.

Makitwurk
=========

Go
[there](https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cntapp=Default%20Demo%20App&cntpkg=gcm.play.android.samples.com.gcmquickstart&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fstart%3Fconfigured%3Dtrue&cntlbl=Continue%20Try%20Cloud%20Messaging)
accept the app name (WELL IF YOU WANT SOMETHING BETTER THAN "DEFAULT DEMO APP"
DO A PULL REQUEST, I DON'T CARE ABOUT THAT KIND OF DETAILS), the package name
(SAME, DON'T WHINE, FIX), check cloud-messaging / GCM, save the json file, and
copy it in the android/app/ directory.

Write the API key in a file named server/key.

Change the variable SUBREDDITS to a +-separated list of subreddits you want to
be notified about.

Go in android/app/src/main/java/gcm/play/android/samples/com/gcmquickstart/VoteActivity.java
and change the `VOTE_URL` to wherever you deployed.

Open the android/ directory with Android Studio, plug your phone, compile it, ignore the
stupid activity that opens up (AGAIN, FIX IT, I DON'T GIVE A SHIT, IT WORKS).

You're done with the app.

You should get 100 notifications on the first launch.

Server side:

    sudo docker build -t reddit .
    sudo docker --net="host" -t reddit

Enjoy, don't whine, and fix the shit you don't like on your own. The app is
stupid enough to be fixed by a 3yo kido.

