package robotedit


import javax.swing.JFileChooser

import griffon.util.Metadata

actions {
	action(id: 'openHTMLFile', name: 'Open HTML file..', closure: controller.openHTMLFile)
	action(id: 'addLibrary', name: 'Add Library', closure: controller.addLibrary)
}
mainFrame = application(title: 'RobotEdit',
		preferredSize: [600, 600],
		pack: true,
		location: [150, 50],
		locationByPlatform:true,
		iconImage: tangoIcon('go-home').image) {
			borderLayout()
			jxtaskPaneContainer() {
				jxtaskPane(title: 'Settings') {
					button(addLibrary)
					jxtable(id: 'settingsTable', autoResizeMode: JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS) {
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
