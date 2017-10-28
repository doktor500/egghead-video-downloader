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
        def links = lessonLinks.collect { element -> element.attr(LINK_ATTRIBUTE) }
        links.eachWithIndex { link, index ->
            browser.go(link)
            downloadVideo(formatCourse(course), formatTitle(index, videoTitle.text()), videoLink.attr(LINK_ATTRIBUTE))
        }
    }

    private void downloadVideo(String course, String title, String url) {
        println("Downloading: $title")
        FileUtils.copyURLToFile(new URL(url), new File("./videos/${course}/${title}.mp4"))
    }

    private String formatCourse(String course) {
        course.replace(HTTPS, "")
    }

    private String formatTitle(Integer index, String title) {
        "${index}-${title}"
    }
}
