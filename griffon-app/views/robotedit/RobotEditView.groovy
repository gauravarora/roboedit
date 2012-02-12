package robotedit

import net.miginfocom.swing.MigLayout

actions {
	action(id: 'openHTMLFile', name: 'Open HTML file..', closure: controller.openHTMLFile)
	action(id: 'addLibrary', name: 'Add Library', closure: controller.addLibrary)
}
mainFrame = application(title: 'RobotEdit',
		preferredSize: [320, 240],
		pack: true,
		//location: [50,50],
		locationByPlatform:true,
		iconImage: tangoIcon('go-home').image) {
			borderLayout()
			jxtaskPaneContainer() {
				jxtaskPane(title: 'Settings') {
					button(addLibrary)
					jxtable(id: 'personsTable') {
						tableFormat = defaultTableFormat(columnNames: model.columns)
						eventTableModel(source: model.settings, format: tableFormat)
					}				
				}
				jxtaskPane(title: 'Variables') {
					button('Add Variable')
					label('something')
				}
			}
			menuBar {
				menu("File") {
					menuItem(openHTMLFile)
					separator()
				}
			}
		}
