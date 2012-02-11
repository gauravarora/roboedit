package robotedit

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
			// add content here
			label('Content Goes Here') // delete me
			jxtaskPaneContainer {
				jxtaskPane(title: 'Settings') {
					button(addLibrary)
					jxtable(id: 'personsTable') {
						tableFormat = defaultTableFormat(columnNames: ['Name', 'LastName'])
						// tableFormat = defaultAdvancedTableFormat(columns: [[name:'Name'], [name: 'LastName']])
						eventTableModel(source: model.persons, format: tableFormat)
						installTableComparatorChooser(source: model.persons)
					}				}
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

