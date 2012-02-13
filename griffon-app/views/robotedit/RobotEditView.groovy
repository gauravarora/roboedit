package robotedit


import java.awt.ScrollPane;

import javax.swing.JFileChooser

import griffon.util.Metadata

actions {
	action(id: 'openHTMLFile', name: 'Open HTML file..', closure: controller.openHTMLFile, mnemonic: 'O', accelerator: shortcut('O'))
	action(id: 'addLibrary', name: 'Add Library', closure: controller.addLibrary, mnemonic: 'L', accelerator: shortcut('L'))
	action(id: 'quitAction', name: 'Quit', mnemonic: 'Q', accelerator: shortcut('Q'), closure: controller.quit)
}
mainFrame = application(title: 'RobotEdit',
		preferredSize: [600, 600],
		pack: true,
		location: [250, 25],
		locationByPlatform:true,
		iconImage: tangoIcon('go-home').image) {
			scrollPane() {
				jxtaskPaneContainer() {
					jxtaskPane(title: 'Settings', collapsed: false) {
						panel() { button(addLibrary) }
						scrollPane() {
							jxtable(id: 'settingsTable', autoResizeMode: JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS, autoCreateRowSorter: false, rowSorter: null) {
								tableFormat = defaultTableFormat(columnNames: model.columns)
								eventTableModel(source: model.settings, format: tableFormat)
							}
						}
					}
					jxtaskPane(title: 'Variables', collapsed: true) {
						panel() { button('Add Variable') }
						scrollPane() {
							jxtable(id: 'settingsTable', autoResizeMode: JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS, autoCreateRowSorter: false, rowSorter: null) {
								tableFormat = defaultTableFormat(columnNames: model.columns)
								eventTableModel(source: model.variables, format: tableFormat)
							}
						}
					}
					jxtaskPane(title: 'Test Cases', collapsed: true) {
						panel() { button('Add Variable') }
						scrollPane() {
							jxtable(id: 'testcasesTable', autoResizeMode: JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS, autoCreateRowSorter: false, rowSorter: null) {
								tableFormat = defaultTableFormat(columnNames: model.columns)
								eventTableModel(source: model.testcases, format: tableFormat)
							}
						}
					}
					jxtaskPane(title: 'Keywords', collapsed: true) {
						panel() { button('Add Variable') }
						scrollPane() {
							jxtable(id: 'keywordsTable', autoResizeMode: JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS, autoCreateRowSorter: false, rowSorter: null) {
								tableFormat = defaultTableFormat(columnNames: model.columns)
								eventTableModel(source: model.keywords, format: tableFormat)
							}
						}
					}
				}
			}
			menuBar {
				menu("File") {
					menuItem(openHTMLFile)
					separator()
					menuItem(quitAction)
				}
			}
		}