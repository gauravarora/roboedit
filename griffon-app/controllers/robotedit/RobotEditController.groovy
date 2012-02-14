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

		def openResult
		def fileChooserWindow
		def baseDir = Metadata.getCurrent().getGriffonStartDirSafe() as File
		log.info 'basedir -> ' +  baseDir

		edt {
			fileChooserWindow = builder.fileChooser(currentDirectory: baseDir, dialogTitle: 'Choose an Html file',
					fileSelectionMode: JFileChooser.FILES_ONLY)

			openResult = fileChooserWindow.showOpenDialog(view.mainFrame)
		}

		if(openResult != JFileChooser.APPROVE_OPTION) return //user cancelled
		def configFile = fileChooserWindow.selectedFile

		log.info 'Parsing file ' + configFile
		def parser = new org.cyberneko.html.parsers.SAXParser()
		parser.setFeature('http://xml.org/sax/features/namespaces', false)

		def slurper = new XmlSlurper(parser)
		def page = slurper.parse(getClass().classLoader.getResourceAsStream('y.html'))

		def settingsTableNode = page.BODY.TABLE.find { table ->
			table.TBODY.TR.TH.text() == 'Settings'
		}
		populateTable(settingsTableNode, model.settings)

		def variableTableNode = page.BODY.TABLE.find { table ->
			table.TBODY.TR.TH.text() == 'Variables'
		}
		populateTable(variableTableNode, model.variables)

		def testcaseTableNode = page.BODY.TABLE.find { table ->
			table.TBODY.TR.TH.text() == 'Test Cases'
		}
		populateTable(testcaseTableNode, model.testcases)

		def keywordTableNode = page.BODY.TABLE.find { table ->
			table.TBODY.TR.TH.text() == 'Keywords'
		}
		populateTable(keywordTableNode, model.keywords)
	}

	def populateTable = { table, list ->
		def rowCnt = table.TBODY.TR.size() - 1
		table.TBODY.TR[1..rowCnt].eachWithIndex { row, rnum ->
			println row
			def map = [:]
			def i = model.columns.iterator()

			row.TD.eachWithIndex { cell, idx ->
				def str = cell.text()
				if (!str.allWhitespace && idx < model.columns.size()) {
					def n = i.next().toLowerCase()
					map.put(n, str)
				}
			}
			log.info "map is  ${map}"
			if (map.size() > 0) {
				list.add(map)
			}
			log.info 'rowCnt: ' + rowCnt + '; rnum: ' + rnum
			if ((rowCnt - 1)   == rnum) {
				def tmap = [:]
				model.columns.each {tmap.put( it.toLowerCase(), '' )}
				list.add(tmap)
				log.info "empty map is  ${tmap}"
			}
		}
	}

	def addLibrary = { evt = null -> println 'Add lib called' }
	def quit = { app.shutdown() }
}