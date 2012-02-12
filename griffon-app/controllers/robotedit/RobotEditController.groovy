package robotedit

import java.rmi.server.LoaderHandler;
import java.util.logging.Logger;
import org.ccil.cowan.tagsoup.*
import groovy.xml.*
import javax.xml.parsers.SAXParser

class RobotEditController {
	// these will be injected by Griffon
	def model
	def view

	// void mvcGroupInit(Map args) {
	//    // this method is called after model and view are injected
	// }

	// void mvcGroupDestroy() {
	//    // this method is called when the group is destroyed
	// }


	def openHTMLFile = { evt = null ->
		log.info 'Asked to open HTML file'
		log.info 'Parsing file E:\\Projects-Groovy\\robotedit-trunk\\roboedit2'
		def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
		//slurper.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
		def htmlParser = slurper.parse(getClass().classLoader.getResourceAsStream('y.html'))
		println model.columns
		htmlParser.'**'.findAll{ it.@id == 'setting'}.each {
			it.children().each  { setting ->
				def map = [:]
				def i = model.columns.iterator()

				setting.children().each { c ->
					def str = c.text()
					log.info "text is ${str} and is whitespace? ${str.allWhitespace}"
					if (!str.allWhitespace) {
						def n = i.next().toLowerCase()
						map.put(n, c)
					}
				}
				
				log.info "map is  ${map}"
				model.persons.add(map)

			}
		}
	}

	def addLibrary = { evt = null ->

	}
}
