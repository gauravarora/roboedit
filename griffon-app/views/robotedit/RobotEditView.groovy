package robotedit

import net.miginfocom.swing.MigLayout

actions {
	action(id: 'openHTMLFile', name: 'Open HTML file..', closure: controller.openHTMLFile)
	action(id: 'addLibrary', name: 'Add Library', closure: controller.addLibrary)
}
fileChooserWindow = fileChooser()

mainFrame = application(title: 'RobotEdit',
		preferredSize: [600, 600],
		pack: true,
		location: [50,50],
		locationByPlatform:true,
		iconImage: tangoIcon('go-home').image) {
			borderLayout()
			jxtaskPaneContainer() {
				jxtaskPane(title: 'Settings') {
					button(addLibrary)
					jxtable(id: 'settingsTable') {
						tableFormat = defaultTableFormat(columnNames: model.columns)
						eventTableModel(source: model.settings, format: tableFormat)
					}
				}
				jxtaskPane(title: 'Variables') {
					button('Add Variable')
					jxtable(id: 'settingsTable') {
						tableFormat = defaultTableFormat(columnNames: model.columns)
						eventTableModel(source: model.variables, format: tableFormat)
					}
				}
				jxtaskPane(title: 'Test Cases') {
					button('Add Variable')
					jxtable(id: 'testcasesTable') {
						tableFormat = defaultTableFormat(columnNames: model.columns)
						eventTableModel(source: model.testcases, format: tableFormat)
					}
				}
				jxtaskPane(title: 'Keywords') {
					button('Add Variable')
					jxtable(id: 'keywordsTable') {
						tableFormat = defaultTableFormat(columnNames: model.columns)
						eventTableModel(source: model.keywords, format: tableFormat)
					}
				}

			}
			menuBar {
				menu("File") {
					menuItem(openHTMLFile)
					separator()
				}
			}
		}
