package io.github.cottonmc.jsonfactory.gui

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser

internal object Markdown {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()

    fun toHtml(markdown: String) =
        "<html>" + renderer.render(parser.parse(markdown)) + "</html>"
}
