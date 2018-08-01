import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.util.ConfigureUtil

class IdeExtension {
    final SpringBootLauncherExtension springBoot
    final GwtLauncherExtension gwt
    
    IdeExtension(Project project) {
        springBoot = this.extensions.create('springBoot', SpringBootLauncherExtension, project)
        gwt = this.extensions.create('gwt', GwtLauncherExtension, project)
    }  
}
