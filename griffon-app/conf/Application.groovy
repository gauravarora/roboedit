application {
    title = 'RobotEdit'
    startupGroups = ['robotEdit']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "robotEdit"
    'robotEdit' {
        model      = 'robotedit.RobotEditModel'
        view       = 'robotedit.RobotEditView'
        controller = 'robotedit.RobotEditController'
    }

}
