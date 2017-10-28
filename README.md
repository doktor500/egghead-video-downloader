#### Allows to download full courses for owners of egghead.io pro accounts

Create jar:

    ./gradlew uberjar
    
Execute:

    java -jar build/libs/egghead-video-downloader USER_EMAIL USER_PASSWORD COURSE_URL
