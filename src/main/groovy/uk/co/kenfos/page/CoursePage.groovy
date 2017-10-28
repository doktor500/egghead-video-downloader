package uk.co.kenfos.page

import geb.Page
import org.apache.commons.io.FileUtils

class CoursePage extends Page {

    private static final LINK_ATTRIBUTE = 'href'
    private static final HTTPS = 'https://'

    static url = 'https://egghead.io/'

    static content = {
        lessonLinks(required: false) { $('div.flex.flex-column.flex-row-ns > a') }
        videoTitle(required: false)  { $('h1') }
        videoLink(required: false)   { $('a.f6') }
    }

    void downloadVideos(String course) {
        def links = lessonLinks.collect { element -> element.getAttribute(LINK_ATTRIBUTE) }
        links.collect {
            browser.go(it)
            downloadVideo(course, videoTitle.text(), videoLink.getAttribute(LINK_ATTRIBUTE))
        }
    }

    private void downloadVideo(String course, String title, String url) {
        println("Downloading: $title")
        FileUtils.copyURLToFile(new URL(url), new File("./videos/${formatCourse(course)}/${title}.mp4"))
    }

    private String formatCourse(String course) {
        course.replace(HTTPS, "")
    }

}
