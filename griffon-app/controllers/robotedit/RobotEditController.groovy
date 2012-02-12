package robotedit

import org.ccil.cowan.tagsoup.*
import groovy.xml.*

import javax.swing.JFileChooser

class RobotEditController {
	// these will be injected by Griffon
	def model
	def view
	def builder

	// void mvcGroupInit(Map args) {
	//    // this method is called after model and view are injected
	// }

	// void mvcGroupDestroy() {
	//    // this method is called when the group is destroyed
	// }

	def openHTMLFile = { evt = null ->
		log.info 'Asked to open HTML file'

		def openResult;
		def fileChooserWindow;
		def baseDir = Metadata.getCurrent().getGriffonStartDirSafe() as File
		log.info 'basedir -> ' +  baseDir

		edt {
			//			def fileChooserWindow = builder.fileChooser(currentDirectory: baseDir, dialogTitle: 'Choose an Html file',
			//					fileSelectionMode: JFileChooser.FILES_ONLY,
			//					fileFilter: [getDescription: {-> 'HTML files'},accept: { f->
			//							f ==~ /.*?\.html/ || f.isDirectory()
			//						}] as FileFilter)
			fileChooserWindow = builder.fileChooser(currentDirectory: baseDir, dialogTitle: 'Choose an Html file',
					fileSelectionMode: JFileChooser.FILES_ONLY)

			openResult = fileChooserWindow.showOpenDialog(view.mainFrame)
		}

		if(openResult != JFileChooser.APPROVE_OPTION) return //user cancelled
		def configFile = fileChooserWindow.selectedFile

		log.info 'Parsing file ' + configFile
		def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
		//slurper.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
		def htmlParser = slurper.parse(getClass().classLoader.getResourceAsStream('y.html'))
		println model.columns
		htmlParser.'**'.findAll{ it.@id == 'setting'}.each {
			addSettings(model.settings, it)
		}
		htmlParser.'**'.findAll{ it.@id == 'variable'}.each {
			addSettings(model.variables, it)
		}
		htmlParser.'**'.findAll{ it.@id == 'testcase'}.each {
			addSettings(model.testcases, it)
		}
		htmlParser.'**'.findAll{ it.@id == 'keyword'}.each {
			addSettings(model.keywords, it)
		}
	}

	def addLibrary = { evt = null ->

	}

	def addSettings = { table, node ->
		log.info "Node has ${node.children().size()} children"

		node.children().each  { setting ->
			def map = [:]
			def i = model.columns.iterator()

			log.info "Child has ${setting.children().size()} children"

			setting.children().eachWithIndex { c, idx ->
				def str = c.text()
				log.info "text is ${str} and is whitespace? ${str.allWhitespace}"
				if (!str.allWhitespace && idx < model.columns.size()) {
					def n = i.next().toLowerCase()
					map.put(n, c)
				}
			}

			if (map.size() > 0) {
				log.info "map is  ${map}"
				table.add(map)
			}
		}
	}
}
