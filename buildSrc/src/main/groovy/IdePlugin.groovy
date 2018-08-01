import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Delete
import org.gradle.plugins.ide.eclipse.EclipsePlugin

import groovy.text.GStringTemplateEngine
import groovy.transform.CompileStatic

class IdePlugin implements Plugin<Project> {
    
    @Override
    public void apply(Project project) {
        IdeExtension extension = project.extensions.create('ide', IdeExtension, project)
        
        if(project.plugins.hasPlugin(EclipsePlugin)) {
            applyDefaults(extension, project)
            createEclipseLauncherTasks(project, extension)
        }
    }

    private createEclipseLauncherTasks(Project project, IdeExtension extension) {
        Task eclipseSpringBootLauncherTask = project.tasks.create('eclipseSpringBootLauncher', SpringBootEclipseLauncherTask, {
            it.mainClass = extension.springBoot.mainClass
            it.profiles = extension.springBoot.profiles
            it.launcherFile = extension.springBoot.launcherFile
        })
        Task eclipseGwtCodeServerLauncherTask = project.tasks.create('eclipseGwtCodeServerLauncher', GwtEclipseLauncherTask, {
            it.modules = extension.gwt.modules
            it.launcherFile = extension.gwt.launcherFile
        })
        Task eclipseLaunchers = project.tasks.create('eclipseLaunchers')
        eclipseLaunchers.dependsOn eclipseSpringBootLauncherTask, eclipseGwtCodeServerLauncherTask
        eclipseLaunchers.group = 'IDE'
        eclipseLaunchers.description = 'Generates Eclipse launchers'

        project.tasks['eclipse'].dependsOn eclipseLaunchers
        
        Task cleanSpringBootLauncherTask = project.tasks.create('cleanSpringBootLauncher', Delete, {
            it.delete = extension.springBoot.launcherFile
        })
        Task cleanGwtCodeServerLauncherTask = project.tasks.create('cleanGwtCodeServerLauncher', Delete, {
            it.delete = extension.gwt.launcherFile
        })
        Task cleanEclipseLaunchers = project.tasks.create('cleanEclipseLaunchers')
        cleanEclipseLaunchers.dependsOn cleanSpringBootLauncherTask, cleanGwtCodeServerLauncherTask
        cleanEclipseLaunchers.group = 'IDE'
        cleanEclipseLaunchers.description = 'Remove generated Eclipse launchers'
        
        project.tasks['cleanEclipse'].dependsOn cleanEclipseLaunchers
    }
    
    private void applyDefaults(IdeExtension extension, Project project){
        def springBootExtension = project.extensions.findByName('springBoot')
        if(springBootExtension != null && springBootExtension.mainClassName != null) {
            extension.springBoot.mainClass = springBootExtension.mainClassName
        }
        
        def gwtExtension = project.extensions.findByName('gwt')
        if(gwtExtension != null && gwtExtension.modules != null) {
            extension.gwt.modules = gwtExtension.modules.join(' ')
        }
    }
}
